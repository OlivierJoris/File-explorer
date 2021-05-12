/**
 * Concrete product of the factory method pattern which
 * represents an alias of the file manager.
 */
public class Alias extends Entity{
    private File associatedFile;

    /**
     * Creates an alias to the given file.
     */
    Alias(String name){
        // An alias can not be the root.
        super(name, false);
        associatedFile = null;
    }

    // Sets the file to which the alias is pointing.
    public void setFile(File f) {
        associatedFile = f;
    }

    // Gets the file to which the alias is pointing.
    public File getFile(){
        return associatedFile;
    }

    public void accept(Visitor v){
        v.visitAlias(this);
    }

    public String toString(){
        return super.toString();
    }
}
