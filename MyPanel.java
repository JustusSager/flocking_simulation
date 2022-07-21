import javax.swing.*;
import javax.swing.border.StrokeBorder;

import java.awt.*;
import java.awt.event.*;

public class MyPanel extends JPanel implements ActionListener{

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;

    Timer timer;
    
    Boid[] boids = new Boid[200];
    
    MyPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.DARK_GRAY);

        for (int i = 0; i < boids.length; i++) {
            boids[i] = new Boid(Vector2D.random(0, 500), Vector2D.random(2, 2), new Vector2D());
            boids[i].velocity = boids[i].velocity.normalize();
        }

        timer = new Timer(10, this);
        timer.start();


    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paint(g);

        for (Boid boid : boids) {
            g2d.setColor(Color.WHITE);
            g2d.fillOval((int) boid.position.x - 10, (int) boid.position.y - 10, 20, 20);
            g2d.setColor(Color.BLACK);
            g2d.drawOval((int) boid.position.x - 10, (int) boid.position.y - 10, 20, 20);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Boid boid : boids) {

            // Vector2D average_heading = boid.alignment(boids);
            

            // boid.velocity = average_heading;
            
        }
        
        for (Boid boid : boids) {
            boid.update();
    
            if (boid.position.x < 0) {
                boid.position.x = PANEL_WIDTH;
            } else if (boid.position.x > PANEL_WIDTH) {
                boid.position.x = 0;
            }
            if (boid.position.y < 0) {
                boid.position.y = PANEL_HEIGHT;
            } else if (boid.position.y > PANEL_HEIGHT) {
                boid.position.y = 0;
            }
        }
        repaint();
    }
}