/**
 * Concrete product of the factory method pattern which
 * represents a folder of the file manager.
 */
public class Folder extends Entity {
    Folder(String name, boolean isRoot){
        super(name, isRoot);
    }
}
