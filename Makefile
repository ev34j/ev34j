EV3_NAME = ev3dev
#EV3_NAME = brickpi
EV3_PASSWORD = maker

javadocs:
	rm -rf ./etc/javadocs/output
	mkdir ./etc/javadocs/output
	javadoc @etc/javadocs/options @etc/javadocs/packages

post-javadocs: javadocs
	rm -rf ../ev34j-docs/*
	mv ./etc/javadocs/output/* ../ev34j-docs/

build:
	mvn clean package

scp:
	sshpass -p $(EV3_PASSWORD) scp ev34j-mindstorms-tests/target/ev3app-jar-with-dependencies.jar robot@$(EV3_NAME):/home/robot

run:
	sshpass -p $(EV3_PASSWORD) ssh robot@$(EV3_NAME) java -jar ev3app-jar-with-dependencies.jar

debug:
	# Debug jar on EV3
	sshpass -p $(EV3_PASSWORD) ssh robot@$(EV3_NAME) java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -jar ev3app-jar-with-dependencies.jar
