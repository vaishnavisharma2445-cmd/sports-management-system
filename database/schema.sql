-- =============================================
-- Database Schema: Sports Management System
-- Database: sports_db
-- =============================================

-- Create the database if it doesn't already exist
CREATE DATABASE IF NOT EXISTS sports_db;
USE sports_db;

-- ---------------------------------------------
-- 1. Table: teams
-- ---------------------------------------------
CREATE TABLE IF NOT EXISTS teams (
    id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(100) NOT NULL,
    coach_name VARCHAR(100) NOT NULL,
    sport VARCHAR(50) NOT NULL
);

-- ---------------------------------------------
-- 2. Table: players
-- ---------------------------------------------
CREATE TABLE IF NOT EXISTS players (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    sport VARCHAR(50) NOT NULL,
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE SET NULL
);

-- ---------------------------------------------
-- 3. Table: matches
-- ---------------------------------------------
CREATE TABLE IF NOT EXISTS matches (
    id INT PRIMARY KEY AUTO_INCREMENT,
    team1_id INT NOT NULL,
    team2_id INT NOT NULL,
    match_date DATE NOT NULL,
    venue VARCHAR(100) NOT NULL,
    winner_id INT,
    FOREIGN KEY (team1_id) REFERENCES teams(id) ON DELETE CASCADE,
    FOREIGN KEY (team2_id) REFERENCES teams(id) ON DELETE CASCADE,
    FOREIGN KEY (winner_id) REFERENCES teams(id) ON DELETE SET NULL
);

-- =============================================
-- Dummy Data Insertion (5 rows each with Indian names)
-- =============================================

-- Insert 5 Teams
INSERT INTO teams (team_name, coach_name, sport) VALUES
('Mumbai Warriors', 'Rahul Dravid', 'Cricket'),
('Delhi Dynamos', 'Sourav Ganguly', 'Cricket'),
('Bengaluru Bulls', 'Anil Kumble', 'Kabaddi'),
('Goa Challengers', 'Bhaichung Bhutia', 'Football'),
('Chennai Smashers', 'Pullela Gopichand', 'Badminton');

-- Insert 5 Players
INSERT INTO players (name, age, sport, team_id) VALUES
('Rohit Sharma', 36, 'Cricket', 1),
('Virat Kohli', 35, 'Cricket', 2),
('Pardeep Narwal', 27, 'Kabaddi', 3),
('Sunil Chhetri', 39, 'Football', 4),
('PV Sindhu', 29, 'Badminton', 5);

-- Insert 5 Matches
INSERT INTO matches (team1_id, team2_id, match_date, venue, winner_id) VALUES
(1, 2, '2026-03-15', 'Wankhede Stadium, Mumbai', 1),
(2, 1, '2026-03-22', 'Arun Jaitley Stadium, Delhi', 2),
(3, 1, '2026-04-05', 'Kanteerava Stadium, Bengaluru', 3),
(4, 2, '2026-04-12', 'Fatorda Stadium, Goa', 4),
(5, 3, '2026-04-20', 'Jawaharlal Nehru Stadium, Chennai', 5);
