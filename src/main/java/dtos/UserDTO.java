package dtos;

import entities.Player;
import entities.Role;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    String userName;

    List<String> roles;

    String userPass;

    public UserDTO(User user)
    {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.roles = getRoles(user.getRoleList());
    }

    public static List<UserDTO> getDtos(List<User> users){
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(user -> usersDTO.add(new UserDTO(user)));
        return usersDTO;
    }

    public User toUser() {
        return new User(this.userName, this.userPass);
    }

    public List<String> getRoles(List<Role> roles){
        List<String> stringRoles = new ArrayList<>();
        roles.forEach(role -> stringRoles.add(role.getRoleName()));
        return stringRoles;
    }
}
