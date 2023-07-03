package com.studentmanagement.serviceImpl;

import com.studentmanagement.entities.AssignCourse;
import com.studentmanagement.entities.Courses;
import com.studentmanagement.entities.Users;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.repositories.AssignCourseRepository;
import com.studentmanagement.repositories.CoursesRepository;
import com.studentmanagement.repositories.UserRepository;
import com.studentmanagement.services.AssignCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AssignCourseServiceImp implements AssignCourseService {

    @Autowired
    AssignCourseRepository assignCourseRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CoursesRepository coursesRepository;

    @Override
    public AssignCourse createAssignCourse(AssignCourse assignCourse,Principal principal){
        if(assignCourse.getStudentId()==null || assignCourse.getCourses().isEmpty())
           throw  new ResourceNotFoundException("studentId/Courses are empty");

       Optional<Users> userDao = userRepository.findById(assignCourse.getStudentId());
        if(userDao.get() ==null)
            throw  new ResourceNotFoundException("Student","Student",assignCourse.getStudentId());

        Users users = userDao.get();
       List<Courses>courses = coursesRepository.findByIdIn(assignCourse.getCourses());
       String createdBy = principal.getName();
       Users loginUser = userRepository.findByEmail(createdBy);
        long courses1 = (users.getCourse()!=null)?users.getCourse().stream().filter(e->e.getCreatedBy().equals(createdBy)).count():0;
        long loginUserRole = loginUser.getRoles().stream().filter(role->role.getName().equals("Student")).count();
        if(courses1!=0 && loginUserRole!=0)
            throw new ResourceNotFoundException("Student can assign one course only");
        List<Courses>coursesList = new ArrayList<>();
        courses.forEach(e->{
            Courses course = e;
            course.setCreatedBy(createdBy);
            coursesList.add(course);
        });
        if(users.getCourse()!=null && !users.getCourse().isEmpty()) {
            users.getCourse().forEach(e -> {
                if (!assignCourse.getCourses().contains(e.getId()))
                    coursesList.add(e);
            });
        }
        users.setCourse(coursesList);
        userRepository.save(users);
        return assignCourse;
    }

    @Override
    public List<Users> searchAssignCourse(String searchCourseName) {
        if(searchCourseName==null)
            new ResourceNotFoundException("Course","Course",searchCourseName);

        List<Users> courseListSearch = userRepository.findByCouseName(searchCourseName);
    return courseListSearch;
    }


    @Override
    public void deleteAssignCourse(String studentId,String assignCourseId) {
        if(studentId==null)
           throw  new ResourceNotFoundException("Student","Student",studentId);
        if(assignCourseId==null)
           throw  new ResourceNotFoundException("Course","Course","null");
        Optional<Users> userDao = userRepository.findById(studentId);
        if(userDao.get() == null)
           throw  new ResourceNotFoundException("Student","Student",studentId);
        Users users = userDao.get();
        List<Courses>listCourse = users.getCourse().stream().filter(e -> !e.getId().equals(assignCourseId)).collect(Collectors.toList());
        users.setCourse(listCourse);
        userRepository.save(users);
    }
}
