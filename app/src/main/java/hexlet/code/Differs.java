package hexlet.code;

public final class Differs {

    private String key;
    private DiffersStates status;
    private Object oldValue;
    private Object newValue;

    public Differs(String key, DiffersStates status, Object value1, Object value2) {
        this.key = key;
        this.status = status;
        this.oldValue = value1;
        this.newValue = value2;
    }

    public String getKey() {
        return key;
    }

    public DiffersStates getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    @Override
    public String toString() {
        //return "{key:" + key + "; status:" + status + "; oldValue:" + oldValue + "; newValue:" + newValue + "}";
        return ("\"" + key + "\", \"" + status + "\", \"" + oldValue + "\", \"" + newValue + "\"");
    }
}
