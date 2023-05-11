/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.UI.Camera;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

/**
 *
 * @author natem
 */
public class CustomCameraControl extends AbstractControl {
    
    InputManager inputManager;
    Camera cam;
    
    Vector3f initialUpVec;
    
    float delta = 0;
    float speed = 0;
    float rotationSpeed = 5;
    float speedSensitivity = 2f;
    
    boolean enabled = true;
        
    public CustomCameraControl()
    {
    }
    
    public float getSpeed()
    {
        return speed;
    }
    
    private float changeSpeed(boolean increase, float tpf)
    {
        if (increase)
        {
            delta += 10f * speedSensitivity * tpf;
        } else 
        {
            delta -= 10f * speedSensitivity * tpf;
            /*
            if (delta < 0)
            {
                delta = 0;
            }
            */
        }
        
        return Math.signum(delta) * (float)Math.pow(1.1, Math.abs(delta))-1;
    
    }
    
    public void setCamera(Camera cam)
    {
        this.cam = cam;
        initialUpVec = cam.getUp().clone();
    }
    
    @Override
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
    
    public void setInput(InputManager inputManager)
    {
        this.inputManager = inputManager;
        inputManager.setCursorVisible(false);
        
        inputManager.addMapping("Increase Move Speed", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Decrease Move Speed", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Toggle Move", new KeyTrigger(KeyInput.KEY_SPACE));
        
        inputManager.addMapping("Rotate Left", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("Rotate Right", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("Rotate Up", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("Rotate Down", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        
        AnalogListener analogListener = new AnalogListener() {
            @Override
            public void onAnalog(String name, float value, float tpf) {
                if (!enabled) return;
                if (name.equals("Increase Move Speed"))
                {
                    speed = changeSpeed(true, tpf);
                } else if (name.equals("Decrease Move Speed"))
                {
                    speed = changeSpeed(false, tpf);
                } else if (name.equals("Toggle Move"))
                {
                    enabled = !enabled;
                } else if (name.equals("Rotate Left"))
                {
                    rotateCamera(value, initialUpVec);
                } else if (name.equals("Rotate Right"))
                {
                    rotateCamera(-value, initialUpVec);
                } else if (name.equals("Rotate Up"))
                {
                    rotateCamera(value, new Vector3f(1f,0f,0f));
                } else if (name.equals("Rotate Down"))
                {
                    rotateCamera(-value, new Vector3f(1f,0f,0f));
                }
            }
        };
        
        inputManager.addListener(analogListener, new String[]{"Increase Move Speed","Decrease Move Speed","Toggle Move","Rotate Left","Rotate Right","Rotate Up","Rotate Down"});
    }
    
    /**
     * Rotate the camera by the specified amount around the specified axis.
     *
     * @param value rotation amount
     * @param axis direction of rotation (a unit vector)
     */
    protected void rotateCamera(float value, Vector3f axis) {
        if (!enabled) return;
  
        Quaternion localRotation = this.getSpatial().getLocalRotation();
        Quaternion rotation = new Quaternion();
        if (axis.getX() == 1f)
        {
            localRotation.mult(rotation.fromAngles(value*rotationSpeed,0,0));
        } else if (axis.getY() == 1f)
        {
            localRotation.mult(rotation.fromAngles(0,value*rotationSpeed,0));
        }
        //System.out.println(localRotation.toString());
        this.getSpatial().rotate(rotation);
        
        //Vector3f rotationAxis = axis.mult(rotationSpeed).mult(value);
        //System.out.println("rotation: " + rotationAxis);
        //this.getSpatial().rotate(rotationAxis.getX(), rotationAxis.getY(), 0);
        //System.out.println("up: " + cam.getUp());
        //System.out.println("up: " + this.cam.getUp());
        //this.cam.lookAt(this.getSpatial().getLocalTranslation(), initialUpVec);

        /*
        Matrix3f mat = new Matrix3f();
        mat.fromAngleNormalAxis(rotationSpeed * value, axis);

        Vector3f up = cam.getUp();
        Vector3f left = cam.getLeft();
        Vector3f dir = cam.getDirection();

        mat.mult(up, up);
        mat.mult(left, left);
        mat.mult(dir, dir);

        Quaternion q = new Quaternion();
        q.fromAxes(left, up, dir);
        q.normalizeLocal();

        //cam.setAxes(q);
        this.getSpatial().setLocalRotation(q);
*/

    }
    
    @Override
    public Control cloneForSpatial(Spatial spatial)
    {
        final CustomCameraControl control = new CustomCameraControl();
        control.setCamera(cam);
        control.setInput(inputManager);
        control.setSpatial(spatial);
        return control;
    }
    
    @Override
    protected void controlUpdate(float tpf)
    {
        if (!enabled) return;
        this.getSpatial().move(cam.getDirection().normalize().mult(speed).mult(tpf));
        //System.out.println("speed: " + speed);
        //inputManager.setCursorVisible(false);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
