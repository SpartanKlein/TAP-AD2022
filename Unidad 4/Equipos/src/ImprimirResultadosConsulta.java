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
import java.awt.print.PrinterException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ImprimirResultadosConsulta extends JFrame{
        static final String CONTROLADOR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        static final String URL = "jdbc:sqlserver://localhost;databaseName=Evaluacion;encrypt=true;TrustServerCertificate=true";
        static final String USUARIO = "jhtp7";
        static final String CONTRASENA = "jhtp7";
        static final String CONSULTA_P = "SELECT * FROM Equipos";
        private ResultSetTableModel modeloTabla;

        public ImprimirResultadosConsulta() {
                try {
                        this.modeloTabla = new ResultSetTableModel(CONTROLADOR, URL, USUARIO, CONTRASENA, CONSULTA_P);
                        JTable var1 = new JTable(this.modeloTabla);
                        this.add(new JScrollPane(var1));
                        this.setSize(500, 250);
                        this.setVisible(false);

                        try {
                                var1.print();
                        } catch (PrinterException var3) {
                                JOptionPane.showMessageDialog((Component)null, var3.getMessage(), "Error al imprimir", 0);
                        }
                } catch (ClassNotFoundException var4) {
                        JOptionPane.showMessageDialog((Component)null, "No se encontro controlador de base de datos", "No se encontro el controlador", 0);
                        System.exit(1);
                } catch (SQLException var5) {
                        JOptionPane.showMessageDialog((Component)null, var5.getMessage(), "Error en base de datos", 0);
                        this.modeloTabla.desconectarDeBaseDatos();
                        System.exit(1);
                }
        }
}
