# Bibliotheksmanagement-System

## Projektbeschreibung
Das Bibliotheksmanagement-System ist eine Java-Anwendung mit einer grafischen Benutzeroberfläche (JavaFX), die es Benutzern ermöglicht, Bücher und Leser zu verwalten sowie Buchausleihen und -rückgaben durchzuführen. Die Anwendung soll es erlauben, neue Bücher und Leser hinzuzufügen, bestehende Einträge zu bearbeiten und zu löschen. Außerdem können Bücher an Leser ausgeliehen und zurückgegeben werden.

## Hauptanforderungen
1. **Benutzeroberfläche (JavaFX):** Die Anwendung verfügt über eine intuitive grafische Oberfläche, über die der Benutzer Bücher und Leser pflegen sowie Ausleihen und Rückgaben verwalten kann.

2. **Objekttypen:** Es gibt zwei Hauptobjekttypen:
   - **Bücher:** Verwaltung von Titel, Autor, Erscheinungsjahr und ISBN.
   - **Leser:** Verwaltung von Name, Mitgliedsnummer und Kontaktdaten.

3. **Buchausleihe:** Benutzer können Bücher an Leser ausleihen und Rückgaben durchführen. Dabei wird festgehalten, wann ein Buch ausgeliehen und zurückgegeben wurde.

4. **Persistenz:** Die Daten werden lokal ohne externe Datenbank gespeichert, sodass sie auch nach einem Neustart der Anwendung verfügbar sind (Java-Serialisierung).

5. **Testabdeckung:** Es werden mindestens vier Unit-Tests erstellt, um die Kernfunktionen der Anwendung (z.B. Hinzufügen von Büchern, Ausleihe und Rückgabe) zu überprüfen.

6. **Einsatz von Enums und anderen Techniken:**
   - **Enum:** Ein Enum für Buchkategorien (z. B. „Roman“, „Sachbuch“, „Science-Fiction“).
   - **Vererbung und Interfaces:** Einfache Vererbung und Interface-Implementierung, um die Struktur der Anwendung zu unterstützen.
   - **Collections und Sortierung:** Sammlung von Büchern und Lesern in Collections mit Möglichkeit zur Sortierung.
   - **Lambdas und Streams:** Optimierter Zugriff auf Daten durch Einsatz von Lambdas und Streams.

7. **Fehlerbehandlung und Logging:** Alle relevanten Fehler werden abgefangen und im Log mit detaillierten Meldungen dokumentiert, um eine einfache Fehleranalyse zu ermöglichen.

8. **Build-Prozess:** Das Projekt wird als Maven-Projekt organisiert und kann mit Maven gebaut werden. Es wird eine ausführbare Version zur Verfügung gestellt.

# Implementierung und Fehlerbehebung der Buchspeicherung in `BookService`

## Einleitung
Diese Dokumentation beschreibt die Funktionalitäten und den Umgang mit der `BookService`-Klasse, die für das Speichern und Laden von Bücherdaten in einem JSON-Format verantwortlich ist. Ziel ist es, eine nachhaltige und erweiterbare Lösung zu bieten, die leicht zu warten und zu verstehen ist.

## Umgebung und Abhängigkeiten
- **Java-Version:** Java SE 21
- **Externe Bibliotheken:**
   - **Gson** für JSON-Handling:
     ```xml
     <dependency>
         <groupId>com.google.code.gson</groupId>
         <artifactId>gson</artifactId>
         <version>2.8.9</version>
     </dependency>
     ```

## Klassenstruktur
### Klasse `Book`
- **Attribute:**
   - `title`: Titel des Buches.
   - `author`: Autor des Buches.
- **Methoden:**
   - Konstruktoren, Getter und Setter.
- **Besonderheiten:** Implementiert `Serializable` für die Serialisierung.

### Klasse `BookService`
- **Methoden:**
   - `addBook(Book book)`: Fügt ein Buch hinzu und speichert die Änderungen.
   - `getAllBooks()`: Lädt alle gespeicherten Bücher und gibt sie zurück.
   - `saveBooksToFile()`: Speichert die Bücherliste in einer Datei.
   - `loadBooksFromFile()`: Lädt die Bücherliste aus einer Datei.

## Methodenbeschreibung
### Speichern der Bücher
Speichert die aktuelle Liste von Büchern in einer JSON-Datei:
```java
public void saveBooksToFile() throws IOException {
    try (Writer writer = new FileWriter(FILE_PATH)) {
        gson.toJson(books, writer);
    }
}

