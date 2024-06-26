package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.Set;
import model.Fractal;

/**
 *
 * @author tadaki
 */
public class DrawPanel extends javax.swing.JPanel {

    private Fractal sys;
    private BufferedImage image = null;
    private final Dimension dimension;
    private final double offset = .05;

    /**
     * Creates new form DrawPanel
     */
    public DrawPanel() {
        initComponents();
        dimension = getPreferredSize();
        initImage();
    }

    private void initImage() {
        image = new BufferedImage(dimension.width, dimension.height,
                BufferedImage.TYPE_INT_ARGB);
    }

    private void createImage() {
        if (image == null) {
            initImage();
        }
        Color bg = getBackground();
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(bg);
        g.fillRect(0, 0, dimension.width, dimension.height);
        g.setColor(getForeground());
    }

    public void draw(Set<Shape> shapes) {
        draw(shapes, false);
    }

    public void draw(Set<Shape> shapes, boolean drawFlag) {
        createImage();
        Graphics2D g = (Graphics2D) image.getGraphics();

        g.translate(offset * dimension.width, (1 - offset) * dimension.height);
        g.scale((1 - 2 * offset) * dimension.width,
                -(1 - 2 * offset) * dimension.height);
        g.setStroke(new BasicStroke(.005f));

        if (drawFlag) {
            g.setColor(Color.BLACK);
            for (Shape s : shapes) {
                g.draw(s);
            }
        } else {
            g.setColor(Color.BLUE);
            for (Shape s : shapes) {
                g.fill(s);
            }
        }
        repaint();
    }

    public void setSys(Fractal sys) {
        this.sys = sys;
    }

    public void initialize() {
        sys.initialize();
        createImage();
        repaint();
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if (image == null) {
            return;
        }
        //show image
        g.drawImage(image, 0, 0, image.getWidth(),
                image.getHeight(), this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 800));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
