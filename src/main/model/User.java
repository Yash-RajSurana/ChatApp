package main.model;
import java.sql.Timestamp;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String enroll;
    private String gender;
    private String course;
    private int yop;
    private Timestamp createdAt;

    public User() {}

    public User(int id, String name, int yop) {
        this.id = id;
        this.name = name;
        this.yop = yop;
    }

    public User(int id, String name, String email, String password, String enroll, String gender, String course, int yop) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enroll = enroll;
        this.gender = gender;
        this.course = course;
        this.yop = yop;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYop() {
        return yop;
    }

    public void setYop(int yop) {
        this.yop = yop;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "User {" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Enroll='" + enroll + '\'' +
                ", Gender='" + gender + '\'' +
                ", Course='" + course + '\'' +
                ", YOP=" + yop +
                ", Created At=" + createdAt +
                '}';
    }

}
