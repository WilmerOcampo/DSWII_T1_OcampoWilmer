package com.wilmerocampo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilmerocampo.model.Account;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class JsonService {
    private static final String TXT_EXTENSION = ".txt";
    private int count = 0;
    private final String folderPath;
    private final ObjectMapper objectMapper;

    public JsonService(String folderPath) {
        this.folderPath = folderPath;
        this.objectMapper = new ObjectMapper();
    }

    public List<Account> accounts(String jsonFilePath) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            return objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
            return List.of();
        }
    }

    private List<Account> activeAccounts(List<Account> accounts) {
        return accounts.stream()
                .filter(Account::getStatus)
                .collect(Collectors.toList());
    }

    private Boolean fileExists(String fileName) {
        return new File(folderPath, fileName).exists();
    }

    public void createAccountFiles(List<Account> accounts) {
        List<Account> activeAccounts = activeAccounts(accounts);
        activeAccounts.forEach(account -> createFile(account.getAccountNumber() + TXT_EXTENSION, account));
    }

    private void createFile(String fileName, Account account) {
        if (fileName.isEmpty()) {
            count++;
            fileName = "account" + count + ".txt";
        }
        String fullPath = folderPath + fileName;

        if (fileExists(fileName)) {
            deleteFile(fullPath);
        }

        String content = generateContent(account);

        try {
            FileWriter fileWriter = new FileWriter(fullPath);
            fileWriter.write(content);
            fileWriter.close();
            System.out.println(fileName + " created");
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    private void deleteFile(String fullPath) {
        File file = new File(fullPath);
        if (file.delete()) {
            System.out.println(fullPath + " deleted");
        } else {
            System.out.println(fullPath + " not deleted");
        }
    }

    private static String generateContent(Account account) {
        String message = account.getBalance() > 5000.00
                ? "Usted es apto a participar en el  concurso de la SBS por 10000.00 soles. Suerte!"
                : "Lamentablemente no podrá acceder al concurso de la SBS por 10000.00 soles. Gracias";

        return String.format("""
                        Banco de origen: %s
                        La cuenta con el nro de cuenta: %s no tiene un saldo superior a %s
                        %s""",
                account.getBankName(), account.getAccountNumber(), account.getBalance(), message);
    }
}
