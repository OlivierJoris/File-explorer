import montefiore.ulg.ac.be.graphics.*;

/**
 * Allows to build a copy of the different elements of the tree.
 */
public class CopyVisitor extends Visitor{
    /**
     * Copies a file.
     */
    public void visitFile(File file){
        // Create the new file with the same content.
        Entity copied = FileCreator.getCreator().createEntity(
            file.getName() + "(copy)",
            file.getContent()
        );

        file.getParent().addChild(copied);
        copied.setParent(file.getParent());

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

        folder.getParent().addChild(copied);
        copied.setParent(folder.getParent());

        ExplorerSwingView view = ViewManager.getManager().getTreeManipulator().getView();
        // Add copied folder to tree.
        try{
            view.addNodeToParentNode(copied);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select something to be copied.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while copying.");
            return;
        }

        copyFolderRec(folder, (Folder)copied, 1);
    }

    /**
     * Copies a folder recusrively into the destination folder.
     */
    private void copyFolderRec(Folder folder, Folder destination, int depth){
        for(Entity e : folder.getChildren()){
            if(e instanceof File){
                File toCopy = (File) e;
                Entity copiedFile = FileCreator.getCreator().createEntity(
                    toCopy.getName() + "(copy)",
                    toCopy.getContent()
                );
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
                updateView(alias, depth);
                alias.setParent(destination);
                destination.addChild(alias);
            }
            if(e instanceof Folder){
                Folder toCopy = (Folder) e;
                Entity newFolder = FolderCreator.getCreator().createEntity(toCopy.getName() + "(copy)");

                updateView(newFolder, depth);
                newFolder.setParent(destination);
                destination.addChild(newFolder);

                copyFolderRec(toCopy, (Folder)newFolder, depth+1);
            }
            if(e instanceof Archive){
                // TO FILL
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

    public void visitAlias(Alias alias){
        // We can't copy an alias directly.
        return;
    }

    public void visitArchive(Archive archive){
        // TO FILL
    }
}