package group9.project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * JavaFX App
 */
public class MissionControl extends Application
{
    private static Stage mainStage;

    private static Scene mainScene;

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
    public void start(Stage stage) throws IOException
    {
        mainStage = stage;

        mainScene = new Scene(PhysicsVisualizer.getInstance().getView());


        createPhysicsSystems();

        createTimeline();

        /* 
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
        */
        
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



        mainStage.setTitle("Titanic Space Odyssey");

        mainStage.setScene(mainScene);
        
        mainStage.show();
    }

    private void createPhysicsSystems()
    {
        PhysicsEngine.getInstance().start();

        PhysicsVisualizer.getInstance().start();
    }

    private void createTimeline()
    {
        Timeline loopTimeline = new Timeline(new KeyFrame(Duration.seconds(PhysicsEngine.STEP_TIME), x -> updateLoop()));

        loopTimeline.setCycleCount(Animation.INDEFINITE);

        loopTimeline.play();
    }

    private void updateLoop()
    {
        PhysicsEngine.getInstance().update();

        PhysicsVisualizer.getInstance().update();
    }
}