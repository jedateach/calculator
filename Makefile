all:
	javac -d bin src/calculator/Calculator.java; \
	java -classpath ./bin/ calculator/Calculator