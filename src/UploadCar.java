import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;


@WebServlet("/Pages/UploadCar")
@MultipartConfig
public class UploadCar extends HttpServlet {
   protected String fileExtension(File file) {
      String name = file.getName();
      if(name.lastIndexOf(".") != -1 && name.lastIndexOf(".") != 0)
         return name.substring(name.lastIndexOf(".") + 1);
      else
         return "";
   }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String path ="C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\Lab6\\src\\logic\\uploaded\\";
      Part part = request.getPart("filename");
      String fileName = part.getSubmittedFileName();
      File newFile = new File(fileName);
      String Filex = fileExtension(newFile);
      response.setContentType( "text/html" );
      String finalLocation = path + File.separator + fileName;
      part.write(finalLocation);
     if(Filex.equals("prop")){
        response.sendRedirect( "ConfigureCar.jsp?isUploaded=true" );
     } else {
        response.sendRedirect("ConfigureCar.jsp?isUploaded=false");
     }
   }
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   }
}
