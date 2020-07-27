package HasTech;

public class BugTest {

    public static void main(String[] args) {
        RelationalData checkRelation = new RelationalData();
        /*try {
            int userID = Integer.parseInt("asdf66".substring(3, 6));
        }catch(NumberFormatException e){
            checkRelation.getMessageContinue(e.getMessage().replaceFirst(".*For input string: " , "This is not a Currency Amount: " ) + " Please Try Again..");
       
        }*/
        
        checkRelation.getContinue();
    }
}
