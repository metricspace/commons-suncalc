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
public class North {
    public static void main(String[] args) {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.text.NumberFormat numberFormat = new java.text.DecimalFormat("#.###");
        java.util.Date date = new java.util.Date();
        
        for(int day = 0; day<14; day ++ ) {
            for(int hour = 0; hour < 24; hour ++ ) {
                for(int q = 0; q < 4 ; q++ ) {
                    MoonPosition moon = compute(date);
                    if(null!=moon && moon.getAltitude()>0.1 && moon.getAzimuth()>234.9 && moon.getAzimuth()<305.1) {
                        System.out.println(dateFormat.format(date)+", Height: "+numberFormat.format(moon.getAltitude())+", Direction: "+numberFormat.format(moon.getAzimuth()));
                    }
                    date = new java.util.Date(date.getTime()+900000L); // 15 minutes forward ...
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
