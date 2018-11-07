using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Timer : MonoBehaviour
{

    public float gold_time   = 170f;
    public float silver_time = 190f;
    public float bronze_time = 280f;

    private string gold_str;

    public float time;
    public Text timeText;
    public Text Game_over;

    private bool gold   = true;
    private bool silver = true;
    private bool bronze = true;


    // Use this for initialization
    void Start()
    {
        Game_over.text = "";

    }

    // Update is called once per frame
    void Update()
    {

        time = time - Time.deltaTime;
        float min_t = Mathf.FloorToInt(time / 60F);
        float sec_t = Mathf.FloorToInt(time - min_t * 60);

        timeText.text = "Gold: 1min30sec" 
                      + "\nSilver: 1min30sec" 
                      + "\nBronze: 1min30sec"
                      + "\nTime:\n"
                      + string.Format("{0:00}:{1:00}", min_t, sec_t); // time.ToString("00"); 

        if (time > gold_time && gold)
        {
            gold = false;

            timeText.text = "Silver: 1min30sec"
                          + "\nBronze: 1min30sec"
                          + "\nTime:\n"
                          + string.Format("{0:00}:{1:00}", min_t, sec_t);
            silver = true;

        }

        if (time > silver_time && silver)
        {
            silver = false;

            timeText.text = "Bronze: 1min30sec"
                          + "\nTime:\n"
                          + string.Format("{0:00}:{1:00}", min_t, sec_t);
            bronze = true;

        }

        if (time > bronze_time && bronze)
        {
            bronze = false;

            timeText.text = "Time:\n"
                          + string.Format("{0:00}:{1:00}", min_t, sec_t);

        }

        if (time > bronze_time+10)
        {
            Game_over.text = "Game Over";
        }

        if (time > bronze_time + 12)
        {
            SceneManager.LoadScene(SceneManager.GetActiveScene().name);
        }


    }
}
