import javax.swing.JFrame;

class Main {
    public static void main (String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Flocking Simulation");

        window.add(new MyPanel());
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        

        QuadTree quadtree = new QuadTree(0, 0, 800, 800, 4);

        for(int i = 0; i < 20; i++) {
            quadtree.insert(Math.random() * 800, Math.random() * 800, null);
        }

        System.out.println("End");
    }
}