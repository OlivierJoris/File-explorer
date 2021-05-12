/**
 * Allows to display the different elements of the tree.
 */
public class DisplayVisitor extends Visitor{
    /**
     * Displays a file.
     */
    public void visitFile(File file){
        TextAreaManipulator textManipulator = ViewManager.getManager().getTextManipulator();
        textManipulator.putString(file.getContent());
    }

    /**
     * Displays a file during a recusion at the given depth.
     */
    private void visitFileRec(File file, int depth){
        String indent = "";
        for(int i = 0; i < 2 * depth; i++)
            indent+=" ";

        TextAreaManipulator manipulator = ViewManager.getManager().getTextManipulator();
        manipulator.putString(indent + "- " + file);
    }

    /**
     * Displays a folder.
     */
    public void visitFolder(Folder folder){
        visitFolderRec(folder, 0);
    }

    /**
     * Displays a folder during a recursion at the given depth.
     */
    private void visitFolderRec(Folder folder, int depth){
        TextAreaManipulator manipulator = ViewManager.getManager().getTextManipulator();
        String indent = "";
        for(int i = 0; i < 2 * depth; i++)
            indent+=" ";

        for(Entity e : folder.getChildren()){
            if(e instanceof File)
                visitFileRec((File)e, depth);
            else if(e instanceof Folder){
                manipulator.putString(indent + "- " + e + ":");
                visitFolderRec((Folder)e, depth + 1);
            }
            else if(e instanceof Alias)
                visitAliasRec((Alias)e, depth);
            else if(e instanceof Archive){
                manipulator.putString(indent + "- " + (Archive)e + ":");
                visitArchiveRec((Archive)e, depth+1);
            }
        }
    }

    /**
     * Displays an alias.
     */
    public void visitAlias(Alias alias){
        File f = alias.getFile();
        if(f != null)
            visitFile(f);
    }

    /**
     * Displays an alias during a recursion at the given depth.
     */
    private void visitAliasRec(Alias alias, int depth){
        File f = alias.getFile();
        if(f != null)
            visitFileRec(f, depth);
    }

    /**
     * Displays an archive.
     */
    public void visitArchive(Archive archive){
        visitArchiveRec(archive, 0);
    }

    /**
     * Displays an archive during a recursion at the given depth.
     */
    private void visitArchiveRec(Archive archive, int depth){
        TextAreaManipulator manipulator = ViewManager.getManager().getTextManipulator();
        String indent = "";
        for(int i = 0; i < 2 * depth; i++)
            indent+=" ";

        for(Entity e : archive.getChildren()){
            if(e instanceof File)
                visitFileRec((File)e, depth);
            else if(e instanceof Folder){
                manipulator.putString(indent + "- " + e + ":");
                visitFolderRec((Folder)e, depth + 1);
            }
            else if(e instanceof Alias)
                visitAliasRec((Alias)e, depth);
            else{}
        }
    }
}