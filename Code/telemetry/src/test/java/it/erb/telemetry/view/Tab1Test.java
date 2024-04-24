package it.erb.telemetry.view;

import java.time.LocalDate;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;

import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.Test;

import javafx.scene.control.DatePicker;



class Tab1Test extends ApplicationTest{

	//caso in cui seleziono data inizio successiva a data finale -> otteniamo null
	//@Test
	public void test(){
		
		//quando avvio test non inizializza da riga 39 della classe Tab1, ho messo public solo per prova
		Tab1 tab01 = new Tab1();
		
		DatePicker prima = new DatePicker(LocalDate.of(2024, 13, 03)); //anno, mese, giorno
		DatePicker dopo = new DatePicker(LocalDate.of(2024, 12, 03));
		/*
		tab01.setInizio(dp_tableStartDate);
		tab01.setFine(dp_tableEndDate);
		*/
		
		//assertEquals(null, tab01.getStartDate(prima, dopo));
	}

}
