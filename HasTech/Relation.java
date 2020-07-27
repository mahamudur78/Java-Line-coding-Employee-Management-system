package HasTech;

import java.io.Serializable;

public class Relation implements Serializable {

    int relationID;
    int clientID;
    int employeeID;
    int projectID;
    String assignDate;
    String deliveryDate;
    int projectDuration;
    double clientBudget;
    String jobStates;

    public Relation(int relationID, int clientID, int employeeID, int projectID, String assignDate, String deliveryDate, int projectDuration, double clientBudget, String jobStates) {
        this.relationID = relationID;
        this.clientID = clientID;
        this.employeeID = employeeID;
        this.projectID = projectID;
        this.assignDate = assignDate;
        this.deliveryDate = deliveryDate;
        this.projectDuration = projectDuration;
        this.clientBudget = clientBudget;
        this.jobStates = jobStates;
    }

    public void getRelationInformation() {
        System.out.println("relationID: " + relationID);
        System.out.println("clientID: " + clientID);
        System.out.println("employeeID: " + employeeID);
        System.out.println("projectID: " + projectID);
        System.out.println("assignDate: " + assignDate);
        System.out.println("projectDuration: " + projectDuration);
        System.out.println("clientBudget: " + clientBudget);
        System.out.println("jobStates: " + jobStates);
        System.out.println();
    }
}