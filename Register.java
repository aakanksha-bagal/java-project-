package com.example.jpt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Register {

    @FXML
    private Button sback;

    @FXML
    private Label confirm;

    @FXML
    private PasswordField sconfirmpass;

    @FXML
    private Button signup;

    @FXML
    private TextField slogin;

    @FXML
    private PasswordField spassword;

    @FXML
    private TextField susername;

    @FXML
    void handleButtonActionsback(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void handleButtonActionsignup(ActionEvent event) {
        String sloginid = slogin.getText();
        String suser = susername.getText();
        String spass = spassword.getText();
        String scpass = sconfirmpass.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","shruti2461");

            PreparedStatement statement = con.prepareStatement("select * from registration where username='"+sloginid+"';");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                confirm.setText("Username already exists!");
            } else {
                System.out.println(spass);
                System.out.println(spass.compareTo(scpass));

                if (spass.compareTo(scpass)==0) {
                    Statement s = con.createStatement();
                    String s1 = "insert into registration values ('" + slogin.getText() + "','" + susername.getText() + "','" + spassword.getText() + "','" + sconfirmpass.getText() + "');";
                    s.executeUpdate(s1);
                    System.out.println("success");
                    confirm.setText("Registration Successfull");
                } else {
                    confirm.setText("Passwords do NOT match!");
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
