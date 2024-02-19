import java.util.Random;

public class MatrizMultiplicacion {
    private static final int SIZE = 100; // Tamaño de las matrices
    private static final int THREADS_1 = 1;
    private static final int THREADS_100 = 100;
    private static final int THREADS_1000 = 1000;

    private static int[][] matrixA;
    private static int[][] matrixB;
    private static int[][] resultMatrix;

    public static void main(String[] args) {
        // Inicializar las matrices
        matrixA = generateRandomMatrix();
        matrixB = generateRandomMatrix();
        resultMatrix = new int[SIZE][SIZE];

        // Pruebas
        testPerformance(THREADS_1);
        testPerformance(THREADS_100);
        testPerformance(THREADS_1000);
    }

    // Método para generar una matriz aleatoria
    private static int[][] generateRandomMatrix() {
        int[][] matrix = new int[SIZE][SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = random.nextInt(10); // Valores aleatorios entre 0 y 9
            }
        }
        return matrix;
    }

    // Método para multiplicar las matrices de forma paralela
    private static void multiplyParallel(int numThreads) {
        Thread[] threads = new Thread[numThreads];
        int tasksPerThread = SIZE / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                int start = threadIndex * tasksPerThread;
                int end = (threadIndex + 1) * tasksPerThread;
                for (int x = start; x < end; x++) {
                    for (int y = 0; y < SIZE; y++) {
                        for (int z = 0; z < SIZE; z++) {
                            resultMatrix[x][y] += matrixA[x][z] * matrixB[z][y];
                        }
                    }
                }
            });
            threads[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para probar el rendimiento con un número específico de hilos
    private static void testPerformance(int numThreads) {
        long startTime = System.currentTimeMillis();
        multiplyParallel(numThreads);
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución paralela (" + numThreads + " hilos): " + (endTime - startTime) + " ms");
    }
}
