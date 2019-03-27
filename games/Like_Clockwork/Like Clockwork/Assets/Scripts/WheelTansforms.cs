using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WheelTansforms : MonoBehaviour {

    public Rigidbody car;
    public WheelCollider wheelFL;
    public WheelCollider wheelFR;
    public WheelCollider wheelRL;
    public WheelCollider wheelRR;

    public Transform wheelFL_trans;
    public Transform wheelFR_trans;
    public Transform wheelRL_trans;
    public Transform wheelRR_trans;

    public float maxTorque = 50;

    public AudioSource engineSound;

    // Use this for initialization
    void Start() {

        car = GetComponent<Rigidbody>();

        // Set low center of mass
        car.centerOfMass = new Vector3(0, -0.8f, 0);
    }

    private void Update()
    {
        rotateWheel(wheelFL_trans, wheelFL, 1.82f);
        rotateWheel(wheelFR_trans, wheelFR, 1.82f);
        rotateWheel(wheelRL_trans, wheelRL, -1.65f);
        rotateWheel(wheelRR_trans, wheelRR, -1.65f);
    }

    // Called several times per frame (physics)
    void FixedUpdate()
    {
      
        // Reverse 
        float reverse = 0f;
        if (Input.GetKey(KeyCode.Joystick1Button1)==true)
        {
            reverse -= 0.05f;
            wheelRR.motorTorque = (float)Math.Pow(maxTorque, 2) * reverse;
            wheelRL.motorTorque = (float)Math.Pow(maxTorque, 2) * reverse;
        }
        else {
            reverse = 0f;
            float drive = Input.GetAxis("Right Trigger");
            float brake = Input.GetAxis("Left Trigger");


            Acceleration(wheelRR, drive);
            Acceleration(wheelRL, drive);

            wheelRR.brakeTorque = brake * float.MaxValue/1.1f;
            wheelRL.brakeTorque = brake * float.MaxValue /1.1f;

            if (wheelRR.rpm > 500)
                wheelRR.motorTorque = 0;

            if (wheelRL.rpm > 500)
                wheelRL.motorTorque = 0;
        }

        //}
        // Stearing 
        float steer = Input.GetAxis("Horizontal");
        wheelFR.steerAngle = 30 * steer;
        wheelFL.steerAngle = 30 * steer;
    }

    void Acceleration(WheelCollider wheel, float drive) {
        /*
         * Acceleration factor dependant on rpm.
         */

        if (wheelRR.motorTorque > 0) { engineSound.Play(); }

        if (wheel.rpm < 6 && drive > 0.1)
        {
            
            wheelRR.motorTorque = float.MaxValue;
        }
        else if (wheel.rpm < 30 && drive > 0.1)
        {
            wheelRR.motorTorque = 0.8f * float.MaxValue;
        }
        else if (wheel.rpm < 80 && drive > 0.1)
        {
            wheelRR.motorTorque = 0.25f * float.MaxValue;
        }
        else if (wheel.rpm < 90 && drive > 0.1)
        {
            wheelRR.motorTorque = 0.05f * float.MaxValue;
        }
        else
        {
            wheel.motorTorque = (float)Math.Pow(maxTorque, 3)
                                * drive;
        }
    }

    private void rotateWheel(Transform wheel_t, WheelCollider wheel_col, float of) {
        /*
         * Method to rotate wheel.
         */
        int perMin = 60;
        int degPerRot = 360;
        float spinAmt = wheel_col.rpm
                       / (degPerRot * perMin * Time.deltaTime);



        wheel_t.localEulerAngles = new Vector3(wheel_t.localEulerAngles.x, 0.25f * wheel_col.steerAngle - wheel_t.localEulerAngles.z, wheel_t.localEulerAngles.z);

        wheel_t.Rotate(this.transform.worldToLocalMatrix*this.transform.right, spinAmt, Space.Self);
        //wheel_t.RotateAroundLocal(wheel_t.right, spinAmt);
        //wheel_t.RotateAround(wheel_t.transform.worldToLocalMatrix * wheel_t.right, spinAmt);
    }
    
}
