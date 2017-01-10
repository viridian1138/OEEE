






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
 *    | 03/21/2005            | Thorn Green (viridian_1138@yahoo.com)           | Improve Insert Performance                                           | Added final keywords for inserts.
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
 * StdLowLevelList is the standard LowLevelList instance created by LowLevelList.
 * For more information, see {@link LowLevelList}.  This is an internal structure
 * to LowLevelList, and you should not be using it directly unless you are making
 * modifications to {@link LowLevelList} or {@link HighLevelList}.
 * This class is public only to make it serializable.
 * @author Thorn Green
 */
public class StdLowLevelList<T extends Meta> extends LowLevelList<StdLowLevelList<T>,T> implements Externalizable {
	
	/**
	* Version number used to support versioned persistence.
	*/
	static final long serialVersionUID = (StdLowLevelList.class).getName().hashCode() + "v3/98A".hashCode();
	
    @Override
    public T getNode() {
        return (this.data);
    };
    @Override
    public void setNode(final T input) {
        this.data = input;
    };
    @Override
    public void setCopyMode(final int copy) {
        this.thisCopyMode = copy;
    };
    @Override
    public int getCopyMode() {
        return (this.thisCopyMode);
    };
    @Override
    public void setCopyInfoMode(final int copy) { throw( new UndefinedOperation() );
    };
    @Override
    public int getCopyInfoMode() { throw( new UndefinedOperation() );
    };
    
    @Override
    public StdLowLevelList<T> copyNode() {
    	StdLowLevelList<T> temp = new StdLowLevelList<T>();
        this.copyDat(temp);
        temp.setHead(true);
        return (temp);
    };
    public StdLowLevelList() {
        super();
    };
    @Override
    public void dispose() {
        this.eraseDat();
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
    
    private final void dvSetNode(T in) {
        data = in;
    }
    private final void dvSetCopyMode(int in) {
        thisCopyMode = in;
    }
    
    /**
     * The stored data.
     */
    protected T data = null;
    /**
     * The CopyMode.
     */
    protected int thisCopyMode = Meta.COPY_DO_NOTHING;
    
    /**
     * Copies the stored data according to the current mode, and places the result
     * in <code>input</code>.
     */
    protected void copyDat(final StdLowLevelList<T> input) {
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
};
