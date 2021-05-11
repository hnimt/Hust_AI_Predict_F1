package sample.Model;


public class F1Evidence {
    private F1 f1;
    private Evidence evidence;
    private float positive;
    private float neutral;
    private float negative;

    public F1Evidence() {
    }

    public F1Evidence(F1 f1, Evidence evidence) {
        this.f1 = f1;
        this.evidence = evidence;
    }

    public F1Evidence(F1 f1, Evidence evidence, float positive, float neutral, float negative) {
        this.f1 = f1;
        this.evidence = evidence;
        this.positive = positive;
        this.neutral = neutral;
        this.negative = negative;
    }

    public F1 getF1() {
        return f1;
    }

    public void setF1(F1 f1) {
        this.f1 = f1;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public float getPositive() {
        return positive;
    }

    public void setPositive(float positive) {
        this.positive = positive;
    }

    public float getNeutral() {
        return neutral;
    }

    public void setNeutral(float neutral) {
        this.neutral = neutral;
    }

    public float getNegative() {
        return negative;
    }

    public void setNegative(float negative) {
        this.negative = negative;
    }

    @Override
    public String toString() {
        return "F1Evidence{" +
                "f1=" + f1.getF1Name() +
                ", evidence=" + evidence.getEvidenceName() +
                ", positive=" + positive +
                ", neutral=" + neutral +
                ", negative=" + negative +
                '}';
    }
}
