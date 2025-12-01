package com.example.demo;

import com.example.mapper.TeacherMapper;
import com.example.Entity.TeacherEntity;
import com.example.dto.TeacherDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TeacherMapperTest {

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    void convertEntityToDtoTest() {
        Teacher teacher = new Teacher(1, "Erman", 10);
        TeacherDto dto = teacherMapper.toDto(teacher);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(teacher.getId(), dto.getId());
        Assertions.assertEquals(teacher.getName(), dto.getName());
        Assertions.assertEquals(teacher.getExperience(), dto.getExperience());
    }

    @Test
    void convertDtoToEntityTest() {
        TeacherDto dto = new TeacherDto(1, "Erman", 10);
        Teacher teacher = teacherMapper.toEntity(dto);

        Assertions.assertNotNull(teacher);
        Assertions.assertEquals(dto.getId(), teacher.getId());
        Assertions.assertEquals(dto.getName(), teacher.getName());
        Assertions.assertEquals(dto.getExperience(), teacher.getExperience());
    }

    @Test
    void convertEntityListToDtoList() {
        List<Teacher> entityList = new ArrayList<>();
        entityList.add(new Teacher(1, "Erman", 10));
        entityList.add(new Teacher(2, "Sayat", 8));
        entityList.add(new Teacher(3, "Ayan", 15));

        List<TeacherDto> dtoList = teacherMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i < entityList.size(); i++) {
            Teacher teacher = entityList.get(i);
            TeacherDto dto = dtoList.get(i);

            Assertions.assertEquals(teacher.getId(), dto.getId());
            Assertions.assertEquals(teacher.getName(), dto.getName());
            Assertions.assertEquals(teacher.getExperience(), dto.getExperience());
        }
    }
}

