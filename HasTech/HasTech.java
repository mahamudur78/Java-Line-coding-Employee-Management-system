package HasTech;

import HasTech.CustomException.NotSufficientBalanceException;
import HasTech.CustomException.ReceverClientBankIDNotFoundException;
import HasTech.CustomException.ReceverEmployeeBankIDNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

// Data and Time Import
import java.text.SimpleDateFormat;
import java.util.Date;

public class HasTech {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Scanner src = new Scanner(System.in);

        SimpleDateFormat dnt = new SimpleDateFormat("dd/MM/yyyy : HH:mm:ss");
        Date date = new Date();
        String dateAndTime = dnt.format(date);
        System.out.println("Date & Time Now: " + dateAndTime);
        // Store Text File

        // BankAccount Store
        int totalBankAccount;
        ObjectInputStream inBankAccount = null;
        ObjectOutputStream outBankAccount = null;
        BankAccount[] bankAccountStore = null;
        try {

            inBankAccount = new ObjectInputStream(new FileInputStream("c:\\store\\bankAccount.txt"));
            bankAccountStore = (BankAccount[]) inBankAccount.readObject();
        } catch (FileNotFoundException ex) {
            File file = new File("c:\\store");
            file.mkdir();
            outBankAccount = new ObjectOutputStream(new FileOutputStream("c:\\store\\bankAccount.txt"));
        }

        if (bankAccountStore == null) {
            totalBankAccount = 1;
        } else {
            totalBankAccount = bankAccountStore.length;
        }

        BankAccount[] bankAccount = new BankAccount[totalBankAccount];

        int[] bankID = new int[totalBankAccount];
        String[] bankUserID = new String[totalBankAccount];
        double[] bankBalance = new double[totalBankAccount];
        double[] bankPendingBalance = new double[totalBankAccount];

        for (int i = 0; i < totalBankAccount - 1; i++) {
            bankID[i] = bankAccountStore[i].bankID;
            bankUserID[i] = bankAccountStore[i].userID;
            bankBalance[i] = bankAccountStore[i].blance;
            bankPendingBalance[i] = bankAccountStore[i].pendingBalance;

            bankAccount[i] = new BankAccount(bankID[i], bankUserID[i], bankBalance[i], bankPendingBalance[i]);
        }

        // Compony information
        ObjectInputStream inCompany = null;
        ObjectOutputStream outCompany = null;
        Company companyInfo = new Company();
        try {

            inCompany = new ObjectInputStream(new FileInputStream("c:\\store\\companyInfo.txt"));
            companyInfo = (Company) inCompany.readObject();
        } catch (FileNotFoundException ex) {
            File file = new File("c:\\store");
            file.mkdir();
            outCompany = new ObjectOutputStream(new FileOutputStream("c:\\store\\companyInfo.txt"));
        }
        //RelationalData Object
        RelationalData checkRelation = new RelationalData();
        if (companyInfo.setDataChecker() == true) {
            System.out.println("### Create a New Company.");
            System.out.print("Company Name: ");
            companyInfo.setName(src.nextLine());
            System.out.print("Company Address: ");
            companyInfo.setAddress(src.nextLine());
            System.out.print("Company Descriotion: ");
            companyInfo.setDescriotion(src.nextLine());

            System.out.println("\n### Company Identity Prefix (Please Don't Use Numbe)");
            boolean checkPrefixValidation = true;

            // Company ID Prefix 
            while (checkPrefixValidation) {
                System.out.print("Company ID Prefix (Exp: HT-): ");
                checkPrefixValidation = companyInfo.setCompanyPrefix(src.nextLine());
            }

            // Bank ID Prefix 
            checkPrefixValidation = true;
            while (checkPrefixValidation) {
                System.out.print("Bank ID Prefix (Exp: HTB-): ");
                checkPrefixValidation = companyInfo.setBankPrefix(src.nextLine());
            }

            // Employee ID Prefix 
            checkPrefixValidation = true;
            while (checkPrefixValidation) {
                System.out.print("Employee ID Prefix (Exp: HTE-): ");
                checkPrefixValidation = companyInfo.setEmployeePrefix(src.nextLine());
            }

            // Client ID Prefix 
            checkPrefixValidation = true;
            while (checkPrefixValidation) {
                System.out.print("Client ID Prefix (Exp: HTC-): ");
                checkPrefixValidation = companyInfo.setClientPrefix(src.nextLine());
            }

            // Project ID Prefix 
            checkPrefixValidation = true;
            while (checkPrefixValidation) {
                System.out.print("Project ID Prefix (Exp: HTP-): ");
                checkPrefixValidation = companyInfo.setProjectPrefix(src.nextLine());
            }
            System.out.println("\n### Company Account Information");
            bankID[totalBankAccount - 1] = totalBankAccount;
            bankUserID[totalBankAccount - 1] = companyInfo.getCompanyPrefix() + totalBankAccount;
            System.out.println("Company Bank ID: " + bankUserID[totalBankAccount - 1]);

            System.out.print("Company Current Balance (USB): ");
            bankBalance[totalBankAccount - 1] = checkRelation.getDoubleValue();

            System.out.println("\n### Create Root login information");
            System.out.print("Set User Name: ");
            //src.nextLine();
            companyInfo.setUsername(src.nextLine());
            System.out.print("Set Password: ");
            companyInfo.setPassword(src.nextLine());

            System.out.println("Well Done!!!\n");

            companyInfo.setDataChecker(false);

            // Bank Account Tempory Backup
            int[] bankID_temp = new int[totalBankAccount + 1];
            String[] bankUserID_temp = new String[totalBankAccount + 1];
            double[] bankBalance_temp = new double[totalBankAccount + 1];
            double[] bankPendingBalance_temp = new double[totalBankAccount + 1];

            BankAccount[] bankAccount_temp = new BankAccount[totalBankAccount];

            for (int i = 0; i < totalBankAccount; i++) {
                bankID_temp[i] = bankID[i];
                bankUserID_temp[i] = bankUserID[i];
                bankBalance_temp[i] = bankBalance[i];
                bankPendingBalance_temp[i] = bankPendingBalance[i];

                bankAccount_temp[i] = new BankAccount(bankID_temp[i], bankUserID_temp[i], bankBalance_temp[i], bankPendingBalance_temp[i]);
            }

            totalBankAccount++;

            bankID = new int[totalBankAccount];
            bankUserID = new String[totalBankAccount];
            bankBalance = new double[totalBankAccount];
            bankPendingBalance = new double[totalBankAccount];

            bankAccount = new BankAccount[totalBankAccount];

            for (int i = 0; i < totalBankAccount; i++) {
                bankID[i] = bankID_temp[i];
                bankUserID[i] = bankUserID_temp[i];
                bankBalance[i] = bankBalance_temp[i];
                bankPendingBalance[i] = bankPendingBalance_temp[i];

                bankAccount[i] = new BankAccount(bankID[i], bankUserID[i], bankBalance[i], bankPendingBalance[i]);
            }

            //Data Save
            checkRelation.setSaveData(companyInfo);
            checkRelation.setSaveData(bankAccount);

        }

        checkRelation = new RelationalData(companyInfo);
        boolean loginChecker = true;
        while (loginChecker) {
            //loginChecker = false;
            if (checkRelation.getLoginInfoCheck()) {

                // Employee Store
                int totelEmpolyee;
                ObjectInputStream inEmpolyee = null;
                ObjectOutputStream outEmpolyee = null;
                Employee[] employeeStore = null;
                try {
                    inEmpolyee = new ObjectInputStream(new FileInputStream("c:\\store\\employee.txt"));
                    employeeStore = (Employee[]) inEmpolyee.readObject();
                } catch (FileNotFoundException ex) {
                    File file = new File("c:\\store");
                    file.mkdir();
                    outEmpolyee = new ObjectOutputStream(new FileOutputStream("c:\\store\\employee.txt"));
                }

                if (employeeStore == null) {
                    totelEmpolyee = 1;
                } else {
                    totelEmpolyee = employeeStore.length;
                }

                Employee[] employee = new Employee[totelEmpolyee];

                int[] empID = new int[totelEmpolyee];
                int[] empBankID = new int[totelEmpolyee];
                String[] empName = new String[totelEmpolyee];
                String[] empDateOfBirth = new String[totelEmpolyee];
                String[] empBloodGroup = new String[totelEmpolyee];
                String[] empReligion = new String[totelEmpolyee];
                String[] empMaritalStatus = new String[totelEmpolyee];
                String[] empEmail = new String[totelEmpolyee];
                String[] empJoinedDate = new String[totelEmpolyee];
                String[] empSex = new String[totelEmpolyee];
                String[] empPhone = new String[totelEmpolyee];
                String[] empAddress = new String[totelEmpolyee];
                String[] empDepartment = new String[totelEmpolyee];
                String[] empNID = new String[totelEmpolyee];
                double[] empSalary = new double[totelEmpolyee];
                String[] empComment = new String[totelEmpolyee];
                boolean[] empStates = new boolean[totelEmpolyee];

                for (int i = 0; i < totelEmpolyee - 1; i++) {
                    empID[i] = employeeStore[i].id;
                    empBankID[i] = employeeStore[i].bankID;
                    empName[i] = employeeStore[i].name;
                    empDateOfBirth[i] = employeeStore[i].dateOfBirth;
                    empBloodGroup[i] = employeeStore[i].bloodGroup;
                    empReligion[i] = employeeStore[i].religion;
                    empMaritalStatus[i] = employeeStore[i].maritalStatus;
                    empEmail[i] = employeeStore[i].email;
                    empJoinedDate[i] = employeeStore[i].joinedDate;
                    empSex[i] = employeeStore[i].sex;
                    empPhone[i] = employeeStore[i].phone;
                    empAddress[i] = employeeStore[i].address;
                    empDepartment[i] = employeeStore[i].department;
                    empNID[i] = employeeStore[i].NID;
                    empSalary[i] = employeeStore[i].salary;
                    empComment[i] = employeeStore[i].comment;
                    empStates[i] = employeeStore[i].states;
                    employee[i] = new Employee(empID[i], empBankID[i], empName[i], empDateOfBirth[i], empSex[i], empNID[i], empBloodGroup[i], empReligion[i], empMaritalStatus[i], empEmail[i], empJoinedDate[i], empPhone[i], empAddress[i], empDepartment[i], empSalary[i], empComment[i], empStates[i]);
                }

                // Notification Class
                // dataCheck
                Notification notification = new Notification();
                int foundDataSize = 0;
                int totelClient;

                ObjectInputStream inClient = null;
                ObjectOutputStream outClient = null;
                Client[] clientStore = null;
                try {
                    inClient = new ObjectInputStream(new FileInputStream("c:\\store\\client.txt"));
                    clientStore = (Client[]) inClient.readObject();
                } catch (FileNotFoundException ex) {
                    File file = new File("c:\\store");
                    file.mkdir();
                    outClient = new ObjectOutputStream(new FileOutputStream("c:\\store\\client.txt"));
                }

                if (clientStore == null) {
                    totelClient = 1;
                } else {
                    totelClient = clientStore.length;
                }
                // Client Object Array
                Client[] client = new Client[totelClient];

                int[] clientID = new int[totelClient];
                int[] clientBankID = new int[totelClient];
                String[] clientName = new String[totelClient];
                String[] clientEmail = new String[totelClient];
                String[] clientPhone = new String[totelClient];
                String[] clientSex = new String[totelClient];
                String[] clientAddress = new String[totelClient];
                String[] clientComment = new String[totelClient];

                for (int i = 0; i < totelClient - 1; i++) {
                    clientID[i] = clientStore[i].id;
                    clientBankID[i] = clientStore[i].bankID;
                    clientName[i] = clientStore[i].name;
                    clientEmail[i] = clientStore[i].email;
                    clientPhone[i] = clientStore[i].phone;
                    clientSex[i] = clientStore[i].sex;
                    clientAddress[i] = clientStore[i].address;
                    clientComment[i] = clientStore[i].comment;

                    client[i] = new Client(clientID[i], clientBankID[i], clientName[i], clientEmail[i], clientPhone[i], clientSex[i], clientAddress[i], clientComment[i]);
                }

                // Project Information
                int totelProject;
                ObjectInputStream inProject = null;
                ObjectOutputStream outProject = null;
                Project[] projectStore = null;

                try {
                    inProject = new ObjectInputStream(new FileInputStream("c:\\store\\project.txt"));
                    projectStore = (Project[]) inProject.readObject();
                } catch (FileNotFoundException ex) {
                    File file = new File("c:\\store");
                    file.mkdir();
                    outProject = new ObjectOutputStream(new FileOutputStream("c:\\store\\project.txt"));
                }

                if (projectStore == null) {
                    totelProject = 1;
                } else {
                    totelProject = projectStore.length;
                }

                int[] projectID = new int[totelProject];
                String[] projectName = new String[totelProject];
                String[] projectDescription = new String[totelProject];
                int[] projectDuration = new int[totelProject];
                String[] projectComment = new String[totelProject];

                // Project object Array
                Project[] project = new Project[totelProject];

                for (int i = 0; i < totelProject - 1; i++) {
                    projectID[i] = projectStore[i].projectID;
                    projectName[i] = projectStore[i].name;
                    projectDescription[i] = projectStore[i].descriotion;
                    projectComment[i] = projectStore[i].comment;

                    project[i] = new Project(projectID[i], projectName[i], projectDescription[i], projectComment[i]);
                }

                //Company Account 
                int totelRelation;
                ObjectInputStream inRelation = null;
                ObjectOutputStream outRelation = null;
                Relation[] relationStore = null;

                try {
                    inRelation = new ObjectInputStream(new FileInputStream("c:\\store\\relation.txt"));
                    relationStore = (Relation[]) inRelation.readObject();
                } catch (FileNotFoundException ex) {
                    File file = new File("c:\\store");
                    file.mkdir();
                    outRelation = new ObjectOutputStream(new FileOutputStream("c:\\store\\relation.txt"));
                }

                if (relationStore == null) {
                    totelRelation = 1;
                } else {
                    totelRelation = relationStore.length;
                }

                int[] relationID = new int[totelRelation];
                int[] relClientID = new int[totelRelation];
                int[] relEmployeeID = new int[totelRelation];
                int[] relProjectID = new int[totelRelation];
                String[] relAssignDate = new String[totelRelation];
                String[] relDeliveryDate = new String[totelRelation];
                int[] relProjectDuration = new int[totelRelation];
                double[] relProjectBudget = new double[totelRelation];
                String[] relJobStates = new String[totelRelation];

                Relation[] relation = new Relation[totelRelation];

                for (int i = 0; i < totelRelation - 1; i++) {
                    relationID[i] = relationStore[i].relationID;
                    relClientID[i] = relationStore[i].clientID;
                    relEmployeeID[i] = relationStore[i].employeeID;
                    relProjectID[i] = relationStore[i].projectID;
                    relAssignDate[i] = relationStore[i].assignDate;
                    relDeliveryDate[i] = relationStore[i].deliveryDate;
                    relProjectDuration[i] = relationStore[i].projectDuration;
                    relProjectBudget[i] = relationStore[i].clientBudget;
                    relJobStates[i] = relationStore[i].jobStates;

                    relation[i] = new Relation(relationID[i], relClientID[i], relEmployeeID[i], relProjectID[i], relAssignDate[i], relDeliveryDate[i],
                            relProjectDuration[i], relProjectBudget[i], relJobStates[i]);
                }

                int chooseOption = 0;

                while (true) {
                    System.out.println("### Company Admin Panel ###  (Enter the SERIAL Value for Following Action)");
                    System.out.println("1. Add New Employee");
                    System.out.println("2. View Employee");
                    System.out.println("3. Add New Project");
                    System.out.println("4. View Client");
                    System.out.println("5. Project States");
                    System.out.println("6. Submit or Refund Project");
                    System.out.println("7. Employee States");
                    System.out.println("8. Company Balance");
                    System.out.println("9. Salary");
                    System.out.println("10. Deposit");
                    System.out.println("11. Withdraw");
                    System.out.println("12. Internal Transfer");
                    System.out.println("13. Setting");
                    System.out.println("14. Exit\n");

                    System.out.print("Choose Here: ");
                    int action = checkRelation.getIntegerValue();

                    if (action == 1) {
                        System.out.println("\n### Please Select your Department...");
                        System.out.println("1. Admin");
                        System.out.println("2. Developer");
                        System.out.println("3. Back");
                        System.out.print("\nChoose Here: ");
                        int chooseDepartment = checkRelation.getIntegerValue();
                        int chooseDepartmentAction = 0;
                        boolean back = true;
                        switch (chooseDepartment) {
                            case 1:
                                System.out.println("### Please Select your Designation");
                                System.out.println("1. Chairman");
                                System.out.println("2. Managing Director");
                                System.out.println("3. Senior Admin & Accounts Officer");
                                System.out.println("4. Production Manager");
                                System.out.print("\nChoose Here: ");
                                chooseDepartmentAction = checkRelation.getIntegerValue();
                                switch (chooseDepartmentAction) {
                                    case 1:
                                        empDepartment[totelEmpolyee - 1] = "Chairman";
                                        break;
                                    case 2:
                                        empDepartment[totelEmpolyee - 1] = "Managing Director";
                                        break;
                                    case 3:
                                        empDepartment[totelEmpolyee - 1] = "Senior Admin & Accounts Officer";
                                        break;
                                    case 4:
                                        empDepartment[totelEmpolyee - 1] = "Production Manager";
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("### Please Select your Designation");
                                System.out.println("1. Senior Software Engineer");
                                System.out.println("2. Software engineer");
                                System.out.println("3. UI UX Designer");
                                System.out.println("4. Web Developer");
                                System.out.println("5. Front end developer");
                                System.out.print("\nChoose Here: ");
                                chooseDepartmentAction = checkRelation.getIntegerValue();
                                switch (chooseDepartmentAction) {
                                    case 1:
                                        empDepartment[totelEmpolyee - 1] = "Senior Software engineer";
                                        break;
                                    case 2:
                                        empDepartment[totelEmpolyee - 1] = "Software engineer";
                                        break;
                                    case 3:
                                        empDepartment[totelEmpolyee - 1] = "UI UX Designer";
                                        break;
                                    case 4:
                                        empDepartment[totelEmpolyee - 1] = "Web Developer";
                                        break;
                                    case 5:
                                        empDepartment[totelEmpolyee - 1] = "Front end developer";
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println();
                                back = false;
                                break;
                            default:
                                checkRelation.getPressedWrongKey();
                                back = false;
                                break;
                        }
                        if (back == true) {

                            System.out.println("### Employee Information");
                            empID[totelEmpolyee - 1] = totelEmpolyee;
                            // Bank ID and User
                            empBankID[totelEmpolyee - 1] = bankID[totalBankAccount - 1] = totalBankAccount;
                            bankUserID[totalBankAccount - 1] = companyInfo.getEmployeePrefix() + empID[totelEmpolyee - 1];
                            System.out.println("Empolyee ID No: " + companyInfo.getEmployeePrefix() + empID[totelEmpolyee - 1]);
                            System.out.println("Bank ID No: " + companyInfo.getBankPrefix() + bankID[totalBankAccount - 1]);
                            System.out.print("Name: ");
                            //empName[totelEmpolyee - 1] = src.nextLine();
                            empName[totelEmpolyee - 1] = src.nextLine();
                            System.out.print("Date Of Birth: ");
                            empDateOfBirth[totelEmpolyee - 1] = src.nextLine();
                            System.out.println("Sex: ");
                            System.out.println("1. Male");
                            System.out.println("2. Female");
                            System.out.print("Choose Here: ");
                            int chooseSex = checkRelation.getIntegerValue();
                            switch (chooseSex) {
                                case 1:
                                    empSex[totelEmpolyee - 1] = "Male";
                                    break;
                                case 2:
                                    empSex[totelEmpolyee - 1] = "Female";
                                    break;
                                case 3:
                                    empSex[totelEmpolyee - 1] = "Custom";
                                    break;
                            }

                            System.out.print("NID No: ");
                            empNID[totelEmpolyee - 1] = src.nextLine();

                            System.out.println("Blood Group: ");
                            System.out.println("1. A+");
                            System.out.println("2. B+");
                            System.out.println("3. AB+");
                            System.out.println("4. O+");
                            System.out.println("5. A-");
                            System.out.println("6. B-");
                            System.out.println("7. AB-");
                            System.out.println("8. O-");
                            System.out.print("\nChoose Here: ");
                            int chooseBloodGroup = checkRelation.getIntegerValue();
                            switch (chooseBloodGroup) {
                                case 1:
                                    empBloodGroup[totelEmpolyee - 1] = "A+";
                                    break;
                                case 2:
                                    empBloodGroup[totelEmpolyee - 1] = "B+";
                                    break;
                                case 3:
                                    empBloodGroup[totelEmpolyee - 1] = "AB+";
                                    break;
                                case 4:
                                    empBloodGroup[totelEmpolyee - 1] = "O+";
                                    break;
                                case 5:
                                    empBloodGroup[totelEmpolyee - 1] = "A-";
                                    break;
                                case 6:
                                    empBloodGroup[totelEmpolyee - 1] = "B-";
                                    break;
                                case 7:
                                    empBloodGroup[totelEmpolyee - 1] = "AB-";
                                    break;
                                case 8:
                                    empBloodGroup[totelEmpolyee - 1] = "O-";
                                    break;
                            }
                            System.out.println("Religion: ");
                            System.out.println("1. Islam");
                            System.out.println("2. Christianity");
                            System.out.println("3. Hinduism");
                            System.out.println("4. Buddhism");

                            System.out.print("\nChoose Here: ");
                            int chooseReligion = checkRelation.getIntegerValue();
                            switch (chooseReligion) {
                                case 1:
                                    empReligion[totelEmpolyee - 1] = "Islam";
                                    break;
                                case 2:
                                    empReligion[totelEmpolyee - 1] = "Christianity";
                                    break;
                                case 3:
                                    empReligion[totelEmpolyee - 1] = "Hinduism";
                                    break;
                                case 4:
                                    empReligion[totelEmpolyee - 1] = "Buddhism";
                                    break;
                            }

                            System.out.println("Marital Status: ");
                            System.out.println("1. Single ");
                            System.out.println("2. Married");
                            System.out.println("3. Separated ");
                            System.out.println("4. Divorced");

                            System.out.print("\nChoose Here: ");
                            int chooseMaritalStatus = checkRelation.getIntegerValue();
                            switch (chooseMaritalStatus) {
                                case 1:
                                    empMaritalStatus[totelEmpolyee - 1] = "Single";
                                    break;
                                case 2:
                                    empMaritalStatus[totelEmpolyee - 1] = "Married";
                                    break;
                                case 3:
                                    empMaritalStatus[totelEmpolyee - 1] = "Separated";
                                    break;
                                case 4:
                                    empMaritalStatus[totelEmpolyee - 1] = "Divorced";
                                    break;
                            }

                            System.out.print("Email Address: ");
                            //empEmail[totelEmpolyee - 1] = src.nextLine();
                            empEmail[totelEmpolyee - 1] = src.nextLine();
                            System.out.print("Joined Date: ");
                            empJoinedDate[totelEmpolyee - 1] = src.nextLine();
                            System.out.print("Mobile: ");
                            empPhone[totelEmpolyee - 1] = src.nextLine();
                            System.out.print("Address: ");
                            empAddress[totelEmpolyee - 1] = src.nextLine();

                            System.out.print("Salary: ");
                            empSalary[totelEmpolyee - 1] = checkRelation.getDoubleValue();

                            System.out.print("Comment: ");
                            //src.nextLine();
                            empComment[totelEmpolyee - 1] = src.nextLine();
                            empStates[totelEmpolyee - 1] = true;

                            // Empolyee Tempory Backup Information
                            int[] empID_temp = new int[totelEmpolyee + 1];
                            int[] empBankID_temp = new int[totelEmpolyee + 1];
                            String[] empName_temp = new String[totelEmpolyee + 1];
                            String[] empDateOfBirth_temp = new String[totelEmpolyee + 1];
                            String[] empBloodGroup_temp = new String[totelEmpolyee + 1];
                            String[] empReligion_temp = new String[totelEmpolyee + 1];
                            String[] empMaritalStatus_temp = new String[totelEmpolyee + 1];
                            String[] empEmail_temp = new String[totelEmpolyee + 1];
                            String[] empJoinedDate_temp = new String[totelEmpolyee + 1];
                            String[] empSex_temp = new String[totelEmpolyee + 1];
                            String[] empPhone_temp = new String[totelEmpolyee + 1];
                            String[] empAddress_temp = new String[totelEmpolyee + 1];
                            String[] empDepartment_temp = new String[totelEmpolyee + 1];
                            String[] empNID_temp = new String[totelEmpolyee + 1];
                            double[] empSalary_temp = new double[totelEmpolyee + 1];
                            String[] empComment_temp = new String[totelEmpolyee + 1];
                            boolean[] empStates_temp = new boolean[totelEmpolyee + 1];

                            //Employee Temporary Backup Object Array
                            Employee[] employee_temp = new Employee[totelEmpolyee + 1];
                            //int i = 0;
                            for (int i = 0; i < totelEmpolyee; i++) {
                                empID_temp[i] = empID[i];
                                empBankID_temp[i] = empBankID[i];
                                empName_temp[i] = empName[i];
                                empDateOfBirth_temp[i] = empDateOfBirth[i];
                                empBloodGroup_temp[i] = empBloodGroup[i];
                                empReligion_temp[i] = empReligion[i];
                                empMaritalStatus_temp[i] = empMaritalStatus[i];
                                empEmail_temp[i] = empEmail[i];
                                empJoinedDate_temp[i] = empJoinedDate[i];
                                empSex_temp[i] = empSex[i];
                                empPhone_temp[i] = empPhone[i];
                                empAddress_temp[i] = empAddress[i];
                                empDepartment_temp[i] = empDepartment[i];
                                empNID_temp[i] = empNID[i];
                                empSalary_temp[i] = empSalary[i];
                                empComment_temp[i] = empComment[i];
                                empStates_temp[i] = empStates[i];
                                employee_temp[i] = new Employee(empID_temp[i], empBankID_temp[i], empName_temp[i], empDateOfBirth_temp[i], empSex_temp[i],
                                        empNID_temp[i], empBloodGroup_temp[i], empReligion_temp[i], empMaritalStatus_temp[i], empEmail_temp[i],
                                        empJoinedDate_temp[i], empPhone_temp[i], empAddress_temp[i], empDepartment_temp[i], empSalary_temp[i], empComment_temp[i], empStates_temp[i]);
                            }

                            totelEmpolyee++;

                            employee = new Employee[totelEmpolyee];
                            empID = new int[totelEmpolyee];
                            empBankID = new int[totelEmpolyee];
                            empName = new String[totelEmpolyee];
                            empDateOfBirth = new String[totelEmpolyee];
                            empBloodGroup = new String[totelEmpolyee];
                            empReligion = new String[totelEmpolyee];
                            empMaritalStatus = new String[totelEmpolyee];
                            empEmail = new String[totelEmpolyee];
                            empJoinedDate = new String[totelEmpolyee];
                            empSex = new String[totelEmpolyee];
                            empPhone = new String[totelEmpolyee];
                            empAddress = new String[totelEmpolyee];
                            empDepartment = new String[totelEmpolyee];
                            empNID = new String[totelEmpolyee];
                            empSalary = new double[totelEmpolyee];
                            empComment = new String[totelEmpolyee];
                            empStates = new boolean[totelEmpolyee];

                            for (int i = 0; i < totelEmpolyee - 1; i++) {
                                empID[i] = empID_temp[i];
                                empBankID[i] = empBankID_temp[i];
                                empName[i] = empName_temp[i];
                                empDateOfBirth[i] = empDateOfBirth_temp[i];
                                empBloodGroup[i] = empBloodGroup_temp[i];
                                empReligion[i] = empReligion_temp[i];
                                empMaritalStatus[i] = empMaritalStatus_temp[i];
                                empEmail[i] = empEmail_temp[i];
                                empJoinedDate[i] = empJoinedDate_temp[i];
                                empSex[i] = empSex_temp[i];
                                empPhone[i] = empPhone_temp[i];
                                empAddress[i] = empAddress_temp[i];
                                empDepartment[i] = empDepartment_temp[i];
                                empNID[i] = empNID_temp[i];
                                empSalary[i] = empSalary_temp[i];
                                empComment[i] = empComment_temp[i];
                                empStates[i] = empStates_temp[i];
                                employee[i] = new Employee(empID[i], empBankID[i], empName[i], empDateOfBirth[i], empSex[i], empNID[i], empBloodGroup[i],
                                        empReligion[i], empMaritalStatus[i], empEmail[i], empJoinedDate[i], empPhone[i], empAddress[i], empDepartment[i],
                                        empSalary[i], empComment[i], empStates[i]);
                            }

                            // Bank Account Tempory Backup
                            int[] bankID_temp = new int[totalBankAccount + 1];
                            String[] bankUserID_temp = new String[totalBankAccount + 1];
                            double[] bankBalance_temp = new double[totalBankAccount + 1];
                            double[] bankPendingBalance_temp = new double[totalBankAccount + 1];

                            BankAccount[] bankAccount_temp = new BankAccount[totalBankAccount];

                            for (int i = 0; i < totalBankAccount; i++) {
                                bankID_temp[i] = bankID[i];
                                bankUserID_temp[i] = bankUserID[i];
                                bankBalance_temp[i] = bankBalance[i];
                                bankPendingBalance_temp[i] = bankPendingBalance[i];

                                bankAccount_temp[i] = new BankAccount(bankID_temp[i], bankUserID_temp[i], bankBalance_temp[i], bankPendingBalance_temp[i]);
                            }

                            totalBankAccount++;
                            bankAccount = new BankAccount[totalBankAccount];

                            bankID = new int[totalBankAccount];
                            bankUserID = new String[totalBankAccount];
                            bankBalance = new double[totalBankAccount];
                            bankPendingBalance = new double[totalBankAccount];

                            for (int i = 0; i < totalBankAccount; i++) {
                                bankID[i] = bankID_temp[i];
                                bankBalance[i] = bankBalance_temp[i];
                                bankUserID[i] = bankUserID_temp[i];
                                bankPendingBalance[i] = bankPendingBalance_temp[i];

                                bankAccount[i] = new BankAccount(bankID[i], bankUserID[i], bankBalance[i], bankPendingBalance[i]);
                            }
                            //checkRelation.setSaveAllDataWithMassage("Employee Add Successful", companyInfo, bankAccount, employee, client, project, relation);
                            checkRelation.setSaveData(employee);
                            checkRelation.setSaveData(bankAccount);
                            checkRelation.getMessageContinue("Employee Add Successful");
                        }

                    } else if (action == 2) {
                        //int searchEmployee = 0;
                        System.out.println("### View Employee Details");
                        System.out.println("1. by ID");
                        System.out.println("2. by Name");
                        System.out.println("3. by Department");
                        System.out.println("4. by Blood Group");
                        System.out.println("5. by Religion");
                        System.out.println("6. by Mobile");
                        System.out.println("7. by Email");
                        System.out.println("8. View All Employee");
                        System.out.println("9. back");

                        System.out.print("\nChoose Here: ");
                        int detailsEmployee = checkRelation.getIntegerValue();

                        checkRelation = new RelationalData(companyInfo, employee, bankAccount);
                        if (detailsEmployee == 1) {
                            System.out.print("### Please Enter your Employee ID: " + companyInfo.getEmployeePrefix());
                            int searchEmployee = checkRelation.getIntegerValue();

                            for (int i = 0; i < employee.length - 1; i++) {
                                if (i == totelEmpolyee) {
                                    checkRelation.getMessage("Employee ID not Found...");
                                } else {

                                    if (searchEmployee == employee[i].id) {
                                        checkRelation.getEmployeeInfo(i);
                                        notification.setDataCheck(true);
                                        foundDataSize++;
                                        break;
                                    }
                                }
                            }
                            notification.getDataNotFound("Employee", "ID", foundDataSize);
                            foundDataSize = 0;

                        } else if (detailsEmployee == 2) {
                            System.out.println("### Please Enter your Employee Name to view details:- ");
                            System.out.print("Employee Name: ");
                            String searchEmployee = src.nextLine();
                            //searchEmployee = src.nextLine();
                            for (int i = 0; i < employee.length - 1; i++) {
                                if (i == totelEmpolyee) {
                                    checkRelation.getMessage("Employee Name not Found...");

                                } else {
                                    if (searchEmployee.equalsIgnoreCase(employee[i].name)) {
                                        //employee[i].getEmployeeInfo();
                                        checkRelation.getEmployeeInfo(i);
                                        notification.setDataCheck(true);
                                        foundDataSize++;
                                    }
                                }
                            }

                            notification.getDataNotFound("Employee", "ID", foundDataSize);
                            foundDataSize = 0;

                        } else if (detailsEmployee == 3) {
                            String searchEmployee = "";
                            System.out.println("### Please Choose your Employee Department to view details:-");

                            System.out.println("1. Admin");
                            System.out.println("2. Developer");
                            System.out.println("3. Back");
                            System.out.print("\nChoose Here: ");
                            int chooseDepartment = checkRelation.getIntegerValue();
                            int chooseDepartmentAction = 0;

                            switch (chooseDepartment) {
                                case 1:
                                    System.out.println("### Please Select your Designation");
                                    System.out.println("1. Chairman");
                                    System.out.println("2. Managing Director");
                                    System.out.println("3. Senior Admin & Accounts Officer");
                                    System.out.println("4. Production Manager");
                                    System.out.print("\nChoose Here: ");
                                    chooseDepartmentAction = checkRelation.getIntegerValue();
                                    switch (chooseDepartmentAction) {
                                        case 1:
                                            searchEmployee = "Chairman";
                                            break;
                                        case 2:
                                            searchEmployee = "Managing Director";
                                            break;
                                        case 3:
                                            searchEmployee = "Senior Admin & Accounts Officer";
                                            break;
                                        case 4:
                                            searchEmployee = "Production Manager";
                                            break;
                                    }
                                    break;
                                case 2:
                                    System.out.println("### Please Select your Designation");
                                    System.out.println("1. Senior Software Engineer");
                                    System.out.println("2. Software engineer");
                                    System.out.println("3. UI UX Designer");
                                    System.out.println("4. Web Developer");
                                    System.out.println("5. Front end developer");
                                    System.out.print("\nChoose Here: ");
                                    chooseDepartmentAction = checkRelation.getIntegerValue();
                                    switch (chooseDepartmentAction) {
                                        case 1:
                                            searchEmployee = "Senior Software engineer";
                                            break;
                                        case 2:
                                            searchEmployee = "Software engineer";
                                            break;
                                        case 3:
                                            searchEmployee = "UI UX Designer";
                                            break;
                                        case 4:
                                            searchEmployee = "Web Developer";
                                            break;
                                        case 5:
                                            searchEmployee = "Front end developer";
                                            break;
                                    }
                                    break;
                            }

                            for (int i = 0; i < employee.length - 1; i++) {
                                if (i == totelEmpolyee) {
                                    checkRelation.getMessage("Empolyee Not Found....");
                                } else {
                                    if (searchEmployee.equalsIgnoreCase(employee[i].department)) {
                                        //employee[i].getEmployeeInfo();
                                        checkRelation.getEmployeeInfo(i);
                                        notification.setDataCheck(true);
                                        foundDataSize++;
                                    }
                                }

                            }

                            notification.getDataNotFound("Employee", "ID", foundDataSize);
                            foundDataSize = 0;

                        } else if (detailsEmployee == 4) {
                            System.out.println("### Please choose your blood group to view match all employee:-");
                            String searchEmployee = "";
                            System.out.println("1. A+");
                            System.out.println("2. B+");
                            System.out.println("3. AB+");
                            System.out.println("4. O+");
                            System.out.println("5. A-");
                            System.out.println("6. B-");
                            System.out.println("7. AB-");
                            System.out.println("8. O-");
                            System.out.print("\nChoose Here: ");
                            int chooseBloodGroup = checkRelation.getIntegerValue();
                            switch (chooseBloodGroup) {
                                case 1:
                                    searchEmployee = "A+";
                                    break;
                                case 2:
                                    searchEmployee = "B+";
                                    break;
                                case 3:
                                    searchEmployee = "AB+";
                                    break;
                                case 4:
                                    searchEmployee = "O+";
                                    break;
                                case 5:
                                    searchEmployee = "A-";
                                    break;
                                case 6:
                                    searchEmployee = "B-";
                                    break;
                                case 7:
                                    searchEmployee = "AB-";
                                    break;
                                case 8:
                                    searchEmployee = "O-";
                                    break;
                            }

                            for (int i = 0; i < employee.length - 1; i++) {

                                if (i == totelEmpolyee) {
                                    checkRelation.getMessage("Blood Group Does Not Match...");
                                } else {
                                    if (searchEmployee.equalsIgnoreCase(employee[i].bloodGroup)) {
                                        //employee[i].getEmployeeInfo();
                                        checkRelation.getEmployeeInfo(i);
                                        notification.setDataCheck(true);
                                        foundDataSize++;
                                    }
                                }

                            }
                            notification.getDataNotFound("Employee", "ID", foundDataSize);
                            foundDataSize = 0;

                        } else if (detailsEmployee == 5) {
                            String searchEmployee = "";
                            System.out.println("### Please Choose Your Employee Religion to view details:-");
                            System.out.println("1. Islam");
                            System.out.println("2. Christianity");
                            System.out.println("3. Hinduism");
                            System.out.println("4. Buddhism");

                            System.out.print("\nChoose Here: ");
                            int chooseReligion = checkRelation.getIntegerValue();
                            switch (chooseReligion) {
                                case 1:
                                    searchEmployee = "Islam";
                                    break;
                                case 2:
                                    searchEmployee = "Christianity";
                                    break;
                                case 3:
                                    searchEmployee = "Hinduism";
                                    break;
                                case 4:
                                    searchEmployee = "Buddhism";
                                    break;
                            }

                            for (int i = 0; i < employee.length - 1; i++) {

                                if (i == totelEmpolyee) {
                                    checkRelation.getMessage("Employee not Found...");

                                } else {
                                    if (searchEmployee.equalsIgnoreCase(employee[i].religion)) {
                                        //employee[i].getEmployeeInfo();
                                        checkRelation.getEmployeeInfo(i);
                                        notification.setDataCheck(true);
                                        foundDataSize++;
                                    }
                                }
                            }
                            notification.getDataNotFound("Employee", "ID", foundDataSize);
                            foundDataSize = 0;

                        } else if (detailsEmployee == 6) {
                            System.out.print("### Enter your employee phone number to view details:-");
                            String searchEmployee = src.nextLine();
                            //searchEmployee = src.nextLine();
                            for (int i = 0; i < employee.length - 1; i++) {
                                if (i == totelEmpolyee) {
                                    checkRelation.getMessage("Employee not Found...");

                                } else {
                                    if (searchEmployee.equalsIgnoreCase(employee[i].phone)) {
                                        //employee[i].getEmployeeInfo();
                                        checkRelation.getEmployeeInfo(i);
                                        notification.setDataCheck(true);
                                        foundDataSize++;

                                        break;
                                    }
                                }
                            }
                            notification.getDataNotFound("Employee", "ID", foundDataSize);
                            foundDataSize = 0;

                        } else if (detailsEmployee == 7) {
                            System.out.println("### Enter your employee Email address to view details.");
                            System.out.print("Email: ");
                            String searchEmployee = src.nextLine();
                            //searchEmployee = src.nextLine();
                            for (int i = 0; i < employee.length - 1; i++) {
                                if (i == totelEmpolyee) {
                                    checkRelation.getMessage("Employee not Found...");

                                } else {
                                    if (searchEmployee.equalsIgnoreCase(employee[i].email)) {
                                        //employee[i].getEmployeeInfo();
                                        checkRelation.getEmployeeInfo(i);
                                        notification.setDataCheck(true);
                                        foundDataSize++;

                                        break;
                                    }
                                }
                            }
                            notification.getDataNotFound("Employee", "ID", foundDataSize);
                            foundDataSize = 0;

                        } else if (detailsEmployee == 8) {
                            System.out.print("### All Employee to view details:- \n");

                            for (int i = 0; i < employee.length - 1; i++) {
                                //employee[i].getEmployeeInfo();
                                checkRelation.getEmployeeInfo(i);
                                notification.setDataCheck(true);
                                foundDataSize++;

                            }
                            System.out.println();
                            notification.getDataNotFound("Employee", "ID", foundDataSize);
                            foundDataSize = 0;

                        } else if (detailsEmployee == 9) {
                            System.out.println();
                        } else {
                            checkRelation.getPressedWrongKey();
                        }
                    } else if (action == 3) {
                        System.out.println("1. Add New Client");
                        System.out.println("2. Existing Client");
                        System.out.println("3. Back");
                        System.out.print("\nChoose Here: ");
                        chooseOption = checkRelation.getIntegerValue();

                        if (chooseOption == 1) {
                            System.out.println("### Please Add Client Information");
                            // Client Information
                            clientID[totelClient - 1] = totelClient;
                            System.out.println("Client ID: " + companyInfo.getClientPrefix() + clientID[totelClient - 1]);

                            //Bank Account Id And UserID
                            clientBankID[totelClient - 1] = bankID[totalBankAccount - 1] = totalBankAccount;
                            bankUserID[totalBankAccount - 1] = companyInfo.getClientPrefix() + clientID[totelClient - 1];
                            System.out.println("Client Bank ID: " + companyInfo.getBankPrefix() + bankID[totalBankAccount - 1]);

                            relClientID[totelRelation - 1] = totelClient;
                            System.out.print("Client Name: ");
                            //clientName[totelClient - 1] = src.nextLine();
                            clientName[totelClient - 1] = src.nextLine();
                            System.out.print("Client Email: ");
                            clientEmail[totelClient - 1] = src.nextLine();
                            System.out.print("Client Phone: ");
                            clientPhone[totelClient - 1] = src.nextLine();
                            System.out.println("Sex: ");
                            System.out.println("1. Male");
                            System.out.println("2. Female");
                            System.out.println("3. Custom");
                            System.out.print("Choose here: ");
                            int chooseSex = checkRelation.getIntegerValue();
                            switch (chooseSex) {
                                case 1:
                                    clientSex[totelClient - 1] = "Male";
                                    break;
                                case 2:
                                    clientSex[totelClient - 1] = "Female";
                                    break;
                                case 3:
                                    clientSex[totelClient - 1] = "Custom";
                            }

                            System.out.print("Client Address: ");
                            //src.nextLine();
                            clientAddress[totelClient - 1] = src.nextLine();
                            System.out.print("Client Add Balance: ");
                            bankBalance[totalBankAccount - 1] = checkRelation.getDoubleValue();
                            clientAddress[totelClient - 1] = src.nextLine();

                            System.out.print("Comment: ");
                            clientComment[totelClient - 1] = src.nextLine();

                            //Project Information
                            System.out.println("\n### Please Enter your Project Information");
                            System.out.println("Project Id: " + companyInfo.getProjectPrefix() + totelProject);
                            projectID[totelProject - 1] = totelProject;
                            relProjectID[totelRelation - 1] = projectID[totelProject - 1];
                            System.out.println("Client ID: " + companyInfo.getClientPrefix() + clientID[totelClient - 1]);
                            System.out.print("Project Name: ");
                            projectName[totelProject - 1] = src.nextLine();
                            System.out.print("Project Description: ");
                            projectDescription[totelProject - 1] = src.nextLine();
                            System.out.print("Project Duration(Day): ");
                            relProjectDuration[totelRelation - 1] = checkRelation.getIntegerValue();
                            relAssignDate[totelRelation - 1] = dateAndTime;

                            System.out.print("Budget(USD): ");
                            relProjectBudget[totelRelation - 1] = checkRelation.getDoubleValue();
                            boolean dataSaveOrNotCheck = false;
                            String jobAssignMessage = null;
                            while (true) {
                                if (relProjectBudget[totelRelation - 1] <= bankBalance[totalBankAccount - 1]) {
                                    //Balance tanfar client Pending accent 
                                    bankPendingBalance[totalBankAccount - 1] = bankPendingBalance[totalBankAccount - 1] + relProjectBudget[totelRelation - 1];
                                    bankBalance[totalBankAccount - 1] = bankBalance[totalBankAccount - 1] - relProjectBudget[totelRelation - 1];

                                    System.out.print("Comment: ");
                                    projectComment[totelProject - 1] = src.nextLine();
                                    //projectComment[totelProject - 1] = src.nextLine();

                                    boolean checkEmployeeID = true;
                                    while (checkEmployeeID) {
                                        boolean dataNotFoundCheck = false;
                                        System.out.print("\nEmployee ID: " + companyInfo.getEmployeePrefix());
                                        int employeeCheck = checkRelation.getIntegerValue();
                                        for (int i = 0; i < totelEmpolyee - 1; i++) {
                                            if (employeeCheck == employee[i].id) {
                                                //System.out.println("--------------------------------------------------------------------------");
                                                checkRelation.getSeparatorBefore();
                                                System.out.println("ID No: " + employee[i].id + " Name: " + employee[i].name + " Email: " + employee[i].email);
                                                //System.out.println("--------------------------------------------------------------------------");
                                                checkRelation.getSeparatorAfter();
                                                relEmployeeID[totelRelation - 1] = employee[i].id;
                                                dataNotFoundCheck = true;
                                            }
                                        }
                                        if (dataNotFoundCheck == true) {
                                            System.out.println("The Employee information is OK?");
                                            System.out.println("1. Yes");
                                            System.out.println("2. No");
                                            System.out.print("Choose Here: ");
                                            int employeeCheckOption = checkRelation.getIntegerValue();
                                            if (employeeCheckOption == 1) {
                                                checkEmployeeID = false;
                                            } else if (employeeCheckOption == 2) {
                                                checkEmployeeID = true;
                                            } else {
                                                checkRelation.getPressedWrongKey();
                                            }
                                        } else {
                                            checkRelation.getMessage("Employee Id Not Found... Please Try Again...");
                                            System.out.print("\nEnter The valid ");
                                        }

                                    }
                                    relJobStates[totelRelation - 1] = "Pending";

                                    // project Temporary Backup Array
                                    int[] projectID_temp = new int[totelProject + 1];
                                    String[] projectName_temp = new String[totelProject + 1];
                                    String[] projectDescription_temp = new String[totelProject + 1];

                                    String[] projectComment_temp = new String[totelProject + 1];

                                    Project[] project_temp = new Project[totelProject];

                                    for (int i = 0; i < totelProject; i++) {
                                        projectID_temp[i] = projectID[i];
                                        projectName_temp[i] = projectName[i];
                                        projectDescription_temp[i] = projectDescription[i];

                                        projectComment_temp[i] = projectComment[i];
                                        project_temp[i] = new Project(projectID_temp[i], projectName_temp[i], projectDescription_temp[i], projectComment_temp[i]);

                                    }

                                    totelProject++;

                                    projectID = new int[totelProject];
                                    projectName = new String[totelProject];
                                    projectDescription = new String[totelProject];
                                    projectDuration = new int[totelProject];
                                    projectComment = new String[totelProject];

                                    project = new Project[totelProject];

                                    for (int i = 0; i < totelProject; i++) {
                                        projectID[i] = projectID_temp[i];
                                        projectName[i] = projectName_temp[i];
                                        projectDescription[i] = projectDescription_temp[i];
                                        projectComment[i] = projectComment_temp[i];
                                        project[i] = new Project(projectID[i], projectName[i], projectDescription[i], projectComment[i]);

                                    }

                                    relationID[totelRelation - 1] = totelRelation;

                                    // Relation Temporary Backup
                                    int[] relationID_temp = new int[totelRelation + 1];
                                    int[] relClientID_temp = new int[totelRelation + 1];
                                    int[] relEmployeeID_temp = new int[totelRelation + 1];
                                    int[] relProjectID_temp = new int[totelRelation + 1];
                                    String[] relAssignDate_temp = new String[totelRelation + 1];
                                    String[] relDeliveryDate_temp = new String[totelRelation + 1];
                                    int[] relProjectDuration_temp = new int[totelRelation + 1];
                                    double[] relProjectBudget_temp = new double[totelRelation + 1];
                                    String[] relJobStates_temp = new String[totelRelation + 1];

                                    Relation[] relation_temp = new Relation[totelRelation];

                                    for (int i = 0; i < totelRelation; i++) {
                                        relationID_temp[i] = relationID[i];
                                        relClientID_temp[i] = relClientID[i];
                                        relEmployeeID_temp[i] = relEmployeeID[i];
                                        relProjectID_temp[i] = relProjectID[i];
                                        relAssignDate_temp[i] = relAssignDate[i];
                                        relDeliveryDate_temp[i] = relDeliveryDate[i];
                                        relProjectDuration_temp[i] = relProjectDuration[i];
                                        relProjectBudget_temp[i] = relProjectBudget[i];
                                        relJobStates_temp[i] = relJobStates[i];
                                        relation_temp[i] = new Relation(relationID_temp[i], relClientID_temp[i], relEmployeeID_temp[i], relProjectID_temp[i], relAssignDate_temp[i], relDeliveryDate_temp[i],
                                                relProjectDuration_temp[i], relProjectBudget_temp[i], relJobStates_temp[i]);
                                    }

                                    totelRelation++;
                                    relation = new Relation[totelRelation];

                                    relationID = new int[totelRelation];
                                    relClientID = new int[totelRelation];
                                    relEmployeeID = new int[totelRelation];
                                    relProjectID = new int[totelRelation];
                                    relAssignDate = new String[totelRelation];
                                    relDeliveryDate = new String[totelRelation];
                                    relProjectDuration = new int[totelRelation];
                                    relProjectBudget = new double[totelRelation];
                                    relJobStates = new String[totelRelation];

                                    for (int i = 0; i < totelRelation; i++) {
                                        relationID[i] = relationID_temp[i];
                                        relClientID[i] = relClientID_temp[i];
                                        relEmployeeID[i] = relEmployeeID_temp[i];
                                        relProjectID[i] = relProjectID_temp[i];
                                        relAssignDate[i] = relAssignDate_temp[i];
                                        relDeliveryDate[i] = relDeliveryDate_temp[i];
                                        relProjectDuration[i] = relProjectDuration_temp[i];
                                        relProjectBudget[i] = relProjectBudget_temp[i];
                                        relJobStates[i] = relJobStates_temp[i];

                                        relation[i] = new Relation(relationID[i], relClientID[i], relEmployeeID[i], relProjectID[i], relAssignDate[i], relDeliveryDate[i],
                                                relProjectDuration[i], relProjectBudget[i], relJobStates[i]);
                                    }
                                    dataSaveOrNotCheck = true;
                                    jobAssignMessage = "New Job " + projectName[totelProject - 2] + " Assign Time: " + dateAndTime;
                                    break;

                                } else {
                                    checkRelation.getMessage("Not Enough Money");
                                    System.out.println("Your Current Balance Is: " + bankBalance[totalBankAccount - 1] + " USD");
                                    System.out.println("Need Money: " + (relProjectBudget[totelRelation - 1] - bankBalance[totalBankAccount - 1]) + " USD");

                                    System.out.println("\n### Please Add Money.");
                                    System.out.println("1. Add Money.");
                                    System.out.println("2. Back");
                                    System.out.print("choose here: ");
                                    chooseOption = checkRelation.getIntegerValue();
                                    if (chooseOption == 1) {
                                        System.out.print("Amount here: ");
                                        double additionalAmount = checkRelation.getDoubleValue();
                                        bankBalance[totalBankAccount - 1] = bankBalance[totalBankAccount - 1] + additionalAmount;
                                    } else if (chooseOption == 2) {
                                        checkRelation.getMessage("Client Add Successful But Project Not Assign");
                                        break;
                                    }

                                }
                            }

                            // Client Temporary Backup Array
                            int[] clientID_temp = new int[totelClient + 1];
                            int[] clientBankID_temp = new int[totelClient + 1];
                            String[] clientName_temp = new String[totelClient + 1];
                            String[] clientEmail_temp = new String[totelClient + 1];
                            String[] clientPhone_temp = new String[totelClient + 1];
                            String[] clientSex_temp = new String[totelClient + 1];
                            String[] clientAddress_temp = new String[totelClient + 1];
                            String[] clientComment_temp = new String[totelClient + 1];

                            // Client Temporary BAck Object Array
                            Client[] client_temp = new Client[totelClient];

                            for (int i = 0; i < totelClient; i++) {
                                clientID_temp[i] = clientID[i];
                                clientBankID_temp[i] = clientBankID[i];
                                clientName_temp[i] = clientName[i];
                                clientEmail_temp[i] = clientEmail[i];
                                clientPhone_temp[i] = clientPhone[i];
                                clientSex_temp[i] = clientSex[i];
                                clientAddress_temp[i] = clientAddress[i];
                                clientComment_temp[i] = clientComment[i];

                                client_temp[i] = new Client(clientID_temp[i], clientBankID_temp[i], clientName_temp[i], clientEmail_temp[i], clientPhone_temp[i], clientSex_temp[i], clientAddress_temp[i], clientComment_temp[i]);
                            }

                            //Client increase
                            totelClient++;
                            client = new Client[totelClient];

                            clientID = new int[totelClient];
                            clientBankID = new int[totelClient];
                            clientName = new String[totelClient];
                            clientEmail = new String[totelClient];
                            clientPhone = new String[totelClient];
                            clientSex = new String[totelClient];
                            clientAddress = new String[totelClient];
                            clientComment = new String[totelClient];

                            for (int i = 0; i < totelClient; i++) {
                                clientID[i] = clientID_temp[i];
                                clientBankID[i] = clientBankID_temp[i];
                                clientName[i] = clientName_temp[i];
                                clientEmail[i] = clientEmail_temp[i];
                                clientPhone[i] = clientPhone_temp[i];
                                clientSex[i] = clientSex_temp[i];
                                clientAddress[i] = clientAddress_temp[i];
                                clientComment[i] = clientComment_temp[i];

                                client[i] = new Client(clientID[i], clientBankID[i], clientName[i], clientEmail[i], clientPhone[i], clientSex[i], clientAddress[i], clientComment[i]);
                            }

                            // Bank Account Tempory Backup
                            int[] bankID_temp = new int[totalBankAccount + 1];
                            String[] bankUserID_temp = new String[totalBankAccount + 1];
                            double[] bankBalance_temp = new double[totalBankAccount + 1];
                            double[] bankPendingBalance_temp = new double[totalBankAccount + 1];

                            BankAccount[] bankAccount_temp = new BankAccount[totalBankAccount];

                            for (int i = 0; i < totalBankAccount; i++) {
                                bankID_temp[i] = bankID[i];
                                bankUserID_temp[i] = bankUserID[i];
                                bankBalance_temp[i] = bankBalance[i];
                                bankPendingBalance_temp[i] = bankPendingBalance[i];

                                bankAccount_temp[i] = new BankAccount(bankID_temp[i], bankUserID_temp[i], bankBalance_temp[i], bankPendingBalance_temp[i]);
                            }

                            totalBankAccount++;

                            bankID = new int[totalBankAccount];
                            bankUserID = new String[totalBankAccount];
                            bankBalance = new double[totalBankAccount];
                            bankPendingBalance = new double[totalBankAccount];

                            bankAccount = new BankAccount[totalBankAccount];

                            for (int i = 0; i < totalBankAccount; i++) {
                                bankID[i] = bankID_temp[i];
                                bankUserID[i] = bankUserID_temp[i];
                                bankBalance[i] = bankBalance_temp[i];
                                bankPendingBalance[i] = bankPendingBalance_temp[i];

                                bankAccount[i] = new BankAccount(bankID[i], bankUserID[i], bankBalance[i], bankPendingBalance[i]);
                            }
                            if (dataSaveOrNotCheck) {
                                //checkRelation.setSaveAllDataWithMassage(jobAssignMessage, companyInfo, bankAccount, employee, client, project, relation);
                                checkRelation.setSaveData(client);
                                checkRelation.setSaveData(bankAccount);
                                checkRelation.setSaveData(project);
                                checkRelation.setSaveData(relation);
                                checkRelation.getMessageContinue(jobAssignMessage);
                            }

                        } else if (chooseOption == 2) {
                            int clientObjectArrayNo = 0;
                            System.out.println("### Please Add Project information Details");
                            boolean checkClientID = true;
                            boolean dataSaveOrNotCheck = false;
                            while (checkClientID) {
                                boolean dataNotFoundCheck = false;
                                System.out.print("Existing Client ID: " + companyInfo.getClientPrefix());
                                int clientCheck = checkRelation.getIntegerValue();
                                for (int i = 0; i < totelClient - 1; i++) {
                                    if (clientCheck == client[i].id) {
                                        checkRelation.getSeparatorBefore();
                                        //System.out.println("--------------------------------------------------------------------------");
                                        System.out.println("ID No: " + client[i].id + " Name: " + client[i].name + " Email: " + client[i].email);
                                        //System.out.println("--------------------------------------------------------------------------");
                                        checkRelation.getSeparatorAfter();
                                        relClientID[totelRelation - 1] = client[i].id;
                                        clientObjectArrayNo = i;
                                        dataNotFoundCheck = true;
                                    }
                                }
                                if (dataNotFoundCheck == true) {
                                    System.out.println("The client information is OK?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    System.out.print("\nchoose here: ");
                                    int clientCheckOption = checkRelation.getIntegerValue();
                                    if (clientCheckOption == 1) {
                                        checkClientID = false;
                                    } else if (clientCheckOption == 2) {
                                        checkClientID = true;
                                    } else {
                                        checkRelation.getPressedWrongKey();
                                    }
                                } else {
                                    checkRelation.getMessage("Client Id Not Found... Please Try Again...");
                                    System.out.print("\nEnter The valid ");
                                }

                            }

                            System.out.println("Project ID: " + companyInfo.getProjectPrefix() + totelProject);
                            projectID[totelProject - 1] = totelProject;
                            relProjectID[totelRelation - 1] = totelProject;

                            System.out.print("Project Name: ");
                            projectName[totelProject - 1] = src.nextLine();
                            //projectName[totelProject - 1] = src.nextLine();
                            System.out.print("Project Description: ");
                            projectDescription[totelProject - 1] = src.nextLine();
                            System.out.print("Project Duration(Day): ");
                            relProjectDuration[totelProject - 1] = checkRelation.getIntegerValue();

                            System.out.print("Project Budget(USD): ");
                            relProjectBudget[totelRelation - 1] = checkRelation.getDoubleValue();

                            while (true) {
                                int clientBankAccountObjectArrayNo = 0;
                                for (int i = 0; i < bankAccount.length; i++) {
                                    if (client[clientObjectArrayNo].bankID == bankAccount[i].bankID) {
                                        clientBankAccountObjectArrayNo = i;
                                        break;
                                    }
                                }

                                if (relProjectBudget[totelRelation - 1] <= bankAccount[clientBankAccountObjectArrayNo].blance) {
                                    bankAccount[clientBankAccountObjectArrayNo].pendingBalance = bankAccount[clientBankAccountObjectArrayNo].pendingBalance + relProjectBudget[totelRelation - 1];
                                    bankAccount[clientBankAccountObjectArrayNo].blance = bankAccount[clientBankAccountObjectArrayNo].blance - relProjectBudget[totelRelation - 1];

                                    //System.out.println("Enough Money");
                                    System.out.print("Project Comment: ");
                                    projectComment[totelProject - 1] = src.nextLine();
                                    //projectComment[totelProject - 1] = src.nextLine();

                                    System.out.println("### Assign for Employee");
                                    boolean checkEmployeeID = true;
                                    while (checkEmployeeID) {
                                        boolean dataNotFoundCheck = false;
                                        System.out.print("Employee ID: " + companyInfo.getEmployeePrefix());
                                        int employeeCheck = checkRelation.getIntegerValue();
                                        for (int i = 0; i < totelEmpolyee - 1; i++) {
                                            if (employeeCheck == employee[i].id) {
                                                //System.out.println("--------------------------------------------------------------------------");
                                                checkRelation.getSeparatorBefore();
                                                System.out.println("ID No: " + employee[i].id + " Name: " + employee[i].name + " Email: " + employee[i].email);
                                                //System.out.println("--------------------------------------------------------------------------");
                                                checkRelation.getSeparatorAfter();
                                                relEmployeeID[totelRelation - 1] = employee[i].id;
                                                dataNotFoundCheck = true;
                                            }
                                        }
                                        if (dataNotFoundCheck == true) {
                                            System.out.println("The Employee information is OK?");
                                            System.out.println("1. Yes");
                                            System.out.println("2. No");
                                            System.out.print("Choose Here: ");
                                            int employeeCheckOption = checkRelation.getIntegerValue();
                                            if (employeeCheckOption == 1) {
                                                checkEmployeeID = false;
                                            } else if (employeeCheckOption == 2) {
                                                checkEmployeeID = true;
                                            } else {
                                                checkRelation.getPressedWrongKey();
                                            }
                                        } else {
                                            checkRelation.getMessage("Employee Id Not Found... Please Try Again...");
                                            System.out.print("\nEnter The valid ");
                                        }

                                    }

                                    relationID[totelRelation - 1] = totelRelation;
                                    relAssignDate[totelRelation - 1] = dateAndTime;
                                    relJobStates[totelRelation - 1] = "Pending";
                                    int[] projectID_temp = new int[totelProject + 1];
                                    String[] projectName_temp = new String[totelProject + 1];
                                    String[] projectDescription_temp = new String[totelProject + 1];
                                    String[] projectComment_temp = new String[totelProject + 1];

                                    Project[] project_temp = new Project[totelProject + 1];

                                    for (int i = 0; i < totelProject; i++) {
                                        //project_ClientID_temp[i] = project_ClientID[i];
                                        projectID_temp[i] = projectID[i];
                                        projectName_temp[i] = projectName[i];
                                        projectDescription_temp[i] = projectDescription[i];
                                        projectComment_temp[i] = projectComment[i];
                                        project_temp[i] = new Project(projectID_temp[i], projectName_temp[i], projectDescription_temp[i], projectComment_temp[i]);

                                    }

                                    totelProject++;

                                    projectID = new int[totelProject];
                                    projectName = new String[totelProject];
                                    projectDescription = new String[totelProject];
                                    projectDuration = new int[totelProject];
                                    projectComment = new String[totelProject];

                                    project = new Project[totelProject];

                                    for (int i = 0; i < totelProject; i++) {
                                        projectID[i] = projectID_temp[i];
                                        projectName[i] = projectName_temp[i];
                                        projectDescription[i] = projectDescription_temp[i];
                                        projectComment[i] = projectComment_temp[i];
                                        project[i] = new Project(projectID[i], projectName[i], projectDescription[i], projectComment[i]);

                                    }

                                    relationID[totelRelation - 1] = totelRelation;

                                    // Relation Temporary Backup
                                    int[] relationID_temp = new int[totelRelation + 1];
                                    int[] relClientID_temp = new int[totelRelation + 1];
                                    int[] relEmployeeID_temp = new int[totelRelation + 1];
                                    int[] relProjectID_temp = new int[totelRelation + 1];
                                    String[] relAssignDate_temp = new String[totelRelation + 1];
                                    String[] relDeliveryDate_temp = new String[totelRelation + 1];
                                    int[] relProjectDuration_temp = new int[totelRelation + 1];
                                    double[] relProjectBudget_temp = new double[totelRelation + 1];
                                    String[] relJobStates_temp = new String[totelRelation + 1];

                                    Relation[] relation_temp = new Relation[totelRelation];

                                    for (int i = 0; i < totelRelation; i++) {
                                        relationID_temp[i] = relationID[i];
                                        relClientID_temp[i] = relClientID[i];
                                        relEmployeeID_temp[i] = relEmployeeID[i];
                                        relProjectID_temp[i] = relProjectID[i];
                                        relAssignDate_temp[i] = relAssignDate[i];
                                        relDeliveryDate_temp[i] = relDeliveryDate[i];
                                        relProjectDuration_temp[i] = relProjectDuration[i];
                                        relProjectBudget_temp[i] = relProjectBudget[i];
                                        relJobStates_temp[i] = relJobStates[i];
                                        relation_temp[i] = new Relation(relationID_temp[i], relClientID_temp[i], relEmployeeID_temp[i], relProjectID_temp[i], relAssignDate_temp[i], relDeliveryDate_temp[i],
                                                relProjectDuration_temp[i], relProjectBudget_temp[i], relJobStates_temp[i]);
                                    }

                                    totelRelation++;
                                    relation = new Relation[totelRelation];
                                    relationID = new int[totelRelation];
                                    relClientID = new int[totelRelation];
                                    relEmployeeID = new int[totelRelation];
                                    relProjectID = new int[totelRelation];
                                    relAssignDate = new String[totelRelation];
                                    relDeliveryDate = new String[totelRelation];
                                    relProjectDuration = new int[totelRelation];
                                    relProjectBudget = new double[totelRelation];
                                    relJobStates = new String[totelRelation];

                                    for (int i = 0; i < totelRelation; i++) {
                                        relationID[i] = relationID_temp[i];
                                        relClientID[i] = relClientID_temp[i];
                                        relEmployeeID[i] = relEmployeeID_temp[i];
                                        relProjectID[i] = relProjectID_temp[i];
                                        relAssignDate[i] = relAssignDate_temp[i];
                                        relDeliveryDate[i] = relDeliveryDate_temp[i];
                                        relProjectDuration[i] = relProjectDuration_temp[i];
                                        relProjectBudget[i] = relProjectBudget_temp[i];
                                        relJobStates[i] = relJobStates_temp[i];

                                        relation[i] = new Relation(relationID[i], relClientID[i], relEmployeeID[i], relProjectID[i], relAssignDate[i], relDeliveryDate[i],
                                                relProjectDuration[i], relProjectBudget[i], relJobStates[i]);

                                    }

                                    dataSaveOrNotCheck = true;
                                    break;

                                } else {
                                    checkRelation.getMessage("Not Enough Money");
                                    System.out.println("Your Current Balance Is: " + bankAccount[clientBankAccountObjectArrayNo].blance + " USD");
                                    System.out.println("Need Money: " + (relProjectBudget[totelRelation - 1] - bankAccount[clientBankAccountObjectArrayNo].blance + " USD"));

                                    System.out.println("\n### Please Add Money.");
                                    System.out.println("1. Add Money.");
                                    System.out.println("2. Back");
                                    System.out.print("choose here: ");
                                    chooseOption = checkRelation.getIntegerValue();
                                    if (chooseOption == 1) {
                                        System.out.print("Amount here: ");
                                        double additionalAmount = checkRelation.getDoubleValue();

                                        bankAccount[clientBankAccountObjectArrayNo].blance = bankAccount[clientBankAccountObjectArrayNo].blance + additionalAmount;

                                    } else if (chooseOption == 2) {
                                        break;
                                    }
                                }

                            }
                            if (dataSaveOrNotCheck) {
                                //checkRelation.getMessageContinue("### New Job " + projectName[totelProject - 2] + " Assign Time: " + dateAndTime);
                                //checkRelation.setSaveAllDataWithMassage("### New Job " + projectName[totelProject - 2] + " Assign Time: " + dateAndTime, companyInfo, bankAccount, employee, client, project, relation);
                                checkRelation.setSaveData(client);
                                checkRelation.setSaveData(bankAccount);
                                checkRelation.setSaveData(project);
                                checkRelation.setSaveData(relation);
                                checkRelation.getMessageContinue("### New Job " + projectName[totelProject - 2] + " Assign Time: " + dateAndTime);
                            }
                        } else if (chooseOption == 3) {
                            System.out.println();
                        } else {
                            checkRelation.getPressedWrongKey();
                        }

                    } else if (action == 4) {
                        System.out.println("### View Client Details");
                        System.out.println("1. by ID");
                        System.out.println("2. by Name");
                        System.out.println("3. by Email");
                        System.out.println("4. by Mobile");
                        System.out.println("5. View All Client");
                        System.out.println("6. Back");

                        System.out.print("\nChoose here: ");
                        chooseOption = checkRelation.getIntegerValue();
                        if (chooseOption == 1) {
                            int clientID_Details;
                            System.out.print("\nPlease Enter your Client ID to view details: " + companyInfo.getClientPrefix());
                            clientID_Details = checkRelation.getIntegerValue();

                            for (int i = 0; i < client.length - 1; i++) {
                                if (client[i].id == clientID_Details) {
                                    client[i].getClientInfo();
                                    notification.setDataCheck(true);
                                    foundDataSize++;
                                }
                            }

                            notification.getDataNotFound("Client", "ID", foundDataSize);
                            foundDataSize = 0;

                        } else if (chooseOption == 2) {
                            String clientName_Details;
                            System.out.print("Please Enter your Client Name to view details: ");
                            //src.nextLine();
                            clientName_Details = src.nextLine();

                            for (int i = 0; i < client.length - 1; i++) {
                                if (client[i].name.equalsIgnoreCase(clientName_Details)) {
                                    client[i].getClientInfo();
                                    notification.setDataCheck(true);
                                    foundDataSize++;
                                }
                            }
                            notification.getDataNotFound("Client", "Name", foundDataSize);
                            foundDataSize = 0;

                        } else if (chooseOption == 3) {
                            String clientEmail_Details;
                            System.out.print("Please Enter your Client Email to view details: ");
                            //src.nextLine();
                            clientEmail_Details = src.nextLine();

                            for (int i = 0; i < client.length - 1; i++) {
                                if (client[i].email.equalsIgnoreCase(clientEmail_Details)) {
                                    client[i].getClientInfo();
                                    notification.setDataCheck(true);
                                    foundDataSize++;
                                }
                            }
                            notification.getDataNotFound("Client", "Email", foundDataSize);
                            foundDataSize = 0;

                        } else if (chooseOption == 4) {
                            String clientPhone_Details;
                            System.out.print("Please Enter your Client Phone Number to view details: ");
                            //src.nextLine();
                            clientPhone_Details = src.nextLine();

                            for (int i = 0; i < client.length - 1; i++) {
                                if (client[i].phone.equalsIgnoreCase(clientPhone_Details)) {
                                    client[i].getClientInfo();
                                    notification.setDataCheck(true);
                                    foundDataSize++;
                                }
                            }
                            notification.getDataNotFound("Client", "Phone Number", foundDataSize);
                            foundDataSize = 0;

                        } else if (chooseOption == 5) {

                            System.out.print("All client view details: ");

                            for (int i = 0; i < totelClient - 1; i++) {
                                client[i].getClientInfo();
                                notification.setDataCheck(true);
                                foundDataSize++;
                            }

                            notification.getDataNotFound("Client", "", foundDataSize);
                            foundDataSize = 0;

                        } else if (chooseOption == 6) {
                            System.out.println();
                        } else {
                            checkRelation.getPressedWrongKey();
                        }

                    } else if (action == 5) {
                        System.out.println("### Project States");
                        System.out.println("1. Pending Project");
                        System.out.println("2. Done Project");
                        System.out.println("3. Refund  Project");
                        System.out.println("4. All Project");
                        System.out.print("\nChoose Here: ");
                        chooseOption = checkRelation.getIntegerValue();
                        // RelationalData Object
                        checkRelation = new RelationalData(companyInfo, client, project, employee, relation);
                        if (chooseOption == 1) {
                            System.out.println("### Pending Project States Information...");
                            for (int i = 0; i < relation.length - 1; i++) {
                                if (relation[i].jobStates.equalsIgnoreCase("Pending")) {
                                    checkRelation.getProjectStates(relation[i].clientID, relation[i].projectID, relation[i].employeeID, relation[i].relationID);
                                    notification.setDataCheck(true);
                                    foundDataSize++;
                                }
                            }
                            notification.getDataNotFound("Project", "Pending", foundDataSize);
                            foundDataSize = 0;
                        } else if (chooseOption == 2) {
                            System.out.println("### Done Project States Information..");
                            for (int i = 0; i < relation.length - 1; i++) {
                                if (relation[i].jobStates.equalsIgnoreCase("Done")) {
                                    checkRelation.getProjectStates(relation[i].clientID, relation[i].projectID, relation[i].employeeID, relation[i].relationID);
                                    notification.setDataCheck(true);
                                    foundDataSize++;
                                }
                            }
                            notification.getDataNotFound("Project", "Done", foundDataSize);
                            foundDataSize = 0;
                        } else if (chooseOption == 3) {
                            System.out.println("### Refund Project States Information..");
                            for (int i = 0; i < relation.length - 1; i++) {
                                if (relation[i].jobStates.equalsIgnoreCase("Refund")) {
                                    checkRelation.getProjectStates(relation[i].clientID, relation[i].projectID, relation[i].employeeID, relation[i].relationID);
                                    notification.setDataCheck(true);
                                    foundDataSize++;
                                }
                            }
                            notification.getDataNotFound("Project", "Refund", foundDataSize);
                            foundDataSize = 0;
                        } else if (chooseOption == 4) {
                            System.out.println("\n### All Project Information..");
                            for (int i = 0; i < relation.length - 1; i++) {
                                checkRelation.getProjectStates(relation[i].clientID, relation[i].projectID, relation[i].employeeID, relation[i].relationID);
                                notification.setDataCheck(true);
                                foundDataSize++;
                            }
                            notification.getDataNotFound("Project", "", foundDataSize);
                            foundDataSize = 0;
                        }

                    } else if (action == 6) {
                        System.out.println("1. by Project ID");
                        System.out.println("2. Employees are doing projects");
                        System.out.print("\nChoose here: ");
                        chooseOption = checkRelation.getIntegerValue();
                        boolean check = true;
                        int counter = 0;
                        while (check) {

                            if (chooseOption == 1) {
                                System.out.print("Project ID: " + companyInfo.getProjectPrefix());
                                int search_projectID = checkRelation.getIntegerValue();
                                System.out.println();
                                int checkClientObjectArrayNo = 0, checkRelationObjectArrayNo = 0;
                                boolean datacheck = false;
                                boolean dataSaveOrNotCheck = false;
                                String message = null;
                                for (int i = 0; i < relation.length - 1; i++) {

                                    if ((search_projectID == relation[i].projectID) && relation[i].jobStates.equalsIgnoreCase("Pending")) {
                                        checkRelationObjectArrayNo = i;
                                        datacheck = true;

                                        for (int k = 0; k < client.length - 1; k++) {
                                            if (relation[i].clientID == client[k].id) {
                                                checkClientObjectArrayNo = k;
                                                System.out.println("Client ID: " + companyInfo.getClientPrefix() + client[k].id);
                                                System.out.println("Client Name: " + client[k].name);
                                            }
                                        }

                                        for (int j = 0; j < project.length - 1; j++) {
                                            if (relation[i].projectID == project[j].projectID) {
                                                System.out.println("Project Name: " + project[j].name);
                                                System.out.println("Project Descriotion: " + project[j].descriotion);
                                            }
                                        }

                                        System.out.println("Assign Date: " + relation[i].assignDate);

                                    }
                                }

                                if (datacheck == true) {
                                    System.out.println("\n### Do you want to Submit or Refund?");
                                    System.out.println("1. Submit");
                                    System.out.println("2. Refund");
                                    System.out.println("3. Back");
                                    System.out.print("\nChoose here: ");
                                    chooseOption = checkRelation.getIntegerValue();

                                    String optionData = null;
                                    if (chooseOption == 1) {
                                        optionData = "Submit";
                                    } else if (chooseOption == 2) {
                                        optionData = "Refund";
                                    }

                                    System.out.println("\nPlease Enter your Root Login Information to " + optionData + ".");
                                    //src.nextLine();
                                    boolean ckeckLoginInfo = true;
                                    while (ckeckLoginInfo) {
                                        System.out.print("Username: ");
                                        String checkUsername = src.nextLine();
                                        System.out.print("Password: ");
                                        String checkPassword = src.nextLine();
                                        if (checkUsername.equals(companyInfo.getUsername()) && checkPassword.equals(companyInfo.getPassword())) {
                                            ckeckLoginInfo = false;
                                            if (chooseOption == 1) {
                                                relation[checkRelationObjectArrayNo].jobStates = "Done";
                                                for (int l = 0; l < bankAccount.length - 1; l++) {
                                                    if (client[checkClientObjectArrayNo].bankID == bankAccount[l].bankID) {
                                                        bankAccount[0].blance = bankAccount[0].blance + relation[checkRelationObjectArrayNo].clientBudget;
                                                        bankAccount[l].pendingBalance = bankAccount[l].pendingBalance - relation[checkRelationObjectArrayNo].clientBudget;
                                                        relation[checkRelationObjectArrayNo].deliveryDate = dateAndTime;
                                                    }
                                                }
                                                message = "Project Submitted Successfully";
                                                dataSaveOrNotCheck = true;

                                            } else if (chooseOption == 2) {
                                                relation[checkRelationObjectArrayNo].jobStates = "Refund";
                                                for (int l = 0; l < bankAccount.length - 1; l++) {
                                                    if (client[checkClientObjectArrayNo].bankID == bankAccount[l].bankID) {
                                                        bankAccount[l].blance = bankAccount[l].blance + relation[checkRelationObjectArrayNo].clientBudget;
                                                        bankAccount[l].pendingBalance = bankAccount[l].pendingBalance - relation[checkRelationObjectArrayNo].clientBudget;
                                                        relation[checkRelationObjectArrayNo].deliveryDate = dateAndTime;
                                                    }
                                                }
                                                message = "Blance Refund Successfully";
                                                dataSaveOrNotCheck = true;
                                            }

                                        } else {
                                            checkRelation.getMessage("Please Enter your Correct root login information to " + optionData + ".");
                                        }
                                    }
                                } else {
                                    checkRelation.getMessageContinue("Pending Project ID Not Found");
                                }

                                System.out.println();
                                check = false;

                                if (dataSaveOrNotCheck) {
                                    //checkRelation.setSaveAllDataWithMassage(message, companyInfo, bankAccount, employee, client, project, relation);
                                    checkRelation.setSaveData(bankAccount);
                                    checkRelation.setSaveData(relation);
                                    checkRelation.getMessageContinue(message);
                                }
                            } else if (chooseOption == 2) {
                                System.out.print("Employee ID: " + companyInfo.getEmployeePrefix());
                                int search_employeeID = checkRelation.getIntegerValue();

                                boolean checkEmployee = false;
                                for (int i = 0; i < employee.length - 1; i++) {
                                    if (employee[i].id == search_employeeID) {
                                        checkRelation.getSeparatorBefore();
                                        //System.out.println("\n--------------------------------------------------------------------------");
                                        System.out.println("Employee Name: " + employee[i].name);
                                        //System.out.println("--------------------------------------------------------------------------");
                                        checkRelation.getSeparatorAfter();
                                        checkEmployee = true;
                                        break;
                                    }
                                }
                                boolean projectCheck = false;

                                if (checkEmployee == true) {

                                    for (int i = 0; i < relation.length - 1; i++) {
                                        if (relation[i].employeeID == search_employeeID && relation[i].jobStates.equalsIgnoreCase("Pending")) {
                                            projectCheck = true;
                                            for (int j = 0; j < project.length - 1; j++) {
                                                if (relation[i].projectID == project[j].projectID) {
                                                    System.out.println("Project ID: " + companyInfo.getProjectPrefix() + project[j].projectID);
                                                    System.out.println("Projct Name: " + project[j].name);
                                                    System.out.println("Projct Descriotion: " + project[j].descriotion);
                                                    break;
                                                }
                                            }
                                            System.out.println("Assign Date: " + relation[i].assignDate);
                                            System.out.println("Project Duration: " + relation[i].projectDuration + " day");

                                            for (int j = 0; j < client.length - 1; j++) {
                                                if (relation[i].clientID == client[j].id) {
                                                    System.out.println("\nClient ID: " + companyInfo.getClientPrefix() + client[j].id);
                                                    System.out.print("Client Name: " + client[j].name + "\n");
                                                    break;
                                                }
                                            }
                                            //System.out.println("--------------------------------------------------------------------------");
                                            checkRelation.getSeparatorAfter();
                                        }
                                    }

                                    if (projectCheck == true) {
                                        System.out.println("Do you wont to submite or Refund?");
                                        System.out.println("1. Yes");
                                        System.out.println("2. No");
                                        System.out.print("\nChoose here: ");
                                        chooseOption = checkRelation.getIntegerValue();
                                        if (chooseOption == 1) {

                                        } else {
                                            check = false;
                                        }
                                    } else {
                                        checkRelation.getMessage("No Running Project Yet");
                                        check = false;
                                    }
                                } else {
                                    counter++;
                                    checkRelation.getMessage("Employee ID Not Found!!! Please Try Again");
                                    if (counter == 2) {
                                        checkRelation.getContinue();
                                        check = false;
                                    }
                                }

                            } else {
                                counter++;
                                checkRelation.getMessage("Please Press the right key, Please try Again");
                                check = false;
                            }

                        }

                    } else if (action == 7) {
                        System.out.println("\n### Employee Working States.");
                        System.out.println("1. Employee working for project.");
                        System.out.println("2. Employee No Projects Were Offered.");
                        System.out.println("3. Employee Work Done Project.");
                        System.out.println("4. All Assign and Done Project.");
                        System.out.println("5. Back.");
                        System.out.print("\nChoose Here: ");
                        chooseOption = checkRelation.getIntegerValue();
                        checkRelation = new RelationalData(companyInfo, employee, relation);
                        if (chooseOption == 1) {

                            System.out.println("### Employee working for project States.\n");
                            checkRelation.getEmployeeProjectStates("Working", "Pending");

                        } else if (chooseOption == 2) {

                            System.out.println("### No Projects Were Offered.\n");
                            checkRelation.getFreeEmployee();
                        } else if (chooseOption == 3) {
                            System.out.println("### Employee Work Done Project States.\n");
                            checkRelation.getEmployeeProjectStates("Done", "Done");

                        } else if (chooseOption == 4) {
                            System.out.println("### All Assign and Done Project.\n");
                            System.out.println("### Assign Project.");
                            checkRelation.getEmployeeProjectStates("Working", "Pending");

                            System.out.println("### Done Project.");
                            checkRelation.getEmployeeProjectStates("Done", "Done");

                        } else if (chooseOption == 5) {
                            System.out.println();
                        } else {
                            checkRelation.getPressedWrongKey();
                        }

                    } else if (action == 8) {
                        checkRelation.getSeparatorBefore();
                        //System.out.println("--------------------------------------------------------------------------");
                        System.out.println("Company ID: " + companyInfo.getCompanyPrefix() + bankAccount[0].bankID);
                        System.out.println("Company Blance: " + bankAccount[0].blance + " USD");
                        double companyPandingBlance = 0;
                        for (int i = 0; i < bankAccount.length - 1; i++) {
                            companyPandingBlance = companyPandingBlance + bankAccount[i].pendingBalance;
                        }
                        System.out.println("Company Panding Blance: " + companyPandingBlance + " USD");
                        //System.out.println("--------------------------------------------------------------------------");
                        checkRelation.getSeparatorAfter();
                        checkRelation.getContinue();

                    } else if (action == 9) {
                        System.out.println("\n### Employee Salary calculation...");
                        System.out.println("\nEmployee ID: \tSalary: \t\tEmployee Name: \tEmail:");
                        checkRelation.getSeparatorBefore();
                        //System.out.println("--------------------------------------------------------------------------");
                        double checkSalary = 0;
                        boolean checkFoundEmployee = true;
                        for (int i = 0; i < employee.length - 1; i++) {
                            if (employee[i].states == true) {
                                System.out.println(companyInfo.getEmployeePrefix() + employee[i].id + " \t" + employee[i].salary + " \t" + employee[i].name + "\t" + employee[i].email);
                                checkSalary = checkSalary + employee[i].salary;
                                checkFoundEmployee = false;
                            }
                        }
                        if (checkFoundEmployee) {
                            //System.out.println("--------------------------Employee ID Not Found!!!------------------------");
                            checkRelation.getSpecificMessage("Employee ID Not Found!!!");
                        }

                        //System.out.println("--------------------------------------------------------------------------");
                        checkRelation.getSeparatorAfter();
                        System.out.println("Totel Employee Salary is: " + checkSalary + " USD\n");
                        boolean checkSalaryPaid = true;
                        boolean dataSaveOrNotCheck = false;
                        String message = null;
                        while (checkSalaryPaid) {
                            System.out.println("Do you want to paid the salary?");
                            System.out.println("1. Yes");
                            System.out.println("2. No");
                            System.out.print("choose here: ");
                            chooseOption = checkRelation.getIntegerValue();

                            if (chooseOption == 1) {
                                if (checkRelation.getLoginInfoCheck()) {

                                    if (checkSalary <= bankAccount[0].blance) {
                                        for (int i = 0; i < employee.length - 1; i++) {
                                            if (employee[i].states == true) {

                                                for (int j = 0; j < bankAccount.length - 1; j++) {
                                                    if (employee[i].bankID == bankAccount[j].bankID) {
                                                        bankAccount[0].blance = bankAccount[0].blance - employee[i].salary;
                                                        bankAccount[j].blance = bankAccount[j].blance + employee[i].salary;
                                                    }
                                                }
                                            }
                                        }
                                        checkRelation.getMessage("All Employee Salary Paid Successfully");
                                        System.out.println("Company Current Balance Is: " + bankAccount[0].blance + " USD\n");
                                        checkSalaryPaid = false;
                                        dataSaveOrNotCheck = true;

                                    } else {
                                        checkRelation.getMessage("Not Enough Money");
                                        System.out.println("Company Current Balance Is: " + bankAccount[0].blance + " USD");
                                        System.out.println("Need Money: " + (checkSalary - bankAccount[0].blance) + " USD");

                                        System.out.println("\n### Please Add Money.");
                                        System.out.println("1. Add Money.");
                                        System.out.println("2. Back");
                                        System.out.print("choose here: ");
                                        chooseOption = checkRelation.getIntegerValue();
                                        if (chooseOption == 1) {
                                            System.out.print("\n### Amount Here(USD): ");
                                            double additionalAmount = checkRelation.getDoubleValue();
                                            bankAccount[0].blance = bankAccount[0].blance + additionalAmount;
                                            checkRelation.getMessage("Balance Added Successfully");
                                        } else if (chooseOption == 2) {
                                            break;
                                        }

                                    }
                                }
                            } else if (chooseOption == 2) {
                                checkSalaryPaid = false;
                                System.out.println();
                            } else {
                                checkRelation.getPressedWrongKey();
                            }

                        }
                        if (dataSaveOrNotCheck) {
                            checkRelation.setSaveData(bankAccount);
                        }
                    } else if (action == 10) {
                        System.out.println("\n### Money Deposit");
                        System.out.println("1. Company Account");
                        System.out.println("2. Client Account");
                        System.out.println("3. Back");
                        System.out.print("\nChoose Here: ");
                        chooseOption = checkRelation.getIntegerValue();

                        boolean dataSaveOrNotCheck = false;

                        checkRelation = new RelationalData(companyInfo);
                        if (chooseOption == 1) {
                            if (checkRelation.getLoginInfoCheck()) {
                                System.out.println("\n### Company Current Balance Is: " + bankAccount[0].blance + " USD");
                                System.out.println("\n### Please Add Money.");
                                System.out.println("1. Add Money.");
                                System.out.println("2. Back");
                                System.out.print("\nChoose here: ");
                                int chooseOptionHere = checkRelation.getIntegerValue();
                                if (chooseOptionHere == 1) {
                                    System.out.print("\n### Amount here(USD): ");
                                    double additionalAmount = checkRelation.getDoubleValue();
                                    bankAccount[0].blance = bankAccount[0].blance + additionalAmount;
                                    checkRelation.getMessage("Balance Added Successfully");
                                    dataSaveOrNotCheck = true;
                                    System.out.println("Company Current Balance Is: " + bankAccount[0].blance + " USD\n");
                                    checkRelation.getContinue();

                                }
                            }

                        } else if (chooseOption == 2) {
                            if (checkRelation.getLoginInfoCheck()) {
                                System.out.print("\nPlease enter your Client ID: " + companyInfo.getClientPrefix());
                                int client_id = checkRelation.getIntegerValue();
                                int i = 0;
                                boolean checkdata = false;
                                for (i = 0; i < client.length - 1; i++) {
                                    if (client_id == client[i].id) {
                                        checkRelation.getMessage("Client ID Found");
                                        System.out.println("Bank ID: " + companyInfo.getBankPrefix() + client[i].bankID);
                                        System.out.println("Client Name: " + client[i].name + "\n");

                                        System.out.println("### Carrent Blance: " + bankAccount[client[i].bankID - 1].blance + "USD");

                                        System.out.println("\n### Do you want to Deposit Money?");
                                        System.out.println("1. Yes.");
                                        System.out.println("2. NO");
                                        System.out.print("\nchoose here: ");
                                        int chooseOptionHere = checkRelation.getIntegerValue();
                                        if (chooseOptionHere == 1) {
                                            System.out.print("\n### Amount here(USD): ");
                                            double additionalAmount = checkRelation.getDoubleValue();
                                            bankAccount[client[i].bankID - 1].blance = bankAccount[client[i].bankID - 1].blance + additionalAmount;
                                            checkRelation.getMessage("Balance Added Successfully");
                                            dataSaveOrNotCheck = true;
                                            System.out.println("Current Balance Is: " + bankAccount[client[i].bankID - 1].blance + " USD\n");
                                            checkdata = true;
                                            checkRelation.getContinue();
                                            break;
                                        } else {
                                            System.out.println();
                                        }

                                    }
                                }

                                if (i == client.length - 1 && checkdata == false) {
                                    checkRelation.getMessage("Client ID not Found");
                                }
                            }
                        } else if (chooseOption == 3) {
                            System.out.println();
                        } else {
                            checkRelation.getPressedWrongKey();
                        }

                        if (dataSaveOrNotCheck) {
                            checkRelation.setSaveAllData(companyInfo, bankAccount, employee, client, project, relation);
                        }
                    } else if (action == 11) {
                        System.out.println("### Balance Withdraw");
                        System.out.println("1. Employee Account");
                        System.out.println("2. Office Account");
                        System.out.println("3. Client Account");
                        System.out.println("4. Back");
                        System.out.print("\nchoose here: ");
                        int chooseOptionHere = checkRelation.getIntegerValue();

                        if (chooseOptionHere == 1) {
                            System.out.println("### Enter Bank ID or Employee ID to Withdraw -");
                            System.out.println("1. by Bank ID");
                            System.out.println("2. by Employee ID");
                            System.out.println("3. Back");
                            System.out.print("\nchoose here: ");
                            chooseOptionHere = checkRelation.getIntegerValue();
                            if (chooseOptionHere == 1) {
                                System.out.print("Please Enter your Bank ID: " + companyInfo.getBankPrefix());
                                int bankIdCheck = checkRelation.getIntegerValue();
                                boolean employeeBankIdCheck = false;
                                int i;
                                for (i = 0; i < employee.length - 1; i++) {
                                    if (bankIdCheck == employee[i].bankID) {
                                        employeeBankIdCheck = true;
                                        break;
                                    }
                                }
                                if (employeeBankIdCheck == true) {
                                    checkRelation = new RelationalData(companyInfo, bankAccount);
                                    System.out.println("\nEmployee ID:\tEmployee Name = Balance");
                                    checkRelation.getSeparatorBefore();
                                    //System.out.println("--------------------------------------------------------------------------");
                                    System.out.print(companyInfo.getEmployeePrefix() + employee[i].id + "\t\t" + employee[i].name + " \t = ");
                                    int findBankAccountObjectArray = checkRelation.getBalanceShow(bankIdCheck);
                                    //System.out.println("--------------------------------------------------------------------------\n");
                                    checkRelation.getSeparatorAfter();
                                    System.out.print("### Enter Your Withdraw Amount Here(USD): ");
                                    double withdrawAmount = checkRelation.getDoubleValue();
                                    try {
                                        checkRelation.getWithdraw(bankAccount, findBankAccountObjectArray, withdrawAmount);
                                    } catch (NotSufficientBalanceException e) {
                                        checkRelation.getMessageContinue(e.getMessage());
                                    }
                                } else {
                                    checkRelation.getMessageContinue("Employee Bank ID not Found");
                                }

                            } else if (chooseOptionHere == 2) {
                                System.out.print("Please Enter your Employee ID: " + companyInfo.getEmployeePrefix());
                                int employeeId = checkRelation.getIntegerValue();
                                boolean employeeIdCheck = false;
                                int i;
                                for (i = 0; i < employee.length - 1; i++) {
                                    if (employeeId == employee[i].id) {
                                        employeeIdCheck = true;
                                        break;
                                    }
                                }

                                if (employeeIdCheck == true) {
                                    checkRelation = new RelationalData(companyInfo, bankAccount);
                                    System.out.println("\nEmployee ID:\tEmployee Name = Balance");
                                    checkRelation.getSeparatorBefore();
                                    //System.out.println("--------------------------------------------------------------------------");
                                    System.out.print(companyInfo.getEmployeePrefix() + employee[i].id + "\t\t" + employee[i].name + " \t = ");
                                    int findBankAccountObjectArray = checkRelation.getBalanceShow(employee[i].bankID);
                                    //System.out.println("--------------------------------------------------------------------------\n");
                                    checkRelation.getSeparatorAfter();
                                    System.out.print("### Enter Your Withdraw Amount Here(USD): ");
                                    double withdrawAmount = checkRelation.getDoubleValue();
                                    try {
                                        checkRelation.getWithdraw(bankAccount, findBankAccountObjectArray, withdrawAmount);
                                    } catch (NotSufficientBalanceException e) {
                                        checkRelation.getMessageContinue(e.getMessage());
                                    }
                                } else {
                                    checkRelation.getMessageContinue("Employee Bank ID not Found");

                                }

                            } else if (chooseOptionHere == 3) {
                                System.out.println();
                            } else {
                                checkRelation.getPressedWrongKey();
                            }

                        } else if (chooseOptionHere == 2) {

                            System.out.println("### Official Account ");
                            System.out.println("\nCompany Name = Balance");
                            checkRelation.getSeparatorBefore();
                            //System.out.println("--------------------------------------------------------------------------");
                            System.out.println(companyInfo.getName() + " \t= " + bankAccount[0].blance + " USD");
                            //System.out.println("--------------------------------------------------------------------------\n");
                            checkRelation.getSeparatorAfter();
                            System.out.print("### Enter Your Withdraw Amount Here(USD): ");
                            double withdrawAmount = checkRelation.getDoubleValue();
                            checkRelation = new RelationalData(companyInfo, bankAccount);
                            try {
                                checkRelation.getWithdraw(bankAccount, 0, withdrawAmount);
                            } catch (NotSufficientBalanceException e) {
                                checkRelation.getMessageContinue(e.getMessage());
                            }
                        } else if (chooseOptionHere == 3) {

                            System.out.println("### Enter Bank ID or Client ID to Withdraw -");
                            System.out.println("1. by Bank ID");
                            System.out.println("2. by Client ID");
                            System.out.println("3. back");
                            System.out.print("\nchoose here: ");
                            chooseOptionHere = checkRelation.getIntegerValue();

                            if (chooseOptionHere == 1) {
                                System.out.print("Please Enter your Bank ID: " + companyInfo.getBankPrefix());
                                int bankIdCheck = checkRelation.getIntegerValue();
                                boolean clientBankIdCheck = false;
                                int i;
                                for (i = 0; i < client.length - 1; i++) {
                                    if (bankIdCheck == client[i].bankID) {
                                        clientBankIdCheck = true;
                                        break;
                                    }
                                }
                                if (clientBankIdCheck == true) {
                                    checkRelation = new RelationalData(companyInfo, bankAccount);
                                    System.out.println("\nClient ID:\tClient Name \t = Balance");
                                    checkRelation.getSeparatorBefore();
                                    //System.out.println("--------------------------------------------------------------------------");
                                    System.out.print(companyInfo.getClientPrefix() + client[i].id + "\t\t" + client[i].name + "\t = ");
                                    int findBankAccountObjectArray = checkRelation.getBalanceShow(bankIdCheck);
                                    //System.out.println("--------------------------------------------------------------------------\n");
                                    checkRelation.getSeparatorAfter();
                                    System.out.print("### Enter Your Withdraw Amount Here(USD): ");
                                    double withdrawAmount = checkRelation.getDoubleValue();
                                    try {
                                        checkRelation.getWithdraw(bankAccount, findBankAccountObjectArray, withdrawAmount);
                                    } catch (NotSufficientBalanceException e) {
                                        checkRelation.getMessageContinue(e.getMessage());
                                    }
                                } else {
                                    checkRelation.getMessageContinue("Client Bank ID not Found");
                                }

                            } else if (chooseOptionHere == 2) {
                                System.out.print("Please Enter your Client ID: " + companyInfo.getClientPrefix());
                                int clientId = checkRelation.getIntegerValue();
                                boolean clientIdCheck = false;
                                int i;
                                for (i = 0; i < client.length - 1; i++) {
                                    if (clientId == client[i].id) {
                                        clientIdCheck = true;
                                        break;
                                    }
                                }

                                if (clientIdCheck == true) {
                                    checkRelation = new RelationalData(companyInfo, bankAccount);
                                    System.out.println("\nClient ID:\tClient Name = Balance");
                                    checkRelation.getSeparatorBefore();
                                    //System.out.println("--------------------------------------------------------------------------");
                                    System.out.print(companyInfo.getClientPrefix() + client[i].id + "\t\t" + client[i].name + " \t = ");
                                    int findBankAccountObjectArray = checkRelation.getBalanceShow(client[i].bankID);
                                    //System.out.println("--------------------------------------------------------------------------\n");
                                    checkRelation.getSeparatorAfter();
                                    System.out.print("### Enter Your Withdraw Amount Here(USD): ");
                                    double withdrawAmount = checkRelation.getDoubleValue();
                                    try {
                                        checkRelation.getWithdraw(bankAccount, findBankAccountObjectArray, withdrawAmount);
                                    } catch (NotSufficientBalanceException e) {
                                        checkRelation.getMessageContinue(e.getMessage());
                                    }
                                } else {
                                    checkRelation.getMessageContinue("Client Bank ID not Found");
                                }

                            } else if (chooseOptionHere == 3) {
                                System.out.println();
                            } else {
                                checkRelation.getPressedWrongKey();
                            }
                        } else if (chooseOptionHere == 4) {
                            System.out.println();
                        } else {
                            checkRelation.getPressedWrongKey();
                        }

                    } else if (action == 12) {
                        System.out.println("### Internal Transfer");
                        // Importent Property
                        String checkSenderUserId = null;
                        int findSenderBankAccountObjectArray = 0;
                        int senderUserArrayNo = 0;

                        String checkReceiverUserId = null;
                        int findReceiverBankAccountObjectArray = 0;
                        int receiverUserArrayNo = 0;

                        char[] checkUserIdPrefix;
                        int checkPrefixStringSize = 0;

                        boolean invalidUserID = true;

                        //Sender ID process
                        System.out.print("Enter your Senter's User ID: ");
                        //src.nextLine();
                        checkSenderUserId = src.nextLine();

                        checkRelation = new RelationalData();
                        checkPrefixStringSize = checkRelation.getPrefixSeparator(checkSenderUserId);

                        if (checkSenderUserId.substring(0, checkPrefixStringSize).equalsIgnoreCase(companyInfo.getCompanyPrefix())) {
                            //--> Company Sender ID Check
                            int userID = checkRelation.getUserIdValidation(checkSenderUserId, checkPrefixStringSize, checkSenderUserId.length());

                            if (userID == 1) {
                                checkRelation = new RelationalData(bankAccount);

                                System.out.println("\n### Sender Information\nCompany ID:\tCompany Name = Balance");
                                checkRelation.getSeparatorBefore();
                                //System.out.println("--------------------------------------------------------------------------");
                                System.out.println(bankAccount[senderUserArrayNo].userID + "\t\t" + companyInfo.getName() + " \t = " + bankAccount[senderUserArrayNo].blance);
                                //System.out.println("--------------------------------------------------------------------------\n");
                                checkRelation.getSeparatorAfter();

                                System.out.print("Enter your Recever's User ID: ");
                                checkReceiverUserId = src.nextLine();

                            } else {
                                if (checkRelation.getValidationMessageCheck()) {
                                    checkRelation.getMessageContinue("Sender Company Bank ID not Found");
                                }
                            }

                        } else if (checkSenderUserId.substring(0, checkPrefixStringSize).equalsIgnoreCase(companyInfo.getEmployeePrefix())) {
                            //--> Employee Sender ID Check
                            int userID = checkRelation.getUserIdValidation(checkSenderUserId, checkPrefixStringSize, checkSenderUserId.length());

                            boolean employeeIdCheck = false;

                            for (int i = 0; i < employee.length - 1; i++) {
                                if (userID == employee[i].id) {
                                    senderUserArrayNo = i;
                                    employeeIdCheck = true;

                                    break;
                                }
                            }

                            if (employeeIdCheck == true) {
                                checkRelation = new RelationalData(bankAccount);

                                System.out.println("\n### Sender Information\nEmployee ID:\tEmployee Name = Balance");
                                //System.out.println("--------------------------------------------------------------------------");
                                checkRelation.getSeparatorBefore();
                                System.out.print(companyInfo.getEmployeePrefix() + employee[senderUserArrayNo].id + "\t\t" + employee[senderUserArrayNo].name + " \t = ");
                                findSenderBankAccountObjectArray = checkRelation.getBalanceShow(employee[senderUserArrayNo].bankID);
                                //System.out.println("--------------------------------------------------------------------------\n");
                                checkRelation.getSeparatorAfter();

                                System.out.print("Enter your Recever's User ID: ");
                                checkReceiverUserId = src.nextLine();

                            } else {
                                if (checkRelation.getValidationMessageCheck()) {
                                    checkRelation.getMessageContinue("Sender Employee Bank ID not Found");
                                }
                            }
                        } else if (checkSenderUserId.substring(0, checkPrefixStringSize).equalsIgnoreCase(companyInfo.getClientPrefix())) {
                            //--> Client Sender ID Check
                            int userID = checkRelation.getUserIdValidation(checkSenderUserId, checkPrefixStringSize, checkSenderUserId.length());

                            boolean clientIdCheck = false;

                            for (int i = 0; i < client.length - 1; i++) {
                                if (userID == client[i].id) {
                                    senderUserArrayNo = i;
                                    clientIdCheck = true;
                                    break;
                                }
                            }

                            if (clientIdCheck == true) {
                                checkRelation = new RelationalData(bankAccount);

                                System.out.println("\n### Sender Information\nClient ID:\tClient Name = Balance");
                                checkRelation.getSeparatorBefore();
                                //System.out.println("--------------------------------------------------------------------------");
                                System.out.print(companyInfo.getClientPrefix() + client[senderUserArrayNo].id + "\t\t" + client[senderUserArrayNo].name + " \t = ");
                                findSenderBankAccountObjectArray = checkRelation.getBalanceShow(client[senderUserArrayNo].bankID);
                                //System.out.println("--------------------------------------------------------------------------\n");
                                checkRelation.getSeparatorAfter();

                                System.out.print("Enter your Recever's User ID: ");
                                checkReceiverUserId = src.nextLine();

                            } else {
                                if (checkRelation.getValidationMessageCheck()) {
                                    checkRelation.getMessageContinue("Sender Client Bank ID not Found");
                                }
                            }

                        } else {
                            invalidUserID = false;
                            checkRelation.getMessageContinue("Invalid User ID!!! Try Again.");
                        }

                        //Recever ID
                        try {
                            if (invalidUserID == true) {
                                while (true) {
                                    if (checkSenderUserId.equalsIgnoreCase(checkReceiverUserId)) {
                                        checkRelation.getMessage("Sender's & Receiver's ID can never be same!!! Try Again.");
                                        System.out.print("Enter your Recever's User ID: ");
                                        checkReceiverUserId = src.nextLine();
                                    } else {
                                        break;
                                    }
                                }

                                //Prefix String Size
                                checkPrefixStringSize = checkRelation.getPrefixSeparator(checkReceiverUserId);

                                if (checkReceiverUserId.substring(0, checkPrefixStringSize).equalsIgnoreCase(companyInfo.getCompanyPrefix())) {
                                    //--> Company ID Receive Check
                                    int userID = checkRelation.getUserIdValidation(checkReceiverUserId, checkPrefixStringSize, checkReceiverUserId.length());

                                    if (userID == 1) {
                                        checkRelation = new RelationalData(companyInfo, bankAccount);

                                        System.out.println("\n### Receiver Information\nCompany ID:\tCompany Name = Balance");
                                        //System.out.println("--------------------------------------------------------------------------");
                                        checkRelation.getSeparatorBefore();
                                        System.out.print(bankAccount[receiverUserArrayNo].userID + "\t\t" + companyInfo.getName() + " \t = " + bankAccount[receiverUserArrayNo].blance + "\n");
                                        //System.out.println("--------------------------------------------------------------------------\n");
                                        checkRelation.getSeparatorAfter();
                                        try {
                                            if (checkRelation.getTransferBlance(bankAccount, findSenderBankAccountObjectArray, findReceiverBankAccountObjectArray)) {
                                                System.out.println("\n### Before Internal Transfer -");
                                                System.out.println("ID: Account Holder's Name = Balance");
                                                checkRelation.getSeparatorBefore();
                                                //System.out.println("--------------------------------------------------------------------------");
                                                System.out.print(bankAccount[receiverUserArrayNo].userID + "\t\t" + companyInfo.getName() + " \t = " + bankAccount[receiverUserArrayNo].blance + "\n");
                                                //System.out.println("--------------------------------------------------------------------------\n");
                                                checkRelation.getSeparatorAfter();
                                                checkRelation.getContinue();
                                            }
                                        } catch (NotSufficientBalanceException e) {
                                            checkRelation.getMessageContinue(e.getMessage());
                                        }

                                    } else {
                                        if (checkRelation.getValidationMessageCheck()) {
                                            checkRelation.getMessageContinue("Receiver Company Bank ID not Found");
                                        }
                                    }

                                } else if (checkReceiverUserId.substring(0, checkPrefixStringSize).equalsIgnoreCase(companyInfo.getEmployeePrefix())) {
                                    //--> Employee Recever ID Check
                                    int userID = checkRelation.getUserIdValidation(checkReceiverUserId, checkPrefixStringSize, checkReceiverUserId.length());

                                    boolean employeeIdCheck = false;
                                    for (int i = 0; i < employee.length - 1; i++) {
                                        if (userID == employee[i].id) {
                                            receiverUserArrayNo = i;
                                            employeeIdCheck = true;
                                            break;
                                        }
                                    }

                                    if (employeeIdCheck == true) {
                                        checkRelation = new RelationalData(companyInfo, bankAccount);
                                        System.out.println("\n### Receiver Information\nEmployee ID:\tEmployee Name = Balance");
                                        checkRelation.getSeparatorBefore();
                                        //System.out.println("--------------------------------------------------------------------------");
                                        System.out.print(companyInfo.getEmployeePrefix() + employee[receiverUserArrayNo].id + "\t\t" + employee[receiverUserArrayNo].name + " \t = ");
                                        findReceiverBankAccountObjectArray = checkRelation.getBalanceShow(employee[receiverUserArrayNo].bankID);
                                        //System.out.println("--------------------------------------------------------------------------\n");
                                        checkRelation.getSeparatorAfter();

                                        try {
                                            if (checkRelation.getTransferBlance(bankAccount, findSenderBankAccountObjectArray, findReceiverBankAccountObjectArray)) {
                                                System.out.println("\n### Before Internal Transfer -");
                                                System.out.println("ID: Account Holder's Name = Balance");
                                                checkRelation.getSeparatorBefore();
                                                //System.out.println("--------------------------------------------------------------------------");
                                                System.out.print("Receiver's Account Details- " + companyInfo.getEmployeePrefix() + employee[receiverUserArrayNo].id + " " + employee[receiverUserArrayNo].name + " = ");
                                                checkRelation.getBalanceShow(employee[receiverUserArrayNo].bankID);
                                                //System.out.println("--------------------------------------------------------------------------\n");
                                                checkRelation.getSeparatorAfter();
                                                checkRelation.getContinue();
                                            }
                                        } catch (NotSufficientBalanceException e) {
                                            checkRelation.getMessageContinue(e.getMessage());
                                        }

                                    } else {
                                        try {
                                            if (checkRelation.getValidationMessageCheck()) {
                                                //checkRelation.getMessageContinue("Recever Employee Bank ID not Found");
                                                throw new ReceverEmployeeBankIDNotFoundException();
                                            }
                                        } catch (ReceverEmployeeBankIDNotFoundException e) {
                                            checkRelation.getMessageContinue(e.getMessage());
                                        }
                                    }

                                } else if (checkReceiverUserId.substring(0, checkPrefixStringSize).equalsIgnoreCase(companyInfo.getClientPrefix())) {
                                    //--> Client ReceverID Check
                                    int userID = checkRelation.getUserIdValidation(checkReceiverUserId, checkPrefixStringSize, checkReceiverUserId.length());

                                    boolean clientIdCheck = false;
                                    for (int i = 0; i < client.length - 1; i++) {
                                        if (userID == client[i].id) {
                                            receiverUserArrayNo = i;
                                            clientIdCheck = true;
                                            break;
                                        }
                                    }

                                    if (clientIdCheck == true) {
                                        checkRelation = new RelationalData(companyInfo, bankAccount);
                                        System.out.println("\n### Receiver Information\nClient ID:\tClient Name = Balance");
                                        checkRelation.getSeparatorBefore();
                                        //System.out.println("--------------------------------------------------------------------------");
                                        System.out.print(companyInfo.getClientPrefix() + client[receiverUserArrayNo].id + "\t\t" + client[receiverUserArrayNo].name + " \t = ");
                                        findReceiverBankAccountObjectArray = checkRelation.getBalanceShow(client[receiverUserArrayNo].bankID);
                                        //System.out.println("--------------------------------------------------------------------------\n");
                                        checkRelation.getSeparatorAfter();

                                        try {
                                            if (checkRelation.getTransferBlance(bankAccount, findSenderBankAccountObjectArray, findReceiverBankAccountObjectArray)) {
                                                System.out.println("\n### Before Internal Transfer -");
                                                System.out.println("ID: Account Holder's Name = Balance");
                                                checkRelation.getSeparatorBefore();
                                                //System.out.println("--------------------------------------------------------------------------");
                                                System.out.print("Receiver's Account Details- " + companyInfo.getClientPrefix() + client[receiverUserArrayNo].id + " " + client[receiverUserArrayNo].name + " = ");
                                                checkRelation.getBalanceShow(client[receiverUserArrayNo].bankID);
                                                //System.out.println("--------------------------------------------------------------------------\n");
                                                checkRelation.getSeparatorAfter();
                                                checkRelation.getContinue();
                                            }
                                        } catch (NotSufficientBalanceException e) {
                                            checkRelation.getMessageContinue(e.getMessage());
                                        }

                                    } else {
                                       try{
                                        if (checkRelation.getValidationMessageCheck()) {
                                            //checkRelation.getMessageContinue("Recever Client Bank ID not Found-");
                                            throw new ReceverClientBankIDNotFoundException();
                                        }
                                       }catch(ReceverClientBankIDNotFoundException e){
                                           checkRelation.getMessageContinue(e.getMessage());
                                       }
                                    }

                                } else {
                                    checkRelation.getMessageContinue("Invalid User ID!!! Try Again.");
                                }

                            }
                        } catch (Exception ex) {

                        }

                    } else if (action == 15) {
                        for (int i = 0; i < totalBankAccount - 1; i++) {
                            bankAccount[i].getBankAccount();
                        }
                    } else if (action == 16) {
                        for (int i = 0; i < totelRelation - 1; i++) {
                            relation[i].getRelationInformation();
                        }
                    } else if (action == 13) {
                        System.out.println("### Setting");
                        System.out.println("1. Edit User Information");
                        System.out.println("2. Change Root login Information");
                        System.out.println("3. Reset All Data");
                        System.out.println("4. Back");
                        System.out.print("\nchoose here: ");
                        chooseOption = checkRelation.getIntegerValue();

                        checkRelation = new RelationalData(companyInfo);
                        if (chooseOption == 1) {
                            int userArrayNo = 0;
                            System.out.print("Enter your User ID: ");
                            //src.nextLine();
                            String checkUserId = src.nextLine();

                            checkRelation = new RelationalData();
                            int checkPrefixStringSize = checkRelation.getPrefixSeparator(checkUserId);

                            if (checkUserId.substring(0, checkPrefixStringSize).equalsIgnoreCase(companyInfo.getCompanyPrefix())) {

                                int userID = checkRelation.getUserIdValidation(checkUserId, checkPrefixStringSize, checkUserId.length());

                                int chooseOptionhere = 0;
                                if (userID == 1) {
                                    System.out.println("\n### Company Name: " + companyInfo.getName());
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Name: ");
                                        companyInfo.setName(src.nextLine());
                                    }

                                    System.out.println("### Company Address: " + companyInfo.getAddress());
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Address: ");
                                        companyInfo.setAddress(src.nextLine());
                                    }

                                    System.out.println("### Company Descriotion: " + companyInfo.getDescriotion());
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Descriotion: ");
                                        companyInfo.setDescriotion(src.nextLine());
                                    }

                                    System.out.println("\n### Company Identity Prefix (Please Don't Use Numbe)");
                                    boolean checkPrefixValidation = true;
                                    System.out.println("### Company ID Prefix: " + companyInfo.getCompanyPrefix());
                                    if (checkRelation.getEditInformationCheck()) {

                                        while (checkPrefixValidation) {
                                            System.out.print("Please enter New Company ID Prefix (Exp: HT-): ");
                                            checkPrefixValidation = companyInfo.setCompanyPrefix(src.nextLine());
                                        }
                                    }

                                    System.out.println("### Bank ID Prefix: " + companyInfo.getBankPrefix());
                                    if (checkRelation.getEditInformationCheck()) {
                                        checkPrefixValidation = true;
                                        while (checkPrefixValidation) {
                                            System.out.print("Please enter New Bank ID Prefix (Exp: HTB-): ");
                                            checkPrefixValidation = companyInfo.setBankPrefix(src.nextLine());
                                        }
                                    }

                                    System.out.println("### Employee ID Prefix: " + companyInfo.getEmployeePrefix());
                                    if (checkRelation.getEditInformationCheck()) {
                                        checkPrefixValidation = true;
                                        while (checkPrefixValidation) {
                                            System.out.print("Please enter New Employee ID Prefix (Exp: HTE-): ");
                                            checkPrefixValidation = companyInfo.setEmployeePrefix(src.nextLine());
                                        }
                                    }

                                    System.out.println("### Client ID Prefix: " + companyInfo.getClientPrefix());
                                    if (checkRelation.getEditInformationCheck()) {
                                        checkPrefixValidation = true;
                                        while (checkPrefixValidation) {
                                            System.out.print("Please enter New Client ID Prefix (Exp: HTC-): ");
                                            checkPrefixValidation = companyInfo.setClientPrefix(src.nextLine());
                                        }
                                    }

                                    System.out.println("### Project ID Prefix: " + companyInfo.getProjectPrefix());
                                    if (checkRelation.getEditInformationCheck()) {
                                        checkPrefixValidation = true;
                                        while (checkPrefixValidation) {
                                            System.out.print("Please enter New Project ID Prefix (Exp: HTP-): ");
                                            checkPrefixValidation = companyInfo.setProjectPrefix(src.nextLine());
                                        }

                                    }
                                } else {
                                    if (checkRelation.getValidationMessageCheck()) {
                                        checkRelation.getMessageContinue("Company ID not Found");
                                    }
                                }

                            } else if (checkUserId.substring(0, checkPrefixStringSize).equalsIgnoreCase(companyInfo.getEmployeePrefix())) {
                                //--> Employee Edit
                                int userID = checkRelation.getUserIdValidation(checkUserId, checkPrefixStringSize, checkUserId.length());

                                boolean idValidationCheck = false;

                                for (int i = 0; i < employee.length - 1; i++) {
                                    if (userID == employee[i].id) {
                                        userArrayNo = i;
                                        idValidationCheck = true;

                                        break;
                                    }
                                }

                                if (idValidationCheck) {
                                    System.out.println("\n### Employee Name: " + employee[userArrayNo].name);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Name: ");
                                        employee[userArrayNo].name = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Date of Birth: " + employee[userArrayNo].dateOfBirth);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Date of Birth: ");
                                        employee[userArrayNo].dateOfBirth = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Sex: " + employee[userArrayNo].sex);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Date of Birth: ");
                                        employee[userArrayNo].sex = src.nextLine();
                                    }

                                    System.out.println("\n### Employee NID: " + employee[userArrayNo].NID);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee NID: ");
                                        employee[userArrayNo].NID = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Blood Group: " + employee[userArrayNo].bloodGroup);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Blood Group: ");
                                        employee[userArrayNo].bloodGroup = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Religion: " + employee[userArrayNo].religion);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Religion: ");
                                        employee[userArrayNo].religion = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Marital Status: " + employee[userArrayNo].maritalStatus);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Marital Status: ");
                                        employee[userArrayNo].maritalStatus = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Email: " + employee[userArrayNo].email);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Email: ");
                                        employee[userArrayNo].email = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Joined Date: " + employee[userArrayNo].joinedDate);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Joined Date: ");
                                        employee[userArrayNo].joinedDate = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Phonen No: " + employee[userArrayNo].phone);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Phonen No: ");
                                        employee[userArrayNo].phone = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Address: " + employee[userArrayNo].address);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Address: ");
                                        employee[userArrayNo].address = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Department: " + employee[userArrayNo].department);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Department: ");
                                        employee[userArrayNo].department = src.nextLine();
                                    }

                                    System.out.println("\n### Employee Salary: " + employee[userArrayNo].salary);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Salary: ");
                                        employee[userArrayNo].salary = checkRelation.getIntegerValue();
                                    }

                                    System.out.println("\n### Employee Comment: " + employee[userArrayNo].comment);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Employee Comment: ");
                                        employee[userArrayNo].comment = src.nextLine();
                                    }

                                    checkRelation.setSaveData(employee);
                                } else {
                                    if (checkRelation.getValidationMessageCheck()) {
                                        checkRelation.getMessageContinue("Employee ID not Found");
                                    }
                                }

                            } else if (checkUserId.substring(0, checkPrefixStringSize).equalsIgnoreCase(companyInfo.getClientPrefix())) {
                                //--> Client Edit
                                int userID = checkRelation.getUserIdValidation(checkUserId, checkPrefixStringSize, checkUserId.length());

                                boolean idValidationCheck = false;

                                for (int i = 0; i < client.length - 1; i++) {
                                    if (userID == client[i].id) {
                                        userArrayNo = i;
                                        idValidationCheck = true;

                                        break;
                                    }
                                }

                                if (idValidationCheck) {
                                    System.out.println("\n### Client Name: " + client[userArrayNo].name);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Client Name: ");
                                        client[userArrayNo].name = src.nextLine();
                                    }

                                    System.out.println("\n### Client Email: " + client[userArrayNo].email);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Client Email: ");
                                        client[userArrayNo].email = src.nextLine();
                                    }

                                    System.out.println("\n### Client Phone: " + client[userArrayNo].phone);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Client Phone: ");
                                        client[userArrayNo].phone = src.nextLine();
                                    }

                                    System.out.println("\n### Client Sex: " + client[userArrayNo].sex);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Client Sex: ");
                                        client[userArrayNo].sex = src.nextLine();
                                    }

                                    System.out.println("\n### Client Address: " + client[userArrayNo].address);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Client Address: ");
                                        client[userArrayNo].address = src.nextLine();
                                    }

                                    System.out.println("\n### Client Comment: " + client[userArrayNo].comment);
                                    if (checkRelation.getEditInformationCheck()) {
                                        System.out.print("Please enter New Client Comment: ");
                                        client[userArrayNo].comment = src.nextLine();
                                        System.out.println();
                                    }
                                    checkRelation.setSaveData(client);

                                } else {
                                    if (checkRelation.getValidationMessageCheck()) {
                                        checkRelation.getMessageContinue("Client ID not Found");
                                    }
                                }

                            }

                        } else if (chooseOption == 2 && checkRelation.getLoginInfoCheck()) {
                            //src.nextLine();
                            System.out.print("### Set New Username: ");
                            companyInfo.setUsername(src.nextLine());
                            System.out.print("### Set New PAssword: ");
                            companyInfo.setPassword(src.nextLine());
                            checkRelation.getMessageContinue("New Login Information Add Successful");
                            checkRelation.setSaveData(companyInfo);

                        } else if (chooseOption == 3) {
                            System.out.println("### Reset All Data");
                            System.out.println("1. Yes");
                            System.out.println("2. No");
                            System.out.print("\nchoose here: ");
                            int chooseOptionHere = checkRelation.getIntegerValue();

                            if (chooseOptionHere == 1 && checkRelation.getLoginInfoCheck()) {
                                companyInfo = null;
                                bankAccount = null;
                                employee = null;
                                client = null;
                                project = null;
                                relation = null;
                                companyInfo = new Company();
                                companyInfo.setDataChecker(true);

                                /*//Company Information Store
                                outCompany = new ObjectOutputStream(new FileOutputStream("c:\\store\\companyInfo.txt"));
                                outCompany.writeObject(companyInfo);
                                //Bank Account Store
                                outBankAccount = new ObjectOutputStream(new FileOutputStream("c:\\store\\bankAccount.txt"));
                                outBankAccount.writeObject(bankAccount);
                                //Employee Store
                                outEmpolyee = new ObjectOutputStream(new FileOutputStream("c:\\store\\employee.txt"));
                                outEmpolyee.writeObject(employee);
                                //Client Store
                                outClient = new ObjectOutputStream(new FileOutputStream("c:\\store\\client.txt"));
                                outClient.writeObject(client);
                                //Project Store
                                outProject = new ObjectOutputStream(new FileOutputStream("c:\\store\\project.txt"));
                                outProject.writeObject(project);
                                //Company Account
                                outRelation = new ObjectOutputStream(new FileOutputStream("c:\\store\\relation.txt"));
                                outRelation.writeObject(relation);
                                System.out.println("\n--------------------------------------------------------------------------");
                                System.out.println("----------------------------All Data Reset Successful---------------------");
                                System.out.println("--------------------------------------------------------------------------\n");
                                checkRelation.getMessage("All Data Reset Successful");*/
                                checkRelation.setSaveAllDataWithMassage("All Data Reset Successful", companyInfo, bankAccount, employee, client, project, relation);
                                loginChecker = false;
                                break;
                            }
                        } else if (chooseOption == 4) {
                            System.out.println();
                        } else {
                            checkRelation.getPressedWrongKey();
                        }
                    } else if (action == 14) {

                        System.out.println("Are you Sure?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        System.out.print("Choose Here: ");
                        chooseOption = checkRelation.getIntegerValue();
                        if (chooseOption == 1) {
                            checkRelation.setSaveAllDataWithMassage("Logout Successful!!!", companyInfo, bankAccount, employee, client, project, relation);
                            loginChecker = false;
                            break;
                        } else if (chooseOption == 2) {
                            System.out.println("Please Continue...");
                        }
                    } else {
                        if (checkRelation.getValidationMessageCheck()) {
                            checkRelation.getPressedWrongKey();
                        }
                    }
                }
            }
        }
    }
}
