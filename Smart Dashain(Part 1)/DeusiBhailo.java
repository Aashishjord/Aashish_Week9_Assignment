// File: DeusiBhailo.java

import java.util.*;
class NoRouteException extends FestivalPlanningException {
    public NoRouteException(String message) {
        super(message);
    }
}
public class DeusiBhailo extends FestivalActivity {
    private List<String> plannedRoutes;
    private int numberOfPerformers;

    // Constructor
    public DeusiBhailo(double estimatedCost, List<String> plannedRoutes, int numberOfPerformers) {
        super("Deusi Bhailo Program", estimatedCost);
        this.plannedRoutes = plannedRoutes;
        this.numberOfPerformers = numberOfPerformers;
    }
    @Override
    public void planActivity() throws FestivalPlanningException {
        if (plannedRoutes == null || plannedRoutes.isEmpty()) {
            throw new NoRouteException("No routes planned for Deusi Bhailo! Are we just singing in the living room?");
        }

        if (numberOfPerformers < 3) {
            throw new FestivalPlanningException("Need at least 3 performers for a proper Deusi Bhailo!");
        }

        System.out.println("Deusi Bhailo program with " + numberOfPerformers +
                           " performers planned for " + plannedRoutes.size() + " routes!");
    }

    /**
     * @param args
     */
   public static void main(String[] args) {
    List<DeusiBhailo> programs = new ArrayList<>();
    programs.add(new DeusiBhailo(20000, Arrays.asList("Ward 1", "Ward 2", "Ward 3"), 5));
    programs.add(new DeusiBhailo(18000, new ArrayList<>(), 4)); 
    programs.add(new DeusiBhailo(15000, Arrays.asList("Temple Area"), 2)); 
    programs.add(new DeusiBhailo(12000, Arrays.asList("Main Road", "Community Hall"), 3));

    for (int i = 0; i < programs.size(); i++) {
        DeusiBhailo program = programs.get(i);
        try {
            program.displayOverview();
            program.planActivity();
            System.out.println();
        } catch (NoRouteException e) {
            System.out.println("Route Issue: " + e.getMessage() + "\n");
        } catch (FestivalPlanningException e) {
            System.out.println("Planning Issue: " + e.getMessage() + "\n");
        }
    }
}
}