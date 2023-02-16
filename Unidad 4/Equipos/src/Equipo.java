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

public class Equipo {
    private int idClave;
    private String tipoComputadora;
    private String marca;
    private String modelo;
    private String procesador;
    private String memoria;
    private String almacenamiento;

    public Equipo() {
    }

    public Equipo(int idClave, String tipoComputadora, String marca, String modelo, String procesador, String memoria, String almacenamiento) {
        this.setIDClave(idClave);
        this.setTipoComputadora(tipoComputadora);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setProcesador(procesador);
        this.setMemoria(memoria);
        this.setAlmacenamiento(almacenamiento);
    }

    public void setIDClave(int idClave) {
        this.idClave = idClave;
    }

    public int getIDClave() {
        return this.idClave;
    }

    public void setTipoComputadora(String tipoComputadora) {
        this.tipoComputadora = tipoComputadora;
    }

    public String getTipoComputadora() {
        return this.tipoComputadora;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getProcesador() {
        return this.procesador;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getMemoria() {
        return this.memoria;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getAlmacenamiento() {
        return this.almacenamiento;
    }
}
