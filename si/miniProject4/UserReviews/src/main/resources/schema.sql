DROP TABLE IF EXISTS Review;
CREATE TABLE Review (
 id INT AUTO_INCREMENT PRIMARY KEY,
 brand VARCHAR(50) NOT NULL,
 stars INT NOT NULL,
 notes varchar(200),
 name varchar (50) NOT NULL
);