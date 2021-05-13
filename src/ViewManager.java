import montefiore.ulg.ac.be.graphics.*;

/**
 * Represents the manager of the view.
 */
class ViewManager{
    private static ViewManager viewManager = null;
    private static TextAreaManipulator textManipulator;
    private static TreeViewManipulator treeManipulator;

    public static ViewManager getManager(ExplorerSwingView view){
        if(viewManager == null)
            viewManager = new ViewManager(view);

        return viewManager;
    }

    public static ViewManager getManager(){
        return viewManager;
    }

    /**
     * Creates the manager of the view.
     */
    private ViewManager(ExplorerSwingView view){
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
