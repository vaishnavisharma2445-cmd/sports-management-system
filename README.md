# Sports Management System

A console-based Java application to manage players, teams, matches and results, using MySQL for data storage.

**Course:** Programming in Java (25CSE0202)

---

## Problem Statement

Schools, colleges and local sports clubs often manage players, teams and match records on paper or in scattered Excel sheets. This causes duplicate entries, lost records, and difficulty in finding information such as which players belong to which team or which team won a match.

**Sports Management System** solves this by giving one simple application where all sports data is stored in a database and can be added, viewed, searched, updated and deleted easily.

**Intended users:** Sports coordinators, coaches and club administrators.

---

## Objectives

1. Store player, team and match details in a MySQL database.
2. Provide full CRUD (Create, Read, Update, Delete) operations for each module.
3. Link players to teams and record match results (winner).
4. Handle invalid inputs safely without crashing the program.
5. Apply core Java concepts: OOP, Collections, Exception Handling and JDBC.

---

## Scope

**In scope:**
- Player Management
- Team Management (including viewing players of a team)
- Match Scheduling and Result Recording
- Console-based menu system
- MySQL database with sample data

**Out of scope:**
- Graphical user interface (GUI) or web interface
- User login and authentication
- Online payments or ticket booking

---

## Features

| Module | Features |
|---|---|
| Player Management | Add, View All, Search by ID, Update, Delete players |
| Team Management | Add, View All, Search by ID, View Players of a Team, Update, Delete teams |
| Match & Results | Add Match, View All, Search by ID, Update Details, Record Result (Winner), Delete |

---

## Technologies Used

| Technology | Purpose |
|---|---|
| Java (JDK 17+) | Programming language |
| MySQL 8.0 | Database |
| JDBC (MySQL Connector/J) | Connecting Java to MySQL |
| NetBeans / Eclipse | IDE |
| Git and GitHub | Version control and team collaboration |

---

## Java Concepts Used

| Concept | Where it is used |
|---|---|
| OOP (Encapsulation) | `Player`, `Team`, `Match` classes with private fields, getters and setters |
| Collections | `ArrayList` to store and display lists of players, teams and matches |
| Exception Handling | `try-catch` for invalid input (`NumberFormatException`) and database errors (`SQLException`) |
| JDBC | `DBConnection` class and DAO classes using `PreparedStatement` for all SQL queries |
| Packages | Code organised into `app`, `db`, `player`, `team`, `match` |

---

## Project Structure

```
sports-management-system/
├── database/
│   └── schema.sql            Creates database, tables and sample data
├── lib/
│   └── mysql-connector-j-26.10.0.jar
└── src/
    ├── app/
    │   └── Main.java         Main menu (entry point)
    ├── db/
    │   └── DBConnection.java MySQL connection
    ├── player/
    │   ├── Player.java       Player model
    │   ├── PlayerDAO.java    Player database operations (CRUD)
    │   └── PlayerMenu.java   Player menu
    ├── team/
    │   ├── Team.java
    │   ├── TeamDAO.java
    │   └── TeamMenu.java
    └── match/
        ├── Match.java
        ├── MatchDAO.java
        └── MatchMenu.java
```

Each module follows the same design: **Model class → DAO class (database) → Menu class (user input)**.

---

## Database Design

Database name: `sports_db`

| Table | Columns |
|---|---|
| `teams` | id, team_name, coach_name, sport |
| `players` | id, name, age, sport, team_id (references teams.id) |
| `matches` | id, team1_id, team2_id, match_date, venue, winner_id (references teams.id) |

Each table contains 5 rows of sample data.

---

## Setup Instructions

### 1. Requirements
- Java JDK 17 or above
- MySQL 8.0
- NetBeans or Eclipse
- Git

### 2. Clone the Project
```
git clone https://github.com/vaishnavisharma2445-cmd/sports-management-system.git
cd sports-management-system
```

### 3. Set Up the Database
Open MySQL Command Line Client and run:
```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root123';
source C:/Users/<your-username>/Projects/sports-management-system/database/schema.sql
```
Note: The application connects using username `root` and password `root123` (see `src/db/DBConnection.java`).

### 4. Open in IDE
- **NetBeans:** File → Open Project → select the project folder.
- **Eclipse:** File → New → Java Project → untick "Use default location" → select the project folder → Finish. Then right-click `lib/mysql-connector-j-26.10.0.jar` → Build Path → Add to Build Path.

### 5. Run
Run `src/app/Main.java`.

---

## Sample Output

```
==================================
    SPORTS MANAGEMENT SYSTEM
==================================
1. Player Management
2. Team Management
3. Match & Results
4. Exit
Enter your choice (1-4): 1

------ PLAYER MANAGEMENT ------
1. Add Player
2. View All Players
3. Search Player by ID
4. Update Player
5. Delete Player
6. Back to Main Menu
Enter your choice (1-6): 2

ID    Name                 Age   Sport        Team
-----------------------------------------------------
1     Rohit Sharma         36    Cricket      1
2     Virat Kohli          35    Cricket      2
3     Pardeep Narwal       27    Kabaddi      3
4     Sunil Chhetri        39    Football     4
5     PV Sindhu            29    Badminton    5
```

---

## Git Workflow

Each module was developed in a separate branch and merged into `main` through a Pull Request.

| Branch | Purpose |
|---|---|
| `main` | Final, combined project |
| `feature/player-module` | Player Management |
| `feature/team-module` | Team Management |
| `feature/match-module` | Match & Results |
| `docs/readme` | Project documentation |

---

## Team Contribution

| Member | Work |
|---|---|
| Vaishnavi Sharma | Repository setup, base project (menu, DB connection, database schema), Player Management module, Team Management module, reviewing and merging Pull Requests |
| Nikhal | Match & Results module |
| Gurprem | Project documentation (README) |

---

## Milestones

| Date | Milestone | Status |
|---|---|---|
| 21 Nov 2026 | GitHub repository and branches created | Completed |
| 21 Nov 2026 | Base project, database schema and MySQL connection | Completed |
| 22 Nov 2026 | Player Management module (PR #1) | Completed |
| 22 Nov 2026 | Team Management module (PR #2) | Completed |
| 22 Nov 2026 | Match & Results module (PR #3) | Completed |
| 23 Nov 2026 | README documentation | Completed |

---

## Current Status

All three modules are complete, connected to MySQL, and merged into the `main` branch. The application runs fully from the console.

---

## Future Scope

- Display team names instead of team IDs in the match list
- Points table / leaderboard based on match results
- User login for admin and coach roles
- Graphical user interface using Java Swing
