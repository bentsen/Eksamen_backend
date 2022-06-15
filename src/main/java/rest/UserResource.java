package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

   /* private static final OwnerRepo ownerREPO =  OwnerRepo.getRepositoryExample(EMF);
    private static final HarbourRepo harbourREPO =  HarbourRepo.getRepositoryExample(EMF);
    private static final BoatRepo boatREPO =  BoatRepo.getRepositoryExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello User\"}";
    }

    @GET
    @Path("allOwners")
    public Response getAllOwners() throws EntityNotFoundException {
        List<OwnerDTO> ownerDTOList = ownerREPO.getAllOwners();
        return Response.ok().entity(ownerDTOList).build();
    }

    @GET
    @Path("harbourBoats/{id}")
    public Response harbourBoats(@PathParam("id") int id) throws EntityNotFoundException {
        List<BoatDTO> boatDTOList = harbourREPO.getAllBoatsInHarbour(id);
        return Response.ok().entity(boatDTOList).build();
    }

    @GET
    @Path("ownsBoat/{id}")
    public Response ownsBoatById(@PathParam("id") int id) throws EntityNotFoundException {
        //List<OwnerDTO> ownsBoat = boatREPO.ownsBoatById(id);
        BoatDTO foundBoat = boatREPO.getById(id);
        return Response.ok().entity(foundBoat.getOwners()).build();
    }*/

}
