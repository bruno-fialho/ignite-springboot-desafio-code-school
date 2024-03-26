package dev.germani.code_school.modules.course.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.germani.code_school.modules.course.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    Optional<CourseEntity> findByName(String name);
}
