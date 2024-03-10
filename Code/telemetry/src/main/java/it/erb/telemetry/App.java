package it.erb.telemetry;

import it.erb.telemetry.controller.ControlChart;	
import it.erb.telemetry.controller.Controller;
import it.erb.telemetry.model.Model;
import it.erb.telemetry.view.View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
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
    	//System.out.println("01");
    	view = new View();
    	//System.out.println("2"); //qui non funziona con il metodo 1 del controller
        model = new Model();
        controller = new Controller(model, view, stage);

        ControlChart con = new ControlChart();
        
        stage.setTitle("ERB Telemetry");
     
        stage.setScene(view.getScene());
 
        stage.getIcons().add(new Image("file:Logo.png"));
        stage.setResizable(true); //per fare il resize della schermata
        stage.show();
    
        
        
        Platform.runLater(() -> {   //trigga il render del componente, aggiorno il componente con i dati (UI)
        	
        	//prendiamo il lineChart creato nel view e aggiungiamo le serie
        con.AddSeries(view.getLineChart());
        
        
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