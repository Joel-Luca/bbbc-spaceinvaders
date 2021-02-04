package bbcspaceinvaders.gameobjects;

import javafx.scene.image.Image;

public class Laser extends GameObject {

    private double speed = 500;

    public Laser(Image image, double x, double y) {
        super(image, x, y);
    }

    public void update(double delta) {
        this.setY(getY() - speed * delta);
    }
}
