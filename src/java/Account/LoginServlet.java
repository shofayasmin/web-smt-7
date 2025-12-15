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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");  // Login or Register
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String dataSource = this.getServletContext().getInitParameter("UserDataSource");

        String realPath = this.getServletContext().getRealPath(dataSource);
        if (realPath == null) {
            String webRoot = this.getServletContext().getRealPath("/");
            if (webRoot == null) {
                realPath = System.getProperty("user.home") + File.separator + "webdata" + File.separator + "usersrc.txt";
            } else {
                realPath = webRoot + File.separator + "data" + File.separator + "usersrc.txt";
            }
        }

        File targetFile = new File(realPath);
        File parent = targetFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        String resultUrl = "/login.jsp";

        if ("Register".equalsIgnoreCase(action)) {
            System.out.println("Registration attempt - firstName: " + firstName + ", lastName: " + lastName + ", email: " + email);
            User newUser = new User(firstName, lastName, email, password);
            boolean success = UserIO.add(newUser, realPath);

            if (success) {
                request.setAttribute("message", "User registered successfully!");
            } else {
                request.setAttribute("message", "Registration failed. Try again.");
            }

            getServletContext().getRequestDispatcher(resultUrl).forward(request, response);

        } else if ("Login".equalsIgnoreCase(action)) {
            System.out.println("Login attempt - firstName: " + firstName + ", lastName: " + lastName);
            User user = UserIO.get(firstName, lastName, password, realPath);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);
                
                String remember = request.getParameter("rememberMe");
                if("true".equals(remember)){
                    Cookie cookie = new Cookie("rememberEmail", email);
                    cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                    getServletContext().log("Remember Me cookies created for: " + email);
                } else {
                    Cookie cookie = new Cookie("rememberEmail", "");
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
                
                response.sendRedirect(request.getContextPath() + "/products");
            } else {
                request.setAttribute("message", "Invalid name or password.");
                getServletContext().getRequestDispatcher(resultUrl).forward(request, response);
            }

        } else {
            request.setAttribute("message", "Unknown action.");
            getServletContext().getRequestDispatcher(resultUrl).forward(request, response);
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
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
