package com.example.demo;

import com.example.dto.StudentDto;
import com.example.Service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void getAllTest() {

        List<StudentDto> list = studentService.getAll();

        Assertions.assertNotNull(list);
        Assertions.assertNotEquals(0, list.size());

        for (StudentDto dto : list) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getFirstNameStudent());
            Assertions.assertNotNull(dto.getLastNameStudent());
            Assertions.assertNotNull(dto.getAge());
        }
    }

    @Test
    void getByIdTest() {

        List<StudentDto> all = studentService.getAll();
        Assertions.assertNotEquals(0, all.size());

        Random random = new Random();
        Long id = all.get(random.nextInt(all.size())).getId();

        StudentDto dto = studentService.getById(id);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertNull(studentService.getById(-1L));
    }

    @Test
    void addTest() {

        StudentDto dto = new StudentDto();
        dto.setFirstNameStudent("Serik");
        dto.setLastNameStudent("Arman");
        dto.setAge(22);

        StudentDto saved = studentService.add(dto);

        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(dto.getFirstNameStudent(), saved.getFirstNameStudent());
        Assertions.assertEquals(dto.getLastNameStudent(), saved.getLastNameStudent());
        Assertions.assertEquals(dto.getAge(), saved.getAge());

        StudentDto found = studentService.getById(saved.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(saved.getId(), found.getId());
    }

    @Test
    void updateTest() {

        List<StudentDto> all = studentService.getAll();
        Assertions.assertNotEquals(0, all.size());

        Random random = new Random();
        Long id = all.get(random.nextInt(all.size())).getId();

        StudentDto dto = new StudentDto();
        dto.setFirstNameStudent("UpdatedName");
        dto.setLastNameStudent("UpdatedLast");
        dto.setAge(30);

        StudentDto updated = studentService.update(id, dto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(id, updated.getId());
        Assertions.assertEquals("UpdatedName", updated.getFirstNameStudent());
        Assertions.assertEquals("UpdatedLast", updated.getLastNameStudent());
        Assertions.assertEquals(30, updated.getAge());
    }

    @Test
    void deleteTest() {

        List<StudentDto> all = studentService.getAll();
        Assertions.assertNotEquals(0, all.size());
        Random random = new Random();
        Long id = all.get(random.nextInt(all.size())).getId();
        Assertions.assertTrue(studentService.delete(id));
        Assertions.assertNull(studentService.getById(id));
    }
}
