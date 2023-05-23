/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uni.model.OurUser;
import uni.service.UserService;
import uni.service.UserServiceInterface;


public class UserRegister extends HttpServlet {


    final String STARTPAGE = "/StudentAdmissionAPP/index.html";
    private SendEmail sendEmail;
    
    /*<private int generateRandomNumber(){
        Random rand = new Random();
        int randomNum  = rand.nextInt(90000)+10000;
        return randomNum;
    }*/
    
    public void init(){
        String username = "mmaestro1738@gmail.com";
        String password = "xyzlldrikikblrwg";
        sendEmail = new SendEmail(username, password);
    }
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            OurUser user = new OurUser();
            UserServiceInterface userInterface = new UserService();
            user = userInterface.checkExistance(email);
            if(user == null){
                OurUser newUser = new OurUser(email,firstName,lastName,password);
                //int verification = generateRandomNumber();
                UserServiceInterface inter = new UserService();
                inter.createAccount(newUser);
                String subject = "Registered Successfully";
                String content = "Dear "+lastName 
                        + " \nYour registration have been completed with a success." 
                        + " \nYou can now login using your email and password!";
                        //+verification+"\n";
                
                try{
                    HttpSession session = request.getSession(true);
                    request.getSession().setAttribute("lastname", lastName);
                    //request.getSession().setAttribute("verification", verification);
                    request.getSession().setAttribute("email", email);
                    sendEmail.sendingEmail(email, subject, content);
                    response.sendRedirect("/StudentAdmissionApp/login.html");
                }catch(MessagingException ex){
                    response.getWriter().write("Failed to send");
                    ex.printStackTrace();
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
