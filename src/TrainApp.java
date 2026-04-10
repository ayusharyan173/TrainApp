import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 1. Create a Bogie class with fields name and capacity
class Bogie {
    String name;
    int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    // Getter methods for the Comparator to use
    public String getName() { return name; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return name + " (" + capacity + " seats)";
    }
}

public class TrainApp {
    public static void main(String[] args) {
        // 2. Create a List<Bogie> to store passenger bogies
        List<Bogie> passengerBogies = new ArrayList<>();

        // 3. Add bogies with different capacities
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("First Class", 24));
        passengerBogies.add(new Bogie("AC Chair", 56));

        System.out.println("Before Sorting: " + passengerBogies);

        // 4. Use Comparator.comparingInt() to define sorting based on capacity
        // This creates a rule: "Look at the capacity field of each Bogie to decide order"
        passengerBogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        // 5. Display the sorted bogies
        System.out.println("\n--- Bogies Sorted by Capacity (Ascending) ---");
        for (Bogie b : passengerBogies) {
            System.out.println(b);
        }

        // Bonus: Sorting in Descending Order (High to Low)
        passengerBogies.sort(Comparator.comparingInt(Bogie::getCapacity).reversed());
        System.out.println("\n--- Bogies Sorted by Capacity (Descending/Priority) ---");
        passengerBogies.forEach(System.out::println);
    }
}