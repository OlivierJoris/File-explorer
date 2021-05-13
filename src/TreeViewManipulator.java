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
     * Returns the associated ExplorerSwingView.
     */
    public ExplorerSwingView getView(){
        return view;
    }

    /**
     * Creates a file as the child of the given node.
     */
    void create_file(Object parentNode){
        if(parentNode instanceof File){
            view.showPopupError("You can't create a file from another file");
            return;
        }
        if(parentNode instanceof Alias){
            view.showPopupError("You can't create a file from an alias");
            return;
        }
        if(parentNode instanceof Archive){
            view.showPopupError("You can't create a file from an archive");
            return;
        }
        if(((Entity)parentNode).isCopy){
            view.showPopupError("You can't create a file from a folder that was copied");
            return;
        }


        // Gets info about the new file
        String[] data = view.fileMenuDialog();
        if(data == null) // Operation has been cancelled by user.
            return;

        // Creates the file & adds to view
        Entity file = FileCreator.getCreator().createEntity(data[0], data[1]);
        try{
            view.addNodeToSelectedNode(file);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select a node before creating a file");
            return;
        }

        // Sets parent for new node and adds new node as child for parent
        Entity parentEntity = (Entity) parentNode;
        file.setParent(parentEntity);
        parentEntity.addChild(file);

        view.refreshTree();
        view.showPopup("Your file " + file + " has been created");
        return;
    }

    /**
     * Creates a folder as the child of the given node.
     */
    void create_folder(Object parentNode){
        if(!(parentNode instanceof Folder)){
            view.showPopupError("You can create a folder only from another folder");
            return;
        }
        if(((Entity)parentNode).isCopy){
            view.showPopupError("You can't create a folder from a folder that was copied");
            return;
        }

        // Gets folder's name
        String folderName = view.folderMenuDialog();
        if(folderName == null) // Operation has been cancelled by user.
            return;

        // Creates folder & adds to view
        Entity folder = FolderCreator.getCreator().createEntity(folderName);
        try{
            view.addNodeToSelectedNode(folder);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select a node before creating a folder");
            return;
        }

        // Sets parent for new node and adds new node as child for parent
        Entity parentEntity = (Entity) parentNode;
        folder.setParent(parentEntity);
        parentEntity.addChild(folder);

        view.refreshTree();
        view.showPopup("Your folder " + folder + " has been created");
        return;
    }

    /**
     * Creates an alias of the given node.
     */
    void create_alias(Object node){
        if(!(node instanceof File)){
            view.showPopupError("You can create an alias only from a file");
            return;
        }

        File file = (File) node;
        if(file.isCopy){
            view.showPopupError("You can't create an alias to a file that was copied");
            return;
        }
        
        // Creates alias
        Entity alias = AliasCreator.getCreator().createEntity(file.getName() + "(alias)", file);
        // Adds to view
        try{
            view.addNodeToParentNode(alias);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select a file before creating an alias.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while creating the alias");
            return;
        }

        // Sets parent for new node and adds new node as child for parent
        Entity parent = file.getParent();
        alias.setParent(parent);
        parent.addChild(alias);

        view.refreshTree();
        view.showPopup("Your alias to " + file + " has been created");
        return;
    }

    /**
     * Creates a copy of the given object.
     */
    void create_copy(Object toCopy){
        Entity toCopyEntity = (Entity) toCopy;

        if((toCopy instanceof Folder) && toCopyEntity.isRoot()){
            view.showPopupError("You can't copy the root");
            return;
        }
        if(toCopy instanceof Alias){
            view.showPopupError("You can't copy an alias");
            return;
        }

        Visitor v = new CopyVisitor();
        toCopyEntity.accept(v);

        view.refreshTree();
        view.showPopup("Your copy of " + toCopy + " has been created");
        return;
    }

    /**
     * Creates an archive of the given object.
     */
    void create_archive(Object toArchive){
        if(!(toArchive instanceof Folder)){
            view.showPopupError("You can only compress a folder");
            return;
        }
        Folder toArchiveFolder = (Folder) toArchive;
        if((toArchive instanceof Folder) && (toArchiveFolder.isRoot())){
            view.showPopupError("You can't compress the root");
            return;
        }
        if(toArchiveFolder.isCopy){
            view.showPopupError("You can't create an archive from a folder that was copied");
            return;
        }

        Visitor v = new ArchiveBuilderVisitor();
        toArchiveFolder.accept(v);

        view.refreshTree();
        view.showPopup("Your archive of " + toArchive + " has been created");
        return;
    }
}
