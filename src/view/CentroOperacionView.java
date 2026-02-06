package view;

import javax.swing.*;
import java.awt.*;

public class CentroOperacionView extends JFrame {

    // 🔹 Campos de texto
    public JTextField txtId;
    public JTextField txtNombre;

    // 🔹 Botones CRUD
    public JButton btnCrear;
    public JButton btnConsultar;
    public JButton btnActualizar;
    public JButton btnEliminar;

    public CentroOperacionView() {

        setTitle("Centro de Operaciones Empresariales");
        setSize(450, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 🔹 Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Centro"));

        txtId = new JTextField();
        txtNombre = new JTextField();

        panelForm.add(new JLabel("ID:"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);

        // 🔹 Panel de botones CRUD
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Operaciones"));

        btnCrear = new JButton("Crear");
        btnConsultar = new JButton("Consultar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        // 🔹 Layout principal
        setLayout(new BorderLayout(10, 10));
        add(panelForm, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}