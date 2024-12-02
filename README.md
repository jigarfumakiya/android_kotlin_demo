# Android Kotlin Demo
This is a demo Android application built using **Kotlin** that follows **Clean Architecture** principles. The application is designed to be scalable, maintainable, and easily extendable. It uses modern Android libraries and tools such as **Hilt**, **Retrofit**, and **Navigation** to ensure efficient development and maintainability.App that fetch some news from [news.api](https://newsapi.org)

## Architecture

This project follows **Clean Architecture** principles, ensuring the following:

- **Separation of Concerns**: Layers are separated into distinct responsibilities for maintainability and testability.
- **Scalability**: The architecture is designed to scale with ease by adding features without compromising the system's integrity.
- **Testability**: Easy to write unit and UI tests for each layer, with clear dependency management handled by **Hilt**.


## Task
- [Task 1](https://github.com/jigarfumakiya/android_kotlin_demo/blob/0d07e56e8ab03c3e1c7f30a29e080b5c2fc0191a/app/src/main/java/com/app/android_test/core/utility/extension/StringExtension.kt#L12)
- [Task 2](https://github.com/jigarfumakiya/android_kotlin_demo/blob/0d07e56e8ab03c3e1c7f30a29e080b5c2fc0191a/app/src/main/java/com/app/android_test/core/utility/SafeDivde.kt#L9)
- [Task 3](https://github.com/jigarfumakiya/android_kotlin_demo/blob/0d07e56e8ab03c3e1c7f30a29e080b5c2fc0191a/app/src/main/java/com/app/android_test/features/welcome/WelcomeFragment.kt#L20)

## Setup

### Prerequisites

- Android Studio
- JDK 1.8+
- Android SDK

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/jigarfumakiya/android_kotlin_demo.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Build and run the app.

### 🎉 Features included:
- [x] User Login
- [x] Fetch news
- [x] User Logout

## 🧪 Testing
The project includes a basic set of tests to ensure the app’s functionality and quality.

### Unit Test
A unit test has been added to verify a extension logic, You can find the unit test in the following folder:
- `app/src/test/java/`

### UI Test
A UI test has been implemented for welcome screen. It tests a basic interaction within the app. You can find the UI test in:
- `app/src/androidTest/java/`


