#  Dispositivos de Entrada y Salida en Linux Mint

## Actividad 1: Listar dispositivos conectados

### Observaciones de comandos:
- **lsblk**: Muestra los dispositivos de bloque como discos duros y particiones. Observé discos y particiones, incluyendo nombres como `sda`, `sdb`, y particiones como `sda1`.
- **lsusb**: Enumeró dispositivos conectados por USB, como mi teclado y mouse USB.
- **lspci**: Mostró dispositivos conectados al bus PCI, como la tarjeta de red y la tarjeta gráfica.
- **dmesg | grep usb**: Muestra un log más detallado sobre dispositivos USB, incluyendo mensajes relacionados con la conexión o desconexión de dispositivos.

### Respuestas:
1. En **lsblk** se muestran dispositivos de almacenamiento como discos duros y particiones.
2. La diferencia entre **lsusb** y **lspci** es que `lsusb` se enfoca en dispositivos conectados a los puertos USB, mientras que `lspci` lista todos los dispositivos conectados al bus PCI, como tarjetas de red o sonido.
3. **dmesg | grep usb** proporciona información del kernel sobre la conexión, desconexión, o problemas de los dispositivos USB.

---

## Actividad 2: Verificar dispositivos de almacenamiento

### Observaciones de comandos:
- **fdisk -l**: Listó discos duros y particiones con información detallada sobre tamaño y tipo de partición.
- **blkid**: Mostró los UUID y tipos de sistemas de archivos, como `ext4` o `NTFS`.
- **df -h**: Detalló los sistemas de archivos montados y el espacio disponible.

### Respuestas:
1. Los dispositivos de almacenamiento conectados son un disco duro principal (`/dev/sda`) y una unidad USB (`/dev/sdb`).
2. Actualmente están montadas las particiones `/dev/sda1` como raíz del sistema (`/`) y `/dev/sdb1` como almacenamiento externo (`/media/usb`).
3. Los sistemas de archivos utilizados son `ext4` para las particiones internas y `FAT32` para el USB.

---

## Actividad 3: Explorar dispositivos de entrada

### Observaciones de comandos:
- **cat /proc/bus/input/devices**: Listó todos los dispositivos de entrada como teclado, mouse y un gamepad.
- **evtest**: Capturó eventos del teclado y mouse, mostrando códigos de teclas presionadas y movimientos del mouse.

### Respuestas:
1. El teclado genera eventos de presión y liberación de teclas. El mouse genera eventos de movimiento y clics.
2. Los dispositivos en `/proc/bus/input/devices` se identifican con nombres, controladores y una descripción como "keyboard" o "mouse".

---

## Actividad 4: Examinar dispositivos de salida

### Observaciones de comandos:
- **xrandr**: Detectó una pantalla conectada con resolución de `1920x1080`.
- **aplay -l**: Mostró dos tarjetas de sonido disponibles (tarjeta integrada y una tarjeta externa USB).
- **lsof /dev/snd/***: Indicó que el proceso `pulseaudio` estaba utilizando la tarjeta de sonido.

### Respuestas:
1. Hay una salida de video disponible, la pantalla principal con resolución `1920x1080`.
2. Se detectaron dos dispositivos de sonido: la tarjeta integrada y una tarjeta USB.
3. El proceso `pulseaudio` está utilizando la tarjeta de sonido.

---

## Actividad 5: Crear un script de resumen

### Observaciones:
- Al ejecutar el script `dispositivos.sh`, recopiló toda la información de los dispositivos conectados en un solo lugar.
- Modifiqué el script para guardar la salida en `resumendispositivos.txt` usando `> resumendispositivos.txt`.

### Respuestas:
1. Usar un script tiene la ventaja de automatizar tareas repetitivas y ahorrar tiempo al recopilar información en un solo paso.
2. Para personalizar el script, añadiría más comandos específicos para dispositivos de interés, como `smartctl` para verificar el estado de discos o `ip link` para listar interfaces de red.

---

## Actividad 6: Reflexión y discusión

### Respuestas:
1. El comando más útil fue **lsblk**, porque permite identificar rápidamente los discos y particiones conectadas.
2. Es muy importante conocer los dispositivos conectados al sistema para diagnosticar problemas, administrar recursos, y garantizar que todo funcione correctamente.
3. Estos conocimientos son esenciales para la administración de sistemas, ya que permiten configurar, solucionar problemas y optimizar el hardware disponible.
