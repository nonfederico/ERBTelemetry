package it.erb.telemetry.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import it.erb.telemetry.model.Model;
import it.erb.telemetry.model.TelemetryData;
import it.erb.telemetry.model.sensor.AnalogSensor;
import it.erb.telemetry.view.DataLabel;
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
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200),
				
			e ->
			{
				model.td.Acc_Voltage.setValue((float) (Math.random()*700f));
				model.td.Acc_Current.setValue((float) (Math.random()*150f));
				model.td.Acc_Temp.setValue((float) (Math.random()*100f));
				model.td.Acc_SoC.setValue((float) (Math.random()*100f));
				model.td.LVAcc_SoC.setValue((float) (Math.random()*100f));
				
				
				
        		view.gg_HVAcc.setValue(model.td.Acc_SoC.getValue());
        		view.lbl_HVAccVoltage.update(model.td.Acc_Voltage.getValue(), model.td.Acc_Voltage.getUnit());
        		view.lbl_HVAccCurrent.update(model.td.Acc_Current.getValue(), model.td.Acc_Current.getUnit());
        		view.lbl_HVAccPower.update(model.td.Acc_Voltage.getValue()*model.td.Acc_Current.getValue(), "W");
        		view.lbl_HVAccTemp.update(model.td.Acc_Temp.getValue(), model.td.Acc_Temp.getUnit());
        		
        		view.gg_LVAcc.setValue(model.td.LVAcc_SoC.getValue());	
        		view.lbl_LVAcc_Voltage.update(model.td.LVAcc_Voltage.getValue(), model.td.LVAcc_Voltage.getUnit());
        		view.lbl_LVAcc_Current.update(model.td.LVAcc_Current.getValue(), model.td.LVAcc_Current.getUnit());
        		
        		view.lbl_inv_Temperature.update(model.td.inv_Temperature.getValue(), model.td.inv_Temperature.getUnit());
        		view.lbl_inv_HVVoltage.update(model.td.inv_HVVoltage.getValue(), model.td.inv_HVVoltage.getUnit());
        		view.lbl_inv_LVVoltage.update(model.td.inv_LVVoltage.getValue(), model.td.inv_LVVoltage.getUnit());
        		
        		view.lbl_motRLActSpeed.update(model.td.MotorRL_ActSpeed.getValue(), model.td.MotorRL_ActSpeed.getUnit());
        		view.lbl_motRRActSpeed.update(model.td.MotorRR_ActSpeed.getValue(), model.td.MotorRR_ActSpeed.getUnit());
        		view.lbl_motRLCmdSpeed.update(model.td.MotorRL_CmdSpeed.getValue(), model.td.MotorRL_CmdSpeed.getUnit());
        		view.lbl_motRRCmdSpeed.update(model.td.MotorRR_CmdSpeed.getValue(), model.td.MotorRR_CmdSpeed.getUnit());
        		view.lbl_motRLActTorque.update(model.td.MotorRL_ActTorque.getValue(), model.td.MotorRL_ActTorque.getUnit());
        		view.lbl_motRRActTorque.update(model.td.MotorRR_ActTorque.getValue(), model.td.MotorRR_ActTorque.getUnit());
        		view.lbl_motRLCmdTorque.update(model.td.MotorRL_CmdTorque.getValue(), model.td.MotorRL_CmdTorque.getUnit());
        		view.lbl_motRRCmdTorque.update(model.td.MotorRR_CmdTorque.getValue(), model.td.MotorRR_CmdTorque.getUnit());
        		view.lbl_motRLActCurrent.update(model.td.MotorRL_ActCurrent.getValue(), model.td.MotorRL_ActCurrent.getUnit());
        		view.lbl_motRRActCurrent.update(model.td.MotorRR_ActCurrent.getValue(), model.td.MotorRR_ActCurrent.getUnit());
        		view.lbl_motRLActFrequency.update(model.td.MotorRL_Frequency.getValue(), model.td.MotorRL_Frequency.getUnit());
        		view.lbl_motRRActFrequency.update(model.td.MotorRR_Frequency.getValue(), model.td.MotorRR_Frequency.getUnit());
        		view.lbl_motRLTemperature.update(model.td.MotorRL_Temperature.getValue(), model.td.MotorRL_Temperature.getUnit());
        		view.lbl_motRRTemperature.update(model.td.MotorRR_Temperature.getValue(), model.td.MotorRR_Temperature.getUnit());
        		
        		view.led_safBSPD.update(Math.random()*100f<50);
        		view.led_safIMD.update(Math.random()*100f<50);
        		view.led_safLVMS.update(Math.random()*100f<50);
        		view.led_safAMS.update(Math.random()*100f<50);
        		view.led_safIS.update(Math.random()*100f<50);
        		view.led_safBOTS.update(Math.random()*100f<50);
        		view.led_safSDBCockpit.update(Math.random()*100f<50);
        		view.led_safSDBLeft.update(Math.random()*100f<50);
        		view.led_safSDBRight.update(Math.random()*100f<50);
        		
        		view.driverInputDataPane.set_throttleProgressBar(Math.random()*1f);
        		view.driverInputDataPane.set_brakeProgressBar(Math.random()*1f);
        	
        		view.gg_linearSpeed.setValue(Math.random()*130f);
        		
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
	
	
}
