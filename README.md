🗳️ **Online Voting System – Spring Boot**

A RESTful Online Voting System built using Spring Boot, Spring Data JPA, and Hibernate.
This project models a real-world election system where voters can register, vote only once, candidates receive votes, and election results are declared in a consistent and secure manner.

--------------------------------------

🚀 **Features:**

✅ Voter registration with duplicate email prevention

✅ Candidate management (add, update, delete)

✅ Secure voting (one voter → one vote only)

✅ Vote counting with transactional safety

✅ Election result declaration (winner + total votes)

✅ Clean REST APIs with proper HTTP status codes

✅ Global exception handling & validation

✅ Well-structured layered architecture

--------------------------------------

🏗️ **Architecture Overview**

The application follows a layered Spring Boot architecture:

Controller → Service → Repository → Database

**Layers:**

1) Controller Layer
   * Handles HTTP requests & responses (REST APIs)

2) Service Layer
   * Contains business logic (voting rules, validations, result computation)

3) Repository Layer
   * Manages database operations using Spring Data JPA

4) Model Layer (Entities)
   * Represents database tables and relationships

5) Exception Layer
   * Centralized exception handling using @ControllerAdvice

--------------------------------------

🧩 **Domain Model & Relationships**
**Core Entities**

1) Voter
    * Registers with name & email
    * Can vote only once

2) Candidate
    * Represents an election contestant
    * Maintains vote count

3) Vote
    * Connects one Voter to one Candidate
    * Enforces one-vote-per-voter rule

4) ElectionResult
    * Stores final election outcome
    * Winner + total votes

Entity Relationships
Voter (1) ── (1) Vote (N) ── (1) Candidate
                         |
                   ElectionResult
                         |
                      Winner

--------------------------------------

🔐 **Business Rules Enforced**

1) A voter cannot vote more than once

2) A voter must exist before voting

3) A candidate must exist before receiving votes

4) Vote count updates are transaction-safe

5) Election results are declared only once per election name

--------------------------------------

📡 **REST API Endpoints**
🧑‍💼 **Voter APIs**

POST	 --    /api/voter/register	   --         Register a new voter

GET	  --     /api/voter/allVoters	   --           Get all voters

GET	 --     /api/voter/{id}	       --             Get voter by ID

PUT	 --     /api/voter/updateVoter/{id}	   --     Update voter

DELETE	--  /api/voter/deleteVoter/{id}	  --      Delete voter

--------------------------------------

🧑‍💼 **Candidate APIs**

POST	 --     /api/candidate/addCandidate	   --       Add candidate

GET	 --     /api/candidate/getAllCandidates	   --     Get all candidates

GET	 --     /api/candidate/getCandidate/{id}	--      Get candidate by ID

PUT	  --    /api/candidate/updateCandidate/{id}	 --   Update candidate

DELETE --	  /api/candidate/deleteCandidate/{id}	 --   Delete candidate

--------------------------------------

🗳️ **Voting APIs**

POST	--    /api/voting/castVote	--  Cast a vote

GET	 --     /api/voting/allVotes	--  Get all votes

Sample Request

{
  "voterId": 1,
  "candidateId": 2
}

--------------------------------------

🏆 **Election Result APIs**

POST	--    /api/election-result/declare	    --      Declare election result

GET	 --   /api/election-result/allElectionResult	--  Get all results

Sample Request

{
  "electionName": "2025-general-election"
}

--------------------------------------

⚠️ **Global Exception Handling**

The project uses @ControllerAdvice for centralized exception handling:

1) ResourceNotFoundException → 404 NOT FOUND

2) DuplicateResourceException → 409 CONFLICT

3) VoteNotAllowedException → 403 FORBIDDEN

4) Validation errors (@Valid) → 400 BAD REQUEST

5) Generic exceptions → 500 INTERNAL SERVER ERROR

6) All errors return clean, consistent JSON responses.

--------------------------------------

🛠️ **Tech Stack**

1) Java

2) Spring Boot

3) Spring Data JPA

4) Hibernate

5) REST APIs

6) Jakarta Validation

7) PostgreSQL (configurable)

--------------------------------------

▶️ **How to Run**

1) Clone the repository

  * git clone https://github.com/hokteyash/voting-spring-boot-app.git

2) Open in IDE (IntelliJ / Eclipse)

3) Configure application.properties

4) Run the application

5) Test APIs using Postman.

--------------------------------------

📌 **Future Enhancements**

🔐 Spring Security (JWT + Roles: ADMIN / VOTER)

📊 Pagination & sorting

🧪 Microservices

🌐 Frontend integration (React / Angular)

🗂️ Multiple election support

--------------------------------------


👨‍💻 **Author**

* Yash Hokte
* Full Stack Developer (MERN + Spring Boot)
📌 Built as a backend-focused, real-world Spring Boot project
