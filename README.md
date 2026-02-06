# -_-_centro_de_operaciones_empresariales_- :. .
# 🏢 Centro de Operaciones Empresariales.  
**Aplicación Java SE (Swing) + JDBC + Oracle 19c**  
**Arquitectura: Modelo–Vista–Controlador (MVC)**

<img width="1024" height="1024" alt="image" src="https://github.com/user-attachments/assets/bcb2df76-4f5a-4a03-aaea-4fde1c17bd2d" />  

<img width="2554" height="1079" alt="image" src="https://github.com/user-attachments/assets/d896e3ff-bb95-427c-910f-ad91fe3a16b6" /> 

Solución completa, profesional y alineada a **buenas prácticas**, desarrollada en **IntelliJ IDEA**, que implementa un **CRUD** mediante un **Stored Procedure único** en **Oracle 19c**.

---

## 🎯 Caso de Uso

Gestión de la información de un **Centro de Operaciones Empresariales**.

### 📌 Entidad principal: `CentroOperacion`

**Atributos gestionados:**

- ID  
- Nombre  
- Ubicación  
- Responsable  
- Estado  

---

## 🧱 Arquitectura MVC

```text
src/
 ├── model/
 │    ├── CentroOperacion.java
 │    └── CentroOperacionDAO.java
 │
 ├── view/
 │    └── CentroOperacionView.java
 │
 ├── controller/
 │    └── CentroOperacionController.java
 │
 ├── util/
 │    └── DBConnection.java
 │
 └── Main.java
```

🗄️ Base de Datos – Oracle 19c
📌 Tabla
```
CREATE TABLE CENTRO_OPERACION (
    ID NUMBER PRIMARY KEY,
    NOMBRE VARCHAR2(100),
    UBICACION VARCHAR2(100),
    RESPONSABLE VARCHAR2(100),
    ESTADO VARCHAR2(50)
);
```
⚙️ Stored Procedure CRUD (Único SP)
```
CREATE OR REPLACE PROCEDURE SP_CENTRO_OPERACION_CRUD (
    P_OPCION        IN NUMBER,   -- 1=INSERT, 2=UPDATE, 3=DELETE, 4=SELECT
    P_ID            IN NUMBER,
    P_NOMBRE        IN VARCHAR2,
    P_UBICACION     IN VARCHAR2,
    P_RESPONSABLE   IN VARCHAR2,
    P_ESTADO        IN VARCHAR2,
    P_CURSOR        OUT SYS_REFCURSOR
) AS
BEGIN
    IF P_OPCION = 1 THEN
        INSERT INTO CENTRO_OPERACION
        VALUES (P_ID, P_NOMBRE, P_UBICACION, P_RESPONSABLE, P_ESTADO);

    ELSIF P_OPCION = 2 THEN
        UPDATE CENTRO_OPERACION
        SET NOMBRE = P_NOMBRE,
            UBICACION = P_UBICACION,
            RESPONSABLE = P_RESPONSABLE,
            ESTADO = P_ESTADO
        WHERE ID = P_ID;

    ELSIF P_OPCION = 3 THEN
        DELETE FROM CENTRO_OPERACION
        WHERE ID = P_ID;

    ELSIF P_OPCION = 4 THEN
        OPEN P_CURSOR FOR
        SELECT * FROM CENTRO_OPERACION;
    END IF;
END;
/
```
📦 MODELO
CentroOperacion.java
```
package model;

public class CentroOperacion {

    private int id;
    private String nombre;
    private String ubicacion;
    private String responsable;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
```
CentroOperacionDAO.java
```
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
```
🔌 UTILIDAD DE CONEXIÓN
DBConnection.java
```
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        return DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:ORCL",
            "USUARIO",
            "PASSWORD"
        );
    }
}
```
🖥️ VISTA (Swing)
CentroOperacionView.java
```
package view;

import javax.swing.*;

public class CentroOperacionView extends JFrame {

    public JTextField txtId = new JTextField(5);
    public JTextField txtNombre = new JTextField(15);
    public JButton btnGuardar = new JButton("Guardar");

    public CentroOperacionView() {

        setTitle("Centro de Operaciones");
        setLayout(new java.awt.FlowLayout());

        add(new JLabel("ID"));
        add(txtId);

        add(new JLabel("Nombre"));
        add(txtNombre);

        add(btnGuardar);

        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
```
🎮 CONTROLADOR
CentroOperacionController.java
```
package controller;

import model.*;
import view.*;

import javax.swing.*;

public class CentroOperacionController {

    public CentroOperacionController(CentroOperacionView view) {

        view.btnGuardar.addActionListener(e -> {
            try {
                CentroOperacion c = new CentroOperacion();
                c.setId(Integer.parseInt(view.txtId.getText()));
                c.setNombre(view.txtNombre.getText());

                new CentroOperacionDAO().ejecutarSP(1, c);

                JOptionPane.showMessageDialog(view, "Registro guardado correctamente");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
```
🚀 MAIN
```
import view.*;
import controller.*;

public class Main {

    public static void main(String[] args) {

        CentroOperacionView view = new CentroOperacionView();
        new CentroOperacionController(view);
        view.setVisible(true);
    }
}
```
✅ Tecnologías Utilizadas
* Java SE 8+
* Swing
* JDBC
* Oracle 19c
* Stored Procedures
* Arquitectura MVC real (desacoplada)

📌 Ventajas del Enfoque
```
✔ Separación clara de responsabilidades
✔ CRUD centralizado en un único Stored Procedure
✔ Fácil mantenimiento
✔ Escalable a Web / REST / Spring
✔ Totalmente compatible con IntelliJ IDEA
```
/ .
