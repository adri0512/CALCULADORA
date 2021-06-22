/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorasoftware;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Adriana
 */
public class moneda extends JFrame {

    JPanel Num, panelesRadio, panelOperaciones;
    JTextField visual;
    JRadioButton dolar, euro, yen;
    boolean nuevaOperacion = true;
    double resultado = 0;
    float vDolar = 0.049f, vEuro = 0.041f, vYen = 5.43f;
    int num;

    public moneda() {
        super();
        setSize(400, 250); //TAMAÑO PANEL
        setTitle("MONEDA");
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
        dolar = new JRadioButton("Dolar");
        euro = new JRadioButton("Euro");
        yen = new JRadioButton("Yen");
        panelesRadio.add(dolar);
        panelesRadio.add(euro);
        panelesRadio.add(yen);
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
            if (dolar.isSelected()) {
                if (visual.getText().length() > 0) {
                    num = Integer.parseInt(visual.getText());
                    float resultado = num * vDolar;
                    visual.setText(Float.toString(resultado));
                }
            }
        }
        if (tecla.equals("Aceptar")) {
            if (dolar.isSelected()) {
                if (visual.getText().length() > 0) {
                    num = Integer.parseInt(visual.getText());
                    float resultado = num * vDolar;
                    visual.setText(Float.toString(Math.round(resultado)));
                }
            }
        }
        if (tecla.equals("Aceptar")) {
            if (euro.isSelected()) {
                if (visual.getText().length() > 0) {
                    num = Integer.parseInt(visual.getText());
                    float resultado = num * vEuro;
                    visual.setText(Float.toString(Math.round(resultado)));
                }
            }
        }
        if (tecla.equals("Aceptar")) {
            if (yen.isSelected()) {
                if (visual.getText().length() > 0) {
                    num = Integer.parseInt(visual.getText());
                    float resultado = num * vYen;
                    visual.setText(Float.toString(Math.round(resultado)));
                }
            }
        }
    }
}
