
*NYELVTANÁR NYILVÁNTARTÓ | David Attila Szekely 
v 2021.07.15.

```
 _                                                _____      _                 _ 
| |                                              /  ___|    | |               | |
| |     __ _ _ __   __ _ _   _  __ _  __ _  ___  \ `--.  ___| |__   ___   ___ | |
| |    / _` | '_ \ / _` | | | |/ _` |/ _` |/ _ \  `--. \/ __| '_ \ / _ \ / _ \| |
| |___| (_| | | | | (_| | |_| | (_| | (_| |  __/ /\__/ / (__| | | | (_) | (_) | |
\_____/\__,_|_| |_|\__, |\__,_|\__,_|\__, |\___| \____/ \___|_| |_|\___/ \___/|_|
                    __/ |             __/ |                                      
                   |___/             |___/  
```

# 1)  Operations on Teachers

Operation   | desc
------------ | -------------
GET    /teachers  | Retrieve all Teachers from the DB, sorted alphabetically.
Content in the first column | Content in the second column
GET    /teachers 		 |  Retrieve all Teachers from the DB, sorted alphabetically. 
GET    /teachers/{id}    |  Retrieve a teacher and his courses using its id 
PUT    /teachers 		 |  UPDATE  a teachers DATA /JSON/ 
POST   /teachers 		 |  ADD a new teacher series via JSON. 
DELETE /teachers 		 |  DELETE a teacher by ID 


# 2)  Operations on Languages
Operation   | desc
------------ | -------------
GET    /languages 	|	   List all Languages /by id/ 
GET    /languages 	|	   GET a  Language by ID  
POST   /languages 	|	   ADD a new language /JSON/. 
DELETE /languages 	|	   DELETE a language /identified by its ID/ 

# 3)  Operations on Courses
Operation   | desc
------------ | -------------
GET    /courses	     |   List all languages identified by their season id,in alphabetical order. 
GET    /courses	/{id}|	   GET a specific course by its Id. 
PUT    /courses 	 |	   UPDATE a course JSON. 
POST   /courses		 |      ADD a new course /JSON/ 
DELETE /courses 	 |      DELETE course by its id. 


# Schema
```
┌────────────┐
│ TEACHERS   │
├────────────┤      ┌─────────────┐
│   ID       │      │ LANGUAGES   │
├────────────┤      ├─────────────┤
│ NAME       │      │ ID          │
│ AGE        ├♦────♦┼─────────────┤
│ ADDRESS    │      │ NAME        │
│ LANGUAGES  │      │             │
│            │      │             │
└─────────O──┘      └───O─────────┘
          │             │
          ♦             ♦
       ┌──┴─────────────┴─┐
       │  COURSES         │
       ├──────────────────┤
       │   ID             │
       │───────────────── │
       │   LANGUAGE       │
       │   START_DATE     │
       │   END_DATE       │
       │   TEACHER        │
       │                  │
       └──────────────────┘
 ``` 

