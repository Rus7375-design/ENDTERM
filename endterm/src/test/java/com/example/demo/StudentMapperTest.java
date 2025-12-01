package com.example.demo;

import com.example.Mapper.StudentMapper;
import com.example.Entity.StudentEntity;
import com.example.dto.StudentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void convertEntityToDtoTest() {
        StudentEntity student = new StudentEntity();
        student.setId(1);
        student.setFirstNameEntity("Arman");
        student.setLastNameEntity("Aslanuly");
        student.setAge(20);

        StudentDto dto = studentMapper.toDto(student);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(student.getId(), dto.getId());
        Assertions.assertEquals(student.getFirstNameEntity(), dto.getFirstName());
        Assertions.assertEquals(student.getLastNameEntity(), dto.getLastName());
        Assertions.assertEquals(student.getAge(), dto.getAge());
    }

    @Test
    void convertDtoToEntityTest() {
        StudentDto dto = new StudentDto(1, "Arman", "Aslanuly", 20);
        StudentEntity student = studentMapper.toEntity(dto);

        Assertions.assertNotNull(student);
        Assertions.assertEquals(dto.getId(), student.getId());
        Assertions.assertEquals(dto.getFirstName(), student.getFirstNameEntity());
        Assertions.assertEquals(dto.getLastName(), student.getLastNameEntity());
        Assertions.assertEquals(dto.getAge(), student.getAge());
    }

    @Test
    void convertEntityListToDtoList() {
        List<StudentEntity> entityList = new ArrayList<>();
        entityList.add(new StudentEntity(1, "Arman", "Asllanuly", 20, null));
        entityList.add(new StudentEntity(2, "Janibek", "Serikuly", 22, null));

        List<StudentDto> dtoList = studentMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i < entityList.size(); i++) {
            StudentEntity student = entityList.get(i);
            StudentDto dto = dtoList.get(i);

            Assertions.assertEquals(student.getId(), dto.getId());
            Assertions.assertEquals(student.getFirstNameEntity(), dto.getFirstName());
            Assertions.assertEquals(student.getLastNameEntity(), dto.getLastName());
            Assertions.assertEquals(student.getAge(), dto.getAge());
        }
    }
}
