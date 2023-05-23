/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import uni.model.OurUser;

/**
 *
 * @author yves
 */
public interface UserServiceInterface extends Remote{
    public boolean createAccount(OurUser user) throws RemoteException;
    public OurUser checkExistance(String userEmail) throws RemoteException;
    public OurUser authenticateUser(String userEmail, String password) throws RemoteException;
}
