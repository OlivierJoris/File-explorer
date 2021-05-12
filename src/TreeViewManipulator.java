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

        String[] data = view.fileMenuDialog();
        if(data == null) // Operation has been cancelled by user.
            return;

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

        String folderName = view.folderMenuDialog();
        if(folderName == null) // Operation has been cancelled by user.
            return;


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

        Entity alias = AliasCreator.getCreator().createEntity(file.getName() + "(alias)", file);

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

        Entity copied = null;
        String copiedName = toCopyEntity.getName() + "(copy)";
        if(toCopy instanceof File){
            Visitor v = new CopyVisitor();
            File f = (File) toCopy;
            f.accept(v);
        }else if(toCopy instanceof Folder){
            Visitor v = new CopyVisitor();
            Folder f = (Folder) toCopy;
            f.accept(v);
        }else if(toCopy instanceof Archive){
            copied = ArchiveCreator.getCreator().createEntity(copiedName);
            // Should call the adequate function built with the visitor pattern

            // Temporary
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
        Entity toArchiveEntity = (Entity) toArchive;
        if((toArchive instanceof Folder) && (toArchiveEntity.isRoot())){
            view.showPopupError("You can't compress the root");
            return;
        }

        String archiveName = view.displayArchiveWindow1();
        String archiveExtension = view.displayArchiveWindow2();
        int archiveCompression = view.displayArchiveWindow3();

        // Operation was cancelled by the user.
        if(archiveName == null || archiveExtension == null || archiveCompression == -1)
            return;

        Entity archived = ArchiveCreator.getCreator().createEntity(
            archiveName,
            archiveExtension,
            archiveCompression
        );
        // Should call the adequate function built with the visitor pattern

        try{
            view.addNodeToParentNode(archived);
        }catch(NoSelectedNodeException noNode){
            view.showPopupError("You need to select something to be copied.");
            return;
        }catch(NoParentNodeException noParent){
            view.showPopupError("Issue while archiving");
            return;
        }

        // Sets parent for new node and adds new node as child for parent
        Entity parent = toArchiveEntity.getParent();
        archived.setParent(parent);
        parent.addChild(archived);

        view.refreshTree();
        view.showPopup("Your archive of " + toArchive + " has been created");
        return;
    }
}