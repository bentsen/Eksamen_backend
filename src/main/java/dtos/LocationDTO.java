package dtos;

import entities.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocationDTO {
    private int id;

    private String address;

    private String city;

    private String condition;

    private Set<MatchDTO> matches = new HashSet<>();

    public LocationDTO(Location location){
        this.id = location.getId();
        this.city = location.getCity();
        this.condition = location.getCircumstances();

        location.getMatches().forEach(match -> this.matches.add(new MatchDTO(match)));
    }

    public static List<LocationDTO> getDtos(List<Location> locations){
        List<LocationDTO> locationsDTO = new ArrayList<>();
        locations.forEach(location -> locationsDTO.add(new LocationDTO(location)));
        return locationsDTO;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Set<MatchDTO> getMatches() {
        return matches;
    }

    public void setMatches(Set<MatchDTO> matches) {
        this.matches = matches;
    }
}
