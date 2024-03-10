package it.erb.telemetry.model;

import java.time.LocalDateTime;	
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import it.erb.telemetry.model.sensor.AnalogSensor;
import it.erb.telemetry.model.sensor.DigitalSensor;
import it.erb.telemetry.model.sensor.Sensor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TelemetryData 
{
	public ObjectProperty<LocalDateTime> date = new SimpleObjectProperty<>();
	public AnalogSensor throttlePedal_Pos = new AnalogSensor();
	public AnalogSensor brakePedal_Pos = new AnalogSensor();
	public AnalogSensor steeringWheel_Pos = new AnalogSensor("°");
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
	public AnalogSensor motorRR_ActCurrent = new AnalogSensor("A");
	public AnalogSensor motorRR_ActSpeed = new AnalogSensor("rpm");
	public AnalogSensor motorRR_ActTorque = new AnalogSensor("Nm");
	public AnalogSensor motorRR_CmdSpeed = new AnalogSensor("rpm");
	public AnalogSensor motorRR_CmdTorque = new AnalogSensor("Nm");
	public AnalogSensor motorRR_Frequency = new AnalogSensor("Hz");
	public AnalogSensor motorRR_Temperature = new AnalogSensor("°C");
	public AnalogSensor motorRL_ActCurrent = new AnalogSensor("A");
	public AnalogSensor motorRL_ActSpeed = new AnalogSensor("rpm");
	public AnalogSensor motorRL_ActTorque = new AnalogSensor("Nm");
	public AnalogSensor motorRL_CmdSpeed = new AnalogSensor("rpm");
	public AnalogSensor motorRL_CmdTorque = new AnalogSensor("Nm");
	public AnalogSensor motorRL_Frequency = new AnalogSensor("Hz");
	public AnalogSensor motorRL_Temperature = new AnalogSensor("°C");
	public AnalogSensor vehicle_linearSpeed = new AnalogSensor("km/h");
	public DigitalSensor saf_BSPD = new DigitalSensor();
	public DigitalSensor saf_IMD = new DigitalSensor();
	public DigitalSensor saf_LVMS = new DigitalSensor();
	public DigitalSensor saf_AMS = new DigitalSensor();
	public DigitalSensor saf_IS = new DigitalSensor();
	public DigitalSensor saf_BOTS = new DigitalSensor();
	public DigitalSensor saf_SDBCockpit = new DigitalSensor();
	public DigitalSensor saf_SDBLeft = new DigitalSensor();
	public DigitalSensor saf_SDBRight = new DigitalSensor();
	
	public void parsePacket(String packet)
	{
		HashMap<String, Sensor> map = new HashMap<String, Sensor>();
		
		map.put("F01", throttlePedal_Pos );
		map.put("F02", brakePedal_Pos );
		map.put("F03", steeringWheel_Pos );
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
		map.put("F18", motorRR_ActCurrent );
		map.put("F19", motorRR_ActSpeed );
		map.put("F20", motorRR_ActTorque );
		map.put("F21", motorRR_CmdSpeed );
		map.put("F22", motorRR_CmdTorque );
		map.put("F23", motorRL_ActCurrent );
		map.put("F24", motorRL_ActSpeed );
		map.put("F25", motorRL_ActTorque );
		map.put("F26", motorRL_CmdSpeed );
		map.put("F27", motorRL_CmdTorque );
		map.put("F28", motorRL_Frequency );
		map.put("F29", motorRR_Frequency );
		map.put("F30", motorRL_Temperature );
		map.put("F31", motorRR_Temperature );
		map.put("F32", vehicle_linearSpeed );
		map.put("B01", saf_BSPD );
		map.put("B02", saf_IMD );
		map.put("B03", saf_LVMS );
		map.put("B04", saf_AMS );
		map.put("B05", saf_IS );
		map.put("B06", saf_BOTS );
		map.put("B07", saf_SDBCockpit );
		map.put("B08", saf_SDBLeft );
		map.put("B09", saf_SDBRight );
		
		date.set(LocalDateTime.now());
		
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
	
	public String toString()
	{
		String s = new String();
		String separator = ";";
		
		s = s + date.get().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + separator;
		s = s + throttlePedal_Pos.toString(1,false) + separator;
		s = s + brakePedal_Pos.toString(1,false) + separator;
		s = s + steeringWheel_Pos.toString(1,false) + separator;
		s = s + HVAcc_Voltage.toString(1,false) + separator;
		s = s + HVAcc_SoC.toString(1,false) + separator;
		s = s + HVAcc_Current.toString(1,false) + separator;
		s = s + HVAcc_Temp.toString(1,false) + separator;
		s = s + HVAcc_Cell_1_Temp.toString(1,false) + separator;
		s = s + HVAcc_Cell_2_Temp.toString(1,false) + separator;
		s = s + HVAcc_Cell_3_Temp.toString(1,false) + separator;
		s = s + HVAcc_Cell_4_Temp.toString(1,false) + separator;
		s = s + LVAcc_Voltage.toString(1,false) + separator;
		s = s + LVAcc_SoC.toString(1,false) + separator;
		s = s + LVAcc_Current.toString(1,false) + separator;
		s = s + inv_Temperature.toString(1,false) + separator;
		s = s + inv_HVVoltage.toString(1,false) + separator;
		s = s + inv_LVVoltage.toString(1,false) + separator;
		s = s + motorRR_ActCurrent.toString(1,false) + separator;
		s = s + motorRR_ActSpeed.toString(1,false) + separator;
		s = s + motorRR_ActTorque.toString(1,false) + separator;
		s = s + motorRR_CmdSpeed.toString(1,false) + separator;
		s = s + motorRR_CmdTorque.toString(1,false) + separator;
		s = s + motorRR_Frequency.toString(1,false) + separator;
		s = s + motorRR_Temperature.toString(1,false) + separator;
		s = s + motorRL_ActCurrent.toString(1,false) + separator;
		s = s + motorRL_ActSpeed.toString(1,false) + separator;
		s = s + motorRL_ActTorque.toString(1,false) + separator;
		s = s + motorRL_CmdSpeed.toString(1,false) + separator;
		s = s + motorRL_CmdTorque.toString(1,false) + separator;
		s = s + motorRL_Frequency.toString(1,false) + separator;
		s = s + motorRL_Temperature.toString(1,false) + separator;
		s = s + vehicle_linearSpeed.toString(1,false) + separator;
		s = s + saf_BSPD.toString() + separator;
		s = s + saf_IMD.toString() + separator;
		s = s + saf_LVMS.toString() + separator;
		s = s + saf_AMS.toString() + separator;
		s = s + saf_IS.toString() + separator;
		s = s + saf_BOTS.toString() + separator;
		s = s + saf_SDBCockpit.toString() + separator;
		s = s + saf_SDBLeft.toString() + separator;
		s = s + saf_SDBRight.toString();
		
		return s;
	}
	
	public static String namesToString()
	{
		String s = new String();
		String separator = ";";
		
		s = s + "Time" + separator;
		s = s + "Throttle pedal position" + separator;
		s = s + "Brake pedal position" + separator;
		s = s + "Steering wheel position" + separator;
		s = s + "HV acc. voltage" + separator;
		s = s + "HV acc. SoC" + separator;
		s = s + "HV acc. current" + separator;
		s = s + "HV acc. temperature" + separator;
		s = s + "HV acc. cell 1 temperature" + separator;
		s = s + "HV acc. cell 2 temperature" + separator;
		s = s + "HV acc. cell 3 temperature" + separator;
		s = s + "HV acc. cell 4 temperature" + separator;
		s = s + "LV acc. voltage" + separator;
		s = s + "LV acc. SoC" + separator;
		s = s + "LV acc. current" + separator;
		s = s + "Inverter temperature" + separator;
		s = s + "Inverter HV voltage" + separator;
		s = s + "Inverter LV voltage" + separator;
		s = s + "Motor RR actual current" + separator;
		s = s + "Motor RR actual speed" + separator;
		s = s + "Motor RR actual torque" + separator;
		s = s + "Motor RR command speed" + separator;
		s = s + "Motor RR command torque" + separator;
		s = s + "Motor RR frequency" + separator;
		s = s + "Motor RR temperature" + separator;
		s = s + "Motor RL actual current" + separator;
		s = s + "Motor RL actual speed" + separator;
		s = s + "Motor RL actual torque" + separator;
		s = s + "Motor RL command speed" + separator;
		s = s + "Motor RL command torque" + separator;
		s = s + "Motor RL frequency" + separator;
		s = s + "Motor RR temperature" + separator;
		s = s + "Vehicle linear speed" + separator;
		s = s + "Safety circuit BSPD" + separator;
		s = s + "Safety circuit IMD" + separator;
		s = s + "Safety circuit LVMS" + separator;
		s = s + "Safety circuit AMS" + separator;
		s = s + "Safety circuit IS" + separator;
		s = s + "Safety circuit BOTS" + separator;
		s = s + "Safety circuit SDB cockpit" + separator;
		s = s + "Safety circuit BOTS SDB left" + separator;
		s = s + "Safety circuit BOTS SDB right";
		
		return s;
	}
	
}
