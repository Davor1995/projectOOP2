# OOP2 Abschlussprojekt – Library Management System

## 1. Projektidee
Das **Library Management System** ist eine JavaFX-Anwendung, die die Verwaltung einer kleinen Bibliothek ermöglicht.  
Benutzer können **Bücher und Kunden verwalten**, Bücher ausleihen und zurückgeben.  
Die Daten werden lokal gespeichert und bleiben auch nach dem Schließen der Anwendung erhalten.

## 2. Hauptfunktionen
- **Bücherverwaltung:** Bücher können hinzugefügt, bearbeitet und gelöscht werden.
- **Kundenverwaltung:** Kunden können erstellt und bearbeitet werden.
- **Ausleihen & Zurückgeben:** Bücher können einem Kunden zugewiesen und zurückgegeben werden.
- **Datenpersistenz:** Alle Daten werden in JSON-Dateien gespeichert.
- **Graphische Benutzeroberfläche (GUI):** Alle Funktionen sind über JavaFX bedienbar.

## 3. Technische Umsetzung
- **GUI:** JavaFX mit `TableView` für die Darstellung von Büchern und Kunden.
- **Persistenz:** JSON-Dateien mit `Gson` (keine externe Datenbank).
- **Unit-Tests:** Mindestens 4 Tests für die Kernfunktionen.
- **OOP-Konzepte:** Nutzung von Vererbung, Interfaces und Streams.
- **Exception Handling & Logging:** Fehler werden mit SLF4J und Logback geloggt.
- **Maven-Projekt:** Abhängigkeiten werden über `pom.xml` verwaltet.

## 4. Verantwortlichkeiten
**Projektmitglieder:**
- Davor Andrijasevic

## 5. Anforderungen erfüllt
✅ JavaFX-UI mit Benutzerinteraktion  
✅ Verwaltung von zwei verschiedenen Objekten (`Book`, `Client`)  
✅ Unit-Tests für die Kernfunktionen  
✅ Verwendung eines Enums (`BookStatus`)  
✅ Mindestens drei OOP-Techniken verwendet  
✅ JSON-Speicherung zur Persistenz  
✅ Fehlerbehandlung mit Logging  
✅ Maven-Projekt mit `pom.xml`

---
