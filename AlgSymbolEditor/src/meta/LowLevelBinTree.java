






//$$strtCprt
/**
* OEEE (Oversimplified [MathML] Expression Editor for Eclipse)
* 
* Copyright (C) 2016 Thornton Green
* 
* This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
* published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
* of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program; if not, 
* see <http://www.gnu.org/licenses>.
* Additional permission under GNU GPL version 3 section 7
*
*/
//$$endCprt






package meta;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;



/**
 *
 * --- SOURCE MODIFICATION LIST ---
 *
 * Please document all changes to this source file here.
 * Feel free to add rows if needed.
 *
 *
 *    |-----------------------|-------------------------------------------------|----------------------------------------------------------------------|---------------------------------------------------------------...
 *    | Date of Modification  |    Author of Modification                       |    Reason for Modification                                           |    Description of Modification (use multiple rows if needed)  ...
 *    |-----------------------|-------------------------------------------------|----------------------------------------------------------------------|---------------------------------------------------------------...
 *    |                       |                                                 |                                                                      |
 *    | 9/24/2000             | Thorn Green (viridian_1138@yahoo.com)           | Needed to provide a standard way to document source file changes.    | Added a souce modification list to the documentation so that changes to the souce could be recorded.
 *    | 10/22/2000            | Thorn Green (viridian_1138@yahoo.com)           | Methods did not have names that followed standard Java conventions.  | Performed a global modification to bring the names within spec.
 *    | 10/29/2000            | Thorn Green (viridian_1138@yahoo.com)           | Classes did not have names that followed standard Java conventions.  | Performed a global modification to bring the names within spec.
 *    | 08/12/2001            | Thorn Green (viridian_1138@yahoo.com)           | First-Cut at Error Handling.                                         | First-Cut at Error Handling.
 *    | 09/29/2001            | Thorn Green (viridian_1138@yahoo.com)           | Meta contained anachronisms from C++ that could hurt performance.    | Removed a number of the anachronisms.
 *    | 05/10/2002            | Thorn Green (viridian_1138@yahoo.com)           | Redundant information in persistent storage.                         | Made numerous persistence and packaging changes.
 *    | 08/07/2004            | Thorn Green (viridian_1138@yahoo.com)           | Establish baseline for all changes in the last year.                 | Establish baseline for all changes in the last year.
 *    | 10/13/2005            | Thorn Green (viridian_1138@yahoo.com)           | Update copyright.                                                    | Update copyright.
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *    |                       |                                                 |                                                                      |
 *
 *
 */

/**
 * LowLevelBinTree provides structure and code for the implementation of
 * {@link HighLevelBinTree}.  For more information, see {@link HighLevelBinTree}.  LowLevelBinTree
 * is abstract, but creates instances of {@link StdLowLevelBinTree}.  Other subclasses
 * of LowLevelBinTree can also be used.  The procedure for doing this is beyond the scope
 * of the description here.  One should not be directly creating LowLevelBinTree structures.
 * Instead, use {@link HighLevelBinTree} for typical applications in which a binary tree
 * is needed.
 * @author Thorn Green
 */
public abstract class LowLevelBinTree<U extends LowLevelBinTree, T extends Meta> extends LowLevelType<U,T> implements Externalizable {
	
	/**
	* Version number used to support versioned persistence.
	*/
	static final long serialVersionUID = (LowLevelBinTree.class).getName().hashCode() + "v3/98A".hashCode();
	
	@Override
    public abstract U copyNode();
	@Override
    public U copySub() {
        U ptr = (U) this;
        U end = listParent();
        U out;
        
        if (end == this) { throw( new UndefinedOperation() );
        }
        
        out = (U) ptr.copyNode();
        ptr.copyLeft(out);
        ptr = (U)( ptr.right() );
        
        while (ptr != end) {
            out.importAddRight((U) ptr.copyNode());
            out = (U)( out.right() );
            ptr.copyLeft(out);
            ptr = (U)( ptr.right() );
        }
        
        out = (U)( out.right() );
        return (out);
    };
    @Override
    public U copyAll() {
        U ptr = (U) this;
        U temp = (U)( ptr.listParent() );
        U out;
        
        while (temp != ptr) {
            ptr = temp;
            temp = (U)( ptr.listParent() );
        }
        
        out = (U) temp.copyNode();
        temp.copyLeft(out);
        temp = (U)( temp.right() );
        
        while (temp != ptr) {
            out.importAddRight((U) temp.copyNode());
            out = (U)( out.right() );
            temp.copyLeft(out);
            temp = (U)( temp.right() );
        }
        
        return (out);
    };
    @Override
    public void eraseNode() {
        if (lThread() == true) {
            U temp;
            U parent = listParent();
            U t = (U) this;
            if (parent.left() == this) {
                parent.dvSetLeft(t.right());
                temp = (U)( t.right() );
                while (temp.lThread() == false)
                    temp = (U)( temp.left() );
                temp.dvSetLeft(t.left());
            } else {
                if (rThread() == false) {
                    t.left().dvSetRight(t.right());
                    t = (U)( t.right() );
                    while (t.lThread() == false)
                        t = (U)( t.left() );
                    t.dvSetLeft(this.left());
                } else {
                    t.left().right().dvSetRight(t.right());
                    t.left().dvSetRThread(true);
                }
            }
            
            this.dispose();
        } else {
            throw( new UndefinedOperation() );
        }
    };
    @Override
    public void eraseSub() {
        U ptr;
        U end = findEnd();
        U delTemp;
        
        if (end != this)
            end = (U)( end.right() );
        else { throw( new UndefinedOperation() );
        }
        
        if (end.left() == this)
            end.pruneLeftComp();
        else {
            ptr = (U) this;
            while (!(ptr.lThread()))
                ptr = (U)( ptr.left() );
            ptr = (U)( ptr.left() );
            ptr.dvSetRight(end);
            ptr.dvSetRThread(true);
            
            ptr = (U) this;
            while (ptr != end) {
                ptr.pruneLeftComp();
                delTemp = ptr;
                ptr = (U)( ptr.right() );
                delTemp.dispose();
            }
        }
    };
    @Override
    public void eraseAll() {
        U ptr = (U) this;
        U temp = (U)( ptr.listParent() );
        U delTemp;
        
        while (temp != ptr) {
            ptr = temp;
            temp = (U)( ptr.listParent() );
        }
        
        temp = (U)( temp.right() );
        while (temp != ptr) {
            temp.pruneLeft();
            delTemp = temp;
            temp = (U)( temp.right() );
            delTemp.dispose();
        }
        
        ptr.pruneLeft();
        ptr.dispose();
    };
    @Override
    public void wake() {};
    /**
     * Returns the node to the right of this node.
     */
    public final U right() {
        return (right);
    };
    /**
     * Returns the node to the left of this node.
     */
    public final U left() {
        return (left);
    };
    /**
     * Returns true iff. the left-link is threaded.
     */
    public final boolean lThread() {
        return (lthread);
    };
    /**
     * Returns true iff. the right-link is threaded.
     */
    public final boolean rThread() {
        return (rthread);
    };
    /**
     * Initializes the binary tree.
     */
    public final void iLowLevelBinTree() {
        left = (U) this;
        right = (U) this;
        lthread = true;
        rthread = false;
    };
    public LowLevelBinTree() {
        super();
        this.iLowLevelBinTree();
    };
    /**
     * Prunes everything to the left of this node, using the EraseModes provided.
     */
    public final void pruneLeft() {
        if (!lthread)
            pruneLeftComp();
    };
    /**
     * Adds a node to the right of this one.
     */
    public final void addRight(T in) {
        if (rthread)
            addRightLeaf();
        else
            addRightComp();
        right.setNode(in);
    };
    /**
     * Adds a node to the left of this one.
     */
    public final void addLeft(T in) {
        if (lthread)
            addLeftLeaf();
        else
            addLeftComp();
        left.setNode(in);
    };
    /**
     * Adds a node to the right of this one.
     */
    public final void importAddRight(U in) {
        if (rthread)
            importAddRightLeaf(in);
        else
            importAddRightComp(in);
    };
    /**
     * Adds a node to the left of this one.
     */
    public final void importAddLeft(U in) {
        if (lthread)
            importAddLeftLeaf(in);
        else
            importAddLeftComp(in);
    };
    /**
     * Places a copy of the left subtree of this node in <code>out</code>.
     */
    public final void copyLeft(U out) {
        if (!lthread)
            copyLeftComp(out);
    };
    @Override
    public abstract T getNode();
    /**
     * Performs an inorder traversal, executing the Callback with each node visited.
     */
    public final void inOrder(U tStop, Callback<T> inClass) {
        U t = (U)( this );
        boolean done = false;
        
        while (!done)
            if (!(t.lThread())) {
            t = (U)( t.left() );
            } else {
            inClass.callback((T)(t.getNode()));
            while ((t.rThread()) && (!done)) {
                t = (U)( t.right() );
                inClass.callback((T)(t.getNode()));
                if (t == tStop)
                    done = true;
            }
            if (t == tStop)
                done = true;
            t = (U)( t.right() );
            }
    };
    /**
     * Performs a preorder traversal, executing the Callback<T> with each node visited.
     */
    public final void preOrder(U tStop, Callback<T> inClass) {
        U t = (U) this;
        
        while (tStop.rThread())
            tStop = (U)( tStop.right() );
        tStop = (U)( tStop.right() );
        while (t != tStop) {
            inClass.callback((T)(t.getNode()));
            if (t.lThread() == false)
                t = (U)( t.left() );
            else {
                while (t.rThread())
                    t = (U)( t.right() );
                t = (U)( t.right() );
            }
        }
    };
    
    /**
     * Performs a postorder traversal, executing the Callback<T> with each node visited.
     */
    public final void postOrder(U tStop, Callback<T> inClass) {
        /* Not Implemented Yet. */
    };
    /**
     * Copies this tree to the right of <code>out</code>.
     */
    public final void pasteRight(U out) {
        U src = (U) this;
        U dest = out;
        boolean done = false;
        
        while (!done) {
            dest.importAddRight((U) src.copyNode());
            dest = (U)( dest.right() );
            src.copyLeft(dest);
            src = (U)( src.right() );
            done = (src == this);
        }
    };
    /**
     * Copies this tree to the left of <code>out</code>.
     */
    public final void pasteLeft(U out) {
        U temp = (U) this;
        U ptr = out;
        ptr.importAddLeft((U) copyNode());
        ptr = (U)( ptr.left() );
        copyLeft(ptr);
        
        temp = (U)( temp.right() );
        while (temp != this) {
            ptr.importAddRight((U) temp.copyNode());
            ptr = (U)( ptr.right() );
            temp.copyLeft(ptr);
            temp = (U)( temp.right() );
        }
        
    };
    
    /**
     * Traverses to the right until a right-thread is found.  Then returns that node.
     */
    public final U findEnd() {
        U ptr = (U) this;
        
        if (!(ptr.rThread()))
            ptr = (U)( ptr.right() );
        
        while ((!(ptr.rThread())) && (ptr != this))
            ptr = (U)( ptr.right() );
        
        return (ptr);
    };
    /**
     * Disposes the current node.
     */
    public void dispose() {
        this.right = null;
        this.left = null;
    };
    /**
     * As if the binary tree is a representation of a generalized list, finds
     * the "parent" of the current node and returns it.
     */
    public final U listParent() {
        U ptr = (U) this;
        
        if (!(ptr.rThread())) {
            ptr = (U)( ptr.right() );
            while ((!(ptr.rThread())) && (ptr != this))
                ptr = (U)( ptr.right() );
            
            if (ptr != this)
                ptr = (U)( ptr.right() );
        } else
            ptr = (U)( ptr.right() );
        
        return (ptr);
    };
    
    /**
     * Connects this tree to the right of <code>out</code>.
     */
    public final void connectRight(U out) {
        U m = out;
        U s = (U) this;
        
        U sl = s;
        while (!(sl.lThread()))
            sl = (U)( sl.left() );
        
        U sr = (U)( sl.left() );
        
        sr.dvSetRight(m.right());
        sr.dvSetRThread(m.rThread());
        sl.dvSetLeft(m);
        sl.dvSetLThread(true);
        
        m.dvSetRight(s);
        m.dvSetRThread(false);
        
        if (!(sr.rThread())) {
            U temp = (U)( sr.right() );
            
            while (!(temp.lThread()))
                temp = (U)( temp.left() );
            
            temp.dvSetLeft(sr);
        }
    }
    
    /**
     * Connects this tree to the left of <code>out</code>.
     */
    public final void connectLeft(U out) {
        U m = out;
        U s = (U) this;
        
        U sl = s;
        while (!(sl.lThread()))
            sl = (U)( sl.left() );
        
        U sr = (U)( sl.left() );
        
        sr.dvSetRight(m);
        sr.dvSetRThread(true);
        sl.dvSetLeft(m.left());
        sl.dvSetLThread(m.lThread());
        
        m.dvSetLeft(s);
        m.dvSetLThread(false);
        
        if (!(sl.lThread())) {
            U temp = (U)( sl.left() );
            
            while (!(temp.rThread()))
                temp = (U)( temp.right() );
            
            temp.dvSetRight(sl);
        }
    }
    
    private U right;
    private U left;
    private boolean lthread;
    private boolean rthread;
    final void dvSetRight(U in) {
        right = in;
    }
    final void dvSetLeft(U in) {
        left = in;
    }
    final void dvSetLThread(boolean in) {
        lthread = in;
    }
    final void dvSetRThread(boolean in) {
        rthread = in;
    }
    private final void addRightLeaf() {
        U ptr = (U)( new StdLowLevelBinTree<T>() );
        ptr.dvSetLeft(this);
        ptr.dvSetLThread(true);
        ptr.dvSetRight(right);
        ptr.dvSetRThread(rthread);
        right = ptr;
        rthread = false;
    };
    private final void addLeftLeaf() {
        U ptr = (U)( new StdLowLevelBinTree<T>() );
        ptr.dvSetRight(this);
        ptr.dvSetRThread(true);
        ptr.dvSetLeft(left);
        ptr.dvSetLThread(lthread);
        left = ptr;
        lthread = false;
    };
    private final void addRightComp() {
        U nextT;
        U newT;
        
        nextT = right;
        while (nextT.lThread() != true)
            nextT = (U)( nextT.left() );
        newT = (U)( new StdLowLevelBinTree<T>() );
        newT.dvSetRight(right);
        newT.dvSetRThread(rthread);
        right = newT;
        newT.dvSetLThread(true);
        newT.dvSetLeft(nextT.left());
        nextT.dvSetLeft(newT);
    };
    private final void addLeftComp() {
        U ptr = (U)( new StdLowLevelBinTree<T>() );
        U temp = this.left();
        while (!(temp.rThread()))
            temp = (U)( temp.right() );
        ptr.dvSetLeft(this.left);
        ptr.dvSetLThread(false);
        ptr.dvSetRight(temp.right());
        ptr.dvSetRThread(true);
        temp.dvSetRight(ptr);
        this.left = ptr;
    };
    private final void importAddRightLeaf(U in) {
        U ptr = in;
        ptr.dvSetLeft(this);
        ptr.dvSetLThread(true);
        ptr.dvSetRight(right);
        ptr.dvSetRThread(rthread);
        right = ptr;
        rthread = false;
    };
    private final void importAddLeftLeaf(U in) {
        U ptr = in;
        ptr.dvSetRight(this);
        ptr.dvSetRThread(true);
        ptr.dvSetLeft(left);
        ptr.dvSetLThread(lthread);
        left = ptr;
        lthread = false;
    };
    private final void importAddRightComp(U in) {
        U nextT;
        U newT;
        
        nextT = right;
        while (nextT.lThread() != true)
            nextT = (U)( nextT.left() );
        newT = in;
        newT.dvSetRight(right);
        newT.dvSetRThread(rthread);
        right = newT;
        newT.dvSetLThread(true);
        newT.dvSetLeft(nextT.left());
        nextT.dvSetLeft(newT);
    };
    private final void importAddLeftComp(U in) {
        U ptr = in;
        U temp = this.left;
        while (!(temp.rThread()))
            temp = (U)( temp.right() );
        ptr.dvSetLeft(this.left);
        ptr.dvSetLThread(false);
        ptr.dvSetRight(temp.right());
        ptr.dvSetRThread(true);
        temp.dvSetRight(ptr);
        this.left = ptr;
    };
    final void pruneLeftComp() {
        U temp;
        U ptr;
        U delTemp;
        
        temp = (U) this;
        while (!(temp.lThread()))
            temp = (U)( temp.left() );
        temp = (U)( temp.left() );
        
        ptr = this.left;
        while (ptr != this) {
            while (!(ptr.lThread()))
                ptr = (U)( ptr.left() );
            
            while ((ptr.rThread()) && (ptr.right() != this)) {
                delTemp = ptr;
                ptr = (U)( ptr.right() );
                delTemp.dispose();
            }
            
            delTemp = ptr;
            ptr = (U)( ptr.right() );
            delTemp.dispose();
        }
        
        lthread = true;
        left = temp;
    };
    private final void copyLeftComp(U dest) {
        U t = this.left;
        dest.importAddLeft((U) t.copyNode());
        dest = (U)( dest.left() );
        while (t != this) {
            while (!(t.lThread())) {
                t = (U)( t.left() );
                dest.importAddLeft((U) t.copyNode());
                dest = (U)( dest.left() );
            }
            while ((t.rThread()) && (t.right() != this)) {
                t = (U)( t.right() );
                dest = (U)( dest.right() );
            }
            t = (U)( t.right() );
            if (t != this) {
                dest.importAddRight((U) t.copyNode());
                dest = (U)( dest.right() );
            }
        }
    };
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        
        try {
            VersionBuffer myv = (VersionBuffer) (in.readObject());
            VersionBuffer.chkNul(myv);
            left = (U) (myv.getProperty("Left"));
            right = (U) (myv.getProperty("Right"));
            lthread = myv.getBoolean("lthread");
            rthread = myv.getBoolean("rthread");
        } catch (ClassCastException e) {
            throw (new DataFormatException(e));
        }
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        VersionBuffer myv = new VersionBuffer(VersionBuffer.WRITE);
        
        super.writeExternal(out);
        if (left != null)
            myv.setProperty("Left", left);
        if (right != null)
            myv.setProperty("Right", right);
        myv.setBoolean("lthread", lthread);
        myv.setBoolean("rthread", rthread);
        out.writeObject(myv);
    }
    
};
