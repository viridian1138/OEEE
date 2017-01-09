






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
import java.io.Externalizable;



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
 * This is a {@link LowLevelList} node containing a {@link HighLevelList}.  This is useful if one
 * wishes to create a list of lists (perhaps for a simple sparse matrix).
 * @author Thorn Green
 */
public abstract class AltLowLevelList<U extends AltLowLevelList, T extends Meta> extends LowLevelList<U,T> implements Externalizable {
	
	/**
	* Version number used to support versioned persistence.
	*/
	static final long serialVersionUID = (AltLowLevelList.class).getName().hashCode() + "v3/98A".hashCode();
	
    @Override
    public T getNode() {
        return (myRec);
    };
    /**
     * This is an undefined operation.  Do not use.
     */
    public void setNode(T input) { throw( new UndefinedOperation() );
    };
    /**
     * This is an undefined operation.  Do not use.
     */
    public void setCopyMode(int copy) { throw( new UndefinedOperation() );
    };
    /**
     * This is an undefined operation.  Do not use.
     */
    public int getCopyMode() { throw( new UndefinedOperation() );
    };
    /**
     * Sets the CopyInfoMode for this node.
     */
    public void setCopyInfoMode(int copy) {
        this.copyInfoMode = copy;
    };
    /**
     * Gets the CopyInfoMode for this node.
     */
    public int getCopyInfoMode() {
        return (this.copyInfoMode);
    };
    /**
     * Initializes the structure.
     */
    public void iAltHigh() {
        this.copyInfoMode = Meta.COPY_DATA_INFO;
    };
    public AltLowLevelList() {
        super();
        this.iAltHigh();
    };
    /**
     * Disposes the structure according to the current EraseMode.
     */
    public void dispose() {
        this.eraseDat();
    };
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        
        try {
            VersionBuffer myv = (VersionBuffer) (in.readObject());
            VersionBuffer.chkNul(myv);
            
            T arec = (T) (myv.getProperty("MyRec"));
            arec.copyAllInfo( myRec );
            VersionBuffer.chkNul(myRec);
            copyInfoMode = myv.getInt("ThisCopyInfoMode");
        } catch (ClassCastException e) {
            throw (new DataFormatException(e));
        }
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        VersionBuffer myv = new VersionBuffer(VersionBuffer.WRITE);
        
        myv.setProperty("MyRec", myRec);
        myv.setInt("ThisCopyInfoMode", copyInfoMode);
        
        super.writeExternal(out);
        out.writeObject(myv);
    }
    
    protected abstract T construct();
    
    /**
     * The HighLevelList stored in the node.
     */
    protected final T myRec = construct();
    /**
     * The CopyInfoMode for the node.
     */
    protected int copyInfoMode;
    /**
     * Copies to the parameter <code>input</code> using the current CopyInfoMode.
     */
    protected void copyDat(U input) {
        if (copyInfoMode != Meta.COPY_DATA_INFO)
            myRec.exeCopyInfo(copyInfoMode, input.dvGetMyRec());
        
                /* For future exception handling purposes, it's very important that things happen
                        in this order. */
        
        input.dvSetCopyInfoMode(copyInfoMode);
        input.dvSetEraseMode(eraseMode);
    };
    
    final T dvGetMyRec() {
        return (myRec);
    }
    final void dvSetCopyInfoMode(int in) {
        copyInfoMode = in;
    }
    final void dvSetEraseMode(int in) {
        eraseMode = in;
    }
    
};
