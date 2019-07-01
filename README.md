# TACS-2019-GRUPO3
Repositorio del TP cuatrimestral de TACS UTN 1C-2019

#### Administrador
username: JPerez1
,password: 123

### Proyecto backend

#### Precondiciones
Pull imagen de MongoDB
```
docker pull mongo:4.0.4
```
Para ejecutar la api en local y ejecutar tests
```
docker run -d -p 27017-27019:27017-27019 --name grupo3-mongodb mongo:4.0.4
docker run -d -p 27027-27029:27017-27019 --name grupo3-mongodb-test mongo:4.0.4
```
Si los containers están apagados
```
docker start grupo3-mongodb
docker start grupo3-mongodb-test
```

#### Install

Desde el root del proyecto

```bash
    cd grupo3-backend
    mvn install
```

#### Build

Desde el root del proyecto
```
    cd grupo3-backend/grupo3-spring-controller
    mvn compile
```

#### Run

Desde el root del proyecto
```
    cd grupo3-backend/grupo3-spring-controller
    mvn spring-boot:run
```

#### URL base
```
    http://localhost:8080
```

## Endpoints

| Method | URI | Descripción | Observación | 
| ------ | ------ | ------ | ------ |
| GET | /users | Todos los usuarios | 
| POST | /sign-up | Crear un usuario | body=JSON
| POST | /login | login a un usuario | body=JSON
| GET | /places/near | Lugares cercanos a una coordenada | parameter=coordinates
| POST | /users/{user-id}/list-of-places{list-id}/{place-id} |Registrar un lugar en una lista de lugares | place-id es el id de foursquare
| POST | /users/{user-id}/list-of-places/{list-id} | crear una listas de lugares | 
| GET | /users/{user-id}/list-of-places | Listas de lugares favoritos | 
| GET | /users/{user-id}/list-of-places/{list-id} | buscar una lista de lugares | 
| DELETE | /users/{user-id}/list-of-places/{list-id} | Eliminar una lista de lugares | 
| PUT | /users/{user-id}/list-of-places/{list-id} | Modificar una lista de lugares | parameter=new-name
| PUT | /users/{user-id}/{list-id}/places-visited/{place-id} | Marcar un lugar como visitado | user-id = username, list-id = nombre de lista, place-id = id de foursquare
| GET | /administrator/users/{user-id} | Ver Datos de un usuario | 
| GET | /administrator/places/{place-id}/interested-users | Nombres de usuario interesados en un lugar | place-id es el id de foursquare
| GET | /administrator/places/places-in-common | Obtener lugares en comun entre usuarios | parameters: list-id-1, user-id-1, list-id-2, user-id-2
| GET | /administrator/places/registered-places | Consultar lugares registrados en el sistema | days: hoy, últimos 3 días. última semana, último mes, todos (days=0)
| GET | /places | Todos los lugares | 

##
### Proyecto Telegram

#### Install

```
    cd grupo3-telegram-bot
    mvn install
```

#### Build

```    
    mvn compile
```

#### Run

```
    mvn exec:java
```


#### Bot Username
```
    TACS20191CGrupo3Bot
```
    
##
### Proyecto React

```
    cd grupo3-frontend-react/grupo3-react-application
    npm i
    npm start
