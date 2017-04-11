/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author Kodyscharf
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private TextField items;
    
    @FXML
    private ListView listView;
    ObservableList<Clothes> listData = FXCollections.observableArrayList();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listData.add(new Clothes("Color Type", "Serial Number"));
        listData.add(new Clothes("Red Sport Shirt", "Clark"));
        listData.add(new Clothes("Scharf", "Kody"));
        listData.add(new Clothes("Gerv", "Mat"));

        listView.setItems(listData);
    }    
    
    class Clothes {

        String firstName;
        String lastName;

        Clothes(String fname, String lname) {
            firstName = fname;
            lastName = lname;
        }

        @Override
        public String toString() {
            return lastName + ", " + firstName;
        }
    }
    
}
