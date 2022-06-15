package entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Owner {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String address;
    private int phoneNum;

    @ManyToMany(targetEntity = Boat.class)
    private List<Boat> boats;

    public Owner() {
        super();
    }

    public Owner(int id, String name, String address, int phoneNum, List<Boat> boats) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.boats = boats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }
}
