package com.cdac.student.TestMain;

import com.cdac.student.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMain {


    public static void main(String args[]) {
        List<Student> students = new ArrayList<>();

        students.add(new Student(1, "Muskan", "Java", 85, 90));
        students.add(new Student(2, "Sohail", "Python", 70, 75));
        students.add(new Student(3, "Riya", "DotNet", 50, 80)); // low attendance
        students.add(new Student(4, "Rahul", "React", 95, 88));
        students.add(new Student(5, "Amit", "Angular", 65, 67));
        students.add(new Student(6, "Sana", "Java", 92, 95));
        students.add(new Student(7, "Karan", "C++", 55, 60));
        students.add(new Student(8, "Nisha", "ML", 77, 85));
        students.add(new Student(9, "Anil", "AI", 82, 89));
        students.add(new Student(10, "Tina", "Spring", 99, 97));

        // Sort by attendance in decreasing order
        students.sort((s1, s2) -> Double.compare(s2.getAttendancePercentage(), s1.getAttendancePercentage()));

        // ✅ Serialize (write to file)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            oos.writeObject(students);
            System.out.println("✅ Students saved successfully to students.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ✅ Deserialize (read from file)
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
            List<Student> readStudents = (List<Student>) ois.readObject();

            System.out.println("\n--- Students Read Back from File ---");
            for (Student s : readStudents) {
                System.out.println(s);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}