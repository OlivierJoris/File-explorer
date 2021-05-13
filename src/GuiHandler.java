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
		viewManager.getTreeManipulator().createAlias(selectedNode);
	}

	@Override
	public void createArchiveEvent(Object selectedNode) {
		viewManager.getTreeManipulator().createArchive(selectedNode);
	}

	@Override
	public void createCopyEvent(Object selectedNode) {
		viewManager.getTreeManipulator().createCopy(selectedNode);
	}

	@Override
	public void createFileEvent(Object selectedNode) {
		viewManager.getTreeManipulator().createFile(selectedNode);
	}

	@Override
	public void createFolderEvent(Object selectedNode) {
		viewManager.getTreeManipulator().createFolder(selectedNode);
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
