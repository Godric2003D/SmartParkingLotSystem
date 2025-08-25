# 🚗 Smart Parking Lot System

### A Spring Boot-based system for managing multi-floor parking with automated spot allocation, fee calculation, and real-time availability tracking.

[![Java 17](https://img.shields.io/badge/Java-17-blue.svg?style=flat-square)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot 3.5.4](https://img.shields.io/badge/Spring_Boot-3.5.4-green.svg?style=flat-square)](https://spring.io/projects/spring-boot)
[![H2 Database](https://img.shields.io/badge/Database-H2-lightgrey?style=flat-square)](https://www.h2database.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](https://opensource.org/licenses/MIT)

---

## ✨ Features

- **🚗 Automatic Spot Allocation:** Assigns first available spot based on vehicle type and size.  
- **📝 Vehicle Check-In / Check-Out:** Logs entry and exit times, releases spots when vehicle leaves.  
- **💰 Dynamic Fee Calculation:** Hourly rates — Motorcycle ₹20, Car ₹40, Bus ₹80 (minimum 1 hour).  
- **📊 Real-Time Availability:** Displays free spots per floor and per spot size.  
- **📌 Active Vehicle Tracking:** Lists currently parked vehicles with floor, spot, and check-in time.  

---

## 📦 Tech Stack

- **Backend:** Java 17, Spring Boot 3.5.4  
- **Data:** Spring Data JPA, H2 Database
- **Validation & Security:** Jakarta Bean Validation  
- **Build Tool:** Maven  

---

## ⚡ Getting Started

Follow these steps to run the system locally.

### Prerequisites

- **JDK 17** or higher  
- **Maven**  

### 1. Clone the repository


git clone https://github.com/Godric2003D/SmartParkingLotSystem.git
cd SmartParkingLotSystem

### 2. Build the project
mvn clean install

### 3. Run the application
mvn spring-boot:run


The application will start on http://localhost:8080

### 4. Test APIs via Swagger

Once the application is running, open your browser:

http://localhost:8080/swagger-ui.html


From the Swagger UI, you can test all endpoints, see request/response models, and interact with the APIs directly.
