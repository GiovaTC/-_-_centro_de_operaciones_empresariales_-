package controller;

import model.*;
import view.*;

import javax.swing.*;

public class CentroOperacionController {

    private CentroOperacionView view;
    private CentroOperacionDAO dao;

    public CentroOperacionController(CentroOperacionView view) {

        this.view = view;
        this.dao = new CentroOperacionDAO();

        /* ===================== CREAR ===================== */
        view.btnCrear.addActionListener(e -> ejecutar(1, "Registro creado correctamente"));

        /* ===================== ACTUALIZAR ===================== */
        view.btnActualizar.addActionListener(e -> ejecutar(2, "Registro actualizado correctamente"));

        /* ===================== ELIMINAR ===================== */
        view.btnEliminar.addActionListener(e -> {
            try {
                CentroOperacion c = new CentroOperacion();
                c.setId(Integer.parseInt(view.txtId.getText()));
                dao.ejecutarSP(3, c);

                JOptionPane.showMessageDialog(view, "Registro eliminado correctamente");
                limpiarCampos();
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        /* ===================== CONSULTAR ===================== */
        view.btnConsultar.addActionListener(e -> {
            try {
                var lista = dao.listar();
                StringBuilder sb = new StringBuilder("CENTROS REGISTRADOS:\n\n");

                for (CentroOperacion c : lista) {
                    sb.append(c.getId()).append(" - ")
                            .append(c.getNombre()).append(" - ")
                            .append(c.getUbicacion()).append(" - ")
                            .append(c.getResponsable()).append(" - ")
                            .append(c.getEstado()).append("\n");
                }

                JOptionPane.showMessageDialog(view, sb.toString());
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });
    }

    /* ===================== MÉTODO GENERAL ===================== */
    private void ejecutar(int opcion, String mensaje) {
        try {
            CentroOperacion c = new CentroOperacion();
            c.setId(Integer.parseInt(view.txtId.getText()));
            c.setNombre(view.txtNombre.getText());
            c.setUbicacion(view.txtUbicacion.getText());
            c.setResponsable(view.txtResponsable.getText());
            c.setEstado(view.txtEstado.getText());

            dao.ejecutarSP(opcion, c);

            JOptionPane.showMessageDialog(view, mensaje);
            limpiarCampos();
        } catch (Exception ex) {
            mostrarError(ex);
        }
    }

    private void limpiarCampos() {
        view.txtId.setText("");
        view.txtNombre.setText("");
        view.txtUbicacion.setText("");
        view.txtResponsable.setText("");
        view.txtEstado.setText("");
    }

    private void mostrarError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
