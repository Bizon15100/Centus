package com.example.centus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class wykres {

    @FXML
    private TextField txtbudzet;
    @FXML
    private TextField txtwydatek;
    @FXML
    private Label budzettext;
    @FXML
    private Label txtwydano;
    @FXML
    private Label txtzostalo;
    @FXML
    private LineChart<?,?> LineChart;

    int g;                                           //  przechowujemy liczbę informacji ile column jest dostępnych do wczytania
    int[] expenses = new int[1000];                  //  przechowujemy wydatki
    String[] data = new String[1000];                //  przechowujemy datę (godzinę dzień i rok )
    public static int budget = 0;                    //  przechowujemy wielkość budżetu
    private static int id;                           //  przechowujemy nasze id konta
    static public void id(int idd) {
        id=idd;
    }             // pozyskujemy id, odbywa się to automatycznie po zalogowaniu ( data.datalogin linia nr. 50 )



    public void diagram() {
        String dataa;        // nowa zmienna do przechowywania danych daty
        int k=0;             // nowa zmienna do przechowywania danych wydatków
        expensesData();        // zdobycie danych wydatków
        datadady();           // zdobycie danych daty
        LineChart.getData().clear();     //czyszczenie wykresu
        budget = datacentusia.budgetData(id);        //wczytanie wielkości budżetu

        XYChart.Series series = new XYChart.Series();    // tworzenie wykresu

        for (int i = 0; i < g; i++) {          // powtarzamy pętlę do puki nie wykona się ,,liczba,, ilość razy, gdzie liczba to ilość infrmacji jaką posiadamy
            k += expenses[i];                 // wprowadzenie danych wyatków
            dataa = data[i];             // wprowadzenie danych daty
            series.getData().add(new XYChart.Data(dataa, k));       // rysowanie punktu na wykresie z danymi

        }
        LineChart.getData().add(series);     // dodanie powyższych danych do wykresu
        series.setName("Wydatki");     // nazwa tytułu
        txtwydano.setText("" + k);           // text z wydatkami
        txtzostalo.setText("" + (budget - k));   //text ile zostało
        budzettext.setText("" + budget);    // text z wielkością budżetu
    }


    public void expensesData() {
        g = 0;                        // służy do zbierania informacji na temat ilości zebranych danych
        int k = 0;
        dataConnection connectionNow = new dataConnection();
        Connection connectionDB = connectionNow.getConnection();
        String loginnn = "SELECT wydatek FROM dane WHERE id='" + id + "'";  // szukamy columny wydatek w bazie danych dane
        try {
            Statement st = connectionDB.createStatement();
            ResultSet qr = st.executeQuery(loginnn);
            while (qr.next()) {
                k = qr.getInt(1);
                expenses[g] = k;                // zapisujemy dane w tabelce
                g++;

            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }

    }                  //zbieramy dane o wydatkach do tablicy abyśmy mogli ją zaimplementować w wykresie
    public void datadady() {
        g =0;     // służy do liczenie ile danych wczytaliśmy

        dataConnection connectionNow = new dataConnection();
        Connection connectionDB = connectionNow.getConnection();
        String loginnn = "SELECT data FROM dane WHERE id='"+id+"'";   //szukamy columny daty naszej bazie danych
        try {
            Statement st = connectionDB.createStatement();
            ResultSet qr = st.executeQuery(loginnn);
            while (qr.next()) {
                data[g]=qr.getString(1);  //zapisujemy naszą datę w zmiennej tabeli
                g++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }

    }                     //zbieramy dane o dacie do tablicy abyśmy mogli ją zaimplementować w wykresie
    public void addExpenses(){
        LocalDateTime date = LocalDateTime.now();        // używająć biblioteki tworzymy zmieną która to pobierze naszą aktualną datę od 122-127
        LocalDateTime localDateTime = date.atZone(ZoneId.systemDefault()).toLocalDateTime();
        int year  = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day   = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        String data = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        if(txtbudzet.getText().isEmpty())   // sprawdzamy czy osoba wypełniła formularz, jeśli nie ** jeśli tak ++
        {
            budget = datacentusia.budgetData(id);   //  ** wczytujemy ostatni budżet jaki był zapisany
            datacentusia.addData(budget,Integer.parseInt(txtwydatek.getText()),id,data);
        }else{                                    // ++ dajemy dudżet podany w formualrzu

            datacentusia.addData(Integer.parseInt(txtbudzet.getText()),Integer.parseInt(txtwydatek.getText()),id,data);  //wysyłamy dane do zapisu
        }
        diagram();

    }                  // dodawanie nowego wydatku





    public void logOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }  // służy do zmiany sceny na logowanie
}
