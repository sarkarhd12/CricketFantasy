Cricket Predictor API
Overview

The Cricket Predictor API is designed to provide robust functionality for searching cricket players, filtering based on attributes, creating fantasy teams, and determining the winner of a game based on provided team data. The API supports advanced querying, including search by player name, role, and country, as well as filtering players based on custom criteria.

This application leverages Java Spring Boot to handle RESTful operations and efficiently manage paginated data requests. It allows for easy integration with frontend platforms via simple HTTP requests, making it highly flexible for building cricket-based applications like fantasy leagues, player statistics, and game predictions.
Features

    Search Players: Search cricket players by name, country, or role with pagination support.
    Filter Players: Filter players based on custom attributes (like stats) and roles.
    Create Fantasy Teams: Automatically generate fantasy teams based on country names.
    Determine Game Winner: Input two teams and predict the game winner using a custom algorithm.

Endpoints
1. Player Search

    Endpoint: /search
    Method: GET
    Parameters: playerName, countryName, role, page, size
    Description: Retrieves a paginated list of players based on name, country, or role.

2. Player Filtering

    Endpoint: /filter
    Method: GET
    Parameters: filterBy, value, role
    Description: Filter players based on specific attributes like average or strike rate.

3. Get All Players

    Endpoint: /getall
    Method: GET
    Parameters: page, size
    Description: Returns a paginated list of all players in the database.

4. Find Player By Name

    Endpoint: /find
    Method: GET
    Parameters: name
    Description: Searches for a player by their name.

5. Get Fantasy Team

    Endpoint: /team
    Method: GET
    Parameters: countryName
    Description: Generates a fantasy cricket team based on the selected country.

6. Determine Game Winner

    Endpoint: /determineWinner
    Method: POST
    Body: JSON containing two teams (team1, team2)
    Description: Predicts the winner between two teams based on their player data.

Technologies Used

    Java 17: For the backend logic and API implementation.
    Spring Boot: To create the RESTful web services.
    Spring Data JPA: To manage database access and paging of results.
    Spring Cross-Origin Support: Allows seamless integration with frontends like React.
    Maven: For dependency management and project building.

How to Run

    Clone the repository:

    bash

git clone https://github.com/sarkarhd12/CricketFantasy.git

Navigate to the project directory:

bash

cd cricket-predictor

Build the project using Maven:

bash

mvn clean install

Run the application:

bash

mvn spring-boot:run

Access the API at http://localhost:8080.