package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Location.deleteAllRows", query = "DELETE from Location")
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "circumstances")
    private String circumstances;

    @OneToMany(mappedBy = "location")
    private Set<Match> matches = new HashSet<>();

    public Location(){
    }

    public Location(String address, String city, String condition){
        this.address = address;
        this.city = city;
        this.circumstances = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCircumstances() {
        return circumstances;
    }

    public void setCircumstances(String condition) {
        this.circumstances = condition;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }
}
