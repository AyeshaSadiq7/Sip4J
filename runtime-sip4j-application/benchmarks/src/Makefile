JOMPS=crypt/IDEATest.jomp \
	lufact/Linpack.jomp \
	series/SeriesTest.jomp \
	sor/SOR.jomp \
	sparsematmult/SparseMatmult.jomp

# The JOMP compiler
JOMP=java jomp.compiler.Jomp

# default target
all: ${JOMPS:.jomp=.java}
	javac *.java

%.java: %.jomp
	${JOMP} $*

# removes all class files
clean:
	rm -f `find . -name \*.class`

# also forces JOMP to process all the necessary files
ultraclean: clean
	rm -f ${JOMPS:.jomp=.java}
