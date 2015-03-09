package org.argszero.ec2.gistalk.service;

import org.springframework.stereotype.Component;

/**
 * Created by shaoaq on 3/2/15.
 */
@Component
public class DistanceService {
    private static final double EARTH_RADIUS = 6378.137;

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }

}
