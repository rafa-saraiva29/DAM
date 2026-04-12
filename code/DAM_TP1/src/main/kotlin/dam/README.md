# Assignment 1 — Hello Kotlin. Hello Android World

Course: Desenvolvimento de Aplicações Móveis

Student(s): Rafael Saraiva 47484

Date: 15th March 2026

Repository URL: ___

## 1. Introduction

This assignment aims to introduce the fundamental concepts of Kotlin and Android development. Initially, basic Kotlin concepts are explored, including data types, control flow, and functional programming. Subsequently, simple Android applications are developed to consolidate the acquired knowledge.

Additionally, the assignment includes the implementation of an object-oriented system — a virtual library — with the purpose of reinforcing concepts such as inheritance, encapsulation, and polymorphism.

## 2. System Overview

The developed system is divided into two main parts:

Kotlin Console Exercises:
Generation of arrays with perfect squares.
Interactive calculator with arithmetic and boolean operations.
Simulation of a bouncing ball using sequences.
Virtual Library System:
Management of digital and physical books.
Operations such as borrowing, returning, and searching books.
Object-oriented representation using base and derived classes.

Main use cases include:

Performing calculations and logical operations.
Mathematical simulation using sequences.
Managing a book catalog.

## 3. Architecture and Design

The project follows a package-based organization:

dam.exer_1, dam.exer_2, dam.exer_3 — independent exercises
dam.virtual_library — main object-oriented system

### Design Decisions

* Package separation: improves organization and maintainability.

* Functional programming: use of map and generateSequence.

* Object-oriented design:
Abstract class Book, subclasses DigitalBook and PhysicalBook

* Encapsulation:
Custom getter for time classification (Classic, Modern, Contemporary), setter with validation for available copies
## 4. Implementation
### Exercise 1 — Perfect Squares Arrays

Three different approaches were implemented:

IntArray,
range + map,
Array


### Exercise 2 — Calculator

An interactive calculator was implemented with:

* when expression for operation selection

* Exception handling (try/catch)

* Supported operations:
Arithmetic (+, -, *, /)
Boolean (AND, OR, NOT)
Bitwise (shl, shr)

### Exercise 3 — Bounce Sequence

Functional programming was used with generateSequence:


### Virtual Library System

* Base Class Book: 

    Abstract class

    Custom getter:
Classic (<1980)
Modern (1980–2010)
Contemporary (>2010)

    Setter with validation for copies


* Subclasses:

    DigitalBook - Properties: fileSize, format

    PhysicalBook - Properties: weight, hasHardcover


* Library Class:

    Responsible for:
Adding books,
borrowing and returning books,
displaying catalog,
Ssearching by author.


* Data Class:

    LibraryMember used to represent users

## 5. Testing and Validation
### Strategy
* Manual testing through program execution

* Verification of expected outputs

### Tested Cases
* Division by zero (proper handling)
* Invalid input (exception handling)
* Borrowing unavailable books
* Searching for non-existing books

### Limitations
* Console-based interface
* No data persistence
* Input validation could be improved

## 6. Usage Instructions
### Requirements
* IntelliJ IDEA
* Kotlin

### Execution
* Open the project in IntelliJ and run the following files:
exer_1.kt,
exer_2.kt,
exer_3.kt,
exer_6_kt.
Execute the main() function inside each file.

## 7. Prompting Strategy

ChatGPT was used only to help write and organize this report. The prompts were mainly focused on improving the structure and clarity of the text based on the assignment requirements. Some adjustments were made during the process to make the report clearer and better aligned with the expected format.

## 8. Autonomous Agent Workflow

No autonomous agents or AI tools were used during the development of the project itself. All the Kotlin exercises and the virtual library system were implemented manually by the student. AI was only used later to support the writing of the report.

## 9. Verification of AI-Generated Artifacts

Since AI was only used to generate parts of the report text, all the content was reviewed and corrected manually. The report was checked to make sure it matches the actual code and implementation.

## 10. Human vs AI Contribution

All the code in this assignment was written by the student without AI assistance. ChatGPT was only used to help structure and write the report. The student understands all parts of the implementation and is responsible for the entire work.

## 11. Ethical and Responsible Use

AI tools were used in a limited and responsible way, only for documentation purposes. No AI-generated code was used. The student followed the assignment guidelines and ensured full understanding of the work submitted.
## 12. Version Control and Commit History

Version control was managed using Git, ensuring:

* Progressive commit history
* Clear separation between exercises

## 13. Difficulties and Lessons Learned
### Difficulties
* Correct implementation of custom getters and setters
* Handling user input exceptions
* Understanding Kotlin sequences
### Lessons Learned
* Difference between Array and IntArray
* Use of when instead of switch

## 14. Future Improvements
* Graphical interface for the library
* Database integration for persistence
* Improved input validation
* User authentication system

## 15. AI Usage Disclosure

ChatGPT was used to:
* Assist in writing the report
* Structure the document according to the assignment requirements

All code was manually understood and validated, and the student remains fully responsible for its content.