package it.erb.telemetry.model;

public class Measurement<T>{
	
	private T value;	
	
	public Measurement(T value)
	{
		this.value = value;		
	}
		
	public T getValue(){ return value; }
	
	public void setValue(T newValue){ value = newValue; }
	
	
}
