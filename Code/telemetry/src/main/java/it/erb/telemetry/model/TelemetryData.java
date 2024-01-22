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
	public AnalogSensor SteeringWheel_Pos = new AnalogSensor("°");
	public AnalogSensor HVAcc_Voltage = new AnalogSensor("V");
	public AnalogSensor HVAcc_SoC = new AnalogSensor("%");
	public AnalogSensor HVAcc_Current = new AnalogSensor("A");
	public AnalogSensor HVAcc_Temp = new AnalogSensor("°C");
	public AnalogSensor HVAcc_Cell_1_Temp = new AnalogSensor("°C");
	public AnalogSensor HVAcc_Cell_2_Temp = new AnalogSensor("°C");
	public AnalogSensor HVAcc_Cell_3_Temp = new AnalogSensor("°C");
	public AnalogSensor HVAcc_Cell_4_Temp = new AnalogSensor("°C");
	public AnalogSensor LVAcc_Voltage = new AnalogSensor("V");
	public AnalogSensor LVAcc_SoC = new AnalogSensor("%");
	public AnalogSensor LVAcc_Current = new AnalogSensor("A");
	public AnalogSensor inv_Temperature = new AnalogSensor("°C");
	public AnalogSensor inv_HVVoltage = new AnalogSensor("V");
	public AnalogSensor inv_LVVoltage = new AnalogSensor("V");
	public AnalogSensor MotorRR_ActCurrent = new AnalogSensor("A");
	public AnalogSensor MotorRR_ActSpeed = new AnalogSensor("rpm");
	public AnalogSensor MotorRR_ActTorque = new AnalogSensor("Nm");
	public AnalogSensor MotorRR_CmdSpeed = new AnalogSensor("rpm");
	public AnalogSensor MotorRR_CmdTorque = new AnalogSensor("Nm");
	public AnalogSensor MotorRL_ActCurrent = new AnalogSensor("A");
	public AnalogSensor MotorRL_ActSpeed = new AnalogSensor("rpm");
	public AnalogSensor MotorRL_ActTorque = new AnalogSensor("Nm");
	public AnalogSensor MotorRL_CmdSpeed = new AnalogSensor("rpm");
	public AnalogSensor MotorRL_CmdTorque = new AnalogSensor("Nm");
	public AnalogSensor MotorRL_Frequency = new AnalogSensor("Hz");
	public AnalogSensor MotorRR_Frequency = new AnalogSensor("Hz");
	public AnalogSensor MotorRL_Temperature = new AnalogSensor("°C");
	public AnalogSensor MotorRR_Temperature = new AnalogSensor("°C");
	public AnalogSensor vehicle_linearSpeed = new AnalogSensor("km/h");
	public DigitalSensor saf_BSPD = new DigitalSensor();
	public DigitalSensor saf_IMD = new DigitalSensor();
	public DigitalSensor saf_LVMS = new DigitalSensor();
	public DigitalSensor saf_AMS = new DigitalSensor();
	public DigitalSensor saf_IS = new DigitalSensor();
	public DigitalSensor saf_BOTS = new DigitalSensor();
	public DigitalSensor saf_SDBCockpit = new DigitalSensor();
	public DigitalSensor saf_SDBLeft = new DigitalSensor();
	public DigitalSensor saf_SDBRght = new DigitalSensor();
	
	public void parsePacket(String packet)
	{
		HashMap<String, Sensor> map = new HashMap<String, Sensor>();
		
		map.put("F01", ThrottlePedal_Pos );
		map.put("F02", BrakePedal_Pos );
		map.put("F03", SteeringWheel_Pos );
		map.put("F04", HVAcc_Voltage );
		map.put("F05", HVAcc_SoC );
		map.put("F06", HVAcc_Current );
		map.put("F07", HVAcc_Temp );
		map.put("F08", HVAcc_Cell_1_Temp );
		map.put("F09", HVAcc_Cell_2_Temp );
		map.put("F10", HVAcc_Cell_3_Temp );
		map.put("F11", HVAcc_Cell_4_Temp );
		map.put("F12", LVAcc_Voltage );
		map.put("F13", LVAcc_SoC );
		map.put("F14", LVAcc_Current );
		map.put("F15", inv_Temperature );
		map.put("F16", inv_HVVoltage );
		map.put("F17", inv_LVVoltage );
		map.put("F18", MotorRR_ActCurrent );
		map.put("F19", MotorRR_ActSpeed );
		map.put("F20", MotorRR_ActTorque );
		map.put("F21", MotorRR_CmdSpeed );
		map.put("F22", MotorRR_CmdTorque );
		map.put("F23", MotorRL_ActCurrent );
		map.put("F24", MotorRL_ActSpeed );
		map.put("F25", MotorRL_ActTorque );
		map.put("F26", MotorRL_CmdSpeed );
		map.put("F27", MotorRL_CmdTorque );
		map.put("F28", MotorRL_Frequency );
		map.put("F29", MotorRR_Frequency );
		map.put("F30", MotorRL_Temperature );
		map.put("F31", MotorRR_Temperature );
		map.put("F32", vehicle_linearSpeed );
		map.put("B01", saf_BSPD );
		map.put("B02", saf_IMD );
		map.put("B03", saf_LVMS );
		map.put("B04", saf_AMS );
		map.put("B05", saf_IS );
		map.put("B06", saf_BOTS );
		map.put("B07", saf_SDBCockpit );
		map.put("B08", saf_SDBLeft );
		map.put("B09", saf_SDBRght );
		
		date = LocalDateTime.now();
		
		String[] packets = packet.split(";");
			
		for(String p : packets)
		{
			if(p.indexOf(':') == -1)
				break;
			
			String[] substring = p.split(":");
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
