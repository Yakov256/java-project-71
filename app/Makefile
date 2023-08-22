run-dist:
	@./build/install/app/bin/app testFile1.json testFile2.json

clean:
	./gradlew clean

lint:
	./gradlew checkstyleMain

test:
	@./gradlew test

build:
	@./gradlew clean build

report:
	@./gradlew jacocoTestReport

.PHONY: build