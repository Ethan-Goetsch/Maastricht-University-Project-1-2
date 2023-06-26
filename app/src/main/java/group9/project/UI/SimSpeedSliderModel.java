package group9.project.UI;

import com.simsilica.lemur.DefaultRangedValueModel;
import group9.project.MissionControl;


public class SimSpeedSliderModel extends DefaultRangedValueModel
{
    /**
     * Constructor.
     * @param min the minimum slider value
     * @param max the maximum slider value
     * @param value the default slider value
     */
    public SimSpeedSliderModel(double min, double max, double value)
    {
        super(min, max, value);
    }
    
    /**
     * Sets the value of the slider and changes the application's wait time between each frame to {@code this.getMaximum() - this.getValue()}
     * @param value the value to set the slider to
     */
    @Override
    public void setValue(double value)
    {
        super.setValue(value);
        
        MissionControl.getInstance().setFrameWaitTime((long)(getMaximum() - getValue()));
    }
}
