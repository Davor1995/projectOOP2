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

