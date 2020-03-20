package com.javastart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javastart.Entity.Account;
import com.javastart.Entity.Payment;
import com.javastart.Service.AccountService;
import com.javastart.Service.PaymentService;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private BufferedReader reader;
    private BufferedWriter writer;
    private ObjectMapper objectMapper;
    private Scanner scanner = new Scanner(System.in);
    private AccountService accountService = new AccountService();
    private PaymentService paymentService = new PaymentService();


    public Client(int port) {
        System.out.println("---INFO---\nStart program\n");
        try {
            Socket socket = new Socket("localhost", port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (socket.isConnected()){
                System.out.println("---INFO---\nСlient is connected to server \n");
                System.out.println("Insert 1 - to create Account, 2 - to make Payment, 3 - to exit program");

                int insertNumber = scanner.nextInt();
                if (insertNumber == 1) {
                    Account account = accountService.sendByKeyboard();
                    sendAccount(account);
                } if (insertNumber == 2) {
                    Payment payment = paymentService.makePayment();
                    sendPayment(payment);
                } if (insertNumber == 3){
                    writer.write("exit");
                    writer.close();
                    reader.close();
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void sendAccount(Account account) throws IOException {
        objectMapper = new ObjectMapper();
        String objectToServer = objectMapper.writeValueAsString(account);
        writer.write(objectToServer + "\n");
        writer.flush();
        System.out.println("---INFO---\nAccount sent to server\n");
        String readAccount = reader.readLine();
        System.out.println("---INFO---\nServer sent message.\n" + readAccount + "\n");
    }

    public void sendPayment(Payment payment) throws IOException {
        objectMapper = new ObjectMapper();
        String paymentToServer = objectMapper.writeValueAsString(payment);
        writer.write(paymentToServer + "\n");
        writer.flush();
        System.out.println("---INFO---\nPayment sent to server\n");
        System.out.println("---INFO---\nServer sent message:\n" + reader.readLine() + "\n"  + reader.readLine() + "\n");
        System.out.println(reader.readLine() + "\n" + reader.readLine() + "\n");

    }
}
