EventManager: Users.java
	javac *.java

clean:
	rm -f *.class

clearData:
	rm -f *.ser

run: 
	java Users
