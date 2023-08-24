run-dist:
	make -C app run-dist

clean:
	./gradlew clean

install:
	./gradlew clean install

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

.PHONY: build