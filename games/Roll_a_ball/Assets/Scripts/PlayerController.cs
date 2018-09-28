using System.Collections;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


public class PlayerController : MonoBehaviour {

    // Create a rigid body insance
    private Rigidbody rb;

    // Create a speed field
    public float speed;

    // Create a counter for the coins
    private int count;

    // Create text variable for count
    public Text countText;

    // Create text variable for win
    public Text winText;

    void Start()
    {
        rb = GetComponent<Rigidbody>();

        // Set count to zero
        count = 0;

        // Set Count text
        set_countText();

        winText.text = "";
    }

    // Check every frame for player input
    // Update called before rendering frame
    void Update()
    {

        if (Input.GetKeyDown(KeyCode.R))
        {
            SceneManager.LoadScene(SceneManager.GetActiveScene().name);
        }

    }

    // Physics code applies
    void FixedUpdate()
    {

        float moveHorizontal = Input.GetAxis("Horizontal");
        float moveVertical = Input.GetAxis("Vertical");

        // Add a 3Vector for force
        Vector3 movement = new Vector3(moveHorizontal, 0.0f, moveVertical);  // Initialized

        rb.AddForce(movement*speed);
    }

    // Create a setter for countText
    void set_countText() {

        // Set Count text
        countText.text = "Coins: " + count.ToString();

        // 
        if (count>=12){
            winText.text = "You Win!";

            SceneManager.LoadScene(SceneManager.GetActiveScene().name);
        }
    }

    // A trigger method to check if objects will be picked up
    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.CompareTag("Pick Up"))
        {
           
            other.gameObject.SetActive(false);
            // Increment the count
            count += 1;

            // Change the displayed count
            set_countText();
        }
    }
}
