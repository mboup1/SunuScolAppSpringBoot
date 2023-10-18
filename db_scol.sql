CREATE DATABASE db_scol;


-- @ManyToMany'
-- Création de la table 'users'
CREATE TABLE IF NOT EXISTS  users (
    user_id INT AUTO_INCREMENT,
    first_name VARCHAR(50),
    user_name VARCHAR(50),
    PRIMARY KEY (user_id)
);


-- Création de la table 'role'
CREATE TABLE IF NOT EXISTS role (
    role_id INT AUTO_INCREMENT,
    role_name VARCHAR(50),
    permissions VARCHAR(255),
    user_id INT,
    PRIMARY KEY (role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
-- Création de la table 'user_roles' pour la relation many-to-many de User
CREATE TABLE IF NOT EXISTS  user_roles (
    user_id INT,
    role_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(role_id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS  student (
    student_id INT AUTO_INCREMENT,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    user_id INT,
    PRIMARY KEY (student_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
-- Création de la table 'user_students' pour la relation one-to-many
CREATE TABLE IF NOT EXISTS  user_students (
    user_id INT,
    student_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, student_id)
);



INSERT INTO `users` VALUES ( 1,'Dame@','MBOUP'),
                            (2,'Elon@','MUSK'),
                            (3,'Bill@','GATES'),
                            (4,'Jeff@','BEZOS'),
                             (5,'Mark@','ZUCKERBERG'),
                             (6,'Steve@','Jobs'),
                             (7,'test@','test');

INSERT INTO `role` VALUES ( 1,'Spe maths','Enseigner', 1),
                            (2,'Classes enseignées','Evaluer',1),
                            (3,'Elèves','Noter',1),
                            (4,'Elève','Surveiller',3),
                             (5,'exclure','Sanctionner',5),
                             (6,'test','test',6);

INSERT INTO `user_roles` VALUES (1,1),(1,2),(1,3),(3,4),(2,1),(3,1), (4,3), (5,5), (6,6);

INSERT INTO `student` VALUES ( 1,'MBOUP','Baye Dame', 1),
                            (2,'Elon@','MUSK',1),
                            (3,'Bill@','GATES',1),
                            (4,'Jeff@','BEZOS',3),
                            (5,'Mark@','ZUCKERBERG',3),
                            (6,'Steve@','Jobs',5),
                            (7,'Steve@','Jobs',6);

INSERT INTO `user_students` VALUES (1,1),(1,2),(1,3),(3,4),(2,1),(3,1), (4,3), (5,5), (6,6);
