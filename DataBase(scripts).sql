------------------------------------------------------------------------------------------------------------------------
BOOKS
------------------------------------------------------------------------------------------------------------------------

CREATE DATABASE bookstore_pozhilov;

CREATE TABLE books
(
    id BIGSERIAL PRIMARY KEY,
    author VARCHAR(255),
    isbn VARCHAR(255),
    numberOfPages INT,
    price DECIMAL(10,2),
    yearOfPublishing INT,
	title VARCHAR(255)
);

INSERT INTO books (author, isbn, number_of_pages, price, year_of_publishing, title)
VALUES
('J.K. Rowling', '9780545010221', 336, 25.99, 1997,'Загадка синего лотоса'),
('George R.R. Martin', '9780553103540', 694, 32.50, 1996,'Тень прошлого'),
('Harper Lee', '9780061120084', 376, 15.00, 1960,'Путь великана'),
('F. Scott Fitzgerald', '9780743273565', 180, 9.99, 1925,'Лабиринты разума'),
('Jane Austen', '9780141439518', 352, 7.99, 1813,'Похититель душ'),
('Stephen King', '9781501180989', 1138, 27.99, 1986,'Звездные хроники'),
('Markus Zusak', '9780375842207', 552, 12.99, 2005,'Пленники времени'),
('Ernest Hemingway', '9780684801469', 127, 10.99, 1926,'Искусство забыть'),
('Gabriel Garcia Marquez', '9781410496343', 348, 14.95, 1967,'Искусство забыть'),
('Dan Brown', '9780385504218', 593, 9.99, 2003,'Сияние во тьме'),
('Margaret Atwood', '9780385721677', 311, 17.00, 1985,'Падение солнечной империи'),
('Victor Hugo', '9780451525260', 1488, 14.95, 1862,'Падение солнечной империи'),
('J.R.R. Tolkien', '9780618640157', 1178, 23.95, 1954,'Зов предков'),
('Leo Tolstoy', '9780143035008', 1392, 16.00, 1869,'Погружение в бездну'),
('Herman Melville', '9780142437261', 650, 11.00, 1851,'Крестоносцы душ'),
('Charles Dickens', '9780141439600', 720, 8.00, 1859,'Астральный мост'),
('Mary Shelley', '9780141439471', 352, 9.99, 1818,'Секреты затерянного острова'),
('Arthur Conan Doyle', '9781435142137', 256, 6.99, 1892,'Времена перемен'),
('Ray Bradbury', '9781451673319', 249, 13.99, 1953,'Чарующий маг'),
('Agatha Christie', '9780062073488', 265, 7.99, 1939,'Собиратель звезд');

-----------------------------------------------------------------------------------------------------------------------
USERS
-----------------------------------------------------------------------------------------------------------------------

CREATE TABLE users
(
	id BIGSERIAL PRIMARY KEY,
	firstName VARCHAR(255),
	lastName VARCHAR(255),
	email VARCHAR(255),
	dateOfBirth VARCHAR(255),
	gender VARCHAR(255),
	phoneNumber VARCHAR(255)
);

INSERT INTO users (first_name, last_name, email, date_of_birth, gender, phone_number)
VALUES
('John', 'Doe', 'johndoe@example.com', '1990-01-01', 'Male', '+1234567890'),
('Jane', 'Smith', 'janesmith@example.com', '1992-05-15', 'Female', '+9876543210'),
('David', 'Johnson', 'davidjohnson@example.com', '1985-09-30', 'Male', '+1122334455'),
('Emily', 'Brown', 'emilybrown@example.com', '1988-12-12', 'Female', '+9988776655'),
('Michael', 'Wilson', 'michaelwilson@example.com', '1993-06-20', 'Male', '+5544332211'),
('Sarah', 'Anderson', 'sarahanderson@example.com', '1991-02-18', 'Female', '+6677889900'),
('Christopher', 'Martinez', 'christophermartinez@example.com', '1987-07-04', 'Male', '+1122998877'),
('Olivia', 'Lee', 'olivialeee@example.com', '1989-11-27', 'Female', '+8899776655'),
('Daniel', 'Taylor', 'danieltaylor@example.com', '1995-09-10', 'Male', '+5566778899'),
('Sophia', 'Walker', 'sophiawalker@example.com', '1986-03-05', 'Female', '+9988776655'),
('Matthew', 'Harris', 'matthewharris@example.com', '1994-08-22', 'Male', '+2233445566'),
('Ava', 'Thomas', 'avathomas@example.com', '1998-12-09', 'Female', '+6655443322'),
('Andrew', 'White', 'andrewwhite@example.com', '1997-01-14', 'Male', '+8877665544'),
('Isabella', 'Robinson', 'isabellarobinson@example.com', '1996-04-27', 'Female', '+9988776655'),
('Joseph', 'Clark', 'josephclark@example.com', '1992-07-08', 'Male', '+1122334455'),
('Mia', 'Evans', 'miaevans@example.com', '1993-09-16', 'Female', '+3322114466'),
('James', 'Hall', 'jameshall@example.com', '1990-11-19', 'Male', '+6655443322'),
('Charlotte', 'Lewis', 'charlottelewis@example.com', '1988-03-24', 'Female', '+9988776655'),
('Benjamin', 'Young', 'benjaminyoung@example.com', '1995-05-11', 'Male', '+1122334455'),
('Amelia', 'King', 'ameliaking@example.com', '1991-06-26', 'Female', '+4455667788');

ALTER TABLE users ADD COLUMN password VARCHAR(255);
UPDATE users SET password = MD5(RANDOM()::text) FROM generate_series(1, 20);

-----------------------------------------------------------------------------------------------------------------------
ORDERS
-----------------------------------------------------------------------------------------------------------------------

CREATE TABLE orders
(
	id BIGSERIAL PRIMARY KEY,
	user_id INT8 REFERENCES users NOT NULL,
	total_cost DECIMAL(10,2),
	status VARCHAR(255) NOT NULL
);

CREATE TABLE order_item
(
id BIGSERIAL PRIMARY KEY,
order_id INT8 REFERENCES orders NOT NULL,
book_id INT8 REFERENCES books NOT NULL,
quantity INT,
price DECIMAL(10,2) REFERENCES books NOT NULL
);

INSERT INTO orders (user_id, total_cost, status)
VALUES
(1, 29.99, 'PENDING'),
(2, 59.99, 'PAID'),
(3, 14.99, 'DELIVERED'),
(1, 39.99, 'PAID'),
(2, 19.99, 'PENDING'),
(3, 24.99, 'DELIVERED'),
(1, 10.99, 'CANCELED'),
(2, 49.99, 'PAID'),
(3, 34.99, 'PENDING'),
(1, 9.99, 'DELIVERED'),
(2, 74.99, 'PAID'),
(3, 19.99, 'PENDING'),
(1, 14.99, 'PENDING'),
(2, 64.99, 'PAID'),
(3, 29.99, 'CANCELED'),
(1, 39.99, 'DELIVERED'),
(2, 19.99, 'PAID'),
(3, 24.99, 'PENDING'),
(1, 39.99, 'PENDING'),
(2, 64.99, 'DELIVERED');

INSERT INTO order_item (order_id, book_id, quantity, price)
VALUES
(1, 1, 2, 19.99),
(1, 2, 1, 9.99),
(2, 3, 3, 14.99),
(2, 4, 1, 19.99),
(3, 1, 1, 19.99),
(3, 3, 2, 24.99),
(4, 2, 1, 9.99),
(4, 4, 1, 19.99),
(5, 1, 3, 29.99),
(5, 4, 2, 19.99),
(6, 3, 1, 14.99),
(6, 4, 1, 19.99),
(7, 2, 1, 9.99),
(7, 3, 1, 14.99),
(8, 1, 2, 19.99),
(8, 4, 1, 19.99),
(9, 2, 1, 9.99),
(9, 3, 1, 14.99),
(10, 1, 3, 29.99),
(10, 4, 2, 19.99);

-----------------------------------------------------------------------------------------------------------------------