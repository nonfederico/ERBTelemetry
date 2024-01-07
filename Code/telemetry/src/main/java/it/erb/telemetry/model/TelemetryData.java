package it.erb.telemetry.model;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TelemetryData 
{
	public LocalDateTime date;
	public Float ThrottlePedal_Pos = 0f;
	public Float BrakePedal_Pos;
	public Float SteeringWheel_Pos;
	public Measurement<Float> Acc_Voltage = new Measurement<Float>(0f);
	public Measurement<Float> Acc_SoC = new Measurement<Float>(0f);
	public Measurement<Float> Acc_Current = new Measurement<Float>(0f);
	public Measurement<Float> Acc_Temp = new Measurement<Float>(0f);
	public Float Acc_Cell_1_Temp;
	public Float Acc_Cell_2_Temp;
	public Float Acc_Cell_3_Temp;
	public Float Acc_Cell_4_Temp;
	public Float MotorRR_ActCurrent;
	public Float MotorRR_ActSpeed;
	public Float MotorRR_ActTorque;
	public Float MotorRR_CmdSpeed;
	public Float MotorRR_CmdTorque;
	public Float MotorRL_ActCurrent;
	public Float MotorRL_ActSpeed;
	public Float MotorRL_ActTorque;
	public Float MotorRL_CmdSpeed;
	public Float MotorRL_CmdTorque;
	
	
	

	
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
		System.out.println(ThrottlePedal_Pos);
		
		System.out.print("MotorRL_CmdSpeed: ");
		System.out.println(MotorRL_CmdSpeed);
		
		System.out.print("MotorRL_CmdTorque: ");
		System.out.println(MotorRL_CmdTorque);
		
	
	}
	
	public void parsePacket(String packet)
	{
		HashMap<String, Measurement<Float>> map = new HashMap<String, Measurement<Float>>();
		
		map.put("F01", Acc_Voltage );
		map.put("F02", Acc_SoC );
		map.put("F03", Acc_Current );
		
				
		date = LocalDateTime.now();
		String[] values = packet.split(",");
		
		
		for(String s : values)
		{
			System.out.print(s);
			String[] d = s.split("=");
		
			Measurement<Float> m = map.get(d[0]);
			if( m != null )
			{
				m.setValue(Float.valueOf(d[1]));
				System.out.println(" " + m.getValue());
			}
			
		}
		
		
	}
}
