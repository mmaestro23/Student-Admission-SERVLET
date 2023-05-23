/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import uni.model.Student;
import uni.service.StudentService;
import uni.service.StudentServiceInterface;


@MultipartConfig
public class Admission extends HttpServlet {
    private SendEmail sendEmail;
    public void init(){
        String username = "mmaestro1738@gmail.com";
        String password = "xyzlldrikikblrwg";
        sendEmail = new SendEmail(username, password);
    }
    public byte[] convertToByteArray(InputStream input) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = input.read(buffer)) != -1) {
        output.write(buffer, 0, bytesRead);
    }
    return output.toByteArray();
}

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession session = request.getSession(false);
        
        //boolean isAuthenticated = (session != null && session.getAttribute("isLoggedIn") != null);
        if(session == null || session.getAttribute("isLoggedIn") == null || !(boolean) session.getAttribute("isLoggedIn")){
            // User is not logged in, redirect to login page
            response.sendRedirect("login.html");
        }else{
            try {
                String names = request.getParameter("username");
                String date = request.getParameter("dob");
                Date dob = formatter.parse(date);
                String gender = request.getParameter("gender");
                int id = Integer.parseInt(request.getParameter("nID"));
                
                //I receive a picture
                Part photo = request.getPart("photo");
                InputStream photoContent = photo.getInputStream();
                byte[] photoBytes = convertToByteArray(photoContent);
                
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String address = request.getParameter("address");

                // I receive pdf
                Part diploma = request.getPart("diploma");
                InputStream diplomaContent = diploma.getInputStream();
                byte[] diplomaBytes = convertToByteArray(diplomaContent);
                
                String faculty = request.getParameter("faculty");

                Student student = new Student();
                StudentServiceInterface studentInterface = new StudentService();
                student = studentInterface.checkExistance(email);
                if(student == null){
                    Student newStudent = new Student(
                            names,
                            dob,
                            gender,
                            id,
                            photoBytes,
                            phone,
                            email,
                            address,
                            diplomaBytes,
                            faculty
                    );
                    StudentServiceInterface inter = new StudentService();
                    inter.createAccount(newStudent);
                    request.getSession().setAttribute(names, "names");
                String subject = "Admission application received";
                String content = "Dear "+names 
                        + " \nI have successfully received your application. \n"
                        +"I'll get back to you as soon as possible";
                        
                        sendEmail.sendingEmail(email, subject, content);
                        System.out.println("Email sent");
                        
                        request.getSession().setAttribute("successMessage", "Your submission was successful!");
                    
                    response.sendRedirect("done.html");
                }
            } catch (ParseException | MessagingException ex) {
                Logger.getLogger(Admission.class.getName()).log(Level.SEVERE, null, ex);
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
        // Check if the token in the URL matches the token stored in the session
        String token = request.getParameter("token");
        HttpSession session = request.getSession();
        if (token == null || !token.equals(session.getAttribute("admissionToken"))) {
            response.sendRedirect("login.html"); // or redirect to login page
            return;
        }
        // Remove the token from the user's session to limit its usage
        session.removeAttribute("admissionToken");
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
         // Generate a new token and store it in the session
        String token = generateToken();
        HttpSession session = request.getSession();
        session.setAttribute("admissionToken", token);

        // Redirect the user to the admission page with the token in the URL
        //response.sendRedirect("/Admission?token=" + token);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    private String generateToken() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
