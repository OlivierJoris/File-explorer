import montefiore.ulg.ac.be.graphics.*;

/**
 * Allows to manipulate the view of the tree of the window.
 */
class TreeViewManipulator{
    private ExplorerSwingView view;

    /**
     * Builds the manipulator of the view of the tree.
     */
    public TreeViewManipulator(ExplorerSwingView view){
        this.view = view;
    }

    /**
     * Creates a file as the child of the given node.
     */
    void create_file(Object parentNode){
        // We need to check if the parent node is root or folder.
        // Else, operation should fail.

        String[] data = view.fileMenuDialog();
        if(data == null) // Operation has been cancelled by user.
            return;

        // Temporary. Should build the file object.
        A file = new A(data[0]);
        try{
            view.addNodeToSelectedNode(file);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select a node before creating a file");
            return;
        }

        // Temporary -- NEED TO BE REMOVED !
        System.out.println(data[1]);

        // Should add link in parent node to the newly created child.

        view.refreshTree();
        view.showPopup("Your file " + data[0] + " has been created");
        return;
    }

    /**
     * Creates a folder as the child of the given node.
     */
    void create_folder(Object parentNode){
        // We need to check if the parent node is root or a folder.
        // Else, operation should fail.

        String data = view.folderMenuDialog();
        if(data == null) // Operation has been cancelled by user.
            return;


        // Temporary. Should build the folder object.
        A folder = new A(data);
        try{
            view.addNodeToSelectedNode(folder);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select a node before creating a folder");
            return;
        }

        // Should add link in parent node to the newly created child.

        view.refreshTree();
        view.showPopup("Your folder " + data + " has been created");
        return;
    }

    /**
     * Creates an alias of the given node.
     */
    void create_alias(Object node){
        // We need to check if the node is a file and not something else.
        // Else, operation should fail.

        /* Temporary. Should build the alias object with
            the name of file followed by (alias). */
        A alias = new A(node + "(alias)");

        try{
            view.addNodeToParentNode(alias);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select a file before creating an alias.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while creating the alias");
            return;
        }

        // Should add link in parent node to the newly created child.

        view.refreshTree();
        // Requires object to have toString method
        view.showPopup("Your alias to " + node + " has been created");
        return;
    }

    /**
     * Creates a copy of the given object.
     */
    void create_copy(Object toCopy){
        // We need to check that the the given object is not the root node nor an alias.
        // Else, operation should fail.

        /* Temporary. Should called the adequate function built with the
            visitor pattern */
        A copied = new A(toCopy + "(copy)");

        try{
            view.addNodeToParentNode(copied);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select something to be copied.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while copying.");
            return;
        }

        // Should set all the links properly.

        view.refreshTree();
        // Requires object to have toString method
        view.showPopup("Your copy of " + toCopy + " has been created");
        return;
    }

    /**
     * Creates an archive of the given object.
     */
    void create_archive(Object toArchive){
        // We need to check that the given object is a folder (and not root node)
        // Else, operation should fail.

        String archiveName = view.displayArchiveWindow1();
        String archiveExtension = view.displayArchiveWindow2();
        int archiveCompression = view.displayArchiveWindow3();

        // Operation was cancelled by the user.
        if(archiveName == null || archiveExtension == null || archiveCompression == -1)
            return;

        /* Temporary. Should called the adequate function build with the
            visitor pattern */
        A archived = new A(archiveName + archiveExtension);

        try{
            view.addNodeToParentNode(archived);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select something to be copied.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while archiving");
            return;
        }

        view.refreshTree();
        view.showPopup("Archive of " + toArchive + " has been created");
        return;
    }
}