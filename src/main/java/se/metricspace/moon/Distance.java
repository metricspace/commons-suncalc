package se.metricspace.moon;

import org.shredzone.commons.suncalc.MoonPosition;

public class Distance {
    public static void main(String[] args) {
        final double LONGITUDE=18.05;
        final double LATITUDE = 59.33333;
        java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[] times = {
            "2018-08-04 20:00",
            "2018-08-11 12:00",
            "2018-08-18 10:00",
            "2018-08-26 14:00",
            "2018-09-03 05:00",
            "2018-09-09 20:00",
            "2018-09-17 01:00",
            "2018-09-25 05:00",
            "2018-10-02 12:00",
            "2018-10-09 06:00",
            "2018-10-16 20:00",
            "2018-10-24 19:00"
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
