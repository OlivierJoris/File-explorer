/* Product of the factory method pattern which
   representis an entity of the file manager */
public abstract class Entity {
    protected String name; 
    protected boolean isRoot;

    Entity(String name, boolean isRoot){
        this.name = name;
        this.isRoot = isRoot;
    }
}
