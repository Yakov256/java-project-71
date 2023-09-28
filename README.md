### Hexlet tests and linter status:
[![Actions Status](https://github.com/Yakov256/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/Yakov256/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/17c5ee080de81c82696a/maintainability)](https://codeclimate.com/github/Yakov256/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/17c5ee080de81c82696a/test_coverage)](https://codeclimate.com/github/Yakov256/java-project-71/test_coverage)

## Описание
Вычислитель отличий – программа, определяющая разницу между двумя структурами данных. Это популярная задача, для решения которой существует множество онлайн-сервисов, например: http://www.jsondiff.com/. Подобный механизм используется при выводе тестов или при автоматическом отслеживании изменении в конфигурационных файлах.

Возможности утилиты:
    Поддержка разных входных форматов: yaml и json
    Генерация отчета в виде plain text, stylish и json

## Пример использования:

# формат plain
./app --format plain path/to/file.yml another/path/file.json

Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed

# формат stylish
./app filepath1.json filepath2.json

{
+ follow: false
+ numbers: [1, 2, 3]
  setting1: Value 1
- setting2: 200
- setting3: true
+ setting3: {key=value}
+ setting4: blah blah
  }


## Asciinemas
[Step 5 - Comparison of flat files (JSON)](https://asciinema.org/a/HnPUad2apHyU8xjeMZ5AW4tpB)  
[Step 7 - Comparison of flat files (yaml)](https://asciinema.org/a/srguS7bJtSx4H0WI08vWNI8tM)  
