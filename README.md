# Vaccination schedule API
(Spring boot, rest api, maven, postgresql, spring security) 

### Setup:
In postgres add DB with name: "vaccination", owner: "postgres", password: "root"<br>
Or use script:<br>
`CREATE DATABASE vaccination
    WITH 
    OWNER = "postgres"
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;`

Collection requests for Postman - Vaccination Schedule API.json

root request directory: http://localhost:8080/api<br>
for accounts: http://localhost:8080/api/account<br>
for vaccination: http://localhost:8080/api/vaccinations<br>
