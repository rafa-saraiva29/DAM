# Assignment 3 — Annotations, MVVM and Jetpack Compose

Course: Desenvolvimento de Aplicações Móveis

Student(s): Rafael Saraiva 47484

Date: 03/05/2026

Repository URL: ___

---

## 1. Introduction

This assignment focuses on advanced Android development concepts, combining 
compile-time code generation with modern mobile application architecture.

The first part explores Kotlin annotation processing, where custom annotations 
are used to automatically generate code during compilation. The second part 
involves rebuilding the previously developed Weather Application using the MVVM 
(Model–View–ViewModel) architecture and Jetpack Compose, a modern declarative UI 
toolkit.

The objective is to improve code modularity, separation of concerns, and to gain 
experience with contemporary Android development practices.

---

## 2. System Overview

The system is divided into two main components:

### Annotation Processor
A Kotlin multi-module project that:
- Defines a custom annotation (`@Greeting`)
- Processes annotated methods at compile time
- Generates wrapper classes that extend functionality

### Android Application — Jetpack Weather App
A modern version of the Weather Application that:
- Uses MVVM architecture
- Implements UI using Jetpack Compose
- Retrieves weather data from the Open-Meteo API
- Displays real-time weather information based on user input

Main use cases include:
- Automatic code generation using annotations
- Managing UI state through ViewModel
- Displaying and updating weather data dynamically

---

## 3. Architecture and Design

### Multi-Module Structure (Annotation Processor)

The annotation processor project is organized into three modules:

- `annotations` — defines custom annotations
- `processor` — processes annotations and generates code
- `app` — demonstrates usage of generated code

### Android Application Structure

The application follows the MVVM architecture, organized into:

- `data` — data models and API client
- `viewmodel` — business logic and UI state management
- `ui` — Jetpack Compose interface

### Design Decisions

* **Separation of concerns**:
  Clear division between data, logic, and UI layers.

* **Annotation processing**:
  Used to automate repetitive logic and demonstrate compile-time code generation.

* **MVVM architecture**:
  Ensures maintainability and testability by separating UI from business logic.

* **Declarative UI (Compose)**:
  Replaces XML layouts with composable functions for more flexible UI development.

* **State management**:
  UI reacts to state changes exposed by the ViewModel.

---

## 4. Implementation

### Annotation Processor — Greeting

The annotation processor was implemented using:
- Custom annotation `@Greeting`
- Annotation processor using `kapt`
- Code generation using KotlinPoet

Functionality:
- Detects methods annotated with `@Greeting`
- Generates a wrapper class for each containing class
- Adds behavior to print a message before executing the original method

The generated class uses composition to wrap the original class and delegate 
method calls.

---

### Android Application — Jetpack Weather App

The Weather Application was rebuilt using MVVM and Jetpack Compose, following the 
structure defined in the assignment.

#### Data Layer
- Contains `WeatherData` data classes
- Uses a `WeatherApiClient` with Ktor to fetch API data
- Parses JSON responses into structured objects

#### ViewModel
- Manages application state
- Handles:
    - Latitude and longitude updates
    - API calls to fetch weather data
- Exposes observable state to the UI

#### UI Layer (Jetpack Compose)
- Implemented using composable functions
- Main UI function (`WeatherUI`) renders state from ViewModel
- Supports:
    - Portrait and landscape layouts
    - Input fields for coordinates
    - Update button interaction
    - Weather data display (temperature, wind, pressure, etc.)

#### Features Implemented
- Weather data retrieval using Open-Meteo API
- Dynamic UI updates based on state changes
- Orientation handling (portrait and landscape)

---

## 5. Testing and Validation

### Strategy
- Manual testing using Android Emulator
- Verification of UI updates and API responses

### Tested Cases
- UI state updates after user interaction
- Orientation changes

### Limitations
- No optional features implemented (location picker, favorites)
- No advanced error handling for API failures
- Limited UI customization beyond core requirements

---

## 6. Usage Instructions

### Requirements
- Android Studio
- Kotlin
- Internet connection

### Execution
1. Open the project in Android Studio
2. Build and run the application
3. Insert latitude and longitude values
4. Press the update button to fetch weather data

---

## 7. Prompting Strategy

ChatGPT was used to assist in structuring and writing this report, as well as to support understanding of specific Android development functionalities.

During development, AI was used to:
- Understand how to configure Jetpack Compose features
- Explore implementation details such as themes, layouts, and UI components
- Clarify Android-related functionalities and development practices

---

## 8. Autonomous Agent Workflow

No autonomous agents were used during the development of this assignment. All implementation work was completed manually by the student.

---

## 9. Verification of AI-Generated Artifacts

All information obtained through AI was reviewed and validated manually. The student ensured that all concepts and implementations were fully understood and correctly applied.

---

## 10. Human vs AI Contribution

All code in this assignment was written manually by the student.

AI tools were only used for:
- Writing and structuring the report
- Supporting understanding of Android functionalities

The student remains fully responsible for the implementation and demonstrates full understanding of the developed system.

---

## 11. Ethical and Responsible Use

AI tools were used responsibly and only as a support mechanism for documentation and learning.

At no point was AI used to generate code or directly solve the assignment tasks. All solutions were developed independently by the student.

---

## 12. Version Control and Commit History

Version control was managed using Git, ensuring:
- Progressive commit history
- Separation of features and modules

---

## 13. Difficulties and Lessons Learned

### Difficulties
- Understanding annotation processing and code generation
- Adapting from XML layouts to Jetpack Compose
- Managing state in MVVM architecture

### Lessons Learned
- How annotation processors work at compile time
- Benefits of modular architecture
- Differences between imperative (XML) and declarative (Compose) UI
- Importance of state management in modern Android applications

---

## 14. Future Improvements

- Implement optional features (location picker, favorites)
- Improve UI design with animations and advanced Compose features
- Add better error handling and loading indicators
- Extend functionality with additional weather parameters

---

## 15. AI Usage Disclosure

ChatGPT was used to:
- Assist in writing and structuring the report
- Support understanding of Android development features and concepts

No AI-generated code was used. All implementation was manually completed and fully understood by the student.