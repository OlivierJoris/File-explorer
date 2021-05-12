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
}
