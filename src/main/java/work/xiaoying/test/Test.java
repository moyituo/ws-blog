package work.xiaoying.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        CsvData data = CsvData.builder().deptName("销售").teacherId(1).teacherName("张三").studentId(1).studentName("张小三").build();
        CsvData data1 = CsvData.builder().deptName("销售").teacherId(1).teacherName("张三").studentId(1).studentName("赵六").build();
        CsvData data2 = CsvData.builder().deptName("销售").teacherId(2).teacherName("李四").studentId(1).studentName("张小四").build();
        List<CsvData> csvData = Arrays.asList(data, data1, data2);
        Map<String,Dept> deptMap=new HashMap<>();
        Map<Integer,Teacher> teacherMap=new HashMap<>();
        csvData.forEach(d->{
            Teacher teacher = teacherMap.get(d.getTeacherId());
            if (null==teacher){
                Dept dept=deptMap.computeIfAbsent(d.getDeptName(),key->Dept.builder().deptName(d.getDeptName()).teachers(new ArrayList<>()).build());
                teacher=Teacher.builder().id(d.getTeacherId()).teachName(d.getTeacherName()).students(new ArrayList<>()).build();
                List<Teacher> teachers = dept.getTeachers();
                teachers.add(teacher);
            }
            teacher.getStudents().add(Student.builder().id(d.getStudentId()).stuName(d.getStudentName()).build());
        });
        List<Dept> deptList=new ArrayList<>(deptMap.values());
        System.out.println(deptList);
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class CsvData {
    String deptName;
    Integer teacherId;
    String teacherName;
    Integer studentId;
    String studentName;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Dept {
    String deptName;
    List<Teacher> teachers;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Teacher {
    Integer id;
    String teachName;
    List<Student> students;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Student {
    Integer id;
    String stuName;
}