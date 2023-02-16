import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MostrarEquipos extends JFrame{
    private Equipo entradaActual;
    private ConsultasEquipo consultasEquipo = new ConsultasEquipo();
    private List<Equipo> resultados;
    private int numeroDeEntradas = 0;
    private int indiceEntradaActual;
    private JButton botonExplorar = new JButton();
    private JLabel etiquetaModelo = new JLabel();
    private JTextField campoTextoModelo = new JTextField(15);
    private JLabel etiquetaTipoComputadora = new JLabel();
    private JTextField campoTextoTipoComputadora = new JTextField(15);
    private JLabel etiquetaID = new JLabel();
    private JTextField campoTextoID = new JTextField(15);
    private JTextField campoTextoIndice = new JTextField(2);
    private JLabel etiquetaMarca = new JLabel();
    private JTextField campoTextoMarca = new JTextField(15);
    private JTextField campoTextoMax = new JTextField(2);
    private JButton botonSiguiente = new JButton();
    private JLabel etiquetaDe = new JLabel();
    private JLabel etiquetaProcesador = new JLabel();
    private JTextField campoTextoProcesador = new JTextField(15);
    private JButton botonAnterior = new JButton();
    private JButton botonConsulta = new JButton();
    private JLabel etiquetaConsulta = new JLabel();
    private JPanel panelConsulta = new JPanel();
    private JPanel panelNavegar = new JPanel();
    private JPanel panelMostrar = new JPanel();
    private JTextField campoTextoConsulta = new JTextField(15);
    private JButton botonInsertar = new JButton();
    private JLabel etiquetaMemoria = new JLabel();
    private JTextField campoTextoMemoria = new JTextField(15);
    private JLabel etiquetaAlmacenamiento = new JLabel();
    private JTextField campoTextoAlmacenamiento = new JTextField(15);
    private JButton botonModificar = new JButton();

    public MostrarEquipos() {
        super("Catálogo de Equipos");
        this.setLayout(new FlowLayout(1, 10, 10));
        this.setSize(580, 350);
        this.setResizable(false);
        this.panelNavegar.setLayout(new BoxLayout(this.panelNavegar, 0));
        this.botonAnterior.setText("Anterior");
        this.botonAnterior.setEnabled(false);
        this.botonAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                MostrarEquipos.this.botonAnteriorActionPerformed(var1);
            }
        });
        this.panelNavegar.add(this.botonAnterior);
        this.panelNavegar.add(Box.createHorizontalStrut(10));
        this.campoTextoIndice.setHorizontalAlignment(0);
        this.campoTextoIndice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                MostrarEquipos.this.campoTextoIndiceActionPerformed(var1);
            }
        });
        this.panelNavegar.add(this.campoTextoIndice);
        this.panelNavegar.add(Box.createHorizontalStrut(10));
        this.etiquetaDe.setText("de");
        this.panelNavegar.add(this.etiquetaDe);
        this.panelNavegar.add(Box.createHorizontalStrut(10));
        this.campoTextoMax.setHorizontalAlignment(0);
        this.campoTextoMax.setEditable(false);
        this.panelNavegar.add(this.campoTextoMax);
        this.panelNavegar.add(Box.createHorizontalStrut(10));
        this.botonSiguiente.setText("Siguiente");
        this.botonSiguiente.setEnabled(false);
        this.botonSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                MostrarEquipos.this.botonSiguienteActionPerformed(var1);
            }
        });
        this.panelNavegar.add(this.botonSiguiente);
        this.add(this.panelNavegar);
        this.panelMostrar.setLayout(new GridLayout(7, 2, 4, 4));
        this.etiquetaID.setText("ID Clave:");
        this.panelMostrar.add(this.etiquetaID);
        this.campoTextoID.setEditable(false);
        this.panelMostrar.add(this.campoTextoID);
        this.etiquetaTipoComputadora.setText("Tipo de computadora:");
        this.panelMostrar.add(this.etiquetaTipoComputadora);
        this.panelMostrar.add(this.campoTextoTipoComputadora);
        this.etiquetaMarca.setText("Marca:");
        this.panelMostrar.add(this.etiquetaMarca);
        this.panelMostrar.add(this.campoTextoMarca);
        this.etiquetaModelo.setText("Modelo:");
        this.panelMostrar.add(this.etiquetaModelo);
        this.panelMostrar.add(this.campoTextoModelo);
        this.etiquetaProcesador.setText("Procesador:");
        this.panelMostrar.add(this.etiquetaProcesador);
        this.panelMostrar.add(this.campoTextoProcesador);
        this.etiquetaMemoria.setText("Memoria:");
        this.panelMostrar.add(this.etiquetaMemoria);
        this.panelMostrar.add(this.campoTextoMemoria);
        this.etiquetaAlmacenamiento.setText("Almacenamiento:");
        this.panelMostrar.add(this.etiquetaAlmacenamiento);
        this.panelMostrar.add(this.campoTextoAlmacenamiento);
        this.add(this.panelMostrar);
        this.panelConsulta.setLayout(new BoxLayout(this.panelConsulta, 0));
        this.panelConsulta.setBorder(BorderFactory.createTitledBorder("Buscar una entrada por marca"));
        this.etiquetaConsulta.setText("Marca de la computadora:");
        this.panelConsulta.add(Box.createHorizontalStrut(5));
        this.panelConsulta.add(this.etiquetaConsulta);
        this.panelConsulta.add(Box.createHorizontalStrut(10));
        this.panelConsulta.add(this.campoTextoConsulta);
        this.panelConsulta.add(Box.createHorizontalStrut(10));
        this.botonConsulta.setText("Buscar");
        this.botonConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                MostrarEquipos.this.botonConsultaActionPerformed(var1);
            }
        });
        this.panelConsulta.add(this.botonConsulta);
        this.panelConsulta.add(Box.createHorizontalStrut(5));
        this.add(this.panelConsulta);
        this.botonExplorar.setText("Explorar todas las entradas");
        this.botonExplorar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                MostrarEquipos.this.botonExplorarActionPerformed(var1);
            }
        });
        this.add(this.botonExplorar);
        this.botonInsertar.setText("Insertar nueva entrada");
        this.botonInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                MostrarEquipos.this.botonInsertarActionPerformed(var1);
            }
        });
        this.add(this.botonInsertar);
        this.botonModificar.setText("Modificar entrada actual");
        this.botonModificar.setEnabled(false);
        this.botonModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                MostrarEquipos.this.botonModificarActionPerformed(var1);
            }
        });
        this.add(this.botonModificar);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent var1) {
                JFrame var2 = (JFrame)var1.getSource();
                MostrarEquipos.this.consultasEquipo.close();
                var2.dispose();
            }
        });
        this.setVisible(true);
    }

    private void botonAnteriorActionPerformed(ActionEvent var1) {
        --this.indiceEntradaActual;
        if (this.indiceEntradaActual < 0) {
            this.indiceEntradaActual = this.numeroDeEntradas - 1;
        }

        this.campoTextoIndice.setText("" + (this.indiceEntradaActual + 1));
        this.campoTextoIndiceActionPerformed(var1);
    }

    private void botonSiguienteActionPerformed(ActionEvent var1) {
        ++this.indiceEntradaActual;
        if (this.indiceEntradaActual >= this.numeroDeEntradas) {
            this.indiceEntradaActual = 0;
        }

        this.campoTextoIndice.setText("" + (this.indiceEntradaActual + 1));
        this.campoTextoIndiceActionPerformed(var1);
    }

    private void botonConsultaActionPerformed(ActionEvent var1) {
        this.resultados = this.consultasEquipo.obtenerEquiposPorMarca(this.campoTextoConsulta.getText());
        this.numeroDeEntradas = this.resultados.size();
        if (this.numeroDeEntradas != 0) {
            this.indiceEntradaActual = 0;
            this.entradaActual = (Equipo)this.resultados.get(this.indiceEntradaActual);
            this.campoTextoID.setText("" + this.entradaActual.getIDClave());
            this.campoTextoTipoComputadora.setText(this.entradaActual.getTipoComputadora());
            this.campoTextoMarca.setText(this.entradaActual.getMarca());
            this.campoTextoModelo.setText(this.entradaActual.getModelo());
            this.campoTextoProcesador.setText(this.entradaActual.getProcesador());
            this.campoTextoMemoria.setText(this.entradaActual.getMemoria());
            this.campoTextoAlmacenamiento.setText(this.entradaActual.getAlmacenamiento());
            this.campoTextoMax.setText("" + this.numeroDeEntradas);
            this.campoTextoIndice.setText("" + (this.indiceEntradaActual + 1));
            this.botonSiguiente.setEnabled(true);
            this.botonAnterior.setEnabled(true);
            this.botonModificar.setEnabled(true);
        } else {
            this.botonExplorarActionPerformed(var1);
        }

    }

    private void campoTextoIndiceActionPerformed(ActionEvent var1) {
        this.indiceEntradaActual = Integer.parseInt(this.campoTextoIndice.getText()) - 1;
        if (this.numeroDeEntradas != 0 && this.indiceEntradaActual < this.numeroDeEntradas) {
            this.entradaActual = (Equipo)this.resultados.get(this.indiceEntradaActual);
            this.campoTextoID.setText("" + this.entradaActual.getIDClave());
            this.campoTextoTipoComputadora.setText(this.entradaActual.getTipoComputadora());
            this.campoTextoMarca.setText(this.entradaActual.getMarca());
            this.campoTextoModelo.setText(this.entradaActual.getModelo());
            this.campoTextoProcesador.setText(this.entradaActual.getProcesador());
            this.campoTextoMemoria.setText(this.entradaActual.getMemoria());
            this.campoTextoAlmacenamiento.setText(this.entradaActual.getAlmacenamiento());
            this.campoTextoMax.setText("" + this.numeroDeEntradas);
            this.campoTextoIndice.setText("" + (this.indiceEntradaActual + 1));
        }

    }

    private void botonExplorarActionPerformed(ActionEvent var1) {
        try {
            this.resultados = this.consultasEquipo.obtenerTodosLosEquipos();
            this.numeroDeEntradas = this.resultados.size();
            if (this.numeroDeEntradas != 0) {
                this.indiceEntradaActual = 0;
                this.entradaActual = (Equipo)this.resultados.get(this.indiceEntradaActual);
                this.campoTextoID.setText("" + this.entradaActual.getIDClave());
                this.campoTextoTipoComputadora.setText(this.entradaActual.getTipoComputadora());
                this.campoTextoMarca.setText(this.entradaActual.getMarca());
                this.campoTextoModelo.setText(this.entradaActual.getModelo());
                this.campoTextoProcesador.setText(this.entradaActual.getProcesador());
                this.campoTextoMemoria.setText(this.entradaActual.getMemoria());
                this.campoTextoAlmacenamiento.setText(this.entradaActual.getAlmacenamiento());
                this.campoTextoMax.setText("" + this.numeroDeEntradas);
                this.campoTextoIndice.setText("" + (this.indiceEntradaActual + 1));
                this.botonSiguiente.setEnabled(true);
                this.botonAnterior.setEnabled(true);
                this.botonModificar.setEnabled(true);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private void botonInsertarActionPerformed(ActionEvent var1) {
        int var2 = this.consultasEquipo.agregarEquipo(this.campoTextoTipoComputadora.getText(), this.campoTextoMarca.getText(), this.campoTextoModelo.getText(), this.campoTextoProcesador.getText(), this.campoTextoMemoria.getText(), this.campoTextoAlmacenamiento.getText());
        if (var2 == 1) {
            JOptionPane.showMessageDialog(this, "Se agrego equipo!", "Se agrego equipo", -1);
        } else {
            JOptionPane.showMessageDialog(this, "No se agrego equipo!", "Error", -1);
        }

        this.botonExplorarActionPerformed(var1);
    }

    private void botonModificarActionPerformed(ActionEvent var1) {
        int var2 = this.consultasEquipo.modificarEquipo(this.campoTextoTipoComputadora.getText(), this.campoTextoMarca.getText(), this.campoTextoModelo.getText(), this.campoTextoProcesador.getText(), this.campoTextoMemoria.getText(), this.campoTextoAlmacenamiento.getText(), Integer.parseInt(this.campoTextoID.getText()));
        if (var2 == 1) {
            JOptionPane.showMessageDialog(this, "Se modifico equipo!", "Se modifico equipo", -1);
        } else {
            JOptionPane.showMessageDialog(this, "No se modifico equipo!", "Error", -1);
        }

        this.botonExplorarActionPerformed(var1);
    }
}
