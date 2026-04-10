import java.util.LinkedList;

public class TrainApp {
    public static void main(String[] args) {

        System.out.println("=== UC4 - Maintain Ordered Bogie IDs ===");

        // 1. Create a LinkedList for the consist
        LinkedList<String> train = new LinkedList<>();

        // 2. Add initial bogies
        train.add("Engine");
        train.add("Sleeper");
        train.add("AC");
        train.add("Cargo");
        train.add("Guard");

        System.out.println("Initial Train Consist: " + train);

        // 3. Insert Pantry Car at position 2 (Index 2)
        // In Java LinkedList, index 2 is the 3rd position
        train.add(2, "Pantry Car");
        System.out.println("After inserting \"Pantry Car\" at position 2: " + train);

        // 4. Remove the first and last bogie
        train.removeFirst();
        train.removeLast();

        // 5. Display the final ordered train consist
        System.out.println("After Removing First & Last Bogie: " + train);
    }
}