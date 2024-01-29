package it.erb.telemetry.model.sensor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AnalogSensor extends Sensor
{
	private float value;
	private String unit;
	
	
	public AnalogSensor()
	{
		this.value = 0;
		this.unit = "";
	}
	
	public AnalogSensor(String unit)
	{
		this.value = 0;
		this.unit = unit;
	}
	
	public AnalogSensor(float value, String unit)
	{
		this.value = value;
		this.unit = unit;
	}
	
	public float getValue() { return value; }
	
	public StringProperty  getProperty() { return new SimpleStringProperty(Float.toString(value)); }

	public void setValue(float value) { this.value = value; }
	
	public void setValue(String value) { this.value = Float.valueOf(value); }

	public String getUnit() { return unit; }

	public void setUnit(String unit) { this.unit = unit; }
	
	public String toString(){ return String.format("%.0f", value) + unit; }
		
	public String toString(int decimals){ return this.toString(decimals, true); }
	
	public String toString(int decimals, boolean showUnit){ return String.format("%."+decimals+"f", value) + (showUnit ? unit : "") ; }
}
