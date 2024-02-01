package it.erb.telemetry.model;

public interface NewPacketListener {
	void packetHandling(TelemetryData td);
}
