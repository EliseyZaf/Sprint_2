package samsungcampus.sprint2.elikur.core.addative;

public class Transform {
    public float x;
    public float y;
    private float angle;

    public Transform() {
        this.x = 0;
        this.y = 0;
        this.angle = 0;
    }

    public Transform(float x, float y) {
        this.x = x;
        this.y = y;
        this.angle = 0;
    }

    public Transform(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = normalizeAngle(angle);
    }

    public void addAngle(float add) {
        angle = normalizeAngle((angle + add));
    }

    public void subAngel(float sub) {
        angle = normalizeAngle((angle - sub));
    }

    public float normalizeAngle(float angle) {
        while (angle > 360) angle -= 360;
        while (angle < 0)   angle += 360;
        return angle;
    }
}
