/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student implements Serializable{
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    //private int StudentID;
    private String names;
    private Date dob;
    private String gender;
    
    private byte[] photo;
    private String phone;
    private String email;
    //private String nextOfKin;
    private String address;
    //private String highName;
    //private Date startHigh;
    //private Date endHigh;
    private byte[] diploma;
    private String faculty;
    //private String department;
    //private String mode;

    public Student() {
    }

    public Student(String names, Date dob, String gender, int id, byte[] photo, String phone, String email, String address, byte[] diploma, String faculty) {
        this.names = names;
        this.dob = dob;
        this.gender = gender;
        this.id = id;
        this.photo = photo;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.diploma = diploma;
        this.faculty = faculty;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getDiploma() {
        return diploma;
    }

    public void setDiploma(byte[] diploma) {
        this.diploma = diploma;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    
}
