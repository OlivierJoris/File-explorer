/* Concrete creator of the factory method pattern which
       is creating a file of the file manager. */
public class FileCreator extends EntityCreator {
    @Override
    public Entity createEntity(String name){
        return new FileCreator(name);
    }
}
