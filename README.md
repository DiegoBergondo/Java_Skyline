# Java_Skyline
Versión Java del proyecto Skyline

La línea de horizonte que dibujan los edificios de una ciudad se conoce como Skyline.

En esta aplicació vamos a implementar un algoritmo que partiendo de los datos de situación y altura de una serie de edificios, nos devuelva la línea de horizonte (consistente en una lista de coordenadas) o Skyline que forman dichos edificios.

La ejecución de la versión se efectuará de este modo:

java -jar Skyline.jar fichero

donde el fichero contendrá un edificio por cada línea de texto en forma de tres
números naturales separados por comas. Si hay algún error de lectura el programa
lo indicará. Se adjunta un fichero a modo de ejemplo.

El programa principal realiza lo siguiente:

-Crea un nuevo problema de Skyline
-Lo alimenta con los edificios leídos del fichero de entrada
-Le pide que se resuelva
-Imprime la línea de horizonte en forma de lista de coordenadas
-Imprime el dibujo del skyline
