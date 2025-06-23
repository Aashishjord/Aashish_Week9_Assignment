class InvalidGuestCountException extends FestivalPlanningException {
    public InvalidGuestCountException(String message) {
        super(message);
    }
}

class BudgetExceededException extends FestivalPlanningException {
    public BudgetExceededException(String message) {
        super(message);
    }
}


public class TikaCeremony extends FestivalActivity {
    private int expectedGuests;
    private String mainFamilyElder;

    // Constructor
    public TikaCeremony(double estimatedCost, int expectedGuests, String mainFamilyElder) {
        super("Tika Ceremony", estimatedCost);
        this.expectedGuests = expectedGuests;
        this.mainFamilyElder = mainFamilyElder;
    }

    @Override
    public void planActivity() throws FestivalPlanningException {
        if (expectedGuests < 5) {
            throw new InvalidGuestCountException("Not enough guests for a lively Tika. Is the old vibes over or the generation has been changed to modernity?");
        }

        if (getEstimatedCost() > 50000) {
            throw new BudgetExceededException("Tika budget too high. Is this for the whole village or your family is very big?");
        }

        System.out.println("Tika ceremony with " + mainFamilyElder + " planned successfully for " + expectedGuests + " guests!");
    }

    public static void main(String[] args) {
        // Test cases
        TikaCeremony[] ceremonies = new TikaCeremony[] {
            new TikaCeremony(45000, 10, "Grandfather"),
            new TikaCeremony(60000, 15, "Grandmother"),   
            new TikaCeremony(30000, 3, "Uncle"), 
            new TikaCeremony(48000, 8, "Father")
        };

        for (TikaCeremony ceremony : ceremonies) {
            try {
                ceremony.displayOverview();
                ceremony.planActivity();
                System.out.println();
            } catch (InvalidGuestCountException e) {
                System.out.println("Guest Count Issue: " + e.getMessage() + "\n");
            } catch (BudgetExceededException e) {
                System.out.println("Budget Issue: " + e.getMessage() + "\n");
            } catch (FestivalPlanningException e) {
                System.out.println("Festival Planning Error: " + e.getMessage() + "\n");
            }
        }
    }
}
