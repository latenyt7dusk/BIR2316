/*
 * Copyright (C) 2015 HERU
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package utility;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author HERU
 */
public class VButton extends JButton {

    private boolean visi = true;
    private boolean hov = false;
    private boolean overpaint = true;
    private Graphics2D g2d;
    private GradientPaint gp;

    public VButton(String n) {
        super(n);
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFocusable(false);
        setForeground(Color.BLACK);
        setBackground(new Color(0,204,102));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMargin(new Insets(1, 3, 1, 3));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hov = true;
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                hov = false;
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                hov = false;
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hov = true;
            }
        });

    }
    
    public VButton() {
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFocusable(false);
        setForeground(Color.BLACK);
        setBackground(new Color(0,204,102));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMargin(new Insets(1, 3, 1, 3));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hov = true;
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                hov = false;
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                hov = false;
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hov = true;
            }
        });

    }
    
    public void setOverpaint(boolean b){
        this.overpaint = b;
    }
    public boolean isOverpaint(){
        return overpaint;
    }

    
    public void setButtonVisible(boolean b) {
        this.visi = b;
    }

    public boolean isButtonVisible() {
        return visi;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        if (visi) {
            g2d.setColor(this.getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
            if (hov) {
                gp = new GradientPaint(getWidth(), 0, this.getBackground().brighter(), getWidth(), 27, this.getBackground(), true);
                g2d.setPaint(gp);
                //g2d.setStroke(new BasicStroke(3,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        }else{
            if (hov) {
                gp = new GradientPaint(getWidth(), 0, this.getBackground().brighter(), getWidth(), 27, this.getBackground(), true);
                g2d.setPaint(gp);
                //g2d.setStroke(new BasicStroke(3,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        }
        super.paintComponent(g);
        g2d.dispose();
    }

}
