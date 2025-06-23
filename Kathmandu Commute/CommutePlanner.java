
public class CommutePlanner {
    public static void planMyCommute(String origin, String destination,RouteValidator validator, NavigationService navigator) {
        System.out.println("Planning your commute from " + origin + " to " + destination + ".");

        try {
            navigator.navigate(origin, destination, validator);
        } catch (NavigationFailedException e) {
            System.out.println("Commute planning failed!");

            Throwable cause = e.getCause();
            if (cause instanceof SameLocationException) {
                System.out.println("Cannot plan: Origin and destination are the same.");
            } else if (cause instanceof InvalidRouteException) {
                System.out.println("Cannot plan: Invalid route details.");
            } else {
                System.out.println("Cannot plan: GPS issue or unknown error.");
            }
        } finally {
            System.out.println("Commute planning for " + origin + " to " + destination + " completed.\n");
        }
    }


    public static void main(String[] args) {
        RouteValidator validator = new KathmanduTrafficValidator();
        NavigationService navigator = new GPSNavigationModule();

     
        String[][] testCases = {
            {"Baneshwor", "Baneshwor"},     
            {"Thamel", "Patan"},           
            {"Malpi", "Chakrapath"},          
            {"Koteshwor", "Lagankhel"}    
        };

        for (int i = 0; i < testCases.length; i++) {
            String origin = testCases[i][0];
            String destination = testCases[i][1];

            System.out.println("Test Case " + (i + 1));
            planMyCommute(origin, destination, validator, navigator);
        }

        System.out.println("This app will not fix the traffic, but at least it tells you why you're stuck!");
    }
}
