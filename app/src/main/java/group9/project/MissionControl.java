package group9.project;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * JavaFX App
 */
public class MissionControl extends Application
{
    private static Stage stage;

    private static Scene scene;

    /* 
    private static final double WIDTH = 500;
    private static final double HEIGHT = 500;
    private static final double DEPTH = 500;
    */

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage newStage) throws IOException
    {
        stage = newStage;

        createUI();

        createTimer();

        CelestialBodyObject sun = new CelestialBodyObject("Sun");
        sun.setRadius(30);
        sun.setPosition(new Vector3(0,0,0));
        sun.setMass(1.99e30);
        sun.start();

        CelestialBodyObject saturn = new CelestialBodyObject("Saturn");
        saturn.setRadius(10);
        saturn.setPosition(Converter.scaleToScreen(new Vector3(-1.25e9, -7.6e8, -3.67e7)));
        saturn.setMass(5.68e26);
        saturn.start();
        
/* 
        CelestialBodyObject testStar1 = new CelestialBodyObject("test star 1");
        testStar1.setRadius(30);
        testStar1.setPosition(new Vector3(250,250,250));
        testStar1.setMass(10000);
        testStar1.start();

        CelestialBodyObject testStar2 = new CelestialBodyObject("test star 2");
        testStar2.setRadius(10);
        testStar2.setPosition(new Vector3(100,100,250));
        testStar2.setMass(2);
        testStar2.start();
*/

    }

    private static void createUI()
    {
        Pane testPane = createLabelPane();

        Scene scene = new Scene(testPane);

        stage.setScene(scene);
        
        stage.show();
    }

    private static Pane createLabelPane()
    {
        Pane pane  = new Pane();

        pane.getChildren().add(new Label("Hello Pane"));

        return pane;
    }

    private static Pane createVisualPane()
    {
        return null;
    }

    private static void createTimer()
    {
        Timeline loopTimeline = new Timeline(new KeyFrame(Duration.seconds(PhysicsEngine.STEP_TIME), x -> updateLoop()));

        loopTimeline.setCycleCount(Animation.INDEFINITE);

        loopTimeline.play();
    }

    private static void updateLoop()
    {
        PhysicsEngine.updatePhysicsObjects();
    }
}