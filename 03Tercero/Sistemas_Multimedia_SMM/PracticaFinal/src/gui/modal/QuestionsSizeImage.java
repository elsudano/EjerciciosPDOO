package gui.modal;

import gui.VentanaInternaListener;
import gui.image.VentanaInternaImagen;
import gui.VentanaPrincipal;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JInternalFrame;

/**
 * This class For contact with me visit https://www.sudano.net or send me a
 * email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 01-may-2016
 */
public class QuestionsSizeImage extends Questions {

    /**
     * Ventana modal que se encarga de hacer diferentes preguntas al usuario.
     *
     * @param parent [in] Este será el padre de la ventana modal
     * @param modal [in] Aquí indicamos el tipo de ventana modal que queremos:
     * si podemos pinchar fuera de ella sin seleccionar nada o por el contrario
     * tenemos que seleccionar algo para cerrar la ventana y continuar.
     */
    public QuestionsSizeImage(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        super.setContentDialog(this.jPanelContent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelContent = new javax.swing.JPanel();
        jPanelSppiners = new javax.swing.JPanel();
        jLabelAncho = new javax.swing.JLabel();
        jSpinnerAncho = new javax.swing.JSpinner();
        jLabelAlto = new javax.swing.JLabel();
        jSpinnerAlto = new javax.swing.JSpinner();
        jPanelTransparencia = new javax.swing.JPanel();
        jCheckBoxTransparencia = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanelContent.setPreferredSize(new java.awt.Dimension(300, 120));
        jPanelContent.setLayout(new javax.swing.BoxLayout(jPanelContent, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelSppiners.setLayout(new java.awt.GridLayout(2, 2));

        jLabelAncho.setLabelFor(jSpinnerAncho);
        jLabelAncho.setText("Ancho");
        jPanelSppiners.add(jLabelAncho);

        jSpinnerAncho.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jSpinnerAnchoMouseWheelMoved(evt);
            }
        });
        jPanelSppiners.add(jSpinnerAncho);

        jLabelAlto.setLabelFor(jSpinnerAlto);
        jLabelAlto.setText("Alto");
        jPanelSppiners.add(jLabelAlto);

        jSpinnerAlto.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jSpinnerAltoMouseWheelMoved(evt);
            }
        });
        jPanelSppiners.add(jSpinnerAlto);

        jPanelContent.add(jPanelSppiners);

        jPanelTransparencia.setPreferredSize(new java.awt.Dimension(200, 100));
        jPanelTransparencia.setLayout(new java.awt.BorderLayout());

        jCheckBoxTransparencia.setText("Transparencia");
        jCheckBoxTransparencia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 15));
        jCheckBoxTransparencia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanelTransparencia.add(jCheckBoxTransparencia, java.awt.BorderLayout.EAST);

        jPanelContent.add(jPanelTransparencia);

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSpinnerAnchoMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jSpinnerAnchoMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            if ((int) this.jSpinnerAncho.getValue() < this.MAX_PIXEL_IMAGE) {
                this.jSpinnerAncho.setValue((int) this.jSpinnerAncho.getValue() + 7);
            }
        } else if ((int) this.jSpinnerAncho.getValue() > 0) {
            this.jSpinnerAncho.setValue((int) this.jSpinnerAncho.getValue() - 7);
        }
    }//GEN-LAST:event_jSpinnerAnchoMouseWheelMoved

    private void jSpinnerAltoMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jSpinnerAltoMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            if ((int) this.jSpinnerAlto.getValue() < this.MAX_PIXEL_IMAGE) {
                this.jSpinnerAlto.setValue((int) this.jSpinnerAlto.getValue() + 7);
            }
        } else if ((int) this.jSpinnerAlto.getValue() > 0) {
            this.jSpinnerAlto.setValue((int) this.jSpinnerAlto.getValue() - 7);
        }
    }//GEN-LAST:event_jSpinnerAltoMouseWheelMoved

    @Override
    public void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {
        JInternalFrame ultima_ventana = this.myParent.getEscritorio().getSelectedFrame();
        // dimensión de la imagen
        Dimension dim;
        // ancho
        int w = (int) this.jSpinnerAncho.getValue();
        // alto
        int h = (int) this.jSpinnerAlto.getValue();

        // tamaño de la imagen que queremos crear
        // @TODO aqui tenemos que controlar si el usuario pone un dato incorrecto
        // intentar lanzar una exception.
        if (w > 0 && h > 0) {
            dim = new Dimension(w, h);
        } else {
            dim = new Dimension(250, 250);
        }
        // si el usuario quiere transparencia
        VentanaInternaImagen vi;
        if (this.jCheckBoxTransparencia.isSelected()){
            vi = new VentanaInternaImagen(this.myParent, dim, BufferedImage.TYPE_INT_ARGB);
        }else{
            vi = new VentanaInternaImagen(this.myParent, dim, BufferedImage.TYPE_INT_RGB);
        }
        vi.setTitle("(Sin Título " + VentanaPrincipal.COUNT_VI + ")");
        if (ultima_ventana != null) {
            vi.setLocation(ultima_ventana.getLocation().x + 15, ultima_ventana.getLocation().y + 15);
        } else {
            vi.setLocation(15, 15);
        }
        vi.setListener(new VentanaInternaListener(myParent));
        this.myParent.getEscritorio().add(vi);
        vi.setVisible(true);
        this.dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxTransparencia;
    private javax.swing.JLabel jLabelAlto;
    private javax.swing.JLabel jLabelAncho;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelSppiners;
    private javax.swing.JPanel jPanelTransparencia;
    private javax.swing.JSpinner jSpinnerAlto;
    private javax.swing.JSpinner jSpinnerAncho;
    // End of variables declaration//GEN-END:variables

}
