# Citronix Project

## Project Description  
The Citronix project is a farm management application designed to help lemon farmers track production, harvest, and sales. It aims to streamline the management of farms, fields, trees, harvests, and sales while optimizing tree productivity based on their age.

---

## General Objective  
To provide an efficient and user-friendly solution for lemon farmers to manage their operations effectively, from planting trees to selling harvested lemons.

---

## Technologies Used  
- **Spring Boot**: For building the REST API.  
- **Java**: The core programming language.  
- **Lombok & Builder Pattern**: Simplifies entity management.  
- **MapStruct**: For entity-to-DTO conversion.  
- **JUnit & Mockito**: For unit testing.  
- **Postman/Swagger**: For API testing and documentation.  

---

## Project Structure  
The project follows a layered architecture:  
1. **Controller Layer**: Handles API requests.  
2. **Service Layer**: Contains business logic.  
3. **Repository Layer**: Interfaces with the database.  
4. **Entity Layer**: Represents the database tables.

---

## Brief Architectural Description  
The architecture is designed to separate concerns, ensuring scalability and maintainability. Centralized exception handling, data validation with annotations, and the use of interfaces provide a robust and clear structure.

---

## Installation and Usage Instructions  

### Prerequisites  
- Java 8
- Maven  
- PostgreSQL  

---

### Database Setup  
1. Create a PostgreSQL database named `citronix`.  
2. Use the SQL scripts provided in the repository to set up tables and seed initial data.  
3. Update the `application.properties` file with your PostgreSQL credentials.  

---

### Steps to Run the Application  
1. Clone the repository:  
   ```bash
   git clone [https://github.com/username/citronix.git](https://github.com/JavaAura/Benfill_S3_B4_Citronix)
   cd citronix
   ```
2. Build the application:  
   ```bash
   mvn clean install
   ```
3. Run the application:  
   ```bash
   java -jar target/citronix.jar
   ```
4. Test the API using Postman or Swagger documentation.

---

## Future Improvements  
- **Enhanced Analytics**: Integrate dashboards to display production and sales trends.  
- **Mobile App**: Develop a mobile-friendly version for farmers.  
- **Integration with IoT**: Monitor tree conditions using IoT devices.  

---

## Ideas for Extensions  
- Introduce machine learning to predict optimal harvest times.  
- Expand the application to support other crops besides lemons.  
- Add support for multi-language interfaces.

---

## Author and Contact  
Developed by **Anass Benfillous**.  
For inquiries, please contact [benfianass@gmail.com](mailto:benfianass@gmail.com).  
