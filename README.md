# Money Magic

Write a program that keeps a record of payments. Each payment includes a currency and an amount. Think about these payments in the same way, you have them on your bank account. Data should be kept in memory (please don’t introduce any database engines).

## Author

Petr Kobelka [info@petrkobelka.cz]

https://github.com/PetrKobelka/money-magic

## Used technology

* JAVA 1.8
* Maven
* [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)

## How to run

Run by this command in this order

```bash
mvn clean compile exec:java
```

or to run code with default values stored in text file. Replace example path as your real path.

```bash
mvn clean compile exec:java  -Dexec.args="--file=src/main/resources/data.txt"
```
* file must be encoded in UTF-8
* as parameter there can be relative path or absolute path

## Usable commands

* ```quit``` - shut down running application
* insert currency code and value to add some to balance like in examples below
  * EUR 14
  * EUR -78.7
  * CZK 12500

## Init file

Must be in specific format. All rules below are also applied on manually inserted values during application running

* every line start with currency code in uppercase format OK (CZK, EUR), NOT OK (czk, UsD)
* amount must be without any delimiter, only decimal dot is allowed OK (25, -17.75) NOT OK (17 770,00 or 75,98 or -,74)
* currency code and amount must be separated with space (or spaces)

## Possible extensions

* load multiple init files
* load file in runnig of app
* currency code can be case insensitive
* change time period for scheduled output
