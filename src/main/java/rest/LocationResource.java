package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.LocationDTO;
import dtos.MatchDTO;
import errorhandling.API_Exception;
import repository.LocationRepo;
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

@Path("location")
public class LocationResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final LocationRepo REPO = LocationRepo.getRepo(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws EntityNotFoundException, API_Exception {
        List<LocationDTO> locationDTO;

        try {
            locationDTO = REPO.getAllLocations();
        } catch (IOException | URISyntaxException e) {
            throw new API_Exception("locations Not Found", 404, e);
        }

        return Response
                .ok()
                .entity(GSON.toJson(locationDTO))
                .build();
    }

    @POST
    @Path("/createLocation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response createLocation(String content) throws EntityNotFoundException, API_Exception {
        LocationDTO locationDTO;
        LocationDTO newLocationDTO;
        try{
            locationDTO = GSON.fromJson(content, LocationDTO.class);
            newLocationDTO = REPO.createLocation(locationDTO);
        }
        catch (IOException | URISyntaxException e) {
            throw new API_Exception("Match Not Found", 404, e);
        }

        return Response
                .ok()
                .entity(GSON.toJson(newLocationDTO))
                .build();
    }

    @DELETE
    @Path("/deleteLocation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response deleteMatch(@PathParam("id") int id) throws EntityNotFoundException, API_Exception{
        try {
            REPO.deleteLocation(id);
        }
        catch (IOException | URISyntaxException e) {
            throw new API_Exception("Match Not Found", 404, e);
        }
        return Response
                .ok()
                .entity("Deleted")
                .build();
    }
}
