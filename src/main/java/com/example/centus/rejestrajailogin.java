package com.example.centus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class rejestrajailogin {
    @FXML
    private Label label;
    @FXML
    private Label rejestracjatxt;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordField2;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField;




    public void registerAccount(){
        if(textField1.getText().isBlank() == false && passwordField1.getText().isBlank() ==false && passwordField2.getText().isBlank() ==false && textField2.getText().isBlank() ==false ) // sprawdza czy texty nie są puste
        {
            if(datacentusia.loginProcess(textField2.getText())==0)   // sprawdza czy podany login istnieje
            {
                if(passwordField1.getText().equals(passwordField2.getText()))  //sprawdza czy hasła są takie same
                {
                    rejestracjatxt.setText("Gratulacje ! zostałeś zarejestrowany");
                    datacentusia.registrationDate(textField2.getText(), textField1.getText(), passwordField1.getText());  // wysyła dane do rejestracji w sql
                }else {
                    rejestracjatxt.setText("Podane Hasło nie zgadza się");
                }

            }else  if(datacentusia.loginProcess(textField2.getText())==1){      // przypadek kiedy login już istnieje
                rejestracjatxt.setText("Podany login już istnieje");
            }
        }else {
            rejestracjatxt.setText("Wypełnij wszystkie kolumny");
        }
    }   // rejestruje nowe konto
    public void logIn(ActionEvent event)throws IOException{
        if(textField.getText().isBlank() == false && passwordField.getText().isBlank() ==false ) // sprawdzanie czy nie ma pustych aren textu
        {
            if(datacentusia.loginTry(textField.getText(), passwordField.getText())==0) //sprawdzam czy dany login istnieje
            {
                label.setText("Podany login nie istnieje ");
            }else if(datacentusia.loginTry(textField.getText(), passwordField.getText())==1) //sprawdzam czy hasło jest prawdziwe
            {
                label.setText("Podane hasło jest błędne ");
            }else if(datacentusia.loginTry(textField.getText(), passwordField.getText())==2) // wszystko jest ok przechodzę dalej
            {


                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("wykres.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }else {
            label.setText("Podaj Login oraz Hasło");
        }

    }  // próba logowania



    public void register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Rejestracja.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    } // zmiana panelu z logowania na rejestracje
    public void loginPanel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }  // zmiana panelu z rejestracje na logwanie

    }

