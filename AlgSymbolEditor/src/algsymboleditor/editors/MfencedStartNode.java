






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
public class MfencedStartNode extends ParseNode {
	
	
	protected MfencedRendNode.RendMode rendMode;

	/**
	 * Constructs the node.
	 * @param _parseValue The parsed token.
	 * @param _next The next node in the list.
	 */
	public MfencedStartNode( MfencedRendNode.RendMode _rendMode , ParseNode _next) {
		super(_next);
		rendMode = _rendMode;
	}
	
	
	public MfencedRendNode applyParse( ParseRendNode script , ParseNode endNode , DroolsSession ds )
	{
		MfencedRendNode r = new MfencedRendNode( script , rendMode );
		r.next = endNode.next;
		ds.insert( r );
		return( r );
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MfencedStartNode p0 = new MfencedStartNode( rendMode , nxt );
		ds.insert( p0 );
		return( p0 );
	}

	
}

