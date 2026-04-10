import java.util.ArrayList;
import java.util.List;

// --- MODEL CLASS ---
class Bogie {
    String type;
    int capacity;

    public Bogie(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public int getCapacity() { return capacity; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return type + " (" + capacity + " seats)";
    }
}

// --- MAIN APPLICATION ---
public class TrainApp {
    public static void main(String[] args) {
        // 1. Initialize Bogie List
        List<Bogie> trainConsist = new ArrayList<>();
        trainConsist.add(new Bogie("Sleeper", 72));
        trainConsist.add(new Bogie("Sleeper", 72));
        trainConsist.add(new Bogie("AC Chair", 56));
        trainConsist.add(new Bogie("First Class", 24));

        System.out.println("--- UC10: Total Train Capacity Calculation ---");
        System.out.println("Consist: " + trainConsist);

        // 2. Stream Pipeline: map() transformation and reduce() aggregation
        int totalCapacity = trainConsist.stream()
                .map(Bogie::getCapacity)        // Extracts numeric values
                .reduce(0, Integer::sum);      // Sums extracted values

        // 3. Display Result
        System.out.println("Total Seating Capacity: " + totalCapacity);

        // 4. Run Comprehensive Test Suite
        TrainReductionTestSuite.runTests(trainConsist);
    }
}

// --- DEDICATED TEST CASE CLASS ---
class TrainReductionTestSuite {
    public static void runTests(List<Bogie> originalList) {
        System.out.println("\n--- UC10 TEST CASE EXECUTION ---");

        testReduce_TotalSeatCalculation(originalList);
        testReduce_MultipleBogiesAggregation(originalList);
        testReduce_SingleBogieCapacity();
        testReduce_EmptyBogieList();
        testReduce_CorrectCapacityExtraction(originalList);
        testReduce_AllBogiesIncluded(originalList);
        testReduce_OriginalListUnchanged(originalList);
    }

    private static void testReduce_TotalSeatCalculation(List<Bogie> list) {
        int expected = list.stream().mapToInt(Bogie::getCapacity).sum();
        int total = list.stream().map(Bogie::getCapacity).reduce(0, Integer::sum);
        System.out.println("testReduce_TotalSeatCalculation: " + (total == expected ? "PASSED" : "FAILED"));
    }

    private static void testReduce_MultipleBogiesAggregation(List<Bogie> list) {
        int total = list.stream().map(Bogie::getCapacity).reduce(0, Integer::sum);
        System.out.println("testReduce_MultipleBogiesAggregation: " + (total > 0 && list.size() > 1 ? "PASSED" : "FAILED"));
    }

    private static void testReduce_SingleBogieCapacity() {
        List<Bogie> singleList = List.of(new Bogie("Solo", 50));
        int total = singleList.stream().map(Bogie::getCapacity).reduce(0, Integer::sum);
        System.out.println("testReduce_SingleBogieCapacity: " + (total == 50 ? "PASSED" : "FAILED"));
    }

    private static void testReduce_EmptyBogieList() {
        List<Bogie> emptyList = new ArrayList<>();
        int total = emptyList.stream().map(Bogie::getCapacity).reduce(0, Integer::sum);
        System.out.println("testReduce_EmptyBogieList: " + (total == 0 ? "PASSED" : "FAILED"));
    }

    private static void testReduce_CorrectCapacityExtraction(List<Bogie> list) {
        int firstActual = list.get(0).getCapacity();
        int firstMapped = list.stream().map(Bogie::getCapacity).findFirst().orElse(-1);
        System.out.println("testReduce_CorrectCapacityExtraction: " + (firstActual == firstMapped ? "PASSED" : "FAILED"));
    }

    private static void testReduce_AllBogiesIncluded(List<Bogie> list) {
        long count = list.stream().map(Bogie::getCapacity).count();
        System.out.println("testReduce_AllBogiesIncluded: " + (count == list.size() ? "PASSED" : "FAILED"));
    }

    private static void testReduce_OriginalListUnchanged(List<Bogie> list) {
        int initialSize = list.size();
        list.stream().map(Bogie::getCapacity).reduce(0, Integer::sum);
        System.out.println("testReduce_OriginalListUnchanged: " + (list.size() == initialSize ? "PASSED" : "FAILED"));
    }
}