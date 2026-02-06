package view;

import javax.swing.*;
import java.awt.*;

public class CentroOperacionView extends JFrame {

    // 🔹 Cajas de texto (campos del procedimiento)
    public JTextField txtId;
    public JTextField txtNombre;
    public JTextField txtUbicacion;
    public JTextField txtResponsable;
    public JTextField txtEstado;

    // 🔹 Botones CRUD
    public JButton btnCrear;
    public JButton btnConsultar;
    public JButton btnActualizar;
    public JButton btnEliminar;

    public CentroOperacionView() {

        setTitle("Gestión Centro de Operaciones");
        setSize(550, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /* ================= PANEL FORMULARIO ================= */
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Centro de Operación"));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtUbicacion = new JTextField();
        txtResponsable = new JTextField();
        txtEstado = new JTextField();

        panelForm.add(new JLabel("ID:"));
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Ubicación:"));
        panelForm.add(txtUbicacion);

        panelForm.add(new JLabel("Responsable:"));
        panelForm.add(txtResponsable);

        panelForm.add(new JLabel("Estado:"));
        panelForm.add(txtEstado);

        /* ================= PANEL BOTONES ================= */
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Operaciones CRUD"));

        btnCrear = new JButton("Crear");
        btnConsultar = new JButton("Consultar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        /* ================= LAYOUT PRINCIPAL ================= */
        setLayout(new BorderLayout(10, 10));
        add(panelForm, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
