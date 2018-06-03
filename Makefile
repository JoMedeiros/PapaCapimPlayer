JFLAGS = -g
JC = javac
SRC_PATH=src
.SUFFIXES: .java .class
#.java.class:
all:
	$(JC) -cp $(SRC_PATH) $(JFLAGS) $(SRC_PATH)/Main.java

#CLASSES = Player.java \
        Main.java 

default: all

#classes: $(CLASSES:.java=.class)

run: all
	java -cp $(SRC_PATH) Main

clean:
	$(RM) *.class
