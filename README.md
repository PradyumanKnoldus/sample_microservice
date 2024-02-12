# sample_microservice

# postgres-setup
1. **Open a terminal** and **connect to postgres DB** by the below commands.     
     ```sh 
   sudo -i -u postgres;       
   ```         
   ![Screenshot from 2024-02-12 17-57-24](https://github.com/PradyumanKnoldus/sample_microservice/assets/124979629/c7274a2c-5c73-4590-9066-fdf6269cc965)
        

3. **Create a database PizzaHutDB.**        
    ```sh 
   CREATE DATABASE PizzaHutDB;       
   ```        
   ![Screenshot from 2024-02-12 17-57-40](https://github.com/PradyumanKnoldus/sample_microservice/assets/124979629/8cbbd882-9950-48e0-a717-a079956e29e9)
         


4. **Connect to PizzaHutDB** and **Create a table UserInfo.**
    ```sh 
   \c PizzaHutDB 
   ```
    ```sh 
    CREATE TABLE UserInfo (
      userId INT PRIMARY KEY,
      userName VARCHAR(30),
      password VARCHAR(30),
      firstName VARCHAR(20),
      lastName VARCHAR(20),
      gender VARCHAR(10),
      dob VARCHAR(15),
      email VARCHAR(50) NOT NULL,
      phoneNumber BIGINT,
      address VARCHAR(40),
      city VARCHAR(20),
      state VARCHAR(20),
      pincode BIGINT,
      country VARCHAR(50)
    );
    ```             
    ![Screenshot from 2024-02-12 17-57-49](https://github.com/PradyumanKnoldus/sample_microservice/assets/124979629/8dab4ec1-39be-43ed-acf0-6de87b5fe5eb)

         

5. In **resources/postgres.conf** file **_change the username and password_** (Enter your username and password)
   ```sh
       postgres {
       url = "jdbc:postgresql://localhost:5432/pizzahutdb"
       host = "localhost"
       port = 5432
       name = "pizzahutdb"
       username = "postgres"  
       password = "manish12345"
    }
   ```
   

6. **Now Run** the **UserAPI scala class** it will generate the userAPI to create, read, update, delete the users.    
   Below are the **list of user API.**
    - **create user:** http://localhost:8081/api/create-user
    - **get all users:** http://localhost:8081/api/get-all-users
    - **get user by id:** http://localhost:8081/api/get-user-by-id/101 **_(Replace 101 by the userID)_**
    - **delete user:** http://localhost:8081/api/delete-user-by-id/101 **_(Replace 101 by the userID)_**
    - **delete all users:** http://localhost:8081/api/delete-all-users 
    - **update username:** http://localhost:8081/api/update-user-name/101/Manish **_(Replace 101 by the userID & Manish with UserName)_**
    - **update user fields(multiple fields):** http://localhost:8081/api/update-user-fields


7. **Open Postman** and select your **choice(post, get, delete, patch)** and **enter the above url** as per your choice and **hit send button.**  
    **Sample Data to Create User:**
    ```sh
   { 
      "userId":101,
      "userName": "Manish01",
      "password":"122233@Ram",
      "firstName": "Manish",
      "lastName": "Mishra",
      "gender": "Male",
      "dob": "14/03/2001",
      "email": "manish.mishra1@nashtechglobal.com",
      "phoneNumber": 9898787876,
      "address": "Near Ram-eesh International",
      "city": "Greater Noida",
      "state": "Uttar Pradesh",
      "pinCode": 201306,
      "country": "INDIA"
   }
   ```
   **Example:**               
   ![Screenshot from 2024-02-12 17-58-00](https://github.com/PradyumanKnoldus/sample_microservice/assets/124979629/7032b984-5b6e-4c4c-ac5e-e293bf2db985)


8. **You can also import the Postman directory to the Postman Application & From the top-right select User Local Environment from Environment option**. It contains all the API that is written above. You have to only change the data as per your need.
