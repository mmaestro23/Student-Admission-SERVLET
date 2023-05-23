package uni.controller;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ConfirmEmail extends HttpServlet {
    private SendEmail sendEmail;
    
    public void init(){
        String username = "mmaestro1738@gmail.com";
        String password = "xyzlldrikikblrwg";
        sendEmail = new SendEmail(username, password);
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
        HttpSession session = request.getSession(false);
        int verificationCode = (int) session.getAttribute("verification");
        String lastName = (String) session.getAttribute("lastname");
        String email = (String) session.getAttribute("email");
        String box1 = request.getParameter("box1");
        String box2 = request.getParameter("box2");
        String box3 = request.getParameter("box3");
        String box4 = request.getParameter("box4");
        String box5 = request.getParameter("box5");
        
        String userVerif = box1+box2+box3+box4+box5;
        int userVerification = Integer.parseInt(userVerif);
        System.out.println(verificationCode);
        System.out.println(lastName);
        System.out.println(email);
        boolean isVerified = (userVerification == verificationCode);
        if(isVerified){
            response.sendRedirect("index.html");
        }else{
            int verification = 23580;
            String subject = "Verify your email";
            String content = "Dear "+lastName 
                + " \nWe've sent this code to confirm that this email is yours." 
                + " \nConfirmation code is:\n"
                +verification+"\n"
                +"Welcome To My World";
    
                try{
                    sendEmail.sendingEmail(email, subject, content);
                    request.getSession().setAttribute("verification", verification);
                    response.sendRedirect("/NewUni/confirmEmail.html");
                }catch(MessagingException ex){
                    response.getWriter().write("Failed to send");
                    ex.printStackTrace();
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
