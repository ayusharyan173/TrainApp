import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// --- MODEL CLASS ---
class Bogie {
    String type;
    int capacity;

    public Bogie(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public String getType() { return type; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return "Bogie{type='" + type + "', capacity=" + capacity + "}";
    }
}

// --- MAIN APPLICATION ---
public class TrainApp {
    public static void main(String[] args) {
        // 1. Initialize Bogie List with multiple bogies of the same type
        List<Bogie> trainConsist = new ArrayList<>();
        trainConsist.add(new Bogie("Sleeper", 72));
        trainConsist.add(new Bogie("Sleeper", 72));
        trainConsist.add(new Bogie("AC Chair", 56));
        trainConsist.add(new Bogie("First Class", 24));
        trainConsist.add(new Bogie("AC Chair", 56));

        System.out.println("--- UC9: Grouping Bogies by Type ---");

        // 2. Stream Pipeline: Grouping bogies by their 'type'
        Map<String, List<Bogie>> groupedBogies = trainConsist.stream()
                .collect(Collectors.groupingBy(Bogie::getType));

        // 3. Display Grouped Result
        groupedBogies.forEach((type, list) -> {
            System.out.println(type + ": " + list);
        });

        // 4. Run Test Suite
        TrainGroupingTestSuite.runTests(trainConsist);
    }
}

// --- SEPARATE TEST CASE CLASS ---
class TrainGroupingTestSuite {
    public static void runTests(List<Bogie> originalList) {
        System.out.println("\n--- UC9 TEST CASE EXECUTION ---");

        testGrouping_BogiesGroupedByType(originalList);
        testGrouping_MultipleBogiesInSameGroup(originalList);
        testGrouping_DifferentBogieTypes(originalList);
        testGrouping_EmptyBogieList();
        testGrouping_SingleBogieCategory();
        testGrouping_MapContainsCorrectKeys(originalList);
        testGrouping_GroupSizeValidation(originalList);
        testGrouping_OriginalListUnchanged(originalList);
    }

    private static void testGrouping_BogiesGroupedByType(List<Bogie> list) {
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(Bogie::getType));
        System.out.println("testGrouping_BogiesGroupedByType: " + (!result.isEmpty() ? "PASSED" : "FAILED"));
    }

    private static void testGrouping_MultipleBogiesInSameGroup(List<Bogie> list) {
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(Bogie::getType));
        // Check if Sleeper has 2 entries
        boolean passed = result.get("Sleeper").size() == 2;
        System.out.println("testGrouping_MultipleBogiesInSameGroup: " + (passed ? "PASSED" : "FAILED"));
    }

    private static void testGrouping_DifferentBogieTypes(List<Bogie> list) {
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(Bogie::getType));
        boolean passed = result.containsKey("Sleeper") && result.containsKey("AC Chair") && result.containsKey("First Class");
        System.out.println("testGrouping_DifferentBogieTypes: " + (passed ? "PASSED" : "FAILED"));
    }

    private static void testGrouping_EmptyBogieList() {
        List<Bogie> emptyList = new ArrayList<>();
        Map<String, List<Bogie>> result = emptyList.stream().collect(Collectors.groupingBy(Bogie::getType));
        System.out.println("testGrouping_EmptyBogieList: " + (result.isEmpty() ? "PASSED" : "FAILED"));
    }

    private static void testGrouping_SingleBogieCategory() {
        List<Bogie> list = List.of(new Bogie("Sleeper", 72), new Bogie("Sleeper", 72));
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(Bogie::getType));
        System.out.println("testGrouping_SingleBogieCategory: " + (result.size() == 1 ? "PASSED" : "FAILED"));
    }

    private static void testGrouping_MapContainsCorrectKeys(List<Bogie> list) {
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(Bogie::getType));
        boolean passed = result.keySet().containsAll(List.of("Sleeper", "AC Chair", "First Class"));
        System.out.println("testGrouping_MapContainsCorrectKeys: " + (passed ? "PASSED" : "FAILED"));
    }

    private static void testGrouping_GroupSizeValidation(List<Bogie> list) {
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(Bogie::getType));
        int sleeperCount = result.get("Sleeper").size();
        System.out.println("testGrouping_GroupSizeValidation: " + (sleeperCount == 2 ? "PASSED" : "FAILED"));
    }

    private static void testGrouping_OriginalListUnchanged(List<Bogie> list) {
        int initialSize = list.size();
        list.stream().collect(Collectors.groupingBy(Bogie::getType));
        System.out.println("testGrouping_OriginalListUnchanged: " + (list.size() == initialSize ? "PASSED" : "FAILED"));
    }
}