package work.xiaoying.entity;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class Project {
    private Integer id;
    private String name;
    private List<Project> projects;

    public static void main(String[] args) {
        Project p1 = new Project();
        Project p2 = new Project();
        p1.setProjects(Collections.singletonList(p2));
        p2.setProjects(Collections.singletonList(p1));
        System.out.println(p1.hashCode());
    }
}
