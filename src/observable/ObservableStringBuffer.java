package observable;

import javafx.beans.binding.StringBinding;

public class ObservableStringBuffer extends StringBinding {

    private final StringBuffer buffer = new StringBuffer() ;

    private static ObservableStringBuffer instance;
    private ObservableStringBuffer ()
    {

    }

    public static ObservableStringBuffer getInstance()
    {
        if(instance == null)
        {
            instance =new ObservableStringBuffer();
        }
        return instance;
    }
    @Override
    protected String computeValue() {
        return buffer.toString();
    }


    public void set(String content) {
        buffer.replace(0, buffer.length(), content);
        invalidate();
    }

    public void append(String text) {
        buffer.append(text+"\n");
        invalidate();
    }

    // wrap other StringBuffer methods as needed...

}