package dtos;

import entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerDTO {
    private int id;
    private String name;
    private String address;
    private int phoneNum;

    private List<BoatDTO> boats = new ArrayList<>();

    public OwnerDTO(String name, String address, int phoneNum, List<BoatDTO> boats) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.boats = boats;
    }

    public OwnerDTO(Owner owner){
        if(owner.getId()!=0);
            this.id = owner.getId();
        this.name = owner.getName();
        this.address = owner.getAddress();
        this.phoneNum = owner.getPhoneNum();
        owner.getBoats().forEach(boat -> this.boats.add(new BoatDTO(boat)));
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

    public List<BoatDTO> getBoats() {
        return boats;
    }

    public void setBoats(List<BoatDTO> boats) {
        this.boats = boats;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum=" + phoneNum +
                ", boats=" + boats +
                '}';
    }
}
