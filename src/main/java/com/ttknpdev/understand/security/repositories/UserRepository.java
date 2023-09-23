package com.ttknpdev.understand.security.repositories;

import com.ttknpdev.understand.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    /*
    Hibernate:
                # find user tale by username or email
                select
                    u.uid,
                    u.email,
                    u.password,
                    u.username
                from
                    user as u **
                where
                    u.username = ?
                    or
                    u.email = ?
    Hibernate:
                # find user_role tale by uid
                # Many to Many
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
}
