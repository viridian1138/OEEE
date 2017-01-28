






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
 * Node indicating the start of a MathML mrow.
 * @author tgreen
 *
 */
public class MrowStartNode extends ParseNode {

	/**
	 * Constructs the node.
	 * @param _next The next node in the list.
	 */
	public MrowStartNode(ParseNode _next) {
		super(_next);
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MrowStartNode p0 = new MrowStartNode( nxt );
		ds.insert( p0 );
		return( p0 );
	}
	
	/**
	 * Applies the parsing of the mrow start.
	 * @param ds The Drools session.
	 * @return Parsing node for aggregating the productions in the row.
	 */
	public MrowCondNode applyMrowStart( DroolsSession ds )
	{
		MrowCondNode r = new MrowCondNode( next );
		ds.insert( r );
		return( r );
	}

	
}

