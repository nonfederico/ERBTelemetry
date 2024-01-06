package it.erb.telemetry.model;

import com.fazecast.jSerialComm.SerialPort;



public class Model 
{
	TelemetryData td;
	
	SerialPort[] availableSerialPorts;		
	
	
	public void loadSerialPorts()
	{
		availableSerialPorts = SerialPort.getCommPorts();
		
		
		
	}
	
	
	void onDataReceived()
	{
		//Validate data packet
		
		//copy data packet into td
		
		//add record into db
		
		//update GUI
		
		
		
		
	}
	
	
	
}
