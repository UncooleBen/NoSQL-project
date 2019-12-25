# SteamedEgg
This is a project for Information Storage and Management. It's only allowed for the use as a final project.  
## Description
The project aims at providing history prices and some other information like tags and categories for all applications(primarily games) on Steam.
## Development Environment
### Framework
Frontend: bootstrap  
Backend: SpringMVC
### Tools
Python 3.7.3  
IntelliJ IDEA Ultimate 2019.3  
MongoDB 4.2.2 Community version  
Tomcat 9.0.26
## Runtime Environment
1. MongoDB should be installed as a service on localhost with port 27017.  
2. In MongoDB, there must be a database named as `steamdb`.  
3. In `steamdb`, 2 collections are needed which are named as `apps` and `blogs`.  
4. Create index for documents in collection to bypass the 32MB limitation on query sorted documens
4.1 Run `mongo` in commandline prompt
4.2 `use steamdb`
4.3 `db.apps.createIndex({"steam_appid" : 1}, { unique : true })`
4.4 `db.apps.createIndex({"recommendations.total" : -1 })`

Then, everything will work well.
