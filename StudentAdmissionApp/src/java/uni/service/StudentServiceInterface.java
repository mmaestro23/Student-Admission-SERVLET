/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import uni.model.Student;

/**
 *
 * @author yves
 */
public interface StudentServiceInterface extends Remote{
    public boolean createAccount(Student user) throws RemoteException;
    public Student checkExistance(String userEmail) throws RemoteException;
}
