/* Concrete product of the factory method pattern which
   represents an archive of the file manager. */
public class Archive extends Entity {
    Archive(String name){
        // An archive can not be the root.
        super(name, false);
    }
}
