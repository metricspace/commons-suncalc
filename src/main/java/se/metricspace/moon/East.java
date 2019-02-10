/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.metricspace.moon;

import org.shredzone.commons.suncalc.MoonPosition;

/**
 *
 * @author mange
 */
public class East {
    public static void main(String[] args) {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.text.NumberFormat numberFormat = new java.text.DecimalFormat("#.###");
        java.util.Date date = new java.util.Date();
        
        for(int day = 0; day<14; day ++ ) {
            for(int hour = 0; hour < 24; hour ++ ) {
                for(int q = 0; q < 4 ; q++ ) {
                    MoonPosition moon = compute(date);
                    if(null!=moon && moon.getAltitude()>0.49 && moon.getAzimuth()>54.9 && moon.getAzimuth()<125.1) {
                        System.out.println("<tr><td>"+dateFormat.format(date)+"</td><td>"+numberFormat.format(moon.getAltitude())+"&deg;</td><td>"+numberFormat.format(moon.getAzimuth())+"&deg;</td></tr>");
                    }
                    date = new java.util.Date(date.getTime()+1200000L); // 20 minutes forward ...
                }
            }
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
