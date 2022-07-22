public class Boid {

    Vector2D position, velocity, acceleration;

    Boid(Vector2D position, Vector2D velocity, Vector2D acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    Vector2D seperation(Boid[] boids, double force, int view_distance) {
        Vector2D result = new Vector2D();
        int boids_in_view = 0;

        for (Boid other_boid : boids) {
            if (other_boid != this && this.vector_to_other(other_boid).length() < view_distance) {
                boids_in_view++;
                Vector2D v = new Vector2D();
                v = this.position.sub(other_boid.position);
                if (v.length() != 0) {
                    v = v.div(v.length());
                } else {
                    v = new Vector2D();
                }

                result = result.add(v);
            }
        }
        if (boids_in_view > 0) {
            result = result.div(boids_in_view);
        }
        result = result.sub(this.velocity);
        result = result.normalize().mult(force);
        
        return result;
    }

    Vector2D alignment(Boid[] boids, double force, int view_distance) {
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
        result = result.normalize().mult(force);

        return result;
    }

    Vector2D cohesion(Boid[] boids, double force, int view_distance) {
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
        result = result.sub(this.velocity);
        result = result.normalize().mult(force);

        return result;
    }

    void flock(Boid[] boids){
        Vector2D seperation = this.seperation(boids, 0.4, 50);
        Vector2D alignment = this.alignment(boids, 0.1, 100);
        Vector2D cohesion = this.cohesion(boids, 0.2, 100);
        
        this.acceleration = Vector2D.add(seperation, alignment, cohesion);
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
