/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.controler;

import com.ibm.dao.tarjetaDao;
import org.json.JSONObject;

/**
 *
 * @author wilberthmoreno
 */
public class tarjetaControler {

    public JSONObject insertar( JSONObject params ) {
        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            tarjetaDao tarejta;
            tarejta = new tarjetaDao();
            respuesta = tarejta.insertar( params );

        }
        catch ( Exception ex ) {
            ex.printStackTrace();

            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
        }
        return respuesta;
    }

    public JSONObject listar( String cedula ) {

        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            tarjetaDao tarejta;
            tarejta = new tarjetaDao();
            respuesta = tarejta.listar( cedula );

        }
        catch ( Exception ex ) {
            ex.printStackTrace();

        }
        finally {

        }

        return respuesta;
    }

}
