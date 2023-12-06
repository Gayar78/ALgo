import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DeliveryMan {
    private int idDM;
    private String nameDM;
    private String lastNameDM;
    private boolean available;
    private final int STORAGE_DM = 5;
    private final int AVGspeed = 45;

    public DeliveryMan(int id, String name, String lastName, boolean available) {
        this.idDM = id;
        this.nameDM = name;
        this.lastNameDM = lastName;
        this.available = available;
    }
    public int getIdDM(){
        return this.idDM;
    }
    public String getNom(){
        return this.nameDM;
    }
    public String getPrenom(){
        return this.lastNameDM;
    }
    public boolean getAvailable(){
        return this.available;
    }
    public void changeAvailable(){
        this.available = !this.available;
    }
    public String toString() {
        return "DeliveryMan [idDM=" + idDM + ", nameDM=" + nameDM + ", lastNameDM=" + lastNameDM + ", available=" + available + "]";
    }
    //######## BEST DELIVERY ########
    public ArrayList<GPS> bestDelivery(ArrayList<Order> Orders, GPS position) {
        return bestDeliveryRecursive(new ArrayList<>(Orders), position, 0);
    }

    private ArrayList<GPS> bestDeliveryRecursive(List<Order> Orders, GPS position, double elapsedTime) {
        if (Orders.isEmpty()) {
            return new ArrayList<>(); // no more Orders to deliver
        }
        Orders.sort(Comparator.comparingDouble(Order -> position.distanceKM(Order.getGPS())));
        Order nextOrder = Orders.get(0);
        double distance = position.distanceKM(nextOrder.getGPS());
        double deliveryTime = distance / AVGspeed * 60; // Temps de livraison en minutes
        double remainingTime = nextOrder.getRestTime() - elapsedTime;
        /* DEBUG
        System.out.println("Temps restant: " + formatDouble(remainingTime, 2));
        System.out.println("Temps de livraison: " + formatDouble(deliveryTime, 2));
        */
        ArrayList<GPS> result = new ArrayList<>();
        if (remainingTime > 0) {
            // the Order can't be delivered on time
            result.add(nextOrder.getGPS());
            // create a new list of Orders without the first one
            List<Order> remainingOrders = new ArrayList<>(Orders.subList(1, Orders.size()));

            // recution to iterate on the remaining Orders
            ArrayList<GPS> remainingDelivery = bestDeliveryRecursive(remainingOrders, nextOrder.getGPS(), elapsedTime + deliveryTime);
            result.addAll(remainingDelivery);
        } else {
            // the Order cannot be delivered on time
            result.addAll(bestDeliveryRecursive(Orders.subList(1, Orders.size()), position, elapsedTime));
            result.add(nextOrder.getGPS());
        }
        return result;
    }

    //######## FAST DELIVERY ########
    public ArrayList<GPS> FastDelivery(ArrayList<Order> Orders, GPS position) {
        ArrayList<Order> copie = new ArrayList<>(Orders);
        ArrayList<GPS> result = new ArrayList<>();
        while (!copie.isEmpty()) {
            int minDistanceIndex = 0;
            double minDistance = Double.MAX_VALUE;
            for (int i = 0; i < copie.size(); i++) {
                double distance = position.distanceKM(copie.get(i).getGPS());
                if (distance < minDistance) {
                    minDistance = distance;
                    minDistanceIndex = i;
                }
            }
            result.add(copie.get(minDistanceIndex).getGPS());
            position = copie.get(minDistanceIndex).getGPS(); // value update for the next iteration
            copie.remove(minDistanceIndex);
        }
        return result;
    }

    //######## REST_TIME DELIVERY #########
    public ArrayList<GPS> RestTimeDelivery(ArrayList<Order> Orders, GPS position) {
        ArrayList<Order> copie = new ArrayList<>(Orders);
        ArrayList<GPS> result = new ArrayList<>();
        while (!copie.isEmpty()) {
            int minTimeIndex = 0;
            double minTime = Double.MAX_VALUE;
            for (int i = 0; i < copie.size(); i++) {
                double time = copie.get(i).getRestTime();
                if (time < minTime) {
                    minTime = time;
                    minTimeIndex = i;
                }
            }
            result.add(copie.get(minTimeIndex).getGPS());
            position = copie.get(minTimeIndex).getGPS(); // value update for the next iteration
            copie.remove(minTimeIndex);
        }
        return result;
    }
    //-------------------------------------------------------------------------

    //######## UTILS (DEBUG) ########
    //Arround float number to 2 decimal if : decimalPlaces = 2
    //Exemple : 2.123456789 -> 2.12
    public static String formatDouble(double number, int decimalPlaces) {
        StringBuilder patternBuilder = new StringBuilder("#.");
        for(int i = 0; i < decimalPlaces; i++) {
            patternBuilder.append("#");
        }
        String pattern = patternBuilder.toString();
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number);
    }
}