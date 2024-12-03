# Bibliotecas para administrar entradas y salidas (cámaras, escáneres, etc.) en C, Python y Java

## 1. **C:**

### a) **OpenCV**
- **Descripción:** OpenCV es una biblioteca de código abierto muy popular para visión por computadora. Permite interactuar con cámaras, procesar imágenes y realizar tareas de visión computacional.
- **Instalación:** `sudo apt install libopencv-dev` (Linux)
- **Sitio web:** [https://opencv.org/](https://opencv.org/)

### b) **libusb**
- **Descripción:** Biblioteca para interactuar con dispositivos USB en sistemas Unix, Linux y Windows. Es útil para controlar dispositivos como escáneres y otros periféricos conectados por USB.
- **Instalación:** Se puede instalar mediante gestores de paquetes como `apt` o `brew`.
- **Sitio web:** [https://libusb.info/](https://libusb.info/)

### c) **Video4Linux (V4L2)**
- **Descripción:** Interfaz de usuario para interactuar con dispositivos de captura de video, como cámaras, en sistemas Linux.
- **Instalación:** V4L2 ya está integrado en el núcleo de Linux.
- **Sitio web:** [https://www.kernel.org/doc/html/latest/media/v4l-drivers.html](https://www.kernel.org/doc/html/latest/media/v4l-drivers.html)

---

## 2. **Python:**

### a) **OpenCV (Python bindings)**
- **Descripción:** OpenCV también tiene enlaces (bindings) para Python, lo que permite capturar imágenes y videos desde cámaras y otros dispositivos de captura.
- **Instalación:** `pip install opencv-python`
- **Sitio web:** [https://opencv.org/](https://opencv.org/)

### b) **PyUSB**
- **Descripción:** PyUSB es una biblioteca para interactuar con dispositivos USB, útil para administrar escáneres y otros dispositivos conectados por USB.
- **Instalación:** `pip install pyusb`
- **Sitio web:** [https://pyusb.readthedocs.io/](https://pyusb.readthedocs.io/)

### c) **Pillow**
- **Descripción:** Biblioteca para manipular imágenes una vez capturadas. No está orientada a dispositivos de captura, pero es útil para el procesamiento de imágenes.
- **Instalación:** `pip install pillow`
- **Sitio web:** [https://pillow.readthedocs.io/](https://pillow.readthedocs.io/)

### d) **PySerial**
- **Descripción:** Para interactuar con dispositivos conectados a través de puertos seriales (como algunos escáneres), PySerial es útil.
- **Instalación:** `pip install pyserial`
- **Sitio web:** [https://pythonhosted.org/pyserial/](https://pythonhosted.org/pyserial/)

---

## 3. **Java:**

### a) **JavaCV (Java bindings for OpenCV)**
- **Descripción:** JavaCV proporciona enlaces de Java para OpenCV y otros proyectos relacionados con visión computacional, permitiendo la captura de imágenes desde cámaras y el procesamiento de video.
- **Instalación:** Se puede instalar mediante Maven o descargar desde el sitio web.
- **Sitio web:** [https://github.com/bytedeco/javacv](https://github.com/bytedeco/javacv)

### b) **Java Media Framework (JMF)**
- **Descripción:** JMF es una API de Java para trabajar con medios de comunicación (audio, video, etc.). Aunque está algo desactualizada, aún se puede usar para interactuar con dispositivos multimedia como cámaras.
- **Instalación:** Se puede descargar desde [Oracle](https://www.oracle.com/java/technologies/java-media-framework.html).

### c) **USB API for Java**
- **Descripción:** Existen varias bibliotecas Java para interactuar con dispositivos USB, como la API **javax.usb**, que permite controlar dispositivos conectados por USB.
- **Instalación:** Se puede instalar con Maven o descargar desde sitios de la comunidad Java.
- **Sitio web:** [https://github.com/usb4java/usb4java](https://github.com/usb4java/usb4java)

### d) **TWAIN for Java (TWAIN Scanners)**
- **Descripción:** Biblioteca para trabajar con escáneres, utilizando el protocolo TWAIN, común en dispositivos de escaneo.
- **Sitio web:** [https://github.com/twain/twain-32](https://github.com/twain/twain-32)

---

## Resumen:

- **C:** **OpenCV**, **libusb**, **V4L2**
- **Python:** **OpenCV (bindings)**, **PyUSB**, **Pillow**, **PySerial**
- **Java:** **JavaCV**, **Java Media Framework**, **javax.usb**, **TWAIN for Java**

