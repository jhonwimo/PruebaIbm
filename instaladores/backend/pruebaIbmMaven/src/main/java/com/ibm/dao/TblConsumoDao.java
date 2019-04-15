/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.dao;

import com.ibm.model.TblConsumo;
import com.ibm.model.TblConsumoTarjetas;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HibernateUtil;

/**
 *
 * @author wilberthmoreno
 */
public class TblConsumoDao {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    public JSONObject insertar( JSONObject params ) {

        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            TblConsumo consumo = new TblConsumo();

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if ( params != null && params.length() > 0 ) {

                if ( params.has( "descripcion" ) ) {
                    consumo.setDescripcion( params.getString( "descripcion" ) );
                }
                if ( params.has( "fecha" ) ) {
                    consumo.setFecha( params.getString( "fecha" ) );
                }
                if ( params.has( "idtarjeta" ) ) {
                    consumo.setIdtarjeta( params.getInt( "idtarjeta" ) );
                }

                if ( params.has( "monto" ) ) {
                    consumo.setMonto( params.getInt( "monto" ) );
                }

            }

            session.save( consumo );

            session.getTransaction().commit();
            session.flush();
        }
        catch ( Exception ex ) {
            respuesta.put( "success", false );
            ex.printStackTrace();

            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
        }

        return respuesta;
    }

    public JSONObject listar() {
        // Configure the session factory

        JSONArray data = null;
        JSONObject lista;

        JSONObject respuesta;
        respuesta = new JSONObject();
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();

            List<TblConsumoTarjetas> List = session.createQuery( "select idconsumo,fecha,descripcion,monto, numerotarjeta from  tbl_consumos a join tbl_tarjetas b on a.numerotarjeta=b.numerotarjeta" ).list();
            session.flush();
            data = new JSONArray();
            for ( TblConsumoTarjetas consumo : List ) {
                lista = new JSONObject();
                lista.put( "idcliente", consumo.getNumerotarjeta() );
                lista.put( "nombre", consumo.getMonto() );
                lista.put( "direccion", consumo.getFecha() );

                data.put( lista );
            }

            respuesta.put( "data", data.getJSONObject( 0 ) );

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
            respuesta.put( "success", true );
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.

        }

        return respuesta;
    }

}
