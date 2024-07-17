import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainForm {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Map<String, Estudiante> estudianteMap;

    public MainForm() {
        estudianteMap = new HashMap<>();

        frame = new JFrame("Gestión de Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Pantallas
        JPanel insertarPanel = crearInsertarPanel();
        JPanel buscarPanel = crearBuscarPanel();

        mainPanel.add(insertarPanel, "insertar");
        mainPanel.add(buscarPanel, "buscar");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel crearInsertarPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField(20);

        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField(20);

        JLabel cedulaLabel = new JLabel("Cédula:");
        JTextField cedulaField = new JTextField(20);

        JLabel edadLabel = new JLabel("Edad:");
        JTextField edadField = new JTextField(20);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String cedula = cedulaField.getText();
                int edad = Integer.parseInt(edadField.getText());

                Estudiante estudiante = new Estudiante(nombre, apellido, cedula, edad);
                estudianteMap.put(cedula, estudiante);
                JOptionPane.showMessageDialog(frame, "Estudiante registrado con éxito");
            }
        });

        JButton buscarButton = new JButton("Buscar Estudiante");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "buscar");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        panel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(apellidoLabel, gbc);

        gbc.gridx = 1;
        panel.add(apellidoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(cedulaLabel, gbc);

        gbc.gridx = 1;
        panel.add(cedulaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(edadLabel, gbc);

        gbc.gridx = 1;
        panel.add(edadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registrarButton, gbc);

        gbc.gridy = 5;
        panel.add(buscarButton, gbc);

        return panel;
    }

    private JPanel crearBuscarPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel cedulaLabel = new JLabel("Cédula:");
        JTextField cedulaField = new JTextField(20);

        JTextArea resultadoArea = new JTextArea(5, 20);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaField.getText();
                Estudiante estudiante = estudianteMap.get(cedula);
                if (estudiante != null) {
                    resultadoArea.setText(estudiante.toString());
                } else {
                    resultadoArea.setText("Estudiante no encontrado.");
                }
            }
        });

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "insertar");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(cedulaLabel, gbc);

        gbc.gridx = 1;
        panel.add(cedulaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(buscarButton, gbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(volverButton, gbc);

        return panel;
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        new MainForm();
    }
}
