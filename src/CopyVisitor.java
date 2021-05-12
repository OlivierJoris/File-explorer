import montefiore.ulg.ac.be.graphics.*;

/**
 * Allows to build a copy of the different elements of the tree.
 */
public class CopyVisitor extends Visitor{
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

    public void visitFolder(Folder folder){
        // TO FILL
    }

    public void visitAlias(Alias alias){
        // We don't copy an alias.
        return;
    }

    public void visitArchive(Archive archive){
        // TO FILL
    }
}