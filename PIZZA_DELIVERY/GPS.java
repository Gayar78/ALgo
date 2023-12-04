public class GPS {
    private double x;
    private double y;

    /**
     * Constructor for GPS coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public GPS(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x-coordinate.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Get the y-coordinate.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Calculate the distance between two GPS coordinates using the Haversine formula.
     *
     * @param otherGPS The other GPS coordinates.
     * @return The distance in kilometers.
     */
    public double distanceKM(GPS otherGPS) {
        // Earth's radius in kilometers
        double earthRadius = 6371;

        // Convert latitudes and longitudes from degrees to radians
        double lat1 = Math.toRadians(this.getY());
        double lon1 = Math.toRadians(this.getX());
        double lat2 = Math.toRadians(otherGPS.getY());
        double lon2 = Math.toRadians(otherGPS.getX());

        // Calculate differences in latitudes and longitudes
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Haversine formula for distance calculation
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers
        double distance = earthRadius * c;

        return distance;
    }

    /**
     * String representation of GPS coordinates.
     *
     * @return A string representation of the coordinates.
     */
    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + "]";
    }
}
