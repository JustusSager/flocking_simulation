import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPanel extends JPanel implements ActionListener{

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;

    Timer timer;
    
    Boid[] boids = new Boid[200];
    
    MyPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.WHITE);

        for (int i = 0; i < boids.length; i++) {
            boids[i] = new Boid(new Vector2D(PANEL_WIDTH / 2, PANEL_HEIGHT / 2), Vector2D.random(2, 2), new Vector2D());
            boids[i].velocity = boids[i].velocity.normalize();
        }

        timer = new Timer(10, this);
        timer.start();


    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paint(g);

        for (Boid boid : boids) {
            g2d.drawOval((int) boid.position.x - 10, (int) boid.position.y - 10, 20, 20);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // for (Boid boid : boids) {
        //     for (Boid other_boid : boids) {
        //         if (boid == other_boid) {
        //             continue;
        //         }
        //         if (boid.vector_to_other(other_boid).length() < 200) {
        //             boid.velocity = boid.velocity.add(other_boid.velocity).normalize();
        //         }
                
        //     }
        // }
        
        for (Boid boid : boids) {
            boid.update();
    
            if (boid.position.x < 0) {
                boid.velocity.x *= -1;
            } else if (boid.position.x > PANEL_WIDTH) {
                boid.velocity.x *= -1;
            }
            if (boid.position.y < 0) {
                boid.velocity.y *= -1;
            } else if (boid.position.y > PANEL_HEIGHT) {
                boid.velocity.y *= -1;
            }
        }
        repaint();
    }
}