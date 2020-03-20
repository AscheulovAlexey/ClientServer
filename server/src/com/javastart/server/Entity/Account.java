package com.javastart.server.Entity;

public class Account {

    private int id;
    private String name;
    private Bill bill;

    public int getId() {
        return id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getName() {
        return this.name;
    }

    public Account()
    {
    }

    public Account(int id, String name, Bill bill){
        this.id = id;
        this.name = name;
        this.bill = bill;
    }
}