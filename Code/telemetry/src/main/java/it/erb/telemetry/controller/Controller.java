package it.erb.telemetry.controller;


import it.erb.telemetry.model.Model;
import it.erb.telemetry.view.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;

public class Controller 
{
	public Controller(Model model, View view)
	{
		
		
		view.chBox_comPort.setItems(model.getSerialPortName());
			
		view.chBox_comPort.setOnAction((event) -> {
			int selectedIndex = view.chBox_comPort.getSelectionModel().getSelectedIndex();
			Object selectedItem = view.chBox_comPort.getSelectionModel().getSelectedItem();
		    
			model.setSelectedComPortIndex(selectedIndex);
		    //System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    //System.out.println("   ChoiceBox.getValue(): " + view.chBox_comPort.getValue());
		});
		
		view.chBox_comPort.addEventHandler(ChoiceBox.ON_SHOWING, event -> model.loadSerialPorts());
		
		view.btn_comConnect.setOnAction(event -> model.startListening() );
		
		view.btn_comDisconnect.setOnAction(event -> model.stopListening() );
		
	}
}
