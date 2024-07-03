
# RaveFinder

RaveFinder is a web application tailored for selling and trading tickets for rave and EDM music events. It is built with a Spring Boot backend and a Vue.js frontend.

## Table of Contents

- [Project Structure](#project-structure)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Project Structure

```plaintext
ravefinder/
├── .gitignore
├── README.md
├── rave-finder
│   ├── src
│   ├── pom.xml
│   └── ...
└── rave-finder-frontend
    ├── src
    ├── package.json
    └── ...
```

## Features

- Event listing and search
- Ticket booking and payment integration
- User authentication and profile management
- Event creation and management by organizers
- Calendar view for events
- Email notifications

## Technologies Used

- **Backend**: Spring Boot, Spring Security, Spring Data JPA (Hibernate), REST APIs, PostgreSQL
- **Frontend**: Vue.js
- **Mobile Version**: React Native
- **Payment Integration**: Stripe or PayPal API
- **Deployment**: Heroku, AWS, or DigitalOcean
- **Other Tools**: Docker, Thymeleaf (or modern JavaScript framework for the frontend)

## Setup and Installation

### Backend Setup

1. **Clone the repository**

    ```bash
    git clone https://github.com/yourusername/ravefinder.git
    cd ravefinder/rave-finder
    ```

2. **Configure the database**

    Update the `application.properties` file with your PostgreSQL database configuration.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build and run the backend**

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

### Frontend Setup

1. **Navigate to the frontend directory**

    ```bash
    cd ../rave-finder-frontend
    ```

2. **Install dependencies**

    ```bash
    npm install
    ```

3. **Run the frontend**

    ```bash
    npm run serve
    ```

## Usage

Once both the backend and frontend are running, you can access the application in your browser at `http://localhost:8080` for the backend and `http://localhost:8081` for the frontend.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


