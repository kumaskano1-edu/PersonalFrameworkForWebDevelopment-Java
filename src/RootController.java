import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RootController")
public class RootController extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String value = request.getParameter("Choice");
      if(value.equals("Upload")) {
         response.sendRedirect("Pages/UploadCar.jsp");
      } else {
         response.sendRedirect("Pages/ConfigureCar.jsp");
      }
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

}
}
