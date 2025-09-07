# 🌍 Filiera agricola

*Filiera agricola* è un progetto sviluppato per l'esame di *Ingegneria del Software* presso *UNICAM* nell'anno accademico 2025/2026.  
Il team di sviluppo è composto da:
- *Beatrice Cruciani*
- *Giulia Lupi*
- *Sofia Pacetti*

L'obiettivo del software è *gestione, valorizzazione e tracciabilità dei prodotti agricoli di un territorio comunale*, consentendo la registrazione e consultazione di eventi, punti di interesse, itinerari e altre informazioni culturali.


---


## Struttura del Progetto

Filiera Agricola è basato su un'architettura *API REST*, che permette di gestire i dati attraverso chiamate HTTP.  
L'interfaccia grafica per l'interazione con le API è fornita da *Swagger*, che facilita l'esplorazione delle chiamate in modo intuitivo e veloce.

Le API consentono di:
-  Creare e gestire eventi, itinerari e punti di interesse.
-  Controllare e modificare i contenuti associati a ciascun elemento.

Nel progetto è incluso un file *Visual Paradigm che documenta le fasi di progettazione attraverso diagrammi UML*, mostrando casi d'uso e flussi implementati nel codice.


---


## 📦 Installazione e Avvio del Progetto

### *Prerequisiti*
- *Java 17+* installato
- *Maven* installato sul dispositivo
- *Git* (opzionale, per clonare il repository)


### *Istruzioni*
- Puoi scaricare il progetto manualmente oppure clonarlo tramite Git
- Una volta avviato il server, puoi accedere alla documentazione delle API tramite Swagger visitando:
http://localhost:8080/swagger-ui/index.html
- Per visualizzare il DB, le info relative al login sono contenute nella classe application.properties
http://localhost:8080/h2-console
