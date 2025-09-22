# Voyanta - Vehicle Rental System

A comprehensive full-stack web application for vehicle rental management built with Spring Boot and React.

## 🚀 Features

### Core Functionality
- **User Authentication & Authorization** - JWT-based authentication with role-based access control
- **Vehicle Management** - CRUD operations for vehicles with search and filtering
- **Booking System** - Create, manage, and cancel vehicle bookings
- **Payment Processing** - Integrated payment system with transaction tracking
- **Feedback & Ratings** - Customer feedback system with rating management
- **Subscription System** - Loyalty program with discount management

### User Roles
1. **Client (Customer)** - Search, book, cancel, pay, view history, provide feedback
2. **Staff Member** - Confirm/update bookings, assist with payments, mark maintenance, issue receipts, view feedback
3. **Admin** - Manage vehicles, manage users, configure pricing/discounts, view reports, control settings

## 🛠️ Tech Stack

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.5.6** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence
- **MySQL** - Database
- **JWT** - Token-based authentication
- **Maven** - Build tool

### Frontend
- **React 18** - UI framework
- **Vite** - Build tool and dev server
- **React Router** - Client-side routing
- **Bootstrap 5** - CSS framework
- **Axios** - HTTP client
- **React Toastify** - Notifications

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17** or higher
- **Node.js 16** or higher
- **MySQL 8.0** or higher
- **Maven 3.6** or higher
- **npm** or **yarn**

## 🗄️ Database Setup

1. **Install MySQL** and start the service
2. **Create a database** named `voyanta_db`:
   ```sql
   CREATE DATABASE voyanta_db;
   ```
3. **Update database credentials** in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## 🚀 Getting Started

### Backend Setup

1. **Navigate to the project root directory**:
   ```bash
   cd demo
   ```

2. **Install dependencies and run the backend**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   The backend will start on `http://localhost:8080`

### Frontend Setup

1. **Navigate to the frontend directory**:
   ```bash
   cd frontend
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Start the development server**:
   ```bash
   npm run dev
   ```

   The frontend will start on `http://localhost:3000`

## 📁 Project Structure

```
demo/
├── src/main/java/com/voyanta/
│   ├── entity/           # JPA entities
│   ├── repository/       # Data access layer
│   ├── service/          # Business logic layer
│   ├── controller/       # REST controllers
│   ├── dto/              # Data transfer objects
│   ├── config/           # Configuration classes
│   └── security/         # Security configuration
├── src/main/resources/
│   └── application.properties
├── frontend/
│   ├── src/
│   │   ├── components/   # Reusable React components
│   │   ├── pages/        # Page components
│   │   ├── services/     # API service functions
│   │   ├── contexts/     # React contexts
│   │   └── utils/        # Utility functions
│   ├── package.json
│   └── vite.config.js
└── README.md
```

## 🔐 Authentication

The application uses JWT (JSON Web Tokens) for authentication. Users can register and login through the frontend interface.

### Default Admin User
To create an admin user, you can either:
1. Register through the frontend and manually update the role in the database
2. Use the application's user management features (if you have admin access)

## 🎯 API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Public Vehicle Endpoints
- `GET /api/public/vehicles` - Get all vehicles
- `GET /api/public/vehicles/available` - Get available vehicles
- `GET /api/public/vehicles/{id}` - Get vehicle by ID
- `GET /api/public/vehicles/type/{type}` - Get vehicles by type
- `GET /api/public/vehicles/make/{make}` - Get vehicles by make
- `GET /api/public/vehicles/search?q={query}` - Search vehicles

### Client Endpoints (Authenticated)
- `POST /api/client/bookings` - Create booking
- `GET /api/client/bookings` - Get user bookings
- `GET /api/client/bookings/{id}` - Get booking by ID
- `PUT /api/client/bookings/{id}/cancel` - Cancel booking

### Admin Endpoints (Admin Role Required)
- `POST /api/admin/vehicles` - Create vehicle
- `PUT /api/admin/vehicles/{id}` - Update vehicle
- `DELETE /api/admin/vehicles/{id}` - Delete vehicle
- `PUT /api/admin/vehicles/{id}/status` - Update vehicle status
- `GET /api/admin/users` - Get all users
- `GET /api/admin/bookings` - Get all bookings
- `PUT /api/admin/bookings/{id}/status` - Update booking status

## 🎨 Frontend Features

### Pages
- **Home** - Landing page with features and call-to-action
- **Login/Register** - Authentication pages
- **Vehicle List** - Browse and filter available vehicles
- **Vehicle Detail** - Detailed vehicle information and booking
- **Booking Form** - Create new vehicle bookings
- **User Dashboard** - Manage personal bookings
- **Admin Dashboard** - System administration
- **Staff Dashboard** - Staff management tools

### Components
- **Navbar** - Navigation with role-based menu items
- **ProtectedRoute** - Route protection based on authentication and roles
- **VehicleCard** - Vehicle display component
- **BookingForm** - Booking creation form

## 🔧 Configuration

### Backend Configuration
Key configuration options in `application.properties`:

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/voyanta_db
spring.datasource.username=root
spring.datasource.password=password

# JWT
jwt.secret=your-secret-key
jwt.expiration=86400000

# CORS
cors.allowed.origins=http://localhost:3000
```

### Frontend Configuration
The frontend is configured to proxy API requests to the backend server running on port 8080.

## 🧪 Testing

### Backend Testing
```bash
mvn test
```

### Frontend Testing
```bash
cd frontend
npm test
```

## 🚀 Deployment

### Backend Deployment
1. Build the JAR file:
   ```bash
   mvn clean package
   ```

2. Run the JAR file:
   ```bash
   java -jar target/voyanta-0.0.1-SNAPSHOT.jar
   ```

### Frontend Deployment
1. Build the production bundle:
   ```bash
   cd frontend
   npm run build
   ```

2. Serve the `dist` folder with a web server like nginx or Apache.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

If you encounter any issues or have questions:

1. Check the existing issues in the repository
2. Create a new issue with detailed information
3. Include error messages, steps to reproduce, and environment details

## 🔮 Future Enhancements

- [ ] Real-time notifications
- [ ] Mobile app development
- [ ] Advanced reporting and analytics
- [ ] Integration with external payment gateways
- [ ] GPS tracking for vehicles
- [ ] Automated maintenance scheduling
- [ ] Multi-language support
- [ ] Advanced search with AI recommendations

---

**Voyanta** - Your trusted vehicle rental solution! 🚗✨
