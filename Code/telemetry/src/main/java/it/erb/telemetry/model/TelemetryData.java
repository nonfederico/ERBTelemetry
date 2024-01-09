package it.erb.telemetry.model;

import java.time.LocalDateTime;
import java.util.HashMap;

import it.erb.telemetry.model.sensor.AnalogSensor;
import it.erb.telemetry.model.sensor.DigitalSensor;
import it.erb.telemetry.model.sensor.Sensor;

public class TelemetryData 
{
	public LocalDateTime date;
	public AnalogSensor ThrottlePedal_Pos = new AnalogSensor();
	public AnalogSensor BrakePedal_Pos = new AnalogSensor();
	public AnalogSensor SteeringWheel_Pos = new AnalogSensor();
	public AnalogSensor Acc_Voltage = new AnalogSensor();
	public AnalogSensor Acc_SoC = new AnalogSensor();
	public AnalogSensor Acc_Current = new AnalogSensor();
	public AnalogSensor Acc_Temp = new AnalogSensor();
	public AnalogSensor Acc_Cell_1_Temp = new AnalogSensor();
	public AnalogSensor Acc_Cell_2_Temp = new AnalogSensor();
	public AnalogSensor Acc_Cell_3_Temp = new AnalogSensor();
	public AnalogSensor Acc_Cell_4_Temp = new AnalogSensor();
	public AnalogSensor MotorRR_ActCurrent = new AnalogSensor();
	public AnalogSensor MotorRR_ActSpeed = new AnalogSensor();
	public AnalogSensor MotorRR_ActTorque = new AnalogSensor();
	public AnalogSensor MotorRR_CmdSpeed = new AnalogSensor();
	public AnalogSensor MotorRR_CmdTorque = new AnalogSensor();
	public AnalogSensor MotorRL_ActCurrent = new AnalogSensor();
	public AnalogSensor MotorRL_ActSpeed = new AnalogSensor();
	public AnalogSensor MotorRL_ActTorque = new AnalogSensor();
	public AnalogSensor MotorRL_CmdSpeed = new AnalogSensor();
	public AnalogSensor MotorRL_CmdTorque = new AnalogSensor();
	public DigitalSensor Saf_BSPD = new DigitalSensor();
	

	
	// Stampa i campi su console
	public void print()
	{
		System.out.print("Date: ");
		System.out.println(date);
		
		System.out.print("Acc_Voltage: ");
		System.out.println(Acc_Voltage.getValue());

		System.out.print("Acc_SoC: ");
		System.out.println(Acc_SoC.getValue());

		System.out.print("Acc_Current: ");
		System.out.println(Acc_Current.getValue());
		
		System.out.print("ThrottlePedal_Pos: ");
		System.out.println(ThrottlePedal_Pos.getValue());
		
		System.out.print("MotorRL_CmdSpeed: ");
		System.out.println(MotorRL_CmdSpeed.getValue());
		
		System.out.print("MotorRL_CmdTorque: ");
		System.out.println(MotorRL_CmdTorque.getValue());
		
	
	}
	
	public void parsePacket(String packet)
	{
		HashMap<String, Sensor> map = new HashMap<String, Sensor>();
		
		map.put("F01", Acc_Voltage );
		map.put("F02", Acc_SoC );
		map.put("F03", Acc_Current );
		
		date = LocalDateTime.now();
		
		String[] values = packet.split(",");
			
		for(String s : values)
		{
			String[] substring = s.split("=");
			String id = substring[0];
			String value = substring[1];
			
			Sensor sensor = map.get(id);
			if( sensor != null )
			{
				sensor.setValue(value);
			}
			
		}
		
		
	}
}
