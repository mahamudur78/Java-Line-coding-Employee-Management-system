package HasTech;

public class Notification {

    boolean dataCheck;
    int foundDataSize;
    
    RelationalData checkRelation = new RelationalData();

    public void setDataCheck(boolean dataCheck) {
        this.dataCheck = dataCheck;
    }

    public void setFoundDataSize(int foundDataSize) {
        this.foundDataSize = foundDataSize;
    }

    public void getDataNotFound(String name, String massage, int foundDataSize) {
        if (dataCheck == false) {
            
            /*System.out.println("\n--------------------------------------------------------------------------");  
            System.out.println("---------------------------- " + name + " " + massage + " Not Found...");
            System.out.println("--------------------------------------------------------------------------\n");*/
            checkRelation.getMessageContinue(name + " " + massage + " Not Found...");
        } else {
            /*System.out.println("--------------------------------------------------------------------------");
            System.out.println("---------------------------- " + foundDataSize + " " + name + " " + massage + " Found...");
            //System.out.println("### " + foundDataSize + " " + name + " " + massage + " Found...");
            System.out.println("--------------------------------------------------------------------------\n");*/
            checkRelation.getMessageContinue(foundDataSize + " " + name + " " + massage + " Found...");
            this.dataCheck = false;
        }
    }

}