# Variables
JC = javac
JVM = java
MAIN = T1Stage1
CONFIG = config.txt
MOVE = move.txt

# Regla por defecto: compila todo
all:
	$(JC) *.java

# Regla para ejecutar (rótulo "run" exigido por la tarea)
run: all
	$(JVM) $(MAIN) $(CONFIG) $(MOVE)

# Regla para limpiar los .class (rótulo "clean" exigido)
clean:
	rm -f *.class
