package it.erb.telemetry.controller;

import javafx.scene.chart.LineChart;	
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


public class ControlChart {
    //si occupa di renderizzare il grafico, aggiungendo i dati presi dal database o dai messaggi
	//inserendoli nel grafico creato nella classe view
  
	public ControlChart() {};
	
	private XYChart.Series<Number, Number> serie= new XYChart.Series<Number, Number>();
	private LineChart lineChart = new LineChart(new NumberAxis(), new NumberAxis()); 	


	public void AddSeries(LineChart A) {
		serie.getData().add(new XYChart.Data<>(1, 5));
	    serie.getData().add(new XYChart.Data<>(2, 10));
	    serie.getData().add(new XYChart.Data<>(3, 15));
	    A.getData().add(serie);
	}

	/*
    serie.getData().remove(0);
	
	
	
	private LineChart<Number, Number> chart;

	private XYChart.Series<Number, Number> dataSeries;

	private NumberAxis xAxis;

	private Timeline animation;

	private double sequence = 0;

	private double y = 10;

	private final int MAX_DATA_POINTS = 25, MAX = 10, MIN = 5;;
	
	public ControlChart() {
		// create timeline to add new data every 60th of second
	    animation = new Timeline();
	    animation.getKeyFrames().add(new KeyFrame(Duration.ofMillis(1000)), (ActionEvent actionEvent) -> plotTime());
	    animation.setCycleCount(Animation.INDEFINITE);
	};

	public void AddSeries(LineChart A) {
	
	}
	*/
}
