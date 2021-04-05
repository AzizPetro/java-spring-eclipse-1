# JavaSpringRestController

The aim of this project is to provide service which handles Rest requests and communication with PostGreSql database.

## SETUP
- Java
- Spring Boot
- PostGreSql
- Maven 
- Valentina Studio

## Prerequisite

Table "players" needed to be created. Valentina Studio can be used for table creation.

CREATE TABLE players (
 playername VARCHAR(40) NOT NULL PRIMARY KEY,
 role VARCHAR(40),
 nationality VARCHAR(40),
 teamid INTEGER,
 teamname VARCHAR(40)) should be used for table creation.


## Usage

Client can sent RESTApi requests in JSON format in order to add new player with constraints, delete teams' and its players.

GET localhost:5000 and GET localhost:5000/teams (Response will return team names in JSON format)

GET localhost:5000/teams/{id} (Response will return players' of certain team)

POST localhost:5000/teams/add (RequestBody example : { "playername" : "Zafer Tav", "role" : "gk", "nationality" : "tr", "teamid" : "0", "teamname" : "Galatasaray"}
Constraints : Teams can't have more than 6 foreign player ,2 goalkeepers and 18 players.

DELETE localhost:5000/teams/{id} (Deletes teams' player from database)

Notes: Team Id can be UUID. 


#### Ports
| Service 	| Port 	|
|-	|-	|
| API Gateway 	| 5000 	|
| PostGreSql database 	| 8080 	|

Database username: "postgres"
Database password: " "
