import java.util.*;

public class TourismReportGenerator {

    public static void generateOverallReport(List<TouristDataSource> dataSources, DataProcessor processor) {
        System.out.println("Generating overall tourism report...");

        for (int i = 0; i < dataSources.size(); i++) {
            TouristDataSource dataSource = dataSources.get(i);
            List<String> rawData;

            try {
                rawData = dataSource.fetchData();
            } catch (DataSourceAccessException e) {
                System.out.println("Could not fetch data from " + dataSource.getSourceName() + ": " + e.getMessage() + ". Skipping this source.");
                Throwable cause = e.getCause();
                if (cause != null) {
                    System.out.println("Reason: " + cause.getMessage());
                }
                continue;
            }

            try {
                List<String> result = processor.process(rawData);
                for (int j = 0; j < result.size(); j++) {
                    System.out.println(result.get(j));
                }
            } catch (DataProcessingException e) {
                System.out.println("Error processing data from " + dataSource.getSourceName() + ": " + e.getMessage() + ". Skipping this data.");
            } finally {
                System.out.println("Data handling from " + dataSource.getSourceName() + " completed.");
            }
        }
    }

    public static void main(String[] args) {
        List<TouristDataSource> dataSources = new ArrayList<>();
        dataSources.add(new AirportArrivalsDataSource());
        dataSources.add(new HotelRegistrationsDataSource());
        dataSources.add(new AirportArrivalsDataSource());
        dataSources.add(new HotelRegistrationsDataSource());

        DataProcessor processor = new UniqueVisitorCounter();

        generateOverallReport(dataSources, processor);
    }
}
