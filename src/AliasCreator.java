/**
 * Concrete creator of the factory method pattern which
 * is creating an alias of the file manager.
 */
public class AliasCreator extends EntityCreator{
    private static AliasCreator creator = null;

    private AliasCreator(){}

    /**
     * Returns the alias creator.
     */
    public static AliasCreator getCreator(){
        if(creator == null)
            creator = new AliasCreator();
            
        return creator;
    }

    @Override
    public Entity createEntity(String name){
        return new Alias(name);
    }
}
