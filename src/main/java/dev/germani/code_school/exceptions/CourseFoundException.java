package dev.germani.code_school.exceptions;

public class CourseFoundException extends RuntimeException {
    public CourseFoundException() {
        super("Course already exists!");
    }
}
