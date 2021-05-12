/**
 * Product of the factory method pattern which 
 * represents an entity of the file manager.
 */
public abstract class Entity {
    protected String name; 
    protected boolean isRoot;

    /**
     * Creates an entity of the file manager.
     */
    Entity(String name, boolean isRoot){
        this.name = name;
        this.isRoot = isRoot;
    }
}
