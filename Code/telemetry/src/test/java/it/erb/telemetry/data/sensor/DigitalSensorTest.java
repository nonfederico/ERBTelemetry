package it.erb.telemetry.data.sensor;

import static org.junit.Assert.*;
import org.junit.Test;



public class DigitalSensorTest {

	@Test
	public void test() {
		DigitalSensor ds1 = new DigitalSensor();
		
		assertFalse(ds1.getValue());
		
		ds1.setValue(true);
		assertTrue(ds1.getValue());
		
		ds1.setValue("0");
		assertFalse(ds1.getValue());
		
		ds1.setValue("1");
		assertTrue(ds1.getValue());
		
		assertEquals("1", ds1.toString() );
		
		ds1.setValue(false);
		assertEquals("0", ds1.toString() );
	}

}
