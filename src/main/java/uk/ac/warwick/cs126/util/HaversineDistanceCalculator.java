package uk.ac.warwick.cs126.util;

public class HaversineDistanceCalculator {

    private final static float R = 6372.8f;
    private final static float kilometresInAMile = 1.609344f;

    private static float oneRound(double val) {
      float roundedVal = Math.round(val * 10);
      return (roundedVal / 10);
    }

    private static double distance(float lat1, float lon1, float lat2, float lon2) {
      double latitude1 = Math.toRadians(lat1);
      double latitude2 = Math.toRadians(lat2);
      double longitude1 = Math.toRadians(lon1);
      double longitude2 = Math.toRadians(lon2);
      double a = Math.pow(Math.sin((latitude2 - latitude1) / 2), 2) + (Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin((longitude2 - longitude1) / 2), 2));
      double c = 2 * (Math.asin(Math.sqrt(a)));
      return c;
    }

    public static float inKilometres(float lat1, float lon1, float lat2, float lon2) {
        float d = oneRound(R * distance(lat1, lon1, lat2, lon2));
        return d;
    }

    public static float inMiles(float lat1, float lon1, float lat2, float lon2) {
        float d = oneRound((R / kilometresInAMile) * distance(lat1, lon1, lat2, lon2));
        return d;
    }

}
