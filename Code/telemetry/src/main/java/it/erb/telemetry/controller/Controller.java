package it.erb.telemetry.controller;


import it.erb.telemetry.model.Model;
import it.erb.telemetry.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;

public class Controller 
{
	public Controller(Model model, View view)
	{
		view.chBox_comPort.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Ciao");
				
				
			}
		
		});
		
		view.chBox_comPort.addEventHandler(ChoiceBox.ON_SHOWING, event -> {
		    System.out.println("CheckComboBox is now showing.");
		    model.loadSerialPorts();
		    //view.chBox_comPort.getItems().addAll(null);
		});
		
		view.btn_comScan.setOnAction(event -> System.out.println("COM Scan button pressed") );
		
		
	}
}
