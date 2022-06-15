package dtos;

import entities.Player;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerDTO {
    private int id;

    private String name;

    private int phone;

    private String email;

    private String status;

    private Set<MatchDTO> matches = new HashSet<>();

    public PlayerDTO(Player player){
        this.id = player.getId();
        this.name = player.getName();
        this.phone = player.getPhone();
        this.email = player.getEmail();
        this.status = player.getStatus();

        player.getMatches().forEach(match -> this.matches.add(new MatchDTO(match)));
    }

    public static List<PlayerDTO> getDtos(List<Player> players){
        List<PlayerDTO> playersDTO = new ArrayList<>();
        players.forEach(player -> playersDTO.add(new PlayerDTO(player)));
        return playersDTO;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<MatchDTO> getMatches() {
        return matches;
    }

    public void setMatches(Set<MatchDTO> matches) {
        this.matches = matches;
    }
}
