🐾 PetStore API BDD Automation Framework
Veeva Assignment — API Test Automation for Fresh Graduates A production-grade BDD automation framework built with Java, REST Assured, and Cucumber targeting the Petstore Swagger API.
________________________________________
📌 Git Repository
🔗 https://github.com/divya2-9/PetStore_Automation
________________________________________
👥 Team Members & Roles
Member		Responsibilities
Divya		Project setup, Maven/pom.xml configuration, BaseTest, PetClient, StoreClient, ScenarioContext, TC1 Pet Lifecycle, TC2 Inventory Analysis, Log4J configuration, Git setup and version control
P. M. S.                                                  LASYA PRIYA	UserClient, TC3 User Security & Error Handling, TC4 Cross-Endpoint Data Consistency, Postman   collection, Architecture diagram, README documentation
Both members participated in code reviews, debugging, and final integration testing.
________________________________________
🛠️ Tech Stack
Tool / Library	Version	Purpose
Java	17	Core programming language
REST Assured	5.3.0	HTTP API testing library
Cucumber	7.11.1	BDD framework — Gherkin feature files
JUnit	4.13.2	Test runner integrated with Cucumber
Maven	3.8+	Build tool and dependency management
Log4J 2	2.20.0	Logging to console and rolling file
Jackson	2.15.3	JSON serialization for request bodies
PicoContainer	7.11.1	Dependency injection for ScenarioContext
IntelliJ IDEA	Latest	IDE
GitHub	—	Version control
Postman	Latest	Manual API exploration and collection
________________________________________
📁 Project Structure
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
🚀 Setup & How to Run
Clone the repository
git clone https://github.com/divya2-9/PetStore_Automation.git
cd PetStore_Automation
Install dependencies
mvn clean install -DskipTests
Run all tests
mvn clean test
Run a specific feature file
mvn clean test -Dcucumber.features=src/test/resources/features/PetLifecycle.feature
________________________________________
🧪 Feature Files & Test Cases
TC1 — Pet Lifecycle (CRUD & Chaining)
src/test/resources/features/PetLifecycle.feature
Step	Endpoint	Validation
POST /pet	Create pet with unique name + status "available"	Status 200
GET /pet/{id}	Retrieve using extracted ID	Name and status match
PUT /pet	Update status to "sold"	Status 200
DELETE /pet/{id}	Delete the pet	Status 200
GET /pet/{id}	Pet should not be found	Status 404 or removed
TC2 — Inventory Analysis (Complex Data Parsing)
src/test/resources/features/InventoryAnalysis.feature
Step	Endpoint	Validation
GET /store/inventory	Fetch inventory map	Extract "available" count
GET /pet/findByStatus?status=available	Count returned list	Count matches inventory
TC3 — User Security & Error Handling (Negative Testing)
src/test/resources/features/UserSecurity.feature
Step	Endpoint	Validation
POST /user	Invalid email format	Status 200 (API accepts)
GET /user/nonExistentUser123	Non-existent user	Status 404 + "User not found"
GET /user/login	Wrong credentials	No valid session (API limitation logged)
TC4 — Cross-Endpoint Data Consistency
src/test/resources/features/CrossEndpoint.feature
Step	Endpoint	Validation
POST /pet	Create with category "HighValueBulldog"	Status 200, extract ID
PUT /pet	Update status to "sold"	Status 200
GET /store/inventory	Fetch updated inventory	Status 200
GET /pet/findByStatus?status=sold	Find pet in sold list	Java Stream finds pet ID ✅
________________________________________
📈 Execution Reports
After running mvn clean test reports are generated at:
Report	Location	How to View
Cucumber HTML	target/cucumber-reports/cucumber.html	Open in Chrome
Cucumber JSON	target/cucumber-reports/cucumber.json	CI tool integration
Surefire XML	target/surefire-reports/TEST-cucumber.xml	Maven/Jenkins reports
Log file	target/logs/automation.log	Open in any text editor
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
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
________________________________________
📬 Postman Collection : 
Import files from the /postman folder:
•	PetStore_Collection.json — all 4 assignments
•	PetStore_Environment.json — environment with url variable
Assignment	Method	Endpoint	Test Assertion
A1	GET	{{url}}/pet/findByStatus?status=available	Status code is 200
A2	POST	{{url}}/pet with JSON body	Response contains pet name
A3a	GET	{{url}}/pet/123456	Status 200 (valid pet)
A3b	GET	{{url}}/pet/999999	Status 404 (not found)
A3c	POST	{{url}}/pet empty body	Status 400 or 405
A4	ALL	{{url}}/...	Environment variable url used
Environment variable:
Variable: url
Value:    https://petstore.swagger.io/v2
Usage:    {{url}}/pet/123
________________________________________
⚠️  API Limitations : 
The Petstore API is a public shared demo with no data isolation:
Issue	How Handled
Pet not immediately available after POST	Retry GET up to 3 times with 1.5s wait
Deleted pet may still return 200	Accepts 200 or 404, logs warning
Login returns token for any credentials	Documents API security gap, asserts response structure
Inventory counts fluctuate between calls	Both endpoints queried in same test run
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

