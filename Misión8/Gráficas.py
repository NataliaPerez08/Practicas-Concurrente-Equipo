from threading import Lock

class Arista:
    """Representa una arista en el grafo."""
    def __init__(self, vertice1, vertice2):
        """Inicializa una arista con dos vértices."""
        self.vertices = (vertice1, vertice2)

    def __str__(self):
        """Devuelve una representación de cadena de la arista."""
        return f"Arista entre {self.vertices[0].dato} y {self.vertices[1].dato}"

class Vertice:
    """Representa un vértice en el grafo."""
    def __init__(self, dato):
        """Inicializa un vértice con un dato y propiedades adicionales."""
        self.dato = dato
        self.adyacentes = []  # Lista de vértices adyacentes
        self.aristas = []  # Lista de aristas incidentes al vértice
        self.grado = 0  # Grado del vértice (número de aristas incidentes)
        self.visitado = False  # Indica si el vértice ha sido visitado durante un recorrido
        self.lock = Lock()  # Objeto Lock para garantizar la seguridad en operaciones concurrentes

    def __str__(self):
        """Devuelve una representación de cadena del vértice."""
        adyacentes_str = ', '.join(str(v.dato) for v in self.adyacentes)
        return f"Vertice: {self.dato} - Grado: {self.grado} - Visitado: {self.visitado} - Adyacentes: [{adyacentes_str}]"

class Grafo:
    """Representa un grafo y proporciona métodos para manipularlo y realizar operaciones sobre él."""
    def __init__(self):
        """Inicializa un grafo vacío."""
        self.vertices = []  # Lista de vértices en el grafo
        self.aristas = []  # Lista de aristas en el grafo
        self.conexo = False  # Indica si el grafo es conexo

    def agregar_vertice(self, dato):
        """Agrega un nuevo vértice al grafo y lo devuelve."""
        nuevo_vertice = Vertice(dato)
        self.vertices.append(nuevo_vertice)
        return nuevo_vertice

    def agregar_arista(self, vertice1, vertice2):
        """Agrega una nueva arista al grafo entre dos vértices dados."""
        arista = Arista(vertice1, vertice2)
        vertice1.lock.acquire()
        vertice2.lock.acquire()
        vertice1.adyacentes.append(vertice2)
        vertice2.adyacentes.append(vertice1)
        vertice1.aristas.append(arista)
        vertice2.aristas.append(arista)
        vertice1.grado += 1
        vertice2.grado += 1
        self.aristas.append(arista)
        vertice2.lock.release()
        vertice1.lock.release()

    def verificar_conexion(self):
        """Verifica si el grafo es conexo."""
        for vertice in self.vertices:
            vertice.visitado = False

        if self.vertices:
            self.dfs(self.vertices[0])

        # Si algún vértice no fue visitado, entonces el grafo no es conexo
        for vertice in self.vertices:
            if not vertice.visitado:
                return False
        return True

    def dfs(self, vertice):
        """Realiza un recorrido DFS (Depth-First Search) desde un vértice dado."""
        vertice.visitado = True
        for adyacente in vertice.adyacentes:
            if not adyacente.visitado:
                self.dfs(adyacente)

    def flooding(self, vertice_inicial):
        """Realiza un algoritmo de flooding desde un vértice inicial."""
        for vertice in self.vertices:
            vertice.visitado = False

        print(f"Iniciando Flooding desde el vértice {vertice_inicial.dato}:")
        self.flood_helper(vertice_inicial)

    def flood_helper(self, vertice):
        """Función auxiliar para el algoritmo de flooding."""
        print(f"Mensaje enviado desde el vértice {vertice.dato}")
        vertice.visitado = True
        for adyacente in vertice.adyacentes:
            if not adyacente.visitado:
                print(f"Visitando vértice adyacente {adyacente.dato}")
                self.flood_helper(adyacente)
                adyacente.visitado = True

    def flooding_with_ack(self, vertice_inicial):
        """Realiza un algoritmo de flooding con ACK desde un vértice inicial."""
        for vertice in self.vertices:
            vertice.visitado = False

        print(f"Iniciando Flooding with ACK desde el vértice {vertice_inicial.dato}:")
        self.flood_with_ack_helper(vertice_inicial)

    def flood_with_ack_helper(self, vertice):
        """Función auxiliar para el algoritmo de inundación con ACK."""
        print(f"ACK recibido en el vértice {vertice.dato}")
        vertice.visitado = True
        for adyacente in vertice.adyacentes:
            if not adyacente.visitado:
                print(f"Visitando vértice adyacente {adyacente.dato}")
                self.flood_with_ack_helper(adyacente)
                adyacente.visitado = True

    def broadcast(self, vertice_inicial, mensaje):
        """Realiza un algoritmo de broadcast desde un vértice inicial con un mensaje dado."""
        for vertice in self.vertices:
            vertice.visitado = False

        print(f"Iniciando Broadcast desde el vértice {vertice_inicial.dato}:")
        self.broadcast_helper(vertice_inicial, mensaje)

    def broadcast_helper(self, vertice, mensaje):
        """Función auxiliar para el algoritmo de broadcast."""
        print(f"Mensaje enviado desde el vértice {vertice.dato}: {mensaje}")
        vertice.visitado = True
        for adyacente in vertice.adyacentes:
            if not adyacente.visitado:
                print(f"Visitando vértice adyacente {adyacente.dato}")
                self.broadcast_helper(adyacente, mensaje)
                adyacente.visitado = True

    def multicast(self, vertices_destino, mensaje):
        """Realiza un algoritmo de multicast a un conjunto de vértices destino con un mensaje dado."""
        for vertice in self.vertices:
            vertice.visitado = False

        print("Iniciando Multicast:")
        for vertice in vertices_destino:
            print(f"Visitando vértice destino {vertice.dato}")
            self.broadcast_helper(vertice, mensaje)

    def algoritmo_secuencial(self):
        """Simula un algoritmo secuencial visitando todos los vértices del grafo."""
        print("Iniciando Algoritmo Secuencial:")
        for vertice in self.vertices:
            print(f"Visitando vértice {vertice.dato}")
            vertice.visitado = True

    def __str__(self):
        """Devuelve una representación de cadena del grafo."""
        grafo_str = "Grafo:\n"
        for vertice in self.vertices:
            adyacentes = ", ".join(str(v.dato) for v in vertice.adyacentes)
            grafo_str += f"{vertice} -> [{adyacentes}]\n"
        return grafo_str

# Ejemplo de uso
if __name__ == "__main__":
    grafo = Grafo()
    # Agregar vértices
    v1 = grafo.agregar_vertice("A")
    v2 = grafo.agregar_vertice("B")
    v3 = grafo.agregar_vertice("C")
    v4 = grafo.agregar_vertice("D")
    # Agregar aristas
    grafo.agregar_arista(v1, v2)
    grafo.agregar_arista(v1, v3)
    grafo.agregar_arista(v2, v3)
    grafo.agregar_arista(v3, v4)

    print("Estado de los vértices antes de ejecutar los métodos:")
    for vertice in grafo.vertices:
        print(vertice)

    print("\nFlooding:")
    grafo.flooding(v1)

    print("\nEstado de los vértices después de ejecutar Flooding:")
    for vertice in grafo.vertices:
        print(vertice)

    print("\nBroadcast:")
    grafo.broadcast(v1, "Hola")

    print("\nEstado de los vértices después de ejecutar Broadcast:")
    for vertice in grafo.vertices:
        print(vertice)

    print("\nMulticast:")
    grafo.multicast([v2, v3], "Hola a todos")

    print("\nEstado de los vértices después de ejecutar Multicast:")
    for vertice in grafo.vertices:
        print(vertice)

    print("\nAlgoritmo Secuencial:")
    grafo.algoritmo_secuencial()

    print("\nEstado de los vértices después de ejecutar Algoritmo Secuencial:")
    for vertice in grafo.vertices:
        print(vertice)

    grafo.conexo = grafo.verificar_conexion()
    print("\nEl grafo es conexo:", grafo.conexo)
