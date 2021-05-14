import java.util.Vector;

/**
 * Product of the factory method pattern which 
 * represents an entity of the file manager.
 */
public abstract class Entity{
    protected String name; 
    protected boolean isRoot;
    protected boolean isCopy;

    private Entity parent;
    private Vector<Entity> children;

    /**
     * Creates an entity of the file manager.
     */
    Entity(String name, boolean isRoot){
        this.name = name;
        this.isRoot = isRoot;
        this.isCopy = false;
        this.parent = null;
        this.children = new Vector<Entity>();
    }

    /**
     * Returns the name of the Entity.
     */
    public String getName(){
        return name;
    }

    /**
     * True if the Entity is the root. Else, false.
     */
    public boolean isRoot(){
        return isRoot;
    }

    /**
     * Sets the parent of the entity.
     */
    public void setParent(Entity parent){
        this.parent = parent;
    }

    /**
     * Returns the parent of the entity.
     */
    public Entity getParent(){
        return parent;
    }

    /**
     * Returns the children of the entity.
     */
    public Vector<Entity> getChildren(){
        return children;
    }

    /**
     * Adds a child to the entity.
     */
    public void addChild(Entity child){
        children.add(child);
    }

    /**
     * True if the entity has one or multiple child(ren). Else, false.
     */
    public boolean hasChildren(){
        return children.size() > 0;
    }

    /**
     * Allows a visitor to visit the entity.
     */
    public abstract void accept(Visitor v);

    public String toString(){
        return name;
    }
}
