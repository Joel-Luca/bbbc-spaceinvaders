package bbcspaceinvaders.gameobjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Alienship extends GameObject {

    private double speed = 200;
    private Direction direction;

    private final Random random = new Random();

    public Alienship(Image image, double x, double y) {
        super(image, x, y);
    }

    public void update(double delta) {
        if(direction == Direction.LEFT) {
            this.setX(this.getX() - speed * delta);

            if(this.getX() < 100) {
                direction = Direction.RIGHT;
            }
        }
        else if(direction == Direction.RIGHT) {
            this.setX(this.getX() + speed * delta);

            if(this.getX() > 700) {
                direction = Direction.LEFT;
            }
        }
        else {
            if(random.nextInt(2) == 1) {
                direction = Direction.RIGHT;
            }
            else {
                direction = Direction.LEFT;
            }
        }
    }
}
