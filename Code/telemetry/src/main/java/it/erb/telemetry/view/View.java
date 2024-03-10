package it.erb.telemetry.view;

import javafx.geometry.Insets;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class View 
{
	public Scene scene;
	public ChoiceBox<String> chBox_comPort;
	
	public Button btn_comConnect;
	public Button btn_comDisconnect;
	
	public Label lbl_comStatus;
	public Circle circle_comStatus;
	
    public Tab1 vBoxTable = new Tab1();
    public Tab2 chart = new Tab2();
    
    //metodo 2 data
    public DatePicker dataInizio = new DatePicker();
    public DatePicker dataFine = new DatePicker();
    
    //bottomPane
    public DataSensorReal dataReal = new DataSensorReal();
    
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
		dataInizio = vBoxTable.getStartDate();
		
		//metodo2
		dataFine = vBoxTable.getEndDate();
		
		ToolBar tlb_com = new ToolBar();
		tlb_com.getItems().add(chBox_comPort);
		tlb_com.getItems().add(btn_comConnect);
		tlb_com.getItems().add(btn_comDisconnect);
		tlb_com.getItems().add(circle_comStatus);
		tlb_com.getItems().add(lbl_comStatus);
		
		
		// CENTER PANE
		
		TabPane tabPane = new TabPane();
		Tab	tabChart = new Tab("Live chart", chart.getTilePane());
		Tab tabData = new Tab("History database", vBoxTable.getVbox());
		tabPane.getTabs().addAll(tabData, tabChart); //guarda classi Tab1 e Tab2
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		
		// MAIN PANE
		BorderPane bPane = new BorderPane();
		bPane.getStyleClass().add("borderPane");
		bPane.setTop(tlb_com);
		bPane.setCenter(tabPane); 
		bPane.setBottom(dataReal.getBottomPane()); //guarda classe DataSensorReak
		
		LinearGradient paint = new LinearGradient(
				0.0, 0.0038, 0.0, 1.0, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(0.2316, 0.2316, 0.2316, 1.0)),
				new Stop(1.0, new Color(0.1053, 0.1053, 0.1053, 1.0)));
		bPane.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
		
		// SCENE
		scene = new Scene(bPane, 1280, 720);
		scene.setFill(Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		
	}
	
	public Scene getScene()
	{
		return scene;
	}
	
	public LineChart<Number, Number> getLineChart() {
		return chart.getLineChart();
    }
	
	// Delegation pattern metodo 01 -> non funziona
	/*
	public DatePicker getStartDate() {
		return vBoxTable.getStartDate();
	}
	
	public DatePicker getEndDate() {
		return vBoxTable.getEndDate();
	}
	*/
	
//	metodo 02
	public DatePicker getStartDate() {
		dataInizio = vBoxTable.getStartDate();
		return dataInizio;
	}
	public DatePicker getEndDate() {
		dataFine = vBoxTable.getEndDate();
		return dataFine;
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
