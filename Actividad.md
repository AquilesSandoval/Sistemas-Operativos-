
# Administrador de Memoria 
## 3.1 Politica y filosofia
### 1.Tipos de memoria y sus diferencias
- Interna: Se produce cuando una partición de memoria es asignada a un proceso o datos que no la llenan por completo. Esto hace que se desperdicie el espacio sobrante.
- Externa:  Se produce cuando la memoria o el espacio en disco se divide en bloques pequeños y no contiguos. Esto dificulta encontrar un bloque lo suficientemente grande para un nuevo programa o datos.

*Su principal diferencia seria la fragmentación interna se produce cuando un bloque de memoria es más grande que el tamaño solicitado, mientras que la fragmentación externa se produce cuando la memoria o el espacio en disco se divide en muchos bloques pequeños y no contiguos*

### 2.Tipos de remplazo de paginas
*Son algoritmos que determinan qué páginas eliminar de la memoria principal cuando se necesita cargar una nueva página*

1. **Criterio de página óptima (OPT, MIN)**
   - Selecciona la página que no será utilizada por el mayor tiempo en el futuro.
   - Este criterio es teóricamente óptimo, pero difícil de implementar debido a la imprevisibilidad de las referencias futuras.

2. **Criterio de página pésima (MAX)**
   - Elige la página que ha sido utilizada recientemente con menos frecuencia o prioridad.
   - Contrasta con el criterio óptimo.

3. **Criterio de selección estocástica (aleatoria)**
   - Selecciona una página al azar para reemplazar.
   - Fácil de implementar pero no optimiza el desempeño.

4. **Criterio MRU (Most Recently Used)**
   - Reemplaza la página que ha sido utilizada más recientemente.
   - Basado en la idea de que las páginas usadas recientemente podrían no ser necesarias en el futuro inmediato.

5. **Criterio por orden de carga (FIFO)**
   - Reemplaza la página más antigua, es decir, la que fue cargada primero.
   - Implementación simple pero puede no ser eficiente en ciertos patrones de acceso.

6. **Criterio NRU (Not Recently Used)**
   - Reemplaza páginas que no han sido referenciadas recientemente.
   - Basado en un bit de referencia que indica si una página ha sido utilizada.

7. **Criterio de segunda oportunidad**
   - Variación de FIFO, pero se revisa el bit de referencia antes de reemplazar.
   - Si el bit es 1, se le da una "segunda oportunidad" y se mueve al final de la cola.

8. **Criterio de tercera oportunidad (Método Multics)**
   - Extiende el criterio de segunda oportunidad añadiendo un nivel adicional de prioridad basado en otro bit.
   - Proporciona mayor control sobre el reemplazo.

9. **Criterio LRU (Least Recently Used)**
   - Reemplaza la página que no ha sido utilizada por más tiempo.
   - Buena aproximación al óptimo, pero puede ser costoso de implementar sin hardware especial.

10. **Criterio LFU (Least Frequently Used)**
    - Reemplaza la página que ha sido utilizada con menor frecuencia.
    - Se basa en un contador de accesos para cada página.

*Considero desde mi punto de vista que el criterio LRU seria la mejor para remplazar y asi continuar con los procesos que casi no son utilizados y dar prioridad a los que si, generando una mayor redimiento de nuestra computadora*

## 3.2 Memoria real
**Simulador de particiones en C**
``` C
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_PARTICIONES 10
#define MAX_PROCESOS 10

typedef struct {
    int tamano;     
    bool ocupado;
    int proceso_id;
} Particion;

typedef struct {
    int id;
    int tamano;   
    int particion_asignada;
} Proceso;

Particion particiones[MAX_PARTICIONES];
Proceso procesos[MAX_PROCESOS];
int num_particiones = 0;
int num_procesos = 0;

void mostrar_memoria() {
    if (num_particiones == 0) {
        printf("No hay particiones definidas. Por favor, configura las particiones primero.\n");
        return;
    }

    printf("Estado de la memoria:\n");
    for (int i = 0; i < num_particiones; i++) {
        printf("Particion %d: Tamano = %d KB, ", i + 1, particiones[i].tamano);
        if (particiones[i].ocupado)
            printf("Ocupado por Proceso %d\n", particiones[i].proceso_id);
        else
            printf("Libre\n");
    }
}

void definir_particiones() {
    int tamano_total, tamano;
    printf("Define el tamano total de la memoria (en KB): ");
    scanf("%d", &tamano_total);

    printf("Cuantas particiones quieres crear (max %d): ", MAX_PARTICIONES);
    scanf("%d", &num_particiones);

    int suma_tamanos = 0;
    for (int i = 0; i < num_particiones; i++) {
        printf("Tamano de la particion %d (en KB): ", i + 1);
        scanf("%d", &tamano);
        if (suma_tamanos + tamano > tamano_total) {
            printf("No hay suficiente memoria restante. Intenta un tamano menor.\n");
            i--;
            continue;
        }
        particiones[i].tamano = tamano;
        particiones[i].ocupado = false;
        particiones[i].proceso_id = -1;
        suma_tamanos += tamano;
    }
    printf("Particiones configuradas correctamente.\n");
}

void asignar_proceso() {
    if (num_particiones == 0) {
        printf("No hay particiones definidas. Por favor, configura las particiones primero.\n");
        return;
    }

    int tamano;
    printf("Tamano del nuevo proceso (en KB): ");
    scanf("%d", &tamano);

    for (int i = 0; i < num_particiones; i++) {
        if (!particiones[i].ocupado && particiones[i].tamano >= tamano) {
            procesos[num_procesos].id = num_procesos + 1;
            procesos[num_procesos].tamano = tamano;
            procesos[num_procesos].particion_asignada = i;

            particiones[i].ocupado = true;
            particiones[i].proceso_id = procesos[num_procesos].id;

            printf("Proceso %d asignado a la particion %d\n", procesos[num_procesos].id, i + 1);
            num_procesos++;
            return;
        }
    }
    printf("No hay particiones disponibles para este proceso.\n");
}

void liberar_proceso() {
    if (num_particiones == 0) {
        printf("No hay particiones definidas. Por favor, configura las particiones primero.\n");
        return;
    }

    int proceso_id;
    printf("ID del proceso a liberar: ");
    scanf("%d", &proceso_id);

    for (int i = 0; i < num_particiones; i++) {
        if (particiones[i].ocupado && particiones[i].proceso_id == proceso_id) {
            particiones[i].ocupado = false;
            particiones[i].proceso_id = -1;
            printf("Proceso %d liberado de la particion %d\n", proceso_id, i + 1);
            return;
        }
    }
    printf("Proceso no encontrado.\n");
}

int main() {
    int opcion;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Definir o redefinir particiones\n");
        printf("2. Asignar proceso\n");
        printf("3. Liberar proceso\n");
        printf("4. Mostrar memoria\n");
        printf("5. Salir\n");
        printf("Elige una opcion: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1: definir_particiones(); break;
            case 2: asignar_proceso(); break;
            case 3: liberar_proceso(); break;
            case 4: mostrar_memoria(); break;
            case 5: printf("Saliendo del programa.\n"); return 0;
            default: printf("Opcion invalida. Intenta de nuevo.\n");
        }
    }
    return 0;
}
```

**Simulador de Primera Cabidad**
``` C
#include <stdio.h>

#define MAX_PROCESOS 100
#define MAX_BLOQUES 100

void primeraCabida(int bloques[], int nBloques, int procesos[], int nProcesos) {
    int asignacion[MAX_PROCESOS]; 
    for (int i = 0; i < nProcesos; i++) {
        asignacion[i] = -1;
    }

    for (int i = 0; i < nProcesos; i++) {
        for (int j = 0; j < nBloques; j++) {
            if (bloques[j] >= procesos[i]) { 
                asignacion[i] = j;          
                bloques[j] -= procesos[i]; bloque
                break;                     
        }
    }

    printf("\nResultado de asignación:\n");
    for (int i = 0; i < nProcesos; i++) {
        if (asignacion[i] != -1) {
            printf("Proceso %d (Tamaño: %d) -> Bloque %d\n", i + 1, procesos[i], asignacion[i] + 1);
        } else {
            printf("Proceso %d (Tamaño: %d) -> No asignado\n", i + 1, procesos[i]);
        }
    }
}

int main() {
    int bloques[MAX_BLOQUES], procesos[MAX_PROCESOS];
    int nBloques, nProcesos;

    printf("Ingrese el número de bloques de memoria: ");
    scanf("%d", &nBloques);

    printf("Ingrese los tamaños de los bloques:\n");
    for (int i = 0; i < nBloques; i++) {
        printf("Bloque %d: ", i + 1);
        scanf("%d", &bloques[i]);
    }

    printf("\nIngrese el número de procesos: ");
    scanf("%d", &nProcesos);

    printf("Ingrese los tamaños de los procesos:\n");
    for (int i = 0; i < nProcesos; i++) {
        printf("Proceso %d: ", i + 1);
        scanf("%d", &procesos[i]);
    }

    primeraCabida(bloques, nBloques, procesos, nProcesos);

    return 0;
}

```
## 3.3 Organizador de memoria Virtual
### Conceptos de Paginación y Segmentación

#### Paginación
La **paginación** es una técnica de gestión de memoria que divide tanto la memoria física como la memoria lógica en bloques de tamaño fijo llamados **páginas** y **marcos**:
- Una **página** es una unidad de memoria lógica (del proceso).
- Un **marco** es una unidad de memoria física (RAM).

Cuando un proceso necesita ejecutarse, sus páginas se cargan en los marcos disponibles de la memoria física, y la relación entre páginas y marcos se guarda en una **tabla de páginas**.

#### Ventajas de la paginación:
1. **Elimina fragmentación externa:** El espacio libre se utiliza más eficientemente.
2. **Acceso rápido:** La traducción de direcciones es sencilla mediante la tabla de páginas.
3. **Memoria virtual:** Permite usar más memoria lógica que la física real.

#### Desventajas de la paginación:
1. **Fragmentación interna:** Puede sobrar espacio dentro de un marco si el tamaño de la página no se usa completamente.
2. **Sobrecarga:** Se requiere espacio adicional para las tablas de páginas.
3. **Mayor complejidad:** La traducción de direcciones implica más pasos, lo que puede ralentizar el acceso.



### Segmentación
La **segmentación** es una técnica de gestión de memoria que divide la memoria lógica en bloques de tamaño variable llamados **segmentos**:
- Cada segmento representa una parte lógica del programa, como código, datos o pila.
- La dirección lógica en segmentación consta de un par `(número de segmento, desplazamiento)`.

#### Ventajas de la segmentación:
1. **Sin fragmentación interna:** Los segmentos son de tamaño variable y se ajustan mejor al uso real.
2. **Modularidad:** Facilita la organización lógica de los programas, separando datos y código.
3. **Protección:** Permite aplicar políticas de acceso específicas a cada segmento.

#### Desventajas de la segmentación:
1. **Fragmentación externa:** El espacio libre puede dispersarse en la memoria física.
2. **Complejidad:** Administrar segmentos requiere tablas más sofisticadas.
3. **Desempeño:** La traducción de direcciones puede ser más lenta debido al manejo de tablas de segmentos.

``` C
#include <stdio.h>
#include <stdlib.h>

#define NUM_PAGINAS 4
#define NUM_BLOQUES 3

struct TablaDePaginas {
    int marcoFisico;
    int valido;
};

void inicializarTabla(struct TablaDePaginas tabla[], int numPaginas) {
    for (int i = 0; i < numPaginas; i++) {
        tabla[i].marcoFisico = -1;
        tabla[i].valido = 0;
    }
}

void mostrarTabla(struct TablaDePaginas tabla[], int numPaginas) {
    printf("Tabla de Páginas:\n");
    for (int i = 0; i < numPaginas; i++) {
        if (tabla[i].valido) {
            printf("Página %d -> Marco %d\n", i, tabla[i].marcoFisico);
        } else {
            printf("Página %d -> No asignada\n", i);
        }
    }
}

void asignarPagina(struct TablaDePaginas tabla[], int numPaginas, int numBloques, int pagina) {
    for (int i = 0; i < numPaginas; i++) {
        if (tabla[i].valido == 0) {
            tabla[i].marcoFisico = pagina;
            tabla[i].valido = 1;
            printf("Página %d asignada al marco %d.\n", i, pagina);
            return;
        }
    }
    printf("No hay espacio disponible para la página %d.\n", pagina);
}

void accesoMemoria(struct TablaDePaginas tabla[], int numPaginas, int direccionLogica) {
    if (direccionLogica >= numPaginas) {
        printf("Dirección lógica fuera de rango.\n");
        return;
    }

    if (tabla[direccionLogica].valido) {
        printf("Acceso exitoso a la dirección lógica %d (Marco %d).\n", direccionLogica, tabla[direccionLogica].marcoFisico);
    } else {
        printf("Error: la página %d no está cargada en memoria.\n", direccionLogica);
    }
}

int main() {
    struct TablaDePaginas tabla[NUM_PAGINAS];
    int direccionLogica;

    inicializarTabla(tabla, NUM_PAGINAS);

    asignarPagina(tabla, NUM_PAGINAS, NUM_BLOQUES, 0);
    asignarPagina(tabla, NUM_PAGINAS, NUM_BLOQUES, 1);
    asignarPagina(tabla, NUM_PAGINAS, NUM_BLOQUES, 2);

    mostrarTabla(tabla, NUM_PAGINAS);

    printf("\nSimulación de acceso a memoria:\n");
    printf("Ingrese la dirección lógica (0-3): ");
    scanf("%d", &direccionLogica);

    accesoMemoria(tabla, NUM_PAGINAS, direccionLogica);

    return 0;
}

```
## 3.5 Administracion de memoria Virtual


### Código en C para LRU:
```c
#include <stdio.h>
#include <stdlib.h>

#define NUM_PAGINAS 5
#define TAM_MEMORIA 3

int memoria[TAM_MEMORIA];
int tiempoAcceso[NUM_PAGINAS];

void inicializarMemoria() {
    for (int i = 0; i < TAM_MEMORIA; i++) {
        memoria[i] = -1;
    }
}

int buscarPagina(int pagina) {
    for (int i = 0; i < TAM_MEMORIA; i++) {
        if (memoria[i] == pagina) {
            return i;
        }
    }
    return -1;
}

void actualizarTiempos(int pagina) {
    for (int i = 0; i < TAM_MEMORIA; i++) {
        if (memoria[i] != -1) {
            tiempoAcceso[memoria[i]]++;
        }
    }
    tiempoAcceso[pagina] = 0;
}

void reemplazarPagina(int nuevaPagina) {
    int maxTiempo = -1;
    int paginaReemplazada = -1;
    
    for (int i = 0; i < TAM_MEMORIA; i++) {
        if (memoria[i] != -1 && tiempoAcceso[memoria[i]] > maxTiempo) {
            maxTiempo = tiempoAcceso[memoria[i]];
            paginaReemplazada = i;
        }
    }

    memoria[paginaReemplazada] = nuevaPagina;
    tiempoAcceso[nuevaPagina] = 0;
}

void accederPagina(int pagina) {
    int paginaEnMemoria = buscarPagina(pagina);
    if (paginaEnMemoria == -1) {
        for (int i = 0; i < TAM_MEMORIA; i++) {
            if (memoria[i] == -1) {
                memoria[i] = pagina;
                tiempoAcceso[pagina] = 0;
                return;
            }
        }
        reemplazarPagina(pagina);
    }
    actualizarTiempos(pagina);
}

void mostrarMemoria() {
    printf("Memoria actual: ");
    for (int i = 0; i < TAM_MEMORIA; i++) {
        printf("%d ", memoria[i]);
    }
    printf("\n");
}

int main() {
    int secuenciaPaginas[] = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
    int n = sizeof(secuenciaPaginas) / sizeof(secuenciaPaginas[0]);

    inicializarMemoria();
    
    for (int i = 0; i < n; i++) {
        printf("Accediendo a la página %d\n", secuenciaPaginas[i]);
        accederPagina(secuenciaPaginas[i]);
        mostrarMemoria();
    }

    return 0;
}


```
### Diagrama de traducción de direcciones virtuales a físicas en un sistema con memoria virtual
!(![ima](https://cursos.clavijero.edu.mx/cursos/182_so/modulo3/imagenes/tema3.3.1_clip_image006.jpg))

### Análisis de un sistema operativo moderno: Gestión de memoria virtual en Linux

En **Linux**, la gestión de memoria virtual se realiza utilizando la **paginación** y técnicas como el **swapping**. La memoria virtual permite que los procesos utilicen más memoria de la que está físicamente disponible. Cuando la memoria física se llena, Linux utiliza el **swap space** (espacio de intercambio) en el disco duro para liberar espacio en la RAM.

#### Paginación:
Linux utiliza **tablas de páginas** para gestionar la asignación de memoria virtual a física. Cada proceso tiene su propio espacio de direcciones virtuales.

#### Swap:
Cuando la **RAM** está llena, **Linux** transfiere páginas inactivas a la partición de intercambio (**swap**), lo que permite que los procesos sigan ejecutándose sin agotar la memoria física.

### Simulación en C de swapping de procesos

```C
#include <stdio.h>
#include <stdlib.h>

#define NUM_PROCESOS 5
#define TAM_MEMORIA 3

int memoria[TAM_MEMORIA];
int swapSpace[NUM_PROCESOS];

void inicializarMemoria() {
    for (int i = 0; i < TAM_MEMORIA; i++) {
        memoria[i] = -1;
    }
}

void inicializarSwap() {
    for (int i = 0; i < NUM_PROCESOS; i++) {
        swapSpace[i] = -1;
    }
}

void cargarEnMemoria(int proceso) {
    int espacioLibre = -1;
    for (int i = 0; i < TAM_MEMORIA; i++) {
        if (memoria[i] == -1) {
            memoria[i] = proceso;
            printf("Proceso %d cargado en memoria.\n", proceso);
            return;
        }
    }
    
    printf("Memoria llena. Realizando swapping...\n");
    espacioLibre = memoria[0];
    memoria[0] = proceso;
    swapSpace[espacioLibre] = 0;
    printf("Proceso %d movido a swap. Proceso %d cargado en memoria.\n", espacioLibre, proceso);
}

void mostrarMemoria() {
    printf("Memoria actual: ");
    for (int i = 0; i < TAM_MEMORIA; i++) {
        printf("%d ", memoria[i]);
    }
    printf("\n");
}

int main() {
    inicializarMemoria();
    inicializarSwap();
    
    cargarEnMemoria(1);
    mostrarMemoria();
    
    cargarEnMemoria(2);
    mostrarMemoria();
    
    cargarEnMemoria(3);
    mostrarMemoria();
    
    cargarEnMemoria(4);
    mostrarMemoria();
    
    return 0;
}

```

# Administración de Entrada/Salida
## 4.1 Dispositivos y manejadores de dispositivos
### Diferencia entre dispositivos de bloque y dispositivos de carácter

En los sistemas operativos, los dispositivos se clasifican en dos tipos principales: **dispositivos de bloque** y **dispositivos de carácter**. La principal diferencia entre ellos radica en cómo acceden a los datos y en la forma en que estos datos se transmiten.

#### Dispositivos de bloque:
- **Definición**: Son dispositivos que manejan datos en bloques de tamaño fijo. Estos bloques son leídos o escritos de forma independiente, lo que permite el acceso aleatorio a los datos. Cada bloque tiene una dirección única.
- **Acceso a los datos**: Los datos en los dispositivos de bloque se pueden leer o escribir en cualquier orden, ya que el acceso es aleatorio.
- **Ejemplo**: Un **disco duro (HDD)** o una **unidad de estado sólido (SSD)** son ejemplos de dispositivos de bloque. En ellos, los datos se organizan en bloques y se accede a ellos de manera eficiente a través de un sistema de gestión de bloques.

#### Dispositivos de carácter:
- **Definición**: Son dispositivos que transmiten datos en un flujo continuo de caracteres o bytes. En lugar de ser accedidos por bloques de tamaño fijo, los dispositivos de carácter entregan los datos de forma secuencial, lo que implica acceso secuencial a los datos.
- **Acceso a los datos**: Los datos se manejan de forma secuencial, y generalmente no se puede acceder a ellos de manera aleatoria.
- **Ejemplo**: Un **teclado** o un **puerto serial** son ejemplos de dispositivos de carácter. Estos dispositivos envían o reciben datos de uno en uno (carácter por carácter), y los datos deben ser procesados en ese orden.

```C
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TAM_BUFFER 100

typedef struct {
    char buffer[TAM_BUFFER];
    int idx;
} DispositivoEntrada;

void inicializarDispositivo(DispositivoEntrada *dispositivo) {
    memset(dispositivo->buffer, 0, TAM_BUFFER);
    dispositivo->idx = 0;
    printf("Dispositivo de entrada inicializado.\n");
}

void leerEntrada(DispositivoEntrada *dispositivo, const char *entrada) {
    int i = 0;
    while (entrada[i] != '\0' && dispositivo->idx < TAM_BUFFER - 1) {
        dispositivo->buffer[dispositivo->idx] = entrada[i];
        dispositivo->idx++;
        i++;
    }
    dispositivo->buffer[dispositivo->idx] = '\0';
    printf("Datos leídos del dispositivo: %s\n", dispositivo->buffer);
}

void escribirSalida(DispositivoEntrada *dispositivo) {
    printf("Datos en el dispositivo de entrada: %s\n", dispositivo->buffer);
}

int main() {
    DispositivoEntrada dispositivo;
    inicializarDispositivo(&dispositivo);
    
    leerEntrada(&dispositivo, "Hola, mundo!");
    escribirSalida(&dispositivo);
    
    return 0;
}

```
## 4.2 Mecanismos y funciones de los manejadores de dispositivos

### ¿Qué es la interrupción por E/S?

Una **interrupción por E/S (Entrada/Salida)** es un mecanismo mediante el cual un dispositivo de entrada/salida (como un teclado, ratón, disco duro, etc.) informa al procesador que ha completado una operación, o que está disponible para realizar una nueva operación. Esta interrupción permite que el sistema operativo interrumpa el flujo de ejecución del programa en curso para atender el dispositivo de E/S.

Las interrupciones por E/S permiten una mejor gestión del tiempo de CPU, ya que el procesador no tiene que estar esperando activamente a que los dispositivos de E/S terminen sus operaciones. En su lugar, el procesador puede continuar ejecutando otras tareas hasta que se reciba una señal de interrupción.

### ¿Cómo lo administra el sistema operativo?

Cuando un dispositivo de E/S genera una interrupción, el procesador detiene la ejecución del proceso actual y transfiere el control al **manejador de interrupciones** del sistema operativo. El manejador de interrupciones guarda el estado actual del proceso interrumpido y gestiona la solicitud de E/S. Una vez completada la operación de E/S, el sistema operativo reanuda la ejecución del proceso que había sido interrumpido.

### Ejemplo en pseudocódigo para simular una interrupción por E/S

```plaintext
Inicio
    Inicializar el sistema de E/S
    Crear proceso que solicita E/S
    Mientras (procesos en ejecución) hacer
        Si (solicitud de interrupción por E/S recibida) entonces
            Guardar estado del proceso actual
            Llamar a manejador de interrupción
            Ejecutar la operación de E/S
            Restaurar estado del proceso
            Reanudar ejecución del proceso interrumpido
        FinSi
    FinMientras
Fin
```
```C
#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void manejador_interrupcion(int signo) {
    if (signo == SIGINT) {
        printf("\nInterrupción recibida. El proceso ha sido interrumpido.\n");
    }
}

void proceso_simulado() {
    printf("Proceso en ejecución... (presiona Ctrl+C para generar una interrupción)\n");
    while (1) {
        // Simulamos que el proceso está haciendo algo en un bucle
        sleep(1);  // Espera de 1 segundo entre ciclos
    }
}

int main() {
    // Establecemos el manejador de la interrupción SIGINT (Ctrl+C)
    if (signal(SIGINT, manejador_interrupcion) == SIG_ERR) {
        printf("Error al establecer el manejador de la interrupción\n");
        return 1;
    }

    proceso_simulado();
    
    return 0;
}

```
## 4.3 Estructuras de datos para manejo de dispositivos
### ¿Qué es una cola de E/S?

Una **cola de E/S (Entrada/Salida)** es una estructura de datos utilizada en sistemas operativos para gestionar las solicitudes de entrada y salida de los dispositivos. Cuando varios procesos requieren acceso a dispositivos de E/S (como discos, impresoras, o interfaces de red), las solicitudes se almacenan en una cola para ser procesadas en orden. El sistema operativo utiliza esta cola para organizar y administrar el acceso a los dispositivos de manera eficiente.

#### Función de la cola de E/S:
1. **Gestión de solicitudes**: Las solicitudes de E/S de los procesos se encolan en la cola, y el sistema operativo las procesa en el orden que se determina según la política de programación de E/S. Dependiendo del algoritmo utilizado, las solicitudes pueden ser procesadas en orden de llegada (FIFO), priorizadas, o con otros métodos.
   
2. **Sincronización**: Las colas de E/S ayudan a garantizar que las solicitudes de acceso a los dispositivos no interfieran entre sí. El sistema operativo puede gestionar varias solicitudes de diferentes procesos y dispositivos, asegurando que los recursos se utilicen de manera eficiente.

#### Tipos de colas de E/S:
- **FIFO (First In, First Out)**: Las solicitudes se procesan en el orden en que llegan.
- **Prioridad**: Las solicitudes se procesan según su prioridad, independientemente del orden de llegada.
- **Round Robin**: Las solicitudes se procesan en un ciclo, asegurando que todas las solicitudes reciban atención.

```C
#include <stdio.h>
#include <string.h>

#define MAX_DISPOSITIVOS 3
#define TAM_BUFFER 100

typedef struct {
    char nombre[30];
    char buffer[TAM_BUFFER];
    int ocupado;
} Dispositivo;

void inicializarDispositivos(Dispositivo dispositivos[], int numDispositivos) {
    for (int i = 0; i < numDispositivos; i++) {
        dispositivos[i].ocupado = 0;
        snprintf(dispositivos[i].nombre, sizeof(dispositivos[i].nombre), "Dispositivo_%d", i + 1);
        dispositivos[i].buffer[0] = '\0';  
    }
}

void escribirEnDispositivo(Dispositivo *dispositivo, const char *datos) {
    if (dispositivo->ocupado == 0) {
        snprintf(dispositivo->buffer, TAM_BUFFER, "%s", datos);
        dispositivo->ocupado = 1;
        printf("Escrito en %s: %s\n", dispositivo->nombre, dispositivo->buffer);
    } else {
        printf("El %s está ocupado.\n", dispositivo->nombre);
    }
}

void leerDeDispositivo(Dispositivo *dispositivo) {
    if (dispositivo->ocupado == 1) {
        printf("Leído del %s: %s\n", dispositivo->nombre, dispositivo->buffer);
        dispositivo->ocupado = 0;
    } else {
        printf("El %s está vacío.\n", dispositivo->nombre);
    }
}

int main() {
    Dispositivo dispositivos[MAX_DISPOSITIVOS];

    inicializarDispositivos(dispositivos, MAX_DISPOSITIVOS);

    escribirEnDispositivo(&dispositivos[0], "Datos de entrada 1");
    leerDeDispositivo(&dispositivos[0]);

    escribirEnDispositivo(&dispositivos[1], "Datos de entrada 2");
    leerDeDispositivo(&dispositivos[1]);

    escribirEnDispositivo(&dispositivos[0], "Más datos para el dispositivo 1");
    leerDeDispositivo(&dispositivos[0]);

    return 0;
}

```
## 4.4 Operaciones de Entrada/Salida
### Código en C para operaciones de E/S asíncronas
```C
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <aio.h>
#include <fcntl.h>
#include <unistd.h>

#define TAM_BUFFER 1024

void manejador_asincrono(sigval_t sigval) {
    struct aiocb *cb = sigval.sival_ptr;
    if (aio_error(cb) == 0) {
        ssize_t bytes_leidos = aio_return(cb);
        printf("Operación de E/S asíncrona completada: %ld bytes leídos\n", bytes_leidos);
    } else {
        perror("Error en la operación de E/S");
    }
}

int main() {
    int fd;
    struct aiocb aiocb_read, aiocb_write;
    char buffer[TAM_BUFFER];

    fd = open("archivo.txt", O_RDONLY);
    if (fd == -1) {
        perror("Error abriendo el archivo");
        return 1;
    }

    memset(&aiocb_read, 0, sizeof(struct aiocb));
    aiocb_read.aio_fildes = fd;
    aiocb_read.aio_buf = buffer;
    aiocb_read.aio_nbytes = TAM_BUFFER - 1;
    aiocb_read.aio_offset = 0;
    aiocb_read.aio_sigevent.sigev_notify = SIGEV_THREAD;
    aiocb_read.aio_sigevent.sigev_notify_function = manejador_asincrono;
    aiocb_read.aio_sigevent.sigev_notify_attributes = NULL;

    if (aio_read(&aiocb_read) == -1) {
        perror("Error en la operación de lectura asíncrona");
        close(fd);
        return 1;
    }

    while (aio_error(&aiocb_read) == EINPROGRESS) {
        printf("Esperando a que la operación de lectura termine...\n");
        sleep(1);
    }

    fd = open("archivo_salida.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (fd == -1) {
        perror("Error abriendo el archivo de salida");
        return 1;
    }

    memset(&aiocb_write, 0, sizeof(struct aiocb));
    aiocb_write.aio_fildes = fd;
    aiocb_write.aio_buf = "Escritura asíncrona completada\n";
    aiocb_write.aio_nbytes = strlen("Escritura asíncrona completada\n");
    aiocb_write.aio_offset = 0;
    aiocb_write.aio_sigevent.sigev_notify = SIGEV_THREAD;
    aiocb_write.aio_sigevent.sigev_notify_function = manejador_asincrono;
    aiocb_write.aio_sigevent.sigev_notify_attributes = NULL;

    if (aio_write(&aiocb_write) == -1) {
        perror("Error en la operación de escritura asíncrona");
        close(fd);
        return 1;
    }

    while (aio_error(&aiocb_write) == EINPROGRESS) {
        printf("Esperando a que la operación de escritura termine...\n");
        sleep(1);
    }

    close(fd);
    return 0;
}

```

## Integración
### Implementación del algoritmo SCAN
```C
#include <stdio.h>
#include <stdlib.h>

#define MAX_CILINDRO 200

void ordenar(int arr[], int n) {
    int temp;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (arr[i] > arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}

void algoritmoSCAN(int solicitudes[], int n, int cabeza, int direccion) {
    int totalMovimiento = 0;
    int recorrido[n + 1];

    solicitudes[n] = cabeza;
    n++;

    ordenar(solicitudes, n);

    int index = 0;
    for (index = 0; index < n; index++) {
        if (solicitudes[index] > cabeza) {
            break;
        }
    }

    if (direccion == 1) {
        for (int i = index; i < n; i++) {
            recorrido[i - index] = solicitudes[i];
        }
        for (int i = index - 1; i >= 0; i--) {
            recorrido[n - i - 1] = solicitudes[i];
        }
    } else {
        for (int i = index - 1; i >= 0; i--) {
            recorrido[index - i - 1] = solicitudes[i];
        }
        for (int i = index; i < n; i++) {
            recorrido[n - i + index - 1] = solicitudes[i];
        }
    }

    for (int i = 1; i < n; i++) {
        totalMovimiento += abs(recorrido[i] - recorrido[i - 1]);
    }

    printf("Secuencia de acceso a los cilindros: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", recorrido[i]);
    }
    printf("\nTotal de movimiento de la cabeza: %d\n", totalMovimiento);
}

int main() {
    int solicitudes[] = {55, 58, 39, 18, 90, 160};
    int n = sizeof(solicitudes) / sizeof(solicitudes[0]);
    int cabeza = 50;
    int direccion = 1;

    printf("Algoritmo de planificación SCAN (Elevator)\n");
    algoritmoSCAN(solicitudes, n, cabeza, direccion);

    return 0;
}

```

### Simulacion del sistema

```c
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define MAX_BUFFER 1024

typedef struct {
    char data[MAX_BUFFER];
} DiscoDuro;

typedef struct {
    char data[MAX_BUFFER];
} Impresora;

typedef struct {
    char data[MAX_BUFFER];
} Teclado;

DiscoDuro disco;
Impresora impresora;
Teclado teclado;

pthread_mutex_t mutexDisco = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutexImpresora = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutexTeclado = PTHREAD_MUTEX_INITIALIZER;

void* leerTeclado(void* arg) {
    printf("Escribiendo en teclado...\n");
    pthread_mutex_lock(&mutexTeclado);
    snprintf(teclado.data, MAX_BUFFER, "Mensaje desde el teclado");
    pthread_mutex_unlock(&mutexTeclado);
    return NULL;
}

void* escribirDisco(void* arg) {
    pthread_mutex_lock(&mutexTeclado);
    pthread_mutex_lock(&mutexDisco);
    snprintf(disco.data, MAX_BUFFER, "%s", teclado.data);
    pthread_mutex_unlock(&mutexDisco);
    pthread_mutex_unlock(&mutexTeclado);
    printf("Datos escritos en el disco: %s\n", disco.data);
    return NULL;
}

void* imprimir(void* arg) {
    pthread_mutex_lock(&mutexDisco);
    pthread_mutex_lock(&mutexImpresora);
    snprintf(impresora.data, MAX_BUFFER, "%s", disco.data);
    pthread_mutex_unlock(&mutexImpresora);
    pthread_mutex_unlock(&mutexDisco);
    printf("Imprimiendo: %s\n", impresora.data);
    return NULL;
}

int main() {
    pthread_t hiloTeclado, hiloDisco, hiloImpresora;

    pthread_create(&hiloTeclado, NULL, leerTeclado, NULL);
    pthread_create(&hiloDisco, NULL, escribirDisco, NULL);
    pthread_create(&hiloImpresora, NULL, imprimir, NULL);

    pthread_join(hiloTeclado, NULL);
    pthread_join(hiloDisco, NULL);
    pthread_join(hiloImpresora, NULL);

    return 0;
}
```

## Avanzados
### ¿Cómo los sistemas operativos modernos optimizan las operaciones de entrada/salida con el uso de memoria caché?

Los sistemas operativos modernos optimizan las operaciones de entrada/salida (E/S) utilizando memoria caché para reducir el tiempo de acceso a los datos y mejorar el rendimiento general del sistema. La memoria caché actúa como un intermediario entre los dispositivos de almacenamiento más lentos (como discos duros o SSDs) y la memoria principal (RAM), almacenando los datos que son frecuentemente accedidos o que han sido recientemente utilizados.

#### **¿Cómo funciona la caché en E/S?**

1. **Caché de disco**:
   - La caché de disco se utiliza para almacenar en memoria los datos que se leen o escriben en el disco. Cuando una aplicación o proceso solicita datos, el sistema operativo primero verifica si estos datos están en la caché. Si están presentes (un "acierto en la caché"), el sistema puede proporcionar los datos mucho más rápidamente sin necesidad de acceder al disco.
   - En caso de que los datos no estén en la caché (un "fallo de caché"), el sistema operativo realiza una operación de E/S para leer los datos del disco y almacenarlos en la caché para futuras referencias.

2. **Algoritmos de reemplazo de caché**:
   - Los sistemas operativos implementan algoritmos como **LRU (Least Recently Used)** o **FIFO (First In, First Out)** para gestionar qué datos se deben mantener en la caché. Estos algoritmos determinan qué datos deben ser reemplazados en la caché cuando esta se llena, maximizando la probabilidad de que los datos más relevantes estén siempre disponibles para su uso inmediato.

3. **Caché en memoria virtual**:
   - Los sistemas operativos también utilizan caché en el contexto de la memoria virtual, donde las páginas de memoria que han sido recientemente accedidas se almacenan temporalmente en una caché para acelerar el acceso a los datos. Este enfoque reduce el tiempo de espera en operaciones de lectura y escritura, mejorando la eficiencia de las aplicaciones que requieren acceso frecuente a la memoria.

4. **Caché de escritura diferida (Write-back Cache)**:
   - En algunos sistemas, los datos escritos en la caché no se escriben inmediatamente en el dispositivo de almacenamiento. En cambio, los datos permanecen en la caché y se escriben en el disco en un momento posterior. Esto minimiza el número de operaciones de escritura en el disco y mejora el rendimiento global al reducir el tiempo de acceso al dispositivo de almacenamiento.

5. **Pre-carga de caché (Read-ahead Cache)**:
   - Los sistemas operativos también pueden predecir qué datos serán solicitados a continuación y cargarlos de antemano en la caché. Este enfoque mejora el rendimiento de la lectura, ya que reduce la cantidad de tiempo que el sistema tiene que esperar para leer los datos del disco.

#### **Beneficios de la caché en E/S:**

- **Reducción de la latencia**: El acceso a los datos desde la caché es mucho más rápido que desde el disco o almacenamiento físico.
- **Mejora del rendimiento general**: La caché reduce la necesidad de acceder repetidamente al almacenamiento, mejorando la eficiencia de las operaciones de lectura y escritura.
- **Optimización de recursos**: Al reducir el número de accesos a disco, los sistemas operativos minimizan el desgaste de los dispositivos de almacenamiento y optimizan el uso de los recursos de hardware.

#### **Conclusión**:
El uso de memoria caché en las operaciones de entrada/salida permite a los sistemas operativos modernos optimizar el rendimiento y la eficiencia, reduciendo significativamente los tiempos de espera y mejorando la capacidad de respuesta del sistema.







