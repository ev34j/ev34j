javadocs:
	rm -rf ./etc/javadocs/output
	mkdir ./etc/javadocs/output
	javadoc @etc/javadocs/options @etc/javadocs/packages
