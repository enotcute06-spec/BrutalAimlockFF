using UnityEngine;

public class BrutalAimlockFF : MonoBehaviour
{
    public Transform playerCamera;
    public float aimRange = 150f;
    public LayerMask enemyLayer;

    void Update()
    {
        Transform headTarget = FindNearestHead();
        if (headTarget != null)
        {
            Vector3 dir = (headTarget.position - playerCamera.position).normalized;
            Quaternion lookRot = Quaternion.LookRotation(dir);
            playerCamera.rotation = Quaternion.Slerp(playerCamera.rotation, lookRot, Time.deltaTime * 30f);
        }
    }

    Transform FindNearestHead()
    {
        Collider[] enemies = Physics.OverlapSphere(transform.position, aimRange, enemyLayer);
        Transform bestHead = null;
        float closest = Mathf.Infinity;

        foreach (Collider enemy in enemies)
        {
            Transform head = enemy.transform.Find("head") ?? enemy.transform.Find("Head") ?? enemy.transform.Find("bone_head");
            if (head == null) continue;

            float dist = Vector3.Distance(transform.position, head.position);
            if (dist < closest)
            {
                closest = dist;
                bestHead = head;
            }
        }

        return bestHead;
    }
}
