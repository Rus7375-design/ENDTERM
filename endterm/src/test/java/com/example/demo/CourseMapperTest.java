package com.example.demo;

import com.example.Mapper.CourseMapper;
import com.example.Entity.CourseEntity;
import com.example.dto.CourseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CourseMapperTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void convertEntityToDtoTest() {
        Course course = new Course(1, "Math", 30);
        CourseDto dto = courseMapper.toDto(course);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(course.getId(), dto.getId());
        Assertions.assertEquals(course.getName(), dto.getName());
        Assertions.assertEquals(course.getHours(), dto.getHours());
    }

    @Test
    void convertDtoToEntityTest() {
        CourseDto dto = new CourseDto(1, "Math", 30);
        Course course = courseMapper.toEntity(dto);

        Assertions.assertNotNull(course);
        Assertions.assertEquals(dto.getId(), course.getId());
        Assertions.assertEquals(dto.getName(), course.getName());
        Assertions.assertEquals(dto.getHours(), course.getHours());
    }

    @Test
    void convertEntityListToDtoList() {
        List<Course> entityList = new ArrayList<>();
        entityList.add(new Course(1, "Math", 30));
        entityList.add(new Course(2, "Teoria", 40));
        entityList.add(new Course(3, "History", 35));

        List<CourseDto> dtoList = courseMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i < entityList.size(); i++) {
            Course course = entityList.get(i);
            CourseDto dto = dtoList.get(i);

            Assertions.assertEquals(course.getId(), dto.getId());
            Assertions.assertEquals(course.getName(), dto.getName());
            Assertions.assertEquals(course.getHours(), dto.getHours());
        }
    }
}

