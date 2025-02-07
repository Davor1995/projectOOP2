# Bibliotheksmanagement-System

## Projektbeschreibung
Das Bibliotheksmanagement-System ist eine Java-Anwendung mit einer grafischen Benutzeroberfläche (JavaFX), die es Benutzern ermöglicht, Bücher und Leser zu verwalten sowie Buchausleihen und -rückgaben durchzuführen. Die Anwendung ermöglicht es, neue Bücher und Leser hinzuzufügen, bestehende Einträge zu bearbeiten und zu löschen. Außerdem können Bücher an Leser ausgeliehen und zurückgegeben werden.

## Hauptanforderungen
1. **Benutzeroberfläche (JavaFX):** Die Anwendung verfügt über eine intuitive grafische Oberfläche, über die der Benutzer Bücher und Leser pflegen sowie Ausleihen und Rückgaben verwalten kann.
2. **Objekttypen:**
    - **Bücher:** Verwaltung von Titel, Autor, Erscheinungsjahr und ISBN.
    - **Leser:** Verwaltung von Name, Mitgliedsnummer und Kontaktdaten.
3. **Buchausleihe:** Benutzer können Bücher an Leser ausleihen und Rückgaben durchführen. Dabei wird festgehalten, wann ein Buch ausgeliehen und zurückgegeben wurde.
4. **Persistenz:** Die Daten werden lokal ohne externe Datenbank gespeichert, sodass sie auch nach einem Neustart der Anwendung verfügbar sind.
5. **Testabdeckung:** Es werden Tests erstellt, um die Kernfunktionalitäten der Anwendung zu überprüfen.
6. **Einsatz von Enums und anderen Techniken:**
    - **Enum:** Verwendung von Enums für Buchkategorien wie „Roman“, „Sachbuch“, „Science-Fiction“.
    - **Vererbung und Interfaces:** Nutzung von Vererbung und Interfaces zur Unterstützung der Anwendungsstruktur.
    - **Collections und Sortierung:** Einsatz von Collections zur Verwaltung von Büchern und Lesern, inklusive Sortierfunktionalitäten.
    - **Lambdas und Streams:** Optimierter Zugriff auf Daten durch den Einsatz von Lambdas und Streams.
7. **Fehlerbehandlung und Logging:** Umfassende Fehlerbehandlung und Logging zur Unterstützung der Fehleranalyse.
8. **Build-Prozess:** Das Projekt ist als Maven-Projekt organisiert und kann über Maven gebaut und verwaltet werden.

## Technische Details

### Umgebung und Abhängigkeiten
- **Java-Version:** Java SE 21
- **Externe Bibliotheken:** Einsatz von Gson für das JSON-Handling und JavaFX für die Benutzeroberfläche.
  ```xml
  <dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.9</version>
  </dependency>
