# Tarea 1: Simulador EloTelTag 

Este proyecto simula el movimiento de dispositivos localizadores (EloTelTags) basándose en archivos de configuración y movimiento. Se desarrolla de forma iterativa e incremental.

## Instrucciones de Uso

Para facilitar el desarrollo y cumplimiento de las normas de entrega, se utiliza un `Makefile` en cada etapa del proyecto.

### 1. Compilación
Para compilar todas las clases del proyecto (`.java`) y generar los archivos ejecutables (`.class`):
make

Para correr la simulación utilizando los archivos de prueba predeterminados (config.txt y move.txt):
make run

Para borrar los archivos binarios generados y mantener la carpeta limpia:
make clean
### 2. Ejecución

### 3. Archivos
Se presentan carpetas la cuales contienen los archivos correspondientes a cada etapa:
#### Stage1:
- T1Stage1.java: contiene la función main la cual genera un archivo de salida con la posición de cada tag dada una entrada.
- EloTelTag.java:
- Territory.java:
- config.txt y move.txt: archivos de entrada
- output.csv: archivo de salida
- Makefile
  
#### Stage2:
- T1Stage2.java: contiene la función main del Stage1 y ahora genera un archivo de salida con la posición reportada por celulares para cada tag.
- EloTelTag.java
- Territory.java
- Celullar.java
- Equipo.java
- ETNube.java
- config.txt y move.txt: archivos de entrada
- output.csv: archivo de salida
- Makefile

#### Stage3: 
- T1Stage2.java: contiene la función main con lo realizado en etapas anteriores y se añade la visualización por FindMy.
- EloTelTag.java
- Territory.java
- Celullar.java
- Tablet.java
- Viewer.java
- Equipo.java
- ETNube.java
- config.txt y move.txt: archivos de entrada
- output.csv: archivo de salida
- Makefile

#### Stage4: 
- SimuladorTest.java: contiene la función main
- EloTelTag.java
- Territory.java
- Celullar.java
- Tablet.java
- Viewer.java
- Equipo.java
- ETNube.java
- config.txt y move.txt: archivos de entrada
- output.csv: archivo de salida
- Makefile

