/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Duskmourne
 */
public class VHolderPanel extends javax.swing.JPanel {

    public Image image = null;
    private int iWidth2;
    private int iHeight2;

    public VHolderPanel(Image image) {

        setLayout(new CardLayout());
        setOpaque(false);
        java.awt.Dimension dim = new java.awt.Dimension(100, 100);
        setMinimumSize(dim);
        setSize(dim);
        
        //this.iWidth2 = image.getWidth(this) / 2;
        //this.iHeight2 = image.getHeight(this) / 2;
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                java.awt.Dimension cdim = getSize();
                setSize(cdim);
                resetImageSize(cdim);
                repaint();
            }
        });
        
        this.image = image;
    }
    
    public VHolderPanel() {
        
        setLayout(new CardLayout());
        setOpaque(false);
        java.awt.Dimension dim = new java.awt.Dimension(100, 100);
        setMinimumSize(dim);
        setSize(dim);
        
        //this.iWidth2 = image.getWidth(this) / 2;
        //this.iHeight2 = image.getHeight(this) / 2;
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                java.awt.Dimension cdim = getSize();
                setSize(cdim);
            }
        });

    }

    public void setImage(Image img) {
        this.image = img;
    }
    
    public void resetImageSize(java.awt.Dimension cdim){
        this.image = image.getScaledInstance(cdim.width, cdim.height, Image.SCALE_SMOOTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int x = 0;
            int y = 0;
            g.drawImage(image, x, y, this);
        }
    }

    public void showPane(JPanel Pan) {

        if (!Pan.isVisible()) {
            Component comps[] = this.getComponents();
            for (int i = 0; i < comps.length; i++) {
                comps[i].setVisible(false);
            }
            Pan.setVisible(true);
        }
    }
    
    public void showPane(VHolderPanel Pan) {

        if (!Pan.isVisible()) {
            Component comps[] = this.getComponents();
            for (int i = 0; i < comps.length; i++) {
                comps[i].setVisible(false);
            }
            Pan.setVisible(true);
        }
    }

    public void showPane(JScrollPane Pan) {
        if (!Pan.isVisible()) {
            Component comps[] = this.getComponents();
            for (int i = 0; i < comps.length; i++) {
                comps[i].setVisible(false);
            }
            Pan.setVisible(true);
        }
    }

    public void removePane(JPanel Pan) {
        try {
            this.remove(Pan);
            this.updateUI();
        } catch (Exception er) {
        }
    }

    public void addPane(JPanel Pan) {
        try {
            this.add(Pan);
            this.updateUI();
        } catch (Exception er) {
        }
    }

    public void addPane(JScrollPane Pan) {
        try {
            this.add(Pan);
            this.updateUI();
        } catch (Exception er) {
        }
    }

}
