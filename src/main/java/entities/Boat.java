package entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Boat {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    private String brand;
    private String make;
    private String name;
    private String image;

    @ManyToMany(targetEntity = Owner.class)
    private List<Owner> owners;

    @ManyToOne
    private Harbour harbour;

    public Boat(){
        super();
    }

    public Boat(int id, String brand, String make, String name, String image, List<Owner> owners) {
        super();
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
        this.owners = owners;
    }

    public Boat(String brand, String make, String name, String image, List<Owner> owners, Harbour harbour) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
        this.owners = owners;
        this.harbour = harbour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public Harbour getHarbour() {
        return harbour;
    }

    public void setHarbour(Harbour harbour) {
        this.harbour = harbour;
    }
}
