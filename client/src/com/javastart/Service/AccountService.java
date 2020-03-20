package com.javastart.Service;

import com.javastart.Entity.Account;
import com.javastart.Entity.Bill;
import com.javastart.Entity.Payment;

import java.util.Scanner;

public class AccountService {

    private Scanner scanner = new Scanner(System.in);

    public Account sendByKeyboard() {
        try {
            System.out.println("Insert id: ");
            int id = scanner.nextInt();
            System.out.println("Insert name: ");
            String name = scanner.next();
            System.out.println("Insert bill: ");
            int bill = scanner.nextInt();

            Account account = makeAccount(id, name, bill);
            return account;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public Account makeAccount(int id, String name, int accountBill){
        Bill bill = new Bill();
        bill.setAccountBill(accountBill);
        Account account = new Account(id, name, bill);
        return account;
    }


}
