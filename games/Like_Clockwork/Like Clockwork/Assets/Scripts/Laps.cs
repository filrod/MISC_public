using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Laps : MonoBehaviour {

    public Collider firstTrigger;
    public Collider secondTrigger;
    public Collider Lap;

    private string[] tags;

    // Use this for initialization
    void Start () {
        tags = new string[3];
        tags[0] = firstTrigger.tag;
        tags[1] = secondTrigger.tag;
        tags[3] = Lap.tag;
	}

    void OnCollisionEnter(Collision collision)
    {
        foreach (ContactPoint contact in collision.contacts)
        {

        }

    }
    private void OnTriggerEnter(Collider other)
    {
        
    }

    // Update is called once per frame
    void Update () {
	}
}
