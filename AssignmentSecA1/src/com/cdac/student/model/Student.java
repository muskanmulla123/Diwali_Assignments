package com.cdac.student.model;

import com.cdac.student.exception.AttendanceException;

import java.io.Serializable;

public class Student implements Serializable
{

    private int rollNo;
    private String sname;
    private String course;
    private double attendancePercentage;
    private double score;

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(int rollNo, String sname, String course, double attendancePercentage, double score) {
        this.rollNo = rollNo;
        this.sname = sname;
        this.course = course;
        this.attendancePercentage = attendancePercentage;
        this.score = score;
    }
    public int getRollNo() { return rollNo; }
    public String getSname() { return sname; }
    public String getCourse() { return course; }
    public double getAttendancePercentage() { return attendancePercentage; }
    public double getScore() { return score; }

    // Grade calculation
    public String calculateGrade() {
        if (attendancePercentage < 60) {
            throw new AttendanceException("Attendance below 60%. Not eligible for grading!");
        }

        if (score >= 90) return "A+";
        else if (score >= 80) return "A";
        else if (score >= 70) return "B";
        else if (score >= 60) return "C";
        else return "Fail";
    }

    // toString method
    @Override
    public String toString() {
        return rollNo + " | " + sname + " | " + course + " | "
                + attendancePercentage + "% | Score: " + score
                + " | Grade: " + safeGrade();
    }

    // helper to avoid exception in toString
    private String safeGrade() {
        try {
            return calculateGrade();
        } catch (AttendanceException e) {
            return "Not Eligible";
        }
    }


}




