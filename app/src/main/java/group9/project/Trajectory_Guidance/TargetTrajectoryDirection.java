package group9.project.Trajectory_Guidance;

import group9.project.Utility.Math.Vector3;

public class TargetTrajectoryDirection extends TrajectoryDirection
{
    @Override
    public Vector3 calculateDirectionToMove(Vector3 position, Vector3 targetPosition)
    {
        return targetPosition.subtract(position).normalize();
    }
}