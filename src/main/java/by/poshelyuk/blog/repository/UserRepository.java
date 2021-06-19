package by.poshelyuk.blog.repository;


import by.poshelyuk.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    Optional<User> findById(String id);

}
