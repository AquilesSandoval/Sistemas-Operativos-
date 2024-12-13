# PROYECTO FINAL 


## Ejercicio 1: Concepto y Noción de Archivo Real y Virtual

### Definición

### Archivo Real:
Es un archivo que representa datos almacenados físicamente en un dispositivo de almacenamiento, como un disco duro, una unidad SSD o una memoria USB. Estos archivos tienen una ubicación específica en el sistema de archivos, ocupan espacio físico y contienen datos que pueden ser directamente leídos o modificados.

### Archivo Virtual:
Es un archivo que no existe físicamente en el disco, sino que se genera dinámicamente por el sistema operativo o una aplicación. Su contenido puede provenir de información en memoria, cálculos en tiempo real o datos obtenidos de otras fuentes. Los archivos virtuales suelen ser usados para representar datos temporales o simulados.

---

## Ejemplos Prácticos en Sistemas Operativos

### Archivos Reales:
- Documentos en un sistema de archivos (ejemplo: `/home/user/documento.txt`).
- Imágenes almacenadas físicamente en una unidad (ejemplo: `/pictures/foto.jpg`).
- Archivos ejecutables o binarios de programas instalados (ejemplo: `/usr/bin/python`).

### Archivos Virtuales:
- Archivos en el sistema de archivos virtual `/proc` en Linux, como `/proc/cpuinfo`, que proporciona información sobre la CPU en tiempo real.
- Dispositivos representados como archivos en `/dev` en Linux, como `/dev/sda` (disco duro).
- Puntos de montaje en sistemas operativos modernos que acceden a servicios de red, como `/mnt/servidor_remoto` usando NFS o SMB.

---

## Caso Práctico: Utilidad de un Archivo Virtual
Un caso donde un archivo virtual es más útil que un archivo real es al monitorear el estado de un sistema. Por ejemplo:

- **Archivo Virtual `/proc/meminfo` en Linux**: Este archivo permite consultar información sobre la memoria RAM disponible, utilizada y reservada. En lugar de acceder a un archivo físico que tendría datos desactualizados, este archivo virtual genera su contenido en tiempo real a partir de la memoria del sistema.

### Ventaja:
Es especialmente útil para herramientas de monitoreo, ya que permite obtener información actualizada sin necesidad de escribir datos en el disco, evitando desgaste físico y mejorando el rendimiento.

---

## Conclusión
La principal diferencia entre archivos reales y virtuales es la existencia física en un almacenamiento. Los archivos reales son esenciales para el almacenamiento permanente, mientras que los virtuales son fundamentales para tareas dinámicas y en tiempo real.

## **Ejercicio 2: Componentes de un Sistema de Archivos**  

---

#### **Componentes Clave de un Sistema de Archivos**  

1. **Metadatos:** Información sobre el archivo, como nombre, tamaño, tipo, permisos, y fechas de creación/modificación.  
2. **Estructura de directorios:** Organización jerárquica que define cómo se almacenan y acceden los archivos.  
3. **Tablas de asignación:** Mapas que indican la ubicación de los archivos en el disco (por ejemplo, tablas como FAT en sistemas de Microsoft).  
4. **Bloques de datos:** Segmentos de almacenamiento físico donde se guardan los datos reales de los archivos.  
5. **Registros de transacciones (journaling):** Mecanismos para registrar cambios pendientes, garantizando la integridad de los datos en caso de fallos.  

---

#### **Cuadro Comparativo: EXT4 vs NTFS**

| **Componente**             | **EXT4 (Linux)**                                                                 | **NTFS (Windows)**                                                     |
|----------------------------|----------------------------------------------------------------------------------|------------------------------------------------------------------------|
| **Metadatos**              | Incluye permisos avanzados (propietario, grupo, otros) y atributos extendidos.   | Metadatos incluyen permisos básicos y soporte para flujos alternativos.|
| **Estructura de directorios** | Organiza los directorios en una tabla basada en árboles binarios para eficiencia. | Usa árboles B+ para almacenar y buscar entradas de directorios.        |
| **Tablas de asignación**    | Mapa basado en inodos, con punteros directos, indirectos y doble indirectos.    | Master File Table (MFT) que almacena todos los metadatos en un solo lugar.|
| **Bloques de datos**        | Soporte para bloques de diferentes tamaños (1 KB - 64 KB).                      | Bloques de tamaño fijo (generalmente 4 KB).                            |
| **Journaling**              | Mantiene un registro de transacciones en una región separada para prevenir corrupción. | Journaling avanzado que incluye cambios en metadatos y datos.         |
| **Compatibilidad**          | Diseñado para Linux, con soporte limitado en otros sistemas operativos.         | Altamente compatible con sistemas Windows, limitado en otros sistemas. |

---

#### **Ventajas y Desventajas de EXT4 y NTFS**  

**EXT4 (Linux):**  
- **Ventajas:**  
  1. Altamente eficiente para manejar grandes volúmenes de datos y pequeñas operaciones de escritura.  
  2. Soporte para sistemas Linux avanzados con permisos robustos y configuraciones de usuario.  
  3. Soporte para desfragmentación en tiempo real y volúmenes muy grandes.  
- **Desventajas:**  
  1. Soporte limitado en sistemas Windows.  
  2. No es tan adecuado para discos que necesitan compatibilidad multiplataforma.  

**NTFS (Windows):**  
- **Ventajas:**  
  1. Altamente compatible con entornos Windows y sistemas modernos.  
  2. Incluye características como cifrado (EFS) y compresión de archivos.  
  3. Sistema de journaling robusto que garantiza la recuperación de datos en caso de fallos.  
- **Desventajas:**  
  1. Más pesado en cuanto a consumo de recursos para tareas simples.  
  2. Mayor fragmentación comparado con EXT4, lo que puede degradar el rendimiento con el tiempo.  

---

#### **Conclusión**  
La elección entre EXT4 y NTFS depende del entorno y las necesidades del usuario. EXT4 destaca por su eficiencia y diseño optimizado para Linux, mientras que NTFS sobresale por su compatibilidad y soporte en sistemas Windows

## **Ejercicio 3: Organización Lógica y Física de Archivos**  

---

#### **Esquema de Organización Lógica**  

A continuación, se presenta un árbol jerárquico que representa la estructura lógica de directorios y subdirectorios de un sistema de archivos típico:  

![Esquema de Organización Lógica](https://docs.oracle.com/cd/E19620-01/805-7644/images/Files.fig149.epsi.gif)

- **Raíz (`/`)**: Es el nivel más alto del sistema de archivos. Contiene todos los directorios principales.  
- **Directorios y Subdirectorios**: Organizan archivos de manera lógica según su propósito (documentos, imágenes, configuraciones, etc.).  

---

#### **Traducción de Dirección Lógica a Dirección Física**  

Cuando se accede a un archivo, el sistema de archivos traduce su ubicación lógica (ruta) a una dirección física en el disco mediante estructuras como:  

1. **Inodos (EXT4, Linux):**  
   - Cada archivo tiene un inodo que almacena información sobre el archivo (tamaño, permisos, fechas, etc.) y punteros a los bloques de datos físicos donde están almacenados.  

2. **Master File Table (MFT, NTFS, Windows):**  
   - Los archivos y directorios tienen entradas en la MFT que contienen sus metadatos y referencias a las ubicaciones físicas en el disco.  

**Proceso General:**  
- El sistema busca el archivo en la estructura lógica (ej., `/home/user/documentos/informe.txt`).  
- El inodo o MFT del archivo contiene punteros que identifican los bloques de datos físicos en el disco.  
- Esos bloques se leen para obtener el contenido del archivo.  

---

#### **Ejemplo Práctico: Almacenamiento Físico de un Archivo**  

**Archivo: `informe.txt` (10 KB)**  

- **Paso 1: Organización Lógica**  
  - El archivo `informe.txt` está en la ruta lógica `/home/user/documentos/`.  
  - El sistema de archivos crea un inodo (EXT4) o una entrada en la MFT (NTFS) para registrar su existencia.  

- **Paso 2: Asignación Física**  
  - El archivo se divide en bloques (generalmente de 4 KB).  
  - El sistema asigna tres bloques consecutivos o dispersos en el disco, dependiendo de la disponibilidad:  
    - Bloque 1010 (primera parte del archivo, 4 KB).  
    - Bloque 1011 (segunda parte del archivo, 4 KB).  
    - Bloque 1012 (resto del archivo, 2 KB).  

- **Paso 3: Relación Lógica-Física**  
  - El inodo o la MFT almacena punteros que vinculan el archivo lógico (`informe.txt`) con los bloques físicos (1010, 1011, 1012) del disco.  
  - Cuando el usuario accede al archivo, el sistema traduce la ruta lógica en una serie de lecturas de bloques físicos.  

---

#### **Conclusión**  

La organización lógica de un sistema de archivos proporciona una estructura intuitiva para el usuario, mientras que la organización física asegura que los datos sean almacenados eficientemente en el disco. Herramientas como inodos y la MFT son cruciales para mapear archivos lógicos a ubicaciones físicas en el almacenamiento.

## **Ejercicio 4: Mecanismos de Acceso a los Archivos**

---

#### **1. Definición de los Mecanismos de Acceso**

1. **Acceso Secuencial:**  
   Los datos se leen o escriben en orden, desde el principio hasta el final, recorriendo todos los registros hasta llegar al deseado. Es ideal para leer grandes volúmenes de datos de manera lineal.  

2. **Acceso Directo (Aleatorio):**  
   Permite acceder a cualquier posición específica dentro del archivo sin necesidad de recorrerlo secuencialmente. Es eficiente cuando se necesita acceder a datos específicos rápidamente.  

3. **Acceso Indexado:**  
   Utiliza una estructura adicional (índice) que almacena referencias a las ubicaciones de los datos en el archivo. Permite acceder directamente a un registro mediante la búsqueda en el índice.  

---

#### **2. Pseudocódigos para Cada Mecanismo**

**a) Acceso Secuencial:**  
```python
# Abrir el archivo en modo lectura
archivo = abrir("datos.txt", "r")

# Leer cada línea del archivo secuencialmente
mientras no fin_de_archivo(archivo):
    linea = leer_linea(archivo)
    imprimir(linea)

# Cerrar el archivo
cerrar(archivo)
```

**b) Acceso Directo:**  
```python
# Abrir el archivo en modo lectura
archivo = abrir("datos.txt", "r")

# Saltar a la posición 50 del archivo
posicion = 50
mover_puntero(archivo, posicion)

# Leer datos desde la posición específica
dato = leer_bytes(archivo, 20)  # Leer 20 bytes desde la posición actual
imprimir(dato)

# Cerrar el archivo
cerrar(archivo)
```

**c) Acceso Indexado:**  
```python
# Supongamos que existe un índice con claves y posiciones
indice = {
    "registro1": 0,
    "registro2": 50,
    "registro3": 100
}

# Obtener la posición del registro deseado
registro = "registro2"
posicion = indice[registro]

# Abrir el archivo en modo lectura
archivo = abrir("datos.txt", "r")

# Mover el puntero a la posición especificada en el índice
mover_puntero(archivo, posicion)

# Leer el registro
dato = leer_linea(archivo)
imprimir(dato)

# Cerrar el archivo
cerrar(archivo)
```

---

#### **3. Comparación de Ventajas de Cada Mecanismo**

| **Mecanismo**     | **Ventajas**                                                                                     | **Casos de Uso**                                                                               |
|-------------------|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| **Secuencial**    | - Simple de implementar y eficiente para leer archivos completos.                               | - Procesamiento de registros en orden (ej., logs, archivos CSV).                             |
| **Directo**       | - Acceso rápido a datos específicos.                                                            | - Archivos con registros fijos (ej., bases de datos planas, archivos binarios).              |
| **Indexado**      | - Eficiente para buscar datos cuando hay un índice bien diseñado.                               | - Archivos con búsquedas frecuentes y registros de longitud variable (ej., bases de datos).  |

---

#### **Conclusión**

Cada mecanismo de acceso tiene su ventaja dependiendo del contexto. Para lecturas completas o flujos de datos, el acceso secuencial es óptimo. El acceso directo es ideal para registros predefinidos, mientras que el acceso indexado combina flexibilidad y velocidad en escenarios donde las búsquedas son frecuentes.

## **Ejercicio 5: Modelo Jerárquico y Mecanismos de Recuperación en Caso de Falla**

---

#### **1. Modelo Jerárquico para un Sistema de Archivos**  

A continuación, se presenta un modelo jerárquico con tres niveles:  

![](https://www.profesionalreview.com/wp-content/uploads/2017/02/estructura-sistema-de-archivos-unix.jpg)

En este ejemplo, cada usuario tiene su propio directorio con subcarpetas para organizar documentos, imágenes y música. Otros directorios importantes del sistema incluyen `var` (logs) y `etc` (archivos de configuración).

---

#### **2. Simulación de una Falla y Pasos de Recuperación**  

**Escenario de Falla:**  
El directorio `/home/user1/documentos/` se corrompe debido a una falla en el disco o una eliminación accidental.  

##### **Pasos para Recuperar el Directorio:**  

1. **Identificar la Falla:**  
   - Usar el comando `ls` para verificar si el directorio está accesible.  
   - Revisar el sistema de archivos con `fsck` para detectar sectores dañados o corrupción.  
     ```bash
     sudo fsck /dev/sdX
     ```

2. **Recuperar desde un Respaldo:**  
   - Si existe un respaldo reciente, restaurarlo usando herramientas de backup como `rsync` o desde un software de copia de seguridad (ej., `Deja Dup`, `TimeShift`).  
     ```bash
     rsync -av /backup/home/user1/documentos/ /home/user1/documentos/
     ```

3. **Utilizar Herramientas de Recuperación:**  
   - Si no hay respaldo disponible, usar herramientas de recuperación de datos como:  
     - **`testdisk`**: Recupera particiones y archivos eliminados.  
       ```bash
       sudo testdisk
       ```
     - **`photorec`**: Recupera archivos individuales, especialmente documentos e imágenes.  
       ```bash
       sudo photorec
       ```

4. **Verificar Integridad:**  
   - Después de la recuperación, verificar que los archivos sean accesibles y estén completos.  

---

#### **3. Técnicas de Respaldo para Prevenir Pérdida de Datos**  

**a) Herramientas de Respaldo:**  
1. **`rsync`:** Para copias incrementales en tiempo real.  
   - Ventaja: Solo sincroniza cambios, ahorrando tiempo y espacio.  
     ```bash
     rsync -av --delete /home/user1/ /backup/user1/
     ```
   
2. **`tar`:** Para crear archivos comprimidos de respaldo.  
   - Ventaja: Ideal para archivado y almacenamiento externo.  
     ```bash
     tar -czvf backup_user1.tar.gz /home/user1/
     ```

3. **Software de Respaldo Automatizado:**  
   - **Linux:** `Deja Dup`, `TimeShift`, o configuraciones de RAID para redundancia.  
   - **Windows:** Herramientas como **File History** o software de terceros como **Acronis**.

**b) Técnicas de Respaldo:**  
1. **Regla 3-2-1:**  
   - Mantener **3 copias** de los datos (original + 2 respaldos).  
   - Usar al menos **2 tipos de medios de almacenamiento** (ej., disco duro y nube).  
   - Guardar **1 copia fuera del sitio** (ej., almacenamiento en la nube).  

2. **Versionado:**  
   - Mantener versiones anteriores de los archivos para recuperar estados previos en caso de errores o corrupción.  

---

#### **Conclusión**  
Diseñar un modelo jerárquico claro facilita la organización y recuperación de archivos en caso de fallas. Usar herramientas como `rsync` y estrategias como la regla 3-2-1 asegura que los datos estén protegidos frente a pérdidas o corrupción. La implementación de planes de respaldo regulares es clave para evitar interrupciones críticas.

# Protección y Seguridad

### **Ejercicio 1: Concepto y Objetivos de Protección y Seguridad**

---

#### **1. Definiciones**

- **Protección:**  
  En el contexto de sistemas operativos, la protección se refiere a los mecanismos que controlan el acceso a los recursos del sistema, como memoria, archivos, dispositivos y CPU. Su propósito es evitar el mal uso, la interferencia y los errores accidentales o intencionados que puedan comprometer el funcionamiento del sistema.

- **Seguridad:**  
  La seguridad en sistemas operativos implica garantizar que los datos y recursos estén protegidos frente a accesos no autorizados, robos, modificaciones o ataques. Incluye aspectos como la autenticación de usuarios, la encriptación de datos y la prevención de ataques externos.

---

#### **2. Objetivos Principales de un Sistema de Protección y Seguridad**

1. **Confidencialidad:**  
   Garantizar que la información solo sea accesible para usuarios y procesos autorizados.  
   - **Ejemplo:** La implementación de permisos en archivos, como en Linux con comandos `chmod` para definir quién puede leer, escribir o ejecutar un archivo.

2. **Integridad:**  
   Proteger los datos y recursos frente a modificaciones no autorizadas o accidentes que puedan corromperlos.  
   - **Ejemplo:** Verificar integridad de archivos mediante sumas de verificación (ej., `sha256sum` en Linux).

3. **Disponibilidad:**  
   Asegurar que los recursos estén accesibles y funcionando correctamente para los usuarios autorizados cuando los necesiten.  
   - **Ejemplo:** Implementación de sistemas RAID para garantizar la disponibilidad de datos incluso en caso de fallas de hardware.

4. **Autenticación:**  
   Verificar la identidad de los usuarios o procesos que intentan acceder al sistema.  
   - **Ejemplo:** Uso de contraseñas y autenticación multifactor (MFA) en sistemas operativos modernos.

5. **Auditoría y Registro:**  
   Mantener un registro de las actividades en el sistema para identificar accesos o cambios no autorizados.  
   - **Ejemplo:** Archivos de log en `/var/log` en Linux, que registran actividades como inicios de sesión.

---

#### **3. Ejemplo Práctico**

**Sistema Operativo:** Linux

**Aplicación de los Objetivos:**
1. **Confidencialidad:**  
   Un administrador puede establecer permisos en un archivo utilizando el comando:  
   ```bash
   chmod 600 archivo_secreto.txt
   ```  
   Esto asegura que solo el propietario pueda leer y escribir en el archivo.

2. **Integridad:**  
   Un usuario puede generar una suma de verificación SHA-256 para un archivo crítico y luego compararla para asegurarse de que no fue alterado:  
   ```bash
   sha256sum archivo_crítico.txt
   ```

3. **Disponibilidad:**  
   Configurar un sistema de respaldo automático con `rsync` para garantizar que los datos siempre estén disponibles en caso de una falla:  
   ```bash
   rsync -av /datos_importantes/ /respaldo/
   ```

---

#### **Conclusión**

Los sistemas operativos implementan protección y seguridad para garantizar la correcta gestión y uso de los recursos. La confidencialidad, integridad y disponibilidad son fundamentales para prevenir ataques, evitar fallas y garantizar un funcionamiento seguro y eficiente del sistema. Un enfoque proactivo en seguridad puede evitar pérdidas de datos y mantener la confianza en los sistemas informáticos.

## Ejercicio 2: Clasificación aplicada a la seguridad

#### **Clasificaciones comunes de la seguridad**
Los mecanismos de seguridad de un sistema operativo suelen clasificarse en tres grandes categorías: 

1. **Seguridad física**  
   Se enfoca en la protección de los componentes físicos del sistema (hardware, servidores, dispositivos de almacenamiento, etc.).  
   - **Papel en la protección del sistema operativo**: Previene el acceso no autorizado o la destrucción de los recursos físicos. Sin un hardware seguro, el software no puede operar correctamente.  
   - **Ejemplos prácticos**:
     - **Control de acceso físico**: Cerraduras, credenciales de acceso, sistemas biométricos.
     - **Ambientes controlados**: Centros de datos con monitoreo de temperatura, humedad y sistemas contra incendios.
     - **Videovigilancia**: Cámaras que supervisan los puntos de acceso.

2. **Seguridad lógica**  
   Protege los datos, aplicaciones y sistemas operativos contra accesos indebidos, fallos de software y ataques cibernéticos.  
   - **Papel en la protección del sistema operativo**: Restringe el acceso no autorizado al sistema operativo, asegura la autenticidad de los usuarios, y previene que el sistema sea comprometido a través de vulnerabilidades en el software.  
   - **Ejemplos prácticos**:
     - **Control de acceso lógico**: Políticas de usuarios, contraseñas robustas, autenticación multifactor (MFA).
     - **Sistemas de detección y prevención de intrusos** (IDS/IPS): Detectan comportamientos sospechosos dentro del sistema.
     - **Software antivirus/antimalware**: Protegen contra aplicaciones maliciosas.

3. **Seguridad de red**  
   Se ocupa de proteger las comunicaciones y los datos que viajan entre dispositivos conectados.  
   - **Papel en la protección del sistema operativo**: Evita que las amenazas externas accedan al sistema operativo a través de la red. También protege las transferencias de datos y asegura la comunicación interna entre procesos y servicios.  
   - **Ejemplos prácticos**:
     - **Cortafuegos (firewalls)**: Filtran y monitorean el tráfico de red entrante y saliente.
     - **Redes privadas virtuales (VPN)**: Aseguran las conexiones remotas mediante cifrado.
     - **Protocolo HTTPS y certificados digitales**: Protegen la integridad de los datos en tránsito.

#### **Interacción entre las clasificaciones**
La protección total de un sistema operativo requiere una combinación equilibrada de estas tres clasificaciones. Por ejemplo:
- Un servidor podría estar físicamente protegido por biometría y candados, pero también necesitaría firewalls para prevenir ataques externos y contraseñas seguras para controlar el acceso lógico.

#### **Conclusión**
Integrar estas clasificaciones garantiza una defensa en profundidad (defense-in-depth), mejorando la capacidad de resiliencia del sistema operativo ante posibles ataques o fallos.

## Ejercicio 3: Funciones del sistema de protección

#### **Control del acceso a los recursos**
Un sistema de protección en un entorno multiusuario se encarga de gestionar cómo los usuarios y procesos acceden a los recursos del sistema operativo, garantizando la integridad, confidencialidad y disponibilidad de estos.  
- **Mecanismos utilizados**:
  - **Listas de control de acceso (ACL)**: Especifican los permisos de cada usuario o grupo sobre un recurso.
  - **Matriz de control de acceso**: Relaciona usuarios, recursos y los permisos asociados en una estructura tabular.
  - **Capacidades**: Tokens o certificados que otorgan a un proceso permiso para acceder a un recurso.

#### **Funciones principales del sistema de protección**

1. **Autenticación**  
   Verifica la identidad del usuario o proceso que intenta acceder al sistema.  
   - **Herramientas comunes**:
     - Contraseñas y PIN.
     - Autenticación biométrica (huella digital, reconocimiento facial).
     - Sistemas de autenticación multifactor (MFA).
   - **Ejemplo práctico**: Un usuario debe ingresar su contraseña y un código temporal enviado a su dispositivo móvil para acceder al sistema.

2. **Autorización**  
   Determina qué acciones puede realizar un usuario o proceso después de haber sido autenticado.  
   - **Mecanismos utilizados**:
     - Roles y políticas de acceso basados en necesidades específicas (RBAC: Role-Based Access Control).
     - Definición de permisos de lectura, escritura, ejecución y eliminación.  
   - **Ejemplo práctico**: Un administrador puede instalar software, pero un usuario estándar solo puede acceder a sus propios archivos.

3. **Auditoría**  
   Registra las actividades realizadas por los usuarios y procesos para detectar comportamientos sospechosos, investigar incidentes de seguridad y garantizar el cumplimiento de normativas.  
   - **Herramientas comunes**:
     - Registros de eventos (logs).
     - Sistemas de monitoreo de acceso y alertas.  
   - **Ejemplo práctico**: Un informe que muestra los intentos de acceso fallidos al sistema, incluyendo la hora y el usuario involucrado.

---

#### **Caso práctico: Sistema de protección en acción**

**Escenario**:  
Una empresa utiliza un servidor compartido para alojar aplicaciones y almacenar datos confidenciales. El sistema de protección está diseñado para garantizar que los empleados solo puedan acceder a los recursos relevantes según su rol.

**Acción 1: Inicio de sesión (autenticación)**  
- El usuario "Maria", una empleada de recursos humanos, accede al servidor ingresando su contraseña y verificando su identidad con un código temporal enviado a su correo (MFA).

**Acción 2: Acceso a recursos (autorización)**  
- Una vez autenticada, Maria tiene acceso de solo lectura a los archivos de nómina, pero no puede editar ni eliminar documentos, porque esos permisos están restringidos al gerente del área.

**Acción 3: Supervisión de actividades (auditoría)**  
- El sistema registra las actividades de Maria, como la fecha y hora de acceso, los archivos abiertos, y si se realizaron intentos de acceder a áreas restringidas.

**Resultados**:
- **Autenticación** garantiza que solo Maria pueda usar su cuenta.  
- **Autorización** restringe el acceso a funciones que Maria no necesita.  
- **Auditoría** permite al administrador identificar si Maria intenta realizar acciones fuera de sus permisos.

#### **Conclusión**
Un sistema de protección robusto en un entorno multiusuario asegura que los recursos sean utilizados de manera controlada y segura, minimizando los riesgos de accesos indebidos y cumpliendo con políticas internas y normativas externas.

## Ejercicio 4: Implantación de matrices de acceso

#### **Diseño de una matriz de acceso**

**Supuestos del sistema**:
- **Usuarios**:  
  1. Admin  
  2. Usuario1  
  3. Usuario2  

- **Recursos**:  
  1. Archivo1  
  2. Archivo2  
  3. BaseDatos1  
  4. Impresora  

**Matriz de acceso (ejemplo)**:

| Usuario   | Archivo1      | Archivo2      | BaseDatos1   | Impresora     |
|-----------|---------------|---------------|--------------|---------------|
| **Admin** | Lectura, Escritura, Ejecución | Lectura, Escritura, Ejecución | Lectura, Escritura | Configuración |
| **Usuario1** | Lectura         | Lectura, Escritura | Sin acceso     | Uso           |
| **Usuario2** | Sin acceso      | Lectura         | Lectura         | Uso           |

---

#### **Explicación del uso de la matriz en el sistema operativo**
1. **Asignación de permisos**:  
   - Cada celda de la matriz especifica qué operaciones están permitidas para un usuario sobre un recurso.
   - El sistema operativo consulta esta matriz cada vez que un usuario intenta realizar una acción.

2. **Control de acceso**:  
   - **Cuando un usuario solicita acceso**: El sistema verifica la matriz para comprobar si el permiso necesario está asignado.
   - **Acción denegada**: Si el permiso requerido no está en la matriz, el sistema bloquea la operación y puede registrar el intento como parte de la auditoría.

---

#### **Simulación de un escenario**

**Escenario**:  
- **Acción**: Usuario2 intenta modificar (escribir) en "Archivo2".  
- **Proceso de verificación**:  
  - El sistema consulta la matriz.  
  - Encuentra que Usuario2 solo tiene permiso de "Lectura" sobre "Archivo2".  
  - Por lo tanto, **la solicitud es denegada**.  

**Respuesta del sistema**:  
1. **Mensaje al usuario**:  
   - “Acceso denegado: No tiene permisos de escritura sobre Archivo2”.  
2. **Registro de auditoría**:  
   - El intento de Usuario2 de modificar Archivo2 queda registrado en los logs del sistema, incluyendo la fecha, hora y tipo de operación solicitada.

---

#### **Conclusión**
La matriz de acceso es un mecanismo eficiente para controlar quién puede realizar qué acciones en un sistema. Proporciona un modelo claro y estructurado para gestionar permisos, evitar accesos no autorizados y fortalecer la seguridad general del sistema operativo.

## Ejercicio 5: Protección basada en el lenguaje

#### **Concepto de protección basada en el lenguaje**
La protección basada en el lenguaje utiliza las características intrínsecas de un lenguaje de programación para garantizar la seguridad y el acceso controlado a los recursos del sistema. Los lenguajes con protección integrada suelen proporcionar:
- **Control de acceso a memoria**: Prevención de acceso indebido o corrupción de memoria.
- **Manejo seguro de tipos**: Reducción de errores por uso indebido de tipos de datos.
- **Restricciones en permisos**: Implementación de reglas que limitan las operaciones según el contexto o el nivel de privilegio.

Este enfoque está diseñado para minimizar errores comunes (como desbordamientos de memoria) y dificultar exploits como inyecciones de código malicioso.

---

#### **Ejemplo: Seguridad en memoria con Java y Rust**

1. **Java**  
   - **Gestión automática de memoria**:  
     - Usa un recolector de basura (Garbage Collector) que libera memoria no utilizada, evitando errores como el uso de punteros colgantes (dangling pointers).  
   - **Seguridad en el manejo de tipos**:  
     - El lenguaje aplica verificaciones estrictas de tipos en tiempo de compilación, reduciendo la posibilidad de acceder a recursos de forma indebida.  
   - **Ejemplo práctico**:  
     ```java
     class Example {
         public static void main(String[] args) {
             int[] arr = new int[5];
         
             try {
                 arr[10] = 100; 
             } catch (Exception e) {
                 System.out.println("Error: " + e.getMessage());
             }
         }
     }
     ```

2. **Rust**  
   - **Propiedad y préstamos (Ownership and Borrowing)**:  
     - Rust asegura que cada fragmento de memoria tiene un único propietario, lo que previene condiciones de carrera y accesos indebidos.  
   - **Seguridad en tiempo de compilación**:  
     - Verifica el uso seguro de memoria y evita fugas de esta sin necesidad de un recolector de basura.  
   - **Ejemplo práctico**:  
     ```rust
     fn main() {
         let v = vec![1, 2, 3];
         let x = &v[0]; 
        
         println!("{}", x);
     }
     ```

---

#### **Comparación con otros mecanismos de protección**

| **Aspecto**               | **Protección basada en el lenguaje**  | **Protección del sistema operativo** |
|---------------------------|---------------------------------------|---------------------------------------|
| **Control de acceso**     | Restringe accesos a recursos desde el código fuente. | Usa ACL, permisos de archivos y políticas de acceso. |
| **Gestión de memoria**    | Previene errores como desbordamientos y fugas. | Confía en el hardware (MMU) o supervisión del kernel. |
| **Desempeño**             | Puede añadir sobrecarga en tiempo de compilación o ejecución. | Más eficiente, pero con menor control en el nivel del código. |
| **Flexibilidad**          | Ligado a las capacidades del lenguaje. | Aplica a todo el entorno, independientemente del software. |
| **Detección de errores**  | Identifica errores en tiempo de compilación. | Detecta accesos indebidos en tiempo de ejecución. |

---

#### **Conclusión**
La protección basada en el lenguaje proporciona una seguridad más intrínseca y centrada en el desarrollo, evitando problemas desde la escritura del código. Comparada con los mecanismos del sistema operativo, es más restrictiva en términos de acceso, pero complementa la seguridad general al prevenir errores y vulnerabilidades desde la raíz del software. Lenguajes como Rust y Java son ejemplos de cómo esta filosofía puede mejorar significativamente la robustez de las aplicaciones modernas.

## Ejercicio 6: Validación y amenazas al sistema

#### **Amenazas comunes a un sistema operativo**
1. **Malware**  
   - **Descripción**: Programas maliciosos como virus, gusanos, troyanos y ransomware diseñados para comprometer el sistema, robar información o dañar archivos.  
   - **Ejemplo**: Un troyano disfrazado de software legítimo que permite acceso remoto no autorizado.  
   - **Impacto**: Pérdida de datos, control del sistema y exposición de información confidencial.  

2. **Ataques de fuerza bruta**  
   - **Descripción**: Método en el que un atacante prueba combinaciones de contraseñas hasta encontrar la correcta.  
   - **Ejemplo**: Un atacante utiliza herramientas automatizadas para comprometer cuentas de usuario mediante contraseñas débiles.  
   - **Impacto**: Acceso no autorizado a cuentas críticas.  

3. **Inyección de código**  
   - **Descripción**: Ataques que introducen código malicioso en aplicaciones que interactúan con el sistema operativo.  
   - **Ejemplo**: Inyección SQL en bases de datos conectadas al sistema operativo.  
   - **Impacto**: Compromiso de la integridad de datos y del sistema.  

---

#### **Mecanismos de validación**
1. **Autenticación multifactor (MFA)**  
   - Combina dos o más métodos de autenticación (algo que el usuario sabe, tiene o es) para verificar la identidad del usuario.  
   - **Ejemplo**: Uso de contraseña + código temporal en un dispositivo móvil.  
   - **Prevención**: Dificulta que atacantes accedan a cuentas incluso si obtienen una contraseña.

2. **Control de integridad**  
   - Verifica que los archivos del sistema no han sido alterados.  
   - **Ejemplo**: Uso de hashes criptográficos para comparar el estado actual de un archivo con su estado esperado.  
   - **Prevención**: Detecta modificaciones no autorizadas causadas por malware o accesos indebidos.

3. **Limitación de intentos de inicio de sesión**  
   - Restringe el número de intentos fallidos para prevenir ataques de fuerza bruta.  
   - **Ejemplo**: Bloqueo temporal de la cuenta tras 5 intentos fallidos.  
   - **Prevención**: Reduce la efectividad de herramientas automatizadas de ataque.

---

#### **Esquema de validación para un sistema operativo con múltiples usuarios**

**Diseño del esquema**:

1. **Autenticación inicial**:  
   - **Método principal**: Contraseña robusta con políticas obligatorias (mínimo 12 caracteres, combinación de letras, números y símbolos).  
   - **Método adicional**: Autenticación multifactor (MFA) mediante aplicaciones como Google Authenticator o dispositivos biométricos.

2. **Control de acceso continuo**:  
   - Uso de tokens de sesión con tiempos de expiración configurables.  
   - Validación de roles y permisos según el principio de privilegio mínimo.

3. **Monitoreo de integridad**:  
   - Implementación de herramientas como Tripwire para verificar la integridad de archivos críticos del sistema.  
   - Auditorías periódicas del sistema para identificar accesos no autorizados.

4. **Bloqueo y alerta en tiempo real**:  
   - Limitar los intentos fallidos de acceso a 5.  
   - Generar alertas automáticas al administrador del sistema en caso de actividades sospechosas, como múltiples intentos fallidos o accesos desde ubicaciones inusuales.

**Flujo del esquema en acción**:  
1. Un usuario intenta acceder al sistema.  
2. Ingresa su contraseña, que es validada.  
3. Completa la autenticación multifactor con un código enviado a su dispositivo.  
4. Accede únicamente a los recursos asignados según su rol (verificado por la matriz de control de acceso).  
5. Cualquier modificación no autorizada de un archivo crítico genera una alerta y el sistema revierte la acción.

---

#### **Conclusión**
Los sistemas operativos enfrentan múltiples amenazas, pero el uso de mecanismos de validación sólidos como MFA, control de integridad y políticas de acceso pueden reducir significativamente los riesgos. Diseñar un esquema de validación adaptable a usuarios con diferentes niveles de acceso asegura un entorno más seguro y confiable.

## Ejercicio 7: Cifrado

#### **Conceptos de cifrado**
1. **Cifrado simétrico**  
   - Utiliza la misma clave para cifrar y descifrar la información.  
   - **Ventajas**: Rápido y eficiente para grandes cantidades de datos.  
   - **Desventajas**: La clave debe ser compartida de manera segura, lo cual puede ser complicado en entornos abiertos.  
   - **Ejemplo común**: Algoritmos como AES (Advanced Encryption Standard).

2. **Cifrado asimétrico**  
   - Usa un par de claves: una clave pública para cifrar y una clave privada para descifrar.  
   - **Ventajas**: No requiere compartir la clave privada, lo que mejora la seguridad en la transmisión.  
   - **Desventajas**: Es más lento que el cifrado simétrico.  
   - **Ejemplo común**: Algoritmos como RSA (Rivest–Shamir–Adleman).  

---

#### **Ejemplo práctico: Cifrado aplicado en sistemas operativos**

1. **Cifrado simétrico**  
   - Aplicación en **BitLocker** (Windows):  
     - BitLocker utiliza AES para cifrar todo el disco duro, asegurando que solo los usuarios autorizados con la clave puedan acceder a los datos.

2. **Cifrado asimétrico**  
   - Aplicación en **SSH (Secure Shell)**:  
     - Cuando se establece una conexión SSH, el cliente utiliza la clave pública del servidor para cifrar un mensaje de inicio, y el servidor lo descifra con su clave privada.

---

#### **Simulación de cifrado y descifrado**

Supongamos que queremos cifrar un archivo usando **cifrado simétrico (AES)**. Aquí se muestra un ejemplo básico en Python utilizando la biblioteca `cryptography`.

```python
from cryptography.fernet import Fernet

# Generar una clave simétrica
clave = Fernet.generate_key()
cifrador = Fernet(clave)

# Contenido del archivo
contenido_original = b"Este es el contenido del archivo a proteger."

# Cifrado
contenido_cifrado = cifrador.encrypt(contenido_original)
print("Contenido cifrado:", contenido_cifrado)

# Descifrado
contenido_descifrado = cifrador.decrypt(contenido_cifrado)
print("Contenido descifrado:", contenido_descifrado.decode())
```

**Proceso explicado**:
1. Se genera una clave única para cifrado y descifrado.
2. Los datos originales son cifrados usando la clave.
3. El contenido cifrado se almacena o transmite.
4. El receptor, que posee la misma clave, descifra los datos para obtener el contenido original.

---

#### **Simulación de cifrado asimétrico**

En un esquema de cifrado asimétrico con RSA:
1. El servidor genera un par de claves pública/privada.
2. Un cliente usa la clave pública para cifrar un mensaje.
3. El servidor usa su clave privada para descifrar el mensaje.

Ejemplo básico en Python con la biblioteca `cryptography`:

```python
from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives import hashes

# Generar par de claves
private_key = rsa.generate_private_key(public_exponent=65537, key_size=2048)
public_key = private_key.public_key()

# Mensaje original
mensaje = b"Mensaje protegido con RSA."

# Cifrado
mensaje_cifrado = public_key.encrypt(
    mensaje,
    padding.OAEP(
        mgf=padding.MGF1(algorithm=hashes.SHA256()),
        algorithm=hashes.SHA256(),
        label=None
    )
)
print("Mensaje cifrado:", mensaje_cifrado)

# Descifrado
mensaje_descifrado = private_key.decrypt(
    mensaje_cifrado,
    padding.OAEP(
        mgf=padding.MGF1(algorithm=hashes.SHA256()),
        algorithm=hashes.SHA256(),
        label=None
    )
)
print("Mensaje descifrado:", mensaje_descifrado.decode())
```

---

#### **Conclusión**
Los mecanismos de cifrado, tanto simétricos como asimétricos, son esenciales para proteger la información en sistemas operativos. Mientras el cifrado simétrico es ideal para grandes volúmenes de datos, el cifrado asimétrico es preferible para transmitir claves y proteger comunicaciones. Estos métodos son ampliamente utilizados en herramientas como BitLocker, SSH y otros sistemas de seguridad.

# FIN
**Bonitas fiestas profe y que se la pase chido y por favor pongame buena calificacion**
![TIO CHILL DE COJONES](https://cloudfront-us-east-1.images.arcpublishing.com/infobae/YKBCDL3UOZAGFJNF3XHGDLPGHU.jpg)