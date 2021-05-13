import montefiore.ulg.ac.be.graphics.*;

/**
 * Allows to build an archive of the different elements of the tree.
 */
public class ArchiveBuilderVisitor extends Visitor{
    public void visitFile(File file){
        // We can't archive a file directly.
    }

    public void visitAlias(Alias alias){
        // We can't archive an alias directly.
    }

    public void visitArchive(Archive archive){
        // We can't archive an archive directly.
    }

    /**
     * Archives a folder.
     */
    public void visitFolder(Folder folder){
        ExplorerSwingView view = ViewManager.getManager().getTreeManipulator().getView();

        // First, we need the info about the archive
        String archiveName = view.displayArchiveWindow1();
        String archiveExtension = view.displayArchiveWindow2();
        int archiveCompression = view.displayArchiveWindow3();

        // Operation was cancelled by the user
        if(archiveName == null || archiveExtension == null || archiveCompression == -1)
            return;

        // Creates an empty archive and adds it to the tree
        Entity archive = ArchiveCreator.getCreator().createEntity(
            archiveName,
            archiveExtension,
            archiveCompression
        );
        try{
            view.addNodeToParentNode(archive);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select something to be copied.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while archiving");
            return;
        }

        // Sets the pointers accordingly.
        folder.getParent().addChild(archive);
        archive.setParent(folder.getParent());

        // We need to fill the empty archive with the elements inside folder
        buildArchiveRec(folder, archive);
        return;
    }

    /**
     * Builds an archive recursively inside destination based on the elements
     * inside the given source.
     */
    private void buildArchiveRec(Entity source, Entity destination){
        for(Entity e : source.getChildren()){
            if(e instanceof File){
                File toCopy = (File) e;
                Entity copy = FileCreator.getCreator().createEntity(
                    toCopy.getName(),
                    toCopy.getContent()
                );
                copy.setParent(destination);
                destination.addChild(copy);
            }

            if(e instanceof Alias){
                Alias toCopy = (Alias) e;
                Entity copy = AliasCreator.getCreator().createEntity(
                    toCopy.getName(),
                    toCopy.getFile()
                );
                copy.setParent(destination);
                destination.addChild(copy);
            }

            if(e instanceof Folder){
                // First, copy folder
                Folder toCopy = (Folder) e;
                Entity copy = FolderCreator.getCreator().createEntity(toCopy.getName());
                // Set pointers
                copy.setParent(destination);
                destination.addChild(copy);
                // Recursively copy content
                buildArchiveRec(toCopy, copy);
            }

            if(e instanceof Archive){
                // First, copy archive
                Archive toCopy = (Archive) e;
                Entity copy = ArchiveCreator.getCreator().createEntity(
                    toCopy.getName(),
                    toCopy.getExtension(),
                    toCopy.getCompressionLevel()
                );
                // Set pointers
                copy.setParent(destination);
                destination.addChild(copy);
                // Recursively copy content
                buildArchiveRec(toCopy, copy);
            }
        }
    }
}
