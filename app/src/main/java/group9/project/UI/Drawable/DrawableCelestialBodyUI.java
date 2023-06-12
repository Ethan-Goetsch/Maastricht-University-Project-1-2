package group9.project.UI.Drawable;

import com.jme3.scene.Spatial;
import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Math.Vector3;

public class DrawableCelestialBodyUI extends DrawableUI
{
    private float planetRadius;

    //private CelestialBodyObject physicsObject;

    /**
     * Constructor.
     * @param name the name of the drawable, used for lookup purposes
     * @param spatial the spatial to attach to the scene graph
     * @param celestialBody the celestial body which this drawable mirrors (movement of the celestial body will be reflected by movement of the spatial)
     */
    public DrawableCelestialBodyUI(String name, Spatial spatial, CelestialBodyObject celestialBody)
    {
        super(name, spatial, celestialBody);
        
        planetRadius = (float) ScaleConverter.worldToScreenLength(celestialBody.getRadius()); // have to convert the real world size of the celestial body to screen size

        spatial.setLocalScale(planetRadius, planetRadius, planetRadius); // scaling the spatial like this might result in a planet size which isn't exactly of real-world scale, but its good enough (for now)
    }

    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(physicsObject.getPosition());
        
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
        return new DrawableCelestialBodyUI(name, spatial.scale(planetRadius), (CelestialBodyObject)physicsObject);
    }
}