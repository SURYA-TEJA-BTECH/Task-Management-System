# Task Management System

This project allows users to create, read, update, delete, and mark tasks as complete. It uses the H2 in-memory database to store the tasks. The database is lightweight and runs entirely in-memory, making it suitable for testing and development purposes. Once the application is shut down, the data is lost.

## H2 Database

H2 is an in-memory relational database, primarily used for lightweight, embedded databases in Java applications. It supports SQL queries and is commonly used for testing and development because it doesn't require installation.

**URL to access H2 database**: [http://localhost:8083/h2/](http://localhost:8083/h2/)

![H2 Database Image](https://github.com/user-attachments/assets/1e90410b-d6a5-42bf-b58d-d39b5251b13b)

## Validations

The following validations have been implemented for the task model:

- `@NotEmpty(message = "Title cannot be empty.")`: Custom error message for empty title.
- `@Size(min = 5, max = 20, message = "Title must be between 5 and 20 characters.")`: Title length validation.
- `@NotEmpty(message = "Description cannot be empty.")`: Custom error message for empty description.
- `@Size(min = 10, max = 75, message = "Description must be between 10 and 75 characters.")`: Description length validation.
- `@Future(message = "Deadline must be a future date.")`: Future date validation.
- `@NotNull(message = "Status cannot be null.")`: Null status validation.

## Assumptions Made

In this project, **soft deleting** is used instead of hard deleting tasks. This is achieved by using an `isActive` field of type `boolean`. When a user tries to delete a task, it doesn't get permanently deleted from the database. Instead, the `isActive` flag is set to `false`, indicating that the task is inactive but still exists in the system for audit or record-keeping purposes.

## Project Structure

![Project Structure](https://github.com/user-attachments/assets/09b80ce8-c9ee-40d6-85f9-baec0835aa6a)

## Available Endpoints

1. **POST /tasks**: Create a new task.
2. **GET /tasks**: Retrieve all tasks.
3. **GET /tasks/{id}**: Retrieve a specific task by its ID.
4. **PUT /tasks/{id}**: Update an existing task.
5. **DELETE /tasks/{id}**: Soft delete a task (sets `isActive` to false).
6. **PATCH /tasks/{id}/complete**: Change the status to "COMPLETED".

## Testing with Postman or Swagger

You can test the endpoints using **Postman** or **Swagger UI**.

### Swagger UI

Swagger documentation is included to test the endpoints directly from the browser. It provides sample data for each endpoint and automatically generates the necessary URL. You can access the Swagger documentation by visiting the following URL: [http://localhost:8083/swagger-ui/index.html#/](http://localhost:8083/swagger-ui/index.html#/)

**Swagger UI Documentation**

The Swagger UI will display the available endpoints, response codes, and response messages, allowing you to interact with the API easily.

![Swagger UI Screenshot](https://github.com/user-attachments/assets/4a3a93f2-5eb4-4d04-87e4-2418de822f59)

## Future Enhancements

1. **Adding Security with JWT**

   I plan to enhance the security of the application by integrating **JWT (JSON Web Tokens)** for authentication and authorization. This will ensure that only authorized users can perform certain actions, such as creating, updating, or deleting tasks.

2. **Sending Emails to Concerned Users (Developers) When a Task is Created**

   I plan to implement functionality where an email is automatically sent to the concerned users whenever a new task is created. This will keep users informed about their assigned tasks and ensure better communication within the team.

3. **Sending Emails to Concerned Users (Testers) When a Task is Completed**

   Once a task is marked as completed, an email will be sent to the concerned users, notifying them of the task completion. This will help users know when the task is ready for testing, improving the workflow and coordination between developers and testers.

4. **Sending Email to the Concerned User When a Task is Attempted to Be Deleted Before Completion**

   To prevent premature deletions, I plan to implement an email notification system that will alert the concerned user if someone attempts to delete a task before it has been completed. This will ensure that tasks are only deleted when appropriate and will help maintain better control over task statuses.

## Conclusion

This project provides a simple and effective task management system with the ability to soft delete tasks. The API is fully documented using Swagger, making it easy for developers to test and interact with the endpoints. The use of H2 as an in-memory database ensures that the application is lightweight and easy to set up for testing or development purposes.
