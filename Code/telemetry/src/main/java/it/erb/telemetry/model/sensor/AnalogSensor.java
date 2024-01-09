package it.erb.telemetry.model.sensor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AnalogSensor extends Sensor
{
	private float value;
	
	public float getValue() { return value; }
	
	public StringProperty  getProperty() { return new SimpleStringProperty(Float.toString(value)); }

	public void setValue(float value) { this.value = value; }
	
	public void setValue(String value) { this.value = Float.valueOf(value); }
	
	
		
}
