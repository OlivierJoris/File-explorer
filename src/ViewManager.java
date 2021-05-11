import montefiore.ulg.ac.be.graphics.*;

/**
 * Represents the manager of the view.
 */
class ViewManager{
    private TextAreaManipulator textManipulator;
    private TreeViewManipulator treeManipulator;

    /**
     * Creates the manager of the view.
     */
    public ViewManager(ExplorerSwingView view){
        textManipulator = new TextAreaManipulator(view.getTextAreaManager());
        treeManipulator = new TreeViewManipulator(view);
    }

    /**
     * Gets the manipulator of the text area of the view.
     */
    public TextAreaManipulator getTextManipulator(){
        return textManipulator;
    }

    /**
     * Gets the manipulator of the tree of the view.
     */
    public TreeViewManipulator getTreeManipulator(){
        return treeManipulator;
    }
}