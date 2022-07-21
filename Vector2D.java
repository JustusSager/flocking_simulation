public class Vector2D {
    double x, y;

    Vector2D(){
        this.x = 0;
        this.y = 0;
    }
    Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    Vector2D(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    static Vector2D random(double min, double max) {
        return new Vector2D(Math.random() * max * 2 - min, Math.random() * max * 2 - min);
    }

    Vector2D normalize() {
        return new Vector2D(this.div(this.length()));
    }

    double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    static Vector2D add(Vector2D... others) {
        Vector2D result = new Vector2D();
        for (Vector2D other : others) {
            result.x = result.x + other.x;
            result.y = result.y + other.y;
        }
        return result;
    }

    static Vector2D sub(Vector2D... others) {
        if (others.length < 2) {
            return others[0];
        }
        Vector2D result = new Vector2D();
        for (Vector2D other : others) {
            result.x = result.x - other.x;
            result.y = result.y - other.y;
        }
        return result;
    }

    Vector2D mult(double a) {
        return new Vector2D(this.x * a, this.y * a);
    }

    Vector2D div(double a) {
        return new Vector2D(this.x / a, this.y / a);
    }

    @Override
    public String toString() {
        return String.format("x: %f, y: %f", this.x, this.y);
    }
}
