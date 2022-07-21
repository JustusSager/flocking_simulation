public class Boid {

    Vector2D position, velocity, acceleration;

    Boid(Vector2D position, Vector2D velocity, Vector2D acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    void update() {
        position = position.add(velocity);
        velocity = velocity.add(acceleration);
    }

    Vector2D vector_to_other(Boid other) {
        return new Vector2D(other.position.add(this.position.mult(-1)));
    }

}
