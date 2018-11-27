package com.yeta.userservice.repository;

import com.yeta.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author YETA
 * @date 2018/11/27/13:53
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Integer> {
}
