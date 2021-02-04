package bbcspaceinvaders.gameobjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {

    private Image image;
    private double x;
    private double y;

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }

    public GameObject(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }
}
