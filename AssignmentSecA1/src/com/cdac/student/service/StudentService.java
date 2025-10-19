package com.cdac.student.service;

import com.cdac.student.exception.AttendanceException;
import com.cdac.student.model.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.io.*;
import java.util.*;
public class StudentService {

public void validAttendance(Student s) throws AttendanceException
{
    if(s.getAttendancePercentage()<60)
        throw new AttendanceException("attendance is less than 60% for "+s.getSname());
}

public void saveStudentsToFile(List<Student> students, String filename)
{
  try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
  {
      oos.writeObject(students);
      System.out.println("✅ Students saved successfully to " + filename);
  }catch (IOException e)
  {
      e.printStackTrace();
  }
}

    @SuppressWarnings("unchecked")
    public List<Student> readStudentsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Sort in decreasing order of attendance
    public List<Student> sortByAttendanceDescending(List<Student> students) {
        students.sort((s1, s2) -> Double.compare(s2.getAttendancePercentage(), s1.getAttendancePercentage()));
        return students;
    }

}
