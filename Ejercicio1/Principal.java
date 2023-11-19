package Ejercicio1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Calculator {
    double[] Notas;

    public Calculator() {
        Notas = new double[5];
    }

    double calculateAverage() {
        double suma = 0;
        for (double nota : Notas) {
            suma += nota;
        }
        return suma / Notas.length;
    }

    double calculateDeviation() {
        double avg = calculateAverage();
        double sum = 0;
        for (int i = 0; i < Notas.length; i++) {
            sum += Math.pow(Notas[i] - avg, 2);
        }
        return Math.sqrt(sum / Notas.length);
    }

    double calculateMin() {
        double min = Notas[0];
        for (int i = 0; i < Notas.length; i++) {
            if (Notas[i] < min) {
                min = Notas[i];
            }
        }
        return min;
    }

    double calculateMax() {
        double max = Notas[0];
        for (int i = 0; i < Notas.length; i++) {
            if (Notas[i] > max) {
                max = Notas[i];
            }
        }
        return max;
    }
}

class MainWindow extends JFrame implements ActionListener {

    private Container container;

    private JLabel[] labels;
    private JTextField[] NotasFields;

    private JButton calculateButton, clearButton;

    private JLabel averageLabel, deviationLabel, maxLabel, minLabel;

    public MainWindow() {
        initializeInterface();

        setTitle("Notas");
        setSize(300, 400);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initializeInterface() {
        container = getContentPane();
        container.setLayout(null);

        labels = new JLabel[5];
        NotasFields = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            labels[i] = new JLabel();
            labels[i].setText("Nota " + (i + 1) + ":");
            labels[i].setBounds(20, 20 + i * 30, 150, 23);

            NotasFields[i] = new JTextField();
            NotasFields[i].setBounds(105, 20 + i * 30, 160, 23);

            container.add(labels[i]);
            container.add(NotasFields[i]);
        }

        calculateButton = new JButton();
        calculateButton.setText("Calcular");
        calculateButton.setBounds(20, 170, 115, 23);
        calculateButton.addActionListener(this);

        clearButton = new JButton();
        clearButton.setText("Limpiar Campos");
        clearButton.setBounds(150, 170, 115, 23);
        clearButton.addActionListener(this);

        averageLabel = new JLabel();
        averageLabel.setText("Promedio = ");
        averageLabel.setBounds(20, 210, 150, 23);

        deviationLabel = new JLabel();
        deviationLabel.setText("Desviación Estandar = ");
        deviationLabel.setBounds(20, 240, 220, 23);

        maxLabel = new JLabel();
        maxLabel.setText("Maxima Nota = ");
        maxLabel.setBounds(20, 270, 220, 23);

        minLabel = new JLabel();
        minLabel.setText("Minima Nota = ");
        minLabel.setBounds(20, 300, 220, 23);

        container.add(calculateButton);
        container.add(clearButton);
        container.add(averageLabel);
        container.add(deviationLabel);
        container.add(maxLabel);
        container.add(minLabel);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == calculateButton) {
            try {
                // Verificar si todos los campos están llenos
                for (JTextField gradeField : NotasFields) {
                    if (gradeField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos de notas.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;  // Detener el proceso si algún campo está vacío
                    }
                }

                Calculator calculator = new Calculator();
                for (int i = 0; i < calculator.Notas.length; i++) {
                    calculator.Notas[i] = Double.parseDouble(NotasFields[i].getText());
                }

                double average = calculator.calculateAverage();
                double deviation = calculator.calculateDeviation();
                double maxGrade = calculator.calculateMax();
                double minGrade = calculator.calculateMin();

                averageLabel.setText("Promedio = " + String.format("%.2f", average));
                deviationLabel.setText("Desviación Estandar = " + String.format("%.2f", deviation));
                maxLabel.setText("Maxima Nota = " + String.valueOf(maxGrade));
                minLabel.setText("Minima Nota = " + String.valueOf(minGrade));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingresa valores válidos en los campos de notas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (event.getSource() == clearButton) {
            for (JTextField gradeField : NotasFields) {
                gradeField.setText("");
            }

            averageLabel.setText("Promedio = ");
            deviationLabel.setText("Desviación Estandar = ");
            maxLabel.setText("Maxima Nota = ");
            minLabel.setText("Minima Nota = ");
        }
    }
}

public class Principal{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}