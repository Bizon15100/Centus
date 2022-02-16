package com.example.centus;

import java.sql.Connection;
import java.sql.DriverManager;

public class dataConnection {              //połączenie z bazą danych
    public Connection databaselink;

    public Connection getConnection() {           // 8-12 wprowadzenie danych do logowania
        String databaseName = "centus";
        String databaseUser = "root";
        String databasePassword = "bizon.15100.eu";
        String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;
        try {                                                       //13-24 próba nawiązania połaczenia
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaselink;
    }

}
