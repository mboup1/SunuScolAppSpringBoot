package com.App.SunuScol.repository;

import com.App.SunuScol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Utilisation procédure stockées
    @Procedure(name = "User.addUser")
    void addUser(String p_lastName, String p_firstName, String p_password,
                 String p_email, String p_birthDay, String p_birthPlace, String p_adress);

}
