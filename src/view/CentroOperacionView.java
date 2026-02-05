package view;

import javax.swing.*;

public class CentroOperacionView extends JFrame {

    public JTextField txtId = new JTextField(5);
    public JTextField txtNombre = new JTextField(15);
    public JButton btnGuardar = new JButton("Guardar");

    public CentroOperacionView() {

        setTitle("Centro Operaciones");
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
