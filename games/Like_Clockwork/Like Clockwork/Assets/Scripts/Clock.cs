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

    public Rigidbody car;
    public WheelCollider wheel;
    public Text rpm_text;
    public Text speed;

    private bool gold   = true;
    private bool silver = true;
    private bool bronze = true;

    // Make a time string
    string makeTimeStr(float timeInSec) {

        float min_t = Mathf.FloorToInt(timeInSec / 60F);
        float sec_t = Mathf.FloorToInt(timeInSec - min_t * 60);
        return string.Format("{0:0}min {1:00}sec.", min_t, sec_t);

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
        float k_mult = 2.1f;
        rpm_text.text = string.Format(
                                      "{0:00}rpm", 
                                      (
                                       k_mult*5.0225 * car.velocity.magnitude
                                       /(2f*Mathf.PI*wheel.radius))
                                       );

        speed.text = string.Format(
                                   "{0:0}km/h",
                                   k_mult*car.velocity.magnitude
                                   );

        time = time + Time.deltaTime;

        timeText.text = gold_str
                      + silver_str
                      + bronze_str
                      + "Time: "
                      + makeTimeStr(time); // time.ToString("00"); 

        if (time > gold_time && gold)
        {
            gold = false;

            gold_str = "";
            silver = true;

        }

        if (time > silver_time && silver)
        {
            silver = false;

            silver_str = "";

            bronze = true;

        }

        if (time > bronze_time && bronze)
        {
            bronze = false;

            bronze_str = "";

            timeText.text = "Time: "
                          + makeTimeStr(time);

        }

        if (time > bronze_time+10)
        {
            Game_over.text = "Game Over";
        }

        if (time > bronze_time + 13)
        {
            SceneManager.LoadScene(SceneManager.GetActiveScene().name);
        }


    }
}
