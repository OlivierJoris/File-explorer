/**
 * Concrete creator of the factory method pattern which
 * is creating an archive of the file manager.
 */
public class ArchiveCreator extends EntityCreator{
    private static ArchiveCreator creator = null;

    private ArchiveCreator(){}

    /**
     * Returns the archive creator.
     */
    public static ArchiveCreator getCreator(){
        if(creator == null)
            creator = new ArchiveCreator();

        return creator;
    }

    @Override
    public Entity createEntity(String name){
        return new Archive(name);
    }

    /**
     * Creates an archive with the given properties.
     */
    public Entity createEntity(String name, String extension, int compression){
        Archive a = new Archive(name);
        a.setupArchive(extension, compression);
        return a;
    }
}
