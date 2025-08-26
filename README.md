# Book API - Spring Boot Application

A RESTful API for managing books built with Spring Boot 3.1.5 and Java 17.

## Features

- CRUD operations for books
- Search functionality by title, author, and price range
- Input validation
- Exception handling
- H2 in-memory database for development
- Comprehensive test coverage
- Sample data initialization

## Technology Stack

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database** (in-memory)
- **Maven** (build tool)
- **JUnit 5** (testing)

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Getting Started

### 1. Clone the repository
```bash
git clone <repository-url>
cd java-web-app
```

### 2. Build the application
```bash
mvn clean compile
```

### 3. Run the application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 4. Access H2 Console (Optional)
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:bookdb`
- Username: `sa`
- Password: (leave blank)

## API Endpoints

### Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| GET | `/api/books/isbn/{isbn}` | Get book by ISBN |
| GET | `/api/books/author/{author}` | Get books by author |
| GET | `/api/books/search?q={searchTerm}` | Search books by title or author |
| GET | `/api/books/title?title={title}` | Search books by title |
| GET | `/api/books/price-range?minPrice={min}&maxPrice={max}` | Get books by price range |
| POST | `/api/books` | Create a new book |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |

### Example Request Bodies

#### Create/Update Book
```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "publicationDate": "2008-08-01",
  "description": "A Handbook of Agile Software Craftsmanship",
  "price": 45.99
}
```

## Sample API Calls

### Get All Books
```bash
curl -X GET http://localhost:8080/api/books
```

### Get Book by ID
```bash
curl -X GET http://localhost:8080/api/books/1
```

### Create a New Book
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Spring in Action",
    "author": "Craig Walls",
    "isbn": "978-1617294945",
    "publicationDate": "2018-10-02",
    "description": "Covers Spring 5.0",
    "price": 59.99
  }'
```

### Search Books
```bash
curl -X GET "http://localhost:8080/api/books/search?q=Spring"
```

### Get Books by Price Range
```bash
curl -X GET "http://localhost:8080/api/books/price-range?minPrice=40&maxPrice=60"
```

## Testing

### Run all tests
```bash
mvn test
```

### Run tests with coverage
```bash
mvn test jacoco:report
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/bookapi/
│   │       ├── BookApiApplication.java
│   │       ├── config/
│   │       │   └── DataInitializer.java
│   │       ├── controller/
│   │       │   └── BookController.java
│   │       ├── exception/
│   │       │   └── GlobalExceptionHandler.java
│   │       ├── model/
│   │       │   └── Book.java
│   │       ├── repository/
│   │       │   └── BookRepository.java
│   │       └── service/
│   │           └── BookService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/example/bookapi/
            ├── controller/
            │   └── BookControllerTest.java
            └── service/
                └── BookServiceTest.java
```

## Configuration

The application uses the following default configuration in `application.properties`:

- **Server Port**: 8080
- **Database**: H2 in-memory database
- **JPA**: Hibernate with automatic table creation
- **Logging**: Debug level for application packages

## Sample Data

The application initializes with 5 sample books:

1. Clean Code by Robert C. Martin
2. Effective Java by Joshua Bloch
3. Spring in Action by Craig Walls
4. Java: The Complete Reference by Herbert Schildt
5. Design Patterns by Gang of Four

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## CI/CD Pipeline

This project includes a GitHub Actions workflow for continuous integration and deployment:

### CI/CD Pipeline (`.github/workflows/simple-ci-cd.yml`)

**Triggers:**
- Push to `main` or `develop` branches
- Pull requests to `main` branch

**Jobs:**
1. **Test**: Runs unit tests with comprehensive reporting and artifacts
2. **Build**: Compiles the application and uploads JAR artifact
3. **Docker**: Builds and pushes Docker image to GitHub Container Registry

**Features:**
- Automated testing with detailed test reports and coverage
- Test result artifacts for download and analysis
- Maven dependency caching for faster builds
- Multi-platform Docker builds (amd64, arm64)
- Images pushed to GitHub Container Registry (`ghcr.io`)
- Test results commented on pull requests

### Test Artifacts

Each workflow run generates downloadable artifacts:
- **Test Results**: JUnit XML, text summaries, and HTML reports
- **Code Coverage**: JaCoCo coverage reports with detailed metrics
- **JAR Artifact**: Compiled application ready for deployment

### Container Registry

**GitHub Container Registry:**
```bash
docker pull ghcr.io/akilans/java-web-app:latest
```

## Docker Deployment

### Local Docker Build and Run

```bash
# Build the image
docker build -t book-api .

# Run the container
docker run -p 8080:8080 book-api

# Run with custom port
docker run -p 3000:8080 book-api
```

### Docker Compose (Optional)

Create a `docker-compose.yml`:
```yaml
version: '3.8'
services:
  book-api:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
```

Run with: `docker-compose up`

## License

This project is licensed under the MIT License.
