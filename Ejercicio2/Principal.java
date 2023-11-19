package Ejercicio2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FiguraGeometrica {
    private double volumen;
    private double areaSuperficial;

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public void setAreaSuperficial(double areaSuperficial) {
        this.areaSuperficial = areaSuperficial;
    }

    public double getVolumen() {
        return volumen;
    }

    public double getAreaSuperficial() {
        return areaSuperficial;
    }
}

class Cilindro extends FiguraGeometrica {
    private double radio;
    private double altura;

    public Cilindro(double radio, double altura) {
        this.radio = radio;
        this.altura = altura;
        this.setVolumen(calcularVolumen());
        this.setAreaSuperficial(calcularAreaSuperficial());
    }

    public double calcularVolumen() {
        return Math.PI * altura * Math.pow(radio, 2.0);
    }

    public double calcularAreaSuperficial() {
        double areaLadoA = 2.0 * Math.PI * radio * altura;
        double areaLadoB = 2.0 * Math.PI * Math.pow(radio, 2.0);
        return areaLadoA + areaLadoB;
    }
}

class Cubo extends FiguraGeometrica {
    private double lado;

    public Cubo(double lado) {
        this.lado = lado;
        this.setVolumen(calcularVolumen());
        this.setAreaSuperficial(calcularAreaSuperficial());
    }

    public double calcularVolumen() {
        return Math.pow(lado, 3);
    }

    public double calcularAreaSuperficial() {
        return 6 * Math.pow(lado, 2);
    }
}

class Esfera extends FiguraGeometrica {
    private double radio;

    public Esfera(double radio) {
        this.radio = radio;
        this.setVolumen(calcularVolumen());
        this.setAreaSuperficial(calcularAreaSuperficial());
    }

    public double calcularVolumen() {
        return 1.333 * Math.PI * Math.pow(this.radio, 3.0);
    }

    public double calcularAreaSuperficial() {
        return 4.0 * Math.PI * Math.pow(this.radio, 2.0);
    }
}

class Piramide extends FiguraGeometrica {
    private double base;
    private double altura;
    private double apotema;

    public Piramide(double base, double altura, double apotema) {
        this.base = base;
        this.altura = altura;
        this.apotema = apotema;
        this.setVolumen(calcularVolumen());
        this.setAreaSuperficial(calcularAreaSuperficial());
    }

    public double calcularVolumen() {
        return (Math.pow(base, 2.0) * altura) / 3.0;
    }

    public double calcularAreaSuperficial() {
        double areaBase = Math.pow(base, 2.0);
        double areaLateral = 2.0 * base * apotema;
        return areaBase + areaLateral;
    }
}

class Prisma extends FiguraGeometrica {
    private double base;
    private double altura;
    private double profundidad;

    public Prisma(double base, double altura, double profundidad) {
        this.base = base;
        this.altura = altura;
        this.profundidad = profundidad;
        this.setVolumen(calcularVolumen());
        this.setAreaSuperficial(calcularAreaSuperficial());
    }

    public double calcularVolumen() {
        return base * altura * profundidad;
    }

    public double calcularAreaSuperficial() {
        return 2 * (base * altura + base * profundidad + altura * profundidad);
    }
}

class VentanaCilindro extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel labelRadio, labelAltura, labelVolumen, labelSuperficie;
    private JTextField campoRadio, campoAltura;
    private JButton botonCalcular;

    public VentanaCilindro() {
        inicializar();
        setTitle("Cilindro");
        setSize(280, 210);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializar() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        labelRadio = new JLabel("Radio (cms):");
        labelRadio.setBounds(20, 20, 135, 23);
        campoRadio = new JTextField();
        campoRadio.setBounds(100, 20, 135, 23);

        labelAltura = new JLabel("Altura (cms):");
        labelAltura.setBounds(20, 50, 135, 23);
        campoAltura = new JTextField();
        campoAltura.setBounds(100, 50, 135, 23);

        botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(100, 80, 135, 23);
        botonCalcular.addActionListener(this);

        labelVolumen = new JLabel("Volumen (cm3):");
        labelVolumen.setBounds(20, 110, 135, 23);
        labelSuperficie = new JLabel("Superficie (cm2):");
        labelSuperficie.setBounds(20, 140, 135, 23);

        contenedor.add(labelRadio);
        contenedor.add(campoRadio);
        contenedor.add(labelAltura);
        contenedor.add(campoAltura);
        contenedor.add(botonCalcular);
        contenedor.add(labelVolumen);
        contenedor.add(labelSuperficie);
    }

    public void actionPerformed(ActionEvent event) {
        boolean error = false;
        double radio = 0;
        double altura = 0;
        try {
            radio = Double.parseDouble(campoRadio.getText());
            altura = Double.parseDouble(campoAltura.getText());
            Cilindro cilindro = new Cilindro(radio, altura);
            labelVolumen.setText("Volumen (cm3): " + String.format("%.2f", cilindro.getVolumen()));
            labelSuperficie.setText("Superficie (cm2): " + String.format("%.2f", cilindro.getAreaSuperficial()));
        } catch (Exception e) {
            error = true;
        } finally {
            if (error) {
                JOptionPane.showMessageDialog(null, "Campo nulo o error en formato de número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

class VentanaCubo extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel labelLado, labelVolumen, labelSuperficie;
    private JTextField campoLado;
    private JButton botonCalcular;

    public VentanaCubo() {
        inicializar();
        setTitle("Cubo");
        setSize(280, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializar() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        labelLado = new JLabel("Lado (cms):");
        labelLado.setBounds(20, 20, 135, 23);
        campoLado = new JTextField();
        campoLado.setBounds(100, 20, 135, 23);

        botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(100, 50, 135, 23);
        botonCalcular.addActionListener(this);

        labelVolumen = new JLabel("Volumen (cm3):");
        labelVolumen.setBounds(20, 90, 135, 23);
        labelSuperficie = new JLabel("Superficie (cm2):");
        labelSuperficie.setBounds(20, 120, 135, 23);

        contenedor.add(labelLado);
        contenedor.add(campoLado);
        contenedor.add(botonCalcular);
        contenedor.add(labelVolumen);
        contenedor.add(labelSuperficie);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botonCalcular) {
            boolean error = false;
            try {
                double lado = Double.parseDouble(campoLado.getText());
                Cubo cubo = new Cubo(lado);
                labelVolumen.setText("Volumen (cm3): " + String.format("%.2f", cubo.getVolumen()));
                labelSuperficie.setText("Superficie (cm2): " + String.format("%.2f", cubo.getAreaSuperficial()));
            } catch (NumberFormatException e) {
                error = true;
            } finally {
                if (error) {
                    JOptionPane.showMessageDialog(null, "Campo nulo o error en formato de número", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

class VentanaEsfera extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel labelRadio, labelVolumen, labelSuperficie;
    private JTextField campoRadio;
    private JButton botonCalcular;

    public VentanaEsfera() {
        inicializar();
        setTitle("Esfera");
        setSize(280, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializar() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        labelRadio = new JLabel("Radio (cms):");
        labelRadio.setBounds(20, 20, 135, 23);
        campoRadio = new JTextField();
        campoRadio.setBounds(100, 20, 135, 23);

        botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(100, 50, 135, 23);
        botonCalcular.addActionListener(this);

        labelVolumen = new JLabel("Volumen (cm3):");
        labelVolumen.setBounds(20, 90, 135, 23);
        labelSuperficie = new JLabel("Superficie (cm2):");
        labelSuperficie.setBounds(20, 120, 135, 23);

        contenedor.add(labelRadio);
        contenedor.add(campoRadio);
        contenedor.add(botonCalcular);
        contenedor.add(labelVolumen);
        contenedor.add(labelSuperficie);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botonCalcular) {
            boolean error = false;
            try {
                double radio = Double.parseDouble(campoRadio.getText());
                Esfera esfera = new Esfera(radio);
                labelVolumen.setText("Volumen (cm3): " + String.format("%.2f", esfera.getVolumen()));
                labelSuperficie.setText("Superficie (cm2): " + String.format("%.2f", esfera.getAreaSuperficial()));
            } catch (Exception e) {
                error = true;
            } finally {
                if (error) {
                    JOptionPane.showMessageDialog(null, "Campo nulo o error en formato de número", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

class VentanaPiramide extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel labelBase, labelAltura, labelApotema, labelVolumen, labelSuperficie;
    private JTextField campoBase, campoAltura, campoApotema;
    private JButton botonCalcular;

    public VentanaPiramide() {
        inicializar();
        setTitle("Pirámide");
        setSize(280, 240);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializar() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        labelBase = new JLabel("Base (cms):");
        labelBase.setBounds(20, 20, 135, 23);
        campoBase = new JTextField();
        campoBase.setBounds(120, 20, 135, 23);

        labelAltura = new JLabel("Altura (cms):");
        labelAltura.setBounds(20, 50, 135, 23);
        campoAltura = new JTextField();
        campoAltura.setBounds(120, 50, 135, 23);

        labelApotema = new JLabel("Apotema (cms):");
        labelApotema.setBounds(20, 80, 135, 23);
        campoApotema = new JTextField();
        campoApotema.setBounds(120, 80, 135, 23);

        botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(120, 110, 135, 23);
        botonCalcular.addActionListener(this);

        labelVolumen = new JLabel("Volumen (cm3):");
        labelVolumen.setBounds(20, 140, 135, 23);

        labelSuperficie = new JLabel("Superficie (cm2):");
        labelSuperficie.setBounds(20, 170, 135, 23);

        contenedor.add(labelBase);
        contenedor.add(campoBase);
        contenedor.add(labelAltura);
        contenedor.add(campoAltura);
        contenedor.add(labelApotema);
        contenedor.add(campoApotema);
        contenedor.add(botonCalcular);
        contenedor.add(labelVolumen);
        contenedor.add(labelSuperficie);
    }

    public void actionPerformed(ActionEvent event) {
        Piramide piramide;
        boolean error = false;
        double base = 0;
        double altura = 0;
        double apotema = 0;
        try {
            base = Double.parseDouble(campoBase.getText());
            altura = Double.parseDouble(campoAltura.getText());
            apotema = Double.parseDouble(campoApotema.getText());
            piramide = new Piramide(base, altura, apotema);
            labelVolumen.setText("Volumen (cm3): " + String.format("%.2f", piramide.calcularVolumen()));
            labelSuperficie.setText("Superficie (cm2): " + String.format("%.2f", piramide.calcularAreaSuperficial()));
        } catch (Exception e) {
            error = true;
        } finally {
            if (error) {
                JOptionPane.showMessageDialog(null, "Campo nulo o error en formato de número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

class VentanaPrisma extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel labelBase, labelAltura, labelProfundidad, labelVolumen, labelSuperficie;
    private JTextField campoBase, campoAltura, campoProfundidad;
    private JButton botonCalcular;

    public VentanaPrisma() {
        inicializar();
        setTitle("Prisma");
        setSize(280, 250);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializar() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        labelBase = new JLabel("Base (cm):");
        labelBase.setBounds(20, 20, 100, 23);
        campoBase = new JTextField();
        campoBase.setBounds(130, 20, 120, 23);

        labelAltura = new JLabel("Altura (cm):");
        labelAltura.setBounds(20, 50, 100, 23);
        campoAltura = new JTextField();
        campoAltura.setBounds(130, 50, 120, 23);

        labelProfundidad = new JLabel("Profundidad (cm):");
        labelProfundidad.setBounds(20, 80, 100, 23);
        campoProfundidad = new JTextField();
        campoProfundidad.setBounds(130, 80, 120, 23);

        botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(100, 120, 100, 23);
        botonCalcular.addActionListener(this);

        labelVolumen = new JLabel("Volumen (cm^3):");
        labelVolumen.setBounds(20, 160, 200, 23);
        labelSuperficie = new JLabel("Superficie (cm^2):");
        labelSuperficie.setBounds(20, 190, 200, 23);

        contenedor.add(labelBase);
        contenedor.add(campoBase);
        contenedor.add(labelAltura);
        contenedor.add(campoAltura);
        contenedor.add(labelProfundidad);
        contenedor.add(campoProfundidad);
        contenedor.add(botonCalcular);
        contenedor.add(labelVolumen);
        contenedor.add(labelSuperficie);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botonCalcular) {
            boolean error = false;
            try {
                double base = Double.parseDouble(campoBase.getText());
                double altura = Double.parseDouble(campoAltura.getText());
                double profundidad = Double.parseDouble(campoProfundidad.getText());
                Prisma prisma = new Prisma(base, altura, profundidad);
                labelVolumen.setText("Volumen (cm3): " + String.format("%.2f", prisma.getVolumen()));
                labelSuperficie.setText("Superficie (cm2): " + String.format("%.2f", prisma.getAreaSuperficial()));
            } catch (Exception e) {
                error = true;
            } finally {
                if (error) {
                    JOptionPane.showMessageDialog(null, "Campo nulo o error en formato de número", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

class VentanaPrincipal extends JFrame implements ActionListener {
    private Container contenedor;
    private JButton cilindro, esfera, piramide, cubo, prisma;

    public VentanaPrincipal() {
        inicio();
        setTitle("Figuras");
        setSize(560, 160);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        cilindro = new JButton();
        cilindro.setText("Cilindro");
        cilindro.setBounds(20, 50, 80, 23);
        cilindro.addActionListener(this);

        esfera = new JButton();
        esfera.setText("Esfera");
        esfera.setBounds(125, 50, 80, 23);
        esfera.addActionListener(this);

        piramide = new JButton();
        piramide.setText("Pirámide");
        piramide.setBounds(230, 50, 100, 23);
        piramide.addActionListener(this);

        cubo = new JButton();
        cubo.setText("Cubo");
        cubo.setBounds(345, 50, 80, 23);
        cubo.addActionListener(this);

        prisma = new JButton();
        prisma.setText("Prisma");
        prisma.setBounds(450, 50, 80, 23);
        prisma.addActionListener(this);

        contenedor.add(cilindro);
        contenedor.add(esfera);
        contenedor.add(piramide);
        contenedor.add(cubo);
        contenedor.add(prisma);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == esfera) {
            VentanaEsfera ventanaEsfera = new VentanaEsfera();
            ventanaEsfera.setVisible(true);
        }
        if (evento.getSource() == cilindro) {
            VentanaCilindro ventanaCilindro = new VentanaCilindro();
            ventanaCilindro.setVisible(true);
        }
        if (evento.getSource() == piramide) {
            VentanaPiramide ventanaPiramide = new VentanaPiramide();
            ventanaPiramide.setVisible(true);
        }
        if (evento.getSource() == cubo) {
            VentanaCubo ventanaCubo = new VentanaCubo();
            ventanaCubo.setVisible(true);
        }
        if (evento.getSource() == prisma) {
            VentanaPrisma ventanaPrisma = new VentanaPrisma();
            ventanaPrisma.setVisible(true);
        }
    }
}

public class Principal {
    public static void main(String[] args) {
        VentanaPrincipal miVentanaPrincipal;
        miVentanaPrincipal = new VentanaPrincipal();
        miVentanaPrincipal.setVisible(true);
        miVentanaPrincipal.setResizable(false);
    }
}