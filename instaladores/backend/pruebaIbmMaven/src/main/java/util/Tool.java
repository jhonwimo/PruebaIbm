/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author julianrayo
 */
public class Tool {

    public static JSONObject getJSONError( String mensajeError ) throws Exception {
        JSONObject error = new JSONObject();
        error.put( "success", false );
        error.put( "error", mensajeError );

        return error;
    }

    public static String getPathUbicationApp( ServletContext servletContext, HttpServletRequest request ) {
        String path = null;
        String requestURI = null;
        String realPath = null;
        String contextPath = null;
        int lastOcurrence;

        try {
            if ( request != null ) {
                requestURI = request.getRequestURI();
                realPath = request.getRequestURI().substring( request.getContextPath().length() );
                contextPath = servletContext.getRealPath( "/" );

                requestURI = requestURI.replace( realPath, "" );
                requestURI = requestURI.replace( "/", "" );
                requestURI = requestURI + "\\";

                lastOcurrence = contextPath.lastIndexOf( requestURI );
                if ( lastOcurrence >= 0 ) {
                    path = new StringBuilder( contextPath ).replace( lastOcurrence, contextPath.length(), "" ).toString();
                }
            }
            else {
                throw new Exception( "El request no puede ser vacio." );
            }
        }
        catch ( Exception e ) {
            System.out.println( "getPathUbicationApp(): " + e.getMessage() );
        }

        return path;
    }

    /**
     * metodo para odtener las rutas de los archivos via browser
     *
     * @param servletContext
     * @param request
     * @return retorna la ruta de el servidor via browser para descargar
     * archivos
     */
    public static String getRutaServidorbrower( HttpServletRequest request ) {

        //si todo sale bien guardo la direccion para hipervincilo
//        ServletRequest request = ( ServletRequest ) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //Obtener vserver_name con puerto
        String vserver_name = request.getServerName() + ":" + String.valueOf( request.getServerPort() ).trim();
        //Obtener nombre aplicativo dentro del URL
//       String vcontext = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

//        String vcontexts = servletContext.getContextPath();
//        String vcontext = servletContext.getServletContextName();
        String rutarchivosbrowser = "http://" + vserver_name + "/AccesoPalmSecure/";

        return rutarchivosbrowser;
    }

    public static String getFileSeparator() {
        String separador = File.separator;
        return separador;
    }

    public static String getRutaServidor( HttpServletRequest request ) {
        String path = request.getRealPath( "/main.jsp" );
        path = path.replace( "file:/", "" );
        int vpos = path.lastIndexOf( getFileSeparator() + "main.jsp" );
        // Define la ruta completa del archivo
        String rutaArchivo = path.substring( 0, vpos );
        return rutaArchivo;
    }

    public static JSONObject getInfoFehaSistema() throws Exception {
        JSONObject json;
        Timestamp fecha;
        Calendar calendar;
        String hora;
        String minutos;
        String segundos;
        String franja;
        String[] strDays = new String[]{
            "Domingo",
            "Lunes",
            "Martes",
            "Miercoles",
            "Jueves",
            "Viernes",
            "Sabado" };
        Long time;
        int hour;

        try {

            time = System.currentTimeMillis();
            fecha = new Timestamp( time );

            calendar = Calendar.getInstance();
            calendar.setTimeInMillis( time );

            franja = calendar.get( Calendar.AM_PM ) == 0 ? "am" : "pm";
            hour = calendar.get( Calendar.HOUR );

            if ( hour == 0 ) {
                hora = "12";
            }
            else {
                hora = "" + hour;
            }

            if ( hora.length() == 1 ) {
                hora = "0" + hora;
            }

            minutos = "" + calendar.get( Calendar.MINUTE );
            if ( minutos.length() == 1 ) {
                minutos = "0" + minutos;
            }

            segundos = "" + calendar.get( Calendar.SECOND );
            if ( segundos.length() == 1 ) {
                segundos = "0" + segundos;
            }

            json = new JSONObject();
            json.put( "success", true );
            json.put( "fecha", fecha.toString() );
            json.put( "diaSemana", strDays[ calendar.get( Calendar.DAY_OF_WEEK ) - 1 ] );
            json.put( "horaCompleta", hora + ":" + minutos + ":" + segundos + " " + franja );
            json.put( "hora", hora );
            json.put( "minutos", minutos );
            json.put( "segundos", segundos );
            json.put( "franja", franja );

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "getInfoFehaSistema(): " + e.getMessage() );
        }

        return json;
    }

    public static JSONObject createFolder( String pathFolder ) throws Exception {
        JSONObject json = null;
        File folder;
        boolean nuevo = false;

        try {

            if ( pathFolder != null && !pathFolder.trim().equals( "" ) ) {
                folder = new File( pathFolder );

                nuevo = folder.exists();
                if ( !nuevo ) {
                    folder.mkdirs();
                }

                json = new JSONObject();
                json.put( "success", true );
                json.put( "nuevo", nuevo );
            }
            else {
                throw new Exception( "Es necesario la ruta del folder o carpeta a crear." );
            }

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "createFolder(): " + e.getMessage() );
        }

        return json;
    }

    public static String getEndPoint( String ruta ) {
        String endPoint = null;
        Properties prop;
        try {

            prop = getDomFileProperties( ruta );
            if ( prop != null && prop.size() > 0 ) {
                endPoint = prop.getProperty( "endPoint" );
            }
            else {
                throw new Exception( "El archivo de propiedades no existe o esta vacío." );
            }

        }
        catch ( Exception e ) {
            System.out.println( e.getMessage() );
        }

        return endPoint;
    }

    public static Properties getDomFileProperties( String rutaWs ) {
        Properties properties = null;
        String path;
        String separador = null;
        InputStream inputStream;

        try {

            path = rutaWs + "config.properties";
            inputStream = new FileInputStream( path );
            properties = new Properties();
            properties.load( inputStream );
            inputStream.close();
        }
        catch ( Exception e ) {
            System.out.println( "datecsa.integracion.licenciamiento.Utilidades.getDomFileProperties(): " + e.getMessage() );
        }

        return properties;
    }

    public static File[] filterFilesName( File[] files, String[] names ) throws Exception {
        File[] filesResponse = new File[ 0 ];
        List<File> filesFiltered = null;

        try {

            if ( files != null && files.length > 0 ) {
                if ( names != null ) {
                    for ( String name : names ) {
                        for ( File file : files ) {
                            if ( file.getName().contains( name ) ) {
                                if ( filesFiltered == null ) {
                                    filesFiltered = new ArrayList<File>();
                                }
                                filesFiltered.add( file );
                            }
                        }
                    }

                    if ( filesFiltered != null && filesFiltered.size() > 0 ) {
                        filesResponse = new File[ filesFiltered.size() ];
                        filesResponse = filesFiltered.toArray( filesResponse );
                    }
                }
                else {
                    filesResponse = files;
                }
            }
        }
        catch ( Exception e ) {
            throw new Exception( "filterFilesName(): " + e.getMessage() );
        }

        return filesResponse;
    }

    public static String[] getPropertyStringJsonArray( JSONArray data, String property ) throws Exception {
        List<String> result = new ArrayList<String>();
        String[] properties = new String[ 0 ];
        JSONObject jsonProp = null;

        try {
            if ( property != null && !property.trim().equals( "" ) ) {
                if ( data != null ) {

                    properties = new String[ data.length() ];

                    for ( int i = 0; i < data.length(); i++ ) {
                        jsonProp = data.getJSONObject( i );
                        result.add( jsonProp.getString( property ) );
                    }

                    properties = result.toArray( properties );

                }
            }
            else {
                throw new Exception( "Es necesario el nombre de la propiedad para extraerlo del JSONArray." );
            }
        }
        catch ( Exception e ) {
            throw new Exception( "getPropertyStringJsonArray(): " + e.getMessage() );
        }

        return properties;
    }

    public static JSONObject inputStreamToFile( InputStream is, String pathFile ) throws Exception {
        JSONObject json;
        FileOutputStream fos;
        byte[] buffer;
        int length;
        File file;
        try {
            if ( is != null ) {
                if ( pathFile != null && !pathFile.trim().equals( "" ) ) {
                    file = new File( pathFile );
                    fos = new FileOutputStream( file );
                    buffer = new byte[ 4096 ];
                    while ( ( length = is.read( buffer ) ) > 0 ) {
                        fos.write( buffer, 0, length );
                    }
                    fos.close();

                    json = new JSONObject();
                    json.put( "success", true );
                    json.put( "path", file.getCanonicalPath() );

                }
                else {
                    throw new Exception( "Es necesario el path del archivo para crearlo." );
                }
            }
            else {
                throw new Exception( "Es necesario el InputStream para convertirlo en File." );
            }
        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "inputStreamToFile(): " + e.getMessage() );
        }

        return json;
    }

    /**
     * Devuelme el nombre de el mes
     *
     * @param mes numero de el mes
     * @return retorna el mes en string ejemplo retorna enero
     */
    public static String mes( int mes ) {
        String mesactual = "NUMERO MES INCONRRECTO TIENE QUE SER ENTRE 1 Y 12";
        if ( mes == 1 ) {
            mesactual = "Enero";
        }
        if ( mes == 2 ) {
            mesactual = "Febrero";
        }
        if ( mes == 3 ) {
            mesactual = "Marzo";
        }
        if ( mes == 4 ) {
            mesactual = "Abril";
        }
        if ( mes == 5 ) {
            mesactual = "Mayo";
        }
        if ( mes == 6 ) {
            mesactual = "Junio";
        }
        if ( mes == 7 ) {
            mesactual = "Julio";
        }
        if ( mes == 8 ) {
            mesactual = "Agosto";
        }
        if ( mes == 9 ) {
            mesactual = "Septiembre";
        }
        if ( mes == 10 ) {
            mesactual = "Octubre";
        }
        if ( mes == 11 ) {
            mesactual = "Noviembre";
        }
        if ( mes == 12 ) {
            mesactual = "Diciembre";
        }

        return mesactual;
    }

    public static String padLeft( String s, int n, String padchar ) {
        s = s.trim();
        int length = s.length();
        if ( length >= n ) {
            return s;
        }
        else {
            StringBuilder linea = new StringBuilder( n );
            for ( int i = length; i < n; i++ ) {
                linea.append( padchar );
            }
            linea.append( s );
            return linea.toString();
        }
    }

    /**
     * Convierte una fecha en una cadena con formato yyyy-MM-dd
     *
     * @param date fecha a convertir
     * @return la cadena que representa la fecha que ingreso transformada
     */
    public static String convertirFechaStringYYYY_MM_DD( Date date ) {
        Format formatter = new SimpleDateFormat( "yyyy-MM-dd" );
        return formatter.format( date );
    }

    public static BufferedImage getBufferedImage( String pathImage ) {
        BufferedImage image = null;
        File file;

        try {

            if ( pathImage != null && !pathImage.trim().equals( "" ) ) {
                file = new File( pathImage );
                image = ImageIO.read( file );
            }
            else {
                throw new Exception( "getBufferedImage(): Se necesita la ubicación de la imagen" );
            }

        }
        catch ( Exception e ) {
            e.printStackTrace();
        }

        return image;
    }

    public static String encodeToString( BufferedImage image, String type ) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write( image, type, bos );
            byte[] imageBytes = bos.toByteArray();

            imageString = Base64.getEncoder().encodeToString( imageBytes );

            bos.close();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        return imageString;
    }

}
