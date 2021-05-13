import montefiore.ulg.ac.be.graphics.*;

public class GuiHandler implements ExplorerEventsHandler {

	private ExplorerSwingView esv;
	public ViewManager viewManager;
	
    GuiHandler(String[] args) throws NullHandlerException {
        this.esv = new ExplorerSwingView(this);
		viewManager = ViewManager.getManager(esv);

        try {
        	// First step to do before anything !!! 
            this.esv.setRootNode(FolderCreator.getCreator().createEntity("root"));
        } catch (RootAlreadySetException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public void createAliasEvent(Object selectedNode) {
		viewManager.getTreeManipulator().create_alias(selectedNode);
	}

	@Override
	public void createArchiveEvent(Object selectedNode) {
		viewManager.getTreeManipulator().create_archive(selectedNode);
	}

	@Override
	public void createCopyEvent(Object selectedNode) {
		viewManager.getTreeManipulator().create_copy(selectedNode);
	}

	@Override
	public void createFileEvent(Object selectedNode) {
		viewManager.getTreeManipulator().create_file(selectedNode);
	}

	@Override
	public void createFolderEvent(Object selectedNode) {
		viewManager.getTreeManipulator().create_folder(selectedNode);
	}

	@Override
	public void doubleClickEvent(Object selectedNode) {
		viewManager.getTextManipulator().emptyText();
		Visitor v = new DisplayVisitor();
		Entity selectedEntity = (Entity) selectedNode;
		selectedEntity.accept(v);
	}

	@Override
	public void eventExit() {}
}
