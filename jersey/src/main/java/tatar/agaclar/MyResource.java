
package tatar.agaclar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {

    /**
     * Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     *
     * @return String that will be send back as a response of type "text/plain".
     */

    @GET
    @Path("/hi")
    @Produces("text/html")
    //@Consumes("text/html")
    public String getIt() {
        return "Hi there!";
    }

    @GET
    @Path("/hi1")
    @Produces("text/plain")
    //@Consumes("text/html")
    public String getIt1() {
        return "Hi there!";
    }

    @GET
    @Path("/hi2")
    @Produces("application/xml")
    //@Consumes("text/html")
    public String getIt2() {
        return "<xml>Hi there</xml>!";
    }

    @GET
    @Path("/hi3")
    @Produces("application/json")
    public String getIt3() {
        return "Hi there!";
    }

    @GET
    @Path("/himan")
    @Produces("application/json")
    //@Consumes("text/html")
    public String getIt4(@QueryParam("manname") String man) {
        return "Hi " + man + "!";
    }

    @POST
    @Path("/hipost")
    @Produces("text/html")
    public String getItPost(@FormParam("manname") String man) {
        return "Hi " + man + "!";
    }


    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://Java/";
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        // save the file to the server
        String filePath = SERVER_UPLOAD_LOCATION_FOLDER	+ contentDispositionHeader.getFileName();

        String output = null;
        int filesize=saveFile(fileInputStream, filePath);

        output = "File saved to server location : " + filePath+"<br> Filesize is "+filesize+"b";

        /*
        try {
            output = "File size : " +  fileInputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;*/

        return Response.status(200).entity(output).build();

    }

    // save uploaded file to a defined location on the server
    private int saveFile(InputStream uploadedInputStream,
                          String serverLocation) {

        try {
            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            int counter=0;
            byte[] bytes = new byte[1024];

            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
                counter=counter+read;
            }
            outpuStream.flush();
            outpuStream.close();
            return counter;
        } catch (IOException e) {

            e.printStackTrace();
        }

       return -1;
    }
/*
    @GET
    @Path("/{user}/{hi}")
    @Produces("application/json")
       public String getIt4(@PathParam("user") String man,@PathParam("hi") String hi) {
        return hi+" " + man + "!";
    }
*/
    @POST
    @Path("/{user}/hi")
    @Produces("application/json")
    //@Consumes("text/html")
    public String getIt5(@PathParam("user") String man, @Context HttpServletResponse servletResponse) {
        try {
            servletResponse.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hi " + man + "!";
    }

    //

    @GET
    @Path("/{user}/hi")
    @Produces("application/json")
    @Consumes("text/html")
    public String getIt5(@PathParam("user") String man, @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse, @Context ServletContext _context) {
        try {
            servletRequest.setAttribute("mantojsp", man);
            _context.getRequestDispatcher("/test.jsp").forward(servletRequest,servletResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "Hi " + man + "!";
    }

}

