package group9.project;

import javafx.animation.AnimationTimer;

public class LoopController extends AnimationTimer
{
    @Override
    public void handle(long arg0)
    {
        PhysicsEngine.updatePhysicsObjects();
    }
}