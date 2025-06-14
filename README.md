# 📇 SmartContactManager

A modern and secure **Contact Management Web Application** built using **Spring Boot**, **Cloudinary**, **Flowbite**, and **SweetAlert**. Users can register, log in, and manage their personal contacts with profile images and cloud-based image storage.

---

## 🚀 Features

- 🔐 User Registration & Login with Spring Security
- 📩 OTP-based Email Verification
- 👤 Manage Personal Contacts (Add, Edit, Delete, View)
- ☁️ Profile Image Uploads via Cloudinary
- 🎨 Responsive UI with Flowbite & Tailwind CSS
- ⚡ Interactive Alerts with SweetAlert
- 🛡️ Secure Access Control – Users manage only their own data
- 📱 Mobile-Responsive Design

---

## 🧰 Tech Stack

| Layer         | Technology                             |
|---------------|-----------------------------------------|
| Backend       | Spring Boot 3, Java 21                  |
| Frontend      | Thymeleaf, Flowbite, Tailwind CSS       |
| Security      | Spring Security, BCrypt                 |
| Image Hosting | Cloudinary API                          |
| Email Service | JavaMail (SMTP)                         |
| Alerts        | SweetAlert2                             |
| Database      | MySQL                                   |
| Build Tool    | Maven                                   |

---

## ⚙️ Setup Instructions

### ✅ Prerequisites

- Java 21+
- Maven 3.x
- MySQL or MariaDB
- Cloudinary account
- SMTP email credentials

---

### 📦 Steps to Run Locally

1. **Clone the Repository**

   ```bash
   git clone https://github.com/abdulalimswe/SmartContactManager.git
   cd SmartContactManager
````

2. **Create MySQL Database**

   ```sql
   CREATE DATABASE smart_contact_manager;
   ```

3. **Configure `application.properties`**

   Update `src/main/resources/application-demo.properties`:

4. **Build and Run the Application**

   ```bash
   mvn clean install
   java -jar target/SmartContactManager-0.0.1-SNAPSHOT.jar
   ```

5. **Access the App**

   Open in your browser: [http://localhost:8081](http://localhost:8081)

---

## 📌 Key Functionalities

* OTP email confirmation for secure registration
* Upload and store contact profile pictures using Cloudinary
* Secure session handling and role-based access control
* Beautiful and responsive UI using Flowbite + Tailwind
* SweetAlert for enhanced user interaction and feedback

---

## 📸 Screenshots

```markdown
![Home Page](https://github.com/user-attachments/assets/8bfcb253-56d7-4022-b1a9-5921a838dd63)
![Login Page](https://github.com/user-attachments/assets/281a69e5-74d1-477d-8353-80d1fdaf1225)
![Add Contact](https://github.com/user-attachments/assets/c03369b3-48d9-4da6-9189-cecdf82770da)
![Contact List](https://github.com/user-attachments/assets/b71ffe94-bb64-46db-9828-72896c56193b)
---


## 👨‍💻 Author

**Md. Abdul Alim**
📧 [abdulalim.swe@gmail.com](mailto:abdulalim.swe@gmail.com)
🔗 [GitHub](https://github.com/abdulalimswe) | [LinkedIn](https://linkedin.com/in/abdulalim-swe)
