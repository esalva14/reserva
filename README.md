# Reserva

El Commit 5 fa que la App quan picas a "Entrar" es conecti al Servidor amb un GET i la url del
Servidor a la xarxa local (pe: 192.168.1.39) y al fitxer /message (El que utilitzem per retornar aquestes comandes).

(La carpeta server_app es el server que he creado con sbt.bat de Play. Es como el que teneis vosotros pero sin
los .java de las bases de datos y tal. Era solo para probar la conexion).

El Servidor fa 2 checks: Primer mira a applications.conf (linea 285) i mira si accepta les peticions del host (movil)
Tal i com está en aquest commit permet qualsevol. Així que applications.conf ha d'estar com aquí o de tal manera que accepti peticions
de la xarxa local

Després amb routes veu que els GET de /message els controla el AsyncController així que executa el codi d'aquesta classe.
Aquí simplement retorna "OK". Arriba a la app y ho veu bé
________________________________


Ara el que quedaria es amb aquesta config (applications i tal) en comptes d'apunta a AsyncController que apunti a les classes
de Java que heu fet per al Server (la que tenia la Base de dades i feia el check)

Si pengeu el codi de les classes de Java del Server puc anar pipeando si hay alguna cosita más.
