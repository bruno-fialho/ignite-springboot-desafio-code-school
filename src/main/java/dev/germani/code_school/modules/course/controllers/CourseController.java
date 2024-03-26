package dev.germani.code_school.modules.course.controllers;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import dev.germani.code_school.exceptions.CourseNotFoundException;
import dev.germani.code_school.modules.course.dto.CreateCourseDTO;
import dev.germani.code_school.modules.course.dto.DeleteCourseResponseDTO;
import dev.germani.code_school.modules.course.dto.GetCoursesResponseDTO;
import dev.germani.code_school.modules.course.dto.UpdateCourseDTO;
import dev.germani.code_school.modules.course.entities.Category;
import dev.germani.code_school.modules.course.entities.CourseEntity;
import dev.germani.code_school.modules.course.useCases.CreateCourseUseCase;
import dev.germani.code_school.modules.course.useCases.DeleteCourseUseCase;
import dev.germani.code_school.modules.course.useCases.GetCoursesUseCase;
import dev.germani.code_school.modules.course.useCases.UpdateCourseUseCase;

@RestController
@RequestMapping("/curso")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @Autowired
    private GetCoursesUseCase getCoursesUseCase;

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;

    @PostMapping()
    public CourseEntity create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        var courseEntity = CourseEntity.builder().name(createCourseDTO.getName())
                .category(createCourseDTO.getCategory()).build();

        return createCourseUseCase.execute(courseEntity);
    }

    @GetMapping()
    public GetCoursesResponseDTO get(@RequestParam(required = false) String name,
            @RequestParam(required = false) Category category) {

        Category categoryEnum = null;
        if (category != null && !"".equals(category.toString())) {
            try {
                categoryEnum = Category.valueOf(category.toString().toUpperCase());
            } catch (IllegalArgumentException e) {
                return new GetCoursesResponseDTO(null);
            }
        }

        var courses = getCoursesUseCase.executeWithFilters(name, categoryEnum);

        return new GetCoursesResponseDTO(courses);
    }

    @PutMapping("/{id}")
    public CourseEntity update(@PathVariable UUID id,
            @Valid @RequestBody UpdateCourseDTO updateCourseDTO) {

        try {
            CourseEntity existingCourse = getCoursesUseCase.executeById(id);

            if (existingCourse == null) {
                throw new CourseNotFoundException();
            }

            if (updateCourseDTO.getName() != null) {
                existingCourse.setName(updateCourseDTO.getName());
            }

            if (updateCourseDTO.getCategory() != null) {
                existingCourse.setCategory(updateCourseDTO.getCategory());
            }

            return updateCourseUseCase.execute(existingCourse);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping("/{id}")
    public DeleteCourseResponseDTO delete(@PathVariable UUID id) {

        try {
            CourseEntity existingCourse = getCoursesUseCase.executeById(id);

            if (existingCourse == null) {
                throw new CourseNotFoundException();
            }

            deleteCourseUseCase.execute(id);

            return new DeleteCourseResponseDTO("Course deleted successfully");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    @PatchMapping("/{id}/active")
    public CourseEntity update(@PathVariable UUID id) {

        try {
            CourseEntity existingCourse = getCoursesUseCase.executeById(id);

            if (existingCourse == null) {
                throw new CourseNotFoundException();
            }

            if (existingCourse.getActive() != null) {
                boolean isActive = existingCourse.getActive();

                existingCourse.setActive(!isActive);
            }

            return updateCourseUseCase.execute(existingCourse);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}
