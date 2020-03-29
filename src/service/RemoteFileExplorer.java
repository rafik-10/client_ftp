package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.io.IOException;

/**
 * @author Alexander Bolte - Bolte Consulting (2010 - 2014).
 *
 *         This class shall be a simple implementation of a TreeItem for
 *         displaying a file system tree.
 *
 *         The idea for this class is taken from the Oracle API docs found at
 *         http
 *         ://docs.oracle.com/javafx/2/api/javafx/scene/control/TreeItem.html.
 *
 *         Basically the file sytsem will only be inspected once. If it changes
 *         during runtime the whole tree would have to be rebuild. Event
 *         handling is not provided in this implementation.
 */
public class RemoteFileExplorer extends TreeItem<String> {

    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;
    private boolean isLeaf;

    private SimpleFTP ftp;

    /**
     * Calling the constructor of super class in oder to create a new
     * TreeItem<String>.
     *
     * @param path
     *            an object of type String from which a tree should be build or
     *            which children should be gotten.
     */
    public RemoteFileExplorer(SimpleFTP ftp, String path) {
        super(path);
        this.ftp=ftp;
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.scene.control.TreeItem#getChildren()
     */
    @Override
    public ObservableList<TreeItem<String>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;

            /*
             * First getChildren() call, so we actually go off and determine the
             * children of the File contained in this TreeItem.
             */
            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.scene.control.TreeItem#isLeaf()
     */
    @Override
    public boolean isLeaf() {
        if (isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
            String path = (String) getValue();
            isLeaf = isFile(path);
        }

        return isLeaf;
    }

    /**
     * Returning a collection of type ObservableList containing TreeItems, which
     * represent all children available in handed TreeItem.
     *
     * @param TreeItem
     *            the root node from which children a collection of TreeItem
     *            should be created.
     * @return an ObservableList<TreeItem<File>> containing TreeItems, which
     *         represent all children available in handed TreeItem. If the
     *         handed TreeItem is a leaf, an empty list is returned.
     */
    private ObservableList<TreeItem<String>> buildChildren(TreeItem<String> TreeItem) {
        String  path = TreeItem.getValue();
        if (path != null && isDirectory(path)) {
            String[] pathes = listPathes(path);
            if (pathes != null) {
                ObservableList<TreeItem<String>> children = FXCollections
                        .observableArrayList();

                for (String childPath : pathes) {
                    children.add(new RemoteFileExplorer(ftp,childPath));
                }

                return children;
            }
        }

        return FXCollections.emptyObservableList();
    }

    private boolean isFile(String path){
        if(path.startsWith("-")) return true;
        return false;
    }

    private boolean isDirectory(String path){
        if(path.startsWith("d") || path.startsWith("/")) return true;
        return false;
    }

    private String[] listPathes(String dirPath){
        String [] pathesList ;
        try {
            ftp.cwd(getFolderNameFromPath(dirPath));
            pathesList= ftp.list().split("\n");
            return pathesList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    private String getFolderNameFromPath(String dirPath)
    {
        String [] strs = dirPath.split(" ");
        return "/"+strs[strs.length-1];
    }

}