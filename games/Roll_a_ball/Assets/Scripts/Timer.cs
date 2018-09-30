using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Timer : MonoBehaviour {

    public float time;
    public Text timeText;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {

        time = time - Time.deltaTime;

        timeText.text = "Time: " + time.ToString("00");

        if (time < -0.2)
        {

            SceneManager.LoadScene(SceneManager.GetActiveScene().name);
        }

    }
}
