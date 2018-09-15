package se.metricspace.moon;

import org.shredzone.commons.suncalc.MoonTimes;
import org.shredzone.commons.suncalc.SunTimes;

public class Rising {

    public static void main(String[] args) {
        final double LONGITUDE=18.05;
        final double LATITUDE = 59.33333;
        java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.List<String> events = new java.util.ArrayList<>();
        for(int d=1;d<32;d++) {
            String timestamp = "2018-08-";
            if(d<10) {
                timestamp += "0";
            }
            timestamp += d;
            timestamp += " 00:01";
            SunTimes sun = getSunTimes(timestamp, LONGITUDE, LATITUDE);
            if(null!=sun) {
                events.add("<td>"+dateFormatter.format(sun.getRise())+"</td><td>Sun rise</td>");
                events.add("<td>"+dateFormatter.format(sun.getSet())+"</td><td>Sun set</td>");
            }
        }
        for(int d=1;d<31;d++) {
            String timestamp = "2018-09-";
            if(d<10) {
                timestamp += "0";
            }
            timestamp += d;
            timestamp += " 00:01";
            SunTimes sun = getSunTimes(timestamp, LONGITUDE, LATITUDE);
            if(null!=sun) {
                events.add("<td>"+dateFormatter.format(sun.getRise())+"</td><td>Sun rise</td>");
                events.add("<td>"+dateFormatter.format(sun.getSet())+"</td><td>Sun set</td>");
            }
        }
        MoonTimes moon = getMoonTimes("2018-08-01 00:00", LONGITUDE, LATITUDE);
        if(moon.getRise().getTime()>moon.getSet().getTime()) {
            moon = getMoonTimes(new java.util.Date(moon.getRise().getTime()-60000L), LONGITUDE, LATITUDE);
        }
        for(int i=0;i<62;i++) {
            events.add("<td>"+dateFormatter.format(moon.getRise())+"</td><td>Moon rise</td>");
            events.add("<td>"+dateFormatter.format(moon.getSet())+"</td><td>Moon set</td>");
            moon = getMoonTimes(new java.util.Date(moon.getRise().getTime()+86300000L), LONGITUDE, LATITUDE);
        }
        java.util.Collections.sort(events);
        for(String event: events) {
            System.out.println("<tr>"+event+"</tr>");
        }
    }

    private static MoonTimes getMoonTimes(String theTimeStamp, double theLongitude, double theLatitude) {
        MoonTimes moon = null;
        try {
            java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
            java.util.Date date = dateFormatter.parse(theTimeStamp);
            moon = MoonTimes.compute()
                        .on(date)       // set a date
                        .at(theLatitude, theLongitude)   // set a location
                        .execute();     // get the results
        } catch (java.text.ParseException exception) {
            System.out.println("Problem parsing date: "+exception.getMessage());
        }
        return moon;
    }

    private static MoonTimes getMoonTimes(java.util.Date theTime, double theLongitude, double theLatitude) {
        MoonTimes moon = null;
        moon = MoonTimes.compute()
                    .on(theTime)       // set a date
                    .at(theLatitude, theLongitude)   // set a location
                    .execute();     // get the results
        return moon;
    }

    private static SunTimes getSunTimes(String theTimeStamp, double theLongitude, double theLatitude) {
        SunTimes sun = null;
        try {
        java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
            java.util.Date date = dateFormatter.parse(theTimeStamp);
            sun = SunTimes.compute()
                        .on(date)       // set a date
                        .at(theLatitude, theLongitude)   // set a location
                        .execute();     // get the results
        } catch (java.text.ParseException exception) {
            System.out.println("Problem parsing date: "+exception.getMessage());
        }
        return sun;
    }
}
