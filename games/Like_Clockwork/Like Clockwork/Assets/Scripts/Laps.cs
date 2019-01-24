using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

public class Laps : MonoBehaviour {

    // Fields ===========================
    // public:
    public Text displayLap;     // Shows current lap/3
    public Text winText;        // The end game text for winning
    // -    -    -    -    -    -    -    -    -    -    -    -    
    // private:
    static int lap;                 // Lap number
    private bool firstCheckpoint;   // The first checkpoint
    private bool secondCheckpoint;  // The second checkpoint
    // ==========================================================


    // Initialization
    void Start () {
        lap = 0;
        displayLap.text = lap + "/3";
        firstCheckpoint = false;
        secondCheckpoint = false;
        winText.text = "";

    }

    // When a checkpoint is reached
    private void OnTriggerEnter(Collider other)
    {
        // When car hits Lap
        if (other.gameObject.CompareTag("Lap"))
        {

            // Only increment if it passed all checkpoints
            if (firstCheckpoint == true && secondCheckpoint == true && lap <= 2)
            {
                // increment lap and display it
                lap +=1;
                displayLap.text = lap.ToString()+ "/3";

                // Set checkpoints to false onced used up
                firstCheckpoint = false;    
                secondCheckpoint = false;

                // If it crossed the final lap, it wins.
                if (lap == 3) {
                    winText.text = "You win!";
                }
            }
        }

        // Check if first checkpoint is crossed
        if (other.gameObject.CompareTag("firstCheckpoint"))
        {
            firstCheckpoint = true;
        }

        // Check if second checkpoint is crossed
        if (other.gameObject.CompareTag("secondCheckpoint"))
        {
            secondCheckpoint = true;
        }

    }

}
