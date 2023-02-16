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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MiVentanaCatalogo extends JFrame{
    private JMenuBar barraMenu;
    private JMenu menuArchivo;
    private JMenuItem mnuArcActualizar;
    private JMenuItem mnuArcConsultar;
    private JMenuItem mnuArcImprimir;
    private JMenuItem mnuArcSalir;
    private JMenu menuAyuda;
    private JMenuItem mnuAyuAcercaDe;
    private MostrarEquipos mostrarEquipos = null;
    private MostrarResultadosConsulta mostrarResultadosConsulta = null;

    public MiVentanaCatalogo(String var1) {
        super(var1);
        MiOyenteVentana var2 = new MiOyenteVentana();
        MiOyenteArchivo var3 = new MiOyenteArchivo();
        MiOyenteAcercaDe var4 = new MiOyenteAcercaDe();
        this.barraMenu = new JMenuBar();
        this.menuArchivo = new JMenu("Archivo");
        this.menuArchivo.setMnemonic('A');
        this.mnuArcActualizar = new JMenuItem("Actualizar catálogo...");
        this.mnuArcActualizar.setMnemonic('t');
        this.mnuArcActualizar.addActionListener(var3);
        this.mnuArcConsultar = new JMenuItem("Consultar catálogo...");
        this.mnuArcConsultar.setMnemonic('A');
        this.mnuArcConsultar.addActionListener(var3);
        this.mnuArcImprimir = new JMenuItem("Imprimir..");
        this.mnuArcImprimir.setMnemonic('I');
        this.mnuArcImprimir.addActionListener(var3);
        this.mnuArcSalir = new JMenuItem("Salir");
        this.mnuArcSalir.setMnemonic('l');
        this.mnuArcSalir.addActionListener(var3);
        this.menuArchivo.add(this.mnuArcActualizar);
        this.menuArchivo.add(this.mnuArcConsultar);
        this.menuArchivo.addSeparator();
        this.menuArchivo.add(this.mnuArcImprimir);
        this.menuArchivo.addSeparator();
        this.menuArchivo.add(this.mnuArcSalir);
        this.barraMenu.add(this.menuArchivo);
        this.menuAyuda = new JMenu("Ayuda");
        this.menuAyuda.setMnemonic('A');
        this.mnuAyuAcercaDe = new JMenuItem("Acerca de...");
        this.mnuAyuAcercaDe.setMnemonic('A');
        this.mnuAyuAcercaDe.addActionListener(var4);
        this.menuAyuda.add(this.mnuAyuAcercaDe);
        this.barraMenu.add(this.menuAyuda);
        this.setJMenuBar(this.barraMenu);
        this.addWindowListener(var2);
    }

    private void preguntarAntesDeSalir() {
        if (0 == JOptionPane.showConfirmDialog(this, "¿Deseas salir de la aplicación?", "Catálogo de Equipos", 0)) {
            System.exit(0);
        }

    }

    class MiOyenteVentana extends WindowAdapter {
        MiOyenteVentana() {
        }

        public void windowClosing(WindowEvent var1) {
            MiVentanaCatalogo.this.preguntarAntesDeSalir();
        }
    }

    class MiOyenteArchivo implements ActionListener {
        MiOyenteArchivo() {
        }

        public void actionPerformed(ActionEvent var1) {
            JMenuItem var2 = (JMenuItem)var1.getSource();
            if (var2 == MiVentanaCatalogo.this.mnuArcActualizar) {
                if (MiVentanaCatalogo.this.mostrarEquipos == null) {
                    MiVentanaCatalogo.this.mostrarEquipos = new MostrarEquipos();
                } else {
                    MiVentanaCatalogo.this.mostrarEquipos.setVisible(true);
                }
            } else if (var2 == MiVentanaCatalogo.this.mnuArcConsultar) {
                if (MiVentanaCatalogo.this.mostrarResultadosConsulta == null) {
                    MiVentanaCatalogo.this.mostrarResultadosConsulta = new MostrarResultadosConsulta();
                } else {
                    MiVentanaCatalogo.this.mostrarResultadosConsulta.setVisible(true);
                }
            } else if (var2 == MiVentanaCatalogo.this.mnuArcImprimir) {
                new ImprimirResultadosConsulta();
            } else if (var2 == MiVentanaCatalogo.this.mnuArcSalir) {
                MiVentanaCatalogo.this.preguntarAntesDeSalir();
            }

        }
    }

    class MiOyenteAcercaDe implements ActionListener {
        MiOyenteAcercaDe() {
        }

        public void actionPerformed(ActionEvent var1) {
            JOptionPane.showMessageDialog(MiVentanaCatalogo.this, "Programa de acceso a base de datos de equipos de computo \n Elaborado por: Jaime Alonso Ruiz Lizarraga  \n No. de control: 19170736", "Datos del autor", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
