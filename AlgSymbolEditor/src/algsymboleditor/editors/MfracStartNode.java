






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






package algsymboleditor.editors;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating the end of a MathML mrow.
 * @author tgreen
 *
 */
public class MfracStartNode extends ParseNode {

	/**
	 * Constructs the node.
	 * @param _parseValue The parsed token.
	 * @param _next The next node in the list.
	 */
	public MfracStartNode(ParseNode _next) {
		super(_next);
	}
	
	
	public MfracRendNode applyParse( ParseRendNode numer , ParseRendNode denom , ParseNode endNode , DroolsSession ds )
	{
		MfracRendNode r = new MfracRendNode( numer , denom );
		r.next = endNode.next;
		ds.insert( r );
		return( r );
	}

	
}

