import java.util.*;

public class UniqueVisitorCounter implements DataProcessor {

    @Override
    public List<String> process(List<String> rawData) throws DataProcessingException {
        if (rawData.isEmpty()) {
            throw new EmptyDataException("No raw data to process! Did all tourists are missing?");
        }

        Set<String> uniqueNames = new HashSet<>();

        for (int i = 0; i < rawData.size(); i++) {
            String entry = rawData.get(i);
            String[] parts = entry.split(":");
            if (parts.length > 1) {
                String[] details = parts[1].split(",");
                if (details.length > 0) {
                    String name = details[0].trim();
                    uniqueNames.add(name);
                }
            }
        }

        List<String> result = new ArrayList<>();
        result.add("Unique Visitors: " + uniqueNames.size());
        return result;
    }

    public static void main(String[] args) {
        UniqueVisitorCounter counter = new UniqueVisitorCounter();

        List<String> emptyList = new ArrayList<>();
        List<String> sampleData = Arrays.asList(
                "Visitor:Aashish Shrestha, Nepal",
                "Guest:Harry Shrestha, Kathmandu",
                "Visitor: Doland Trump, USA",
                "Guest: Ram Thapa, NP"
        );

        try {
            System.out.println(counter.process(emptyList));
        } catch (DataProcessingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println(counter.process(sampleData));
        } catch (DataProcessingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
