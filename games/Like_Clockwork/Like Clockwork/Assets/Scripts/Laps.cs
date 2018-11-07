using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Laps : MonoBehaviour {

    static int lap;
    private bool firstCheckpoint;
    private bool secondCheckpoint;


    // Use this for initialization
    void Start () {
        lap = 0;
        firstCheckpoint = false;
        secondCheckpoint = false;

    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.CompareTag("Lap"))
        {

            if (firstCheckpoint && secondCheckpoint)
            {
                lap++;

                firstCheckpoint = false;
                secondCheckpoint = false;
            }
        }

        if (other.gameObject.CompareTag("firstCheckpoint"))
        {
            firstCheckpoint = true;
        }

        if (other.gameObject.CompareTag("secondCheckpoint"))
        {
            secondCheckpoint = true;
        }

    }

        // Update is called once per frame
        void Update () {

	    }
}
