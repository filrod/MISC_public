using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Clock : MonoBehaviour
{

    public float gold_time   = 170f;
    public float silver_time = 190f;
    public float bronze_time = 280f;

    private string gold_str;
    private string silver_str;
    private string bronze_str;

    public float time;
    public Text timeText;
    public Text Game_over;

    private bool gold   = true;
    private bool silver = true;
    private bool bronze = true;

    // Make a time string
    string makeTimeStr(float timeInSec) {

        float min_t = Mathf.FloorToInt(timeInSec / 60F);
        float sec_t = Mathf.FloorToInt(timeInSec - min_t * 60);
        return string.Format("{0:00}:{1:00}", min_t, sec_t);

    }
    // Use this for initialization
    void Start()
    {
        gold_str       = "Gold: " + makeTimeStr(gold_time) + "\n";
        silver_str       = "Silver: " + makeTimeStr(silver_time) + "\n";
        bronze_str       = "Bronze: " + makeTimeStr(bronze_time)+ "\n";

        Game_over.text = "";

    }

    // Update is called once per frame
    void Update()
    {

        time = time - Time.deltaTime;
        float min_t = Mathf.FloorToInt(time / 60F);
        float sec_t = Mathf.FloorToInt(time - min_t * 60);

        timeText.text = gold_str
                      + silver_str
                      + bronze_str
                      + "Time:"
                      + makeTimeStr(time); // time.ToString("00"); 

        if (time > gold_time && gold)
        {
            gold = false;

            timeText.text = silver_str
                          + bronze_str
                          + "Time:"
                          + makeTimeStr(time);
            silver = true;

        }

        if (time > silver_time && silver)
        {
            silver = false;

            timeText.text = bronze_str
                          + "Time:"
                          + makeTimeStr(time);
            bronze = true;

        }

        if (time > bronze_time && bronze)
        {
            bronze = false;

            timeText.text = "Time:\n"
                          + makeTimeStr(time);

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
