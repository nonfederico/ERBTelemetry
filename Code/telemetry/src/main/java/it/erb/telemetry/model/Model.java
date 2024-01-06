package it.erb.telemetry.model;

import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Model 
{
	TelemetryData td;
	
	private SerialPort comPort;
	private SerialPort[] availableSerialPorts;		
	private ObservableList<String> serialPortNameList = FXCollections.observableArrayList();
	private int SelectComPortIndex = -1;
	
	
	public void loadSerialPorts()
	{
		availableSerialPorts = SerialPort.getCommPorts();
		
		System.out.println("Loading serial ports");
		serialPortNameList.clear();
		for(SerialPort sp : availableSerialPorts )
		{
			serialPortNameList.add(sp.getSystemPortName());
		}
	}
	
	
	public ObservableList<String> getSerialPortName(){ return serialPortNameList; }
	
	public void startListening()
	{
		// Index selection test
		if( SelectComPortIndex == -1 )
		{
			System.out.println("Invalid COM port selection (" + String.valueOf(SelectComPortIndex + ")"));
			return;
		}
		
		comPort = availableSerialPorts[SelectComPortIndex];
		
		// default connection settings for Arduino
		comPort.setComPortParameters(9600, 8, 1, 0); 
				
		// Port opening
		if( !comPort.openPort() )
		{
			System.out.println("Port opening " + comPort.getPortDescription() + " failed");
		    return;
		}
		System.out.println("Port (" + comPort.getPortDescription() + ") is open" );
		
		
		
	}
	
	public void stopListening()
	{
		comPort.removeDataListener();
		comPort.closePort();
	}
	
	void onDataReceived()
	{
		//Validate data packet
		
		//copy data packet into td
		
		//add record into db
		
		//update GUI
		
		
		
		
	}
	
	
	
	
	
}
