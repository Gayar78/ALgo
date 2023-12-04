import java.util.ArrayList;
import model.*;

public class TestMainDist {
    public static void main(String[] args) {
        // Create a list of delivery addresses.
        GPS first = new GPS(/*co1*/, /*co2*/);//double values x , y // latitude , longitude 
        GPS second = new GPS(/*co3*/, /*co4*/);
        GPS third = new GPS(/*co5*/, /*co6*/);
        GPS fourth = new GPS(/*co6*/, /*co7*/);

        //double distancePierreSamuel = pierre1.distanceKM(samuel);
        //System.out.println(distancePierreSamuel+"km between Pierre and Samuel (bird flight)");

        // Create a list of orders.
        ArrayList<Order> orders = new ArrayList<>();
        Order order1 = new Order(first, 10);//int values
        Order order2 = new Order(second, 5);
        Order order3 = new Order(third, 4);
        Order order4 = new Order(fourth, 3);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        // Create a delivery person.
        DeliveryPerson deliveryPerson = new DeliveryPerson(1, "John", "Doe", true);
        //id = 1
        //lastName = John
        //fistName = Doe
        //available = true

        // Get the best delivery addresses.
        ArrayList<GPS> deliveredAddresses = deliveryPerson.bestDelivery(orders, new GPS(/*coPIZZA_x*/, /*coPIZZA_y*/));

        // Print the addresses in the correct order
        for (GPS deliveredAddress : deliveredAddresses) {
            System.out.println(deliveredAddress);
        }
    }
}
