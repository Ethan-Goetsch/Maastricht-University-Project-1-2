package group9.project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Calendar;

/**
 * JavaFX App
 */
public class MissionControl extends Application
{
    private static Stage mainStage;

    private static Scene mainScene;

    private static RocketLaunchGeneticAlgorithm GA;


    private Timeline loopTimeline;

    public static void main(String[] args)
    {
        GA = new RocketLaunchGeneticAlgorithm(1, 50);
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        createPhysicsSystems();
        

        mainStage = stage;

        mainScene = new Scene(PhysicsVisualizer.getInstance().getView());


        createTimeline();


        mainStage.setTitle("Titanic Space Odyssey");

        mainStage.setScene(mainScene);

        mainStage.setMaximized(true);
        
        mainStage.show();
    }

    private void createPhysicsSystems()
    {
        PhysicsObjectData.getInstance().start();

        PhysicsEngine.getInstance().start();

        PhysicsVisualizer.getInstance().start();
    }

    private void createTimeline()
    {
        loopTimeline = new Timeline(new KeyFrame(Duration.seconds(SimulationSettings.UNIVERSE_TICK_TIME), x -> updateLoop()));

        loopTimeline.setCycleCount(Animation.INDEFINITE);

        loopTimeline.play();
    }

    private void updateLoop()
    {
        PhysicsVisualizer.getInstance().update();

        PhysicsEngine.getInstance().update();


        Calendar calender = Calendar.getInstance();

        calender.setTime(DateView.startDate);
        calender.add(Calendar.SECOND, (int)(3154e+4+(1577e+4/4)));

        // stop after 1 year
        if (DateView.currentDate.compareTo(calender.getTime()) > 0)
        {
            System.out.println("DISTANCE OF ACTUAL ROCKET TO TITAN: " + PhysicsObjectData.getInstance().rocketShipObject.getClosestDistance());
            System.out.println(PhysicsObjectData.getInstance().rocketShipObject.closestDate);
            SimulationSettings.simulationTime = 0;
            PhysicsEngine.getInstance().getPhysicsObjectsToUpdate().clear();
            DateView.getInstance().reset();

            PhysicsObjectData.getInstance().start();

            PhysicsEngine.getInstance().start();


            for (PhysicsObject object : PhysicsEngine.getInstance().getPhysicsObjectsToUpdate()) {
                object.reset();
            } 
            GA.nextGeneration();
            System.out.println("NEW BEST ROCKET:");
            System.out.println("distance: " + GA.getBestRocket().getClosestDistance());
            System.out.println("\tx: " + GA.getBestRocket().getClosestX());
            System.out.println("\ty: " + GA.getBestRocket().getClosestY());
            System.out.println("\tz: " + GA.getBestRocket().getClosestZ());
            System.out.println(GA.getBestRocket().getStartingVelocity());
        }
    }
}