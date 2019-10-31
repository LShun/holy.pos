# HOLY.POS - Fast Food POS System

Travis CI Build Status: [![Build Status](https://travis-ci.com/LShun/holy.pos.svg?branch=master)](https://travis-ci.com/LShun/holy.pos)

## Introduction

**A fast food point of sales system developed in Java**. Supports basic product management, billing, reporting function.

## Project Objective

- To understand how a sales business efficiency can be improved with sophisticated software handling transactions.
- To put what we have learned to practice, expose to real world applications.
- To understand how collaboration is done in real world for programming work.

## Design Specifications

- The software consists of 4 main components that are central to a sales business needs: 
  - **Product Management**: Add, modify, delete products
  - **Staff Management**
    - Check-in/Check-out function
    - Staff Details Management
  - **Billing**
    - Shopping cart function with in-memory persistence
    - Payment calculation function
  - **Reporting**
    - Daily Sales Report
    - Monthly Sales Report
    - Product Performance Report
    - Staff Performance Report
    - Tax Report

- Aside from the reporting function, which utilizes Java Swing API, the rest are fully in command-line interface.
- The data stored is **NOT persistent**.

- An **administrator password is hardcoded** into the software. Additional users may be created as per required.

### Additional Details

- The source code utilizes [Maven](maven.apache.org/) software management tool to efficiently manage dependencies. However, in case Maven isn't available (such as in JCreator), a folder named `dependencies` is included that contains all dependencies.
- [ASCIITable](mvnrepository.com/artifact/de.vandermeer/asciitable) by [de.vandermeer](mvnrepository.com/artifact/de.vandermeer) is used to display data in a flex

