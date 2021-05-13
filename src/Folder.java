/**
 * Concrete product of the factory method pattern which
 * represents a folder of the file manager.
 */
public class Folder extends Entity{
    /**
     * Creates a folder with the given name.
     */
    Folder(String name, boolean isRoot){
        super(name, isRoot);
    }

    /**
     * Performs the given visitor.
     */
    public void accept(Visitor v){
        v.visitFolder(this);
    }

    public String toString(){
        return super.toString();
    }
}
