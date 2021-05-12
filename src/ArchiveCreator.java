/**
 * Concrete creator of the factory method pattern which
 * is creating an archive of the file manager.
 */
public class ArchiveCreator extends EntityCreator{
    private static ArchiveCreator creator = null;

    private ArchiveCreator(){}

    public static ArchiveCreator getCreator(){
        if(creator == null)
            creator = new ArchiveCreator();

        return creator;
    }

    @Override
    public Entity createEntity(String name){
        return new Archive(name);
    }
}
