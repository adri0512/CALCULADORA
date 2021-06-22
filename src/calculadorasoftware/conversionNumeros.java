package calculadorasoftware;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class conversionNumeros extends JFrame {

    JTextField visual;
    JPanel Num, panelesRadio, panelOperaciones;
    boolean nuevaOperacion = true;
    JRadioButton bin, hex, oc;
    double resultado;
    int num;

    public conversionNumeros() {
        super();
        setSize(400, 250); //TAMAÑO PANEL
        setTitle("SISTEMAS NUMERICOS");
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //definiendo la salida del programa
        setResizable(false);  //para dejar la pantalla fija
        setLocationRelativeTo(null);
        //  panel
        JPanel panel = (JPanel) this.getContentPane();
        //agregamos border al panel 
        panel.setLayout(new BorderLayout()); //esto nos srive poner el borderlayout en nuestro panel

        visual = new JTextField("0", 20); //para el texto cuando se inicia la calculadora
        //EmptyBorder:Es el espacio que ocupará
        visual.setBorder(new EmptyBorder(4, 4, 4, 4)); //tamaño para la muestra de la pantalla
        visual.setFont(new Font("Arial", Font.BOLD, 25));  //tamaño y tipo de letra
        visual.setHorizontalAlignment(JTextField.RIGHT); //alineacion del texto
        visual.setEditable(false); //para que no se pueda editar en el resultado
        visual.setBackground(Color.GRAY); //fondo del Text
        panel.add("North", visual);

        Num = new JPanel();
        Num.setLayout(new GridLayout(4, 3)); //FILAS, COLUMNAS
        Num.setBorder(new EmptyBorder(2, 2, 2, 2));

        for (int n = 9; n >= 0; n--) {
            nuevoBotonNumerico("" + n);
        }
        panel.add("Center", Num);

        panelesRadio = new JPanel();
        bin = new JRadioButton("Binario");
        hex = new JRadioButton("Hexadecimal");
        oc = new JRadioButton("Octal");

        panelesRadio.add(bin);
        panelesRadio.add(hex);
        panelesRadio.add(oc);
        panel.add("South", panelesRadio);

        panelOperaciones = new JPanel(); //Creamos un nuevo objeto en el panel
        panelOperaciones.setLayout(new GridLayout(3, 0));  //para el panel y ubicaion
        panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));  //para el bordel del panel
        //Nombraremos los botones de las operaciones
        nuevoBotonOperacion("CE");
        nuevoBotonOperacion("Aceptar");

        panel.add("East", panelOperaciones);

    }

    private void nuevoBotonNumerico(String digito) {
        JButton btn = new JButton();
        btn.setBackground(java.awt.Color.DARK_GRAY); //para el color del boton
        btn.setText(digito);
        btn.setForeground(Color.GREEN);
        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                numeroPulsado(btn.getText());
            }
        });

        Num.add(btn);
    }

    private void numeroPulsado(String digito) {
        if (visual.getText().equals("0") || nuevaOperacion) {
            visual.setText(digito);
        } else {
            visual.setText(visual.getText() + digito);
        }
        nuevaOperacion = false;
    }

    private void nuevoBotonOperacion(String operacion) {
        JButton btn = new JButton(operacion);
        btn.setForeground(Color.GREEN);
        btn.setBackground(java.awt.Color.DARK_GRAY); //para el color del boton
        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                operacionPulsado(btn.getText());
            }
        });

        panelOperaciones.add(btn);
    }

    private void operacionPulsado(String tecla) {
        if (tecla.equals("CE")) {
            resultado = 0;
            visual.setText("");
            nuevaOperacion = true;
        }
        if (tecla.equals("Aceptar")) {
            if (bin.isSelected()) {
                if (visual.getText().length() > 0) {
                    String binario = "";
                    num = Integer.parseInt(visual.getText());
                    while (num > 0) {
                        binario = num % 2 + binario;
                        num = num / 2;
                    }
                    visual.setText(binario);
                }
            }
            if (hex.isSelected()) {
                if (visual.getText().length() > 0) {
                    int residuo;
                    String hexadecimal = "";
                    char[] caracHex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                    num = Integer.parseInt(visual.getText());
                    while (num > 0) {
                        residuo = num % 16;
                        char caracterHexa = caracHex[residuo];
                        hexadecimal = caracterHexa + hexadecimal;
                        num = num / 16;
                    }
                    visual.setText(hexadecimal);
                }
            }
            if (oc.isSelected()) {
                if (visual.getText().length() > 0) {
                    int residuo;
                    String octal = "";
                    char[] caracterOctal = {'0', '1', '2', '3', '4', '5', '6', '7'};
                    num = Integer.parseInt(visual.getText());
                    while (num > 0) {
                        residuo = num % 8;
                        char caracterOc = caracterOctal[residuo];
                        octal = caracterOc + octal;
                        num = num / 8;
                    }
                    visual.setText(octal);
                }
            }

        }

    }
}
