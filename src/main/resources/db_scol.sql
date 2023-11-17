-- Création de la base de données
CREATE DATABASE IF NOT EXISTS db_scol;

-- Utilisation de la base de données
USE db_scol; 

-- Création de la table 'users'
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT,
    user_name VARCHAR(50),
    email VARCHAR(50),
    class_id INT,
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
CREATE TABLE IF NOT EXISTS user_roles (
    user_id INT,
    role_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(role_id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- Création de la table 'class_student' pour la relation many-to-many
CREATE TABLE IF NOT EXISTS class_student (
    class_id INT AUTO_INCREMENT,
    class_level VARCHAR(255),
    user_id INT,
    PRIMARY KEY (class_id)
--    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Création de la table 'student' pour la relation many-to-many
CREATE TABLE IF NOT EXISTS student (
    student_id INT AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    user_id INT,
    class_id INT,
    PRIMARY KEY (student_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES class_student(class_id) ON DELETE CASCADE
);

-- Création de la table 'user_students' pour la relation many-to-many
CREATE TABLE IF NOT EXISTS user_students (
    user_id INT,
    student_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, student_id)
);

-- Création de la table 'class_students_student' pour la relation many-to-many
CREATE TABLE IF NOT EXISTS class_students_student (
    class_id INT,
    student_id INT,
    FOREIGN KEY (class_id) REFERENCES class_student(class_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
    PRIMARY KEY (class_id, student_id)
);

CREATE TABLE IF NOT EXISTS user_class_students (
    user_id INT,
    class_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES class_student(class_id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, class_id)
);

-- Insertion de données dans la table 'users'
INSERT INTO users (user_id, user_name, email, class_id)
VALUES (1, 'MBOUP', 'Dame@',1),
       (2, 'MUSK', 'Elon@',1),
       (3, 'GATES', 'Bill@',2),
       (4, 'BEZOS', 'Jeff@',3),
       (5, 'ZUCKERBERG', 'Mark@',4),
       (6, 'Jobs', 'Steve@',5);

-- Insertion de données dans la table 'class_student'
INSERT INTO class_student (class_id, class_level, user_id)
VALUES (1, '3eme', 1),
       (2, '4eme', 1),
       (3, '6eme', 1),
       (4, '1ere', 3),
       (5, 'Tle', 5),
       (6, '2nde', 5);

-- Insertion de données dans la table 'role'
INSERT INTO role (role_id, role_name, permissions, user_id)
VALUES (1, 'Spe maths', 'Enseigner', 1),
       (2, 'Classes enseignées', 'Evaluer', 1),
       (3, 'Elèves', 'Noter', 1),
       (4, 'Elève', 'Surveiller', 3),
       (5, 'exclure', 'Sanctionner', 5),
       (6, 'test', 'test', 6);

-- Insertion de données dans la table 'student'
INSERT INTO student (student_id, first_name, last_name, user_id, class_id)
VALUES  (1,'Baye Dame', 'MBOUP', 1, 1),
        (2,'Elon','MUSK',1, 1),
        (3,'Bill','GATES',1, 1),
        (4,'Jeff','BEZOS',3, 3),
        (5,'Mark','ZUCKERBERG',3, 3),
        (6,'Steve','Jobs',5, 5);

-- Insertion de données dans la table 'user_roles'
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (3, 4),
       (5, 5),
       (6, 6);

-- Insertion de données dans la table 'user_students'
INSERT INTO user_students (user_id, student_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (3, 4),
       (5, 5),
       (5, 6);

-- Insertion de données dans la table 'class_students_student'
INSERT INTO class_students_student (class_id, student_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (3, 4),
       (5, 5),
       (5, 6);

-- Insertion de données dans la table 'user_class_students'
INSERT INTO user_class_students (user_id, class_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (3, 4),
       (5, 5),
       (5, 6);


