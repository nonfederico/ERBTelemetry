package it.erb.telemetry;

import it.erb.telemetry.controller.Controller;
import it.erb.telemetry.controller.ModChart;
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
    	ModChart A = new ModChart();
    	view = new View();
        model = new Model();
        controller = new Controller(model, view, stage);
        
        stage.setTitle("ERB Telemetry");
        stage.setScene(view.getScene());
        stage.getIcons().add(new Image("file:Logo.png"));
        stage.setResizable(true); //per fare il resize della schermata
        stage.show();
        
        
        //triggero il render del componente per aggiornare la UI
        Platform.runLater(() -> {   
 
        //uso funzione ModChart per aggiungere dati al grafico creato nella view
        A.AddSeries(view.getLineChart());
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