## Sudoku
***El repositorio tiene el juego Sudoku creado en Java (Swing) para Tecnología de Programación (2020) - Universidad Nacional del Sur.***

### Objetivo del juego
El juego consiste en completar una serie de 9 paneles que a su vez se encuentran divididos en 9 celdas con números del 1 al 9. 


### Reglas del juego
*  Completar las casillas vacías con uno solo de los 9 objetos disponibles.
* Una misma fila no puede contener objetos repetidos.
* Una misma columna no puede contener objetos repetidos.
* Un mismo panel no puede contener objetos repetidos
* El jugador gana cuando completa todas las celdas correctamente.


### Requerimientos
1. Utilizar la API Swing de Java apoyándose en el editor de GUI de Eclipse para elaborar una interfaz adecuada para el desarrollo del juego.
2. Los objetos disponibles para completar cada celda deberán mostrarse en la GUI basándose en algún tipo de imagen. Esto significa que si se opta por mostrar números, estos deberán
estar basados en imágenes.
3. La GUI debe tener la capacidad de indicar gráficamente los objetos que se encuentran rompiendo alguna de las reglas del juego 
4. Además de los elementos gráficos básicos necesarios para el juego, se deberá implementar un reloj que muestre el tiempo que ha transcurrido. Este elemento debe estar basado en
imágenes de cada dígito para formar el tiempo, los cuales se actualizan adecuadamente cada segundo.
5. Mostrar gráficamente cuando un jugador gana.
6. Implementar la lógica que cumpla con las reglas del juego.
7. Permitir que el estado inicial del juego sea obtenido a partir de información disponible en un archivo. Este archivo contendrá 9 filas de 9 números separados por un espacio, al leerlo se
debe comprobar que sea una solución sudoku válida. Luego, la eliminación de la información de algunas celdas, dará lugar al estado inicial del juego.
