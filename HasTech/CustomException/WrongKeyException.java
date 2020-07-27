package HasTech.CustomException;

public class WrongKeyException extends CustomException{
    public WrongKeyException(){
        super("You Pressed the wrong Key!!! Try Again.");
    }
}
