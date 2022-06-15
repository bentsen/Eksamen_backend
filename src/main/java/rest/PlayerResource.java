package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PlayerDTO;
import errorhandling.API_Exception;
import repository.PlayerRepo;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Path("player")
public class PlayerResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PlayerRepo REPO = PlayerRepo.getRepo(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws EntityNotFoundException, API_Exception {
        List<PlayerDTO> playerDTO;

        try {
            playerDTO = REPO.getAllPlayers();
        } catch (IOException | URISyntaxException e) {
            throw new API_Exception("Owners Not Found", 404, e);
        }

        return Response
                .ok()
                .entity(GSON.toJson(playerDTO))
                .build();
    }

    @POST
    @Path("/createPlayer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBoat(String content) throws EntityNotFoundException, API_Exception {
        PlayerDTO playerDTO;
        PlayerDTO newPlayerDTO;
        try{
             playerDTO = GSON.fromJson(content, PlayerDTO.class);
             newPlayerDTO = REPO.createPlayer(playerDTO);
        }
        catch (IOException | URISyntaxException e) {
            throw new API_Exception("Owners Not Found", 404, e);
        }

        return Response
                .ok()
                .entity(GSON.toJson(newPlayerDTO))
                .build();
    }

    @DELETE
    @Path("/deletePlayer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBoat(@PathParam("id") int id) throws EntityNotFoundException, API_Exception{
        try {
            REPO.deletePlayer(id);
        }
        catch (IOException | URISyntaxException e) {
            throw new API_Exception("Player Not Found", 404, e);
        }
        return Response
                .ok()
                .entity("Deleted")
                .build();
    }
}
