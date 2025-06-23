public class GPSNavigationModule implements NavigationService {
    

    @Override
    public void navigate(String startPoint, String endPoint, RouteValidator validator)
            throws NavigationFailedException {
        System.out.println("Attempting to navigate from " + startPoint + " to " + endPoint + "...");


        if (startPoint.equalsIgnoreCase("Kalanki")) {
            throw new NavigationFailedException("GPS signal lost near Kalanki. Welcome to Kathmandu traffic!");
        }

        try {
            double distance = simulatedDistance(startPoint, endPoint);
            boolean valid = validator.isValidCommuteRoute(startPoint, endPoint, distance);

            if (valid) {
                System.out.println("Navigation successful. Estimated time: 20 minutes or may be more due to jam.");
            }

        } catch (InvalidRouteException | SameLocationException e) {
            throw new NavigationFailedException("Route validation failed!", e); 
        }
    }
    private double simulatedDistance(String start, String end) {
        return 5.0;
    }

    public static void main(String[] args) {
        RouteValidator validator = new KathmanduTrafficValidator();
        GPSNavigationModule gps = new GPSNavigationModule();

        String[][] testRoutes = {
            {"Thamel", "Thamel"},             
            {"Lalitpur", "Bhaktapur"},     
            {"Malp", "CHakrapath"},         
            {"Koteshwor", "Pulchowk"}       
        };

        for (int i = 0; i < testRoutes.length; i++) {
            String start = testRoutes[i][0];
            String end = testRoutes[i][1];

            System.out.println("\n--- Test Case " + (i + 1) + " ---");
            try {
                gps.navigate(start, end, validator);
            } catch (NavigationFailedException e) {
                System.out.println("NavigationFailedException: " + e.getMessage());

         //if the e causes is not equal to null then it will print the following 
                if (e.getCause() != null) {
                    System.out.println("Caused by: " + e.getCause().getClass().getSimpleName()
                            + " -------- " + e.getCause().getMessage());
                }
            }
        }
    }
}
 