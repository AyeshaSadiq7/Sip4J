JOMPS=euler/Tunnel.jomp \
	montecarlo/AppDemo.jomp \
	raytracer/RayTracer.jomp

# The JOMP compiler
JOMP=java jomp.compiler.Jomp

# Default target
all: ${JOMPS:.jomp=.java}
	javac *.java

%.java: %.jomp
	${JOMP} $*

# Removes all class files
clean:
	rm -f `find . -name \*.class`

# Forces JOMP to process all the necessary files
ultraclean: clean
	rm -f ${JOMPS:.jomp=.java}
