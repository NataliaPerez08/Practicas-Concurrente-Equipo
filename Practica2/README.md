# Equipo: Los hilos del barrio
Jonathan Bautista Parra
Natalia Abigail Pérez Romero
Valeria Reyes Tapia
## Detalles de la Práctica 2: Ley de Amdahl y Paralelismo

Solucionar un problema secuencial, el descifrado de una contrase˜na, de forma con-
currente. Analizar la Ley de Amdahl en la prática.

### Descripción del Programa

1. **Método Principal (`main`) de la clase Main**: En el método principal, se ejecuta de manera secuencial la primera versión del programa para descifrar una contraseña.

2. **Método Principal (`main`) de la clase Cadenas**: En el método principal, se ejecuta de manera concurrente con el número de hilos deseados.

### Salida Esperada
Inicio:
735595.6458208001
Longitud: 6
Hilos: 1
La contrasenna es: hlsbrr
Tiempo final: 
736174.8050515001

---------------------------------------------------------

Inicio: 
737490.8273297
Longitud: 6
Hilos: 2
La contrasenna es: hlsbrr
Tiempo final: 
738216.6176661

---------------------------------------------------------

Inicio: 
738552.3487908001
Longitud: 6
Hilos: 27
La contrasenna es: hlsbrr
Tiempo final: 
738917.0077054

---------------------------------------------------------

Inicio:
739760.5705714
Longitud: 6
Hilos: 100
La contrasenna es: hlsbrr
Tiempo final: 
740971.997299