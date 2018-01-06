## D&D Troops

Team project for PA165 course taught at Faculty of Informatics at Masaryk University in autumn semester 2017.

#### Assignment

Through Forgotten realms are wandering various troops of several heroes trying to complete assigned mission (mission is
not an entity, it is just text attribute). Hero has a name, role, experience level. Troop has a name, mission and amount
of golden money. Role contains name, description and other suitable information. Example of a role is "elf magician".
Every hero could belong to up to one group and have assigned multiple roles. Administrator should be able to manage
(CRUD) all entities. Hero could assign himself to some group and also can assign himself some role.

---

#### Users in DB

| Username  | Password|
| ------------- | ------------- |
| admin | admin  |
| user  | user  |

### Role REST

###### List
```bash
curl localhost:8080/pa165/rest/roles
```

###### View
```bash
curl localhost:8080/pa165/rest/roles/{id}
```

###### Delete
```bash
curl -X DELETE localhost:8080/pa165/rest/roles/{id}
```

###### Create
```bash
curl -X POST -H "Content-Type: application/json" localhost:8080/pa165/rest/roles/create --data '{"name":"Fighter","description":"Kung-Fu", "power":"MAGIC","damage":30,"cooldown":2}'
```

###### Edit
```bash
curl -X PUT -H "Content-Type: application/json" localhost:8080/pa165/rest/roles/{id} --data '{"id":{id},"name":"Noob","description":"Easy target", "power":"MARTIAL_ARTS","damage":2,"cooldown":3}'
```
