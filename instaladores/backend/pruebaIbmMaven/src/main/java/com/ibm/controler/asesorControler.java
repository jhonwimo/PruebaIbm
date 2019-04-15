/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.controler;

import com.ibm.dao.asesorDao;
import org.json.JSONObject;

/**
 *
 * @author wilberthmoreno
 */
public class asesorControler {

    /**
     * metodo donde se direcciona el metodo insertar
     *
     * @param params este argument es un json donde vienen todos los valores a
     * insertar
     * @return
     */
    public JSONObject insertar( JSONObject params ) {
        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            asesorDao asesor;
            asesor = new asesorDao();
            respuesta = asesor.insertar( params );
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo donde que devuelve una lista de asesores
     *
     * @return este metodo retorna un json
     */
    public JSONObject listar() {

        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            asesorDao asesor;
            asesor = new asesorDao();
            respuesta = asesor.listar();

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return respuesta;
    }

}
