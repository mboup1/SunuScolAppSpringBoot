package com.App.SunuScol;

import com.App.SunuScol.model.ClassStudent;
import com.App.SunuScol.model.User;
import com.App.SunuScol.service.ClassStudentService;
import com.App.SunuScol.service.RoleService;
import com.App.SunuScol.service.StudentService;
import com.App.SunuScol.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class SunuScolApplication implements CommandLineRunner {


	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ClassStudentService classStudentService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SunuScolApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		//Un utilisateur peut avoir plusieurs élèves
		try {
			ClassStudent classStudentId1 = classStudentService.getClassStudent(1);

			if (classStudentId1 != null) {
				System.out.println("Classe : "+classStudentId1.getClassLevel());
				AtomicInteger counter2 = new AtomicInteger(1); // Initialiser un compteur

				classStudentId1.getStudents().forEach(
						student -> System.out.println("Etudiant " + counter2.getAndIncrement() + " : "+student.getLastName()));
			} else {
				System.out.println("L'élève n'a pas été trouvé.");
			}
		} catch (Exception e) {
			System.err.println("Une erreur s'est produite lors de la récupération de l'élève : " + e.getMessage());
		}


//		//Un utilisateur peut avoir plusieurs élèves
//		try {
//			User userId1 = userService.getUser(1);
//
//			if (userId1 != null) {
//				System.out.println("Utilisateur : "+userId1.getUserName());
//				AtomicInteger counter2 = new AtomicInteger(1); // Initialiser un compteur
//
//				userId1.getStudents().forEach(
//						student -> System.out.println("Elève " + counter2.getAndIncrement() + " : "+student.getLastName()));
//			} else {
//				System.out.println("L'élève n'a pas été trouvé.");
//			}
//		} catch (Exception e) {
//			System.err.println("Une erreur s'est produite lors de la récupération de l'élève : " + e.getMessage());
//		}


//		// Créer la base de données
//		jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS db_scol");
//		// Création des tables
//		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (user_id INT AUTO_INCREMENT, first_name VARCHAR(50), last_name VARCHAR(50), PRIMARY KEY (user_id))");
//		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS role (role_id INT AUTO_INCREMENT, role_name VARCHAR(50), permissions VARCHAR(255), user_id INT, PRIMARY KEY (role_id), FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE)");
//		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS user_role (user_id INT, role_id INT, FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE, FOREIGN KEY (role_id) REFERENCES role(role_id) ON DELETE CASCADE, PRIMARY KEY (user_id, role_id))");
//		// Insertion de données
//		jdbcTemplate.execute("INSERT INTO users (user_id, first_name, last_name) VALUES (1,'Dame','MBOUP'), (2,'Elon ','MUSK'), (3,'Bill','GATES'), (4,'Jeff','BEZOS'), (5,'Mark','ZUCKERBERG'), (6,'Steve','Jobs'), (7,'test','test')");
//		jdbcTemplate.execute("INSERT INTO role (role_id, role_name, permissions, user_id) VALUES (1,'Spe maths','Enseigner', 1), (2,'Classes enseignées','Evaluer',1), (3,'Elèves','Noter',1), (4,'Elève','Surveiller',3), (5,'exclure','Sanctionner',5), (6,'test','test',6)");
//		jdbcTemplate.execute("INSERT INTO user_role (user_id, role_id) VALUES (1,1),(1,2),(1,3),(3,4),(2,1),(3,1), (4,3), (5,5), (6,6)");

////		Ajouter un role
//		userService.getUsers().forEach(
//				user -> System.out.println(user.getLastName()));
//		User newUser = new User();
//		newUser.setLastName("Jobs");
//		newUser.setFirstName("Steve");
//
//		newUser = userService.addUser(newUser);
//
////		Ajouter un utilisateur et l'attribuer à un role'
//		userService.getUsers().forEach(
//				user -> System.out.println(user.getFirstName() + " "+ user.getLastName()));
//
//		Role newRole = new Role();
//		newRole.setRoleName("Technicien");
//		newRole.setPermissions("Réparer");
//		newRole.setUserId((long) newUser.getUserId());
//
//		newUser.addRole(newRole);
//		newRole = roleService.addRole(newRole);
//
//		roleService.getRoles().forEach(role -> System.out.println(role.getRoleName()));
//		newRole.getUsers().forEach(user -> System.out.println(user.getLastName()));

		//Mise à jour données personelles
//		User existingUser = userService.getUser(1);
//		System.out.println(existingUser.getLastName());
//		existingUser.setLastName("MBOUP6");
//		userService.addUser(existingUser);
//		existingUser = userService.getUser(1);
//		System.out.println(existingUser.getLastName());

//		//Supprimer un utilisateur
//		User existingUser = userService.getUser(10);
//		try {
//			if (existingUser.getUserId() != null){
//				System.out.println(existingUser.getLastName());
//				userService.deleteUser(existingUser.getUserId());
//				System.out.println("Suppression de "+ existingUser.getLastName());
//			}
//		}catch(Exception e){
//		System.out.println("Utilisateur inexistant");
//	}

		//Un role peut être attribué à plusieurs utilisateurs.
//		try {
//			Role roleId1 = roleService.getRole(4);
//
//			if (roleId1 != null) {
//				System.out.println("Role : "+ roleId1.getRoleName());
//				AtomicInteger counter = new AtomicInteger(1); // Initialiser un compteur
//
//				roleId1.getUsers().forEach(
//						user -> System.out.println("Utilisateur " + counter.getAndIncrement() + " : " + user.getLastName()));
//			} else {
//				System.out.println("Le role n'a pas été trouvé.");
//			}
//		} catch (Exception e) {
//			System.err.println("Une erreur s'est produite lors de la récupération du role : " + e.getMessage());
//		}

		//Un utilisateur peut avoir plusieurs roles
//		try {
//			User userId1 = userService.getUser(20);
//
//			if (userId1 != null) {
//				System.out.println("Utilisateur : "+userId1.getLastName());
//				AtomicInteger counter2 = new AtomicInteger(1); // Initialiser un compteur
//
//				userId1.getRoles().forEach(
//						role -> System.out.println("Role " + counter2.getAndIncrement() + " : "+role.getRoleName()));
//			} else {
//				System.out.println("L'utilisateur n'a pas été trouvé.");
//			}
//		} catch (Exception e) {
//			System.err.println("Une erreur s'est produite lors de la récupération de l'utilisateur : " + e.getMessage());
//		}
	}
}

