package it.erb.telemetry;

import it.erb.telemetry.controller.ControlChart;
import it.erb.telemetry.controller.Controller;
import it.erb.telemetry.model.Model;
import it.erb.telemetry.view.View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application 
{

	View view;
	Model model;
	Controller controller;
	
	ObservableList<String> serialItems;
	
	
    @Override
    public void start(Stage stage) 
    {
    	System.out.println("01");
    	view = new View();
    	System.out.println("2");
        model = new Model();
        System.out.println("3");
        controller = new Controller(model, view, stage);
        System.out.println("4");
        ControlChart con = new ControlChart();
        
        stage.setTitle("ERB Telemetry");
        System.out.println("44");
        stage.setScene(view.getScene());
        System.out.println("46");
        stage.getIcons().add(new Image("file:Logo.png"));
        stage.setResizable(true); //per fare il resize della schermata
        System.out.println("49");
        stage.show();
        System.out.println("51");
        Platform.runLater(() -> {   //trigga il render del componente, aggiorno il componente con i dati (UI)
        	System.out.println("53");
        	//prendiamo il lineChart creato nel view e aggiungiamo le serie
        con.AddSeries(view.getLineChart());
        System.out.println("56");
        
        });
        
    
               
    }
    
    public void stop()
    {
    	model.stopListening();
    }

    public static void main(String[] args) 
    {
        launch();
    }

}