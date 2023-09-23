package com.ttknpdev.understand.security.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User  {
    @Id // mark PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mark auto increment
    private Long uid;
    private String username;
    private String password;
    private String email;
    /*
    the meaning of CascadeType. ALL
    is that the persistence will propagate (cascade) all EntityManager operations ( PERSIST, REMOVE, REFRESH, MERGE, DETACH ) to the relating entities

    Fetch type Eager is essentially the opposite of Lazy, Eager will by default load ALL of the relationships related to a particular object loaded by Hibernate
    */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // for query relations user_role
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "uid", referencedColumnName = "uid"),
            inverseJoinColumns = @JoinColumn(name = "rid", referencedColumnName = "rid")
    )
    private List<Role> roles;
    /*
                # statement query ???
                select
                    u_r.uid,
                    u_r.rid,
                    r.role
                from
                    user_role AS u_r
                join
                    role AS r
                        on r.rid = u_r.rid
                where
                    u_r.uid = ?
    */

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
