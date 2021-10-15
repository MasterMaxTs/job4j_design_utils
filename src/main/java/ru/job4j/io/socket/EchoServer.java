package ru.job4j.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EchoServer {

    public static final String HELLO = "Hello";
    public static final String EXIT = "Exit";
    public static final String WHAT = "What";
    public static final int POSITION = 2;

    private String getRequestCommand(BufferedReader reader) throws IOException {
        String requestCommand = "";
        StringBuilder sb = new StringBuilder();
        String str = reader.readLine();
        sb.append(str).append(System.lineSeparator());
        requestCommand = Arrays.stream(str.split(" "))
                .flatMap(f -> Arrays.stream(f.split("=")))
                .collect(Collectors.toList()).get(POSITION);
        for (str = reader.readLine(); str != null && !str.isEmpty(); str = reader.readLine()) {
            sb.append(str).append(System.lineSeparator());
        }
        System.out.println(sb);
        return requestCommand;
    }

    private void getResponse(ServerSocket server, String requestCommand,
                             OutputStream out) throws IOException {
        switch (requestCommand) {
            case HELLO -> {
                out.write("Hello".getBytes());
                out.flush();
            }
            case EXIT -> {
                server.close();
                out.write((server + " closed").getBytes());
            }
            default -> {
                out.write(WHAT.getBytes());
                out.flush();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        EchoServer echoServer = new EchoServer();
        String requestCommand;
        try (ServerSocket server = new ServerSocket(9000)) {
            System.out.println(server + " start");
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    requestCommand = echoServer.getRequestCommand(in);
                    echoServer.getResponse(server, requestCommand, out);
                }
            }
        }
    }
}

