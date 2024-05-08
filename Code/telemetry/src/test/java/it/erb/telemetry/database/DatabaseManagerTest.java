package it.erb.telemetry.database;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.erb.telemetry.data.TelemetryData;


public class DatabaseManagerTest {

	
	@Before
	public void setupDatabase() {
		try {
			DatabaseManager.getInstance().getConn().setAutoCommit(false);
		} catch (SQLException ex) {
			System.err.println("Cannot disable autocommit");
			ex.printStackTrace();
		}
	}
	
	@AfterClass
	public static void restoreDatabase() {
		try {
			DatabaseManager.getInstance().getConn().rollback();
			DatabaseManager.getInstance().getConn().setAutoCommit(true);;
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.err.println("Cannot restore database");
			ex.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		TelemetryData td = new TelemetryData();
		
		String packet = ""
				+ "F01:34.2;"
				+ "F02:029.09;"
				+ "F03: 233.13;"
				+ "B01:1;"
				+ "B09:0;"
				+ "";
		
		td.parsePacket(packet);
		
		assertTrue( DatabaseManager.getInstance().addRecord(td));
		
		
	}

}
