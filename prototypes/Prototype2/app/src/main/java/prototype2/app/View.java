package prototype2.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class View extends Application
{
	static LineChart linechart;
	static XYChart.Series series;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		NumberAxis xAxis = new NumberAxis("Years",1960, 2020, 10);
		NumberAxis yAxis = new NumberAxis("Value",0, 100, 10);
		
		series = new XYChart.Series();	
		series.setName("First series");
		
		series.getData().add( new XYChart.Data(1970,25) );
		series.getData().add( new XYChart.Data(1980,50) );
		series.getData().add( new XYChart.Data(1990,75) );
		series.getData().add( new XYChart.Data(2000,25) );
		series.getData().add( new XYChart.Data(2010,35) );
		
		linechart = new LineChart(xAxis,yAxis);
		linechart.getData().add(series);
		
		Group root = new Group();
		root.getChildren().add(linechart);
		Scene scene = new Scene(root,600,300);
		primaryStage.setTitle("Sample application");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();	
		
	}
	
	
	public static void main(String args[])
	{ 
		launch(args); 
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		series.getData().add( new XYChart.Data(2015,81) );
		
		
	} 
	
	
	

	
}
