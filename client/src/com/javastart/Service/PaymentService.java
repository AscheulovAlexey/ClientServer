package com.javastart.Service;

import com.javastart.Entity.Payment;

import java.util.Scanner;

public class PaymentService {

    private Scanner scanner = new Scanner(System.in);

    public Payment makePayment() {
        try {
            System.out.println("Insert id: ");
            int id = scanner.nextInt();
            System.out.println("Insert payment: ");
            int amountOfPayment = scanner.nextInt();
            Payment payment = new Payment(id, amountOfPayment);

            return payment;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
