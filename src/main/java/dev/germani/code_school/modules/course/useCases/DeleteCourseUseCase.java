package dev.germani.code_school.modules.course.useCases;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.germani.code_school.exceptions.CourseFoundException;
import dev.germani.code_school.exceptions.CourseNotFoundException;
import dev.germani.code_school.modules.course.repositories.CourseRepository;

@Service
public class DeleteCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID courseId) {

        try {
            if (courseId == null) {
                throw new IllegalArgumentException("Course ID cannot be null");
            }

            if (!this.courseRepository.existsById(courseId)) {
                throw new CourseNotFoundException();
            }

            this.courseRepository.deleteById(courseId);
        } catch (IllegalArgumentException e) {
            throw new CourseFoundException();
        }
    }
}
