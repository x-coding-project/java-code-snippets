package com.coding.projects;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ReadGitBlob {

    public static void main(String[] args) throws IOException, DataFormatException {
        String filePath ="src/test/resources/7acd32c4e687021ef32db511e8a206129b88ec"; // Read from test resources
        decompressFile(filePath);
    }

    public static void decompressFile(String path) throws DataFormatException, IOException {
        byte[] fileBytes = new byte[0];

        fileBytes = Files.readAllBytes(Paths.get(path));

        Inflater inflater = new Inflater();
        inflater.setInput(fileBytes);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(fileBytes.length);
        byte[] buffer = new byte[1024];

        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();

        // Decode the bytes into a String
        byte[] result = outputStream.toByteArray();
        String outputString = new String(result, 0, result.length, StandardCharsets.UTF_8);
        System.out.println(outputString);

    }
}
