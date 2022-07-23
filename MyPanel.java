import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MyPanel extends JPanel implements ActionListener{

    final int PANEL_WIDTH = 1000;
    final int PANEL_HEIGHT = 700;

    Timer timer;
    
    Boid[] boids = new Boid[100];
    
    MyPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.DARK_GRAY);

        /* Boids erstellen */
        for (int i = 0; i < boids.length; i++) {
            boids[i] = new Boid(Vector2D.random(0, 500), Vector2D.random(2, 2), new Vector2D());
            // boids[i] = new Boid(new Vector2D(PANEL_WIDTH/2, PANEL_HEIGHT/2), Vector2D.random(2, 2), new Vector2D());
            boids[i].velocity = boids[i].velocity.normalize();
        }

        timer = new Timer(10, this);
        timer.start();


    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paint(g);

        /* Boid zeichnen */
        for (Boid boid : boids) {
            g2d.setColor(Color.WHITE);
            g2d.fillOval((int) boid.position.x - 5, (int) boid.position.y - 5, 10, 10);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /* Flocking Algorithmus ausführen */
        for (Boid boid : boids) {
            boid.flock(boids);
            // System.out.println(boid);
        }
        
        /* Positionen der Boids aktualisieren */
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