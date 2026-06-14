# Lost and Found Portal System

A campus-based **Lost and Found Portal** developed using **Java**, **SQLite**, **HTML**, **CSS**, and **JavaScript**.
This system helps students and staff report lost items and allows others to report found items through a simple web interface.

The goal of this project is to create a centralized platform for managing lost and found items inside the campus.

---

# Features

* Report lost items
* Report found items
* Store item details in SQLite database
* User management system
* Simple and responsive frontend
* JSON export support
* Java backend for handling logic and database operations

---

# Tech Stack

## Frontend

* HTML
* CSS
* JavaScript

## Backend

* Java

## Database

* SQLite

---

# Project Structure

```text
LOST-AND-FOUND-PORTAL/
│
├── frontend/
│   ├── dashboard.html
│   ├── index.html
│   ├── style.css
│   └── data.json
│
├── src/
│   ├── DBConnection.java
│   ├── Item.java
│   ├── ItemDAO.java
│   ├── JsonExporter.java
│   ├── Main.java
│   ├── User.java
│   ├── UserDAO.java
│   ├── Inf.db
│   └── sqlite-jdbc.jar
│
└── README.md
```

---

# How It Works

1. A user reports a lost item on the portal.
2. The information is stored in the SQLite database.
3. If another user finds the item, they can report it through the portal.
4. The system helps connect lost and found reports efficiently.

---

# Database

The project uses **SQLite** for lightweight local database storage.

Database file:

```text
Inf.db
```

SQLite JDBC driver included in the project:

```text
sqlite-jdbc.jar
```

---

# Setup Instructions

## 1. Clone the Repository

```bash
git clone https://github.com/your-username/lost-and-found-portal.git
```

---

## 2. Open the Project

Open the project in any Java IDE such as:

* VS Code
* IntelliJ IDEA
* Eclipse

---

## 3. Run the Backend

Compile and run the Java files:

```bash
javac *.java
java Main
```

---

## 4. Open Frontend

Open:

```text
frontend/index.html
```

in your browser.

---

# Future Improvements

* User authentication system
* Admin dashboard
* Search and filter functionality
* Image upload support
* Email notifications
* Cloud deployment

---

# Learning Outcomes

This project helped us understand:

* Java backend development
* SQLite database integration
* DAO design pattern
* Frontend and backend integration
* JSON handling in Java
* Full-stack project structure

---

# Team Members

* Ravi Kumar
* Sanjay Rawat
- @salonig405-hub
* Shriyansh Negi

---

# License

This project is developed for educational and campus use.
