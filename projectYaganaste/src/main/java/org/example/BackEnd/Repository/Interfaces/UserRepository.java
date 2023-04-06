package org.example.BackEnd.Repository.Interfaces;

import org.example.BackEnd.Entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
/*
se utiuliza para habilitar la consulta por todos los campos
 */
public interface UserRepository extends CrudRepository<User, String> {
    //boolean existByNombre(String nombre);

    User findByUsernameAndPassword(String userName, String password);
    User findByUsername(String userName);
}


