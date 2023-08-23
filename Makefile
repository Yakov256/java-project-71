run-dist:
	@/build/install/app/bin/app

clean:
	./gradlew clean

install:
	./gradlew clean install

lint:
	./gradlew checkstyleMain

test:
	./gradlew test

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

.PHONY: build