import java.util.HashMap;
import java.util.Map;

public class TrainApp {
    public static void main(String[] args) {
        // 1. Create a HashMap to store bogie-capacity mapping
        // Key: Bogie Name (String), Value: Capacity (Integer)
        HashMap<String, Integer> bogieCapacities = new HashMap<>();

        // 2. Use put() to map each bogie to its capacity
        bogieCapacities.put("Sleeper", 72);
        bogieCapacities.put("AC Chair", 56);
        bogieCapacities.put("First Class", 24);
        bogieCapacities.put("Cargo-Rectangular", 5000); // 5000kg load
        bogieCapacities.put("Cargo-Cylindrical", 3000); // 3000L load

        System.out.println("--- Bogie Capacity Mapping Created ---");

        // 3. Iterate over the map using entrySet()
        // This allows us to access both the Key and the Value simultaneously
        System.out.println("Bogie Name \t | \t Capacity");
        System.out.println("------------------------------------");

        for (Map.Entry<String, Integer> entry : bogieCapacities.entrySet()) {
            String bogie = entry.getKey();
            Integer capacity = entry.getValue();
            System.out.println(bogie + " \t | \t " + capacity);
        }

        // 4. Demonstrate Fast Lookup
        String searchBogie = "AC Chair";
        if(bogieCapacities.containsKey(searchBogie)) {
            System.out.println("\nFast Lookup: The capacity of " + searchBogie + " is " + bogieCapacities.get(searchBogie));
        }
    }
}