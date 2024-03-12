package it.erb.telemetry.view;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import javafx.scene.control.DatePicker;

class Tab1Test {

	//caso in cui seleziono data inizio successiva a data finale -> otteniamo null
	@Test
	void test() {
		Tab1 tab01 = new Tab1();
		
		DatePicker dp_tableStartDate = new DatePicker(LocalDate.now().plusDays(1));
		DatePicker dp_tableEndDate = new DatePicker(LocalDate.now());
		
		tab01.setInizio(dp_tableStartDate);
		tab01.setFine(dp_tableEndDate);
		
		assertEquals(null, tab01.getStartDate());
		
		fail("Not yet implemented");
	}

}
