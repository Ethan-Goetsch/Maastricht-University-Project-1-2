package group9.project.UI.Drawable;

import com.jme3.scene.Spatial;
import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Math.Vector3;

public class DrawableCelestialBodyUI extends DrawableUI
{
    private float planetRadius;

    private CelestialBodyObject celestialBody;

    public DrawableCelestialBodyUI(String name, Spatial spatial, CelestialBodyObject celestialBody)
    {
        super(name, spatial);

        this.celestialBody = celestialBody;
        
        planetRadius = (float) ScaleConverter.worldToScreenLength(celestialBody.getRadius());

        spatial.setLocalScale(planetRadius, planetRadius, planetRadius);

    }

    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(celestialBody.getPosition());
        
        spatial.setLocalTranslation((float)scaledVector.getX(), (float)scaledVector.getY(), (float)scaledVector.getZ());
    }

    @Override
    public float getPreferredViewDistance()
    {
        return (float)(4*planetRadius);
    }
    
    @Override
    public DrawableCelestialBodyUI clone()
    {
        return new DrawableCelestialBodyUI(name, spatial.scale(planetRadius), celestialBody);
    }

}