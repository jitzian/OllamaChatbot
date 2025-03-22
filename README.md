# Ollama Chatbot

This project is a simple chatbot that interacts with the Ollama API. It is written in Kotlin and uses several libraries to handle HTTP requests, JSON parsing, and coroutines.

## Libraries Used

- **OkHttp**: A third-party library for making HTTP requests.
- **org.json**: A library for parsing JSON data.
- **Kotlin Coroutines**: A library for asynchronous programming in Kotlin.
- **JUnit**: A testing framework for unit tests.
- **MockWebServer**: A library for testing HTTP clients.

## Prerequisites

- Kotlin 1.9.23
- Gradle
- IntelliJ IDEA (optional, but recommended)
- Ollama: Install the Ollama client from [here](https://github.com/ollama/ollama) and ensure the required models are pulled.

## Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/ollama-chatbot.git
    cd ollama-chatbot
    ```

2. Open the project in IntelliJ IDEA.

3. Build the project using Gradle:
    ```sh
    ./gradlew build
    ```

## Running the Chatbot

1. Start the Ollama API locally. Ensure it is running on `http://localhost:11434`.

2. Run the `main` function in `OllamaChatbot.kt`:

3. Interact with the chatbot through the terminal.

## Known Issues

- **Duplicate Outputs**: There is a known issue where the outputs can be duplicated sometimes. This is due to the input being persisted and causing repeated responses. We are actively working on fixing this bug.
