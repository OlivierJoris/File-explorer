/**
 * Concrete product of the factory method pattern which
 * represents a file of the file manager.
 */
public class File extends Entity{
    private String content;

    /**
     * Creates a file with the given name.
     */
    File(String name){
        // A file cannot be the root
        super(name, false);
        content = "";
    }

    /**
     * Sets the content of the file.
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * Gets the content of the file.
     */
    public String getContent(){
        return content;
    }

    /**
     * Performs the given visitor.
     */
    public void accept(Visitor v){
        v.visitFile(this);
    }

    public String toString(){
        return super.toString();
    }
}
