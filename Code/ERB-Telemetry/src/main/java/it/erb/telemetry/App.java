package it.erb.telemetry;

import java.time.LocalDateTime;

import it.erb.telemetry.controller.Controller;
import it.erb.telemetry.database.DatabaseManager;
import it.erb.telemetry.model.Model;
import it.erb.telemetry.model.TelemetryData;
import it.erb.telemetry.view.View;


public class App 
{
    public static void main( String[] args )
    {
    	System.out.println( "Hello World!" );
    	
    	View view = new View();
    	view.run();
    	System.out.println("Game over2");
    	Model model = new Model();
    	System.out.println("Game over1");
    	Controller controller = new Controller(model, view);
        
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
        
        controller.updateView();
        
        System.out.println("Game over");
        
    }
}
