class FestivalPlanningException extends Exception{
    public FestivalPlanningException(String message){
        super(message);
    }
}


public abstract class FestivalActivity {
    private String activityName;
    private double estimatedCost;


    public FestivalActivity(String activityName,double estimatedCost){
        this.activityName=activityName;
        this.estimatedCost=estimatedCost;

    }


public String getName(){
    return activityName;

}

public double getEstimatedCost(){
    return estimatedCost;
}

public abstract void planActivity() throws FestivalPlanningException;

public void displayOverview(){
    System.out.println("Activity Name:"+activityName);
    System.out.println("Estimated Cost:$"+estimatedCost);
}

    String getActivityName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}