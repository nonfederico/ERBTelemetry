package it.erb.telemetry.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

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
		comPort.setComPortParameters(38400, 8, 1, 0); 
		
		String portName = comPort.getSystemPortName();
		
		// Port opening
		if( !comPort.openPort() )
		{
			System.out.println("Port opening " + portName + " failed");
		    return;
		}
		isComPortOpen = true;
		System.out.println("Port (" + portName + ") is open" );
		
		comPort.addDataListener(new SerialPortMessageListener(){
			@Override
			   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED | SerialPort.LISTENING_EVENT_PORT_DISCONNECTED; }
			   
			   @Override
			   public byte[] getMessageDelimiter() { return new byte[] { (byte)0x2A, (byte)0x26, (byte)0x23 }; }

			   @Override
			   public boolean delimiterIndicatesEndOfMessage() { return true; }

			   @Override
			   public void serialEvent(SerialPortEvent event)
			   {

				  if (event.getEventType() == SerialPort.LISTENING_EVENT_PORT_DISCONNECTED)
				  {
				      System.out.println("Connection has been lost. COM port closing");
				      stopListening();
				  }  
				 
				  if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED)
				  {
				      byte[] message = event.getReceivedData();
					  
				      System.out.println("(COM) read " + message.length + " bytes.");
				      for(byte b : message)
				      {
				    	  System.out.print((char)b);
				      }
				      System.out.println();
				      
				      handleComPacket(message);
				  } 
				  
				 
			   }
			});
			
		
	}
	
	public void stopListening()
	{
		if( comPort == null )
			return;
		comPort.removeDataListener();
		comPort.closePort();
		isComPortOpen = false;
	}
	
	
	public void handleComPacket(byte[] packet)
	{
		String text = new String(packet);
		
		int startIndex = text.indexOf("#$*");
		int endIndex = text.indexOf("*&#");
		 
		if( endIndex > startIndex && startIndex >= 0)
		{
			text = text.substring(startIndex+3, endIndex);
			
			TelemetryData td = new TelemetryData();
			
			td.parsePacket(text);
						
			// Save latest data received
			latestData = td;
			
			// Add data to database
			db.addRecord(td);
		}

		
	}
	
	
	public List<TelemetryData> retrieveDataFromDB( LocalDateTime startDate, LocalDateTime endDate )
	{
		return db.retrieveRecord(startDate, endDate);	
	}
	
	
}



