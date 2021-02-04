package bbcspaceinvaders.gameobjects;

import javafx.scene.image.Image;

public class Spaceship extends GameObject {

    private double speed = 300;
    private double width = 58;

    public Spaceship(Image image, double x, double y) {
        super(image, x, y);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }
}
