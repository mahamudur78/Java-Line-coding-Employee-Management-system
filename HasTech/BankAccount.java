package HasTech;

import java.io.Serializable;

public class BankAccount implements Serializable {

    int bankID;
    String userID;
    double blance;
    double pendingBalance;

    public BankAccount(int bankID, String userID, double blance, double pendingBalance) {
        this.bankID = bankID;
        this.userID = userID;
        this.blance = blance;
        this.pendingBalance = pendingBalance;
    }

    public void pendingBalance(double blance) {
        this.pendingBalance = this.pendingBalance + blance;
    }

    public void getBankAccount() {
        System.out.println("\nBank id: " + bankID);
        System.out.println("User ID: " + userID);
        System.out.println("blance: " + blance);
        System.out.println("Pending Blance: " + pendingBalance + "\n");
    }
}