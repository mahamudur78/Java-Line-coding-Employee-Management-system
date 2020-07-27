package HasTech;

public class Employee extends Human {

    //id, name, email, phone, sex
    String dateOfBirth, bloodGroup, religion, maritalStatus, joinedDate, department;
    String NID;
    double salary;
    boolean states;

    public Employee(int id, int empBankID, String name, String dateOfBirth, String sex, String NID, String bloodGroup, String religion, String maritalStatus, String email, String joinedDate, String phone, String address, String department, double salary, String comment, boolean states) {
        super(id, empBankID, name, email, phone, sex, address, comment);
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.NID = NID;
        this.bloodGroup = bloodGroup;
        this.religion = religion;
        this.maritalStatus = maritalStatus;
        this.joinedDate = joinedDate;
        this.department = department;
        this.salary = salary;
        this.states = states;
    }
    
    public void getEmployeeInfoList() {
        System.out.println(id + "\t" + name + "\t" + dateOfBirth + "\t" + sex + "\t" + NID + "\t" + bloodGroup + "\t" + religion + maritalStatus + "\t" + email + "\t" + joinedDate + "\t" + phone + "\t" + address + "\t" + department + "\t" + salary);
    }

}