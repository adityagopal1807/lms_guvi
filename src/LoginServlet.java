import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Mock user validation (Replace this with actual database validation)
        boolean isValidUser = "studentUser".equals(username) && "password123".equals(password) && "Student".equals(role);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (isValidUser) {
            // Store the username and role in session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);

            // Redirect based on role
            if ("Student".equals(role)) {
                response.sendRedirect("home.html");
            } else if ("Teacher".equals(role)) {
                response.sendRedirect("teach.html");
            }
        } else {
            // Display an error message
            out.println("<script>alert('Invalid username, password, or role'); window.location='login.html';</script>");
        }
        out.close();
    }
}
