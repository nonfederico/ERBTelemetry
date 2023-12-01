package serial;

import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;

public class App 
{
    public static void main( String[] args )
    {
    	ComHandler comHandler = new ComHandler();
    	
    	ComHandler.printComAvailable();
    	
    	System.out.println(comHandler.getPort());
    	
    	comHandler.startListening();
    	
    
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    
    }
}
