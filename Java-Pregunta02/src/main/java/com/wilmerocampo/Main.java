package com.wilmerocampo;

import com.wilmerocampo.model.Account;
import com.wilmerocampo.service.JsonService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String folderPath = "D:/DSW-II/DSWII_T1_OcampoWilmer/";
        JsonService jsonService = new JsonService(folderPath);
        List<Account> accounts = jsonService.accounts(folderPath + "accounts.json");

        System.out.println(accounts);
        if (accounts != null) {
            jsonService.createAccountFiles(accounts);
        }
    }
}