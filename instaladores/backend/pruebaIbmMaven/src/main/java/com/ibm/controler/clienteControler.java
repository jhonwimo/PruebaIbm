/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.controler;

import com.ibm.dao.clienteDao;
import org.json.JSONObject;

/**
 *
 * @author wilberthmoreno
 */
public class clienteControler {

    /**
     * metodo donde se encargado de mandar a insertar
     *
     * @param params parametro que contiene json
     */
    public void insertar( JSONObject params ) {

        try {
            clienteDao cliente;
            cliente = new clienteDao();
            cliente.insertar( params );

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    /**
     * metodo donde se encargado listar todos los clientes
     *
     * @param cedula parametro que me define la busqueda enfocada o total
     *
     */
    public JSONObject listar( int cedula ) {

        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            clienteDao cliente;
            cliente = new clienteDao();
            respuesta = cliente.listar( cedula );

        }
        catch ( Exception ex ) {
            ex.printStackTrace();

        }
        finally {

        }

        return respuesta;
    }

}
