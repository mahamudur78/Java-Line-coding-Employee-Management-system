package HasTech;

import java.io.Serializable;

public class Project implements Serializable {

    int projectID;
    String name, descriotion;
    String comment;
    
    //RelationalData checkRelation = new RelationalData();

    public Project(int projectID, String name, String descriotion, String comment) {

        this.projectID = projectID;
        this.name = name;
        this.descriotion = descriotion;
        this.comment = comment;
    }

    public void getProjectInfo() {
        System.out.println("\n--------------------------------------------------------------------------");
        //checkRelation.getSeparatorBefore();
        System.out.println("Project ID: " + projectID);
        System.out.println("Project Name: " + name);
        System.out.println("Project Descriotion: " + descriotion);
        System.out.println("Assign for Empolyee: " + comment);
        System.out.println("--------------------------------------------------------------------------\n");
        //checkRelation.getSeparatorAfter();
    }
}