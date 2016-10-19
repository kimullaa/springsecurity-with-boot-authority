package com.example.repositories;

import com.example.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{
    public User findOneByName(String name);
}
