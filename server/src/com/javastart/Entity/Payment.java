package com.javastart.Entity;

public class Payment {

    private int id;
    private int accountPayment;

    public int getAccountPayment() {
        return accountPayment;
    }

    public int getId() {
        return id;
    }

    public Payment() {
    }

    public Payment(int id, int accountPayment) {
        this.id = id;
        this.accountPayment = accountPayment;
    }

}
