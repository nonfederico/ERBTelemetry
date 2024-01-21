package it.erb.telemetry.database;

import it.erb.telemetry.model.TelemetryData;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;


public class DatabaseManager 
{
	private static DatabaseManager instance = new DatabaseManager("DB","");
	private Connection conn;
	private String nome;
	private String path;
	
	
	private DatabaseManager(String nome, String path)
	{
		this.nome = nome;
		this.path = path;
	
		File db = new File(path + nome + ".db");
		
		try
		{
			if( db.exists() )
			{
				System.out.println("Il file esiste");
			}
			else
			{
				System.out.println("Il file non esiste");
			}
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + path + nome + ".db");
			if(conn != null)
			{
				System.out.println("Creazione tabelle..");
				
				// Creazione table History
				Statement stmt = conn.createStatement();
				String create_history = "CREATE TABLE HISTORY("
														+ "data DATE, " 
														+ "ThrottlePedal_Pos FLOAT, "
														+ "BrakePedal_Pos FLOAT, "
														+ "SteeringWheel_Pos FLOAT, "
						                                + "Acc_Voltage FLOAT, "
						                                + "Acc_SoC FLOAT, "
						                                + "Acc_Current FLOAT, "
						                                + "Acc_Temp FLOAT, "
						                                + "Acc_Cell_1_Temp FLOAT, "
						                                + "Acc_Cell_2_Temp FLOAT, "
						                                + "Acc_Cell_3_Temp FLOAT, "
						                                + "Acc_Cell_4_Temp FLOAT, "
						                                + "MotorRR_ActCurrent FLOAT, "
						                                + "MotorRR_ActSpeed FLOAT, "
						                                + "MotorRR_ActTorque FLOAT, "
						                                + "MotorRR_CmdSpeed FLOAT, "
						                                + "MotorRR_CmdTorque FLOAT, "
						                                + "MotorRL_ActCurrent FLOAT, "
						                                + "MotorRL_ActSpeed FLOAT, "
						                                + "MotorRL_ActTorque FLOAT, "
						                                + "MotorRL_CmdSpeed FLOAT, "
						                                + "MotorRL_CmdTorque FLOAT) ";
				stmt.executeUpdate(create_history);
				stmt.close();
				
				System.out.println("Table created successfully");
						
				
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	}
	
	/**
	 * Restituisce l'istanza DatabaseManager
	 */ 
	public static DatabaseManager getInstance(){ return instance; }
	
	/**
	 * Aggiunge un record alla tabella History
	 * @param data contiene i dati da salvare.
	 * @param p_iva the agency EIN.
	 * @return vero se l'operazione di insert è andata a buon fine.
	 */
	public boolean addRecord(TelemetryData data) 
	{
		PreparedStatement stmt;
		
		try 
		{
			stmt = conn.prepareStatement("INSERT INTO HISTORY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			stmt.setObject(1, data.date );
			stmt.setFloat(2, data.ThrottlePedal_Pos.getValue());
			stmt.setFloat(3, data.BrakePedal_Pos.getValue());
			stmt.setFloat(4, data.SteeringWheel_Pos.getValue());
			stmt.setFloat(5, data.HVAcc_Voltage.getValue());
			stmt.setFloat(6, data.HVAcc_SoC.getValue());
			stmt.setFloat(7, data.HVAcc_Current.getValue());
			stmt.setFloat(8, data.HVAcc_Temp.getValue());
			stmt.setFloat(9, data.HVAcc_Cell_1_Temp.getValue());
			stmt.setFloat(10, data.HVAcc_Cell_2_Temp.getValue());
			stmt.setFloat(11, data.HVAcc_Cell_3_Temp.getValue());
			stmt.setFloat(12, data.HVAcc_Cell_4_Temp.getValue());
			stmt.setFloat(13, data.MotorRR_ActCurrent.getValue());
			stmt.setFloat(14, data.MotorRR_ActSpeed.getValue());
			stmt.setFloat(15, data.MotorRR_ActTorque.getValue());
			stmt.setFloat(16, data.MotorRR_CmdSpeed.getValue());
			stmt.setFloat(17, data.MotorRR_CmdTorque.getValue());
			stmt.setFloat(18, data.MotorRL_ActCurrent.getValue());
			stmt.setFloat(19, data.MotorRL_ActSpeed.getValue());
			stmt.setFloat(20, data.MotorRL_ActTorque.getValue());
			stmt.setFloat(21, data.MotorRL_CmdSpeed.getValue());
			stmt.setFloat(22, data.MotorRL_CmdTorque.getValue());
			stmt.executeUpdate();
			stmt.close();
			
			return true;
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
		
	}
	
	
	public TelemetryData retrieveRecord( LocalDateTime startDate, LocalDateTime endDate )
	{
		try 
		{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HISTORY WHERE Data BETWEEN ? AND ?");
			stmt.setObject(1, startDate);
			stmt.setObject(2, endDate);
			ResultSet rs = stmt.executeQuery();
			
			TelemetryData td = new TelemetryData();
			
			td.date					= rs.getObject("Data",LocalDateTime.class);
			td.ThrottlePedal_Pos.setValue(rs.getFloat("ThrottlePedal_Pos"));
			td.BrakePedal_Pos.setValue(rs.getFloat("BrakePedal_Pos"));
			td.SteeringWheel_Pos.setValue(rs.getFloat("SteeringWheel_Pos"));
			td.HVAcc_Voltage.setValue(rs.getFloat("Acc_Voltage"));			
			td.HVAcc_SoC.setValue(rs.getFloat("Acc_SoC")); 			
			td.HVAcc_Current.setValue(rs.getFloat("Acc_Current")); 		
			td.HVAcc_Temp.setValue(rs.getFloat("Acc_Temp")); 	
			td.HVAcc_Cell_1_Temp.setValue(rs.getFloat("Acc_Cell_1_Temp")); 
			td.HVAcc_Cell_2_Temp.setValue(rs.getFloat("Acc_Cell_2_Temp"));
			td.HVAcc_Cell_3_Temp.setValue(rs.getFloat("Acc_Cell_3_Temp"));
			td.HVAcc_Cell_4_Temp.setValue(rs.getFloat("Acc_Cell_4_Temp"));
			td.MotorRR_ActCurrent.setValue(rs.getFloat("MotorRR_ActCurrent"));
			td.MotorRR_ActSpeed.setValue(rs.getFloat("MotorRR_ActCurrent"));
			td.MotorRR_ActTorque.setValue(rs.getFloat("MotorRR_ActTorque"));
			td.MotorRR_CmdSpeed.setValue(rs.getFloat("MotorRR_CmdSpeed"));
			td.MotorRR_CmdTorque.setValue(rs.getFloat("MotorRR_CmdTorque")); 
			td.MotorRL_ActCurrent.setValue(rs.getFloat("MotorRL_ActCurrent")); 
			td.MotorRL_ActSpeed.setValue(rs.getFloat("MotorRL_ActSpeed")); 	
			td.MotorRL_ActTorque.setValue(rs.getFloat("MotorRL_ActTorque"));
			td.MotorRL_CmdSpeed.setValue(rs.getFloat("MotorRL_CmdSpeed"));
			td.MotorRL_CmdTorque.setValue(rs.getFloat("MotorRL_CmdTorque"));
			
			return td;
			
			
			
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
		
		
	}
	
}
