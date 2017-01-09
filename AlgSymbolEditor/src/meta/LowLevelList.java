






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
 *    | 09/06/2002            | Thorn Green (viridian_1138@yahoo.com)           | Needed to add a method to improve the BlueToh solver.                | Added the method.
 *    | 08/07/2004            | Thorn Green (viridian_1138@yahoo.com)           | Establish baseline for all changes in the last year.                 | Establish baseline for all changes in the last year.
 *    | 03/21/2005            | Thorn Green (viridian_1138@yahoo.com)           | Improve Performance.                                                 | Added final keyword to insert methods.
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
 * LowLevelList provides structure and code for the implementation of
 * {@link HighLevelList}.  For more information, see {@link HighLevelList}.  LowLevelList
 * is abstract, but creates instances of {@link StdLowLevelList}.  Other subclasses
 * of LowLevelList can also be used.  The procedure for doing this is beyond the scope
 * of the description here.  One should not be directly creating LowLevelList structures.
 * Instead, use {@link HighLevelList} for typical applications in which a linked list
 * is needed.
 * @author Thorn Green
 * @param <T>
 */
public abstract class LowLevelList<U extends LowLevelList, T extends Meta> extends LowLevelType<U,T> implements Externalizable {
	
	/**
	* Version number used to support versioned persistence.
	*/
	static final long serialVersionUID = (LowLevelList.class).getName().hashCode() + "v3/98A".hashCode();
	
	@Override
    public abstract  U copyNode();
	@Override
    public U copySub() {
        U inCopy = (U) this;
        U outCopy = null;
        U temp = null;
        
        outCopy = (U) inCopy.copyNode();
        
        while (!(inCopy.right().getHead())) {
            inCopy = (U)inCopy.right();
            temp = (U) inCopy.copyNode();
            temp.setHead(false);
            outCopy.insRight(temp);
            outCopy = (U)outCopy.right();
        }
        
        return ((U)outCopy.right());
    };
    @Override
    public U copyAll() {
        U inCopy = (U) this;
        U stCopy = (U) inCopy.searchHead();
        U goCopy = stCopy;
        U outCopy = null;
        U temp = null;
        
        outCopy = (U) goCopy.copyNode();
        
        while (goCopy.right() != stCopy) {
            goCopy = (U) goCopy.right();
            temp = (U) goCopy.copyNode();
            temp.setHead(false);
            outCopy.insRight(temp);
            outCopy = (U) outCopy.right();
        }
        
        while (goCopy != inCopy) {
            goCopy = (U) goCopy.right();
            outCopy = (U) outCopy.right();
        }
        
        return (outCopy);
    };
    @Override
    public void eraseNode() {
        if (this.getHead())
            this.right().setHead(true);
        this.left().dvSetRight(this.right());
        this.right().dvSetLeft(this.left());
        this.dispose();
    };
    @Override
    public void eraseSub() {
        U temp = (U) this;
        U next;
        
        if (this.getHead())
            this.eraseAll();
        else {
            while (!temp.getHead()) {
                next = (U) temp.right();
                temp.disconnect();
                temp.dispose();
                temp = next;
            }
        }
    };
    @Override
    public void eraseAll() {
        U next;
        U temp = this.right();
        
        while (temp != this) {
            next = (U) temp.right();
            temp.dispose();
            temp = next;
        }
        
        this.dispose();
    };
    @Override
    public void wake() {};
    /**
     * Returns the node to the right of this node.
     */
    public final U right() {
        return (this.right);
    };
    /**
     * Returns the node to the left of this node.
     */
    public final U left() {
        return (this.left);
    };
    /**
     * Initializes the list.
     */
    public final void iLowLevelList() {
        this.setHead(true);
        this.dvSetRight( (U) this);
        this.left = (U) this;
    };
    public LowLevelList() {
        super();
        this.iLowLevelList();
    };
    /**
     * Inserts the data <code>in</code> to the left of the node
     * on which this method is called.
     */
    public final void insertLeft(final T in) {
        final U temp = (U)( new StdLowLevelList<T>() );
        temp.setHead(this.head);
        this.head = false;
        temp.setNode(in);
        this.insLeft(temp);
    };
    /**
     * Inserts the data <code>in</code> to the right of the node
     * on which this method is called.
     */
    public final void insertRight(final T in) {
        final U temp = (U)( new StdLowLevelList<T>() );
        temp.setHead(false);
        temp.setNode(in);
        this.insRight(temp);
    };
    /**
     * Inserts the node <code>in</code> to the left of the node
     * on which this method is called.
     */
    public final void importInsertLeft(final U in) {
        final U temp = in;
        temp.setHead(this.head);
        this.head = false;
        this.insLeft(temp);
    };
    /**
     * Inserts the node <code>in</code> to the left of the node
     * on which this method is called.
     */
    public final void importInsertRight(final U in) {
        final U temp = in;
        temp.setHead(false);
        this.insRight(temp);
    };
    /**
     * Disconnects this node from its neighbors.
     */
    public final void disconnect() {
        if (this.getHead())
            this.right().setHead(true);
        this.left().dvSetRight(this.right());
        this.right().dvSetLeft(this.left());
        this.right = (U) this;
        this.left = (U) this;
        this.head = true;
    };
    
    /**
     * Disposes the node.
     */
    public void dispose() {
        this.right = null;
        this.left = null;
    };
    @Override
    public abstract T getNode();
    /**
     * Gets whether this node is the head.
     */
    public final boolean getHead() {
        return (this.head);
    };
    /**
     * Sets whether this node is the head.
     */
    public final void setHead(final boolean inHead) {
        this.head = inHead;
    };
    /**
     * Returns the head of the list.
     */
    public final U searchHead() {
        U temp = (U) this;
        while (!(temp.getHead())) {
            temp = (U) temp.right();
        }
        return (temp);
    };
    
    /**
     * Returns whether the list is a single-node list.
     */
    public boolean isSingleNode() {
        return (this == this.right());
    }
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        
        try {
            VersionBuffer myv = (VersionBuffer) (in.readObject());
            VersionBuffer.chkNul(myv);
        } catch (ClassCastException e) {
            throw (new DataFormatException(e));
        }
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        VersionBuffer myv = new VersionBuffer(VersionBuffer.WRITE);
        
        super.writeExternal(out);
        out.writeObject(myv);
    }
    
    private transient boolean head;
    private transient U right;
    private transient U left;
    final void dvSetRight(final U in) {
        right = in;
    }
    final void dvSetLeft(final U in) {
        left = in;
    }
    final void insRight(final U temp) {
        temp.dvSetRight(this.right());
        temp.dvSetLeft(this);
        this.right().dvSetLeft(temp);
        this.right = temp;
    };
    private final void insLeft(final U temp) {
        temp.dvSetLeft(this.left);
        temp.dvSetRight(this);
        this.left().dvSetRight(temp);
        this.left = temp;
    };
};
