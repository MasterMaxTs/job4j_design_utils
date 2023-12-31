package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "{"
                + "zipCode="
                + zipCode
                + ", phone='"
                + phone
                + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, phone);
    }

    public static void main(String[] args) throws IOException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
            ObjectOutputStream oos =
                    new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
            System.out.println("Object 'contact' " + contact + " serialized "
                    + "in temporary file: " + tempFile.getPath());
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
            ObjectInputStream ois =
                    new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            if (contactFromFile.equals(contact)) {
                System.out.println("Object 'contactFromFile' " + contactFromFile
                        + " is deserialized from temporary file: " + tempFile.getPath());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
