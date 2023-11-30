#include "Arduino.h"
#include <SoftwareSerial.h>

// Arduino <----mySerial---->E32
// Arduino <-----Serial----->PC

SoftwareSerial mySerial(2, 3);  // E32 TX, RX      
 
void setup() 
{
  Serial.begin(9600);
  Serial.println("E32 Tx setup..");

  delay(500);
  mySerial.begin(9600);
  
  Serial.println("E32 Tx setup completed");
}
 
void loop() 
{
 
  mySerial.println("#$*F01=23.6,F02=54.89,B01=1*&#");
  delay(1000);
  
}