

Cricket Fantasy App - v1

This is the first version of the Cricket Fantasy app, a Spring Boot application that helps users build their own fantasy cricket teams. The app allows users to find player information, retrieve all available players, and generate a fantasy team based on a selected country.
Table of Contents

    Features
    Technologies Used
    Installation
    Usage
    API Endpoints
    Project Structure
    Contributing
    License

Features

    Search for cricket players by name.
    Retrieve a paginated list of all available players.
    Generate a fantasy team based on players from a specific country.

Technologies Used

    Java: The core programming language.
    Spring Boot: For building the RESTful API.
    Maven: For project management and build automation.

Installation

    Clone the repository:

    bash

git clone https://github.com/sarkarhd12/CricketFantasy.git
cd cricket-fantasy-app

Build the project using Maven:

bash

mvn clean install

Run the application:

bash

    mvn spring-boot:run

The application will start running at http://localhost:8080.
Usage

You can interact with the app using the API endpoints listed below. You can use tools like Postman or Curl to test the API.
API Endpoints
1. Search for Players by Name

    URL: /find
    Method: GET
    Description: Retrieves a list of players matching the given name.
    Parameters:
        name (required): The name of the player to search for.
    Example:

    arduino

    GET /find?name=Virat

2. Get All Players

    URL: /getall
    Method: GET
    Description: Retrieves a paginated list of all players.
    Parameters: Standard pagination parameters like page, size, etc.
    Example:

    arduino

    GET /getall?page=0&size=10

3. Generate a Fantasy Team

    URL: /team
    Method: GET
    Description: Generates a fantasy team based on the players from a given country.
    Parameters:
        countryName (required): The name of the country.
    Example:

    bash

GET /team?countryName=India