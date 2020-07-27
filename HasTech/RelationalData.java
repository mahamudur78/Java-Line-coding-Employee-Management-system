package HasTech;

import HasTech.CustomException.LoginCheckerException;
import HasTech.CustomException.NotSufficientBalanceException;
import HasTech.CustomException.WrongKeyException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class RelationalData {

    Scanner src = new Scanner(System.in);
    Company companyInfo;
    Client[] client;
    Project[] project;
    Relation[] relation;
    Employee[] employee;
    BankAccount[] bankAccount;
    int[] pendingJobEmpID;
    private boolean ValidationMessage = true;
    final int messageLength = 74;

    public RelationalData() {
    }

    public RelationalData(Company companyInfo, Client[] client, Project[] project, Employee[] employee, Relation[] relation) {
        this.companyInfo = companyInfo;
        this.client = client;
        this.project = project;
        this.employee = employee;
        this.relation = relation;
    }

    public RelationalData(Company companyInfo, Employee[] employee, Relation[] relation) {
        this.companyInfo = companyInfo;
        this.employee = employee;
        this.relation = relation;
    }

    public RelationalData(Company companyInfo, BankAccount[] bankAccount) {
        this.companyInfo = companyInfo;
        this.bankAccount = bankAccount;
    }

    public RelationalData(Company companyInfo, Employee[] employee) {
        this.companyInfo = companyInfo;
        this.employee = employee;
    }

    public RelationalData(Company companyInfo, Employee[] employee, BankAccount[] bankAccount) {
        this.companyInfo = companyInfo;
        this.employee = employee;
        this.bankAccount = bankAccount;
    }

    public RelationalData(BankAccount[] bankAccount) {
        this.bankAccount = bankAccount;
    }

    public RelationalData(Company companyInfo) {
        this.companyInfo = companyInfo;
    }

    public void getContinue() {
        System.out.print("Press enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
        System.out.println();
    }

    public void getPressedWrongKey() {
        try {
            throw new WrongKeyException();
        } catch (WrongKeyException e) {
            getMessageContinue(e.getMessage());
        }
    }

    public void getEmployeeInfo(int employeeBankID, int employeeID) {
        if (employeeBankID > 0) {
            System.out.println("Employe ID");
            for (int i = 0; i < employee.length - 1; i++) {
                if (employeeBankID == employee[i].bankID) {
                }
            }
        }
    }

    public int getBalanceShow(int bankID) {
        int findBankAccountObjectArray = 0;
        for (int i = 0; i < bankAccount.length - 1; i++) {
            if (bankID == bankAccount[i].bankID) {
                System.out.println(bankAccount[i].blance);
                findBankAccountObjectArray = i;
                break;
            }
        }
        return findBankAccountObjectArray;
    }

    public void getWithdraw(BankAccount[] bankAccount, int findBankAccountObjectArrany, double withdrawAmount) throws IOException, NotSufficientBalanceException {
        this.bankAccount = bankAccount;
        if (getLoginInfoCheck()) {
            if (withdrawAmount > bankAccount[findBankAccountObjectArrany].blance) {
                throw new NotSufficientBalanceException();
            } else {
                bankAccount[findBankAccountObjectArrany].blance = bankAccount[findBankAccountObjectArrany].blance - withdrawAmount;
                getMessage("Withdraw Successful!");
                System.out.println("Current Balance is: " + bankAccount[findBankAccountObjectArrany].blance);
                System.out.println();
                setSaveData(bankAccount);
                getContinue();
            }
        }

    }

    public void getProjectStates(int clientID, int projectID, int employeeID, int relationID) {
        getSeparatorBefore();
        System.out.println("### Client Information");
        for (int i = 0; i < client.length - 1; i++) {
            if (clientID == client[i].id) {
                System.out.println("Client ID: " + companyInfo.getClientPrefix() + client[i].id);
                System.out.println("Client Name: " + client[i].name);
                System.out.println("Client Email: " + client[i].email);
            }
        }

        System.out.println("\n### Project Information");
        for (int i = 0; i < project.length - 1; i++) {
            if (projectID == project[i].projectID) {
                System.out.println("Project ID: " + companyInfo.getProjectPrefix() + project[i].projectID);
                System.out.println("Project Name: " + project[i].name);
                System.out.println("Project Descriotion: " + project[i].descriotion);
            }
        }

        for (int i = 0; i < relation.length - 1; i++) {
            if (relationID == relation[i].relationID) {
                System.out.println("Assign Date: " + relation[i].assignDate);
                if (relation[i].jobStates.equalsIgnoreCase("Pending")) {

                } else {
                    System.out.println("Delivery Date: " + relation[i].deliveryDate);
                }

                System.out.println("Project Duration: " + relation[i].projectDuration);
                System.out.println("Project Budget: " + relation[i].clientBudget);
                System.out.println("Project States: " + relation[i].jobStates);
            }
        }

        System.out.println("\n### Job Assign Employee Information");
        for (int i = 0; i < employee.length - 1; i++) {
            if (employeeID == employee[i].id) {
                System.out.println("Employee ID: " + companyInfo.getEmployeePrefix() + employee[i].id);
                System.out.println("Employee Name: " + employee[i].name);
            }
        }
        getSeparatorAfter();
    }

    public int[] getSequentialEmployeeID() {
        //Copy relation Array to employeeID
        int[] employeeID = new int[relation.length - 1];
        for (int i = 0; i < relation.length - 1; i++) {
            employeeID[i] = relation[i].employeeID;

        }

        //Shorting employeeID
        for (int i = 0; i < employeeID.length; i++) {
            for (int j = i; j < employeeID.length; j++) {
                if (employeeID[i] > employeeID[j]) {
                    int temp = employeeID[j];
                    employeeID[j] = employeeID[i];
                    employeeID[i] = temp;

                }
            }
        }

        //remove duplicate value
        int[] employeeID_temp = new int[employeeID.length];
        int j = 0;
        for (int i = 0; i < employeeID.length - 1; i++) {
            if (employeeID[i] != employeeID[i + 1]) {
                employeeID_temp[j] = employeeID[i];
                j++;
            }
        }
        employeeID_temp[j] = employeeID[employeeID.length - 1];

        return employeeID_temp;
    }

    public void getEmployeeProjectStates(String statesName, String states) {
        if (relation.length > 1) {
            int[] employeeID_temp = getSequentialEmployeeID();

            this.pendingJobEmpID = new int[employeeID_temp.length];
            int pendingJobCount = 0;
            boolean noProjectFound = true;
            System.out.println(statesName + "\t\t ID:\t Name:");
            getSeparatorBefore();
            for (int i = 0; i < employeeID_temp.length; i++) {
                int temp = 0;
                int m;
                boolean check = false;

                for (m = 0; m < relation.length - 1; m++) {
                    if (relation[m].jobStates.equalsIgnoreCase(states) && relation[m].employeeID == employeeID_temp[i]) {
                        temp++;
                        check = true;
                    }
                }
                if (check == true) {
                    System.out.println(temp + " Project\t " + companyInfo.getEmployeePrefix() + employee[employeeID_temp[i] - 1].id + "\t " + employee[employeeID_temp[i] - 1].name + "\t Email: " + employee[employeeID_temp[i] - 1].email);
                    pendingJobEmpID[pendingJobCount++] = employee[i].id;
                    noProjectFound = false;
                    check = false;
                }
            }
            if (noProjectFound) {
                getSpecificMessage("No Project Found");
            }
            getSeparatorAfter();
        } else {
            getMessageContinue("No Data Available");
        }
    }

    public void getFreeEmployee() {
        if (relation.length > 1) {
            int[] employeeID_temp = getSequentialEmployeeID();
            this.pendingJobEmpID = new int[employeeID_temp.length];
            int pendingJobCount = 0;
            for (int i = 0; i < employeeID_temp.length; i++) {

                for (int j = 0; j < relation.length - 1; j++) {
                    if (relation[j].jobStates.equalsIgnoreCase("Pending") && relation[j].employeeID == employeeID_temp[i]) {
                        pendingJobEmpID[pendingJobCount++] = employeeID_temp[i];
                        break;
                    }
                }
            }
            boolean noProjectFound = true;
            System.out.println("ID:\t Name:");
            getSeparatorBefore();
            for (int i = 0; i < employee.length - 1; i++) {
                for (int j = 0; j < pendingJobEmpID.length; j++) {
                    if (employee[i].id != pendingJobEmpID[i]) {
                        //System.out.println(employee[i].id);
                        System.out.println(companyInfo.getEmployeePrefix() + employee[i].id + "\t " + employee[i].name + "\t Email: " + employee[i].email);
                        noProjectFound = false;
                        break;
                    }
                }
            }
            if (noProjectFound) {
                getSpecificMessage("No Employee Found");
            }
            getSeparatorAfter();
        } else {
            getMessageContinue("No Data Available");

        }
    }

    public int getPrefixSeparator(String userID) {

        char[] checkUserIdPrefix = userID.toCharArray();
        int prefixSize = 0;
        for (int i = 0; i < checkUserIdPrefix.length; i++) {
            if (checkUserIdPrefix[i] >= '0' && checkUserIdPrefix[i] <= '9') {
                return i;
            }
        }
        return prefixSize;
    }

    public void setValidationMessageCheck(boolean ValidationMessage) {
        this.ValidationMessage = ValidationMessage;
    }

    public boolean getValidationMessageCheck() {
        return ValidationMessage;
    }

    public int getUserIdValidation(String checkSenderUserId, int checkPrefixStringSize, int checkSenderUserId_Length) {
        int userID = 0;

        try {
            userID = Integer.parseInt(checkSenderUserId.substring(checkPrefixStringSize, checkSenderUserId.length()));
        } catch (NumberFormatException e) {
            getMessage("Invalid User ID!!! Try Again.");
            setValidationMessageCheck(false);
        }

        return userID;
    }

    public boolean getLoginInfoCheck() {
        System.out.println("\nPlease Enter your Root Login Information");
        System.out.print("Username: ");
        String checkUsername = src.nextLine();
        System.out.print("Password: ");
        String checkPassword = src.nextLine();

        try {
            if (checkUsername.equals(companyInfo.getUsername()) && checkPassword.equals(companyInfo.getPassword())) {
                getMessage("Login Successful");
                return true;
            } else {
                throw new LoginCheckerException("Login Information Wrong, Please try Again.");
            }
        } catch (LoginCheckerException e) {
            getMessage(e.getMessage());
            return false;
        }
    }

    public boolean getTransferBlance(BankAccount[] bankAccount, int findSenderBankAccountObjectArray, int findReceiverBankAccountObjectArray) throws IOException, NotSufficientBalanceException {
        this.bankAccount = bankAccount;
        System.out.print("Enter Transfer Amount Here(USD): ");
        double transferAmount = src.nextDouble();
        src.nextLine();
        if (bankAccount[findSenderBankAccountObjectArray].blance >= transferAmount && getLoginInfoCheck()) {
            bankAccount[findSenderBankAccountObjectArray].blance = bankAccount[findSenderBankAccountObjectArray].blance - transferAmount;
            bankAccount[findReceiverBankAccountObjectArray].blance = bankAccount[findReceiverBankAccountObjectArray].blance + transferAmount;
            getMessage("Transaction Successful!!!");
            setSaveData(bankAccount);
            return true;
        } else {
            //System.out.println("Transfer Amount cannot be greater than Sender's Account Balance!!! Try Again.\n");
            throw new NotSufficientBalanceException();
        }
    }

    public boolean getEditInformationCheck() {
        System.out.println("Do you want to change?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("\nchoose here: ");
        int selectOption = src.nextInt();
        System.out.println();
        return selectOption == 1;
    }

    public void getEmployeeInfo(int employeeArrayNo) {
        //System.out.println("\n--------------------------------------------------------------------------");
        getSeparatorBefore();
        System.out.println("### Employee Information");
        System.out.println("Employee ID: " + companyInfo.getEmployeePrefix() + employee[employeeArrayNo].id);
        System.out.println("Name: " + employee[employeeArrayNo].name);
        System.out.println("Date of Birth: " + employee[employeeArrayNo].dateOfBirth);
        System.out.println("Sex: " + employee[employeeArrayNo].sex);
        System.out.println("NID No: " + employee[employeeArrayNo].NID);
        System.out.println("Blood Group: " + employee[employeeArrayNo].bloodGroup);
        System.out.println("Religion: " + employee[employeeArrayNo].religion);
        System.out.println("Marital Status: " + employee[employeeArrayNo].maritalStatus);
        System.out.println("Email: " + employee[employeeArrayNo].email);
        System.out.println("Joined Date: " + employee[employeeArrayNo].joinedDate);
        System.out.println("Phone: " + employee[employeeArrayNo].phone);
        System.out.println("Address: " + employee[employeeArrayNo].address);
        System.out.println("Department: " + employee[employeeArrayNo].department);
        System.out.println("Salary: " + employee[employeeArrayNo].salary);
        System.out.print("Current Balance: ");

        for (int i = 0; i < bankAccount.length - 1; i++) {
            if (employee[employeeArrayNo].bankID == bankAccount[i].bankID) {
                System.out.print(bankAccount[i].blance);
            }
        }
        System.out.println("\nComment: " + employee[employeeArrayNo].comment);
        //System.out.println("--------------------------------------------------------------------------\n");
        getSeparatorAfter();
    }

    public int getIntegerValue() {
        int userID = 0;
        String userInput = src.nextLine();

        try {
            userID = Integer.parseInt(userInput.substring(getPrefixSeparator(userInput), userInput.length()));
        } catch (NumberFormatException ex) {
            /*System.out.println("\n--------------------------------------------------------------------------");
            System.out.println("------------------------Invalid Number!!! Try Again.----------------------");
            System.out.println("--------------------------------------------------------------------------\n");*/
            setValidationMessageCheck(false);
            //System.out.println(ex);
            getMessageContinue("Invalid Number!!! Try Again.");
        }
        return userID;
    }

    public double getDoubleValue() {
        double userID = -1;
        //src.nextLine();
        String userInput = src.nextLine();
        //System.out.println("getPrefixSeparator:" + getPrefixSeparator(userInput));
        try {
            userID = Double.parseDouble(userInput.substring(getPrefixSeparator(userInput), userInput.length()));
        } catch (NumberFormatException e) {
            /*System.out.println("\n--------------------------------------------------------------------------");
            System.out.println("--------------------Invalid Currency Amount!!! Try Again.-----------------");
            System.out.println("--------------------------------------------------------------------------\n");*/
            setValidationMessageCheck(false);
            //getMessageContinue("Invalid Valid Currency Amount!!! Try Again.");
            getMessage(e.getMessage().replaceFirst(".*For input string: ", "This is not a Currency Amount: "));
        }

        return userID;
    }

    public void setSaveData(Company companyInfo) throws FileNotFoundException, IOException {
        this.companyInfo = companyInfo;
        //Company Information Store
        ObjectOutputStream outCompany = new ObjectOutputStream(new FileOutputStream("c:\\store\\companyInfo.txt"));
        outCompany.writeObject(companyInfo);
    }

    public void setSaveData(BankAccount[] bankAccount) throws FileNotFoundException, IOException {
        this.bankAccount = bankAccount;
        //Bank Account Store
        ObjectOutputStream outBankAccount = new ObjectOutputStream(new FileOutputStream("c:\\store\\bankAccount.txt"));
        outBankAccount.writeObject(bankAccount);

    }

    public void setSaveData(Employee[] employee) throws FileNotFoundException, IOException {
        this.employee = employee;
        //Employee Store
        ObjectOutputStream outEmpolyee = new ObjectOutputStream(new FileOutputStream("c:\\store\\employee.txt"));
        outEmpolyee.writeObject(employee);
    }

    public void setSaveData(Client[] client) throws FileNotFoundException, IOException {
        this.client = client;
        //Client Store
        ObjectOutputStream outClient = new ObjectOutputStream(new FileOutputStream("c:\\store\\client.txt"));
        outClient.writeObject(client);

    }

    public void setSaveData(Project[] project) throws FileNotFoundException, IOException {
        this.project = project;
        //Project Store
        ObjectOutputStream outProject = new ObjectOutputStream(new FileOutputStream("c:\\store\\project.txt"));
        outProject.writeObject(project);

    }

    public void setSaveData(Relation[] relation) throws FileNotFoundException, IOException {
        this.relation = relation;
        //Relation Account
        ObjectOutputStream outRelation = new ObjectOutputStream(new FileOutputStream("c:\\store\\relation.txt"));
        outRelation.writeObject(relation);

    }

    public void setSaveAllData(Company companyInfo, BankAccount[] bankAccount, Employee[] employee, Client[] client, Project[] project, Relation[] relation) throws FileNotFoundException, IOException {
        this.companyInfo = companyInfo;
        this.bankAccount = bankAccount;
        this.employee = employee;
        this.client = client;
        this.project = project;
        this.relation = relation;

        //Company Information Store
        ObjectOutputStream outCompany = new ObjectOutputStream(new FileOutputStream("c:\\store\\companyInfo.txt"));
        outCompany.writeObject(companyInfo);
        //Bank Account Store
        ObjectOutputStream outBankAccount = new ObjectOutputStream(new FileOutputStream("c:\\store\\bankAccount.txt"));
        outBankAccount.writeObject(bankAccount);
        //Employee Store
        ObjectOutputStream outEmpolyee = new ObjectOutputStream(new FileOutputStream("c:\\store\\employee.txt"));
        outEmpolyee.writeObject(employee);
        //Client Store
        ObjectOutputStream outClient = new ObjectOutputStream(new FileOutputStream("c:\\store\\client.txt"));
        outClient.writeObject(client);
        //Project Store
        ObjectOutputStream outProject = new ObjectOutputStream(new FileOutputStream("c:\\store\\project.txt"));
        outProject.writeObject(project);
        //Relation Account
        ObjectOutputStream outRelation = new ObjectOutputStream(new FileOutputStream("c:\\store\\relation.txt"));
        outRelation.writeObject(relation);

        //Message View
        //getMessage(message);
    }

    public void setSaveAllDataWithMassage(String message, Company companyInfo, BankAccount[] bankAccount, Employee[] employee, Client[] client, Project[] project, Relation[] relation) throws IOException {
        setSaveAllData(companyInfo, bankAccount, employee, client, project, relation);
        getMessage(message);
    }

    public void getMessage(String message) {

        getSeparatorBefore();

        getSpecificMessage(message);

        getSeparatorAfter();

    }

    public void getMessageContinue(String message) {
        getMessage(message);
        getContinue();
    }

    public void getSeparator() {
        for (int i = 0; i < messageLength; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void getSeparatorBefore() {
        getSeparator();
    }

    public void getSeparatorAfter() {
        getSeparator();
        //System.out.println();
    }

    public void getSpecificMessage(String message) {

        for (int i = 0; i < ((messageLength / 2) + (message.length() / 2)) - message.length(); i++) {
            System.out.print("-");
        }
        System.out.print(message);
        for (int i = 0; i < ((messageLength / 2) + (message.length() / 2)) - message.length(); i++) {
            System.out.print("-");
        }
        if (message.length() % 2 == 1) {
            System.out.print("-");
        }
        System.out.println();
    }

}
