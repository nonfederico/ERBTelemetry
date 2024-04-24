/**
 * 
 */
/**
 * 
 */
module ManCatt_Telemetry {
	exports it.erb.telemetry.model;
	exports it.erb.telemetry;
	exports it.erb.telemetry.controller;
	exports it.erb.telemetry.database;
	exports it.erb.telemetry.view;
	exports it.erb.telemetry.model.sensor;

	requires com.fazecast.jSerialComm;
	requires eu.hansolo.medusa;
	requires hamcrest.core;
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires junit;
	requires org.testfx;
	requires org.testfx.junit;
}