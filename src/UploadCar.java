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

@WebServlet("/Pages/UploadCar")
@MultipartConfig
public class UploadCar extends HttpServlet {
   protected String filePath = "";
   /*Function Helpers*/
protected String getFileExt(File file) {
      String name = file.getName();
      try {
         return name.substring(name.lastIndexOf("."));

      } catch (Exception e) {
         return "";
      }
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter out = response.getWriter();
      String path ="C:\\Users\\Kuma\\IdeaProjects\\Advanced Java 36A\\Lab6\\src\\logic\\data\\";
      Part part = request.getPart("filename");
      String fileName = part.getSubmittedFileName();
      String finalLocation = path + File.separator + fileName;
      part.write(finalLocation);
      out.println("File Uploaded Succesffully at" + finalLocation);
   }
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   }
}
