package HasTech;

import java.io.Serializable;

public class Company implements Serializable {

    private String name, address, descriotion;
    private String username, password;
    private String companyIdPrefix, employeeIdPrefix, clientIdPrefix, projectIdPrefix, bankIdPrefix;
    private boolean checker = true;

    //RelationalData checkRelation = new RelationalData();
    public Company() {

    }

    public Company(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Company(String name, String address, String descriotion, String employeeIdPrefix, String clientIdPrefix, String bankIdPrefix) {
        this.name = name;
        this.address = address;
        this.descriotion = descriotion;
        this.employeeIdPrefix = employeeIdPrefix;
        this.clientIdPrefix = clientIdPrefix;
        this.bankIdPrefix = bankIdPrefix;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDataChecker(boolean checker) {
        this.checker = checker;
    }

    public boolean setDataChecker() {
        return checker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDescriotion(String descriotion) {
        this.descriotion = descriotion;
    }

    public String getDescriotion() {
        return descriotion;
    }

    public boolean checkPrefixValidation(String idPrefix) {
        if (idPrefix.equalsIgnoreCase("")) {
            System.out.println("\n### Please use Prefix.\n");
            return false;
        } else if (idPrefix.equalsIgnoreCase(getCompanyPrefix())) {
            System.out.println("\n### Company ID Prefix or New ID Prefix Same.");
            System.out.println("### Please Do not use same Prefix.\n");
            return false;
        } else if (idPrefix.equalsIgnoreCase(getBankPrefix())) {
            System.out.println("\n### Bank ID Prefix or New ID Prefix Same.");
            System.out.println("### Please Do not use same Prefix\n");
            return false;
        } else if (idPrefix.equalsIgnoreCase(getEmployeePrefix())) {
            System.out.println("\n### Employee ID Prefix or New ID Prefix Same.");
            System.out.println("### Please Do not use same Prefix.\n");
            return false;
        } else if (idPrefix.equalsIgnoreCase(getClientPrefix())) {
            System.out.println("\n### Client ID Prefix or New ID Prefix Same.");
            System.out.println("### Please Do not use same Prefix\n");
            return false;
        } else if (idPrefix.equalsIgnoreCase(getProjectPrefix())) {
            System.out.println("\n### Project ID Prefix or New ID Prefix Same.");
            System.out.println("### Please Do not use same Prefix.\n");
            return false;
        }

        char[] CheckCompanyIdPrefix = idPrefix.toCharArray();
        boolean symbolCheck = false;
        String CheckDataValidation = "";
        for (int i = 0; i < CheckCompanyIdPrefix.length; i++) {
            if (CheckCompanyIdPrefix[i] >= '0' && CheckCompanyIdPrefix[i] <= '9') {

                if (i != 0 && symbolCheck == true) {
                    CheckDataValidation = CheckDataValidation + ", ";
                }
                CheckDataValidation = CheckDataValidation + CheckCompanyIdPrefix[i];

                symbolCheck = true;
            }
        }
        if (symbolCheck == true) {
            System.out.println("\n----------------------------------------------------------------");
            //checkRelation.getSeparatorBefore();
            System.out.println("### Please Avoid this Number Example: \" " + CheckDataValidation + " \"");
            System.out.println("----------------------------------------------------------------\n");
            //checkRelation.getSeparatorAfter();
        }

        if ("".equals(CheckDataValidation)) {
            return true;
        }

        return false;
    }

    public boolean setCompanyPrefix(String companyIdPrefix) {
        if (checkPrefixValidation(companyIdPrefix) == true) {
            this.companyIdPrefix = companyIdPrefix;
            return false;
        }
        return true;
    }

    public String getCompanyPrefix() {
        return companyIdPrefix;
    }

    public boolean setBankPrefix(String bankIdPrefix) {
        if (checkPrefixValidation(bankIdPrefix) == true) {
            this.bankIdPrefix = bankIdPrefix;
            return false;
        }
        return true;
    }

    public String getBankPrefix() {
        return bankIdPrefix;
    }

    public boolean setEmployeePrefix(String employeeIdPrefix) {
        if (checkPrefixValidation(employeeIdPrefix) == true) {
            this.employeeIdPrefix = employeeIdPrefix;
            return false;
        }
        return true;
    }

    public String getEmployeePrefix() {
        return employeeIdPrefix;
    }

    public boolean setClientPrefix(String clientIdPrefix) {
        if (checkPrefixValidation(clientIdPrefix) == true) {
            this.clientIdPrefix = clientIdPrefix;
            return false;
        }
        return true;
    }

    public String getClientPrefix() {
        return clientIdPrefix;
    }

    public boolean setProjectPrefix(String projectIdPrefix) {
        if (checkPrefixValidation(projectIdPrefix) == true) {
            this.projectIdPrefix = projectIdPrefix;
            return false;
        }
        return true;
    }

    public String getProjectPrefix() {
        return projectIdPrefix;
    }
}
