/* Concrete creator of the factory method pattern which
   is creating a folder of the file manager. */
public class FolderCreator extends EntityCreator {
    @Override
    public Entity createEntity(String name){
        if(name.equals("root"))
            return new FolderCreator(name, true);
        return new FolderCreator(name, false);
    }
}
