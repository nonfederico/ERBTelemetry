package it.erb.telemetry.model;

import java.time.LocalDateTime;

public class TelemetryData 
{
	public LocalDateTime date;
	public float ThrottlePedal_Pos;
	public float BrakePedal_Pos;
	public float SteeringWheel_Pos;
	public float Acc_Voltage;
	public float Acc_SoC;
	public float Acc_Current;
	public float Acc_Temp;
	public float Acc_Cell_1_Temp;
	public float Acc_Cell_2_Temp;
	public float Acc_Cell_3_Temp;
	public float Acc_Cell_4_Temp;
	public float MotorRR_ActCurrent;
	public float MotorRR_ActSpeed;
	public float MotorRR_ActTorque;
	public float MotorRR_CmdSpeed;
	public float MotorRR_CmdTorque;
	public float MotorRL_ActCurrent;
	public float MotorRL_ActSpeed;
	public float MotorRL_ActTorque;
	public float MotorRL_CmdSpeed;
	public float MotorRL_CmdTorque;

	
	// Stampa i campi su console
	public void print()
	{
		System.out.print("Date: ");
		System.out.println(date);
		
		System.out.print("ThrottlePedal_Pos: ");
		System.out.println(ThrottlePedal_Pos);
		
		System.out.print("MotorRL_CmdSpeed: ");
		System.out.println(MotorRL_CmdSpeed);
		
		System.out.print("MotorRL_CmdTorque: ");
		System.out.println(MotorRL_CmdTorque);
		
	}
}
