# Briis Scrabble
A web-based Scrabble scoring and validation tool that checks if a word is valid and calculates its Scrabble score. Users can also add new words to the dictionary.

## Prerequisites
- Java 17 or later
- Node.js and npm (for the frontend)
- A package manager like Gradle
- A database (e.g., MySQL, PostgreSQL, or H2 for testing)

## Installation

1. #### Clone the repository:
   ```bash
   git clone https://github.com/palllaura/briis-scrabble.git
   cd briis-scrabble

2. #### Configure the database:
- Open **src/main/resources/application.properties**.
- Update the database settings (e.g., URL, username, password) to match your environment.

3. #### Run the backend:
- Build and start the Spring Boot application:
   ```bash
   ./gradlew bootRun

### Frontend Setup
1. #### Navigate to the frontend directory:
   ```bash
   cd frontend
2. #### Install dependencies:
   ```bash
   npm install
3. #### Start the development server:
   ```bash
   npm run serve
