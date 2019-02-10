package se.metricspace.moon;

import java.text.ParseException;
import org.shredzone.commons.suncalc.MoonPosition;
import org.shredzone.commons.suncalc.SunPosition;

public class Eclipse {
    public static void main(String[] args) {
        // Stockholm Lat=59.33333,Long=18.05 -> https://www.google.se/maps/@59.33333,18.05,14z
        // Göteborg Lat=57.7125,Long=11.985 -> https://www.google.se/maps/@57.7125,11.985,14z
        // Malmö Lat=55.6,Long=13.0 -> https://www.google.se/maps/@55.6,13,14z
        // Umeå Lat=63.8,Long=20.25 -> https://www.google.se/maps/@63.8,20.250,14z
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.text.NumberFormat numberFormat = new java.text.DecimalFormat("#.###");
        try {
            java.util.Date date = dateFormat.parse("2019-01-21 04:30");
            for(int i=0;i<15;i++) {
//                 MoonPosition moon = computeMoon(date, 18.05, 59.33333); // Stockholm
//                MoonPosition moon = computeMoon(date, 13.0, 55.6); // Malmö
                MoonPosition moon = computeMoon(date, 20.25, 63.8); // Umeå
                String direction = getDirection(moon.getAzimuth());
                System.out.println("<tr><td>"+dateFormat.format(date)+"</td><td>"+numberFormat.format(moon.getAzimuth())+"&deg;"+direction+"</td><td>"+numberFormat.format(moon.getAltitude())+"&deg;</td></tr>");
                date = new java.util.Date(date.getTime()+900000L); // 15 minutes forward ...
            }
            System.out.println("##########################");
            date = dateFormat.parse("2019-01-21 06:40");
            for(int i=0;i<7;i++) {
//                SunPosition sun = computeSun(date, 18.05, 59.33333); // Stockholm
//                SunPosition sun = computeSun(date, 13.0, 55.6); // Malmö
                SunPosition sun = computeSun(date, 20.25, 63.8); // Umeå
                String direction = getDirection(sun.getAzimuth());
                System.out.println("<tr><td>"+dateFormat.format(date)+"</td><td>"+numberFormat.format(sun.getAzimuth())+"&deg;"+direction+"</td><td>"+numberFormat.format(sun.getAltitude())+"&deg;</td></tr>");
                date = new java.util.Date(date.getTime()+1200000L); // 20 minutes forward ...
            }
        } catch(ParseException exception) {
            System.out.println("ParseException: "+exception.getMessage());
        } finally {
        }
    }

    private static MoonPosition computeMoon(java.util.Date date, double longitude, double latitude) {
        MoonPosition moon = MoonPosition.compute()
                        .on(date)       // set a date
                        .at(latitude, longitude)   // set a location
                        .execute();     // get the results
        return moon;
    }

    private static SunPosition computeSun(java.util.Date date, double longitude, double latitude) {
        SunPosition sun = SunPosition.compute()
                        .on(date)       // set a date
                        .at(latitude, longitude)   // set a location
                        .execute();     // get the results
        return sun;
    }

    private static String getDirection(double azimuth) {
        String direction = " (N)";
        if(azimuth>44.999) {
            direction = " (E)";
        }
        if(azimuth>134.999) {
            direction = " (S)";
        }
        if(azimuth>224.999) {
            direction = " (W)";
        }
        if(azimuth>314.999) {
            direction = " (N)";
        }
        return direction;
    }
}
