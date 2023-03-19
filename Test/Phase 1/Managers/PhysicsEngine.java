package Managers;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import Objects.*;
import UI.UIManager;
import Utility.Vector2;

public class PhysicsEngine
{
    public static final double STEP_TIME = 0.0001;

    public static void staticStart()
    {
        startManagers();

        startPhysicsObjects();

        startTimer();
    }

    private static void startManagers()
    {
        PhysicsController.getInstance().start();

        PhysicsVisualizer.getInstance().start();
    }

    private static void startPhysicsObjects()
    {
        RocketshipObject test = new RocketshipObject(new Vector2(), new Vector2(500, 0));

        PlanetObject planet = new PlanetObject(new Vector2(450, 300), new Vector2(2500, 1000));

        UIManager.addComponent(test.getPhysicsComponent());

        UIManager.addComponent(planet.getPhysicsComponent());
    }

    private static void startTimer()
    {
        Timer updateTimer = new Timer();
        
        TimerTask updateTimerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                PhysicsController.getInstance().fixedUpdate();

                PhysicsVisualizer.getInstance().fixedUpdate();
            }    
        };

        updateTimer.schedule((updateTimerTask), 0, 1);
    }
}