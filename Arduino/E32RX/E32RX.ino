#include <LoRa_E32.h>
#include "Arduino.h"
#include <SoftwareSerial.h>

// Arduino <----mySerial---->E32
// Arduino <-----Serial----->PC

SoftwareSerial mySerial(2, 3);  // E32 TX, RX      
LoRa_E32 e32ttl(&mySerial); // Config without connect AUX and M0 M1

void setup() 
{
  Serial.begin(38400);
  Serial.println("E32 Rx setup..");

  delay(500);
  mySerial.begin(9600);
  e32ttl.begin();

  Serial.println("E32 Rx setup completed");
}
 
void loop() 
{
  //if (mySerial.available()) 
  //{
  //  Serial.write(mySerial.read());
  //}
  String buffer;

  if (e32ttl.available()>1) 
  {
      // read the String message
    ResponseContainer rc = e32ttl.receiveMessage();
    // Is something goes wrong print error
    if (rc.status.code!=1)
    {
      rc.status.getResponseDescription();
    }
    else
    {
      // Print the data received
      const String startString = "#$*";
      const String endString = "*&#";
      int startIndex = rc.data.indexOf(startString);
      int endIndex = rc.data.indexOf(endString);

      if(startIndex == -1 and endIndex == -1)
      {
        buffer.concat( rc.data.substring(startIndex,rc.data.length()) );
      }

      if(endIndex > -1 && endIndex < rc.data.length() )
      {
        buffer.concat( rc.data.substring(0,endIndex+3) );
        Serial.println(buffer);
      }
        

      if(startIndex > -1)
      {
        buffer = "";

        //if(endIndex > startIndex && endIndex < rc.data.length() )
        buffer.concat( rc.data.substring(startIndex,rc.data.length()) );
      }

      

    }
  }
  delay(1);
}