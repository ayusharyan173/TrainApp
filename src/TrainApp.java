import java.util.LinkedHashSet;
import java.util.Set;

public class TrainApp {
    public static void main(String[] args) {
        // 1. Create a LinkedHashSet to represent the train formation
        // This ensures uniqueness AND preserves insertion order
        Set<String> trainFormation = new LinkedHashSet<>();

        // 2. Attach initial bogies
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        System.out.println("Current Train Formation: " + trainFormation);

        // 3. Attempt to attach a duplicate bogie intentionally
        System.out.println("\nAttempting to add duplicate bogie: 'Sleeper'...");
        boolean isAdded = trainFormation.add("Sleeper");

        if (!isAdded) {
            System.out.println("Alert: Bogie 'Sleeper' is already part of the consist. Duplicate ignored.");
        }

        // 4. Display the final formation order
        // Notice the order remains Engine -> Sleeper -> Cargo -> Guard
        System.out.println("\nFinal Formation Order (Duplicates Removed):");
        System.out.println(trainFormation);
    }
}