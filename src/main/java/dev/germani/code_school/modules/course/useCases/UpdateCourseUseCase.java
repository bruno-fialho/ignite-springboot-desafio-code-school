package dev.germani.code_school.modules.course.useCases;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.germani.code_school.exceptions.CourseFoundException;
import dev.germani.code_school.exceptions.CourseNotFoundException;
import dev.germani.code_school.modules.course.entities.CourseEntity;
import dev.germani.code_school.modules.course.repositories.CourseRepository;
import lombok.NonNull;

@Service
public class UpdateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(@NonNull CourseEntity courseEntity) {

        try {
            UUID courseId = courseEntity.getId();
            if (courseId == null) {
                throw new IllegalArgumentException("Course ID cannot be null");
            }

            if (!this.courseRepository.existsById(courseId)) {
                throw new CourseNotFoundException();
            }

            return this.courseRepository.save(courseEntity);
        } catch (IllegalArgumentException e) {
            throw new CourseFoundException();
        }
    }
}
