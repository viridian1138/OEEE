






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



/**
 * An individual node in a VersionBuffer.
 * @author tgreen
 *
 */
public class VersionBufferNode extends Meta<VersionBufferNode> {
    
	/**
	 * Constructs the node.
	 * @param callId The unique ID for the calling VersionBuffer.
	 * @param user The user (caller) selected name for the node.
	 * @param hash The hash key for storing the node.
	 * @param val The object value stored by the node.
	 */
    public VersionBufferNode(
            int callId,
            String user,
            String hash,
            Object val) {
        callerId = callId;
        userKey = user;
        hashKey = hash;
        value = val;
    }
    
    /**
     * Gets the object value stored by the node.
     * @return The object value stored by the node.
     */
    public Object getValue() {
        return (value);
    }
    
    /**
     * Gets the unique ID for the calling VersionBuffer.
     * @return The unique ID for the calling VersionBuffer.
     */
    public int getCallerId() {
        return (callerId);
    }
    
    /**
     * Gets the user (caller) selected name for the node.
     * @return The user (caller) selected name for the node.
     */
    public String getUserKey() {
        return (userKey);
    }
    
    /**
     * Gets the hash key for storing the node.
     * @return The hash key for storing the node.
     */
    public String getHashKey() {
        return (hashKey);
    }
    
    @Override
    public void wake() {
    };
    
    /**
     * The hash key for storing the node.
     */
    private String hashKey;
    
    /**
     * The user (caller) selected name for the node.
     */
    private String userKey;
    
    /**
     * The object value stored by the node.
     */
    private Object value;
    
    /**
     * The unique ID for the calling VersionBuffer.
     */
    private int callerId;
    
}


