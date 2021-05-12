/**
 * Concrete creator of the factory method pattern which
 * is creating an archive of the file manager.
 */
public class ArchiveCreator extends EntityCreator{
    @Override
    public Entity createEntity(String name){
        return new Archive(name);
    }
}
