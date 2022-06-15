package repository;

import dtos.MatchDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IMatchRepo {
    List<MatchDTO> getAllMatches() throws IOException, URISyntaxException;
    List<MatchDTO> getMatchesFromPlayer(int id) throws IOException, URISyntaxException;
    List<MatchDTO> getMatchesFromLocation(int id) throws IOException, URISyntaxException;
    void deleteMatch(int id) throws IOException, URISyntaxException;
    MatchDTO createMatch(MatchDTO matchDTO) throws IOException, URISyntaxException;
    MatchDTO updateMatch(int id, MatchDTO matchDTO) throws IOException, URISyntaxException;
    MatchDTO updateAllMatches(int id, MatchDTO matchDTO) throws IOException, URISyntaxException;
    MatchDTO connectMatchToLocation(int matchId, int locationId) throws IOException, URISyntaxException;
}
