package com.dengo.erp.repository;

import com.dengo.erp.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * User Repository
 * Repository that holds all department requests to the database
 *
 * @author Dmitry Sheremet, Sergey Petrovsky
 */
public interface UserRepository extends JpaRepository<User, Long> {

    //TODO: When we override this method, we start dubbing users when one user we add more than one skill, but if we use the standard findAll then falling two test
    @Override
    @EntityGraph(value = "user.departments", type = EntityGraph.EntityGraphType.LOAD)
    List<User> findAll();

    @Override
    @EntityGraph(value = "user.departments", type = EntityGraph.EntityGraphType.LOAD)
    User findOne(Long id);

    @Cacheable("responsible")
    @Query(value = "SELECT DISTINCT * FROM user", nativeQuery = true)
    List<User> listOfResponsible();

    @Query(value = "SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
