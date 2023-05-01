package group9.project.Trajectory_Guidance;

import group9.project.Utility.Math.Vector3;

public abstract class TrajectoryDirection
{
    public abstract Vector3 calculateDirectionToMove(Vector3 position, Vector3 targetPosition);
}