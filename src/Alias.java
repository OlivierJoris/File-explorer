/**
 * Concrete product of the factory method pattern which
 * represents an alias of the file manager.
 */
public class Alias extends Entity{
    /**
     * Creates an alias to the given file.
     */
    Alias(String name){
        // An alias can not be the root.
        super(name, false);
    }
}
