package ru.job4j.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Класс реализует сущность серверного бота.
 * Клиент отправляет запросы, сервер отвечает клиенту штатным набором фраз
 */
public class EchoServer {

    public static final String HELLO = "Hello";
    public static final String EXIT = "Exit";
    public static final String WHAT = "What";
    public static final int POSITION = 2;

    /**
     * Метод выделяет значение ключа msg в параметрах строки клиентского HTTP
     * запроса, также выводит в консоль строку клиентского запроса.
     * @param reader буфферизированный поток входных данных клиентского
     * запроса на входе
     * @return возвращает значение ключа msg в параметрах строки клиентского
     * HTTP запроса
     */
    private String getRequestCommand(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String str = reader.readLine();
        sb.append(str).append(System.lineSeparator());
        String requestCommand = Arrays.stream(str.split(" "))
                .flatMap(f -> Arrays.stream(f.split("=")))
                .collect(Collectors.toList()).get(POSITION);
        for (str = reader.readLine(); str != null && !str.isEmpty(); str = reader.readLine()) {
            sb.append(str).append(System.lineSeparator());
        }
        System.out.println(sb);
        return requestCommand;
    }

    /**
     * Метод генерирует ответ серверного бота в зависимости от ключа запроса
     * @param server сущность серверного сокета на входе
     * @param requestCommand значение клиентского ключа запроса на входе
     * @param out поток для записи выходных данных на входе
     */
    private void getResponse(ServerSocket server, String requestCommand,
                             OutputStream out) throws IOException {
        switch (requestCommand) {
            case HELLO:
                out.write(HELLO.getBytes());
                out.flush();
                break;
            case EXIT:
                server.close();
                out.write((server + " closed").getBytes());
                break;
            default:
                out.write(WHAT.getBytes());
                out.flush();
                break;
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

