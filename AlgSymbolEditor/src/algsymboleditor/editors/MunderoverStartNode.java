






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
 * Node indicating the end of a MathML underscript-overscript production,
 * producing an expression of the form <math display="inline">
 * <munderover><mi>&alpha;</mi><mi>&beta;</mi><mi>&gamma;
 * </mi></munderover>
 * </math>.
 * 
 * This documentation should be viewed using Firefox version 33.1.1 or above.
 * 
 * @author tgreen
 *
 */
public class MunderoverStartNode extends ParseNode {

	/**
	 * Constructs the node.
	 * @param _next The next node in the list.
	 */
	public MunderoverStartNode(ParseNode _next) {
		super(_next);
	}
	
	
	/**
	 * Applies the parsing of an underscript-overscript production.
	 * @param script The parsed script production.
	 * @param underscript The parsed underscript production.
	 * @param overscript The parsed overscript production.
	 * @param endNode The end terminal of the production.
	 * @param ds The Drools session.
	 * @return The rendering node for the parsed production.
	 */
	public MunderoverRendNode applyParse( ParseRendNode script , ParseRendNode underscript , ParseRendNode overscript , ParseNode endNode , DroolsSession ds )
	{
		MunderoverRendNode r = new MunderoverRendNode( script , underscript , overscript );
		r.next = endNode.next;
		ds.insert( r );
		return( r );
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MunderoverStartNode p0 = new MunderoverStartNode( nxt );
		ds.insert( p0 );
		return( p0 );
	}

	
}

