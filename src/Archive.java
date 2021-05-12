/**
 * Concrete product of the factory method pattern which
 * represents an archive of the file manager.
 */
public class Archive extends Entity{
    private String extension;
    private int compression;

    Archive(String name){
        // An archive cannot be the root.
        super(name, false);
    }

    /**
     * Sets up the child, the extension, and the compression level of
     * the archive.
     */
    public void setupArchive(String extension, int compression){
        this.extension = extension;
        this.compression = compression;
    }

    /**
     * Returns the compression level of the archive.
     */
    public int getCompressionLevel(){
        return compression;
    }

    public String toString(){
        return super.name + extension;
    }
}
