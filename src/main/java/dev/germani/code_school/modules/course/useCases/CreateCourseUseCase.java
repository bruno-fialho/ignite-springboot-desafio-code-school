package dev.germani.code_school.modules.course.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.germani.code_school.exceptions.CourseFoundException;
import dev.germani.code_school.modules.course.entities.CourseEntity;
import dev.germani.code_school.modules.course.repositories.CourseRepository;
import lombok.NonNull;

@Service
public class CreateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(@NonNull CourseEntity courseEntity) {
        this.courseRepository.findByName(courseEntity.getName()).ifPresent(course -> {
            throw new CourseFoundException();
        });

        return this.courseRepository.save(courseEntity);
    }
}
