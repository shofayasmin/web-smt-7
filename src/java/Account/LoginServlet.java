package Account;

import DataIO.UserIO;
import DataModel.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        String savedEmail = "";

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("rememberEmail".equals(c.getName())) {
                    savedEmail = c.getValue();
                    break;
                }
            }
        }

        request.setAttribute("savedEmail", savedEmail);
        getServletContext()
                .getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");   // Login or Register
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        if ("Register".equalsIgnoreCase(action)) {

            User newUser = new User(firstName, lastName, email);
            
            boolean success = UserIO.add(newUser);

            if (success) {
                request.setAttribute("message", "User registered successfully.");
            } else {
                request.setAttribute("message", "Registration failed.");
            }

            getServletContext()
                    .getRequestDispatcher("/login.jsp")
                    .forward(request, response);

        } else if ("Login".equalsIgnoreCase(action)) {

            User user = UserIO.get(firstName, lastName);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);

                System.out.println("LOGIN SUCCESS: " + user.getEmail());
                System.out.println("SESSION ID: " + session.getId());

                String remember = request.getParameter("rememberMe");
                if (remember != null) {
                    Cookie cookie = new Cookie("rememberEmail", user.getEmail());
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                } else {
                    Cookie cookie = new Cookie("rememberEmail", "");
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
                
                response.sendRedirect(request.getContextPath() + "/products");
            } else {
                request.setAttribute("message", "Invalid name");
                getServletContext()
                        .getRequestDispatcher("/login.jsp")
                        .forward(request, response);
            }
            
        } else {
            request.setAttribute("message", "Unknown action.");
            getServletContext()
                    .getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
    }

    


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
