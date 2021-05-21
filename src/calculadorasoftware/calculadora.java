package calculadorasoftware;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class calculadora extends JFrame {

    JTextField visual;
    double resultado;
    String operacion;
    JPanel Num, panelOperaciones;
    boolean nuevaOperacion = true;

    public calculadora() {
        super();
        setSize(300, 400); //TAMAÑO PANEL
        setTitle("Calculadora Simple");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //definiendo la salida del programa
        setResizable(false);  //para dejar la pantalla fija

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
        Num.setLayout(new GridLayout(4, 3));
        Num.setBorder(new EmptyBorder(4, 4, 4, 4));

        for (int n = 9; n >= 0; n--) {
            nuevoBotonNumerico("" + n);
        }

        nuevoBotonNumerico(".");
        nuevoBotonNumerico(";-;");

        panel.add("Center", Num);

        panelOperaciones = new JPanel(); //Creamos un nuevo objeto en el panel
        panelOperaciones.setLayout(new GridLayout(6, 1));  //para el panel y ubicaion
        panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));  //para el bordel del panel
        //Nombraremos los botones de las operaciones

        nuevoBotonOperacion("+");
        nuevoBotonOperacion("-");
        nuevoBotonOperacion("*");
        nuevoBotonOperacion("/");
        nuevoBotonOperacion("=");
        nuevoBotonOperacion("CE");

        panel.add("East", panelOperaciones);

        validate();
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

    private void numeroPulsado(String digito) {
        if (visual.getText().equals("0") || nuevaOperacion) {
            visual.setText(digito);
        } else {
            visual.setText(visual.getText() + digito);
        }
        nuevaOperacion = false;
    }

    private void operacionPulsado(String tecla) {
        if (tecla.equals("=")) {
            calcularResultado();
        } else if (tecla.equals("CE")) {
            resultado = 0;
            visual.setText("");
            nuevaOperacion = true;
        } else {
            operacion = tecla;
            if ((resultado > 0) && !nuevaOperacion) {
                calcularResultado();
            } else {
                resultado = Double.parseDouble(visual.getText());
            }
        }

        nuevaOperacion = true;
    }

    private void calcularResultado() {
        if (operacion.equals("+")) {
            resultado += Double.parseDouble(visual.getText());
        } else if (operacion.equals("-")) {
            resultado -= Double.parseDouble(visual.getText());
        } else if (operacion.equals("/")) {
            resultado /= Double.parseDouble(visual.getText());
        } else if (operacion.equals("*")) {
            resultado *= Double.parseDouble(visual.getText());
        }

        visual.setText("" + resultado);
        operacion = "";
    }

}
