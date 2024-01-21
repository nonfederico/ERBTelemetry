package it.erb.telemetry.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import it.erb.telemetry.database.DatabaseManager;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Model 
{
	private DatabaseManager db;
	private SerialPort comPort;
	private SerialPort[] availableSerialPorts;		
	private ObservableList<String> serialPortNameList = FXCollections.observableArrayList();
	private int SelectComPortIndex = -1;
	private boolean isComPortOpen = false;
	
	public TelemetryData latestData = new TelemetryData();
	
	
	public Model()
	{
		db = DatabaseManager.getInstance();
		
	}
	
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
	
	public void setSelectedComPortIndex(int index)
	{
		SelectComPortIndex = index;		
	}
	
	public void startListening()
	{
		// Index selection test
		if( SelectComPortIndex == -1 )
		{
			System.out.println("Invalid COM port selection (" + String.valueOf(SelectComPortIndex + ")"));
			return;
		}
		
		if( isComPortOpen ) 
		{
			System.out.println("A port is already opened");
			return;
		}
		
		comPort = availableSerialPorts[SelectComPortIndex];
		
		// default connection settings for Arduino
		comPort.setComPortParameters(9600, 8, 1, 0); 
		
		String portName = comPort.getSystemPortName();
		
		// Port opening
		if( !comPort.openPort() )
		{
			System.out.println("Port opening " + portName + " failed");
		    return;
		}
		isComPortOpen = true;
		System.out.println("Port (" + portName + ") is open" );
		
		
		comPort.addDataListener(
				new SerialPortDataListener() 
				{
				   @Override
				   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE | SerialPort.LISTENING_EVENT_PORT_DISCONNECTED; }
				   
				   @Override
				   public void serialEvent(SerialPortEvent event)
				   {
				      if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE && event.getEventType() != SerialPort.LISTENING_EVENT_PORT_DISCONNECTED)
				         return;
				      if (event.getEventType() == SerialPort.LISTENING_EVENT_PORT_DISCONNECTED)
				      {
					      System.out.println("Connection has been lost. COM port closing");
					      stopListening();
				      }   
				      
				      byte[] newData = new byte[comPort.bytesAvailable()];
				      int numRead = comPort.readBytes(newData, newData.length);
				      System.out.println("Read " + numRead + " bytes.");
				      for(byte b : newData)
				      {
				    	  System.out.print((char)b);
				      }
				      System.out.println();
				      
				      
				      String packet = new String(newData);
				      int startIndex = packet.indexOf("#$*");
				      int endIndex = packet.indexOf("*&#");
				      
				      if( endIndex > startIndex && startIndex >= 0)
				      {
				    	  String payload = packet.substring(startIndex+3, endIndex);
				    	  System.out.println(payload);
				    	  
				    	  TelemetryData td = new TelemetryData();
				    	  td.parsePacket(payload);
				    	  td.print();	
				    	  latestData = td;
				    	  db.addRecord(td);
					      
				      }
				   }
				}
			);
			
		
	}
	
	public void stopListening()
	{
		if( comPort == null )
			return;
		comPort.removeDataListener();
		comPort.closePort();
		isComPortOpen = false;
	}
	
	void onDataReceived()
	{
		//Validate data packet
		
		//copy data packet into td
		
		//add record into db
		
		//update GUI
		
		
		
		
	}
	
	
	public TelemetryData retrieveDataFromDB( LocalDateTime startDate, LocalDateTime endDate )
	{
		return db.retrieveRecord(startDate, endDate);	
	}
	
	
}
