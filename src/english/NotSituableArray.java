package english;

public class NotSituableArray extends Exception
{

    String message;

    public NotSituableArray(String msg) {
        super(msg);
        message = msg;
    }

    @Override
    public String toString() {
        return "NotSituableArray was passed into Word object\n" + message;
    }
}
