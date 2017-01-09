






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
 * HighLevelList implements abstract linked lists which can
 * be used with any Meta-compliant datatype.  It is perhaps the most heavliy used of any
 * Meta class.  Both standard and Alt nodes are supported by
 * the data structure.  The information in each node is treated as a reference to Meta, and
 * to get it back to its original type one must use a cast or an instanceof operator.<P>
 * HighLevelList structures are built out of linked collections of
 * {@link LowLevelList} nodes.  The {@link LowLevelList} nodes implement many of the features
 * supported by HighLevelList.<P>
 * @author Thorn Green
 */
public class HighLevelList<U extends LowLevelList, T extends Meta> extends Meta<HighLevelList<U,T>> implements Externalizable {
	
	/**
	* Version number used to support versioned persistence.
	*/
	static final long serialVersionUID = (HighLevelList.class).getName().hashCode() + "v3/98A".hashCode();
	
	@Override
    public HighLevelList<U,T> copyNode() {
    	HighLevelList<U,T> temp = new HighLevelList<U,T>();
        
        if (this.myList != null) {
            temp.dvSetMyList((U)(myList.copyNode()));
        } else {
            temp.dvSetMyList(null);
        }
        
        return (temp);
    };
    @Override
    public HighLevelList<U,T> copySub() {
    	HighLevelList<U,T> temp = new HighLevelList<U,T>();
        
        if (this.myList != null) {
            temp.dvSetMyList((U)(myList.copySub()));
        } else {
            temp.dvSetMyList(null);
        }
        
        return (temp);
    };
    @Override
    public HighLevelList<U,T> copyAll() {
    	HighLevelList<U,T> temp = new HighLevelList<U,T>();
        
        if (this.myList != null) {
            temp.dvSetMyList((U)(myList.copyAll()));
        } else {
            temp.dvSetMyList(null);
        }
        
        return (temp);
    };
    @Override
    public HighLevelList<U,T> copyData() {
    	HighLevelList<U,T> temp = new HighLevelList<U,T>();
        
        return (temp);
    };
    @Override
    public HighLevelList<U,T> copyDataPlusPtr() {
    	HighLevelList<U,T> temp = new HighLevelList<U,T>();
        temp.dvSetMyList(myList);
        
        return (temp);
    };
    @Override
    public void copyNodeInfo(HighLevelList<U,T> in) {
    	HighLevelList<U,T> temp = in;
        
        if (this.myList != null) {
            temp.dvSetMyList((U)(myList.copyNode()));
        } else {
            temp.dvSetMyList(null);
        }
    };
    @Override
    public void copySubInfo(HighLevelList<U,T> in) {
    	HighLevelList<U,T> temp = in;
        
        if (this.myList != null) {
            temp.dvSetMyList((U)(myList.copySub()));
        } else {
            temp.dvSetMyList(null);
        }
    };
    @Override
    public void copyAllInfo(HighLevelList<U,T> in) {
    	HighLevelList<U,T> temp = in;
        
        if (this.myList != null) {
            temp.dvSetMyList((U)(myList.copyAll()));
        } else {
            temp.dvSetMyList(null);
        }
    };
    @Override
    public void copyDataInfo(HighLevelList<U,T> in) {};
    @Override
    public void copyDataPlusPtrInfo(HighLevelList<U,T> in) {
    	HighLevelList<U,T> temp = in;
        temp.dvSetMyList(myList);
    };
    public void eraseNode() {
        if (this.myList != null) {
            myList.eraseNode();
        }
        
    };
    @Override
    public void eraseSub() {
        if (this.myList != null) {
            myList.eraseSub();
        }
        
    };
    @Override
    public void eraseAll() {
        if (this.myList != null) {
            myList.eraseAll();
        }
        
    };
    @Override
    public void eraseData() {};
    @Override
    public void eraseNodeInfo() {
        U temp;
        
        if (this.myList != null) {
            if (myList.right() != myList)
                temp = (U)( myList.right() );
            else
                temp = null;
            myList.eraseNode();
            myList = temp;
        }
    };
    @Override
    public void eraseSubInfo() {
        U temp;
        
        if (this.myList != null) {
            if (!myList.getHead())
                temp = (U)( myList.left() );
            else
                temp = null;
            myList.eraseSub();
            myList = temp;
        }
    };
    @Override
    public void eraseAllInfo() {
        if (this.myList != null) {
            myList.eraseAll();
        }
        
        myList = null;
    };
    @Override
    public void erasePtrVal() {
        myList = null;
    };
    @Override
    public void wake() {};
    /**
     * Initializes the list.
     */
    public void iHighLevelList() {
        this.myList = null;
    };
    public HighLevelList() {
        this.iHighLevelList();
    };
    /**
     * Moves the current node one node to the right.
     */
    public final void right() {
        myList = (U)( myList.right() );
    };
    /**
     * Moves the current node one node to the left.
     */
    public final void left() {
        myList = (U)( myList.left() );
    };
    /**
     * Moves the current node to the head of the list.
     */
    public final void searchHead() {
        myList = (U)( myList.searchHead() );
    };
    /**
     * Returns true iff. the current node is the head of the list.
     */
    public final boolean getHead() {
        return (myList.getHead());
    };
    /**
     * Sets whether the current node is the head of the list.  This method can
     * corrupt the integrity of the list.  Use with caution.
     */
    public final void setHead(final boolean inHead) {
        myList.setHead(inHead);
    };
    /**
     * Returns the data stored in the current node of the list.
     */
    public final T getNode() {
        return ((T)(myList.getNode()));
    };
    /**
     * Stores <code>in</code> in the current node of the list.
     */
    public final void setNode(final T in) {
        myList.setNode(in);
    };
    /**
     * Sets the CopyMode of the current node in the list.
     */
    public final void setCopyMode(final int copy) {
        myList.setCopyMode(copy);
    };
    /**
     * Sets the CopyInfoMode of the current node in the list.
     */
    public final void setCopyInfoMode(final int copy) {
        myList.setCopyInfoMode(copy);
    };
    /**
     * Sets the EraseMode of the current node in the list.
     */
    public final void setEraseMode(final int erase) {
        myList.setEraseMode(erase);
    };
    /**
     * Returns the CopyMode of the current node in the list.
     */
    public final int getCopyMode() {
        return (myList.getCopyMode());
    };
    /**
     * Returns the CopyInfoMode of the current node in the list.
     */
    public final int getCopyInfoMode() {
        return (myList.getCopyInfoMode());
    };
    /**
     * Sets the EraseMode of the current node in the list.
     */
    public final int getEraseMode() {
        return (myList.getEraseMode());
    };
    /**
     * Returns true iff. the list is empty.
     */
    public final boolean empty() {
        return (myList == null);
    };
    /**
     * Inserts the data <code>in</code> to the left of the node
     * on which this method is called.
     */
    public final void insertLeft(final T in) {
        if (this.myList != null) {
            myList.insertLeft(in);
            myList = (U)( myList.left() );
        } else {
            myList = (U)( new StdLowLevelList<T>() );
            myList.setNode(in);
        }
    };
    /**
     * Inserts the data <code>in</code> to the right of the node
     * on which this method is called.
     */
    public final void insertRight(final T in) {
        if (this.myList != null) {
            myList.insertRight(in);
            myList = (U)( myList.right() );
        } else {
            myList = (U)( new StdLowLevelList<T>() );
            myList.setNode(in);
        }
    };
    /**
     * Inserts the data <code>in</code> to the left of the node
     * on which this method is called.
     */
    public final void importInsertLeft(final U in) {
        if (this.myList != null) {
            myList.importInsertLeft(in);
            myList = (U)( myList.left() );
        } else {
            myList = in;
        }
    };
    /**
     * Inserts the data <code>in</code> to the right of the node
     * on which this method is called.
     */
    public final void importInsertRight(final U in) {
        if (this.myList != null) {
            myList.importInsertRight(in);
            myList = (U)( myList.right() );
        } else {
            myList = in;
        }
    };
    
    /**
     * Returns whether the list is a single-node list.
     */
    public boolean isSingleNode() {
        return (myList.isSingleNode());
    }
    
    /**
     * Returns the LowLevelList used by this HighLevelList.  This method should not be used
     * unless one absolutely needs this capability.  Exercise with caution.
     */
    public final U exportNode() {
        return (myList);
    };
    /**
     * Disconnects the current node from the rest of the list, and returns it
     * in its own HighLevelList instance.
     */
    HighLevelList<U,T> disconnect() {
        HighLevelList<U,T> temp = new HighLevelList<U,T>();
        U lTemp;
        
        temp.dvSetMyList(this.myList);
        if (this.myList.right() != this.myList) {
            lTemp = (U)( this.myList.right() );
            myList.disconnect();
            myList = lTemp;
        } else
            this.myList = null;
        
        return (temp);
    };
    
    private final void dvSetMyList(U in) {
        myList = in;
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        U tmp = myList;
        int cnt = 0;
        boolean found = false;
        
        if (tmp == null) {} else {
            tmp = (U)( tmp.searchHead() );
            boolean done = false;
            
            while (!done) {
                if (tmp == myList)
                    found = true;
                
                if (!found)
                    cnt++;
                
                out.writeObject(tmp);
                tmp = (U)( tmp.right() );
                done = tmp.getHead();
            }
        }
        
        out.writeObject("s"); /* Stop */
        out.writeInt(cnt);
    }
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        try {
            myList = null;
            Object myo = in.readObject();
            
            while (myo instanceof LowLevelList<?,?>) {
                U myl = (U) myo;
                VersionBuffer.chkNul(myl);
                
                if (myList == null) {
                    myList = myl;
                } else {
                    myList.importInsertRight(myl);
                    myList = (U)( myList.right() );
                }
                
                myo = in.readObject();
            }
            
            int cnt = in.readInt();
            
            if (myList != null)
                myList = (U)( myList.searchHead() );
            
            int count;
            for (count = 0; count < cnt; ++count)
                myList = (U)( myList.right() );
        } catch (ClassCastException e) {
            throw (new DataFormatException(e));
        }
    }
    
    private U myList;
};
