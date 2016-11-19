package ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.opencv.core.Core;

import com.soccergame_main.ConectorWeb;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductRestful.
 */
@Path("/producto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class ProductRestful {
	

    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
	
	/**
	 * Post cliched message.
	 *
	 * @param request the request
	 * @return the string finalPath
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @throws InterruptedException 
	 */
	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("text/plain")
	public String postClichedMessage(@Context final HttpServletRequest request) 
			throws IOException, ServletException, InterruptedException {
		
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + SAVE_DIR;
        String finalPath = "";
        
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
		for (Part part : request.getParts())
		{
			String fileName = extractFileName(part);
			String[] path = fileName.split("\\\\");
			
			part.write(savePath + File.separator + "prcvideo.avi");
			finalPath = savePath + File.separator + path[path.length - 1];
		}
		
		ConectorWeb test = new ConectorWeb();
		test.conector(savePath + File.separator + "prcvideo.avi");
		
		return finalPath;
	}
	
	/**
	 * Download file.
	 *
	 * @param response the response
	 * @return the string Fin de descarga
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Path("/download")
	@GET
	@Produces("text/plain")
	public String downloadFile(@Context final HttpServletResponse response) 
			throws IOException, ServletException {
		String path = "C:\\Users\\Manuel\\Desktop\\work\\.metadata\\.plugins"
				+ "\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SoccerVideoProcessing\\"
				+ "uploadFiles\\resultadoVideo.avi";
		File file = new File(path);
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		response.setHeader("Content-Disposition", "attachment; filename=" 
		+ file.getName().replace(" ", "_"));
		
		InputStream iStream = new FileInputStream(file);
		IOUtils.copy(iStream, response.getOutputStream());
		
		response.flushBuffer();
		return "Descarga Finalizada";
	}
	

	/**
	 * Extracts file name from HTTP header content-disposition.
	 *
	 * @param part the part
	 * @return the string ""
	 */
    private String extractFileName(final Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}