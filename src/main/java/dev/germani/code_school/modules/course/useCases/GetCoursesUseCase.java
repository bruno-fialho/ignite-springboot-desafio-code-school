package dev.germani.code_school.modules.course.useCases;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.germani.code_school.modules.course.entities.Category;
import dev.germani.code_school.modules.course.entities.CourseEntity;
import dev.germani.code_school.modules.course.repositories.CourseRepository;

@Service
public class GetCoursesUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> execute() {
        return this.courseRepository.findAll();
    }

    public CourseEntity executeById(UUID id) {
        if (id == null) {
            return null;
        }

        return this.courseRepository.findById(id).orElse(null);
    }

    public List<CourseEntity> executeWithFilters(String name, Category category) {
        Stream<CourseEntity> courseStream = courseRepository.findAll().stream();

        if (name != null) {
            courseStream = courseStream
                    .filter(course -> course.getName().toLowerCase().contains(name.toLowerCase()));
        }

        if (category != null) {
            courseStream = courseStream.filter(course -> course.getCategory() == category);
        }

        return courseStream.collect(Collectors.toList());
    }
}
