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
	private StringProperty data;
	private Thread thread;
	private Thread mainThread;
	
	
	ObservableList<String> serialItems;
	
	
    @Override
    public void start(Stage stage) {

    	mainThread = Thread.currentThread();
    	
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(model, view);
        
        data = new SimpleStringProperty();
        
        
        stage.setTitle("ERB Telemetry");
        stage.setScene(view.getScene());
        stage.getIcons().add(new Image("file:Logo.png"));
        stage.show();
        
        thread = new Thread(new Runnable() {
        	
        	public void run() {

                while (mainThread.isAlive()) {
                	Double f = Math.random()*130f;
                    s = f.toString();
                    
                    
                    
                    
                    
                    Platform.runLater(new Runnable(){
                    	@Override
                    	public void run()
                    	{
                    		//data.setValue(s);
                    		view.lbl_accCurrentData.setText(s);
                    		
                    	}
                          	
                    });
                    //System.out.print(" "); 
                    //System.out.println(s);
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
    	
    	
    	
    }

    public static void main(String[] args) {
        launch();
    }

}