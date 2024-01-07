package it.erb.telemetry.model;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TelemetryData 
{
	public LocalDateTime date;
	public Float ThrottlePedal_Pos = 0f;
	public Float BrakePedal_Pos;
	public Float SteeringWheel_Pos;
	public Float Acc_Voltage = 0f;
	public Float Acc_SoC;
	public Float Acc_Current;
	public Float Acc_Temp;
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
		System.out.println(Acc_Voltage);

		System.out.print("Acc_SoC: ");
		System.out.println(Acc_SoC);

		System.out.print("Acc_Current: ");
		System.out.println(Acc_Current);
		
		System.out.print("ThrottlePedal_Pos: ");
		System.out.println(ThrottlePedal_Pos);
		
		System.out.print("MotorRL_CmdSpeed: ");
		System.out.println(MotorRL_CmdSpeed);
		
		System.out.print("MotorRL_CmdTorque: ");
		System.out.println(MotorRL_CmdTorque);
		
	
	}
	
	public void parsePacket(String packet)
	{
		HashMap<String, Float> map = new HashMap<String, Float>();
		
		map.put("F01", Acc_Voltage );
		map.put("F02", Acc_Current );
		map.put("F03", Acc_SoC );
				
		date = LocalDateTime.now();
		String[] values = packet.split(",");
		
		System.out.print(Acc_Voltage);
		Acc_Voltage = 1f;
		System.out.print(" " + Acc_Voltage);
		Float f = Acc_Voltage;
		System.out.print(" " + f);
		f = 2f;
		System.out.print(" " + f);
		System.out.println(" " + Acc_Voltage);
		/*for(String s : values)
		{
			
			System.out.print(s);
			String[] d = s.split("=");
		
			Float data = map.get(d[0]);
			System.out.print(" " + data);
			data = 67.8f;
			//data = Float.valueOf(d[1]);
			System.out.println(" " + data);
		}*/
		
		
	}
}
