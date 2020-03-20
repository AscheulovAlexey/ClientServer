package com.javastart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javastart.Entity.Account;
import com.javastart.Entity.Payment;
import com.javastart.Service.DataBaseService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    private ServerSocket serverSocket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private int port;
    private ObjectMapper objectMapper;
    private DataBaseService dataBaseService = new DataBaseService();

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException, SQLException {
        System.out.println("---INFO---\nStart program\n");
        createConnection(port);
        Socket clientSocket = serverSocket.accept();
        dataBaseService.createTable();
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        startListener();
        System.out.println("Waiting for client connection...\n");
    }

    private void createConnection(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("---INFO---\nServer is deployed\n");
        } catch (IOException e) {
            System.out.println("---INFO---\nNo connection");
            e.printStackTrace();
        }
    }

    private void startListener() {
        new Thread(() -> {
            while (true) {
                try {
                    if (reader.ready()) {
                        readClientMessage();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }

    public void readClientMessage() throws IOException, SQLException {
        String readLine = reader.readLine();

        if (readLine.equals("exit")) {
            writer.close();
            reader.close();
        }
        if (readLine.contains("name")) {
            readAccount(readLine);
        } else {
            readPayment(readLine);
        }
    }

    private void readAccount(String readLine) throws IOException, SQLException {

        objectMapper = new ObjectMapper();
        Account account = objectMapper.readValue(readLine, Account.class);

        System.out.println("---INFO---\nClient sent message:\n" +
                "New Account: id = " + account.getId() +
                ", name = " + account.getName() +
                ", bill = " + account.getBill().getAccountBill() + "\n");

        writer.write("Server got instance of Account: " +
                "New Account: id = " + account.getId() +
                ", name = " + account.getName() +
                ", bill = " + account.getBill().getAccountBill() + "\n");
        writer.flush();

        dataBaseService.addToTable(account);
        System.out.println("Server wrote instance in database ("
                + account.getId() + ", " + account.getName() + ", " + account.getBill().getAccountBill() + ")\n");

    }

    private void readPayment(String readLine) throws IOException, SQLException {

        objectMapper = new ObjectMapper();
        Payment payment = objectMapper.readValue(readLine, Payment.class);

        System.out.println("---INFO---\nClient sent message:\n" +
                "New Payment: ClientId = " + payment.getId() +
                ", payment = " + payment.getAccountPayment() + "\n");
        writer.write("Server got instance of Payment:\n" +
                "New Payment: ClientId = " + payment.getId() +
                ", payment = " + payment.getAccountPayment() + "\n");
        writer.flush();

        DataBaseService dataBaseService = new DataBaseService();
        String currentBill = dataBaseService.updateTable(payment);

        String toClient = ("Payment completed successfully\nCurrent info: " +
                "Client with id = " + payment.getId() + " has Bill = " + currentBill + "\n");
        System.out.println(toClient);
        writer.write(toClient);
        writer.flush();
    }

}
