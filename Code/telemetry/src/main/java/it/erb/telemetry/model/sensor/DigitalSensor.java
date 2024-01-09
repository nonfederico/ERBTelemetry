package it.erb.telemetry.model.sensor;

public class DigitalSensor extends Sensor
{
	private boolean value;
	
	public boolean getValue() { return value; }

	public void setValue(boolean value) { this.value = value; }
	
	public void setValue(String value) { this.value = value.equals("1"); }
		
}
