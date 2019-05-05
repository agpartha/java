package io.anand.springboot;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
/*
    Iterable<User> findAll(Sort sort);
    User findByName(String name);

    Page<User> findAll(Pageable pageable);
 */
}
