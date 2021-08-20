# challenge
Mobile è un'applicazione Spring Boot.
All'avvio legge un file csv formato da un id utente e un numero di telefono cerca di correggere i numeri di telefono scritti male e salva i dati in un db H2 in memoria dove tiene traccia dell'eventuale correzione.

Espone delle Rest API per consultazione e per la verifica dei numeri di telefono. 


Per avviare il programma 
```shell   
  mvn spring-boot:run  
```
  
oppure 

```shell  
  mvn install  
  java -jar target/mobile-0.0.1-SNAPSHOT.jar  
``` 
  
Per leggere la documentazione aprire il browser digitando l'indirizzo localhost:8080






