## Detalles del Programa de Multiplicación de Matrices Paralelas para nuestra Misión Secundaria

Este programa en Java, `MatrizMultiplicacion`, se encarga de multiplicar dos matrices de tamaño fijo de manera paralela utilizando múltiples hilos. A continuación, se proporciona una descripción más detallada de su funcionamiento y cómo ejecutarlo:

### Descripción del Programa

1. **Declaración de Variables y Constantes**: Se establecen las variables estáticas y constantes necesarias para el tamaño de las matrices y el número de hilos a utilizar.

2. **Generación de Matrices Aleatorias**: Se define el método `generateRandomMatrix()` que crea y devuelve una matriz de tamaño `SIZE x SIZE` con valores aleatorios entre 0 y 9.

3. **Multiplicación de Matrices Paralelas**: El método `multiplyParallel(int numThreads)` se encarga de realizar la multiplicación de las matrices de forma paralela, dividiendo el trabajo entre los hilos para distribuir la carga eficientemente.

4. **Pruebas de Rendimiento**: El método `testPerformance(int numThreads)` ejecuta la multiplicación de matrices utilizando el número dado de hilos y registra el tiempo de ejecución.

5. **Método Principal (`main`)**: En el método principal, se inicializan las matrices, se realizan las pruebas de rendimiento para diferentes cantidades de hilos y se imprime el tiempo de ejecución de cada prueba.

### Ejecución del Programa

Para ejecutar el programa, sigue los siguientes pasos:

1. Asegúrate de tener instalado Java en tu sistema.
2. Clona el repo y dirigete a la carpeta `Matriz`.
3. Abre una terminal y navega hasta el directorio que contiene el archivo Java.
4. Compila el programa ejecutando el siguiente comando:

   ```bash
   javac MatrizMultiplicacion.java
   ```

5. Una vez compilado sin errores, ejecuta el programa con el siguiente comando:

   ```bash
   java MatrizMultiplicacion
   ```

### Salida Esperada

Al ejecutar el programa, se mostrarán en la consola los tiempos de ejecución de la multiplicación paralela de matrices utilizando diferentes cantidades de hilos. Por ejemplo:


```
Tiempo de ejecución paralela (1 hilo): 14 ms
Tiempo de ejecución paralela (100 hilos): 10 ms
Tiempo de ejecución paralela (10,000 hilos): 65 ms
```

Esto muestra el tiempo tomado para realizar la multiplicación de matrices utilizando 1, 100 y 10,000 hilos, respectivamente. Los valores de tiempo proporcionados pueden variar según el hardware y la carga del sistema en el momento de la ejecución.

