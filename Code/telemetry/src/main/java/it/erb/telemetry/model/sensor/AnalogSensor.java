package it.erb.telemetry.model.sensor;

public class AnalogSensor extends Sensor
{
	private float value;
	
	public float getValue() { return value; }

	public void setValue(float value) { this.value = value; }
	
	public void setValue(String value) { this.value = Float.valueOf(value); }
		
}
