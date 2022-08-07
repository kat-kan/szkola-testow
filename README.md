# szkola-testow

## Built with
Java 17, JUnit 5, AssertJ, Mockito, Maven


## Description

This repository documents code written by me during unit testing course ([course](https://szkolatestow.online/) by Aleksandra Kunysz). 

My motivation:
* learn about developers' perspective to tests
* get to know about effective ways to use unit testing tools (which are also used in test automation)

## Guidelines for running tests with Maven
To run all tests, use following command :
`mvn clean test`

To run tests from specific module use command

`mvn clean '-Dtest=moduleX.*Test' test` where X = module number, for example `mvn clean '-Dtest=module1.*Test' test`

## Course topics & homeworks
### Module 1
Module 1 covers basics of unit testing - why do we even want to write unit tests and how we should think about them. It shows how to write first unit test (with and without JUnit).

*Homework* 

The task was to create basic string calculator (based on the pseudocode given) and write few unit tests. 


### Module 2
Module 2 is all about helpful tools - JUnit, AssertJ, Mockito, IDE shortcuts. It explains what is the goal of stubbing/mocking.

*Homework*
- Part 1: The task was to create VatService app (based on the pseudocode given) and write unit tests. 
- Part 2 : Provide VAT with VatProvider interface and stub it in the tests. 
- Part 3 (for volunteers only) : Add logger with different levels to VatService. Adjust tests so they use only mocks. 


### Module 3
Module 3 shows concept of TDD, BDD, ATDD (focuses mostly on TDD). It mentions some practices to polish the programming skills - katas, pair programming, coding dojo.

*Homework*
- Part 1 : Watch a video with TDD solution to a kata then try to solve it on your own. Commit after each TDD cycle. Delete your solution and try again :) 
- Part 2 (for volunteers only) : Try to solve another kata using TDD. I've decided to solve [Employee Report kata](https://codingdojo.org/kata/Employee-Report/)


### Module 4
Module 4 points out newbies mistakes and some good practices.

*Homework*

The task was to get back to all previous homeworks and refactor tests trying to avoid newbies mistakes and introduce good practices.
