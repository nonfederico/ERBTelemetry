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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

	String s;
	View view;
	Model model;
	Controller controller;
	
	private Thread thread;
	private Thread mainThread;
	
	
	ObservableList<String> serialItems;
	
	
    @Override
    public void start(Stage stage) 
    {
    	mainThread = Thread.currentThread();
    	
    	view = new View();
        model = new Model();
        controller = new Controller(model, view);
        
        stage.setTitle("ERB Telemetry");
        stage.setScene(view.getScene());
        stage.getIcons().add(new Image("file:Logo.png"));
        
        //per fare il resize della schermata
        final boolean resizable = stage.isResizable();
		stage.setResizable(resizable);
        stage.show();
    
        
     
        
        // Secondary thread
        thread = new Thread(new Runnable() {
        	
        	public void run() {

                while (mainThread.isAlive()) {
                	   
                    Platform.runLater(new Runnable(){
                    	@Override
                    	public void run()
                    	{
                    		//data.setValue(s);
                    		
                    		
                    		
                    	}
                          	
                    });
                    
                    try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });
        
        thread.start();
        
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