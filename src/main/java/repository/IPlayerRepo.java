package repository;

import dtos.PlayerDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IPlayerRepo {
    List<PlayerDTO> getAllPlayers() throws IOException, URISyntaxException;
    void deletePlayer(int id) throws IOException, URISyntaxException;
    PlayerDTO createPlayer(PlayerDTO playerDTO) throws IOException, URISyntaxException;
}
