package dev.germani.code_school.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course not found!");
    }
}
