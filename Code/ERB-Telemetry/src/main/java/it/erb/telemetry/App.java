package it.erb.telemetry;

import java.time.LocalDateTime;
import it.erb.telemetry.database.DatabaseManager;
import it.erb.telemetry.model.TelemetryData;
import it.erb.telemetry.view.View;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println( "Hello World!" );
    	
    	View view = new View();
        
        DatabaseManager db = DatabaseManager.getInstance();
        
        TelemetryData td = new TelemetryData();
        
        td.date 			 = java.time.LocalDateTime.now();
        td.ThrottlePedal_Pos = (float) (Math.random()*100f);
        // ...
        td.MotorRL_CmdTorque = (float) (Math.random()*900f);
        
        db.addRecord(td);
        
        TelemetryData resTD;
        resTD = db.retrieveRecord( LocalDateTime.now().minusMinutes(3), LocalDateTime.now() );
        resTD.print();
        
        view.run();
    }
}
