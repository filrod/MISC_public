using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Rotation : MonoBehaviour {
	
	// Update is called once per frame
	void Update () {

        // Rotate the coins to grab player's attention
        transform.Rotate(new Vector3(0, 0, 60) * Time.deltaTime);  // Mult makes smoother
		
	}
}
