import numpy as np
import matplotlib.pyplot as plt

# Datos proporcionados
num_threads = [1, 100, 1000]
execution_times = [14, 10, 65]  # Tiempos de ejecución proporcionados en ms

# Crear la gráfica
plt.figure(figsize=(8, 6))
plt.plot(num_threads, execution_times, marker='o', linestyle='-')
plt.title('Tiempo de ejecución vs Número de hilos')
plt.xlabel('Número de hilos')
plt.ylabel('Tiempo de ejecución (ms)')
plt.grid(True)

# Mostrar la gráfica
plt.show()
