# LLM REST API (Kotlin)

A minimal backend API for interacting with a Large Language Model (LLM) through REST endpoints.  
The service runs locally and exposes endpoints that forward prompts to an LLM provider.

The implementation is designed so the **LLM provider can easily be swapped**.

## Backend

The backend is implemented in Kotlin using the Ktor framework. It defines a single endpoint `/api/llm` that accepts POST requests with a JSON body containing a `prompt`. The backend forwards this prompt to the configured LLM provider and returns the response.

## Frontend

The frontend is implemented in Vue 3 with TypeScript and Vite. It provides a simple interface for users to input prompts and view responses from the LLM. The frontend makes POST requests to the backend endpoint to retrieve responses.