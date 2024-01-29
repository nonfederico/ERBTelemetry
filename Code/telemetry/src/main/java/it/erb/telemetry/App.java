package it.erb.telemetry;

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
    	view = new View();
        model = new Model();
        controller = new Controller(model, view);
        
        stage.setTitle("ERB Telemetry");
        stage.setScene(view.getScene());
        stage.getIcons().add(new Image("file:Logo.png"));
        stage.setResizable(true); //per fare il resize della schermata
        stage.show();
    
      //mostro le cose e poi le reinderizzo (le aggiorno)
    	//la view detiene l'istanza del grafico e qui compilo i dati, devo triggerare manualmente i dati
        
        
        
        //crea metodo nel view per aggiungere i dati nel grafico con:
        Platform.runLater(() -> {   //trigga il render del componente, aggiorno il componente con i dati (UI)
        
        XYChart.Series<Number, Number> series = new XYChart.Series<>(); //<-- fuori dal costruttore view
            
        series.getData().add(new XYChart.Data<>(1, 5));
        series.getData().add(new XYChart.Data<>(2, 10));
        series.getData().add(new XYChart.Data<>(3, 15));

        view.getLineChart().getData().add(series);
          
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