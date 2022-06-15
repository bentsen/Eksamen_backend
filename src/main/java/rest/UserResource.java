package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.User;
import repository.UserRepo;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("info")
public class UserResource {

    /*private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserRepo REPO =  UserRepo.getRepo(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;
    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response allUsers() {
        List<UserDTO> userDTO;

        userDTO = REPO.getAllUsers();

        return Response
                .ok()
                .entity(GSON.toJson(userDTO))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed({"user"})
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/userInfo")
    @RolesAllowed({"user", "admin"})
    public String getUserName() {
        String thisuser = securityContext.getUserPrincipal().getName();
        EntityManager em = EMF.createEntityManager();
        User currenUser = em.find(User.class, thisuser);
        UserDTO userDTO = new UserDTO(currenUser);
        return GSON.toJson(userDTO);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("newuser")
    public String addNewUser(String data) {
        System.out.println("data" + data);
        UserDTO userDTO = GSON.fromJson(data, UserDTO.class);
        User user = userDTO.toUser();

        User user1 = UserRepo.getRepo(EMF).registerUser(user.getUserName(), user.getUserPass());
        return GSON.toJson(user1);
    }*/
}
