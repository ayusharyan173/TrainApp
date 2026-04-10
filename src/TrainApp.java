import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// --- MODEL CLASS ---
class Bogie {
    String name;
    int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public int getCapacity() { return capacity; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (" + capacity + " seats)";
    }
}

// --- MAIN APPLICATION ---
public class TrainApp {
    public static void main(String[] args) {
        // 1. Initialize Bogie List (Reuse from UC7)
        List<Bogie> trainConsist = new ArrayList<>();
        trainConsist.add(new Bogie("Sleeper", 72));
        trainConsist.add(new Bogie("AC Chair", 56));
        trainConsist.add(new Bogie("First Class", 24));
        trainConsist.add(new Bogie("General", 90));

        System.out.println("--- UC8: Train Consist Management ---");
        System.out.println("Original Consist: " + trainConsist);

        // 2. Stream Filtering (Capacity > 60)
        List<Bogie> filteredList = trainConsist.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());

        System.out.println("Filtered High-Capacity Bogies: " + filteredList);

        // 3. Run Validation Suite
        TrainTestSuite.runTests(trainConsist);
    }
}

// --- SEPARATE TEST CASE CLASS ---
class TrainTestSuite {
    public static void runTests(List<Bogie> originalList) {
        System.out.println("\n--- UC8 TEST CASE EXECUTION ---");

        testFilter_CapacityGreaterThanThreshold(originalList);
        testFilter_CapacityEqualToThreshold();
        testFilter_CapacityLessThanThreshold();
        testFilter_MultipleBogiesMatching(originalList);
        testFilter_NoBogiesMatching(originalList);
        testFilter_AllBogiesMatching(originalList);
        testFilter_EmptyBogieList();
        testFilter_OriginalListUnchanged(originalList);
    }

    private static void testFilter_CapacityGreaterThanThreshold(List<Bogie> list) {
        List<Bogie> result = list.stream().filter(b -> b.getCapacity() > 70).collect(Collectors.toList());
        boolean passed = result.stream().allMatch(b -> b.getCapacity() > 70) && !result.isEmpty();
        System.out.println("testFilter_CapacityGreaterThanThreshold: " + (passed ? "PASSED" : "FAILED"));
    }

    private static void testFilter_CapacityEqualToThreshold() {
        List<Bogie> list = List.of(new Bogie("ThresholdBogie", 70));
        List<Bogie> result = list.stream().filter(b -> b.getCapacity() > 70).collect(Collectors.toList());
        System.out.println("testFilter_CapacityEqualToThreshold: " + (result.isEmpty() ? "PASSED" : "FAILED"));
    }

    private static void testFilter_CapacityLessThanThreshold() {
        List<Bogie> list = List.of(new Bogie("LowCap", 69));
        List<Bogie> result = list.stream().filter(b -> b.getCapacity() > 70).collect(Collectors.toList());
        System.out.println("testFilter_CapacityLessThanThreshold: " + (result.isEmpty() ? "PASSED" : "FAILED"));
    }

    private static void testFilter_MultipleBogiesMatching(List<Bogie> list) {
        List<Bogie> result = list.stream().filter(b -> b.getCapacity() > 50).collect(Collectors.toList());
        // Sleeper(72), AC Chair(56), General(90) should match
        System.out.println("testFilter_MultipleBogiesMatching: " + (result.size() >= 2 ? "PASSED" : "FAILED"));
    }

    private static void testFilter_NoBogiesMatching(List<Bogie> list) {
        List<Bogie> result = list.stream().filter(b -> b.getCapacity() > 200).collect(Collectors.toList());
        System.out.println("testFilter_NoBogiesMatching: " + (result.isEmpty() ? "PASSED" : "FAILED"));
    }

    private static void testFilter_AllBogiesMatching(List<Bogie> list) {
        List<Bogie> result = list.stream().filter(b -> b.getCapacity() > 0).collect(Collectors.toList());
        System.out.println("testFilter_AllBogiesMatching: " + (result.size() == list.size() ? "PASSED" : "FAILED"));
    }

    private static void testFilter_EmptyBogieList() {
        List<Bogie> emptyList = new ArrayList<>();
        try {
            emptyList.stream().filter(b -> b.getCapacity() > 70).collect(Collectors.toList());
            System.out.println("testFilter_EmptyBogieList: PASSED");
        } catch (Exception e) {
            System.out.println("testFilter_EmptyBogieList: FAILED");
        }
    }

    private static void testFilter_OriginalListUnchanged(List<Bogie> list) {
        int initialSize = list.size();
        list.stream().filter(b -> b.getCapacity() > 60).collect(Collectors.toList());
        System.out.println("testFilter_OriginalListUnchanged: " + (list.size() == initialSize ? "PASSED" : "FAILED"));
    }
}