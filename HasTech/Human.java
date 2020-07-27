package HasTech;
import java.io.Serializable;
public class Human implements Serializable{
    int id, bankID;
    String name, email, phone, sex, address, comment;
    public Human(int id, int bankID, String name, String email, String phone, String sex, String address, String comment){
        this.id = id;
        this.bankID = bankID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.address = address;
        this.comment = comment;
    }
}