# <span style="color: red;">Application web basée sur MVC2 et JPA </span>

## Objectif : 

*L’objectif principal de cet atelier et de maitriser l’API JPA « java persistance API » par la mise
en place d’une application web qui simule le comportement d’un site web e-Commerce, la partie
contrôleur de cette application sera basé su le MVC 2 c.à.d. une Servlet va contenir tous les actions
nécessaire liées à une gestion spécifique.* 

## Outils : 
Eclipse, Maven, Tomcate, MySQL, JPA


## Table of contents

- [diagramme de classe](#diagramme-de-classe)
- [Shema de base de donnees](#Shema-de-base-de-donnees)
- [Structure du projet](#Structure-du-projet)
- [Configuration JPA](#Configuration-JPA)
- [Login](#Login)
- [Logup](#Logup)
- [BackEnd](#BackEnd)
- [FrontEnd](#FrontEnd)
- [Creator](#creator)



## Diagramme de Classe
<img width="796" alt="diagrammeClass" src="https://user-images.githubusercontent.com/91638100/204505696-a600de60-c8d0-4910-9145-095020cf272e.png">



## Shema de base de donnees
<img width="716" alt="shemaBaseDonnes" src="https://user-images.githubusercontent.com/91638100/204505762-6b1553be-5230-4565-a771-0e6c9b5b2366.png">



## Structure du projet
<img width="299" alt="structureProjet" src="https://user-images.githubusercontent.com/91638100/204505732-04a01f7f-4a56-474b-8e18-742c32d5c267.png">



## Configuration JPA

```<?xml version="1.0" encoding="UTF-8" ?>


<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.2"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
<persistence-unit name="unit" transaction-type="RESOURCE_LOCAL">
 	  <class>ma.fstt.entities.Article</class>
 	   <class>ma.fstt.entities.Categorie</class>
 	   <class>ma.fstt.entities.Panier</class>
 	   <class>ma.fstt.entities.User</class>
 	   <class>ma.fstt.entities.Article_Panier</class>
      <properties>
         <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
         <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/mvc2"/>
         <property name="javax.persistence.jdbc.user" value="root"/>
         <property name="javax.persistence.jdbc.password" value=""/>
         <property name="eclipselink.ddl-generation" value="create-tables"/>
      </properties>
      
   </persistence-unit>
</persistence>
```

## Login
 <img width="893" alt="login" src="https://user-images.githubusercontent.com/91638100/204505886-bc2b1882-dc48-4780-b4a5-e779875e12e1.png">


## Logup

<img width="898" alt="logup" src="https://user-images.githubusercontent.com/91638100/204505902-822fe520-435c-4b79-9d6c-1435da3903a0.png">

## BackEnd
### Ajouter une Article
<img width="944" alt="ajouterArticle" src="https://user-images.githubusercontent.com/91638100/204506008-ddd6b0ff-a4ee-4b77-92a9-2d8bb56e4020.png">

### Modifier une Article
<img width="958" alt="updateArticle" src="https://user-images.githubusercontent.com/91638100/204506039-293a47e1-d5be-4a4e-b2c7-113557ad7538.png">

### Ajouter une categorie
<img width="960" alt="ajouterCategorie" src="https://user-images.githubusercontent.com/91638100/204506071-b0a157bf-739d-4bd8-aa92-ec596e4d60e2.png">

### Modifier une categorie
<img width="958" alt="updateCategorie" src="https://user-images.githubusercontent.com/91638100/204506109-9eaffee5-5cde-423f-9126-ec7b21be8203.png">

### Consulter la listes des utilisateurs avec les roles
<img width="959" alt="afficherUsers" src="https://user-images.githubusercontent.com/91638100/204506145-dd667894-e97a-42fd-99c2-417df4f2d394.png">

## FrontEnd
![fullFrontend](https://user-images.githubusercontent.com/91638100/204506186-e1788b71-dcce-4cac-ba15-2276b4964b5d.jpeg)

### Panier
<img width="960" alt="panier" src="https://user-images.githubusercontent.com/91638100/204506220-03e6db63-4bcb-4d3d-9483-1dbe53b12872.png">

### Details Article
<img width="949" alt="afficherUneArticle" src="https://user-images.githubusercontent.com/91638100/204506249-9a88c1aa-260b-4a82-9ffe-a9f6d771ddc2.png">

### Apres Authentification
<img width="960" alt="apresLogin" src="https://user-images.githubusercontent.com/91638100/204508462-0e587bbd-405d-43d2-bb87-1be8090f2383.png">

## Creator


- <https://github.com/FouadElMetioui>

