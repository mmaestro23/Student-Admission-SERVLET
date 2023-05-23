/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import uni.dao.UserDAO;
import uni.model.OurUser;

/**
 *
 * @author yves
 */
public class UserService extends UnicastRemoteObject implements UserServiceInterface{

    public UserService() throws RemoteException {
    }

    @Override
    public boolean createAccount(OurUser user) throws RemoteException {
        UserDAO dao = new UserDAO();
        dao.createAccount(user);
        return true; 
    }

    @Override
    public OurUser checkExistance(String userEmail) throws RemoteException {
        UserDAO dao = new UserDAO();
        OurUser result = new OurUser();
        result = dao.checkExistance(userEmail);
        return result;
    }

    @Override
    public OurUser authenticateUser(String userEmail, String password) throws RemoteException {
        UserDAO dao = new UserDAO();
        OurUser result = new OurUser();
        result = dao.authenticateUser(userEmail, password);
        return result;
    }
    
    
}
