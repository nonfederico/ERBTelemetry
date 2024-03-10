package it.erb.telemetry.database;

import it.erb.telemetry.model.TelemetryData;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager 
{
	private static DatabaseManager instance = new DatabaseManager("DB","");
	private Connection conn;
	private String nome;
	private String path;
	
	
	private DatabaseManager(String nome01, String path01)
	{
		this.nome = nome01;
		this.path = path01;
	
		File db = new File(path + nome + ".db");
		
		/*
		 * per prima cosa controlliamo che il database non esista, altrimenti lo carichiamo.
		 * se non esiste viene creato un nuovo db
		 */
		
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
						                                + "HVAcc_Voltage FLOAT, "
						                                + "HVAcc_SoC FLOAT, "
						                                + "HVAcc_Current FLOAT, "
						                                + "HVAcc_Temp FLOAT, "
						                                + "HVAcc_Cell_1_Temp FLOAT, "
						                                + "HVAcc_Cell_2_Temp FLOAT, "
						                                + "HVAcc_Cell_3_Temp FLOAT, "
						                                + "HVAcc_Cell_4_Temp FLOAT, "
						                                + "LVAcc_Voltage FLOAT, "
						                                + "LVAcc_SoC FLOAT, "
						                                + "LVAcc_Current FLOAT, "
						                                + "inv_Temperature FLOAT, "
						                                + "inv_HVVoltage FLOAT, "
						                                + "inv_LVVoltage FLOAT, "
						                                + "motorRR_ActCurrent FLOAT, "
						                                + "motorRR_ActSpeed FLOAT, "
						                                + "motorRR_ActTorque FLOAT, "
						                                + "motorRR_CmdSpeed FLOAT, "
						                                + "motorRR_CmdTorque FLOAT, "
						                                + "motorRR_Frequency FLOAT,"
						                                + "motorRR_Temperature FLOAT,"
						                                + "motorRL_ActCurrent FLOAT, "
						                                + "motorRL_ActSpeed FLOAT, "
						                                + "motorRL_ActTorque FLOAT, "
						                                + "motorRL_CmdSpeed FLOAT, "
						                                + "motorRL_CmdTorque FLOAT,"
						                                + "motorRL_Frequency FLOAT,"
						                                + "motorRL_Temperature FLOAT,"
						                                + "vehicle_linearSpeed FLOAT,"
						                                + "saf_BSPD BOOLEAN,"
						                                + "saf_IMD BOOLEAN,"
						                                + "saf_LVMS BOOLEAN,"
						                                + "saf_AMS BOOLEAN,"
						                                + "saf_IS BOOLEAN,"
						                                + "saf_BOTS BOOLEAN,"
						                                + "saf_SDBCockpit BOOLEAN,"
						                                + "saf_SDBLeft BOOLEAN,"
						                                + "saf_SDBRight BOOLEAN"
						                                + ")";
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
			stmt = conn.prepareStatement("INSERT INTO HISTORY VALUES("
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?)");
			
			stmt.setObject(1, data.date.getValue() );
			stmt.setFloat(2, data.throttlePedal_Pos.getValue());
			stmt.setFloat(3, data.brakePedal_Pos.getValue());
			stmt.setFloat(4, data.steeringWheel_Pos.getValue());
			stmt.setFloat(5, data.HVAcc_Voltage.getValue());
			stmt.setFloat(6, data.HVAcc_SoC.getValue());
			stmt.setFloat(7, data.HVAcc_Current.getValue());
			stmt.setFloat(8, data.HVAcc_Temp.getValue());
			stmt.setFloat(9, data.HVAcc_Cell_1_Temp.getValue());
			stmt.setFloat(10, data.HVAcc_Cell_2_Temp.getValue());
			stmt.setFloat(11, data.HVAcc_Cell_3_Temp.getValue());
			stmt.setFloat(12, data.HVAcc_Cell_4_Temp.getValue());
			stmt.setFloat(13, data.motorRR_ActCurrent.getValue());
			stmt.setFloat(14, data.motorRR_ActSpeed.getValue());
			stmt.setFloat(15, data.motorRR_ActTorque.getValue());
			stmt.setFloat(16, data.motorRR_CmdSpeed.getValue());
			stmt.setFloat(17, data.motorRR_CmdTorque.getValue());
			stmt.setFloat(18, data.motorRR_Frequency.getValue());
			stmt.setFloat(19, data.motorRR_Temperature.getValue());
			stmt.setFloat(20, data.motorRL_ActCurrent.getValue());
			stmt.setFloat(21, data.motorRL_ActSpeed.getValue());
			stmt.setFloat(22, data.motorRL_ActTorque.getValue());
			stmt.setFloat(23, data.motorRL_CmdSpeed.getValue());
			stmt.setFloat(24, data.motorRL_CmdTorque.getValue());
			stmt.setFloat(25, data.motorRL_Frequency.getValue());
			stmt.setFloat(26, data.motorRL_Temperature.getValue());
			stmt.setFloat(27, data.vehicle_linearSpeed.getValue());
			stmt.setBoolean(28, data.saf_BSPD.getValue());
			stmt.setBoolean(29, data.saf_IMD.getValue());
			stmt.setBoolean(30, data.saf_LVMS.getValue());
			stmt.setBoolean(31, data.saf_AMS.getValue());
			stmt.setBoolean(32, data.saf_IS.getValue());
			stmt.setBoolean(33, data.saf_BOTS.getValue());
			stmt.setBoolean(34, data.saf_SDBCockpit.getValue());
			stmt.setBoolean(35, data.saf_SDBLeft.getValue());
			stmt.setBoolean(36, data.saf_SDBRight.getValue());
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
	
	
	public List<TelemetryData> retrieveRecord( LocalDateTime startDate, LocalDateTime endDate )
	{
		try 
		{
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HISTORY WHERE Data BETWEEN ? AND ?");
			stmt.setObject(1, startDate);
			stmt.setObject(2, endDate);
			ResultSet rs = stmt.executeQuery();
			
			List<TelemetryData> list = new ArrayList<>();
			
			
			while (rs.next()) 
			{
				TelemetryData td = new TelemetryData();
				td.date.set(rs.getObject("Data",LocalDateTime.class));
				td.throttlePedal_Pos.setValue(rs.getFloat("ThrottlePedal_Pos"));
				td.brakePedal_Pos.setValue(rs.getFloat("BrakePedal_Pos"));
				td.steeringWheel_Pos.setValue(rs.getFloat("SteeringWheel_Pos"));
				td.HVAcc_Voltage.setValue(rs.getFloat("HVAcc_Voltage"));			
				td.HVAcc_SoC.setValue(rs.getFloat("HVAcc_SoC")); 			
				td.HVAcc_Current.setValue(rs.getFloat("HVAcc_Current")); 		
				td.HVAcc_Temp.setValue(rs.getFloat("HVAcc_Temp")); 	
				td.HVAcc_Cell_1_Temp.setValue(rs.getFloat("HVAcc_Cell_1_Temp")); 
				td.HVAcc_Cell_2_Temp.setValue(rs.getFloat("HVAcc_Cell_2_Temp"));
				td.HVAcc_Cell_3_Temp.setValue(rs.getFloat("HVAcc_Cell_3_Temp"));
				td.HVAcc_Cell_4_Temp.setValue(rs.getFloat("HVAcc_Cell_4_Temp"));
				td.LVAcc_Voltage.setValue(rs.getFloat("LVAcc_Voltage"));
				td.LVAcc_SoC.setValue(rs.getFloat("LVAcc_SoC"));
				td.LVAcc_Current.setValue(rs.getFloat("LVAcc_Current"));
				td.inv_Temperature.setValue(rs.getFloat("inv_Temperature"));
				td.inv_HVVoltage.setValue(rs.getFloat("inv_HVVoltage"));
				td.inv_LVVoltage.setValue(rs.getFloat("inv_LVVoltage"));
				td.motorRR_ActCurrent.setValue(rs.getFloat("motorRR_ActCurrent"));
				td.motorRR_ActSpeed.setValue(rs.getFloat("motorRR_ActCurrent"));
				td.motorRR_ActTorque.setValue(rs.getFloat("motorRR_ActTorque"));
				td.motorRR_CmdSpeed.setValue(rs.getFloat("motorRR_CmdSpeed"));
				td.motorRR_CmdTorque.setValue(rs.getFloat("motorRR_CmdTorque")); 
				td.motorRR_Frequency.setValue(rs.getFloat("motorRR_Frequency")); 
				td.motorRR_Temperature.setValue(rs.getFloat("motorRR_Temperature")); 
				td.motorRL_ActCurrent.setValue(rs.getFloat("motorRL_ActCurrent")); 
				td.motorRL_ActSpeed.setValue(rs.getFloat("motorRL_ActSpeed")); 	
				td.motorRL_ActTorque.setValue(rs.getFloat("motorRL_ActTorque"));
				td.motorRL_CmdSpeed.setValue(rs.getFloat("motorRL_CmdSpeed"));
				td.motorRL_CmdTorque.setValue(rs.getFloat("motorRL_CmdTorque"));
				td.motorRL_Frequency.setValue(rs.getFloat("motorRL_Frequency")); 
				td.motorRL_Temperature.setValue(rs.getFloat("motorRL_Temperature")); 
				td.vehicle_linearSpeed.setValue(rs.getFloat("vehicle_linearSpeed")); 
				td.saf_BSPD.setValue(rs.getBoolean("saf_BSPD"));
				td.saf_IMD.setValue(rs.getBoolean("saf_IMD"));
				td.saf_LVMS.setValue(rs.getBoolean("saf_LVMS"));
				td.saf_AMS.setValue(rs.getBoolean("saf_AMS"));
				td.saf_IS.setValue(rs.getBoolean("saf_IS"));
				td.saf_BOTS.setValue(rs.getBoolean("saf_BOTS"));
				td.saf_SDBCockpit.setValue(rs.getBoolean("saf_SDBCockpit"));
				td.saf_SDBLeft.setValue(rs.getBoolean("saf_SDBLeft"));
				td.saf_SDBRight.setValue(rs.getBoolean("saf_SDBRight"));
								
		        list.add(td);
		    }
			
			rs.close();
			
			return list;
			
			
			
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
		
		
	}
	
}
