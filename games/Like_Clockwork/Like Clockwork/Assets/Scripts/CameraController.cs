using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour {

    // Camera 
    public Camera cam;
    // Car
    public GameObject car;
    private Rigidbody car_rb;
    private Transform car_t;

    // View
    public int defaultFOV = 53;
    private int viewModeTag = 0;
    private Vector3 view;

    private float distance = 9.3f;
    private float height = 1.46f;

    // Visual effects
    private float rotationDamp = 3.0f;
    private float heightDamp = 2.0f;
    private float zoomRatio = 0.5f;

    private Vector3 rotationVec;

    //private float turn;

	// Use this for initialization
	void Start () {
        car_rb = car.GetComponent<Rigidbody>();
        car_t = car.transform;
        view = transform.position;

	}

    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.V)) {
            nextViewMode();
            view = view - car.transform.position;
        }
    }

    private void FixedUpdate()
    {
        Vector3 localVel = car_t.transform
                                .InverseTransformDirection(
                                                           car_rb.velocity
                                                           );

        if (localVel.z < -0.5)
        {
            rotationVec = new Vector3(
                                      0f,
                                      car_t.transform.eulerAngles.y + 180,
                                      0f
                                      );
        }
        else {
            rotationVec = new Vector3(
                                      0f,
                                      car_t.transform.eulerAngles.y,
                                      0f
                                      );
        }

        float acc = car_rb.velocity.magnitude;
        cam.fieldOfView = defaultFOV + (float) (Math.Pow(acc, 2) * 2 * zoomRatio * Time.deltaTime);
    }

    // Update is called once per frame
    void LateUpdate () {

        float wantedAngle = car_t.eulerAngles.y;
        float wantedHeight = car_rb.position.y + height;

        float currAngle = transform.eulerAngles.y;
        float currHeight = transform.position.y;

        currAngle = Mathf.LerpAngle(
                                    currAngle, 
                                    wantedAngle, 
                                    rotationDamp 
                                    * Time.deltaTime
                                    );

        currHeight = Mathf.Lerp(
                                currHeight, 
                                wantedHeight, 
                                heightDamp
                                * Time.deltaTime
                                );

        Quaternion currRotation = Quaternion.Euler(-45f, currAngle, 0);

        transform.position = car_rb.position;

        transform.position -= currRotation
                              * Vector3.forward
                              * distance;
        transform.position = new Vector3(transform.position.x, car_t.position.y + height, transform.position.z);
        transform.LookAt(car_t);
    }

    private void nextViewMode() {

        if (viewModeTag == 0)
        {
            transform.eulerAngles = new Vector3(1.51f, -0.37f, 0);
            view = new Vector3(-0.08999324f, 2.46f, 7.90001f);

            // Set viewmode tag to 1 
            viewModeTag = 1;

        }
        else if (viewModeTag == 1)
        {
            transform.eulerAngles = new Vector3(1.51f, -0.37f, 0);
            view = new Vector3(-0.15f, 0.47f, 1.35f);

            // Set viewmode tag to 1 
            viewModeTag = 2;
        }
        else {
            transform.eulerAngles = new Vector3(1.51f, -0.37f, 0);
            view = new Vector3(-0.15f, 0.47f, 1.35f);

            // Set viewmode tag to 1 
            viewModeTag = 0;
        }
    }
}
