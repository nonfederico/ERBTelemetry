package it.erb.telemetry.data.sensor;

import static org.junit.Assert.*;
import org.junit.Test;



public class SensorTest {

	@Test
	public void test() {
		Sensor s = new DigitalSensor();
		
		s.setValue("1");
		
	}

}
