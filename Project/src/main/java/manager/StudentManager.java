package manager;

import entity.StudentEntity;
import java.util.List;

public interface StudentManager {
    public String create(StudentEntity studentEntity);
    public StudentEntity get(int id);
    public List<StudentEntity> getAll();
}
