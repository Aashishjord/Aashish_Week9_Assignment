class SameLocationException extends Exception {
    public SameLocationException(String message) {
        super(message);
    }
}

class InvalidRouteException extends Exception {
    public InvalidRouteException(String message) {
        super(message); 
    }
}

interface RouteValidator {
    boolean isValidCommuteRoute(String origin, String destination, double distanceKm)
        throws InvalidRouteException, SameLocationException;
}

public class KathmanduTrafficValidator implements RouteValidator {

    @Override
    public boolean isValidCommuteRoute(String origin, String destination, double distanceKm)
        throws InvalidRouteException, SameLocationException {

        if (origin.equalsIgnoreCase(destination)) {
            throw new SameLocationException("Origin and destination cannot be the same! Are you just spinning in circles, Damodar?");
        }

        if (distanceKm < 0.1 || distanceKm > 30) {
            throw new InvalidRouteException("Distance " + distanceKm + "km is unrealistic for Kathmandu commute!");
        }

        return true;
    }
    public static void main(String[] args) {
        KathmanduTrafficValidator validator = new KathmanduTrafficValidator();

        String[][] testRoutes = {
            {"Thamel", "Kathmandu", "5.0"},
            {"Lalitpur", "Bhaktapur", "35.0"},
            {"Kalanki", "Kalanki", "0.0"},
            {"Baneshwor", "Jawalakhel", "7.5"},
            {"Malpi ", "Chakrapath", "0.05"},
            {"Koteshwor", "Pulchowk", "12.3"}
        };

        for (int i = 0; i < testRoutes.length; i++) {
            String origin = testRoutes[i][0];
            String destination = testRoutes[i][1];
            double distance = Double.parseDouble(testRoutes[i][2]);

            System.out.println("\nValidating route from " + origin + " to " + destination + " (" + distance + " km)");

            try {
                boolean result = validator.isValidCommuteRoute(origin, destination, distance);
                System.out.println("Valid route.");
            } catch (SameLocationException e) {// if there is an exception it will store in e variable 
                System.out.println("SameLocationException: " + e.getMessage());//hhe exception will be printed by e.getMessage()
            } catch (InvalidRouteException e) {
                System.out.println("InvalidRouteException: " + e.getMessage());
            }
        }
    }
}
