package it.erb.telemetry.data;

import static org.junit.Assert.*;
import org.junit.Test;



public class TelemetryDataTest {

	@Test
	public void test() {
		TelemetryData td = new TelemetryData();
		
		assertEquals( 0, td.throttlePedal_Pos.getValue(), 0.001);
		assertEquals( 0, td.brakePedal_Pos.getValue(), 0.001);
		assertEquals( 0, td.steeringWheel_Pos.getValue(), 0.001);
		
		
		String packet = ""
				+ "F01:34.2;"
				+ "F02:029.09;"
				+ "F03: 233.13;"
				+ "B01:1;"
				+ "B09:0;"
				+ "F101:98.23';" //Sensore con ID non registrato
				+ "";
		
		td.parsePacket(packet);
		
		assertEquals( 34.2, td.throttlePedal_Pos.getValue(), 0.001);
		assertEquals( 29.09, td.brakePedal_Pos.getValue(), 0.001);
		assertEquals( 233.13, td.steeringWheel_Pos.getValue(), 0.001);
		assertTrue( td.saf_BSPD.getValue() );
		assertFalse( td.saf_SDBRight.getValue() );
	
		td.parsePacket(packet);
	}

}
