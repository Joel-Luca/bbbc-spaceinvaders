package bbcspaceinvaders;

import bbcspaceinvaders.gameobjects.Alienship;
import bbcspaceinvaders.gameobjects.GameObject;
import bbcspaceinvaders.gameobjects.Laser;
import bbcspaceinvaders.gameobjects.Spaceship;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;
import java.util.ArrayList;
import java.util.List;

public class BbcSpaceInvadersApp extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private Spaceship spaceship = new Spaceship(new Image(this.getClass().getResourceAsStream("/spaceship.png")),150, 400);
    private GameObject background = new GameObject(new Image(this.getClass().getResourceAsStream("/background_game.png")), 0, 0);

    private List<Alienship> alienFleet = new ArrayList<Alienship>();
    private List<Laser> laserShots = new ArrayList<Laser>();

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isSpaceKeyPressed;

    private long lastTime;
    private long lastShot;

    private double canvaswidth = 800;
    private double canvasheight = 600;

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        canvas = new Canvas(canvaswidth, canvasheight);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();

        int anzahl = 5;
        double pos = 0;
        for(int i = 1; i <= anzahl; i++) {
            pos = canvaswidth / anzahl * i;
            alienFleet.add(new Alienship(new Image(getClass().getResourceAsStream("/alienship.png")), pos, 40));
        }

        stage.setTitle("Bbc SpaceInvaders");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        lastShot = System.nanoTime();
        lastTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {

                double delta = (currentTime - lastTime) / 1e9;
                lastTime = currentTime;

                scene.setOnKeyPressed((e) -> onKeyPressed(e));
                scene.setOnKeyReleased((e) -> onKeyReleased(e));

                update(delta);
                paint();
            }
        }.start();
    }

    public void update(double delta) {
        if(isRightKeyPressed && spaceship.getX() < canvaswidth - spaceship.getWidth()) {
            spaceship.setX(spaceship.getX() + spaceship.getSpeed() * delta);
        }
        if(isLeftKeyPressed && spaceship.getX() > 0) {
            spaceship.setX(spaceship.getX() - spaceship.getSpeed() * delta);
        }
        if(isSpaceKeyPressed) {
            long currentTime = System.nanoTime();
            if(lastShot + 1e9 / 2 < currentTime) {
                laserShots.add(new Laser(new Image(getClass().getResourceAsStream("/projectile.png")),spaceship.getX() + 28, spaceship.getY()));
                lastShot = currentTime;
            }

        }

        for(Laser laser : laserShots) {
            laser.update(delta);
        }
        for(Alienship alienship : alienFleet) {
            alienship.update(delta);
        }
    }

    public void paint() {
        background.draw(gc);
        spaceship.draw(gc);

        for(Alienship alienship : alienFleet) {
            alienship.draw(gc);
        }
        for(Laser laser : laserShots) {
            laser.draw(gc);
        }
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case A:
            case LEFT:
                isLeftKeyPressed = true;
                break;
            case D:
            case RIGHT:
                isRightKeyPressed = true;
                break;
            case SPACE:
                isSpaceKeyPressed = true;

        }
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case A:
            case LEFT:
                isLeftKeyPressed = false;
                break;
            case D:
            case RIGHT:
                isRightKeyPressed = false;
                break;
            case SPACE:
                isSpaceKeyPressed = false;
        }
    }
}

