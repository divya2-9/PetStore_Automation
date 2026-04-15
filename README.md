🐾 PetStore API BDD Automation Framework
   -Veeva Systems Project
________________________________________
📌 Git Repository : 
🔗 https://github.com/divya2-9/PetStore_Automation
________________________________________
👥 Team Members & Roles	 : 
1. Divya	:	Project setup, Maven/pom.xml configuration, BaseTest, PetClient,
             StoreClient, ScenarioContext, TC1 Pet Lifecycle, TC2 Inventory Analysis, Log4J configuration, Git setup and version control
2. P.Lasya Priya : 	UserClient, TC3 User Security & Error Handling, TC4 Cross-Endpoint Data Consistency, 
            Postman   collection, Architecture diagram, README documentation
Both members participated in code reviews, debugging, and final integration testing.
________________________________________
🛠️ Tech Stack :

1. Java	17	
2. REST Assured	5.3.0	
3. Cucumber	7.11.1	
4. JUnit	4.13.2
5. Maven	3.8+	
6. Log4J 2	2.20.0
7. Jackson	2.15.3	
8. PicoContainer	7.11.1	
9. IntelliJ IDEA	
10. GitHub	
11. Postman
________________________________________
📁 Project Structure:

PetStore_Automation/
│
├── pom.xml                                         # Maven build config & all dependencies
├── README.md                                       # This file
│
└── src/
    └── test/
        ├── java/
        │   ├── clients/
        │   │   ├── BaseTest.java                   # Base URL + shared RequestSpecification
        │   │   ├── PetClient.java                  # All /pet endpoint methods
        │   │   ├── StoreClient.java                # /store/inventory endpoint
        │   │   └── UserClient.java                 # All /user endpoint methods
        │   ├── runners/
        │   │   └── TestRunner.java                 # Cucumber JUnit runner
        │   ├── stepDefinitions/
        │   │   ├── PetSteps.java                   # TC1 + TC4 step definitions
        │   │   ├── InventorySteps.java             # TC2 step definitions
        │   │   └── UserSteps.java                  # TC3 step definitions
        │   └── utils/
        │       └── ScenarioContext.java            # Shared state via PicoContainer
        └── resources/
            ├── log4j2.xml                          # Logging configuration
            └── features/
                ├── PetLifecycle.feature            # TC1
                ├── InventoryAnalysis.feature       # TC2
                ├── UserSecurity.feature            # TC3
                └── CrossEndpoint.feature           # TC4
________________________________________
🏗️ Architecture Diagram
┌──────────────────────────────────────────────────────────────┐
│                   TEST EXECUTION LAYER                       │
│         mvn clean test  ──►  TestRunner.java                 │
└──────────────────────────────┬───────────────────────────────┘
                               reads
                               ▼
┌──────────────────────────────────────────────────────────────┐
│                   GHERKIN / BDD LAYER                        │
│  PetLifecycle.feature      InventoryAnalysis.feature         │
│  UserSecurity.feature      CrossEndpoint.feature             │
└──────────────────────────────┬───────────────────────────────┘
                             mapped via @Given @When @Then
                               ▼
┌──────────────────────────────────────────────────────────────┐
│                STEP DEFINITION LAYER                         │
│   PetSteps.java    InventorySteps.java    UserSteps.java     |
└──────────────────────────────┬───────────────────────────────┘
                             calls
                               ▼
┌──────────────────────────────────────────────────────────────┐
│                  API CLIENT LAYER                            │
│                    (base URL + RequestSpecification)         │
└──────────────────────────────┬───────────────────────────────┘
                         HTTP via REST Assured
                               ▼
┌──────────────────────────────────────────────────────────────┐
│           PETSTORE SWAGGER API                               │
│      https://petstore.swagger.io/v2                          │
│  POST/GET/PUT/DELETE /pet    GET /store/inventory            │
│  GET /pet/findByStatus       POST/GET /user  GET /user/login │
└──────────────────────────────┬───────────────────────────────┘
                            results
                               ▼
┌──────────────────────────────────────────────────────────────┐
│                  REPORTING LAYER                             │               │
└──────────────────────────────────────────────────────────────┘
________________________________________
⚙️ Prerequisites
•	✅ Java 17+ 
•	✅ Maven 3.8+ 
•	✅ Git 
•	✅ IntelliJ IDEA 
________________________________________
🚀 Setup & How to Run : 
1. Clone : git clone https://github.com/divya2-9/PetStore_Automation.git/
2. cd PetStore_Automation/
3. Install dependencies/
4. mvn clean install -DskipTests/
5. Run all tests/
6. mvn clean test/
7. Run a specific feature file/
8. mvn clean test -Dcucumber.features=src/test/resources/features/PetLifecycle.feature/
________________________________________
🧪 Feature Files & Test Cases : 
1. TC1 — Pet Lifecycle (CRUD & Chaining)
   src/test/resources/features/PetLifecycle.feature

2. TC2 — Inventory Analysis (Complex Data Parsing)
   src/test/resources/features/InventoryAnalysis.feature

3. TC3 — User Security & Error Handling (Negative Testing)
   src/test/resources/features/UserSecurity.feature

4. TC4 — Cross-Endpoint Data Consistency
   src/test/resources/features/CrossEndpoint.feature
________________________________________
📈 Execution Reports :
After running "mvn clean test" reports are generated at:
Report	Location ,
How to View : 
1. Cucumber HTML	target/cucumber-reports/cucumber.html	Open in Chrome
2. Cucumber JSON	target/cucumber-reports/cucumber.json	CI tool integration
3. Surefire XML	target/surefire-reports/TEST-cucumber.xml	Maven/Jenkins reports
4. Log file	target/logs/automation.log	Open in any text editor
________________________________________
TESTS
________________________________________
Running runners.TestRunner

Tests run: 7, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
Total time: ~52s
#	Scenario	Status
1	TC4 — Created pet appears in sold list	✅ PASSED
2	TC2 — Inventory count matches findByStatus	✅ PASSED
3	TC1 — BuddyTest pet lifecycle (CRUD)	✅ PASSED
4	TC1 — MaxTest pet lifecycle (CRUD)	✅ PASSED
5	TC3 — Create user with invalid email	✅ PASSED
6	TC3 — Fetch non-existent user (404)	✅ PASSED
7	TC3 — Login with wrong credentials	✅ PASSED
<img width="1815" height="812" alt="image" src="https://github.com/user-attachments/assets/2e810039-0d72-4eba-b7f9-38453fc8a066" />

________________________________________
📬 Postman Collection : 
Import files from the /postman folder:
•	PetStore_Collection.json — all 4 assignments
•	PetStore_Environment.json — environment with url variable

Environment variable:
Variable: url
Value:    https://petstore.swagger.io/v2
Usage:    {{url}}/pet/123
________________________________________
⚠️  API Limitations : 
1. The Petstore API is a public shared demo with no data isolation:
   How Issues are Handled,
2. Pet not immediately available after POST	Retry GET up to 3 times with 1.5s wait,
3. Deleted pet may still return 200	Accepts 200 or 404, logs warning
   Login returns token for any credentials	Documents API security gap, asserts response structure,
   Inventory counts fluctuate between calls	Both endpoints queried in same test run.
________________________________________
🔧 Key Design Decisions : 
•	Client Pattern — API logic in *Client.java, completely separate from step logic
•	ScenarioContext — PicoContainer injects shared state across step classes
•	Random 6-digit IDs — avoids Long.MAX_VALUE overflow on public Petstore API
•	Data-driven testing — all values in Examples: tables, zero hardcoding in Java
•	Retry mechanism — handles eventual consistency of public API gracefully
________________________________________
📝 API Reference : 
•	Swagger UI: https://petstore.swagger.io/
•	Base URL: https://petstore.swagger.io/v2
__________________________________________
👨‍💻 Author : 
Divya Undapalli
__________________________________________
📌 Future Enhancements : 
🔹 Add Logging (Log4j / SLF4J)
🔹 Implement Hooks (Before/After)
🔹 CI/CD Integration (GitHub Actions / GitLab CI)
__________________________________________
⭐ Conclusion : 
This framework demonstrates a scalable, maintainable, and real-world API automation approach using BDD principles,
along with handling real-world challenges like API inconsistency and dynamic data validation.

