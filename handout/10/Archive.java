/**
 * Concrete product of the factory method pattern which
 * represents an archive of the file manager.
 */
public class Archive extends Entity{
    private String extension;
    private int compression;

    /**
     * Creates an archive with the given name.
     */
    Archive(String name){
        // An archive cannot be the root
        super(name, false);
        extension = "";
        compression = -1;
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
     * Returns the extension of the archive.
     */
    public String getExtension(){
        return extension;
    }

    /**
     * Returns the compression level of the archive.
     */
    public int getCompressionLevel(){
        return compression;
    }

    /**
     * Performs the given visitor.
     */
    public void accept(Visitor v){
        v.visitArchive(this);
    }

    public String toString(){
        return name + extension;
    }
}
