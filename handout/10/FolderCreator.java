/**
 * Concrete creator of the factory method pattern which
 * is creating a folder of the file manager.
 */
public class FolderCreator extends EntityCreator{
    private static FolderCreator creator = null;

    private FolderCreator(){}

    /**
     * Returns the folder creator.
     */
    public static FolderCreator getCreator(){
        if(creator == null)
            creator = new FolderCreator();
        
        return creator;
    }

    @Override
    public Entity createEntity(String name){
        if(name.equals("root"))
            return new Folder(name, true);
        return new Folder(name, false);
    }
}
