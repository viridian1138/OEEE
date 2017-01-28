






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

import java.util.ArrayList;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node for aggregating the productions in a MathML mrow.
 * @author tgreen
 *
 */
public class MrowCondNode extends ParseNode {
	
	/**
	 * The list of renderable parsed productions collected so far.
	 */
	protected ArrayList<ParseRendNode> lst;

	/**
	 * Constructs the node.
	 * @param _next The next node in the list.
	 */
	public MrowCondNode(ParseNode _next) {
		super(_next);
		lst = new ArrayList<ParseRendNode>();
	}
	
	
	/**
	 * Constructs the node.
	 * @param _lst The list of renderable parsed productions collected so far.
	 * @param _next The next node in the parsing list.
	 */
	public MrowCondNode(  ArrayList<ParseRendNode> _lst, ParseNode _next) {
		super(_next);
		lst = _lst;
	}
	
	
	/**
	 * Aggregates one parsed production in the mrow.
	 * @param node The parsed production.
	 * @param ds The Drools session.
	 * @return The result of the aggregation.
	 */
	public MrowCondNode applyParseRend( ParseRendNode node , DroolsSession ds )
	{
		ArrayList<ParseRendNode> ilst = new ArrayList<ParseRendNode>( lst );
		ilst.add( node );
		MrowCondNode r = new MrowCondNode( ilst , null );
		r.next = node.next;
		ds.insert( r );
		return( r );
	}
	
	
	/**
	 * Applies the parsing of the end of the mrow.
	 * @param node Node indicating the end of the mrow.
	 * @param ds The Drools session.
	 * @return The final renderable parsed production.
	 */
	public MrowRendNode applyMrowEnd( MrowEndNode node , DroolsSession ds )
	{
		MrowRendNode r = new MrowRendNode( lst , node.next );
		ds.insert( r );
		return( r );
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MrowCondNode p0 = new MrowCondNode( lst , nxt );
		ds.insert( p0 );
		return( p0 );
	}

	
}

