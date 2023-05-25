package group9.project.Utility.Interfaces;

import group9.project.Utility.Math.Vector3;

public interface ITargetable extends IDescribable
{
    public Vector3 getPosition();

    public String getDescription();
}