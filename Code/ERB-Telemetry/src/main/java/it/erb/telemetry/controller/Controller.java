package it.erb.telemetry.controller;

import it.erb.telemetry.model.Model;
import it.erb.telemetry.view.View;

public class Controller 
{
	private Model model;
	private View view; 
	
	public Controller(Model model, View view)
	{
		this.model = model;
		this.view = view;
		
		
		
	}
	
	public void updateView()
	{
		view.lbl_accCurrentData.textProperty().bind(model.data);
	}
}
