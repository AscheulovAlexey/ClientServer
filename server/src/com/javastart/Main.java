package com.javastart;

import java.sql.SQLException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Server server = new Server(9000);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
