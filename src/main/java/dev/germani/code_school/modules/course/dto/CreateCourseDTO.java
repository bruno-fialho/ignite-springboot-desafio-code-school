package dev.germani.code_school.modules.course.dto;

import dev.germani.code_school.modules.course.entities.Category;

import lombok.Data;

@Data
public class CreateCourseDTO {

    private String name;
    private Category category;
}
