package com.example.jpt;

import com.example.jpt.HelloApplication;
import com.example.jpt.Moviemodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Viewbooking implements Initializable {
    String admin;
    @FXML
    private TableView<Moviemodel> table;

    @FXML
    private Button deleteid;

    @FXML
    private Label status;

    @FXML
    private TableColumn<Moviemodel, Integer> tbcol;

    @FXML
    private TableColumn<Moviemodel, String> tbdate;

    @FXML
    private TableColumn<Moviemodel, String> tbmovie;

    @FXML
    private TableColumn<Moviemodel, String> tbrow;

    @FXML
    private TableColumn<Moviemodel, String> tbtime;

    @FXML
    void backk(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homepage.fxml"));
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
    String val1,val2,val3,val4,val5;
    @FXML
    void delete(ActionEvent event) {
        Moviemodel selecteditem=table.getSelectionModel().getSelectedItem();
        val1=selecteditem.getMovies();
        val2= selecteditem.getDates();
        val3= selecteditem.getRows();
        val4= String.valueOf(selecteditem.getColumns());
        val5= selecteditem.getTimes();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","shruti2461");
            Statement s = con.createStatement();
            String s1 = "delete from ticket values where movie='" + val1 + "' AND dates='" + val2 + "' AND time='" + val5 + "' AND rows='" + val3 + "' AND columns='"+val4+"'';";
            s.executeUpdate(s1);
            System.out.println("success");
            deleteid.setLabel("Booking Cancelled");
        } catch (Exception e) {
            System.out.println(e);
        }




    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin = HelloApplication.getUser();

        tbmovie.setCellValueFactory(new PropertyValueFactory<Moviemodel, String>("movies"));
        tbdate.setCellValueFactory(new PropertyValueFactory<Moviemodel, String>("dates"));
        tbrow.setCellValueFactory(new PropertyValueFactory<Moviemodel, String>("rows"));
        tbcol.setCellValueFactory(new PropertyValueFactory<Moviemodel, Integer>("columns"));
        tbtime.setCellValueFactory(new PropertyValueFactory<Moviemodel, String>("times"));


        ObservableList<Moviemodel> items = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","shruti2461");

            PreparedStatement statement = con.prepareStatement("select * from ticket;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String tbmovie = rs.getString(1);
                String tbdate = rs.getString(2);
                String tbrow = rs.getString(3);
                int tbcol = rs.getInt(4);
                String tbtime = rs.getString(5);


                items.add(new Moviemodel(tbmovie, tbdate, tbrow, tbcol, tbtime));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        table.setItems(items);
    }

}

