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
				view.gg_HVAcc.setValue(model.latestData.HVAcc_SoC.getValue());
        		view.lbl_HVAccVoltage.update(model.latestData.HVAcc_Voltage.getValue(), model.latestData.HVAcc_Voltage.getUnit());
        		view.lbl_HVAccCurrent.update(model.latestData.HVAcc_Current.getValue(), model.latestData.HVAcc_Current.getUnit());
        		view.lbl_HVAccPower.update(model.latestData.HVAcc_Voltage.getValue()*model.latestData.HVAcc_Current.getValue(), "W");
        		view.lbl_HVAccTemp.update(model.latestData.HVAcc_Temp.getValue(), model.latestData.HVAcc_Temp.getUnit());
        		
        		view.gg_LVAcc.setValue(model.latestData.LVAcc_SoC.getValue());	
        		view.lbl_LVAcc_Voltage.update(model.latestData.LVAcc_Voltage.getValue(), model.latestData.LVAcc_Voltage.getUnit());
        		view.lbl_LVAcc_Current.update(model.latestData.LVAcc_Current.getValue(), model.latestData.LVAcc_Current.getUnit());
        		
        		view.lbl_inv_Temperature.update(model.latestData.inv_Temperature.getValue(), model.latestData.inv_Temperature.getUnit());
        		view.lbl_inv_HVVoltage.update(model.latestData.inv_HVVoltage.getValue(), model.latestData.inv_HVVoltage.getUnit());
        		view.lbl_inv_LVVoltage.update(model.latestData.inv_LVVoltage.getValue(), model.latestData.inv_LVVoltage.getUnit());
        		
        		view.lbl_motRLActSpeed.update(model.latestData.MotorRL_ActSpeed.getValue(), model.latestData.MotorRL_ActSpeed.getUnit());
        		view.lbl_motRRActSpeed.update(model.latestData.MotorRR_ActSpeed.getValue(), model.latestData.MotorRR_ActSpeed.getUnit());
        		view.lbl_motRLCmdSpeed.update(model.latestData.MotorRL_CmdSpeed.getValue(), model.latestData.MotorRL_CmdSpeed.getUnit());
        		view.lbl_motRRCmdSpeed.update(model.latestData.MotorRR_CmdSpeed.getValue(), model.latestData.MotorRR_CmdSpeed.getUnit());
        		view.lbl_motRLActTorque.update(model.latestData.MotorRL_ActTorque.getValue(), model.latestData.MotorRL_ActTorque.getUnit());
        		view.lbl_motRRActTorque.update(model.latestData.MotorRR_ActTorque.getValue(), model.latestData.MotorRR_ActTorque.getUnit());
        		view.lbl_motRLCmdTorque.update(model.latestData.MotorRL_CmdTorque.getValue(), model.latestData.MotorRL_CmdTorque.getUnit());
        		view.lbl_motRRCmdTorque.update(model.latestData.MotorRR_CmdTorque.getValue(), model.latestData.MotorRR_CmdTorque.getUnit());
        		view.lbl_motRLActCurrent.update(model.latestData.MotorRL_ActCurrent.getValue(), model.latestData.MotorRL_ActCurrent.getUnit());
        		view.lbl_motRRActCurrent.update(model.latestData.MotorRR_ActCurrent.getValue(), model.latestData.MotorRR_ActCurrent.getUnit());
        		view.lbl_motRLActFrequency.update(model.latestData.MotorRL_Frequency.getValue(), model.latestData.MotorRL_Frequency.getUnit());
        		view.lbl_motRRActFrequency.update(model.latestData.MotorRR_Frequency.getValue(), model.latestData.MotorRR_Frequency.getUnit());
        		view.lbl_motRLTemperature.update(model.latestData.MotorRL_Temperature.getValue(), model.latestData.MotorRL_Temperature.getUnit());
        		view.lbl_motRRTemperature.update(model.latestData.MotorRR_Temperature.getValue(), model.latestData.MotorRR_Temperature.getUnit());
        		
        		view.led_safBSPD.update(model.latestData.saf_BSPD.getValue());
        		view.led_safIMD.update(model.latestData.saf_IMD.getValue());
        		view.led_safLVMS.update(model.latestData.saf_LVMS.getValue());
        		view.led_safAMS.update(model.latestData.saf_AMS.getValue());
        		view.led_safIS.update(model.latestData.saf_IS.getValue());
        		view.led_safBOTS.update(model.latestData.saf_BOTS.getValue());
        		view.led_safSDBCockpit.update(model.latestData.saf_SDBCockpit.getValue());
        		view.led_safSDBLeft.update(model.latestData.saf_SDBLeft.getValue());
        		view.led_safSDBRight.update(model.latestData.saf_SDBRght.getValue());
        		
        		view.driverInputDataPane.set_throttleProgressBar(model.latestData.ThrottlePedal_Pos.getValue()/100f);
        		view.driverInputDataPane.set_brakeProgressBar(model.latestData.BrakePedal_Pos.getValue()/100f);
        		view.driverInputDataPane.set_throttleLabel(model.latestData.ThrottlePedal_Pos.getValue());
        		view.driverInputDataPane.set_brakeLabel(model.latestData.BrakePedal_Pos.getValue());
        		
        		view.gg_linearSpeed.setValue(model.latestData.vehicle_linearSpeed.getValue());
        			
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
			
			//view.tableView.getItems().add(td);
			ObservableList<TelemetryData> items = FXCollections.<TelemetryData>observableArrayList();
			
			items.add(td);
			items.add(td);
			items.add(td);
			
			
			view.tableView.setItems(items);
		});
		
		
	}
	
	
}
