






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
 * Node indicating the start of a MathML mfrac production.
 * @author tgreen
 *
 */
public class MfracStartNode extends ParseNode {

	/**
	 * Constructs the node.
	 * @param _next The next node in the list.
	 */
	public MfracStartNode(ParseNode _next) {
		super(_next);
	}
	
	
	/**
	 * Applies the parsing of the quotient production.
	 * @param numer The parsed numerator production.
	 * @param denom The parsed denominator production.
	 * @param endNode The end terminal of the quotient.
	 * @param ds The Drools session.
	 * @return The rendering node for the parsed production.
	 */
	public MfracRendNode applyParse( ParseRendNode numer , ParseRendNode denom , ParseNode endNode , DroolsSession ds )
	{
		MfracRendNode r = new MfracRendNode( numer , denom );
		r.next = endNode.next;
		ds.insert( r );
		return( r );
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MfracStartNode p0 = new MfracStartNode( nxt );
		ds.insert( p0 );
		return( p0 );
	}

	
}

