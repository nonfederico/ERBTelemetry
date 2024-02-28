#include "Arduino.h"

struct TelemetryData
{
  float ThrottlePedal_Pos;
  float BrakePedal_Pos;
  float SteeringWheel_Pos;
  float HVAcc_Voltage;
  float HVAcc_SoC;
  float HVAcc_Current;
  float HVAcc_Temp;
  float HVAcc_Cell_1_Temp;
  float HVAcc_Cell_2_Temp;
  float HVAcc_Cell_3_Temp;
  float HVAcc_Cell_4_Temp;
  float LVAcc_Voltage;
  float LVAcc_SoC;
  float LVAcc_Current;
  float inv_Temperature;
  float inv_HVVoltage;
  float inv_LVVoltage;
  float MotorRR_ActCurrent;
	float MotorRR_ActSpeed;
	float MotorRR_ActTorque;
	float MotorRR_CmdSpeed;
	float MotorRR_CmdTorque;
	float MotorRL_ActCurrent;
	float MotorRL_ActSpeed;
	float MotorRL_ActTorque;
	float MotorRL_CmdSpeed;
	float MotorRL_CmdTorque;
	float MotorRL_Frequency;
	float MotorRR_Frequency;
	float MotorRL_Temperature;
	float MotorRR_Temperature;
	float vehicle_linearSpeed;
  bool saf_BSPD;
	bool saf_IMD;
	bool saf_LVMS;
	bool saf_AMS;
	bool saf_IS;
	bool saf_BOTS;
	bool saf_SDBCockpit;
	bool saf_SDBLeft;
	bool saf_SDBRight;
};

TelemetryData td;
int pot = A0;

void setup() 
{
  Serial.begin(38400);
  Serial.println("E32 Rx emulator setup..");
  pinMode(A0, INPUT);
  delay(500);
  
  Serial.println("E32 Rx emulator setup completed");
 
}
 
void loop() 
{
  td.ThrottlePedal_Pos = analogRead(A0)*2;
   Serial.println(td.ThrottlePedal_Pos);
  //td.ThrottlePedal_Pos = random(0,1000)/10.0;
  td.BrakePedal_Pos = 0;
  td.SteeringWheel_Pos = random(0,1000)/10.0;
  td.HVAcc_Voltage = random(0,1000)/10.0;
  td.HVAcc_SoC = random(0,1000)/10.0;
  td.HVAcc_Current = random(0,1000)/10.0;
  td.HVAcc_Temp = random(0,1000)/10.0;
  td.HVAcc_Cell_1_Temp = random(0,1000)/10.0;
  td.HVAcc_Cell_2_Temp = random(0,1000)/10.0;
  td.HVAcc_Cell_3_Temp = random(0,1000)/10.0;
  td.HVAcc_Cell_4_Temp = random(0,1000)/10.0;
  td.LVAcc_Voltage = random(0,240)/10.0;
  td.LVAcc_SoC = random(0,1000)/10.0;
  td.LVAcc_Current = random(0,200)/10.0;
  td.inv_Temperature = random(0,1000)/10.0;
  td.inv_HVVoltage = random(0,1000)/10.0;
  td.inv_LVVoltage = random(0,240)/10.0;
  td.MotorRR_ActCurrent = random(0,1000)/10.0;
	//td.MotorRR_ActSpeed = random(0,200000)/10.0;
  td.MotorRR_ActSpeed = td.ThrottlePedal_Pos/10.0;
	td.MotorRR_ActTorque = td.ThrottlePedal_Pos/10.0;
	td.MotorRR_CmdSpeed = td.ThrottlePedal_Pos/10.0;
	td.MotorRR_CmdTorque = td.ThrottlePedal_Pos/10.0;
	td.MotorRL_ActCurrent = random(0,1000)/10.0;
	td.MotorRL_ActSpeed = td.ThrottlePedal_Pos/10.0;
	td.MotorRL_ActTorque = td.ThrottlePedal_Pos/10.0;
	td.MotorRL_CmdSpeed = td.ThrottlePedal_Pos/10.0;
	td.MotorRL_CmdTorque = td.ThrottlePedal_Pos/10.0;
	td.MotorRL_Frequency = random(0,1000)/10.0;
	td.MotorRR_Frequency = random(0,1000)/10.0;
	td.MotorRL_Temperature = random(0,1000)/10.0;
	td.MotorRR_Temperature = random(0,1000)/10.0;
	td.vehicle_linearSpeed = td.ThrottlePedal_Pos; 
  td.saf_BSPD = random(0,100)>20;
  td.saf_IMD = random(0,100)>20;
  td.saf_LVMS = random(0,100)>20;
	td.saf_AMS = random(0,100)>20;
	td.saf_IS = random(0,100)>20;
	td.saf_BOTS = random(0,100)>20;
	td.saf_SDBCockpit = random(0,100)>20;
	td.saf_SDBLeft = random(0,100)>20;
	td.saf_SDBRight = random(0,100)>20;

  String packet = encodePacket(td);
  
  Serial.println(packet);
  delay(100);

    
}

String encodePacket(TelemetryData td)
{
  String packet = "";

  String startCode = "#$*";
  String endCode = "*&#";

  packet.concat(startCode);
  packet.concat(encodeData("F01", td.ThrottlePedal_Pos, 4, 1));
  packet.concat(encodeData("F02", td.BrakePedal_Pos, 4, 1));
  packet.concat(encodeData("F03", td.SteeringWheel_Pos, 4, 1));
  packet.concat(encodeData("F04", td.HVAcc_Voltage, 4, 1));
  packet.concat(encodeData("F05", td.HVAcc_SoC, 4, 1));
  packet.concat(encodeData("F06", td.HVAcc_Current, 4, 1));
  packet.concat(encodeData("F07", td.HVAcc_Temp, 4, 1));
  packet.concat(encodeData("F08", td.HVAcc_Cell_1_Temp, 4, 1));
  packet.concat(encodeData("F09", td.HVAcc_Cell_2_Temp, 4, 1));
  packet.concat(encodeData("F10", td.HVAcc_Cell_3_Temp, 4, 1));
  packet.concat(encodeData("F11", td.HVAcc_Cell_4_Temp, 4, 1));
  packet.concat(encodeData("F12", td.LVAcc_Voltage, 4, 1));
  packet.concat(encodeData("F13", td.LVAcc_SoC, 4, 1));
  packet.concat(encodeData("F14", td.LVAcc_Current, 4, 1));
  packet.concat(encodeData("F15", td.inv_Temperature, 4, 1));
  packet.concat(encodeData("F16", td.inv_HVVoltage, 4, 1));
  packet.concat(encodeData("F17", td.inv_LVVoltage, 4, 1));
  packet.concat(encodeData("F18", td.MotorRR_ActCurrent, 4, 1));
  packet.concat(encodeData("F19", td.MotorRR_ActSpeed, 4, 1));
  packet.concat(encodeData("F20", td.MotorRR_ActTorque, 4, 1));
  packet.concat(encodeData("F21", td.MotorRR_CmdSpeed, 4, 1));
  packet.concat(encodeData("F22", td.MotorRR_CmdTorque, 4, 1));
  packet.concat(encodeData("F23", td.MotorRL_ActCurrent, 4, 1));
  packet.concat(encodeData("F24", td.MotorRL_ActSpeed, 4, 1));
  packet.concat(encodeData("F25", td.MotorRL_ActTorque, 4, 1));
  packet.concat(encodeData("F26", td.MotorRL_CmdSpeed, 4, 1));
  packet.concat(encodeData("F27", td.MotorRL_CmdTorque, 4, 1));
  packet.concat(encodeData("F28", td.MotorRL_Frequency, 4, 1));
  packet.concat(encodeData("F29", td.MotorRR_Frequency, 4, 1));
  packet.concat(encodeData("F30", td.MotorRL_Temperature, 4, 1));
  packet.concat(encodeData("F31", td.MotorRR_Temperature, 4, 1));
  packet.concat(encodeData("F32", td.vehicle_linearSpeed, 4, 1));
  packet.concat(encodeData("B01", td.saf_BSPD));
  packet.concat(encodeData("B02", td.saf_IMD));
  packet.concat(encodeData("B03", td.saf_LVMS));
  packet.concat(encodeData("B04", td.saf_AMS));
  packet.concat(encodeData("B05", td.saf_IS));
  packet.concat(encodeData("B06", td.saf_BOTS));
  packet.concat(encodeData("B07", td.saf_SDBCockpit));
  packet.concat(encodeData("B08", td.saf_SDBLeft));
  packet.concat(encodeData("B09", td.saf_SDBRight));

  packet.concat(endCode);

  return packet;
}

String encodeData(char* id, float value, signed char width, unsigned char precision)
{
    String buffer = "";
    char val[21];
    char data[30] = "";

    if(width > 20)
      width = 20;

    dtostrf(value, width, precision, val);

    buffer.concat(id);
    buffer.concat(":");
    buffer.concat(val);
    buffer.concat(";");

    return buffer;
}

String encodeData( char* id, bool value)
{
    String buffer = "";
   
    buffer.concat(id);
    buffer.concat(":");
    buffer.concat(value ? "1" : "0");
    buffer.concat(";");

    return buffer;
}




