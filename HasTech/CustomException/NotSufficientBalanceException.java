package HasTech.CustomException;

public class NotSufficientBalanceException extends BalanceTransferException{
    public NotSufficientBalanceException(){
        super("Not Sufficient Balance!!! Please Try Again.");
    }
}
