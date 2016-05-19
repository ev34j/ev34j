javadocs:
	rm -rf ./etc/javadocs/output
	mkdir ./etc/javadocs/output
	javadoc @etc/javadocs/options @etc/javadocs/packages

build:
	mvn clean package

scp:
	scp ev34j-mindstorm-examples/target/ev3app-jar-with-dependencies.jar robot@ev3dev:/home/robot


debug:
	# Debug jar on EV3
	ssh robot@ev3dev java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -jar ev3app-jar-with-dependencies.jar
