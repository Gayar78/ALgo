import java.util.ArrayList;
import model.*;

public class testMainDist {
    public static void main(String[] args) {
        // Create a list of delivery addresses..
        GPS Samuel = new GPS(48.79, 2.15);
        GPS Pierre1 = new GPS(48.74, 2.11);
        GPS Pierre2 = new GPS(48.74, 2.11);
        GPS Buc = new GPS(48.77, 2.12);

        //double distancePierreSamuel = Pierre1.distanceKM(Samuel);
        //System.out.println(distancePierreSamuel+"km between Pierre and Samuel (bird flight)");

        // Create a list of Orders.
        ArrayList<Order> Orders = new ArrayList<>();
        Order Order1 = new Order(Samuel, 10);
        Order Order2 = new Order(Pierre1, 5);
        Order Order3 = new Order(Pierre2, 4);
        Order Order4 = new Order(Buc, 3);
        Orders.add(Order1);
        Orders.add(Order2);
        Orders.add(Order3);
        Orders.add(Order4);


        // Create a delivery man for the test of the bestDelivery method.
        DeliveryMan deliveryMan = new DeliveryMan(1, "John", "Doe", true);
        System.out.println(" ");
        System.out.println("REDUCTIONLESS DELIVERY WITH FAST BACK");
        // Get the best delivery patern in deliveryAddresses
        ArrayList<GPS> deliveredAddresses = deliveryMan.bestDelivery(Orders, new GPS(48.73, 2.08));
        // Print the delivered addresses.
        for (GPS deliveredAddress : deliveredAddresses) {
            System.out.println(deliveredAddress);
        }
        System.out.println(" ");
        System.out.println("------------------------------------------------");


        //Creat a delivery man for the test of the fast delivery method.
        DeliveryMan deliveryMan2 = new DeliveryMan(2, "Jane", "Doe", true);
        System.out.println(" ");
        System.out.println("FAST DELIVERY WITH FAST BACK");
        // Get the fastest delivery patern in deliveryAddresses2
        ArrayList<GPS> deliveredAddresses2 = deliveryMan2.FastDelivery(Orders, new GPS(48.73, 2.08));
        // Print the delivered addresses.
        for (GPS deliveredAddress : deliveredAddresses2) {
            System.out.println(deliveredAddress);
        }
        System.out.println(" ");
        System.out.println("------------------------------------------------");


        //Creat a delivery man for the test of the rest_time delivery method.
        DeliveryMan deliveryMan3 = new DeliveryMan(2, "Joe", "Doe", true);
        System.out.println(" ");
        System.out.println("FAST DELIVERY WITH FAST BACK");
        // Get the best restTime delivery patern in deliveryAddresses3
        ArrayList<GPS> deliveredAddresses3 = deliveryMan2.RestTimeDelivery(Orders, new GPS(48.73, 2.08));
        // Print the delivered addresses.
        for (GPS deliveredAddress : deliveredAddresses3) {
            System.out.println(deliveredAddress);
        }
        System.out.println(" ");
        System.out.println("------------------------------------------------");
    }
}