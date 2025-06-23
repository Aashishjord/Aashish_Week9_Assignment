public interface RouteValidator {
    boolean isValidCommuteRoute(String origin, String destination, double distanceKm) throws InvalidRouteException;
}