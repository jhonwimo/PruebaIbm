/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.controler;

import com.ibm.dao.TblConsumoDao;
import org.json.JSONObject;

/**
 *
 * @author wilberthmoreno
 */
public class consumosControler {

    /**
     * metodo donde que manda a insertar
     *
     * @param params parametro fromato json
     * @return este metodo retorna un json
     */
    public JSONObject insertar( JSONObject params ) {
        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            TblConsumoDao consumo;
            consumo = new TblConsumoDao();
            respuesta = consumo.insertar( params );

        }
        catch ( Exception ex ) {
            ex.printStackTrace();

            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
        }
        return respuesta;
    }

    /**
     * metodo que devuelve una lista
     *
     * @return este metodo retorna un json
     */
    public JSONObject listar() {

        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            TblConsumoDao consumo;
            consumo = new TblConsumoDao();
            respuesta = consumo.listar();

        }
        catch ( Exception ex ) {
            ex.printStackTrace();

        }
        finally {

        }

        return respuesta;
    }

}
