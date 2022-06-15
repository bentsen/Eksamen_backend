package dtos;

import entities.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchDTO {
    private int id;

    private String opponentTeam;

    private String judge;

    private String type;

    private boolean inDoors;

    private int locationId;

    public MatchDTO(Match match){
        this.id = match.getId();
        this.opponentTeam = match.getOpponentTeam();
        this.judge = match.getJudge();
        this.type = match.getType();
        this.inDoors = match.isInDoors();
        if(match.getLocation()!=null){
            this.locationId = match.getLocation().getId();
        }
    }

    public static List<MatchDTO> getDtos(List<Match> matches){
        List<MatchDTO> matchesDTO = new ArrayList<>();
        matches.forEach(match -> matchesDTO.add(new MatchDTO(match)));
        return matchesDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpponentTeam() {
        return opponentTeam;
    }

    public void setOpponentTeam(String opponentTeam) {
        this.opponentTeam = opponentTeam;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isInDoors() {
        return inDoors;
    }

    public void setInDoors(boolean inDoors) {
        this.inDoors = inDoors;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
