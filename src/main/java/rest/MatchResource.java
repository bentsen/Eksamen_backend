package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MatchDTO;
import dtos.PlayerDTO;
import errorhandling.API_Exception;
import repository.MatchRepo;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Path("match")
public class MatchResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final MatchRepo REPO = MatchRepo.getRepo(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws EntityNotFoundException, API_Exception {
        List<MatchDTO> matchDTO;

        try {
            matchDTO = REPO.getAllMatches();
        } catch (IOException | URISyntaxException e) {
            throw new API_Exception("Matches Not Found", 404, e);
        }

        return Response
                .ok()
                .entity(GSON.toJson(matchDTO))
                .build();
    }

    @GET
    @Path("/player/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchesFromPlayer(@PathParam("id") int id) throws EntityNotFoundException, API_Exception {
        List<MatchDTO> matchesDTO;
        try{
            matchesDTO = REPO.getMatchesFromPlayer(id);
        } catch (IOException | URISyntaxException e) {
            throw new API_Exception("Matches Not Found", 404, e);
        }

        return Response
                .ok()
                .entity(GSON.toJson(matchesDTO))
                .build();
    }

    @GET
    @Path("/location/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchesFromLocation(@PathParam("id") int id) throws EntityNotFoundException, API_Exception {
        List<MatchDTO> matchesDTO;
        try{
            matchesDTO = REPO.getMatchesFromLocation(id);
        } catch (IOException | URISyntaxException e) {
            throw new API_Exception("Matches Not Found", 404, e);
        }

        return Response
                .ok()
                .entity(GSON.toJson(matchesDTO))
                .build();
    }

    @POST
    @Path("/createMatch")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBoat(String content) throws EntityNotFoundException, API_Exception {
        MatchDTO matchDTO;
        MatchDTO newMatchDTO;
        try{
            matchDTO = GSON.fromJson(content, MatchDTO.class);
            newMatchDTO = REPO.createMatch(matchDTO);
        }
        catch (IOException | URISyntaxException e) {
            throw new API_Exception("Match Not Found", 404, e);
        }

        return Response
                .ok()
                .entity(GSON.toJson(newMatchDTO))
                .build();
    }

    @DELETE
    @Path("/deleteMatch/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBoat(@PathParam("id") int id) throws EntityNotFoundException, API_Exception{
        try {
            REPO.deleteMatch(id);
        }
        catch (IOException | URISyntaxException e) {
            throw new API_Exception("Match Not Found", 404, e);
        }
        return Response
                .ok()
                .entity("Deleted")
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    @RolesAllowed("admin")
    public Response updateMatch(@PathParam("id") int id, String content) throws API_Exception {
        MatchDTO matchDTO;
        MatchDTO newMatchDTO;
        try{
            matchDTO = GSON.fromJson(content, MatchDTO.class);
            newMatchDTO = REPO.updateMatch(id,matchDTO);
        }
        catch (IOException | URISyntaxException e) {
            throw new API_Exception("Match Not Found", 404, e);
        }

        return Response
                .ok()
                .entity(GSON.toJson(newMatchDTO))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/location/{locationID}/{matchID}")
    @RolesAllowed("admin")
    public String connectLocation(@PathParam("locationID") int locationID, @PathParam("matchID") int matchID) throws API_Exception {
        try{
            MatchDTO matchDTO = REPO.connectMatchToLocation(matchID,locationID);
            return GSON.toJson(matchDTO);
        } catch (IOException | URISyntaxException e) {
            throw new API_Exception("Match Not Found", 404, e);
        }
    }
}
