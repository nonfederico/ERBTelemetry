package it.erb.telemetry.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model 
{
	public StringProperty data;
	
	public Model()
	{
		data = new SimpleStringProperty("CIAO");
		
		
	}
	
}
