/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.rest;

import com.ibm.controler.asesorControler;
import com.ibm.controler.clienteControler;
import com.ibm.controler.consumosControler;
import com.ibm.controler.tarjetaControler;
import com.ibm.datos.Ini;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import util.Tool;

/**
 *
 * @author wilberthmoreno
 */
@Path( "minService" )
public class WSprueba {

    public WSprueba() {
        Ini.getInstance();
    }

    /**
     * Método que para insertar un cliente instanciado previamente, este método
     * la instancia automáticamente.
     *
     * @param data es un json que contiene todos los registros para crear el
     * cliente
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path( "setCliente" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response setCliente( String data ) throws Exception {

        JSONObject json = new JSONObject();
        JSONObject params = null;
        try {

            if ( data != null && !data.trim().equals( "" ) ) {
                params = new JSONObject( data );
            }
            clienteControler obj = new clienteControler();
            obj.insertar( params );

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "getUsuarios(): " + e.getMessage() );
        }
        return Response.status( 201 ).entity( json.toString() ).build();
    }

    @GET
    @Path( "getClientes" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getClientesBanco( @Context HttpServletRequest request ) throws Exception {
        int cedula = 0;
        JSONObject json;
        JSONObject params = null;
        try {
            if ( request.getParameter( "cedula" ) != null ) {
                cedula = Integer.parseInt( request.getParameter( "cedula" ) );
            }

            clienteControler obj = new clienteControler();
            json = obj.listar( cedula );

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "getUsuarios(): " + e.getMessage() );
        }

        return Response.status( 201 ).entity( json.getJSONArray( "data" ).toString() ).build();
    }

    @POST
    @Path( "setAsesor" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response insertAsesor( String data ) throws Exception {

        JSONObject json;
        JSONObject params = null;
        try {

            if ( data != null && !data.trim().equals( "" ) ) {
                params = new JSONObject( data );
            }
            asesorControler obj = new asesorControler();
            json = obj.insertar( params );

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "getUsuarios(): " + e.getMessage() );
        }
        return Response.status( 201 ).entity( json.toString() ).build();
    }

    @GET
    @Path( "getAsesor" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getAsesor() throws Exception {

        JSONObject json;
        JSONObject params = null;
        try {

            asesorControler obj = new asesorControler();
            json = obj.listar();

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "getUsuarios(): " + e.getMessage() );
        }
        return Response.status( 201 ).entity( json.getJSONArray( "data" ).toString() ).build();
    }

    @POST
    @Path( "setTarjeta" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response insertTarjeta( String data ) throws Exception {

        JSONObject json;
        JSONObject params = null;
        try {

            if ( data != null && !data.trim().equals( "" ) ) {
                params = new JSONObject( data );
            }
            tarjetaControler obj = new tarjetaControler();
            json = obj.insertar( params );

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "getUsuarios(): " + e.getMessage() );
        }
        return Response.status( 201 ).entity( json.toString() ).build();
    }

    @GET
    @Path( "getTarjetas" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getTarjetasCredito( @Context HttpServletRequest request ) throws Exception {

        JSONObject json;
        JSONObject params = null;
        String cedula = "";
        try {
            if ( request.getParameter( "cedula" ) != null ) {
                cedula = request.getParameter( "cedula" );
            }

            tarjetaControler obj = new tarjetaControler();
            json = obj.listar( cedula );

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "getUsuarios(): " + e.getMessage() );
        }
        return Response.status( 201 ).entity( json.getJSONArray( "data" ).toString() ).build();
    }

    @POST
    @Path( "setConsumo" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getConsumoTarjetas( String data ) throws Exception {

        JSONObject json;
        JSONObject params = null;
        try {

            if ( data != null && !data.trim().equals( "" ) ) {
                params = new JSONObject( data );
            }
            consumosControler obj = new consumosControler();
            json = obj.insertar( params );

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "getUsuarios(): " + e.getMessage() );
        }
        return Response.status( 201 ).entity( json.toString() ).build();
    }

    @GET
    @Path( "getConsumo" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getConsumoTrjetas( String data ) throws Exception {

        JSONObject json;
        JSONObject params = null;
        try {

            if ( data != null && !data.trim().equals( "" ) ) {
                params = new JSONObject( data );
            }
            consumosControler obj = new consumosControler();
            json = obj.listar();

        }
        catch ( Exception e ) {
            json = Tool.getJSONError( "getUsuarios(): " + e.getMessage() );
        }
        return Response.status( 201 ).entity( json.getJSONObject( "data" ).toString() ).build();
    }
}
