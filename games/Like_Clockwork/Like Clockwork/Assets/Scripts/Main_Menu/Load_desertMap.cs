using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Load_desertMap : MonoBehaviour
{
    public void loadDestertMap()
    {
        SceneManager.LoadScene(1);  // Loads desert map track specified to be scene 1 in the build
    }
}
