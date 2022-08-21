package com.example.jpt;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Booking implements Initializable {



    @FXML
    private Button back;

    @FXML
    private Button booknow;

    @FXML
    private ChoiceBox<Integer> column;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox<String> movie;

    @FXML
    private ChoiceBox<String> row;

    @FXML
    private ChoiceBox<String> time;

    @FXML
    void booknow(ActionEvent event) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","shruti2461");
            Statement s = con.createStatement();
            String s1 = "insert into ticket values ('" + movie + "','" + date + "','" + time + "','" + row + "','"+column+"','"+HelloApplication.getUser()+"');";
            s.executeUpdate(s1);
            System.out.println("success");
            booknow.setText("Booking Confirmed");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void goback(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homepage.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList mname = FXCollections.observableArrayList();
        mname.addAll("Shang Chi","Batman","Venom","Sinister");
        movie.setItems(mname);

        ObservableList timing = FXCollections.observableArrayList();
        timing.addAll("9am","12pm","3pm","6pm","9pm");
        time.setItems(timing);

        ObservableList rownum = FXCollections.observableArrayList();
        rownum.addAll("a","b","c","d","e","f");
        row.setItems(rownum);

        ObservableList colnum = FXCollections.observableArrayList();
        colnum.addAll(1,2,3,4,5,6,7,8);
        column.setItems(rownum);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","shruti2461");
            PreparedStatement statement = con.prepareStatement("select * from movie where id='"+movie+"';");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                time.setValue(rs.getString(1));
                row.setValue(rs.getString(2));
                column.setValue(rs.getInt(3));

            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}


