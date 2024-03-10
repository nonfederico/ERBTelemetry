package it.erb.telemetry.controller;


import java.io.File;		
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import it.erb.telemetry.model.Model;
import it.erb.telemetry.model.TelemetryData;
import it.erb.telemetry.view.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller 
{
	
	List<TelemetryData> historyDataSet;
	
	
	public Controller(Model model, View view, Stage stage)
	{
		// UI periodic update
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200),
				
			e ->
			{
				view.dataReal.gg_HVAcc.setValue(model.latestData.HVAcc_SoC.getValue());
        		view.dataReal.lbl_HVAccVoltage.update(model.latestData.HVAcc_Voltage.getValue(), model.latestData.HVAcc_Voltage.getUnit());
        		view.dataReal.lbl_HVAccCurrent.update(model.latestData.HVAcc_Current.getValue(), model.latestData.HVAcc_Current.getUnit());
        		view.dataReal.lbl_HVAccPower.update(model.latestData.HVAcc_Voltage.getValue()*model.latestData.HVAcc_Current.getValue(), "W");
        		view.dataReal.lbl_HVAccTemp.update(model.latestData.HVAcc_Temp.getValue(), model.latestData.HVAcc_Temp.getUnit());
        		
        		view.dataReal.gg_LVAcc.setValue(model.latestData.LVAcc_SoC.getValue());	
        		view.dataReal.lbl_LVAcc_Voltage.update(model.latestData.LVAcc_Voltage.getValue(), model.latestData.LVAcc_Voltage.getUnit());
        		view.dataReal.lbl_LVAcc_Current.update(model.latestData.LVAcc_Current.getValue(), model.latestData.LVAcc_Current.getUnit());
        		
        		view.dataReal.lbl_inv_Temperature.update(model.latestData.inv_Temperature.getValue(), model.latestData.inv_Temperature.getUnit());
        		view.dataReal.lbl_inv_HVVoltage.update(model.latestData.inv_HVVoltage.getValue(), model.latestData.inv_HVVoltage.getUnit());
        		view.dataReal.lbl_inv_LVVoltage.update(model.latestData.inv_LVVoltage.getValue(), model.latestData.inv_LVVoltage.getUnit());
        		
        		view.dataReal.lbl_motRLActSpeed.update(model.latestData.motorRL_ActSpeed.getValue(), model.latestData.motorRL_ActSpeed.getUnit());
        		view.dataReal.lbl_motRRActSpeed.update(model.latestData.motorRR_ActSpeed.getValue(), model.latestData.motorRR_ActSpeed.getUnit());
        		view.dataReal.lbl_motRLCmdSpeed.update(model.latestData.motorRL_CmdSpeed.getValue(), model.latestData.motorRL_CmdSpeed.getUnit());
        		view.dataReal.lbl_motRRCmdSpeed.update(model.latestData.motorRR_CmdSpeed.getValue(), model.latestData.motorRR_CmdSpeed.getUnit());
        		view.dataReal.lbl_motRLActTorque.update(model.latestData.motorRL_ActTorque.getValue(), model.latestData.motorRL_ActTorque.getUnit());
        		view.dataReal.lbl_motRRActTorque.update(model.latestData.motorRR_ActTorque.getValue(), model.latestData.motorRR_ActTorque.getUnit());
        		view.dataReal.lbl_motRLCmdTorque.update(model.latestData.motorRL_CmdTorque.getValue(), model.latestData.motorRL_CmdTorque.getUnit());
        		view.dataReal.lbl_motRRCmdTorque.update(model.latestData.motorRR_CmdTorque.getValue(), model.latestData.motorRR_CmdTorque.getUnit());
        		view.dataReal.lbl_motRLActCurrent.update(model.latestData.motorRL_ActCurrent.getValue(), model.latestData.motorRL_ActCurrent.getUnit());
        		view.dataReal.lbl_motRRActCurrent.update(model.latestData.motorRR_ActCurrent.getValue(), model.latestData.motorRR_ActCurrent.getUnit());
        		view.dataReal.lbl_motRLActFrequency.update(model.latestData.motorRL_Frequency.getValue(), model.latestData.motorRL_Frequency.getUnit());
        		view.dataReal.lbl_motRRActFrequency.update(model.latestData.motorRR_Frequency.getValue(), model.latestData.motorRR_Frequency.getUnit());
        		view.dataReal.lbl_motRLTemperature.update(model.latestData.motorRL_Temperature.getValue(), model.latestData.motorRL_Temperature.getUnit());
        		view.dataReal.lbl_motRRTemperature.update(model.latestData.motorRR_Temperature.getValue(), model.latestData.motorRR_Temperature.getUnit());
        		
        		view.dataReal.led_safBSPD.update(model.latestData.saf_BSPD.getValue());
        		view.dataReal.led_safIMD.update(model.latestData.saf_IMD.getValue());
        		view.dataReal.led_safLVMS.update(model.latestData.saf_LVMS.getValue());
        		view.dataReal.led_safAMS.update(model.latestData.saf_AMS.getValue());
        		view.dataReal.led_safIS.update(model.latestData.saf_IS.getValue());
        		view.dataReal.led_safBOTS.update(model.latestData.saf_BOTS.getValue());
        		view.dataReal.led_safSDBCockpit.update(model.latestData.saf_SDBCockpit.getValue());
        		view.dataReal.led_safSDBLeft.update(model.latestData.saf_SDBLeft.getValue());
        		view.dataReal.led_safSDBRight.update(model.latestData.saf_SDBRight.getValue());
        		
        		view.dataReal.driverInputDataPane.set_throttleProgressBar(model.latestData.throttlePedal_Pos.getValue()/100f);
        		view.dataReal.driverInputDataPane.set_brakeProgressBar(model.latestData.brakePedal_Pos.getValue()/100f);
        		view.dataReal.driverInputDataPane.set_throttleLabel(model.latestData.throttlePedal_Pos.getValue());
        		view.dataReal.driverInputDataPane.set_brakeLabel(model.latestData.brakePedal_Pos.getValue());
        		
        		view.dataReal.gg_linearSpeed.setValue(model.latestData.vehicle_linearSpeed.getValue());
        		
        		
			}));
		   
		timeline.setCycleCount(Animation.INDEFINITE); // loop forever
		timeline.play();
		
		view.chBox_comPort.setItems(model.getSerialPortName());
			
		view.chBox_comPort.setOnAction((event) -> {
			int selectedIndex = view.chBox_comPort.getSelectionModel().getSelectedIndex();
			model.setSelectedComPortIndex(selectedIndex);
		});
		
		view.chBox_comPort.addEventHandler(ChoiceBox.ON_SHOWING, event -> model.loadSerialPorts());
		
		view.btn_comConnect.setOnAction(event -> model.startListening() );
		
		view.btn_comDisconnect.setOnAction(event -> model.stopListening());
		
		view.vBoxTable.getBtn_tableLoad().setOnAction(event -> {
			
			LocalDate sDate = view.getStartDate().getValue();
			LocalDate eDate = view.getEndDate().getValue();
			
			LocalDateTime sDateTime = LocalDateTime.of(sDate, LocalTime.MIDNIGHT);
			LocalDateTime eDateTime = LocalDateTime.of(eDate, LocalTime.MIDNIGHT);
			
			System.out.println("Query date range: " + sDateTime + " " + eDateTime);
			
			// Query the database
			historyDataSet = model.retrieveDataFromDB(sDateTime, eDateTime);
			
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




