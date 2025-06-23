import java.util.*;

public class HotelRegistrationsDataSource extends TouristDataSource {

    public HotelRegistrationsDataSource() {
        super("Kathmandu Hotels Registrations");
    }

    @Override
    public List<String> fetchData() throws DataSourceAccessException {
        try {
            if (getSourceName().contains("Hotels") && Math.random() < 0.2) {
                throw new AuthenticationFailedException("Hotel API authentication failed! Does someone forget the password again?");
            }
            List<String> data = new ArrayList<>();
            data.add("Hotel: Hotel the city Corner, Guest: Ram Thapa, NP");
            data.add("Hotel: Dulhikhel Lodge Resort, Guest: Alice Smith, AU");
            return data;
        } catch (AuthenticationFailedException e) {
            throw new DataSourceAccessException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        HotelRegistrationsDataSource source = new HotelRegistrationsDataSource();
        try {
            List<String> registrations = source.fetchData();
            for (String entry : registrations) {
                System.out.println(entry);
            }
        } catch (DataSourceAccessException e) {
            System.out.println("Failed to fetch hotel data: " + e.getMessage());
        }
    }
}
