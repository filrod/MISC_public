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

    public float maxTorque = 50;

    // Use this for initialization
    void Start () {

        car = GetComponent<Rigidbody>();

        // Set low center of mass
        car.centerOfMass = new Vector3(0, -0.8f, 0);
	}
	
	// Called several times per frame (physics)
	void FixedUpdate () {

        wheelRR.motorTorque = maxTorque * Input.GetAxis("Vertical");
        wheelRL.motorTorque = maxTorque * Input.GetAxis("Vertical");

        // Stearing 
        wheelFR.steerAngle = 45 * Input.GetAxis("Horizontal");
        wheelFL.steerAngle = 45 * Input.GetAxis("Horizontal");


    }
}
