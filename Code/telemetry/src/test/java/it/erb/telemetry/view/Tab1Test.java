package it.erb.telemetry.view;

import java.time.LocalDate;	

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;

import it.erb.telemetry.App;
import it.erb.telemetry.controller.ControlChart;
import it.erb.telemetry.controller.Controller;
import it.erb.telemetry.model.Model;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;

import static org.junit.Assert.*;
import org.junit.Test;

//public class Tab1Test{
public class Tab1Test extends ApplicationTest{	
	//test con GUI
	//class Tab1Test extends GuiTest{
	//caso in cui seleziono data inizio successiva a data finale -> otteniamo null
	//@Test
	/*public Parent getRootNode()
	   {
		return nodeUnderTest;
	   }
	*/
	
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
    
    /*
        Platform.runLater(() -> {   //trigga il render del componente, aggiorno il componente con i dati (UI)
        	
        	//prendiamo il lineChart creato nel view e aggiungiamo le serie
        con.AddSeries(view.getLineChart());   
        });
        */
    }
	
		@Test
		public void test() {
			
			//quando avvio test non inizializza da riga 39 della classe Tab1, ho messo public solo per prova
			Tab1 tab01 = new Tab1();
			
			DatePicker prima = new DatePicker(LocalDate.of(2024, 13, 03)); //anno, mese, giorno
			DatePicker dopo = new DatePicker(LocalDate.of(2024, 12, 03));
			
			tab01.setInizio(prima);
			tab01.setFine(dopo);
			//se uso harmcrest: verifyThat(tab01.getStartDate(prima,dopo),  );
			
			assertNull(tab01.getStartDate(prima, dopo));
		}
	
	    
	}
	    
	    

