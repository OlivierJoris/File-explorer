/**
 * Concrete creator of the factory method pattern which
 * is creating a folder of the file manager.
 */
public class FolderCreator extends EntityCreator{
    @Override
    public Entity createEntity(String name){
        if(name.equals("root"))
            return new Folder(name, true);
        return new Folder(name, false);
    }
}
