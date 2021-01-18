package sk.jarina.reservationsvaiibackend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.jarina.reservationsvaiibackend.model.User;

import java.util.UUID;

@Repository("UserDao")
public interface UserDao extends CrudRepository<User, UUID> {
    Iterable<User> findByEmail(String userEmail);
}
