run-dist:
	make -C app run-dist

clean:
	make -C app clean

install:
	make -C app install

lint:
	make -C app lint

test:
	make -C app test

build:
	make -C app clean build

report:
	make -C app report

.PHONY: build