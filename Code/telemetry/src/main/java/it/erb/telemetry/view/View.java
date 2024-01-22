package it.erb.telemetry.view;

import java.time.LocalDate;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Gauge.SkinType;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.Section;
import it.erb.telemetry.model.TelemetryData;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class View 
{
	public Scene scene;
	
	public ChoiceBox<String> chBox_comPort;
	
	public Button btn_comConnect;
	public Button btn_comDisconnect;
	public Button btn_tableLoad;
	public Button btn_tableCsvExport;
	
	public DataLabel lbl_HVAccVoltage;
	public DataLabel lbl_HVAccCurrent;
	public DataLabel lbl_HVAccPower;
	public DataLabel lbl_HVAccTemp;
	
	public DataLabel lbl_LVAcc_Voltage;
	public DataLabel lbl_LVAcc_Current;
	
	public DataLabel lbl_inv_Temperature;
	public DataLabel lbl_inv_HVVoltage;
	public DataLabel lbl_inv_LVVoltage;
	
	public DataLabel lbl_motRLActSpeed;
	public DataLabel lbl_motRRActSpeed;
	public DataLabel lbl_motRLCmdSpeed;
	public DataLabel lbl_motRRCmdSpeed;
	public DataLabel lbl_motRLActTorque;
	public DataLabel lbl_motRRActTorque;
	public DataLabel lbl_motRLCmdTorque;
	public DataLabel lbl_motRRCmdTorque;
	public DataLabel lbl_motRLActCurrent;
	public DataLabel lbl_motRRActCurrent;
	public DataLabel lbl_motRLActFrequency;
	public DataLabel lbl_motRRActFrequency;
	public DataLabel lbl_motRLTemperature;
	public DataLabel lbl_motRRTemperature;
	
	
	public Label lbl_comStatus;
	
	public Circle circle_comStatus;
	
	public Led led_safBSPD;
	public Led led_safIMD;
	public Led led_safLVMS;
	public Led led_safAMS;
	public Led led_safIS;
	public Led led_safBOTS;
	public Led led_safSDBCockpit;
	public Led led_safSDBLeft;
	public Led led_safSDBRight;
	
	public DatePicker dp_tableStartDate;
	public DatePicker dp_tableEndDate;
	
	public TableView<TelemetryData> tableView;
	
	public Gauge gg_HVAcc;
	public Gauge gg_LVAcc;
	public Gauge gg_linearSpeed;
	
	public DriverInputDataPane driverInputDataPane;
	
	
	public View()
	{
		
		btn_comConnect = new Button();
		btn_comConnect.setText("Connect");
		
		btn_comDisconnect = new Button();
		btn_comDisconnect.setText("Disconnect");
				
		chBox_comPort = new ChoiceBox<>();
		chBox_comPort.setPrefWidth(100);
		
		circle_comStatus = new Circle(5);
		
		lbl_comStatus = new Label("Not connected");
		
		
		// TOOL BAR COM port
		ToolBar tlb_com = new ToolBar();
		tlb_com.getItems().add(chBox_comPort);
		tlb_com.getItems().add(btn_comConnect);
		tlb_com.getItems().add(btn_comDisconnect);
		tlb_com.getItems().add(circle_comStatus);
		tlb_com.getItems().add(lbl_comStatus);
		
		
		// CENTER PANE
		// TABLE
		VBox centerPane = new VBox();
		
		// CONTROL BOX
		HBox table_ctrlBox = new HBox();
		
		dp_tableStartDate = new DatePicker(LocalDate.now());
		dp_tableEndDate = new DatePicker(LocalDate.now().plusDays(1));
		btn_tableLoad = new Button("Load");
		btn_tableCsvExport = new Button("CSV Export");
		
		table_ctrlBox.getChildren().add(dp_tableStartDate);
		table_ctrlBox.getChildren().add(dp_tableEndDate);
		table_ctrlBox.getChildren().add(btn_tableLoad);
		table_ctrlBox.getChildren().add(btn_tableCsvExport);
		
		// TABLE
		tableView = new TableView<>();
		//tableView.setStyle(style_tableView);
		tableView.setPlaceholder( new Label("No rows to display"));
		TableColumn<TelemetryData,String> col1 = new TableColumn<>("Throttle Pedal Position");
		TableColumn<TelemetryData,String> col2 = new TableColumn<>("Brake Pedal Position");
		TableColumn<TelemetryData,String> col3 = new TableColumn<>("Steering Wheel Position");
		TableColumn<TelemetryData,String> col4 = new TableColumn<>("Accumulator Voltage");
		TableColumn<TelemetryData,String> col5 = new TableColumn<>("Accumulator Current");
		TableColumn<TelemetryData,String> col6 = new TableColumn<>("Accumulator Temperature");
		
		col1.setCellValueFactory(data -> data.getValue().ThrottlePedal_Pos.getProperty());
		col2.setCellValueFactory(data -> data.getValue().BrakePedal_Pos.getProperty());
		col3.setCellValueFactory(data -> data.getValue().SteeringWheel_Pos.getProperty());
		col4.setCellValueFactory(data -> data.getValue().HVAcc_Voltage.getProperty());
		col5.setCellValueFactory(data -> data.getValue().HVAcc_Current.getProperty());
		col6.setCellValueFactory(data -> data.getValue().HVAcc_Temp.getProperty());
		
		tableView.getColumns().add(col1);
		tableView.getColumns().add(col2);
		tableView.getColumns().add(col3);
		tableView.getColumns().add(col4);
		tableView.getColumns().add(col5);
		tableView.getColumns().add(col6);
		
		centerPane.getChildren().add(table_ctrlBox);
		centerPane.getChildren().add(tableView);
		centerPane.setPadding(new Insets(20));
		
		// HV accumulator data
		// TITLE + GRID PANE
		VBox vb_HVAcc = new VBox();
		vb_HVAcc.getStyleClass().add("dataPane");
		
		Label lbl_accTitle = new Label("HV Accumulator");
		lbl_accTitle.getStyleClass().add("titleLabel");
		lbl_HVAccVoltage = new DataLabel("-",1,true);
		lbl_HVAccCurrent = new DataLabel("-",1,true);
		lbl_HVAccPower = new DataLabel("-",0,true);
		lbl_HVAccTemp = new DataLabel("-",1,true);
		
		gg_HVAcc = GaugeBuilder.create()
				.skinType(SkinType.SIMPLE_SECTION)
				.prefSize(90,90)  
				.minValue(0)                                                                     
                .maxValue(100) 
                .title("")
                .barColor(Color.GREEN)
                .valueColor(Color.WHITE)
                .decimals(1)  
                .sectionsAlwaysVisible(true)
                .sectionsVisible(true)
                .highlightSections(true)
                .sections(new Section(0, 30, Color.RED), new Section(30, 50, Color.ORANGE), new Section(50, 100, Color.GREEN))
				.build();
		gg_HVAcc.setStyle("-fx-padding: 10;");
		gg_HVAcc.setValue(90);
		
		// GRIDPANE
		GridPane gridPane_HVAcc = new GridPane();
		gridPane_HVAcc.getStyleClass().add("gridPane");
		gridPane_HVAcc.add(new Label("Voltage"), 0, 0);
		gridPane_HVAcc.add(lbl_HVAccVoltage, 1, 0);
		gridPane_HVAcc.add(new Label("Current"), 0, 1);
		gridPane_HVAcc.add(lbl_HVAccCurrent, 1, 1);
		gridPane_HVAcc.add(new Label("Power"), 0, 2);
		gridPane_HVAcc.add(lbl_HVAccPower, 1, 2);
		gridPane_HVAcc.add(new Label("Temperature"), 0, 3);
		gridPane_HVAcc.add(lbl_HVAccTemp, 1, 3);
		gridPane_HVAcc.setMaxWidth(400);
		gridPane_HVAcc.setMaxHeight(200);
		
		vb_HVAcc.getChildren().add(lbl_accTitle);
		vb_HVAcc.getChildren().add(gg_HVAcc);
		vb_HVAcc.getChildren().add(gridPane_HVAcc);
		
		// LV accumulator data
		// TITLE + GRID PANE
		VBox vb_lvAcc = new VBox();
		vb_lvAcc.getStyleClass().add("dataPane");
		
		Label lbl_LVAccTitle = new Label("LV Accumulator");
		lbl_LVAccTitle.getStyleClass().add("titleLabel");
		lbl_LVAcc_Voltage = new DataLabel("-",1,true);
		lbl_LVAcc_Current = new DataLabel("-",1,true);
				
		gg_LVAcc = GaugeBuilder.create()
				.skinType(SkinType.SIMPLE_SECTION)
				.prefSize(90,90)  
				.minValue(0)                                                                     
                .maxValue(100) 
                .title("")
                .barColor(Color.GREEN)
                .valueColor(Color.WHITE)
                .decimals(1)   
                .build();
		gg_LVAcc.setStyle("-fx-padding: 10; ");
		gg_LVAcc.setValue(90);
			
		// GRIDPANE
		GridPane gridPane_lvAcc = new GridPane();
		gridPane_lvAcc.getStyleClass().add("gridPane");
		gridPane_lvAcc.add(new Label("Voltage"), 0, 0);
		gridPane_lvAcc.add(lbl_LVAcc_Voltage, 1, 0);
		gridPane_lvAcc.add(new Label("Current"), 0, 1);
		gridPane_lvAcc.add(lbl_LVAcc_Current, 1, 1);
		gridPane_lvAcc.setMaxWidth(400);
		gridPane_lvAcc.setMaxHeight(200);
		
		vb_lvAcc.getChildren().add(lbl_LVAccTitle);
		vb_lvAcc.getChildren().add(gg_LVAcc);
		vb_lvAcc.getChildren().add(gridPane_lvAcc);
		
		// INVERTER data
		// TITLE + GRID PANE
		VBox vb_inv = new VBox();
		vb_inv.getStyleClass().add("dataPane");
		
		Label lbl_invTitle = new Label("Inverter");
		lbl_invTitle.getStyleClass().add("titleLabel");
		lbl_inv_Temperature = new DataLabel("-",1,true);
		lbl_inv_HVVoltage = new DataLabel("-",1,true);
		lbl_inv_LVVoltage = new DataLabel("-",1,true);
					
		GridPane gridPane_inv = new GridPane();
		gridPane_inv.getStyleClass().add("gridPane");
		gridPane_inv.add(new Label("Temperature"), 0, 0);
		gridPane_inv.add(lbl_inv_Temperature, 1, 0);
		gridPane_inv.add(new Label("HV Voltage"), 0, 1);
		gridPane_inv.add(lbl_inv_HVVoltage, 1, 1);
		gridPane_inv.add(new Label("LV Voltage"), 0, 2);
		gridPane_inv.add(lbl_inv_LVVoltage, 1, 2);
		gridPane_inv.setMaxWidth(400);
		gridPane_inv.setMaxHeight(200);
		
		vb_inv.getChildren().add(lbl_invTitle);
		vb_inv.getChildren().add(gridPane_inv);

		
		// MOTORS data
		// TITLE + GRID PANE
		VBox vb_mot = new VBox();
		vb_mot.getStyleClass().add("dataPane");
		
		
		Label lbl_motTitle = new Label("Motors");
		lbl_motTitle.getStyleClass().add("titleLabel");
		
		lbl_motRLActSpeed = new DataLabel("-",1,true);
		lbl_motRRActSpeed = new DataLabel("-",1,true);
		lbl_motRLCmdSpeed = new DataLabel("-",1,true);
		lbl_motRRCmdSpeed = new DataLabel("-",1,true);
		lbl_motRLActTorque = new DataLabel("-",0,true);
		lbl_motRRActTorque = new DataLabel("-",0,true);
		lbl_motRLCmdTorque = new DataLabel("-",0,true);
		lbl_motRRCmdTorque = new DataLabel("-",0,true);
		lbl_motRLActCurrent = new DataLabel("-",1,true);
		lbl_motRRActCurrent = new DataLabel("-",1,true);
		lbl_motRLActFrequency = new DataLabel("-",1,true);
		lbl_motRRActFrequency = new DataLabel("-",1,true);
		lbl_motRLTemperature = new DataLabel("-",1,true);
		lbl_motRRTemperature = new DataLabel("-",1,true);
				
		GridPane gridPane_mot = new GridPane();
		gridPane_mot.getStyleClass().add("gridPane");
		gridPane_mot.add(new Label("Act. speed"), 0, 0);
		gridPane_mot.add(lbl_motRLActSpeed, 1, 0);
		gridPane_mot.add(lbl_motRRActSpeed, 2, 0);
		gridPane_mot.add(new Label("Cmd speed"), 0, 1);
		gridPane_mot.add(lbl_motRLCmdSpeed, 1, 1);
		gridPane_mot.add(lbl_motRRCmdSpeed, 2, 1);
		gridPane_mot.add(new Label("Act. torque"), 0, 2);
		gridPane_mot.add(lbl_motRLActTorque, 1, 2);
		gridPane_mot.add(lbl_motRRActTorque, 2, 2);
		gridPane_mot.add(new Label("Cmd torque"), 0, 3);
		gridPane_mot.add(lbl_motRLCmdTorque, 1, 3);
		gridPane_mot.add(lbl_motRRCmdTorque, 2, 3);
		gridPane_mot.add(new Label("Current"), 0, 4);
		gridPane_mot.add(lbl_motRLActCurrent, 1, 4);
		gridPane_mot.add(lbl_motRRActCurrent, 2, 4);
		gridPane_mot.add(new Label("Frequency"), 0, 5);
		gridPane_mot.add(lbl_motRLActFrequency, 1, 5);
		gridPane_mot.add(lbl_motRRActFrequency, 2, 5);
		gridPane_mot.add(new Label("Temperature"), 0, 6);
		gridPane_mot.add(lbl_motRLTemperature, 1, 6);
		gridPane_mot.add(lbl_motRRTemperature, 2, 6);
		gridPane_mot.setMaxWidth(400);
		gridPane_mot.setMaxHeight(200);
		
		vb_mot.getChildren().add(lbl_motTitle);
		vb_mot.getChildren().add(gridPane_mot);		

		// SAFETY CIRCUIT data
		// TITLE + GRID PANE
		VBox vb_saf = new VBox();
		vb_saf.getStyleClass().add("dataPane");
		
		Label lbl_safTitle = new Label("Safety circuit");
		lbl_safTitle.getStyleClass().add("titleLabel");
		
		led_safBSPD = new Led(5);
		led_safIMD = new Led(5);
		led_safLVMS = new Led(5);
		led_safAMS = new Led(5);
		led_safIS = new Led(5);
		led_safBOTS = new Led(5);
		led_safSDBCockpit = new Led(5);
		led_safSDBLeft = new Led(5);
		led_safSDBRight = new Led(5);
		       
		GridPane gridPane_saf = new GridPane();
		gridPane_saf.getStyleClass().add("gridPane");
		gridPane_saf.add(led_safBSPD, 0, 0);
		gridPane_saf.add(new Label("BSPD"), 1, 0);
		gridPane_saf.add(led_safIMD, 0, 1);
		gridPane_saf.add(new Label("IMD"), 1, 1);
		gridPane_saf.add(led_safLVMS, 0, 2);
		gridPane_saf.add(new Label("LVMS"), 1, 2);
		gridPane_saf.add(led_safAMS, 0, 3);		
		gridPane_saf.add(new Label("AMS"), 1, 3);
		gridPane_saf.add(led_safIS, 0, 4);
		gridPane_saf.add(new Label("IS"), 1, 4);
		gridPane_saf.add(led_safBOTS, 0, 5);
		gridPane_saf.add(new Label("BOTS"), 1, 5);
		gridPane_saf.add(led_safSDBCockpit, 0, 6);
		gridPane_saf.add(new Label("SDB Cockpit"), 1, 6);
		gridPane_saf.add(led_safSDBLeft, 0, 7);
		gridPane_saf.add(new Label("SDB Left"), 1, 7);
		gridPane_saf.add(led_safSDBRight, 0, 8);
		gridPane_saf.add(new Label("SDB Right"), 1, 8);
		gridPane_saf.setMaxWidth(400);
		gridPane_saf.setMaxHeight(200);
		
		vb_saf.getChildren().add(lbl_safTitle);
		vb_saf.getChildren().add(gridPane_saf);
		
		// DRIVER INPUT DATA PANE
		driverInputDataPane = new DriverInputDataPane();
		
		// SPEED data
		VBox vb_speed = new VBox();
		vb_speed.getStyleClass().add("dataPane");
		
		Label lbl_vehicleTitle = new Label("Speed");
		lbl_vehicleTitle.getStyleClass().add("titleLabel");
			
		gg_linearSpeed = GaugeBuilder.create()
				.skinType(SkinType.SPACE_X)
				.prefSize(150,150)  
				.unit("km/h")
				.maxValue(200)
				.threshold(150)
				.build();
		vb_speed.getChildren().addAll(lbl_vehicleTitle, gg_linearSpeed);
		
		// BOTTOM PANE
		// HV ACCUMULATOR + INVERTER + MOTORS + TYRES + SAFETY CIRCUIT + DRIVING
		HBox bottomPane = new HBox();
		bottomPane.getStyleClass().add("bottomPane");
		bottomPane.getChildren().add(vb_HVAcc);
		bottomPane.getChildren().add(vb_lvAcc);
		bottomPane.getChildren().add(vb_inv);
		bottomPane.getChildren().add(vb_mot);
		bottomPane.getChildren().add(vb_saf);
		bottomPane.getChildren().add(driverInputDataPane.getPane());
		bottomPane.getChildren().add(vb_speed);
		
		// MAIN PANE
		BorderPane bPane = new BorderPane();
		bPane.getStyleClass().add("borderPane");
		bPane.setTop(tlb_com);
		bPane.setCenter(centerPane);
		bPane.setBottom(bottomPane);
		
		LinearGradient paint = new LinearGradient(
				0.0, 0.0038, 0.0, 1.0, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(0.2316, 0.2316, 0.2316, 1.0)),
				new Stop(1.0, new Color(0.1053, 0.1053, 0.1053, 1.0)));
		bPane.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
		
		// SCENE
		scene = new Scene(bPane, 1920, 900);
		scene.setFill(Color.BLACK);
		scene.getStylesheets().add("/stylesheet.css");
		
	}
	
	public Scene getScene()
	{
		return scene;
	}
	
	
}

class UpwardProgress 
{
    private ProgressBar progressBar    = new ProgressBar();
    private Group       progressHolder = new Group(progressBar);

    public UpwardProgress(double width, double height) {
        progressBar.setMinSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        progressBar.setPrefSize(height, width);
        progressBar.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        progressBar.getTransforms().setAll(
                new Translate(0, height),
                new Rotate(-90, 0, 0)
        );
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Group getProgressHolder() {
        return progressHolder;
    }
}
