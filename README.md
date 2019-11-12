# InternetShop


# Project description
Internet Shop is a web application where you can registration new user who can select items from the list to bucket and manage orders.
Also there is default admin account, admin can add new items.

# Project Structure
* Java 11
* Maven 4.0.0
* Servlet 3.1.0
* jstl 1.2
* log4j 1.2.17
* hibernate 5.4.5
* MySQL


# How to start?
Open this project in your IDE.

Make project as maven project.

Install and configure Tomcat.

Install and configure database management system(MySQL).

Run all queries from src/main/resources/init_db.sql file in your db.

Change login and password to your`s DB in src.main.java.Factory class(if you want to try JDBC implementation)

Change login and password to your`s DB in src.main.java.resources.hibernate.cfg.xml

Write desirable path to your log file in resources.log4j.properties.

After first Run Tomcat command make /injectData request to get admin account with login admin and password admin

# Authors
https://github.com/NikolaiKatsai
