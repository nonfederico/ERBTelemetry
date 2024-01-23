package it.erb.telemetry.model.sensor;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class DigitalSensor extends Sensor
{
	private boolean value;
	
	public boolean getValue() { return value; }
	
	public BooleanProperty  getProperty() { return new SimpleBooleanProperty(value); }

	public void setValue(boolean value) { this.value = value; }
	
	public void setValue(String value) { this.value = value.equals("1"); }
	
	
}
