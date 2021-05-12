/**
 * Concrete creator of the factory method pattern which
 * is creating a file of the file manager.
 */
public class FileCreator extends EntityCreator{
    private static FileCreator creator = null;

    private FileCreator(){}

    /**
     * Returns the file creator.
     */
    public static FileCreator getCreator(){
        if(creator == null)
            creator = new FileCreator();
        
        return creator;
    }

    @Override
    public Entity createEntity(String name){
        return new File(name);
    }

    /**
     * Creates a file with the given content.
     */
    public Entity createEntity(String name, String content){
        File f = new File(name);
        f.setContent(content);
        return f;
    }
}
