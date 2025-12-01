package com.example.demo;

import com.example.Service.TeacherService;
import com.example.dto.TeacherDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    void getAllTest() {
        List<TeacherDto> teachers = teacherService.getAll();

        Assertions.assertNotNull(teachers);
        Assertions.assertNotEquals(0, teachers.size());

        for (TeacherDto dto : teachers) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getFirstNameTeacher());
            Assertions.assertNotNull(dto.getLastNameTeacher());
            Assertions.assertNotNull(dto.getExperience());
        }
    }

    @Test
    void getByIdTest() {
        List<TeacherDto> all = teacherService.getAll();
        Assertions.assertNotEquals(0, all.size());

        Random random = new Random();
        Long id = all.get(random.nextInt(all.size())).getId();

        TeacherDto dto = teacherService.getById(id);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getFirstNameTeacher());
        Assertions.assertNotNull(dto.getLastNameTeacher());
        Assertions.assertNotNull(dto.getExperience());

        TeacherDto notFound = teacherService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addTest() {
        TeacherDto dto = new TeacherDto();
        dto.setFirstNameTeacher("TestTeach");
        dto.setLastNameTeacher("TeachLast");
        dto.setExperience(10);

        TeacherDto created = teacherService.create(dto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(dto.getFirstNameTeacher(), created.getFirstNameTeacher());
        Assertions.assertEquals(dto.getLastNameTeacher(), created.getLastNameTeacher());
        Assertions.assertEquals(dto.getExperience(), created.getExperience());

        TeacherDto fromDb = teacherService.getById(created.getId());
        Assertions.assertNotNull(fromDb);
    }

    @Test
    void updateTest() {
        List<TeacherDto> all = teacherService.getAll();
        Assertions.assertNotEquals(0, all.size());

        Random random = new Random();
        Long id = all.get(random.nextInt(all.size())).getId();

        TeacherDto dto = new TeacherDto();
        dto.setFirstNameTeacher("UpdatedTeach");
        dto.setLastNameTeacher("UpdatedLast");
        dto.setExperience(20);

        TeacherDto updated = teacherService.update(id, dto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("UpdatedTeach", updated.getFirstNameTeacher());
        Assertions.assertEquals("UpdatedLast", updated.getLastNameTeacher());
        Assertions.assertEquals(20, updated.getExperience());
    }

    @Test
    void deleteTest() {
        List<TeacherDto> all = teacherService.getAll();
        Assertions.assertNotEquals(0, all.size());

        Random random = new Random();
        Long id = all.get(random.nextInt(all.size())).getId();

        teacherService.delete(id);

        TeacherDto dto = teacherService.getById(id);
        Assertions.assertNull(dto);
    }
}
