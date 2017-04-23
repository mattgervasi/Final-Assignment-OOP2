/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Kodyscharf
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    Menu recentMenu;
    File lastDirectory = null;
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private TextField items;
    
    @FXML
    private Button back;
    
    @FXML
    AnchorPane rootPane;
    
    @FXML
    private ListView listView, listView2;
    ObservableList<Clothes> listData = FXCollections.observableArrayList();
  
    @FXML
    private void fileLoadEvent(ActionEvent e) {
        FileChooser chooser = new FileChooser();
        if (lastDirectory != null) {
            chooser.setInitialDirectory(lastDirectory);
        }
        File selectedFile
                = chooser.showOpenDialog(
                        rootPane.getScene().getWindow()
                );

        if (selectedFile != null) {
            lastDirectory = selectedFile.getParentFile();
            loadDataFromFile(selectedFile);
            recentMenu.setDisable(false);
            MenuItem mi = new MenuItem(selectedFile.getAbsolutePath());
            mi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadDataFromFile(new File(mi.getText()));
                }
            });
            recentMenu.getItems().add(mi);
            if (recentMenu.getItems().size() > 10) {
                recentMenu.getItems().remove(0);
            }
        }
    }
    
    

    private void loadDataFromFile(File selectedFile) {
        if (selectedFile.getName().equals("data.txt")) {
            ObservableList<Clothes> tdata = FXCollections.observableArrayList();
            try {
                BufferedReader in = new BufferedReader(new FileReader(selectedFile));
                for (String line = in.readLine();
                        line != null;
                        line = in.readLine()) {               
                    String name= line.substring(0, line.indexOf(','));
                    String type= line.substring(line.indexOf(',') + 1);
                    String price= line.substring(type.indexOf(',') + 1);
                    String avaialbilty= line.substring(price.indexOf(',') + 1);
                    String serial= line.substring(avaialbilty.indexOf(',') + 1);
                    String size= line.substring(serial.indexOf(',') + 1);
                    //Skips name when its printed out ?? Only reason i can think of is the substring but its starting at index 0 and up to
                    //the first ',' ..
                    tdata.add(new Clothes(name, type, parseDouble(price), parseBoolean(avaialbilty), serial, size));       
                }
                
                in.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }

    @FXML
    public void removeButtonEvent(ActionEvent e) {
        int indexToRemove = listView.getSelectionModel().getSelectedIndex();
        if (indexToRemove >= 0) {
            listData.remove(indexToRemove);
        }
    }

    public void topButtonEvent(ActionEvent e) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            Clothes p = listData.get(selectedIndex);
            listData.remove(selectedIndex);
            listData.add(0, p);
            listView.getSelectionModel().clearAndSelect(0);
        }

    }

    public void bottomButtonEvent(ActionEvent e) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < listData.size() - 1) {
            Clothes p = listData.get(selectedIndex);
            listData.remove(selectedIndex);
            listData.add(p);
            listView.getSelectionModel().clearAndSelect(listData.size() - 1);
        }
    }

    public void upButtonEvent(ActionEvent e) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            Clothes p = listData.get(selectedIndex);
            listData.remove(selectedIndex);
            listData.add(selectedIndex - 1, p);
            listView.getSelectionModel().clearAndSelect(selectedIndex - 1);
        }
    }

    public void downButtonEvent(ActionEvent e) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < listData.size() - 1) {
            Clothes p = listData.get(selectedIndex);
            listData.remove(selectedIndex);
            listData.add(selectedIndex + 1, p);
            listView.getSelectionModel().clearAndSelect(selectedIndex + 1);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listData.add(new Clothes("String", "String", 1.0, true, "String", "String"));
        listData.add(new Clothes("String", "String", 2.0, false, "String", "String"));
        listData.add(new Clothes("String", "String", 3.0, true, "String", "String"));
        listData.add(new Clothes("String", "String", 4.0, false, "String", "String"));
        listView.setItems(listData);
    }    
    
    class Clothes {  
        String name;
        String type;
        Double price;
        Boolean available;
        String serial;
        String size;

        Clothes(String name, String type, 
            Double price, Boolean available,
            String serial,String size){
            setName(name);
            setType(type);
            setPrice(price);
            setAvailable(available);
            setSerial(serial);
            setSize(size);
        }
    
        public String getName(){
            return name;       
        }
        public String getType(){
            return type;       
        }
        public Double getPrice(){
            return price;
        }
        public Boolean getAvailable(){
            return available;       
        }
        public String getSerial(){
            return serial;       
        }
        public String getSize(){
            return size;       
        }
        public void setName(String name){
            this.name = name;
        }
        public void setType(String type){
            this.type = type;
        }
        public void setPrice(Double price){
            this.price = price;
        }
        public void setAvailable(Boolean available){
            this.available = available;
        }
        public void setSerial(String serial){
            this.serial = serial;
        }
        public void setSize(String size){
            this.size = size;
        }

        @Override
        public String toString(){
            String text = "";
            text += getName() + "," + getType() + "," +
                    Double.toString(getPrice()) + "," +
                    Boolean.toString(getAvailable()) + "," +
                    getSerial() + "," + getSize();
            return text; 
        }
    }
}
