package mappers;

import Entities.Student;
import java.util.List;

/**
 *
 * @author Joe
 */
public class StudentInfo {
    public String fullname;
    public long studentId;
    public String classNameThisSemester;
    public String classDescription;
    
    public StudentInfo(Student s){
        this.fullname = s.getFirstname() + " " + s.getLastname();
        this.studentId = s.getId();
        if(s.getSemester() != null) {
            this.classNameThisSemester = s.getSemester().getName();
            this.classDescription = s.getSemester().getDescription();
        }
    }

    @Override
    public String toString() {
        return "StudentInfo{" + "fullname=" + fullname + ", studentId=" + studentId + ", classNameThisSemester=" + classNameThisSemester + ", classDescription=" + classDescription + '}';
    }
}
