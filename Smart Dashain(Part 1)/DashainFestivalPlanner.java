// File: DashainFestivalPlanner.java

import java.util.*;

// DashainFestivalPlanner handles execution of festival plans
public class DashainFestivalPlanner {

    // Static method to execute plans
    public static void executeFestivalPlan(List<FestivalActivity> activities) {
        for (FestivalActivity activity : activities) {
            activity.displayOverview();
            try {
                activity.planActivity();
            } catch (InvalidGuestCountException e) {
                System.out.println("Planning Warning (Guests): " + e.getMessage());
            } catch (BudgetExceededException e) {
                System.out.println("Planning Warning (Budget): " + e.getMessage());
            } catch (NoRouteException e) {
                System.out.println("Planning Warning (Routes): " + e.getMessage());
            } catch (FestivalPlanningException e) {
                System.out.println("General Planning Error: " + e.getMessage());
            } finally {
                // Always runs
                System.out.println("Activity planning attempt for " + activity.getActivityName() + " completed.\n");
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        List<FestivalActivity> activities = new ArrayList<>();

        // TikaCeremony tests
        activities.add(new TikaCeremony(45000, 10, "Grandfather")); // Valid
        activities.add(new TikaCeremony(60000, 15, "Aunt")); // BudgetExceededException
        activities.add(new TikaCeremony(30000, 2, "Uncle")); // InvalidGuestCountException

        // DeusiBhailo tests
        activities.add(new DeusiBhailo(18000, Arrays.asList("Ward 1", "Ward 2"), 5)); // Valid
        activities.add(new DeusiBhailo(22000, new ArrayList<>(), 4)); // NoRouteException
        activities.add(new DeusiBhailo(15000, Arrays.asList("Temple Area"), 2)); // FestivalPlanningException (< 3 performers)

        // Let’s manage the chaos of Dashain!
        System.out.println("----- Dashain Festival Planning Begins! -----\n");
        executeFestivalPlan(activities);
        System.out.println("----- All activities processed. Time for sel roti and rest! 🪔🥰 -----");
    }
}
