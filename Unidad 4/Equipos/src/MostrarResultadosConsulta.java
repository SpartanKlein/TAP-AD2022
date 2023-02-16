/* Datos del desarrollo
---------------------------------------------------------------
//Autor: Jaime Alonso Ruiz Lizarraga
//Fecha: 06/12/22
//Materia: T�picos Avanzados de Programaci�n 17:00 - 18:00
//Semestre: Ago - Dic 2022
//Unidad 4: Acceso a datos
//Proyecto: Ejercicio de Bases de Datos
//Docente: Jaime Arturo F�lix Medina
---------------------------------------------------------------
 */

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class MostrarResultadosConsulta extends JFrame{
    static final String CONTROLADOR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String URL= "jdbc:sqlserver://localhost;databaseName=Evaluacion;encrypt=true;TrustServerCertificate=true";
    static final String USUARIO = "jhtp7";
    static final String CONTRASENA = "jhtp7";
    static final String CONSULTA_P= "SELECT * FROM Equipos";
    private ResultSetTableModel modeloTabla;

    public MostrarResultadosConsulta() {
        super("Visualizacion del catálogo de equipos");

        try {
            this.modeloTabla = new ResultSetTableModel(CONTROLADOR, URL, USUARIO, CONTRASENA, CONSULTA_P);
            JButton btnImprimir = new JButton("Imprimir...");
            Box var2 = Box.createHorizontalBox();
            var2.add(btnImprimir);
            final JTable var3 = new JTable(this.modeloTabla);
            JLabel var4 = new JLabel("Filtro:");
            final JTextField var5 = new JTextField();
            JButton var6 = new JButton("Aplicar filtro");
            Box var7 = Box.createHorizontalBox();
            var7.add(var4);
            var7.add(var5);
            var7.add(var6);
            this.add(var2, "North");
            this.add(new JScrollPane(var3), "Center");
            this.add(var7, "South");
            btnImprimir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent var1) {
                    try {
                        var3.print();
                    } catch (PrinterException var3x) {
                        JOptionPane.showMessageDialog((Component)null, var3x.getMessage(), "Error al imprimir", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
            final TableRowSorter var8 = new TableRowSorter(this.modeloTabla);
            var3.setRowSorter(var8);
            this.setSize(500, 250);
            this.setVisible(true);
            var6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent var1) {
                    String var2 = var5.getText();
                    if (var2.length() == 0) {
                        var8.setRowFilter((RowFilter)null);
                    } else {
                        try {
                            var8.setRowFilter(RowFilter.regexFilter(var2, new int[0]));
                        } catch (PatternSyntaxException var4) {
                            JOptionPane.showMessageDialog(null, "Patron de exp reg incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }
            });
        } catch (ClassNotFoundException var9) {
            JOptionPane.showMessageDialog(null, "No se encontro controlador de base de datos", "No se encontro el controlador", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (SQLException var10) {
            JOptionPane.showMessageDialog(null, var10.getMessage(), "Error en base de datos", JOptionPane.ERROR_MESSAGE);
            this.modeloTabla.desconectarDeBaseDatos();
            System.exit(1);
        }

        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent var1) {
                JFrame var2 = (JFrame)var1.getSource();
                MostrarResultadosConsulta.this.modeloTabla.desconectarDeBaseDatos();
                var2.dispose();
            }
        });
    }
}
