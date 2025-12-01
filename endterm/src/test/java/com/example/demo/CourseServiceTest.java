package com.example.demo;

import com.example.Service.CourseService;
import com.example.dto.CourseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    void getAllTest() {
        List<CourseDto> courses = courseService.getAll();

        Assertions.assertNotNull(courses);
        Assertions.assertNotEquals(0, courses.size());

        for (CourseDto dto : courses) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getCourseName());
            Assertions.assertNotNull(dto.getDuration());
        }
    }

    @Test
    void getByIdTest() {
        List<CourseDto> all = courseService.getAll();
        Assertions.assertNotEquals(0, all.size());

        Random random = new Random();
        Long id = all.get(random.nextInt(all.size())).getId();

        CourseDto dto = courseService.getById(id);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getCourseName());
        Assertions.assertNotNull(dto.getDuration());

        CourseDto notFound = courseService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addTest() {
        CourseDto dto = new CourseDto();
        dto.setCourseName("Spring Boot Course");
        dto.setDuration(60);

        CourseDto created = courseService.create(dto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(dto.getCourseName(), created.getCourseName());
        Assertions.assertEquals(dto.getDuration(), created.getDuration());
    }

    @Test
    void updateTest() {
        List<CourseDto> all = courseService.getAll();
        Assertions.assertNotEquals(0, all.size());

        Random random = new Random();
        Long id = all.get(random.nextInt(all.size())).getId();

        CourseDto dto = new CourseDto();
        dto.setCourseName("UpdatedCourse");
        dto.setDuration(90);

        CourseDto updated = courseService.update(id, dto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("UpdatedCourse", updated.getCourseName());
        Assertions.assertEquals(90, updated.getDuration());
    }

    @Test
    void deleteTest() {
        List<CourseDto> all = courseService.getAll();
        Assertions.assertNotEquals(0, all.size());

        Random random = new Random();
        Long id = all.get(random.nextInt(all.size())).getId();

        courseService.delete(id);

        CourseDto dto = courseService.getById(id);
        Assertions.assertNull(dto);
    }
}
