-- Tạo cơ sở dữ liệu
-- Drop database TravDana;
CREATE DATABASE TravDana;
USE TravDana;

-- Bảng Role
CREATE TABLE Role (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(10)
);

-- Bảng User
CREATE TABLE User (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    role_id INT,
    user_name VARCHAR(50) Unique,
    password VARCHAR(255),
    full_name VARCHAR(100),
    phone VARCHAR(15) Unique,
    email VARCHAR(100) Unique,
    birthday DATE,
    status VARCHAR(20),
    gender BOOLEAN,
    otp varchar(10) NULL,
    otp_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     FOREIGN KEY (role_id) REFERENCES Role(role_id)
);


-- Bảng Email
CREATE TABLE Email (
    email_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50),
    subject VARCHAR(255),
    content TEXT,
    status VARCHAR(50),
    sent_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_name) REFERENCES User(user_name)
);

-- Bảng Tour
CREATE TABLE Tour (
    tour_id VARCHAR(20) PRIMARY KEY,
    tour_name VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE,
    number_day VARCHAR(50),
    price INT,
    child_price INT,
    tour_status VARCHAR(20),
    min_capacity INT,
    sale INT,
    images varchar(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng Booking
CREATE TABLE Booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50),
    tour_id varchar(20),
    cccd VARCHAR(12),
    total_price INT,
    amount_paid INT,
    payment_status VARCHAR(50),
    booking_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    adult_count INT,
    child_count INT,
    content_payment VARCHAR(50),
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_name) REFERENCES User(user_name),
    FOREIGN KEY (tour_id) REFERENCES Tour(tour_id)
);

-- Bảng Tour_review
CREATE TABLE Tour_review (
    tour_review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50),
    tour_id VARCHAR(20),
    rating INT,
    comment TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_name) REFERENCES User(user_name),
    FOREIGN KEY (tour_id) REFERENCES Tour(tour_id)
);


-- Bảng Booking_detail
-- CREATE TABLE Booking_detail (
--     booking_detail_id INT PRIMARY KEY AUTO_INCREMENT,
--     booking_id INT,
--     adult_count INT,
--     child_count INT,
--     FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)
-- );

-- Bảng Site
CREATE TABLE Site (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    content TEXT,
    district VARCHAR(50)
);

-- Bảng Schedule
CREATE TABLE Schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tour_id varchar(20),
    site_id INT,
    tour_guide varchar(100),
    start_time DATETIME,
    end_time DATETIME,
	location varchar(255),
    description text,
    FOREIGN KEY (tour_id) REFERENCES Tour(tour_id),
    FOREIGN KEY (site_id) REFERENCES Site(id)
);

-- Bảng Image_tour
CREATE TABLE Image_site (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    site_id int,
    image_url VARCHAR(255),
    FOREIGN KEY (site_id) REFERENCES Site(id)
);
