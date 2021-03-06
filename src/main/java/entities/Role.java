package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Plaul
 */
@Entity
@Table(name = "roles")
@NamedQuery(name = "role.deleteAllRows", query = "DELETE from Role")
public class Role implements Serializable {
    public static class RoleNames {
        public static final String USER = "user";
        public static final String ADMIN = "admin";
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_name", length = 20)
    private String roleName;
    
    @ManyToMany(mappedBy = "roleList", cascade = CascadeType.PERSIST)
    private List<User> userList;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }   
}
