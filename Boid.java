public class Boid {

    Vector2D position, velocity, acceleration;
    final int view_distance = 50;

    Boid(Vector2D position, Vector2D velocity, Vector2D acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    Vector2D seperation(Boid[] boids) {
        Vector2D result = new Vector2D();

        return result;
    }

    Vector2D alignment(Boid[] boids) {
        Vector2D result = new Vector2D();
        int boids_in_view = 0;
        for (Boid other_boid : boids) {
            if (other_boid != this && this.vector_to_other(other_boid).length() < view_distance) {
                boids_in_view++;
                result = result.add(other_boid.velocity);
            }
        }
        if (boids_in_view > 0) {
            result = result.div(boids_in_view);
        }
        result = result.sub(this.velocity);
        return result;
    }

    Vector2D cohesion(Boid[] boids) {
        Vector2D result = new Vector2D();
        int boids_in_view = 0;
        for (Boid other_boid : boids) {
            if (other_boid != this && this.vector_to_other(other_boid).length() < view_distance) {
                boids_in_view++;
                result = result.add(other_boid.position);
            }
        }
        if (boids_in_view > 0) {
            result = result.div(boids_in_view);
        }
        result = result.sub(this.position);
        return result;
    }

    void flock(Boid[] boids){
        Vector2D seperation = this.seperation(boids);
        Vector2D alignment = this.alignment(boids);
        Vector2D cohesion = this.cohesion(boids);
        
        this.acceleration = seperation.add(alignment.add(cohesion));
    }

    void update() {
        this.position = this.position.add(this.velocity);
        this.velocity = this.velocity.add(this.acceleration);
        this.velocity = this.velocity.normalize();
    }

    Vector2D vector_to_other(Boid other) {
        return other.position.sub(this.position);
    }

    @Override
    public String toString() {
        return String.format("Pos: %s, Vel: %s, Acc: %s", this.position, this.velocity, this.acceleration);
    }
}
