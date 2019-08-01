package at.dalex.grape.sdk.scene.node;

import at.dalex.grape.sdk.resource.ResourceLoader;
import at.dalex.grape.sdk.window.viewport.ViewportCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the foundation for every node usable in the editor.
 */
public abstract class NodeBase implements Serializable {

    private String title;
    private String treeIconStorageKey;

    private ArrayList<NodeBase> children = new ArrayList<>();

    /**
     * Create a new {@link NodeBase} using a given title.
     * This title will be shown in the scene's node list.
     *
     * The title vibible in the editor's node-creation window
     * is parsed from the corresponding json-file!
     *
     * @param title The title of this node
     * @param treeIconStorageKey The icon of the node in the tree,
     *                           in form of a storage key.
     */
    public NodeBase(String title, String treeIconStorageKey) {
        this.title = title;
        this.treeIconStorageKey = treeIconStorageKey;
    }

    /**
     * Every node must be able to draw itself (onto the viewport).
     * @param g The {@link GraphicsContext} from the {@link at.dalex.grape.sdk.window.viewport.ViewportCanvas}
     */
    public abstract void draw(ViewportCanvas canvas, GraphicsContext g);

    /**
     * @return The title of this node visible in the scene's node list.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return The {@link Image} of this node visible in the scene's node list.
     */
    public Image getTreeIcon() {
        return ResourceLoader.get(this.treeIconStorageKey, Image.class);
    }

    /**
     * Sets the title of the node visible in the scene's node list.
     *
     * Whitespaces and tabs will be removed.
     * If the given title only consists of whitespaces or tabs,
     * the given title will <b>NOT</b> be set!
     *
     * @param title The new title
     */
    public void setTitle(String title) {
        String trimmedTitle = title.trim();
        this.title = trimmedTitle.length() > 0 ? trimmedTitle : this.title;
    }

    /**
     * @return The child {@link NodeBase}(s) that this node contains
     */
    public ArrayList<NodeBase> getChildren() {
        return this.children;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
