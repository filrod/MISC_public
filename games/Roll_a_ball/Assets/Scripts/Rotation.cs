using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Rotation : MonoBehaviour {

    private Vector3 rotationVec;
    public int rotationSpeed;

    void Start() {

        rotationVec.x = 10 * (Random.value + Mathf.Epsilon);
        rotationVec.z = 10 * (Random.value + Mathf.Epsilon);

        rotationVec = rotationSpeed * Vector3.Normalize(rotationVec);

    }

    // Update is called once per frame
    void Update () {

        // Rotate the coins to grab player's attention
        transform.Rotate(rotationVec * Time.deltaTime);  // Mult makes smoother
       // transform.localScale();
		
	}
}
