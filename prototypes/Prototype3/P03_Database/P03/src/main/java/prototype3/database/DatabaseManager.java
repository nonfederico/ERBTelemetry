package prototype3.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import model.TelemetryData;

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
			stmt.setFloat(2, data.ThrottlePedal_Pos);
			stmt.setFloat(3, data.BrakePedal_Pos);
			stmt.setFloat(4, data.SteeringWheel_Pos);
			stmt.setFloat(5, data.Acc_Voltage);
			stmt.setFloat(6, data.Acc_SoC);
			stmt.setFloat(7, data.Acc_Current);
			stmt.setFloat(8, data.Acc_Temp);
			stmt.setFloat(9, data.Acc_Cell_1_Temp);
			stmt.setFloat(10, data.Acc_Cell_2_Temp);
			stmt.setFloat(11, data.Acc_Cell_3_Temp);
			stmt.setFloat(12, data.Acc_Cell_4_Temp);
			stmt.setFloat(13, data.MotorRR_ActCurrent);
			stmt.setFloat(14, data.MotorRR_ActSpeed);
			stmt.setFloat(15, data.MotorRR_ActTorque);
			stmt.setFloat(16, data.MotorRR_CmdSpeed);
			stmt.setFloat(17, data.MotorRR_CmdTorque);
			stmt.setFloat(18, data.MotorRL_ActCurrent);
			stmt.setFloat(19, data.MotorRL_ActSpeed);
			stmt.setFloat(20, data.MotorRL_ActTorque);
			stmt.setFloat(21, data.MotorRL_CmdSpeed);
			stmt.setFloat(22, data.MotorRL_CmdTorque);
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
			td.ThrottlePedal_Pos 	= rs.getFloat("ThrottlePedal_Pos");
			td.BrakePedal_Pos 		= rs.getFloat("BrakePedal_Pos");
			td.SteeringWheel_Pos 	= rs.getFloat("SteeringWheel_Pos");
			td.Acc_Voltage 			= rs.getFloat("Acc_Voltage");
			td.Acc_SoC 				= rs.getFloat("Acc_SoC");
			td.Acc_Current 			= rs.getFloat("Acc_Current");
			td.Acc_Temp 			= rs.getFloat("Acc_Temp");
			td.Acc_Cell_1_Temp 		= rs.getFloat("Acc_Cell_1_Temp");
			td.Acc_Cell_2_Temp 		= rs.getFloat("Acc_Cell_2_Temp");
			td.Acc_Cell_3_Temp 		= rs.getFloat("Acc_Cell_3_Temp");
			td.Acc_Cell_4_Temp 		= rs.getFloat("Acc_Cell_4_Temp");
			td.MotorRR_ActCurrent 	= rs.getFloat("MotorRR_ActCurrent");
			td.MotorRR_ActSpeed 	= rs.getFloat("MotorRR_ActCurrent");
			td.MotorRR_ActTorque 	= rs.getFloat("MotorRR_ActTorque");
			td.MotorRR_CmdSpeed 	= rs.getFloat("MotorRR_CmdSpeed");
			td.MotorRR_CmdTorque 	= rs.getFloat("MotorRR_CmdTorque");
			td.MotorRL_ActCurrent 	= rs.getFloat("MotorRL_ActCurrent");
			td.MotorRL_ActSpeed 	= rs.getFloat("MotorRL_ActSpeed");
			td.MotorRL_ActTorque 	= rs.getFloat("MotorRL_ActTorque");
			td.MotorRL_CmdSpeed 	= rs.getFloat("MotorRL_CmdSpeed");
			td.MotorRL_CmdTorque 	= rs.getFloat("MotorRL_CmdTorque");
			
			return td;
			
			
			
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
		
		
	}
	
}
