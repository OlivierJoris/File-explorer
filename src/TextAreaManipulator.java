import montefiore.ulg.ac.be.graphics.*;

/**
 * Allows to manipulate the text area of the window.
 */
public class TextAreaManipulator{
    private TextAreaManager manager;

    /**
     * Builds the manipulator of the given text area manager.
     */
    public TextAreaManipulator(TextAreaManager manager){
        this.manager = manager;
    }

    /**
     * Adds the given string and goes to the next line.
     */
    void putString(String s){
        manager.appendText(s + "\n");
    }

    /**
     * Adds each string inside the array on a new line.
     */
    void putStrings(String[] array){
        for(String s : array)
            putString(s);
    }

    /**
     * Clears the text area and displays the given string.
     */
    void setString(String s){
        emptyText();
        putString(s);
    }

    /**
     * Clears the text area and displays each line inside
     * the array on a new line.
     */
    void setStrings(String[] array){
        emptyText();

        for(String s : array)
            putString(s);
    }

    /**
     * Appends the given string without going to the next line.
     */
    void appendString(String s){
        manager.appendText(s);
    }

    /**
     * Clears the text area.
     */
    void emptyText(){
        manager.clearAllText();
    }
}