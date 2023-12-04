import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DeliveryPerson {
    private int idDP;
    private String lastNameDP;
    private String firstNameDP;
    private boolean available;
    private final int STORAGE_DP = 5;
    private final int AVGspeed = 45;

    /**
     * Constructor for a DeliveryPerson.
     *
     * @param id         The unique identifier for the delivery person.
     * @param lastName   The last name of the delivery person.
     * @param firstName  The first name of the delivery person.
     * @param available  The availability status of the delivery person.
     */
    public DeliveryPerson(int id, String lastName, String firstName, boolean available) {
        this.idDP = id;
        this.lastNameDP = lastName;
        this.firstNameDP = firstName;
        this.available = available;
    }

    /**
     * Get the unique identifier of the delivery person.
     *
     * @return The unique identifier.
     */
    public int getIdDP() {
        return this.idDP;
    }

    /**
     * Get the last name of the delivery person.
     *
     * @return The last name.
     */
    public String getLastName() {
        return this.lastNameDP;
    }

    /**
     * Get the first name of the delivery person.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return this.firstNameDP;
    }

    /**
     * Check the availability status of the delivery person.
     *
     * @return True if available, false otherwise.
     */
    public boolean getAvailable() {
        return this.available;
    }

    /**
     * Toggle the availability status of the delivery person.
     */
    public void changeAvailable() {
        this.available = !this.available;
    }

    /**
     * String representation of the DeliveryPerson object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "DeliveryPerson [idDP=" + idDP + ", lastNameDP=" + lastNameDP + ", firstNameDP=" + firstNameDP + ", available=" + available + "]";
    }

    /**
     * Determine the best delivery route for the delivery person based on orders and current position.
     *
     * @param orders   The list of orders to be delivered.
     * @param position The current GPS position of the delivery person.
     * @return An ArrayList of GPS coordinates representing the optimized delivery route.
     */
    public ArrayList<GPS> bestDelivery(ArrayList<Order> orders, GPS position) {
        // Create a copy of the orders list to avoid modifying the original
        ArrayList<Order> ordersCopy = new ArrayList<>(orders);

        // Calculate the distance between the delivery person's current position and all GPS coordinates of the orders
        ordersCopy.sort(Comparator.comparingDouble(order -> position.distanceKM(order.getGPS())));

        // Select orders based on distance and remaining time
        ArrayList<GPS> sortedGPS = new ArrayList<>();
        double remainingTime = 0;

        for (Order order : ordersCopy) {
            double distance = position.distanceKM(order.getGPS());
            double deliveryTime = distance / AVGspeed * 60; // Delivery time in minutes
            remainingTime += order.getRestTime();

            // Check if the order can be delivered before its remaining time reaches zero
            if (remainingTime <= deliveryTime) {
                sortedGPS.add(order.getGPS());
            } else {
                break; // Exit the loop if the order cannot be delivered on time
            }
        }

        return sortedGPS;
    }
}
