/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import uni.dao.StudentDAO;
import uni.model.Student;

public class StudentService extends UnicastRemoteObject implements StudentServiceInterface{

    public StudentService() throws RemoteException {
    }

    @Override
    public boolean createAccount(Student student) throws RemoteException {
        StudentDAO dao = new StudentDAO();
        dao.createAccount(student);
        return true;
    }

    @Override
    public Student checkExistance(String userEmail) throws RemoteException {
        StudentDAO dao = new StudentDAO();
        Student student = new Student();
        student = dao.checkExistance(userEmail);
        return student;
    }
    
}

        