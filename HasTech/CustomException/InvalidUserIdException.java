package HasTech.CustomException;

public class InvalidUserIdException extends NumberFormatException{
    public InvalidUserIdException(){
        super("Invalid User ID!!! Try Again.");
    }
}
