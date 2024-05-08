package it.erb.telemetry.view;

import java.time.LocalDate;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;

import it.erb.telemetry.App;
import it.erb.telemetry.controller.ControlChart;
import it.erb.telemetry.controller.Controller;
import it.erb.telemetry.model.Model;
import javafx.scene.control.Button;
import javafx.application.Application;
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


public class Tab1Test extends Application{

	

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
        
        stage.setTitle("ERB Telemetry");
     
        stage.setScene(view.getScene());
 
        stage.getIcons().add(new Image("file:Logo.png"));
        stage.setResizable(true); //per fare il resize della schermata
        stage.show();
   
    }
	
    /*
     * 
		@Test
		public void test() {
			Platform.runLater(new Runnable() {
			    public void run() {
					Tab1 tab01 = new Tab1();
					/*test 01, 
					 * seleziono data iniziale < data finale e mi aspetto che tab01.getStartDate(prima, dopo) prenda una data non nulla,
					 *in realtà ho sbagliato a scrivere la data iniziale, anzichè LocalDate.of(2024, 3, 11) ho scritto LocalDate.of(2024, 3, 13)
					 *quindi durante il test vedrei null */
	/*
					DatePicker prima01 = new DatePicker(LocalDate.of(2024, 3, 13)); //anno, mese, giorno
					DatePicker dopo01 = new DatePicker(LocalDate.of(2024, 3, 12));
					tab01.setInizio(prima01);
					tab01.setFine(dopo01);
					assertNotNull(tab01.getStartDate(prima, dopo));
					
					
					//test02, seleziono data iniziale > data finale, mi aspetto null come risultato del tab01.getStartDate(prima, dopo)
					DatePicker prima02 = new DatePicker(LocalDate.of(2024, 3, 13)); //anno, mese, giorno
					DatePicker dopo02 = new DatePicker(LocalDate.of(2024, 3, 12));
					tab01.setInizio(prima02);
					tab01.setFine(dopo02);
					assertNull(tab01.getStartDate(prima, dopo)); 
					
					//test03, seleziono data iniziale = data finale, mi aspetto notNull come risultato del tab01.getStartDate(prima, dopo)
					DatePicker prima03 = new DatePicker(LocalDate.of(2024, 3, 12)); //anno, mese, giorno
					DatePicker dopo03 = new DatePicker(LocalDate.of(2024, 3, 12));
					tab01.setInizio(prima03);
					tab01.setFine(dopo03);
					assertNull(tab01.getStartDate(prima, dopo)); 
					
					//test04, seleziono data iniziale < data finale, mi aspetto notNull come risultato del tab01.getStartDate(prima, dopo) 
					DatePicker prima04 = new DatePicker(LocalDate.of(2024, 3, 11)); //anno, mese, giorno
					DatePicker dopo04 = new DatePicker(LocalDate.of(2024, 3, 12));
					tab01.setInizio(prima04);
					tab01.setFine(dopo04);
					assertNotNull(tab01.getStartDate(prima, dopo));
			    }
			});	
		}

*/
}	
	    
	    

