package com.situ.javaWeb.vo;

public class StudentBanji {
    private int studentId;
    private String studentName;
    private String studentSex;
    private int studentAge;
    private String banjiName;

    public StudentBanji() {
    }

    public StudentBanji(int studentId, String studentName, String studentSex, int studentAge, String banjiName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentAge = studentAge;
        this.banjiName = banjiName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getBanjiName() {
        return banjiName;
    }

    public void setBanjiName(String banjiName) {
        this.banjiName = banjiName;
    }

    @Override
    public String toString() {
        return "StudentBanji{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentSex='" + studentSex + '\'' +
                ", studentAge=" + studentAge +
                ", banjiName=" + banjiName +
                '}';
    }
}
