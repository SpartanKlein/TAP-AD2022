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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConsultasEquipo {
    static final String CONTROLADOR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String URL_BASEDATOS = "jdbc:sqlserver://localhost;databaseName=Evaluacion;encrypt=true; TrustServerCertificate=true";
    private static final String NOMBREUSUARIO = "jhtp7";
    private static final String CONTRASENA = "jhtp7";
    private Connection conexion = null;
    private PreparedStatement seleccionarTodosLosEquipos = null;
    private PreparedStatement seleccionarEquiposPorMarca = null;
    private PreparedStatement insertarNuevoEquipo = null;
    private PreparedStatement modificarEquipoActual = null;

    public ConsultasEquipo() {
        try {
            Class.forName(CONTROLADOR);
            this.conexion = DriverManager.getConnection(URL_BASEDATOS, NOMBREUSUARIO, CONTRASENA);
            this.seleccionarTodosLosEquipos = this.conexion.prepareStatement("SELECT * FROM Equipos");
            this.seleccionarEquiposPorMarca = this.conexion.prepareStatement("SELECT * FROM Equipos WHERE Marca = ?");
            this.insertarNuevoEquipo = this.conexion.prepareStatement("INSERT INTO Equipos ( TipoComputadora, Marca, Modelo, Procesador, Memoria, Almacenamiento ) VALUES ( ?, ?, ?, ?, ?, ? )");
            this.modificarEquipoActual = this.conexion.prepareStatement("UPDATE Equipos SET TipoComputadora = ?, Marca = ?, Modelo = ?, Procesador = ?, Memoria = ?, Almacenamiento = ? WHERE IDClave = ?");
        } catch (SQLException var2) {
            var2.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
        }

    }

    public List<Equipo> obtenerTodosLosEquipos() {
        ArrayList var1 = null;
        ResultSet var2 = null;

        try {
            var2 = this.seleccionarTodosLosEquipos.executeQuery();
            var1 = new ArrayList();

            while(var2.next()) {
                var1.add(new Equipo(var2.getInt("idClave"), var2.getString("TipoComputadora"), var2.getString("Marca"), var2.getString("Modelo"), var2.getString("Procesador"), var2.getString("Memoria"), var2.getString("Almacenamiento")));
            }
        } catch (SQLException var12) {
            var12.printStackTrace();
        }

        return var1;
    }

    public List<Equipo> obtenerEquiposPorMarca(String var1) {
        ArrayList var2 = null;
        ResultSet var3 = null;

        try {
            this.seleccionarEquiposPorMarca.setString(1, var1);
            var3 = this.seleccionarEquiposPorMarca.executeQuery();
            var2 = new ArrayList();

            while(var3.next()) {
                var2.add(new Equipo(var3.getInt("idClave"), var3.getString("TipoComputadora"), var3.getString("Marca"), var3.getString("Modelo"), var3.getString("Procesador"), var3.getString("Memoria"), var3.getString("Almacenamiento")));
            }
        } catch (SQLException var13) {
            var13.printStackTrace();
        }

        return var2;
    }

    public int agregarEquipo(String var1, String var2, String var3, String var4, String var5, String var6) {
        int var7 = 0;

        try {
            this.insertarNuevoEquipo.setString(1, var1);
            this.insertarNuevoEquipo.setString(2, var2);
            this.insertarNuevoEquipo.setString(3, var3);
            this.insertarNuevoEquipo.setString(4, var4);
            this.insertarNuevoEquipo.setString(5, var5);
            this.insertarNuevoEquipo.setString(6, var6);
            var7 = this.insertarNuevoEquipo.executeUpdate();
        } catch (SQLException var9) {
            var9.printStackTrace();
            this.close();
        }

        return var7;
    }

    public int modificarEquipo(String var1, String var2, String var3, String var4, String var5, String var6, int var7) {
        int var8 = 0;

        try {
            this.modificarEquipoActual.setString(1, var1);
            this.modificarEquipoActual.setString(2, var2);
            this.modificarEquipoActual.setString(3, var3);
            this.modificarEquipoActual.setString(4, var4);
            this.modificarEquipoActual.setString(5, var5);
            this.modificarEquipoActual.setString(6, var6);
            this.modificarEquipoActual.setInt(7, var7);
            var8 = this.modificarEquipoActual.executeUpdate();
        } catch (SQLException var10) {
            var10.printStackTrace();
            this.close();
        }

        return var8;
    }

    public void close() {
        try {
            this.conexion.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }
}
