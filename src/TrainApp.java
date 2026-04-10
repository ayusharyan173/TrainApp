import java.util.LinkedHashSet;

public class TrainApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Use LinkedHashSet to maintain insertion order
        LinkedHashSet<String> bogieIds = new LinkedHashSet<>();

        // Add bogie IDs in required order
        bogieIds.add("BG104");
        bogieIds.add("BG103");
        bogieIds.add("BG102");
        bogieIds.add("BG101");

        // Display in required format
        System.out.print("Bogie IDs After Insertion: (");

        int count = 0;
        for (String id : bogieIds) {
            System.out.print(id);
            count++;
            if (count < bogieIds.size()) {
                System.out.print(", ");
            }
        }

        System.out.println(")");
    }
}