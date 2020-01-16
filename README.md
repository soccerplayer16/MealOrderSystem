# MealOrderSystem

## Project Goal
Design a *Meal Order System* using Spring Boot Framework



## Tools/Knowledge explored and employed
Java, Spring Boot, MySQL, Redis, Nginx, RESTful APIs,



## Overall Architecture of the Project

In this project, my desired architecture is shown as below. The front end has seller and customer APIs, where the seller APIs are used as a seller oder management system that can manages what product can be shown on the customer's website and manage customers' orders. The customer APIs (still under development) will be able to select items and make orders.

The RESTful requests will be directed to Tomcat servers (locally hosted on my laotop) through NGINX. Then my Spring Boot applications take care HTTP rerequests and interact with Redis (used as Cache) and MySQL database if needed. I used Redis as Cache memory as well as a distributed lock.

![Overall Architecture](/OverallStructure.png)




## Project Class/Object Structure Design

This projects are divided into multiple layers. 

![Project Structure](/ProjectStructureDesign.png)



