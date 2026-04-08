# Tarea 1: Simulador EloTelTag - Etapa 1

Este proyecto simula el movimiento de dispositivos localizadores (EloTelTags) basándose en archivos de configuración y movimiento. Se desarrolla de forma iterativa e incremental.

## Integrantes
* [Tu Nombre]

## Requerimientos
* Java JDK 11 o superior.
* Herramienta `make` instalada.

## Instrucciones de Uso

Para facilitar el desarrollo y cumplimiento de las normas de entrega, se utiliza un `Makefile`.

### 1. Compilación
Para compilar todas las clases del proyecto (`.java`) y generar los archivos ejecutables (`.class`):
```bash
make

Ejecución
Para correr la simulación utilizando los archivos de prueba predeterminados (config.txt y move.txt):

Bash

make run

Limpieza
Para borrar los archivos binarios generados y mantener la carpeta limpia:

Bash

make clean

El Makefile es un script que automatiza tareas repetitivas. Se compone de:

Variables: Definen el compilador (JC = javac) y el ejecutable de la máquina virtual (JVM = java).

Reglas (Targets):

all: Es la regla por defecto. Asegura que todos los archivos fuente estén actualizados.


run: Ejecuta la clase principal pasando los argumentos de archivo requeridos por la tarea.

clean: Mantiene el directorio de trabajo ordenado, eliminando archivos temporales de compilación.
