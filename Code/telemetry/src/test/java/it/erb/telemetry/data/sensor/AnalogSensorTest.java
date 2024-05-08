package it.erb.telemetry.data.sensor;

import static org.junit.Assert.*;
import org.junit.Test;


public class AnalogSensorTest {

	@Test
	public void test() {
		
		AnalogSensor as1 = new AnalogSensor();
		
		assertEquals(as1.getUnit(), "");
		
		AnalogSensor as2 = new AnalogSensor("volts");
		
		assertEquals(as2.getUnit(), "volts" );
		
		AnalogSensor as3 = new AnalogSensor( 2.3f , "volts");
		
		assertEquals(as3.getUnit(), "volts" );
		assertEquals(as3.getValue(), 2.3, 0.001 );
		
		as1.setUnit("Ampere");
		assertEquals(as1.getUnit(), "Ampere");
		
		as1.setValue(10.345f);
		assertEquals(10.345f, as1.getValue(), 0.001 );
		
		as1.setValue("251.236");
		assertEquals(251.236f, as1.getValue(), 0.001 );
		
		assertEquals("251Ampere", as1.toString() );
		assertEquals("251Ampere", as1.toString() );
		assertEquals("251,2Ampere", as1.toString(1) );
		assertEquals("251,236Ampere", as1.toString(3,true) );
		assertEquals("251,236", as1.toString(3,false) );
	}

}
