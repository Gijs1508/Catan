# Catan
Product of a school project where we built an online boardgame (Catan) with JavaFX and Firestore.

<img src="https://i.imgur.com/CnS395C.png" width="780" height="440">
<img src="https://i.imgur.com/a47SYMf.png" width="780" height="519">

# Authors
<ul>
  <li>Jan van Overbeek</li>
  <li>Werner van der Tak</li>
  <li>Gijs van der Weijden</li>
  <li>Sabrina Zaalberg</li>
  <li>Kaz Schraven</li>
  <li>Jeroen Bol</li>
 </ul>

# EERSTE OPSTART: 
1. wacht tot de pom.xml gegenereerd is
2. Verwijder module-info.java (Project > Projectnaam > src > main > java)
3. Voeg in de pom.xml, in de org.openjfx plugin BINNEN <configuration> het volgende toe:
<executable>C:/Program Files/Java/jdk-14.0.1/bin/java</executable> (zet dit op de goede locatie van je java jdk14!)
4. Klik op build > rebuild project
5. Klik helemaal rechts op Maven.
6. Open de dropdown van Projectnaam
7. Open de dropdown van Plugins
8. Open 'clean', en dubbelklik op clean:clean. Wacht to dit klaar is
9. Open 'compiler', en dubbelklik op compiler:compile. Wacht tot dit klaar is
10. Open 'javafx', en dubbelklik op javafx:run. Wacht tot dit klaar is

# TROUBLESHOOTING:
1. Check of in de pom.xml de executable op de goede locatie staat
2. Klik weer op maven > projectnaam > plugins > 'clean', en dubbelklik op clean:clean. wacht to dit klaar is
3. Je hoeft dus geen run configuration aan te maken, maar gewoon runnen via maven > javafx > javafx:run
