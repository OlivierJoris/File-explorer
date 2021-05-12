/**
 * Allows to visit the different elements of the tree.
 */
public abstract class Visitor{

    /**
     * Allows to visit a given file.
     */
    public abstract void visitFile(File file);

    /**
     * Allows to visit a given folder.
     */
    public abstract void visitFolder(Folder folder);

    /**
     * Allows to visit a given alias.
     */
    public abstract void visitAlias(Alias alias);

    /**
     * Allows to visit a given archive.
     */
    public abstract void visitArchive(Archive archive);
}