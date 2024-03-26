package dev.germani.code_school.modules.course.dto;

import java.util.List;

import dev.germani.code_school.modules.course.entities.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCoursesResponseDTO {

    private List<CourseEntity> courses;
}

