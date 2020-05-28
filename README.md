# Catan
Catan JavaFX school project. 


# EERSTE OPSTART: 
1. wacht tot de pom.xml gegenereed is
2. Verwijder module-info.java (Project > Projectnaam > src > main > java)
3. Voeg in de pom.xml, in de org.openjfx plugin BINNEN <configuration> het volgende toe:
<executable>C:/Program Files/Java/jdk-14.0.1/bin/java</executable> (zet dit op de goede locatie van je java jdk14!)
4. Klik op build > rebuild project
5. Klik helemaal rechts op Maven.
6. Open de dropdown van Projectnaam
7. Open de dropdown van Plugins
8. Open 'clean', en dubbelklik op clean:clean. wacht to dit klaar is
9. Open 'compiler', en dubbelklik op compiler:compile. wacht tot dit klaar is
10. Open 'javafx', en dubbelklik op 

# TROUBLESHOOTING:
Check of in de pom.xml de executable op de goede locatie staat
Klik weer op maven > projectnaam > plugins > 'clean', en dubbelklik op clean:clean. wacht to dit klaar is
