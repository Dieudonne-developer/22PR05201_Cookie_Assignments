import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        File uploads = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIR);

        if (!uploads.exists()) {
            uploads.mkdir();
        }

        File file = new File(uploads, fileName);
        filePart.write(file.getAbsolutePath());

        response.getWriter().println("File uploaded successfully: " + fileName);
    }
}
