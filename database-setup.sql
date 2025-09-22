-- Voyanta Database Setup Script
-- Run this script to create the database and initial data

-- Create database
CREATE DATABASE IF NOT EXISTS voyanta_db;
USE voyanta_db;

-- The application will automatically create tables using JPA/Hibernate
-- This script is for reference and initial data setup

-- Insert sample vehicles (run after application starts and creates tables)
INSERT INTO vehicles (make, model, type, license_plate, year, color, daily_rate, hourly_rate, status, description, created_at, updated_at) VALUES
('Toyota', 'Camry', 'SEDAN', 'ABC123', '2023', 'Silver', 75.00, 12.50, 'AVAILABLE', 'Comfortable sedan perfect for city driving', NOW(), NOW()),
('Honda', 'CR-V', 'SUV', 'DEF456', '2023', 'Black', 95.00, 15.00, 'AVAILABLE', 'Spacious SUV ideal for family trips', NOW(), NOW()),
('BMW', '3 Series', 'SEDAN', 'GHI789', '2023', 'White', 120.00, 20.00, 'AVAILABLE', 'Luxury sedan with premium features', NOW(), NOW()),
('Ford', 'F-150', 'TRUCK', 'JKL012', '2023', 'Red', 110.00, 18.00, 'AVAILABLE', 'Powerful truck for heavy-duty tasks', NOW(), NOW()),
('Nissan', 'Altima', 'SEDAN', 'MNO345', '2022', 'Blue', 70.00, 11.50, 'AVAILABLE', 'Reliable sedan with great fuel efficiency', NOW(), NOW()),
('Chevrolet', 'Tahoe', 'SUV', 'PQR678', '2023', 'Gray', 130.00, 22.00, 'AVAILABLE', 'Large SUV with seating for 8 passengers', NOW(), NOW()),
('Audi', 'A4', 'SEDAN', 'STU901', '2023', 'Black', 125.00, 21.00, 'AVAILABLE', 'Premium sedan with advanced technology', NOW(), NOW()),
('Jeep', 'Wrangler', 'SUV', 'VWX234', '2023', 'Green', 100.00, 16.50, 'AVAILABLE', 'Off-road capable SUV for adventure', NOW(), NOW());

-- Create a default admin user (password: admin123)
-- Note: The password should be hashed using BCrypt in a real application
-- This is just for demonstration purposes
INSERT INTO users (username, email, password, first_name, last_name, role, active, created_at, updated_at) VALUES
('admin', 'admin@voyanta.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Admin', 'User', 'ADMIN', true, NOW(), NOW());

-- Create sample staff user (password: staff123)
INSERT INTO users (username, email, password, first_name, last_name, role, active, created_at, updated_at) VALUES
('staff', 'staff@voyanta.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Staff', 'Member', 'STAFF', true, NOW(), NOW());

-- Create sample client user (password: client123)
INSERT INTO users (username, email, password, first_name, last_name, phone_number, role, active, created_at, updated_at) VALUES
('client', 'client@voyanta.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'John', 'Doe', '555-0123', 'CLIENT', true, NOW(), NOW());

-- Note: The above passwords are hashed using BCrypt with the following plain text passwords:
-- admin123, staff123, client123
-- In a production environment, you should use stronger passwords and proper password policies
