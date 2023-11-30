#include "Arduino.h"
#include <SoftwareSerial.h>

// Arduino <----mySerial---->E32
// Arduino <-----Serial----->PC

SoftwareSerial mySerial(2, 3);  // E32 TX, RX      
 
void setup() 
{
  Serial.begin(9600);
  Serial.println("E32 Rx setup..");

  delay(500);
  mySerial.begin(9600);
  
  Serial.println("E32 Rx setup completed");
}
 
void loop() 
{
 if (mySerial.available()) 
 {
    Serial.write(mySerial.read());
 }
  
}