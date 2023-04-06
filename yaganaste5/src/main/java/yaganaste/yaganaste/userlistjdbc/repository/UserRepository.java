package yaganaste.yaganaste.userlistjdbc.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import yaganaste.yaganaste.userlistjdbc.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
