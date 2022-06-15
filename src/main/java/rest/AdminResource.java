package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import errorhandling.API_Exception;
import repository.AdminRepo;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("admin")
public class AdminResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final AdminRepo adminREPO = AdminRepo.getRepositoryExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello User\"}";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createBoat")
    public Response createBoat(String jsonString) throws API_Exception {
        String brand;
        String make;
        String name;
        String image;

        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            brand = json.get("brand").getAsString();
            make = json.get("make").getAsString();
            name = json.get("name").getAsString();
            image = json.get("image").getAsString();
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied",400,e);
        }

        adminREPO.createBoat(brand,make,name,image);
        return Response.ok().entity("Boat with these details, have been made:"
                +"\nBrand: " + brand
                +"\nMake: " + make
                +"\nName: " + name
                +"\nImage: " +image).build();

    }


}
