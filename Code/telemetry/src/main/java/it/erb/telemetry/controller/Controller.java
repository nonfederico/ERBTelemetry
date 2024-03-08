package it.erb.telemetry.controller;


import java.io.File;	
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import it.erb.telemetry.model.Model;
import it.erb.telemetry.model.TelemetryData;
import it.erb.telemetry.model.sensor.AnalogSensor;
import it.erb.telemetry.view.Tab1;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.FXPermission;

public class Controller 
{
	
	List<TelemetryData> historyDataSet;
	private Tab1 vBox= new Tab1();
	
	public Controller(Model model, View view, Stage stage)
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
        		
        		view.lbl_motRLActSpeed.update(model.latestData.motorRL_ActSpeed.getValue(), model.latestData.motorRL_ActSpeed.getUnit());
        		view.lbl_motRRActSpeed.update(model.latestData.motorRR_ActSpeed.getValue(), model.latestData.motorRR_ActSpeed.getUnit());
        		view.lbl_motRLCmdSpeed.update(model.latestData.motorRL_CmdSpeed.getValue(), model.latestData.motorRL_CmdSpeed.getUnit());
        		view.lbl_motRRCmdSpeed.update(model.latestData.motorRR_CmdSpeed.getValue(), model.latestData.motorRR_CmdSpeed.getUnit());
        		view.lbl_motRLActTorque.update(model.latestData.motorRL_ActTorque.getValue(), model.latestData.motorRL_ActTorque.getUnit());
        		view.lbl_motRRActTorque.update(model.latestData.motorRR_ActTorque.getValue(), model.latestData.motorRR_ActTorque.getUnit());
        		view.lbl_motRLCmdTorque.update(model.latestData.motorRL_CmdTorque.getValue(), model.latestData.motorRL_CmdTorque.getUnit());
        		view.lbl_motRRCmdTorque.update(model.latestData.motorRR_CmdTorque.getValue(), model.latestData.motorRR_CmdTorque.getUnit());
        		view.lbl_motRLActCurrent.update(model.latestData.motorRL_ActCurrent.getValue(), model.latestData.motorRL_ActCurrent.getUnit());
        		view.lbl_motRRActCurrent.update(model.latestData.motorRR_ActCurrent.getValue(), model.latestData.motorRR_ActCurrent.getUnit());
        		view.lbl_motRLActFrequency.update(model.latestData.motorRL_Frequency.getValue(), model.latestData.motorRL_Frequency.getUnit());
        		view.lbl_motRRActFrequency.update(model.latestData.motorRR_Frequency.getValue(), model.latestData.motorRR_Frequency.getUnit());
        		view.lbl_motRLTemperature.update(model.latestData.motorRL_Temperature.getValue(), model.latestData.motorRL_Temperature.getUnit());
        		view.lbl_motRRTemperature.update(model.latestData.motorRR_Temperature.getValue(), model.latestData.motorRR_Temperature.getUnit());
        		
        		view.led_safBSPD.update(model.latestData.saf_BSPD.getValue());
        		view.led_safIMD.update(model.latestData.saf_IMD.getValue());
        		view.led_safLVMS.update(model.latestData.saf_LVMS.getValue());
        		view.led_safAMS.update(model.latestData.saf_AMS.getValue());
        		view.led_safIS.update(model.latestData.saf_IS.getValue());
        		view.led_safBOTS.update(model.latestData.saf_BOTS.getValue());
        		view.led_safSDBCockpit.update(model.latestData.saf_SDBCockpit.getValue());
        		view.led_safSDBLeft.update(model.latestData.saf_SDBLeft.getValue());
        		view.led_safSDBRight.update(model.latestData.saf_SDBRight.getValue());
        		
        		view.driverInputDataPane.set_throttleProgressBar(model.latestData.throttlePedal_Pos.getValue()/100f);
        		view.driverInputDataPane.set_brakeProgressBar(model.latestData.brakePedal_Pos.getValue()/100f);
        		view.driverInputDataPane.set_throttleLabel(model.latestData.throttlePedal_Pos.getValue());
        		view.driverInputDataPane.set_brakeLabel(model.latestData.brakePedal_Pos.getValue());
        		
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
		
		view.btn_comDisconnect.setOnAction(event -> model.stopListening());
		
		view.vBoxTable.getBtn_tableLoad().setOnAction(event -> {
			
			/*cosi non funziona
			LocalDate sDate = view.vBoxTable.getStartDate().getValue();
			LocalDate eDate = view.vBoxTable.getEndDate().getValue();
			*/
			
			LocalDate sDate = view.getStartDate().getValue();
			LocalDate eDate = view.getEndDate().getValue();
			
			LocalDateTime sDateTime = LocalDateTime.of(sDate, LocalTime.MIDNIGHT);
			LocalDateTime eDateTime = LocalDateTime.of(eDate, LocalTime.MIDNIGHT);
			
			System.out.println("Query date range: " + sDateTime + " " + eDateTime);
			
			// Query the database
			historyDataSet = model.retrieveDataFromDB(sDateTime, eDateTime);
			
			//ObservableList<TelemetryData> tableItems = view.tableView.getItems();
			ObservableList<TelemetryData> tableItems = view.vBoxTable.getTableview().getItems(); 
			
			tableItems.clear();
			tableItems.addAll(historyDataSet);	
			
		});
		
		view.vBoxTable.getBtn_tableCsvExport().setOnAction(event -> saveHistorySetToFile(stage) );
		
		
		
		
	}
	
	void saveHistorySetToFile(Stage primaryStage) //creates text file with points
	{
	    FileChooser fileChooser = new FileChooser();

	    //Set extension filter for text files
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
	    fileChooser.getExtensionFilters().add(extFilter);

	    //Show save file dialog
	    File file = fileChooser.showSaveDialog(primaryStage);
	    if (file != null) 
	    {
	        try 
	        {
	            PrintWriter writer;
	            writer = new PrintWriter(file);
	            writer.println(TelemetryData.namesToString());
	            if(historyDataSet != null )
	            {
	            	for(TelemetryData td : historyDataSet)
	            	{
	            		writer.println(td.toString());
	            	}
	            }
	            writer.close();
	        } 
	        catch (IOException ex) 
	        {
	            System.out.println("Exception while exporting csv file");;
	        }
	    }

	}
	
}




