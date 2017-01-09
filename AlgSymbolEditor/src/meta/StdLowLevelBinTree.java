






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
 * StdLowLevelBinTree is the standard LowLevelBinTree instance created by LowLevelBinTree.
 * For more information, see {@link LowLevelBinTree}.  This is an internal structure
 * to LowLevelBinTree, and you should not be using it directly unless you are making
 * modifications to {@link LowLevelBinTree} or {@link HighLevelBinTree}.
 * This class is public only to make it serializable (at some future point).
 * @author Thorn Green
 */
public class StdLowLevelBinTree<T extends Meta> extends LowLevelBinTree<StdLowLevelBinTree<T>,T> {
	
	/**
	* Version number used to support versioned persistence.
	*/
	static final long serialVersionUID = (StdLowLevelBinTree.class).getName().hashCode() + "v3/98A".hashCode();
	
    @Override
    public T getNode() {
        return (this.data);
    };
    /**
     * Sets the object stored in the node.
     */
    public void setNode(T input) {
        this.data = input;
    };
    /**
     * Sets the CopyMode for the node.
     */
    public void setCopyMode(int copy) {
        this.thisCopyMode = copy;
    };
    /**
     * Gets the CopyMode for the node.
     */
    public int getCopyMode() {
        return (this.thisCopyMode);
    };
    /**
     * Sets the CopyInfoMode for the node.
     */
    public void setCopyInfoMode(int copy) { throw( new UndefinedOperation() );
    };
    /**
     * Gets the CopyInfoMode for the node.
     */
    public int getCopyInfoMode() { throw( new UndefinedOperation() );
    };
    
    @Override
    public StdLowLevelBinTree<T> copyNode() {
        StdLowLevelBinTree<T> out = new StdLowLevelBinTree<T>();
        copyDat(out);
        return (out);
    };
    /**
     * Initializes the node.
     */
    public final void iStdLow() {
        this.thisCopyMode = Meta.COPY_DO_NOTHING;
        this.data = null;
    };
    public StdLowLevelBinTree() {
        super();
        this.iStdLow();
    };
    /**
     * Erases the node according to the current delete mode.
     */
    public void dispose() {
        this.eraseDat();
    };
    
    private final void dvSetNode(T in) {
        data = in;
    }
    private final void dvSetCopyMode(int in) {
        thisCopyMode = in;
    }
    
    /**
     * The stored data.
     */
    protected T data;
    /**
     * The CopyMode.
     */
    protected int thisCopyMode;
    /**
     * Copies the stored data according to the current mode, and places the result
     * in <code>input</code>.
     */
    protected void copyDat(StdLowLevelBinTree<T> input) {
        try {
            if (thisCopyMode != Meta.COPY_DO_NOTHING)
                input.dvSetNode((T)(data.exeCopy(thisCopyMode)));
            else
                input.dvSetNode(data);
        } catch (OutOfMemoryError ex) {
            if (getNode() != null)
                eraseDat();
        }
        
        input.dvSetCopyMode(thisCopyMode);
        input.setEraseMode(eraseMode);
    };
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        
        try {
            VersionBuffer myv = (VersionBuffer) (in.readObject());
            VersionBuffer.chkNul(myv);
            
            data = (T) (myv.getProperty("data"));
            thisCopyMode = myv.getInt("ThisCopyMode");
        } catch (ClassCastException e) {
            throw (new DataFormatException(e));
        }
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        VersionBuffer myv = new VersionBuffer(VersionBuffer.WRITE);
        
        if (data != null)
            myv.setProperty("data", data);
        myv.setInt("ThisCopyMode", thisCopyMode);
        
        super.writeExternal(out);
        out.writeObject(myv);
    }
    
};
