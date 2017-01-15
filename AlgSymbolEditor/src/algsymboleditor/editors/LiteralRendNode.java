






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
import meta.FlexString;


/**
 * Node used to represent a renderable parsed MathML production.
 * @author tgreen
 *
 */
public class LiteralRendNode extends ParseRendNode {

	protected final FlexString str = new FlexString();
	
	/**
	 * Constructs the node.
	 * @param _next The next node in the list.
	 */
	public LiteralRendNode(ParseNode _next) {
		super(_next);
	}
	
	
	public FlexString getStr()
	{
		return( str );
	}


	@Override
	public void draw(Graphics2D g, int xoff, int yoff) {
		str.drawString(g, xoff, yoff);
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		LiteralRendNode p0 = new LiteralRendNode( nxt );
		p0.next = nxt;
		str.copyAllInfo( p0.str );
		ds.insert( p0 );
		return( p0 );
	}

	
}

