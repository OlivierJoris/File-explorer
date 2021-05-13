import montefiore.ulg.ac.be.graphics.*;

/**
 * Allows to build a copy of the different elements of the tree.
 */
public class CopyVisitor extends Visitor{

    public void visitAlias(Alias alias){
        // We can't copy an alias directly.
        return;
    }

    /**
     * Copies a file.
     */
    public void visitFile(File file){
        // Create the new file with the same content
        Entity copied = FileCreator.getCreator().createEntity(
            file.getName() + "(copy)",
            file.getContent()
        );

        // Set pointers
        file.getParent().addChild(copied);
        copied.setParent(file.getParent());

        // Add to view
        ExplorerSwingView view = ViewManager.getManager().getTreeManipulator().getView();
        try{
            view.addNodeToParentNode(copied);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select something to be copied.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while copying.");
            return;
        }
    }

    /**
     * Copies a folder.
     */
    public void visitFolder(Folder folder){
        // Creates the new folder
        Entity copied = FolderCreator.getCreator().createEntity(folder.getName() + "(copy)");

        // Sets pointers
        folder.getParent().addChild(copied);
        copied.setParent(folder.getParent());

        ExplorerSwingView view = ViewManager.getManager().getTreeManipulator().getView();
        // Adds copied folder to tree
        try{
            view.addNodeToParentNode(copied);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select something to be copied.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while copying.");
            return;
        }

        // Copies the content of the folder recursively
        copyRec(folder, copied, 1, true);
    }

    /**
     * Copies a source recursively into the destination.
     */
    private void copyRec(Entity source, Entity destination, int depth, boolean show){
        for(Entity e : source.getChildren()){
            if(e instanceof File){
                File toCopy = (File) e;
                Entity copiedFile = FileCreator.getCreator().createEntity(
                    toCopy.getName() + "(copy)",
                    toCopy.getContent()
                );
                if(show)
                    updateView(copiedFile, depth);
                copiedFile.setParent(destination);
                destination.addChild(copiedFile);
            }

            if(e instanceof Alias){
                Alias toCopy = (Alias) e;
                Entity alias = AliasCreator.getCreator().createEntity(
                    toCopy.getName(),
                    toCopy.getFile()
                );
                if(show)
                    updateView(alias, depth);
                alias.setParent(destination);
                destination.addChild(alias);
            }
            
            if(e instanceof Folder){
                // Builds empty folder
                Folder toCopy = (Folder) e;
                Entity newFolder = FolderCreator.getCreator().createEntity(toCopy.getName() + "(copy)");

                // Updates the view and the pointers
                if(show)
                    updateView(newFolder, depth);
                newFolder.setParent(destination);
                destination.addChild(newFolder);

                // Copies the content recursively
                copyRec(toCopy, newFolder, depth+1, true);
            }

            if(e instanceof Archive){
                // Builds empty archive
                Archive toCopy = (Archive) e;
                Entity newArchive = ArchiveCreator.getCreator().createEntity(
                    toCopy.getName(), 
                    toCopy.getExtension(),
                    toCopy.getCompressionLevel()
                );

                // Updates the view and the pointers
                if(show)
                    updateView(newArchive, depth);
                newArchive.setParent(destination);
                destination.addChild(newArchive);

                // Copies the content of the archive recursively
                copyRec(toCopy, newArchive, depth+1, false);
            }
        }
    }

    /**
     * Updates the view when the given object is added at the given depth.
     */
    private void updateView(Object newObject, int depth){
        ExplorerSwingView view = ViewManager.getManager().getTreeManipulator().getView();
        try{
            view.addNodeToLastInsertedNode(newObject, depth);
        }catch(NoPreviousInsertedNodeException noPrev){
            view.showPopupError("Error during copy");
            return;
        }catch(LevelException levelError){
            view.showPopupError("Error during copy");
            return;
        }
    }

    /**
     * Copies an archive.
     */
    public void visitArchive(Archive archive){
        // Creates the new archive
        Entity copied = ArchiveCreator.getCreator().createEntity(
            archive.getName() + "(copy)",
            archive.getExtension(),
            archive.getCompressionLevel()
        );

        // Sets pointers
        archive.getParent().addChild(copied);
        copied.setParent(archive.getParent());

        ExplorerSwingView view = ViewManager.getManager().getTreeManipulator().getView();
        // Adds copied archived to tree
        try{
            view.addNodeToParentNode(copied);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select something to be copied.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while copying.");
            return;
        }

        // Copies the content of the archive recursively
        copyRec(archive, copied, 1, false);
    }
}
