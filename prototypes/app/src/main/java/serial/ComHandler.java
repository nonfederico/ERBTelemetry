package serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class ComHandler 
{
	private SerialPort comPort;
	private String activePortDescriptor;
	
	
	ComHandler()
	{
		// Assegna di default la prima porta disponibile
		if( SerialPort.getCommPorts().length > 0 )
		{
			activePortDescriptor = SerialPort.getCommPorts()[0].getSystemPortName();
		}
		
		
	}
	
	public boolean startListening()
	{
		comPort = SerialPort.getCommPort(activePortDescriptor);
				
		// verifica se la COM port selezionata esiste
		if( comPort == null )
		{
			System.out.println("La porta seriale selezionata (" + activePortDescriptor + ") non é disponibile");
			return false;
		}
		
		// TODO Verifica che la COM port selezionata non sia giá aperta
		
		// default connection settings for Arduino
		comPort.setComPortParameters(9600, 8, 1, 0); 
		
		// Apertura della porta
		if( !comPort.openPort() )
		{
			System.out.println("Apertura della porta fallita");
		    return false;
		}
		System.out.println("La porta (" + activePortDescriptor + ") é aperta" );
		
		
		comPort.addDataListener(
			new SerialPortDataListener() 
			{
			   @Override
			   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
			   @Override
			   public void serialEvent(SerialPortEvent event)
			   {
			      if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
			         return;
			      byte[] newData = new byte[comPort.bytesAvailable()];
			      int numRead = comPort.readBytes(newData, newData.length);
			      System.out.println("Read " + numRead + " bytes.");
			      for(byte b : newData)
			      {
			    	  System.out.print((char)b);
			      }
			      System.out.println();
			      
			      
			      String text = new String(newData);
			      int startIndex = text.indexOf("#$*");
			      int endIndex = text.indexOf("*&#");
			      
			      if( endIndex > startIndex && startIndex >= 0)
			      {
			    	  text = text.substring(startIndex+3, endIndex);
			    	  System.out.println(text);
				      String[] values = text.split(",");
				      for(String s : values)
				      {
				    	  System.out.println(s);
				      }
			      }
			   }
			}
		);
		
		return true;
	}
	
	String getPort()
	{
		return this.activePortDescriptor;
	}
	
	
	void setPort(String portDescriptor)
	{
		// TODO bisognerebbe impedire la modifica della porta se questa é aperta
		activePortDescriptor = portDescriptor;
	}
	
	
	public static void printComAvailable()
	{
		System.out.println( "Numero COM disponibili: " + SerialPort.getCommPorts().length );
		for( SerialPort item : SerialPort.getCommPorts() )
    	{
    		System.out.println( item.getSystemPortName() ); 		
    	}
	}
	
	
}
