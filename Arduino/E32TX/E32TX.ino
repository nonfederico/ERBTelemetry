#include "Arduino.h"
#include <SoftwareSerial.h>

// Arduino <----mySerial---->E32
// Arduino <-----Serial----->PC

SoftwareSerial mySerial(2, 3);  // E32 TX, RX      

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
	bool saf_SDBRght;
};

TelemetryData td;

void setup() 
{
  Serial.begin(38400);
  Serial.println("E32 Tx setup..");

  delay(500);
  mySerial.begin(9600);
  
  Serial.println("E32 Tx setup completed");
}
 
void loop() 
{
 
  
  td.ThrottlePedal_Pos = random(0,1000)/10.0;
  td.BrakePedal_Pos = random(0,1000)/10.0;
  td.SteeringWheel_Pos = random(0,1000)/10.0;
  td.HVAcc_Voltage = random(0,1000)/10.0;
  td.HVAcc_SoC = random(0,1000)/10.0;
  td.HVAcc_Current = random(0,1000)/10.0;
  td.HVAcc_Temp = random(0,1000)/10.0;
  td.HVAcc_Cell_1_Temp = random(0,1000)/10.0;
  td.saf_BSPD = random(0,100)>50;
  td.saf_IMD = random(0,100)>50;


  String packet = encodePacket(td);
  mySerial.println(packet);
  Serial.println(packet);
  delay(1000);

    
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

  packet.concat(encodeData("B01", td.saf_BSPD));
  packet.concat(encodeData("B02", td.saf_IMD));

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




