### Gráfica de tiempo de ejecución vs el número de hilos.
![Gráfico de Tiempo de ejecución vs Número de hilos](https://quickchart.io/chart?c=%7B%0A%20%20%22type%22%3A%20%22line%22%2C%0A%20%20%22data%22%3A%20%7B%0A%20%20%20%20%22labels%22%3A%20%5B%221%22%2C%20%22100%22%2C%20%221000%22%5D%2C%0A%20%20%20%20%22datasets%22%3A%20%5B%0A%20%20%20%20%20%20%7B%0A%20%20%20%20%20%20%20%20%22label%22%3A%20%22Tiempo%20de%20ejecuci%C3%B3n%22%2C%0A%20%20%20%20%20%20%20%20%22data%22%3A%20%5B14%2C%2010%2C%2065%5D%2C%0A%20%20%20%20%20%20%20%20%22fill%22%3A%20false%2C%0A%20%20%20%20%20%20%20%20%22borderColor%22%3A%20%22rgb(75%2C%20192%2C%20219)%22%2C%0A%20%20%20%20%20%20%20%20%22lineTension%22%3A%200.1%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%5D%0A%20%20%7D%2C%0A%20%20%22options%22%3A%20%7B%0A%20%20%20%20%22scales%22%3A%20%7B%0A%20%20%20%20%20%20%22yAxes%22%3A%20%5B%0A%20%20%20%20%20%20%20%20%7B%0A%20%20%20%20%20%20%20%20%20%20%22scaleLabel%22%3A%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20%22display%22%3A%20true%2C%0A%20%20%20%20%20%20%20%20%20%20%20%20%22labelString%22%3A%20%22Tiempo%20de%20ejecuci%C3%B3n%20(ms)%22%0A%20%20%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%5D%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D)

### Cálculo de la Aceleración para Diferentes Cantidades de Hilos

#### Para 1 hilo:
- Tiempo de ejecución secuencial (1 hilo): 14 ms (proporcionado en los datos)
- Aceleración obtenida (1 hilo) = Tiempo de ejecución secuencial / Tiempo de ejecución paralela (1 hilo)
- Aceleración obtenida (1 hilo) = 14 ms / 14 ms = 1

#### Para 100 hilos:
- Tiempo de ejecución secuencial (1 hilo): 14 ms (proporcionado en los datos)
- Tiempo de ejecución paralela (100 hilos): 10 ms (proporcionado en los datos)
- Aceleración obtenida (100 hilos) = Tiempo de ejecución secuencial / Tiempo de ejecución paralela (100 hilos)
- Aceleración obtenida (100 hilos) = 14 ms / 10 ms ≈ 1.4

#### Para 1000 hilos:
- Tiempo de ejecución secuencial (1 hilo): 14 ms (proporcionado en los datos)
- Tiempo de ejecución paralela (1000 hilos): 65 ms (proporcionado en los datos)
- Aceleración obtenida (1000 hilos) = Tiempo de ejecución secuencial / Tiempo de ejecución paralela (1000 hilos)
- Aceleración obtenida (1000 hilos) = 14 ms / 65 ms ≈ 0.215

#### Tabla de Aceleración:

| Hilos | Aceleración Teórica | Aceleración Obtenida | % Código en Paralelo |
|-------|----------------------|----------------------|----------------------|
| 1     | 1                    | 1                    | 100%                 |
| 100   | 100                  | 1.4                  | 100%                 |
| 1000  | 1000                 | 0.215                | 100%                 |

![Muestra los resultados obtenidos en la terminal al ejecutar el programa.](capturas/pruebas.png)

#### Lo aprendido en el ejercicio
- Que ejecutar un algoritmo que realiza una tarea en paralelo utilizando múltiples hilos de ejecución. Nos permite aprovechar los recursos del sistema de manera más efectiva y mejorar el rendimiento al distribuir la carga de trabajo.
- Que la ley de Amdahl nos ayuda a entender que incluso si una parte del código se ejecuta en paralelo, el tiempo total de ejecución aún está limitado por la fracción secuencial.
- Al analizar los resultados de las pruebas de rendimiento, logramos ver cómo la cantidad de hilos utilizados puede afectar la aceleración obtenida.
