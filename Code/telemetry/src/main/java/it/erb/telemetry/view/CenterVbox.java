package it.erb.telemetry.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import it.erb.telemetry.model.TelemetryData;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class CenterVbox {

	private VBox tableHistory;
	private HBox table_ctrlBox;

	private TableView<TelemetryData> tableView;

	private Button btn_tableLoad;
	private Button btn_tableCsvExport;

	private DatePicker dp_tableStartDate;
	private DatePicker dp_tableEndDate;
	private Pane spacer;

	

public CenterVbox() {
		spacer = new Pane();
		tableHistory = new VBox();
		table_ctrlBox = new HBox();
		dp_tableStartDate = new DatePicker(LocalDate.now());
		dp_tableEndDate = new DatePicker(LocalDate.now().plusDays(1));
		btn_tableLoad = new Button("Load");
		btn_tableCsvExport = new Button("CSV Export");
		spacer.setMinSize(10, 1);
}


public TableView<TelemetryData> tableGroupReturn(TableView tableView) {

tableView.setPlaceholder(new Label("No rows to display"));

TableColumn<TelemetryData,LocalDateTime> col1 = new TableColumn<>("Time");
TableColumn<TelemetryData,String> col2 = new TableColumn<>("Throttle Pedal Position");
TableColumn<TelemetryData,String> col3 = new TableColumn<>("Brake Pedal Position");
TableColumn<TelemetryData,String> col4 = new TableColumn<>("Steering Wheel Position");
TableColumn<TelemetryData,String> col5 = new TableColumn<>("HV Acc. voltage");
TableColumn<TelemetryData,String> col6 = new TableColumn<>("HV Acc. SoC");
TableColumn<TelemetryData,String> col7 = new TableColumn<>("HV Acc. current");
TableColumn<TelemetryData,String> col8 = new TableColumn<>("HV Acc. temperature");
TableColumn<TelemetryData,String> col9 = new TableColumn<>("HV Acc. cell 1");
TableColumn<TelemetryData,String> col10 = new TableColumn<>("HV Acc. cell 2");
TableColumn<TelemetryData,String> col11 = new TableColumn<>("HV Acc. cell 3");
TableColumn<TelemetryData,String> col12 = new TableColumn<>("HV Acc. cell 4");
TableColumn<TelemetryData,String> col13 = new TableColumn<>("LV Acc. voltage");
TableColumn<TelemetryData,String> col14 = new TableColumn<>("LV Acc. SoC");
TableColumn<TelemetryData,String> col15 = new TableColumn<>("LV Acc. current");
TableColumn<TelemetryData,String> col16 = new TableColumn<>("Inverter temperature");
TableColumn<TelemetryData,String> col17 = new TableColumn<>("Inverter HVDC voltage");
TableColumn<TelemetryData,String> col18 = new TableColumn<>("Inverter LVDC voltage");
TableColumn<TelemetryData,String> col19 = new TableColumn<>("MotorRR actual current");
TableColumn<TelemetryData,String> col20 = new TableColumn<>("MotorRR actual speed");
TableColumn<TelemetryData,String> col21 = new TableColumn<>("MotorRR actual torque");
TableColumn<TelemetryData,String> col22 = new TableColumn<>("MotorRR command speed");
TableColumn<TelemetryData,String> col23 = new TableColumn<>("MotorRR command torque");
TableColumn<TelemetryData,String> col24 = new TableColumn<>("MotorRR frequency");
TableColumn<TelemetryData,String> col25 = new TableColumn<>("MotorRR temperature");
TableColumn<TelemetryData,String> col26 = new TableColumn<>("MotorRL actual current");
TableColumn<TelemetryData,String> col27 = new TableColumn<>("MotorRL actual speed");
TableColumn<TelemetryData,String> col28 = new TableColumn<>("MotorRL actual torque");
TableColumn<TelemetryData,String> col29 = new TableColumn<>("MotorRL command speed");
TableColumn<TelemetryData,String> col30 = new TableColumn<>("MotorRL command torque");
TableColumn<TelemetryData,String> col31 = new TableColumn<>("MotorRL frequency");
TableColumn<TelemetryData,String> col32 = new TableColumn<>("MotorRL temperature");
TableColumn<TelemetryData,String> col33 = new TableColumn<>("Linear speed");
TableColumn<TelemetryData,Boolean> col34 = new TableColumn<>("SDC BSPD");
TableColumn<TelemetryData,Boolean> col35 = new TableColumn<>("SDC IMD");
TableColumn<TelemetryData,Boolean> col36 = new TableColumn<>("SDC LVMS");
TableColumn<TelemetryData,Boolean> col37 = new TableColumn<>("SDC AMS");
TableColumn<TelemetryData,Boolean> col38 = new TableColumn<>("SDC IS");
TableColumn<TelemetryData,Boolean> col39 = new TableColumn<>("SDC BOTS");
TableColumn<TelemetryData,Boolean> col40 = new TableColumn<>("SDC SDB cockpit");
TableColumn<TelemetryData,Boolean> col41 = new TableColumn<>("SDC SDB left");
TableColumn<TelemetryData,Boolean> col42 = new TableColumn<>("SDC SDB right");



col1.setCellValueFactory(data -> data.getValue().date);
col2.setCellValueFactory(data -> data.getValue().throttlePedal_Pos.getProperty());
col3.setCellValueFactory(data -> data.getValue().brakePedal_Pos.getProperty());
col4.setCellValueFactory(data -> data.getValue().steeringWheel_Pos.getProperty());
col5.setCellValueFactory(data -> data.getValue().HVAcc_Voltage.getProperty());
col6.setCellValueFactory(data -> data.getValue().HVAcc_SoC.getProperty());
col7.setCellValueFactory(data -> data.getValue().HVAcc_Current.getProperty());
col8.setCellValueFactory(data -> data.getValue().HVAcc_Temp.getProperty());
col9.setCellValueFactory(data -> data.getValue().HVAcc_Cell_1_Temp.getProperty());
col10.setCellValueFactory(data -> data.getValue().HVAcc_Cell_2_Temp.getProperty());
col11.setCellValueFactory(data -> data.getValue().HVAcc_Cell_3_Temp.getProperty());
col12.setCellValueFactory(data -> data.getValue().HVAcc_Cell_4_Temp.getProperty());
col13.setCellValueFactory(data -> data.getValue().LVAcc_Voltage.getProperty());
col14.setCellValueFactory(data -> data.getValue().LVAcc_SoC.getProperty());
col15.setCellValueFactory(data -> data.getValue().LVAcc_Current.getProperty());
col16.setCellValueFactory(data -> data.getValue().inv_Temperature.getProperty());
col17.setCellValueFactory(data -> data.getValue().inv_HVVoltage.getProperty());
col18.setCellValueFactory(data -> data.getValue().inv_LVVoltage.getProperty());
col19.setCellValueFactory(data -> data.getValue().motorRR_ActCurrent.getProperty());
col20.setCellValueFactory(data -> data.getValue().motorRR_ActSpeed.getProperty());
col21.setCellValueFactory(data -> data.getValue().motorRR_ActTorque.getProperty());
col22.setCellValueFactory(data -> data.getValue().motorRR_CmdSpeed.getProperty());
col23.setCellValueFactory(data -> data.getValue().motorRR_CmdTorque.getProperty());
col24.setCellValueFactory(data -> data.getValue().motorRR_Frequency.getProperty());
col25.setCellValueFactory(data -> data.getValue().motorRR_Temperature.getProperty());
col26.setCellValueFactory(data -> data.getValue().motorRL_ActCurrent.getProperty());
col27.setCellValueFactory(data -> data.getValue().motorRL_ActSpeed.getProperty());
col28.setCellValueFactory(data -> data.getValue().motorRL_ActTorque.getProperty());
col29.setCellValueFactory(data -> data.getValue().motorRL_CmdSpeed.getProperty());
col30.setCellValueFactory(data -> data.getValue().motorRL_CmdTorque.getProperty());
col31.setCellValueFactory(data -> data.getValue().motorRL_Frequency.getProperty());
col32.setCellValueFactory(data -> data.getValue().motorRL_Temperature.getProperty());
col33.setCellValueFactory(data -> data.getValue().vehicle_linearSpeed.getProperty());
col34.setCellValueFactory(data -> data.getValue().saf_BSPD.getProperty());
col35.setCellValueFactory(data -> data.getValue().saf_IMD.getProperty());
col36.setCellValueFactory(data -> data.getValue().saf_LVMS.getProperty());
col37.setCellValueFactory(data -> data.getValue().saf_AMS.getProperty());
col38.setCellValueFactory(data -> data.getValue().saf_IS.getProperty());
col39.setCellValueFactory(data -> data.getValue().saf_BOTS.getProperty());
col40.setCellValueFactory(data -> data.getValue().saf_SDBCockpit.getProperty());
col41.setCellValueFactory(data -> data.getValue().saf_SDBLeft.getProperty());
col42.setCellValueFactory(data -> data.getValue().saf_SDBRight.getProperty());

tableView.getColumns().add(col1);
tableView.getColumns().add(col2);
tableView.getColumns().add(col3);
tableView.getColumns().add(col4);
tableView.getColumns().add(col5);
tableView.getColumns().add(col6);
tableView.getColumns().add(col7);
tableView.getColumns().add(col8);
tableView.getColumns().add(col9);
tableView.getColumns().add(col10);
tableView.getColumns().add(col11);
tableView.getColumns().add(col12);
tableView.getColumns().add(col13);
tableView.getColumns().add(col14);
tableView.getColumns().add(col15);
tableView.getColumns().add(col16);
tableView.getColumns().add(col17);
tableView.getColumns().add(col18);
tableView.getColumns().add(col19);
tableView.getColumns().add(col20);
tableView.getColumns().add(col21);
tableView.getColumns().add(col22);
tableView.getColumns().add(col23);
tableView.getColumns().add(col24);
tableView.getColumns().add(col25);
tableView.getColumns().add(col26);
tableView.getColumns().add(col27);
tableView.getColumns().add(col28);
tableView.getColumns().add(col29);
tableView.getColumns().add(col30);
tableView.getColumns().add(col31);
tableView.getColumns().add(col32);
tableView.getColumns().add(col33);
tableView.getColumns().add(col34);
tableView.getColumns().add(col35);
tableView.getColumns().add(col36);
tableView.getColumns().add(col37);
tableView.getColumns().add(col38);
tableView.getColumns().add(col39);
tableView.getColumns().add(col40);
tableView.getColumns().add(col41);
tableView.getColumns().add(col42);

tableView.setItems(FXCollections.observableArrayList());
return tableView;
}

public VBox getVbox() {
	
	table_ctrlBox.setSpacing(10);
	table_ctrlBox.setHgrow(spacer, Priority.ALWAYS);
	table_ctrlBox.setPadding(new Insets(0,0,5,0));
	table_ctrlBox.getChildren().add(dp_tableStartDate);
	table_ctrlBox.getChildren().add(dp_tableEndDate);
	table_ctrlBox.getChildren().add(btn_tableLoad);
	table_ctrlBox.getChildren().add(spacer);
	table_ctrlBox.getChildren().add(btn_tableCsvExport);
	
	tableView = new TableView<>(); //guarda classe TableColumnGroup per inserimento dati tabella
	
	tableHistory.getChildren().add(table_ctrlBox);
	tableHistory.getChildren().add(tableGroupReturn(tableView));
	tableHistory.setPadding(new Insets(10,5,10,5));
	tableView.prefHeightProperty().bind(tableHistory.heightProperty());
	
	
	return tableHistory;
}



public TableView getTableview() {
	return tableView;
}

public DatePicker  getStartDate() {
	return dp_tableStartDate;
}

public DatePicker  getEndDate() {
	return dp_tableEndDate;
}

public Button getBtn_tableLoad(){
	return btn_tableLoad;
}

public Button getBtn_tableCsvExport() {
    return btn_tableCsvExport;
}



}
