Data Quality Automation Framework – BDD (Java, MySQL)

This repository demonstrates a simple data quality automation framework built using Java, Cucumber (BDD), and MySQL to validate common ETL data validation scenarios.

Overview

This repository contains a lightweight automation framework created to validate data quality across a simple ETL pipeline.

The aim of this exercise is to demonstrate how common data validation checks can be automated using a Behaviour-Driven Development (BDD) approach rather than relying on manual SQL validation.

The framework is intentionally designed to be simple, modular, and easy to extend so that additional validation rules or new data pipelines can be added with minimal effort.

Technology Stack

The framework has been implemented using the following tools:

Java

Maven

Cucumber (BDD)

JUnit

MySQL

JDBC

The structure follows a modular approach where configuration, reusable utilities, and validation logic are separated from the BDD scenarios.

Framework Design

The project is organised into a few simple layers.

Configuration

Handles environment settings and database connectivity.

Project Structure
data-bdd-framework
│
├── config
│   └── config.properties
│
├── src/main/java
│   ├── config
│   │   └── DBConnection.java
│   │
│   ├── utils
│   │   ├── ConfigReader.java
│   │   └── QueryHelper.java
│   │
│   └── validation
│       └── DataValidation.java
│
├── src/test/java
│   ├── runner
│   │   └── TestRunner.java
│   │
│   └── stepdefinitions
│       └── DataQualitySteps.java
│
├── src/test/resources
│   └── features
│       └── data_validation.feature
Data Quality Checks Implemented

The framework demonstrates several common ETL validation checks including:

Record count validation between source and target tables

Null value validation on key columns

Duplicate record detection

Field-level data comparison

Email/data format validation

Schema comparison between source and target tables

These checks represent typical validations performed during ETL testing or data migration projects.

Database Setup

Create the database and tables:

CREATE DATABASE etltest;
USE etltest;

CREATE TABLE source_customers (
id INT,
name VARCHAR(50),
email VARCHAR(100),
balance INT
);

CREATE TABLE target_customers (
id INT,
name VARCHAR(50),
email VARCHAR(100),
balance INT
);
Insert Sample Data
INSERT INTO source_customers VALUES
(1,'Venu','venu@etltest.com',1000),
(2,'Akki','akki@etltest.com',2000),
(3,'Pav','pav@etltest.com',1500),
(4,'Sri','sri@etltest.com',2500);

INSERT INTO target_customers
SELECT * FROM source_customers;
Running the Tests

Run the test runner from IntelliJ:
TestRunner.java
Or
execute using Maven:
mvn test

Test Report

After execution, a Cucumber HTML report will be generated:

target/cucumber-report.html

This report shows the execution status of all data validation scenarios.