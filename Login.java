package com.example.jpt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Login implements Initializable {
    String uname;
    @FXML
    private Label l1;

    @FXML
    private Button loginbutton;

    @FXML
    private Button register;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;

    @FXML
    void handleButtonActionRegister(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();

        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void handleButtonActionlogin(ActionEvent event) {

        this.uname = txtusername.getText();
        String passwo =txtpassword.getText();
        HelloApplication.setUser(uname);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

            PreparedStatement statement = con.prepareStatement("select * from registration where txtusername='"+uname+"';");
            PreparedStatement statement1 = con.prepareStatement("select * from registration where txtpassword='"+passwo+"';");
            ResultSet rs = statement.executeQuery();
            ResultSet rs1 = statement1.executeQuery();

            if (rs.next()) {
                if (rs1.next()) {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("functionalities.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    l1.setText("Password do NOT Match");
                }
            } else {
                l1.setText("Username do NOT Exist");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
