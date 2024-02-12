# sample_microservice

# postgres-setup
1. **Open a terminal** and **connect to postgres DB** by the below commands.     
     ```sh 
   sudo -i -u postgres;       
   ```   
     ![Screenshot from 2024-02-05 12-58-42.png](..%2F..%2F..%2FPictures%2FScreenshot%20from%202024-02-05%2012-58-42.png)
 

2. **Create a database PizzaHutDB.**        
    ```sh 
   CREATE DATABASE PizzaHutDB;       
   ```      
   ![Screenshot from 2024-02-05 13-00-34.png](..%2F..%2F..%2FPictures%2FScreenshot%20from%202024-02-05%2013-00-34.png)


3. **Connect to PizzaHutDB** and **Create a table UserInfo.**
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
   ![Screenshot from 2024-02-05 13-10-02.png](..%2F..%2F..%2FPictures%2FScreenshot%20from%202024-02-05%2013-10-02.png)
  

4. In **resources/postgres.conf** file **_change the username and password_** (Enter your username and password)
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
   

5. **Now Run** the **UserAPI scala class** it will generate the userAPI to create, read, update, delete the users.    
   Below are the **list of user API.**
    - **create user:** http://localhost:8081/api/create-user
    - **get all users:** http://localhost:8081/api/get-all-users
    - **get user by id:** http://localhost:8081/api/get-user-by-id/101 **_(Replace 101 by the userID)_**
    - **delete user:** http://localhost:8081/api/delete-user-by-id/101 **_(Replace 101 by the userID)_**
    - **delete all users:** http://localhost:8081/api/delete-all-users 
    - **update username:** http://localhost:8081/api/update-user-name/101/Manish **_(Replace 101 by the userID & Manish with UserName)_**
    - **update user fields(multiple fields):** http://localhost:8081/api/update-user-fields


6. **Open Postman** and select your **choice(post, get, delete, patch)** and **enter the above url** as per your choice and **hit send button.**  
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
   ![Screenshot from 2024-02-05 19-47-44.png](..%2F..%2F..%2FPictures%2FScreenshot%20from%202024-02-05%2019-47-44.png)