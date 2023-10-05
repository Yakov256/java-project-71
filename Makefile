run-dist:
	@./app/build/install/app/bin/app testfile1.json testfile2.json -f plain

help:
	@./app/build/install/app/bin/app -h

clean:
	./app/gradlew clean

install:
	./app/gradlew clean install

lint:
	./app/gradlew checkstyleMain checkstyleTest

test:
	./app/gradlew test

build:
	./app/gradlew clean build

report:
	./app/gradlew jacocoTestReport

.PHONY: build
