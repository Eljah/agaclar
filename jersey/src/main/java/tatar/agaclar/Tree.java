package tatar.agaclar;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by eljah32 on 12/16/2015.
 */
@Path("/tree")
public class Tree {

    @GET
    @Path("/{diameter}/{latitude}/{longitude}")
    //@Produces("application/json")
    //@Consumes("text/html")
    public String putTreeData(
            @PathParam("diameter") String diameter,
            @PathParam("latitude") String latitude,
            @PathParam("longitude") String longitude){
        //@FormDataParam("file") InputStream fileInputStream,
        //@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        java.sql.Connection conpg;

        try {
    /*
    * Load the JDBC driver and establish a connection.
    */

            Class.forName("org.postgresql.Driver");
            String urlp = "jdbc:postgresql://192.168.0.100:5432/postgis_22_sample";
            conpg = DriverManager.getConnection(urlp, "postgres", "tatarstan");
    /*
    * Add the geometry types to the connection. Note that you
    * must cast the connection to the pgsql-specific connection
    * implementation before calling the addDataType() method.
    */
            ((org.postgresql.PGConnection) conpg).addDataType("geometry", Class.forName("org.postgis.PGgeometry"));
            //((org.postgresql.PGConnection)conpg).addDataType("point",Class.forName("org.postgis.Point"));
    /*
    * Create a statement and execute a select query.
    */
            conpg.setAutoCommit(false);

            org.postgis.Point pointToAdd = new org.postgis.Point();
            pointToAdd.setX(Double.parseDouble(longitude));
            pointToAdd.setY(Double.parseDouble(latitude));

            //Statement s = conn.createStatement();
            //String geomsql = ;
            PreparedStatement psSE = conpg.prepareStatement("INSERT INTO public.\"poi-point\" (name,geom,leisure) VALUES (?,?,?)");
            psSE.setString(1, diameter);
            psSE.setObject(2, new org.postgis.PGgeometry(pointToAdd));
            psSE.setString(3, "tree");

            psSE.execute();
            conpg.commit();
            conpg.close();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject obj = new JSONObject();
            try {
                obj.put("tag", "tree");
                obj.put("status", new Boolean(false));
                obj.put("error_msg", e.toString());
            } catch (JSONException ej) {
                // TODO Auto-generated catch block
            }
            return obj.toString();
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", "tree");
            obj.put("status", new Boolean(true));
            obj.put("error_msg", "");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();

    }
}
