/**
 * Concrete creator of the factory method pattern which
 * is creating an alias of the file manager.
 */
public class AliasCreator extends EntityCreator{
    @Override
    public Entity createEntity(String name){
        return new Alias(name);
    }
}
