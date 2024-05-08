module it.erb.telemetry {
    requires javafx.controls;
	requires javafx.base;
	requires javafx.graphics;
	requires com.fazecast.jSerialComm;
	requires java.desktop;
	requires java.sql;
	requires eu.hansolo.medusa;
	requires junit;
	requires org.testfx;
	requires org.testfx.junit;
	requires javafx.fxml;
	requires testFx;
			
    exports it.erb.telemetry;
    exports it.erb.telemetry.data;
    exports it.erb.telemetry.data.sensor;
}
