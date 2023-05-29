/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.UI;

import com.simsilica.lemur.DefaultRangedValueModel;
import group9.project.MissionControl;

/**
 *
 * @author natem
 */
public class SimSpeedSliderModel extends DefaultRangedValueModel{
    
    public SimSpeedSliderModel(double min, double max, double value)
    {
        super(min, max, value);
    }
    
    @Override
    public void setValue(double value)
    {
        super.setValue(value);
        MissionControl.getInstance().setFrameWaitTime((long)value);
    }
}
