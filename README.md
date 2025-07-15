# Socialize

Socialize is a social networking platform built with Java and Spring Boot. It provides features such as user authentication, posting, messaging, and group chats, making it a foundation for a modern social media application.

## Features
- User registration and login
- JWT-based authentication
- Create, read, update, and delete posts
- Real-time messaging between users
- Group chat functionality
- GraphQL API support

## Project Structure
```
src/main/java/com/example/socialize/
  ├── controller/        # REST controllers for handling HTTP requests
  ├── entity/            # JPA entities representing database tables
  ├── repository/        # Spring Data JPA repositories
  ├── security/          # Security configuration (JWT, filters)
  ├── service/           # Business logic and service layer
  ├── tokenConfig/       # JWT token utilities and filters
  └── SocializeApplication.java  # Main application entry point
src/main/resources/
  ├── application.properties  # Application configuration
  └── graphql/schema.graphqls # GraphQL schema
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Gradle (or use the provided wrapper)

### Setup
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd socialize
   ```
2. **Configure the application:**
   - Edit `src/main/resources/application.properties` to set up your database and other environment variables.
3. **Build and run the application:**
   ```bash
   ./gradlew bootRun
   ```
   Or, if you have Gradle installed:
   ```bash
   gradle bootRun
   ```

### Running Tests
```bash
./gradlew test
```

## API Endpoints
- REST endpoints for authentication, posts, messaging, and groups (see controllers in `src/main/java/com/example/socialize/controller/`)
- GraphQL endpoint: `/graphql` (schema in `src/main/resources/graphql/schema.graphqls`)

## Folder Descriptions
- `uploads/` - Stores uploaded files (e.g., images, attachments)
- `src/test/` - Contains unit and integration tests

## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License.
