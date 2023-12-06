public class Order {
    private GPS GPScoordinates;
    private int restTime;

    /**
     * Constructor for an Order..
     *
     * @param GPScoordinates The GPS coordinates of the order.
     * @param restTime       The remaining time for the order to be delivered.
     */
    public Order(GPS GPScoordinates, int restTime) {
        this.GPScoordinates = GPScoordinates;
        this.restTime = restTime;
    }

    /**
     * Get the GPS coordinates of the order.
     *
     * @return The GPS coordinates.
     */
    public GPS getGPS() {
        return GPScoordinates;
    }

    /**
     * Get the remaining time for the order to be delivered.
     *
     * @return The remaining time.
     */
    public int getRestTime() {
        return restTime;
    }

    /**
     * String representation of the Order object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Order [GPS=" + GPScoordinates + ", restTime=" + restTime + "]";
    }
}
