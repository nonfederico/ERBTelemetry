package it.erb.telemetry.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import it.erb.telemetry.model.Model;
import it.erb.telemetry.model.TelemetryData;
import it.erb.telemetry.model.sensor.AnalogSensor;
import it.erb.telemetry.view.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.util.FXPermission;

public class Controller 
{
	public Controller(Model model, View view)
	{
		// UI periodic update
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000),
				
			e ->
			{
				model.td.Acc_Temp.setValue((float) (Math.random()*100f));
				model.td.Acc_SoC.setValue((float) (Math.random()*100f));
				model.td.LVAcc_SoC.setValue((float) (Math.random()*100f));
				
				
				view.gg_hvAcc.setValue(model.td.Acc_SoC.getValue());
        		view.gg_lvAcc.setValue(model.td.LVAcc_SoC.getValue());	
        		view.gg_linearSpeed.setValue(Math.random()*130f);
        		view.lbl_accCurrentData.setText(String.format("%.2f", Math.random()*100f) );	
        		updateLabel(view.lbl_accTempData, model.td.Acc_Temp);	
				System.out.println("UI update");
					
			}));
		   
		timeline.setCycleCount(Animation.INDEFINITE); // loop forever
		timeline.play();
		
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
		
		view.btn_tableLoad.setOnAction(event -> {
			
			LocalDate sDate = view.dp_tableStartDate.getValue();
			LocalDate eDate = view.dp_tableEndDate.getValue();
			
			LocalDateTime sDateTime = LocalDateTime.of(sDate, LocalTime.MIDNIGHT);
			LocalDateTime eDateTime = LocalDateTime.of(eDate, LocalTime.MIDNIGHT);
			
			System.out.println("Query date range: " + sDateTime + " " + eDateTime);
			
			TelemetryData td = model.retrieveDataFromDB( sDateTime, eDateTime);
			
			td.print();
			//view.tableView.getItems().add(td);
			ObservableList<TelemetryData> items = FXCollections.<TelemetryData>observableArrayList();
			
			items.add(td);
			items.add(td);
			items.add(td);
			
			
			view.tableView.setItems(items);
		});
		
		
	}
	
	void updateLabel(Label label, AnalogSensor sensor)
	{
		label.setText(String.format("%.2f", sensor.getValue()) + " " + sensor.getUnit() );
	}
}
