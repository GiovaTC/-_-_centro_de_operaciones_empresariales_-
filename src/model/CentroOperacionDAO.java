package model;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CentroOperacionDAO {

    /* ===================== CRUD GENERAL ===================== */
    public ResultSet ejecutarSP(int opcion, CentroOperacion c) throws Exception {

        Connection con = DBConnection.getConnection();
        CallableStatement cs = con.prepareCall(
                "{ call SP_CENTRO_OPERACION_CRUD(?,?,?,?,?,?,?) }"
        );

        cs.setInt(1, opcion);

        // Para DELETE / SELECT se permiten nulls
        if (c != null) {
            cs.setInt(2, c.getId());
            cs.setString(3, c.getNombre());
            cs.setString(4, c.getUbicacion());
            cs.setString(5, c.getResponsable());
            cs.setString(6, c.getEstado());
        } else {
            cs.setNull(2, Types.INTEGER);
            cs.setNull(3, Types.VARCHAR);
            cs.setNull(4, Types.VARCHAR);
            cs.setNull(5, Types.VARCHAR);
            cs.setNull(6, Types.VARCHAR);
        }

        cs.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
        cs.execute();

        // Solo SELECT devuelve cursor
        if (opcion == 4) {
            return (ResultSet) cs.getObject(7);
        }

        con.close();
        return null;
    }

    /* ===================== LISTAR ===================== */
    public List<CentroOperacion> listar() throws Exception {

        List<CentroOperacion> lista = new ArrayList<>();
        ResultSet rs = ejecutarSP(4, null);

        while (rs.next()) {
            CentroOperacion c = new CentroOperacion();
            c.setId(rs.getInt("ID"));
            c.setNombre(rs.getString("NOMBRE"));
            c.setUbicacion(rs.getString("UBICACION"));
            c.setResponsable(rs.getString("RESPONSABLE"));
            c.setEstado(rs.getString("ESTADO"));
            lista.add(c);
        }
        return lista;
    }
}