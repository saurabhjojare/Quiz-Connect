# Quiz-Connect

**Quiz-Connect** is a application for creating, taking, and evaluating quizzes.

---

## **Required Software**

- [Docker](https://www.docker.com/get-started)
- [Postman](https://www.postman.com/downloads/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

---

## **Running the Application**

1. Open a terminal in the project root directory.
2. Run the following command to build and start the application using Docker:

```bash
docker-compose up --build
```

---

## **Testing the API with Postman**

1. Open Postman.
2. Click **Import** → **Upload Files**.
3. Navigate to the `resources/collection` folder in the project.
4. Select `Quize Connect API Collection.json` and click **Import**.
5. You can now use all the predefined API requests to test the application.

---

## **Running Test Cases**

1. Open a terminal in the project root directory.
2. Run the following command to execute all test cases:

```bash
./mvnw test
```

This will compile and run the tests, displaying results in the console.

---

Now the application is ready to use, test, and explore!