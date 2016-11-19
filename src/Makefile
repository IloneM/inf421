.PHONY: all clean test

all: Main.class

Main.class: $(wildcard *.java)
	javac -Xlint *.java

clean:
	rm -f *~ *.class

test: all
	time java -ea -Xmx2G Main

