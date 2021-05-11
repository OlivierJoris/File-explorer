import montefiore.ulg.ac.be.graphics.*;

public class GuiHandler implements ExplorerEventsHandler {

	private ExplorerSwingView esv;
	public ViewManager viewManager;
	
    GuiHandler(String[] args) throws NullHandlerException {
        this.esv = new ExplorerSwingView(this);
		viewManager = new ViewManager(esv);
        
        try {
        	// First step to do before anything !!! 
            this.esv.setRootNode(new A("root")); // set the root node with a silly "A" object
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
		// Temporary
		viewManager.getTextManipulator().emptyText();
		viewManager.getTextManipulator().putString("Maxime");
		viewManager.getTextManipulator().appendString("Test");
		viewManager.getTextManipulator().putString("append");

		String array[] = new String[3];
		array[0] = "INFO-0027";
		array[1] = "Techniques";
		array[2] = "2020-2021";
		viewManager.getTextManipulator().putStrings(array);

		//viewManager.getTextManipulator().setString("What else?");
	}

	@Override
	public void eventExit() {
		// TODO Auto-generated method stub
	}
}
