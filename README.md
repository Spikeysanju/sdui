# Server-Driven UI POC (Android/Jetpack Compose)

A proof-of-concept implementation demonstrating server-driven UI patterns using Jetpack Compose. This project shows how UI components can be dynamically rendered based on JSON responses from a server.

## Overview

This POC simulates how a server could control the UI layout and content by sending JSON descriptions of components. The app parses this JSON and renders the appropriate Compose UI components.

Currently supported components:
- Banner
- Card 
- Button
- Product
- Transaction
- Dashboard
- Chart
- Profile
- Settings

## Implementation Details

- Uses Jetpack Compose for UI rendering
- Kotlinx Serialization for JSON parsing
- Simulated API responses (currently using hardcoded JSON)
- Polymorphic component serialization
- Material3 theming

## Note

This is an experimental proof-of-concept only. In a production environment, you would:

- Implement actual API calls
- Add proper error handling
- Include loading states
- Add component interaction handling
- Implement proper navigation
- Add proper state management
- Add proper testing

## Purpose

The goal is to demonstrate how server-driven UI could work in a Compose application, allowing dynamic UI updates without app releases.
