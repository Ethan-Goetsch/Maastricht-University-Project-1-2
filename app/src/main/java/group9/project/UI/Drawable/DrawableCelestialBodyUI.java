package group9.project.UI.Drawable;

import com.jme3.scene.Spatial;
import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.UI.GUI;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Math.Vector3;

public class DrawableCelestialBodyUI extends DrawableUI
{
    private double planetRadius;

    private String labelText;

    private Spatial spatial;

    private CelestialBodyObject celestialBody;

    public DrawableCelestialBodyUI(float newPlanetRadius, String newLabelText, Spatial spatial, CelestialBodyObject celestialBody)
    {
        super();

        this.celestialBody = celestialBody;

        spatial.setLocalScale((float)newPlanetRadius, (float)newPlanetRadius, (float)newPlanetRadius);
        this.spatial = spatial;

        planetRadius = newPlanetRadius;

        labelText = newLabelText;
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

}