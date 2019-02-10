package se.metricspace.moon;

import org.shredzone.commons.suncalc.MoonPosition;

public class Distance {
    public static void main(String[] args) {
        final double LONGITUDE=18.05;
        final double LATITUDE = 59.33333;
        java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[] times = {
"2019-01-06 02:00",
"2019-01-14 08:00",
"2019-01-21 06:00",
"2019-01-27 22:00",
"2019-02-04 22:00",
"2019-02-12 23:00",
"2019-02-19 17:00",
"2019-02-26 12:00"
        };
        for(String time:times) {
            try {
                java.util.Date date =  dateFormatter.parse(time);
                MoonPosition moon = MoonPosition.compute()
                        .on(date)       // set a date
                        .at(LATITUDE, LONGITUDE)   // set a location
                        .execute();     // get the results
                System.out.println("<tr><td>"+dateFormatter.format(date)+"</td><td></td><td>"+moon.getDistance()+" km</td></tr>");
            } catch(java.text.ParseException exception) {
                System.out.println("ParseException: "+exception.getMessage());
            }
        }
    }
}
