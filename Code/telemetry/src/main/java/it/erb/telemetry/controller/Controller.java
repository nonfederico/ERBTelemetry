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
		
		view.chBox_comPort.setItems(model.getSerialPortName());
		
		view.chBox_comPort.setOnAction((event) -> {
			System.out.println(view.chBox_comPort.getItems());
		    int selectedIndex = view.chBox_comPort.getSelectionModel().getSelectedIndex();
		    Object selectedItem = view.chBox_comPort.getSelectionModel().getSelectedItem();
		    
		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ChoiceBox.getValue(): " + view.chBox_comPort.getValue());
		
		});
		
				
		
		
		view.chBox_comPort.addEventHandler(ChoiceBox.ON_SHOWING, event -> model.loadSerialPorts());
		
		//view.btn_comScan.setOnAction(event -> model.startListening() );
		view.btn_comScan.setOnAction((event) -> {
		    int selectedIndex = view.chBox_comPort.getSelectionModel().getSelectedIndex();
		    Object selectedItem = view.chBox_comPort.getSelectionModel().getSelectedItem();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ChoiceBox.getValue(): " + view.chBox_comPort.getValue());
		
		});
		
		
	}
}
