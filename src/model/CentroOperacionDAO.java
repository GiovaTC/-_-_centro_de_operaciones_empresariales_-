package model;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CentroOperacionDAO {


    public void ejecutarSP(int opcion, CentroOperacion c) throws Exception {

        Connection con = DBConnection.getConnection();
        CallableStatement cs = con.prepareCall(
                "{ call SP_CENTRO_OPERACION_CRUD(?,?,?,?,?,?,?) }"
        );

        cs.setInt(1, opcion);
        cs.setInt(2, c.getId());
        cs.setString(3, c.getNombre());
        cs.setString(4, c.getUbicacion());
        cs.setString(5, c.getResponsable());
        cs.setString(6, c.getEstado());
        cs.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);

        cs.execute();
        con.close();
    }

    public List<CentroOperacion> listar() throws Exception {

        List<CentroOperacion> lista = new ArrayList<>();
        Connection con = DBConnection.getConnection();

        CallableStatement cs = con.prepareCall(
                "{ call SP_CENTRO_OPERACION_CRUD(4,null,null,null,null,null,?) }"
        );

        cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        cs.execute();

        ResultSet rs = (ResultSet) cs.getObject(1);

        while (rs.next()) {
            CentroOperacion c = new CentroOperacion();
            c.setId(rs.getInt("ID"));
            c.setNombre(rs.getString("NOMBRE"));
            c.setUbicacion(rs.getString("UBICACION"));
            c.setResponsable(rs.getString("RESPONSABLE"));
            c.setEstado(rs.getString("ESTADO"));
            lista.add(c);
        }

        con.close();
        return lista;
    }
}
