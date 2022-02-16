package com.example.centus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class datacentusia {
    public static void registrationDate(String login, String imie, String haslo) {
        dataConnection connectionNow = new dataConnection();
        Connection connectionDB = connectionNow.getConnection();

        String form = "INSERT INTO centus(login,haslo,nazwa) VALUES ('";       // wybieramy columny do ktorych mamy wprowadzic dane
        String value = login + "','" + haslo + "','" + imie + "')";            //  wprowadzamy nasze dane
        String register = form + value;
        try {
            Statement st = connectionDB.createStatement();
            st.executeUpdate(register);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }  // zapisywanie danych  z rejestracji
    public static int loginTry(String login, String haslo) {
        int k = 0;

        dataConnection connectionNow = new dataConnection();
        Connection connectionDB = connectionNow.getConnection();
        String loginQuery = "SELECT count(1) FROM centus WHERE login ='" + login + "'";  // sprawdzenie czy login istnieje,
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(loginQuery);
            while (queryResult.next())
            {
                if (queryResult.getInt(1) == 1) {
                    String vlogin = "SELECT count(1) FROM centus WHERE login ='" + login + "' AND haslo ='" + haslo + "'"; // sprawdzenie loginu oraz hasla
                    try {
                        Statement statement1 = connectionDB.createStatement();
                        ResultSet executeQuery = statement1.executeQuery(vlogin);
                        while (executeQuery.next()) {
                            if (executeQuery.getInt(1) == 1) {
                                k = 2;
                            }
                        }
                        if (k != 2) {
                            k = 1;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        if (k==2){
            int id = 0;
            String loginnn = "SELECT id FROM centus WHERE login ='" + login + "'";  // pozyskanie id
            try {
                Statement sttt = connectionDB.createStatement();
                ResultSet qrrr = sttt.executeQuery(loginnn);
                while (qrrr.next()) {

                    id = qrrr.getInt(1);
                    wykres.id(id);   // wysłanie id użytkownika
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();

            }
        }
        return (k);                                    // k służy nam za zmienną do if, abyśmy wiedzieli która z sytuacji miała miejsce
    }  // procedura logowania
    public static int loginProcess(String login) {
        int k = 0;
        dataConnection connectionNow = new dataConnection();
        Connection connectionDB = connectionNow.getConnection();
        String loginnn = "SELECT * FROM centus WHERE login ='" + login + "'";       //w kolumnie Login szuka login
        try {
            Statement st = connectionDB.createStatement();
            ResultSet qr = st.executeQuery(loginnn);
            while (qr.next()) {

                if (qr.getInt(1) == 1) {              // sprawdza jeśli podany login zgadza się z podanym
                    k = 1;                                       // przypisuje wartość zmiennej k
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }
        return (k);                                          // zwraca wartość
    }  // sprawdza czy podany login juz istnieje, dla formularzu rejestracji
    public static void addData(int zarabia, int wydaje, int idd, String data) {
        dataConnection connectionNow = new dataConnection();
        Connection connectionDB = connectionNow.getConnection();


        String form = "INSERT INTO dane(budzet,wydatek,id,data) VALUES ('";              // łączenie z bazą danych
        String value = zarabia + "','" + wydaje + "','" + idd + "','" + data + "')";     // i wpisanie danych
        String register = form + value;
        try {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(register);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    } //wprowadzenie nowego wydatku do bazy danych
    public static int budgetData(int idd) {
        int budzet = 0;
        dataConnection connectionNow = new dataConnection();
        Connection connectionDB = connectionNow.getConnection();
        String loginnn = "SELECT budzet FROM dane WHERE id ='" + idd + "'";        // szukamy budżetu
        try {
            Statement st = connectionDB.createStatement();
            ResultSet qr = st.executeQuery(loginnn);
            while (qr.next()) {

                if(qr.isLast()) {                           // komenda isLast wyszukuje nam ostatni zapisany budżet
                    budzet = qr.getInt(1);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }
        return (budzet);          // zwraca wartosc budzetu
    }   // otrzymanie wartości ostatniego budżetu, jesli osoba nie podała go w formularzu

}
