package se.metricspace.moon;

import org.shredzone.commons.suncalc.MoonPosition;
import org.shredzone.commons.suncalc.SunPosition;

public class WhereIsTheMoon {
    public static void main(String[] args) {
            for(int i=1;i<31;i++) {
                for(int h=0;h<24;h++) {
                    String timestamp = "2018-09-";
                    if(i<10) {
                        timestamp += "0";
                    }
                    timestamp += i;
                    timestamp += " ";
                    if(h<10) {
                        timestamp += "0";
                    }
                    timestamp += h;
                    compute(timestamp+":00");
//                    compute(timestamp+":15");
                    compute(timestamp+":30");
//                    compute(timestamp+":45");
                }
            }
            /*
            SunTimes times = SunTimes.compute()
                        .on(date)       // set a date
                        .at(latitude, longitude)   // set a location
                        .execute();     // get the results
            System.out.println("Sunrise: " + times.getRise());
            System.out.println("Sunset: " + times.getSet()); 
            System.out.println("getNoon: " + times.getNoon()); 
            System.out.println("getNadir: " + times.getNadir()); 
            SunPosition position = SunPosition.compute()
                        .on(date)       // set a date
                        .at(latitude, longitude)   // set a location
                        .execute();     // get the results
            System.out.println("getDistance: " + position.getDistance() + "°");
            System.out.println("getAltitude: " + position.getAltitude() + "°");
            System.out.println("Azimuth: " + position.getAzimuth() + "°");
            MoonTimes moon = MoonTimes.compute()
                        .on(date)       // set a date
                        .at(latitude, longitude)   // set a location
                        .execute();     // get the results
            MoonPosition mposition = MoonPosition.compute()
                        .on(date)       // set a date
                        .at(latitude, longitude)   // set a location
                        .execute();     // get the results
            System.out.println("Moonrise: " + moon.getRise());
            System.out.println("Moonset: " + moon.getSet()); 
            System.out.println("getDistance: " + mposition.getDistance() + "km");
            System.out.println("getAltitude: " + mposition.getAltitude() + "°");
            System.out.println("Azimuth: " + mposition.getAzimuth() + "°");
*/
    }
    private static void compute(String timeStamp) {
        java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.text.DecimalFormat angleFormatter = new java.text.DecimalFormat("#0.00");
        try {
            java.util.Date date =  dateFormatter.parse(timeStamp);
            double longitude=18.05;
            double latitude = 59.33333;
            SunPosition sun = SunPosition.compute()
                        .on(date)       // set a date
                        .at(latitude, longitude)   // set a location
                        .execute();     // get the results
            MoonPosition moon = MoonPosition.compute()
                        .on(date)       // set a date
                        .at(latitude, longitude)   // set a location
                        .execute();     // get the results
            if(moon.getAltitude()>0.0 || sun.getAltitude()>0.0) {
                System.out.print("<tr>");
                System.out.print("<td>"+timeStamp+"</td>");
                if(sun.getAltitude()>0.0) {
                    System.out.print("<td>");
                    System.out.print(angleFormatter.format(sun.getAltitude()));
                    System.out.print("&deg;</td><td>");
                    System.out.print(angleFormatter.format(sun.getAzimuth()));
                    System.out.print("&deg;</td>");
                } else {
                    System.out.print("<td>&nbsp;</td><td>&nbsp;</td>");
                }
                if(moon.getAltitude()>0.0) {
                    System.out.print("<td>");
                    System.out.print(angleFormatter.format(moon.getAltitude()));
                    System.out.print("&deg;</td><td>");
                    System.out.print(angleFormatter.format(moon.getAzimuth()));
                    System.out.print("&deg;</td>");
                } else {
                    System.out.print("<td>&nbsp;</td><td>&nbsp;</td>");
                }
                System.out.println("</tr>");
            }
        } catch (java.text.ParseException exception) {
            System.out.println("Problem parsing date: "+exception.getMessage());
        }
    }
}
