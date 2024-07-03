package com.example.RaveFinder.rave_finder.repository;
import com.example.RaveFinder.rave_finder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<User,Long> 
{
    Optional<User> findByUsername(String username);
}
