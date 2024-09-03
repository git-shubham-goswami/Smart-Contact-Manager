# Smart Contact Manager

A comprehensive contact management system that provides an intuitive interface for managing contacts and leveraging modern technologies. With features for secure authentication, file storage, email functionality, and more, Smart Contact Manager is designed to meet your contact management needs efficiently.

## Technologies Used

- **Spring MVC**: For building the web application framework.
- **Spring Data JPA**: ORM for database interactions. The project is database independent.
- **Spring Security**: For securing routes and managing authentication.
- **Thymeleaf / JSP**: Template engines for rendering views.
- **Java Email API**: For sending and receiving emails.
- **AWS/Cloudinary SDK**: For file storage and management.
- **MySQL**: Relational database management system.
- **JavaScript**: For client-side scripting.
- **Tailwind CSS**: For modern, responsive design.
- **Flowbite Components**: For pre-designed UI components.
- **PDF/Excel Tools**: For generating reports.

## Features

- **User Signup and Authentication**:
  - Signup with email and password.
  - Verify account using an email verification link.
  - Signup with Google and GitHub for social login.

- **Contact Management**:
  - Add contacts with profile pictures.
  - Upload contact pictures to AWS or Cloudinary.
  - View all contacts with pagination.
  - View detailed contact information.
  - Search contacts.
  - Update contact information.
  - Delete contacts.
  - Mark contacts as favorites.

- **Email Functionality**:
  - Compose and send emails with text and attachments.
  - Receive and manage emails.

- **User Preferences**:
  - Toggle between dark and light themes.
  - View and edit personal details.

- **Data Export**:
  - Export contact data to PDF or Excel.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/smart-contact-manager.git

2. **Navigate to Project Directory**:
    ```bash
   cd smart-contact-manager
3. **Set Up the Database**:
   Configure MySQL database settings in application.properties or application.yml.
   Run the database migrations (if applicable).

4. **Build the Project**:
   ```bash
   ./mvnw clean install
5. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run

Usage
1. Access the Application:
  Open a web browser and go to http://localhost:8080.

2. Authentication:
  Sign up with email and password or use social login (Google or GitHub).
  Verify your account using the email verification link sent to your email address.

3. Manage Contacts:
  Add new contacts with profile pictures.
  View, update, delete, and search for contacts.
  Export contact data to PDF or Excel.
  Mark contacts as favorites and toggle between dark and light themes.

4. Email Functionality:
  Compose and send emails with text and attachments.
  Receive and manage incoming emails.

5. User Preferences:
  Toggle between dark and light themes.
  View and edit your personal details.
