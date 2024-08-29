package demo.mvc.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import demo.mvc.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
}
