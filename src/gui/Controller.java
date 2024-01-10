package gui;

import domain.Weather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import service.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;
import java.util.Vector;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private Button Button;
    @FXML
    private ListView<String> listView;
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Text text;

    @FXML
    private TextField textField;


    @FXML
    protected void populateList() {
        try {
            ArrayList<Weather> weathers = service.getAll();
            var sortedWeathers = weathers.stream().sorted(Comparator.comparing(report-> report.getStartHour())).collect(Collectors.toList());
            for (Weather weather : sortedWeathers) {
                listView.getItems().add(weather.toString());
            }
        }catch (Exception exception) { exception.printStackTrace();}
    }

    @FXML
    protected void populateComboBox(){
        ArrayList<String> descriptions = new ArrayList<>();
        try {
            ArrayList<Weather> weathers = service.getAll();
            for(Weather weather:weathers)
            {
                String[] desc = weather.getDescription().split("\s+");
                if(!descriptions.contains(desc[0])) descriptions.add(desc[0]);
            }
            comboBox.getItems().addAll(descriptions);
        }
        catch (Exception exception) {exception.printStackTrace();}
    }

    @FXML
    void sortComboBox(ActionEvent event) {
        listView.getItems().clear();
        String filter_term = comboBox.getValue();
        try {
            ArrayList<Weather> weathers = service.getAll();
            var filteredWeathers = weathers.stream().filter(w -> w.getDescription().startsWith(filter_term)).collect(Collectors.toList());
            for (Weather weather : filteredWeathers) {
                listView.getItems().add(weather.toString());
            }
        }catch (Exception exception) {exception.printStackTrace();}
    }

    @FXML
    void totalHours(ActionEvent event) {
        String input = textField.getText();
        int total = 0;
        try{
            ArrayList<Weather> weathers = service.getAll();
            for(Weather weather:weathers)
            {
                if(weather.getDescription().contains(input))
                {
                    total += weather.getEndHour() - weather.getStartHour();
                }
            }
            text.setText(String.valueOf(total));
        }catch(Exception exception) {exception.printStackTrace();}
    }

    @FXML
    public void initialize(){
        try {
//            service.createSchema();
//            service.AddinSchema();
            populateList();
            populateComboBox();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

