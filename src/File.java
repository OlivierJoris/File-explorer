/**
 * Concrete product of the factory method pattern which
 * represents a file of the file manager.
 */
public class File extends Entity {
    File(String name){
        // A file cannot be the root.
        super(name, false);
    }
}
