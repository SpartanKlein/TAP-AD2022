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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.AbstractTableModel;


public class ResultSetTableModel extends AbstractTableModel {
    private Connection conexion;
    private Statement instruccion;
    private ResultSet conjuntoResultados;
    private ResultSetMetaData metaDatos;
    private int numeroDeFilas;
    private boolean conectadoABaseDatos = false;

    public ResultSetTableModel(String var1, String var2, String var3, String var4, String var5) throws SQLException, ClassNotFoundException {
        Class.forName(var1);
        this.conexion = DriverManager.getConnection(var2, var3, var4);
        this.instruccion = this.conexion.createStatement(1004, 1007);
        this.conectadoABaseDatos = true;
        this.establecerConsulta(var5);
    }

    public Class getColumnClass(int var1) throws IllegalStateException {
        if (!this.conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la base de datos");
        } else {
            try {
                String var2 = this.metaDatos.getColumnClassName(var1 + 1);
                return Class.forName(var2);
            } catch (Exception var3) {
                var3.printStackTrace();
                return Object.class;
            }
        }
    }

    public int getColumnCount() throws IllegalStateException {
        if (!this.conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexión a la base de datos");
        } else {
            try {
                return this.metaDatos.getColumnCount();
            } catch (SQLException var2) {
                var2.printStackTrace();
                return 0;
            }
        }
    }

    public String getColumnName(int var1) throws IllegalStateException {
        if (!this.conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la base de datos");
        } else {
            try {
                return this.metaDatos.getColumnName(var1 + 1);
            } catch (SQLException var3) {
                var3.printStackTrace();
                return "";
            }
        }
    }

    public int getRowCount() throws IllegalStateException {
        if (!this.conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la base de datos");
        } else {
            return this.numeroDeFilas;
        }
    }

    public Object getValueAt(int var1, int var2) throws IllegalStateException {
        if (!this.conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la base de datos");
        } else {
            try {
                this.conjuntoResultados.absolute(var1 + 1);
                return this.conjuntoResultados.getObject(var2 + 1);
            } catch (SQLException var4) {
                var4.printStackTrace();
                return "";
            }
        }
    }

    public void establecerConsulta(String var1) throws SQLException, IllegalStateException {
        if (!this.conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la base de datos");
        } else {
            this.conjuntoResultados = this.instruccion.executeQuery(var1);
            this.metaDatos = this.conjuntoResultados.getMetaData();
            this.conjuntoResultados.last();
            this.numeroDeFilas = this.conjuntoResultados.getRow();
            this.fireTableStructureChanged();
        }
    }

    public void desconectarDeBaseDatos() {
        if (this.conectadoABaseDatos) {
            try {
                this.conjuntoResultados.close();
                this.instruccion.close();
                this.conexion.close();
            } catch (SQLException var5) {
                var5.printStackTrace();
            } finally {
                this.conectadoABaseDatos = false;
            }
        }

    }
}
