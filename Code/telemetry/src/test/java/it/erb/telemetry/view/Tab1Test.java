package it.erb.telemetry.view;

import static org.junit.jupiter.api.Assertions.*;	

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import javafx.scene.control.DatePicker;



class Tab1Test {

	//caso in cui seleziono data inizio successiva a data finale -> otteniamo null
	@Test
	public void test(){
		
		//quando avvio test non inizializza da riga 39 della classe Tab1, ho messo public solo per prova
		Tab1 tab01 = new Tab1();
		
		DatePicker prima = new DatePicker(LocalDate.of(2024, 13, 03)); //anno, mese, giorno
		DatePicker dopo = new DatePicker(LocalDate.of(2024, 12, 03));
		/*
		tab01.setInizio(dp_tableStartDate);
		tab01.setFine(dp_tableEndDate);
		*/
		
		assertEquals(null, tab01.getStartDate(prima, dopo));
	}

}
