package it.erb.telemetry.view;

import javafx.scene.control.Label;

public class DataLabel extends Label
{
	private int decimals;
	private boolean isUnitVisible;
	
	DataLabel()
	{
		super();
		decimals = 0;
		isUnitVisible = true;
		this.getStyleClass().add("dataLabel");
	}
	
	DataLabel(String text)
	{
		super(text);
		decimals = 0;
		isUnitVisible = true;		
		this.getStyleClass().add("dataLabel");
	}
	
	DataLabel(String text, int decimals, boolean isUnitVisible )
	{
		super(text);
		this.decimals = (decimals >= 0) ? decimals : 0;
		this.isUnitVisible = isUnitVisible;		
		this.getStyleClass().add("dataLabel");
	}
	
	public void update(double value, String unit)
	{
		String s1 = String.format("%."+decimals+"f", value);
		s1 = s1 + ((isUnitVisible) ? " "+unit : "");
		this.setText(s1);
	}
	
}
