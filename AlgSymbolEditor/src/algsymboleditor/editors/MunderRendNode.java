






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

import java.awt.Graphics2D;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating a renderable version of an mrow.
 * @author tgreen
 *
 */
public class MunderRendNode extends ParseRendNode {
	
	protected ParseRendNode script;
	protected ParseRendNode subscript;

	/**
	 * Constructs the node.
	 * @param _parseValue The parsed token.
	 * @param _next The next node in the list.
	 */
	public MunderRendNode( ParseRendNode a, ParseRendNode b) {
		super( b.next );
		script = a;
		subscript = b;
	}

	@Override
	public void draw(Graphics2D g, int xoff, int yoff) {
		script.draw(g, xoff, yoff);
		subscript.draw(g, xoff, yoff + 15);
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MunderRendNode p0 = new MunderRendNode( script , subscript );
		p0.next = nxt;
		ds.insert( p0 );
		return( p0 );
	}

	
}

