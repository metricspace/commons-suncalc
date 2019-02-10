package se.metricspace.moon;

import org.shredzone.commons.suncalc.MoonPosition;

public class Direction {
    public static void main(String[] args) {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.text.NumberFormat numberFormat = new java.text.DecimalFormat("#.###");
        java.util.Date date = new java.util.Date();
        String initialtime = dateFormat.format(date).substring(0,14)+"00";
        try {
            date = dateFormat.parse(initialtime);
        } catch (java.text.ParseException exception) {
            System.out.println("Some problem parsing date:"+exception.getMessage());
        }
        System.out.println("start: "+initialtime);
        
        for(int counter = 0; counter<1800; counter++ ) {
            MoonPosition moon = compute(date);
            if(null!=moon && moon.getAltitude()>0.999) {
                String direction = " (N)";
                if(moon.getAzimuth()>44.999) {
                    direction = " (E)";
                }
                if(moon.getAzimuth()>134.999) {
                    direction = " (S)";
                }
                if(moon.getAzimuth()>224.999) {
                    direction = " (W)";
                }
                if(moon.getAzimuth()>314.999) {
                    direction = " (N)";
                }
                System.out.println("<tr><td>"+dateFormat.format(date)+"</td><td>"+numberFormat.format(moon.getAltitude())+"&deg;</td><td>"+numberFormat.format(moon.getAzimuth())+"&deg;"+direction+"</td></tr>");
            }
            date = new java.util.Date(date.getTime()+1200000L); // 20 minutes forward ...
        }
    }
    
    private static MoonPosition compute(java.util.Date date) {
        MoonPosition moon = null;
        double longitude=18.05;
        double latitude = 59.33333;
        moon = MoonPosition.compute()
                        .on(date)       // set a date
                        .at(latitude, longitude)   // set a location
                        .execute();     // get the results
        return moon;
    }
}
