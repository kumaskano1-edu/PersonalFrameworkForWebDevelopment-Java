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
         UploadCar upload = new UploadCar();
         upload.doGet(request, response);
      } else {
         ShowCarsChoice choice = new ShowCarsChoice();
         choice.doGet(request, response);
      }
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

}
}
