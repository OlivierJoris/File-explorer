/* Concrete product of the factory method pattern which
   represents an alias of the file manager. */
public class Alias {
    Alias(String name){
        // An alias can not be the root.
        super(name, false);
    }
}
