package group9.project;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class MissionControl extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage arg0) throws Exception
    {
        createUI();

        createTimer();
    }

    private static void createUI()
    {
        
    }

    private static void createTimer()
    {
        AnimationTimer updateLoopController = new LoopController();

        updateLoopController.start();
    }
}