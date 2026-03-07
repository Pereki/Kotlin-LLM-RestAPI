# LLM REST API (Kotlin)

A minimal backend API for interacting with a Large Language Model (LLM) through REST endpoints.  
The service runs locally and exposes endpoints that forward prompts to an LLM provider.

The implementation is designed so the **LLM provider can easily be swapped**.

## Backend

The backend is implemented in Kotlin using the Ktor framework. It defines a single endpoint `/api/llm` that accepts POST requests with a JSON body containing a `prompt`. The backend forwards this prompt to the configured LLM provider and returns the response.
