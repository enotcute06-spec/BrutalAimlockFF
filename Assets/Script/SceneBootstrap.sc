using UnityEngine;
using UnityEditor;
using UnityEditor.SceneManagement;

public class SceneBootstrap
{
    [MenuItem("BrutalAimlockFF/Generate Scene")]
    public static void GenerateScene()
    {
        var scene = EditorSceneManager.NewScene(NewSceneSetup.DefaultGameObjects, NewSceneMode.Single);

        // Create Player
        GameObject player = new GameObject("Player");
        var aim = player.AddComponent<BrutalAimlockFF>();
        var cam = Camera.main.transform;
        aim.playerCamera = cam;
        aim.enemyLayer = LayerMask.GetMask("Enemy");

        // Create Enemy Dummy
        for (int i = 0; i < 3; i++)
        {
            GameObject enemy = GameObject.CreatePrimitive(PrimitiveType.Capsule);
            enemy.name = "EnemyDummy_" + i;
            enemy.transform.position = new Vector3(i * 5, 0, 10);
            enemy.layer = LayerMask.NameToLayer("Enemy");

            GameObject head = new GameObject("head");
            head.transform.parent = enemy.transform;
            head.transform.localPosition = new Vector3(0, 1.6f, 0);
        }

        EditorSceneManager.SaveScene(scene, "Assets/Scenes/Main.unity");
    }
}
