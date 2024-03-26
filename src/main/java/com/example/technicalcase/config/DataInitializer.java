package com.example.technicalcase.config;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.CourseFeedback;
import com.example.technicalcase.entities.Enrollment;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.enumerators.Role;
import com.example.technicalcase.enumerators.Status;
import com.example.technicalcase.repositories.CourseFeedbackRepository;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.EnrollmentRepository;
import com.example.technicalcase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseFeedbackRepository courseFeedbackRepository;

    @Override
    public void run(String... args) throws Exception {
        insertUsers();
        insertCourses();
        insertEnrollments();
        insertEnrollments2();
        insertCourseFeedbacks();
    }

    private void insertUsers() {
        List<User> users = Arrays.asList(
                new User(null, "johnsnow", "johnsnow@example.com", "John Snow", "password123", LocalDateTime.now(), Role.INSTRUCTOR),
                new User(null, "targaryen", "targaryen@example.com", "Daenerys Targaryen", "password123", LocalDateTime.now(), Role.ADMIN),
                new User(null, "aryastark", "aryastark@example.com", "Arya Stark", "password123", LocalDateTime.now(), Role.STUDENT),
                new User(null, "tyrionlannister", "tyrionlannister@example.com", "Tyrion Lannister", "password123", LocalDateTime.now(), Role.STUDENT),
                new User(null, "cerseilannister", "cerseilannister@example.com", "Cersei Lannister", "password123", LocalDateTime.now(), Role.STUDENT),
                new User(null, "sansastark", "sansastark@example.com", "Sansa Stark", "password123", LocalDateTime.now(), Role.STUDENT),
                new User(null, "jaimelannister", "jaimelannister@example.com", "Jaime Lannister", "password123", LocalDateTime.now(), Role.STUDENT),
                new User(null, "branstark", "branstark@example.com", "Bran Stark", "password123", LocalDateTime.now(), Role.STUDENT),
                new User(null, "sandorclegane", "sandorclegane@example.com", "Sandor Clegane", "password123", LocalDateTime.now(), Role.STUDENT),
                new User(null, "melisandre", "melisandre@example.com", "Melisandre", "password123", LocalDateTime.now(), Role.STUDENT)
        );
        userRepository.saveAll(users);
    }

    private void insertCourses() {
        User instructor = userRepository.findByUsername("johnsnow");
        List<Course> courses = Arrays.asList(
                new Course(null, instructor, "java-basic", "Introduction to Java: Learn the basics of Java programming language.", Status.ACTIVE, "Java Basics", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "html-css", "HTML & CSS Fundamentals: Master the essentials of web development with HTML and CSS.", Status.ACTIVE, "HTML & CSS Fundamentals", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "python-intro", "Python Introduction: Start your journey into the world of Python programming.", Status.ACTIVE, "Introduction to Python", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "sql-basics", "SQL Basics: Learn the fundamentals of Structured Query Language for database management.", Status.ACTIVE, "SQL Fundamentals", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "react-basics", "React Basics: Dive into the fundamentals of React.js for building interactive user interfaces.", Status.ACTIVE, "React Basics", LocalDateTime.now(),null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "java-oop", "Java Object-Oriented Programming: Master the principles of object-oriented programming in Java.", Status.ACTIVE, "Java OOP Principles", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "javascript", "JavaScript Fundamentals: Learn the core concepts of JavaScript programming language.", Status.ACTIVE, "JavaScript Fundamentals", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "nodejs-basics", "Node.js Basics: Get started with Node.js for server-side JavaScript development.", Status.ACTIVE, "Node.js Basics", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "git-github", "Git & GitHub Essentials: Learn version control with Git and collaborate on GitHub.", Status.ACTIVE, "Git & GitHub Essentials", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "vuejs-basics", "Vue.js Basics: Discover the fundamentals of Vue.js for building reactive web interfaces.", Status.ACTIVE, "Vue.js Basics", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "python-adv", "Advanced Python: Deepen your Python skills with advanced concepts and techniques.", Status.ACTIVE, "Advanced Python", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "docker", "Docker Essentials: Learn containerization with Docker for efficient software deployment.", Status.ACTIVE, "Docker Essentials", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "typescript", "TypeScript Fundamentals: Master the TypeScript language for building scalable applications.", Status.ACTIVE, "TypeScript Fundamentals", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "mongodb", "MongoDB Basics: Explore the fundamentals of MongoDB for modern database applications.", Status.ACTIVE, "MongoDB Basics", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "angular", "Angular Essentials: Dive into the essentials of Angular for building dynamic web apps.", Status.ACTIVE, "Angular Essentials", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "aws-basics", "AWS Basics: Learn the fundamentals of Amazon Web Services for cloud computing.", Status.ACTIVE, "AWS Basics", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "css-layout", "CSS Layout: Master the art of creating layouts with Cascading Style Sheets.", Status.ACTIVE, "CSS Layout Mastery", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "java-adv", "Advanced Java: Take your Java skills to the next level with advanced topics.", Status.ACTIVE, "Advanced Java Programming", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "rest-api", "RESTful API Development: Learn to design and build RESTful APIs for web applications.", Status.ACTIVE, "RESTful API Development", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>()),
                new Course(null, instructor, "bash-script", "Bash Scripting: Dive into scripting with Bash for automating tasks in Unix-based systems.", Status.ACTIVE, "Bash Scripting Basics", LocalDateTime.now(), null, new ArrayList<>(), new ArrayList<>())
        );
        courseRepository.saveAll(courses);
    }

    private void insertEnrollments() {
        List<User> users = Arrays.asList(
                userRepository.findByUsername("aryastark"),
                userRepository.findByUsername("tyrionlannister"),
                userRepository.findByUsername("cerseilannister"),
                userRepository.findByUsername("sansastark"),
                userRepository.findByUsername("jaimelannister")
        );

        Course javaBasic = courseRepository.findByCode("java-basic");
        Course htmlCss = courseRepository.findByCode("html-css");
        Course pythonIntro = courseRepository.findByCode("python-intro");
        Course sqlBasics = courseRepository.findByCode("sql-basics");
        Course reactBasics = courseRepository.findByCode("react-basics");
        Course bashscript = courseRepository.findByCode("bash-script");

        List<Enrollment> enrollments = new ArrayList<>();
        for (User user : users) {
            enrollments.add(new Enrollment(null, user, javaBasic, LocalDateTime.now()));
            enrollments.add(new Enrollment(null, user, htmlCss, LocalDateTime.now()));
            enrollments.add(new Enrollment(null, user, pythonIntro, LocalDateTime.now()));
            enrollments.add(new Enrollment(null, user, sqlBasics, LocalDateTime.now()));
            enrollments.add(new Enrollment(null, user, reactBasics, LocalDateTime.now()));

            enrollments.add(new Enrollment(null, user, bashscript, LocalDateTime.now()));
        }

        enrollmentRepository.saveAll(enrollments);
    }

    private void insertEnrollments2() {
        List<Course> courses = courseRepository.findAll();

        List<User> users = Arrays.asList(
                userRepository.findByUsername("branstark"),
                userRepository.findByUsername("sandorclegane"),
                userRepository.findByUsername("melisandre")
        );

        List<Enrollment> enrollments = new ArrayList<>();
        for (Course course : courses) {
            for (User user : users) {
                enrollments.add(new Enrollment(null, user, course, LocalDateTime.now()));
            }
        }

        enrollmentRepository.saveAll(enrollments);
    }

    private void insertCourseFeedbacks() {
        // Encontra os cursos correspondentes
        Course javaBasic = courseRepository.findByCode("java-basic");
        Course htmlCss = courseRepository.findByCode("html-css");
        Course pythonIntro = courseRepository.findByCode("python-intro");
        Course sqlBasics = courseRepository.findByCode("sql-basics");
        Course reactBasics = courseRepository.findByCode("react-basics");

        Course bashscript = courseRepository.findByCode("bash-script");

        // Encontra os usuários
        User aryaStark = userRepository.findByUsername("aryastark");
        User tyrionLannister = userRepository.findByUsername("tyrionlannister");
        User cerseiLannister = userRepository.findByUsername("cerseilannister");
        User sansaStark = userRepository.findByUsername("sansastark");
        User jaimeLannister = userRepository.findByUsername("jaimelannister");
        User branstark = userRepository.findByUsername("branstark");
        User sandorclegane = userRepository.findByUsername("sandorclegane");
        User melisandre = userRepository.findByUsername("melisandre");

        List<CourseFeedback> courseFeedbacks = Arrays.asList(
                //NPS bashscript 100 | This "course NPS" must appear in the consultation because although the course only has 3 feedbacks, the number of enrollments is 8 (which is greater than 4)
                new CourseFeedback(null, branstark, bashscript, 10, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sandorclegane, bashscript, 9, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, melisandre, bashscript, 9, "Texto exemplo de motivo", LocalDateTime.now()),

                //NPS pythonIntro 71,42857142857142...
                new CourseFeedback(null, aryaStark, pythonIntro, 2, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, tyrionLannister, pythonIntro, 10, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, cerseiLannister, pythonIntro, 9, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sansaStark, pythonIntro, 10, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, jaimeLannister, pythonIntro, 9, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, branstark, pythonIntro, 10, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sandorclegane, pythonIntro, 9, "Texto exemplo de motivo", LocalDateTime.now()),

                //NPS javaBasic 25
                new CourseFeedback(null, aryaStark, javaBasic, 8, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, tyrionLannister, javaBasic, 8, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, cerseiLannister, javaBasic, 10, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sansaStark, javaBasic, 10, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, jaimeLannister, javaBasic, 10, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, branstark, javaBasic, 9, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sandorclegane, javaBasic, 4, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, melisandre, javaBasic, 2, "Texto exemplo de motivo", LocalDateTime.now()),

                //NPS reactBasics 0
                new CourseFeedback(null, aryaStark, reactBasics, 7, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, tyrionLannister, reactBasics, 7, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, cerseiLannister, reactBasics, 4, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sansaStark, reactBasics, 5, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, jaimeLannister, reactBasics, 6, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, branstark, reactBasics, 9, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sandorclegane, reactBasics, 9, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, melisandre, reactBasics, 10, "Texto exemplo de motivo", LocalDateTime.now()),

                //NPS sqlBasics -25
                new CourseFeedback(null, aryaStark, sqlBasics, 7, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, tyrionLannister, sqlBasics, 7, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, cerseiLannister, sqlBasics, 4, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sansaStark, sqlBasics, 5, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, jaimeLannister, sqlBasics, 6, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, branstark, sqlBasics, 3, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sandorclegane, sqlBasics, 9, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, melisandre, sqlBasics, 9, "Texto exemplo de motivo", LocalDateTime.now()),

                //NPS htmlCss -87,5
                new CourseFeedback(null, aryaStark, htmlCss, 8, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, tyrionLannister, htmlCss, 0, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, cerseiLannister, htmlCss, 1, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sansaStark, htmlCss, 2, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, jaimeLannister, htmlCss, 3, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, branstark, htmlCss, 4, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, sandorclegane, htmlCss, 5, "Texto exemplo de motivo", LocalDateTime.now()),
                new CourseFeedback(null, melisandre, htmlCss, 6, "Texto exemplo de motivo", LocalDateTime.now())
        );
        courseFeedbackRepository.saveAll(courseFeedbacks);
    }
}