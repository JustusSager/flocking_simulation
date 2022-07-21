public class Boid {

    Vector2D position, velocity, acceleration;
    final int view_distance = 100;

    Boid(Vector2D position, Vector2D velocity, Vector2D acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    void seperation(Boid[] boids) {
        
    }

    Vector2D alignment(Boid[] boids) {
        Vector2D average = new Vector2D();
        int boids_in_view = 0;
        for (Boid other_boid : boids) {
            if (other_boid != this && this.vector_to_other(other_boid).length() < view_distance) {
                boids_in_view++;
                average = Vector2D.add(average, other_boid.velocity);
            }
        }
        average = average.div(boids_in_view);
        return average;
    }

    void cohesion(Boid[] boids) {
        
    }

    void update() {
        position = Vector2D.add(position, velocity);
        velocity = Vector2D.add(velocity, acceleration);
    }

    Vector2D vector_to_other(Boid other) {
        return Vector2D.sub(other.position, this.position);
    }

    @Override
    public String toString() {
        return String.format("Pos: %s, Vel: %s, Acc: %s", this.position, this.velocity, this.acceleration);
    }
}
