






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
import java.util.ArrayList;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating a renderable version of an mrow.
 * @author tgreen
 *
 */
public class MrowRendNode extends ParseRendNode {
	
	
	protected ArrayList<ParseRendNode> lst;

	
	public MrowRendNode(  ArrayList<ParseRendNode> _lst, ParseNode _next) {
		super(_next);
		lst = _lst;
	}

	
	@Override
	public void draw(Graphics2D g, int xoff, int yoff) {
		int x = xoff;
		final int sz = lst.size();
		for( int cnt = 0 ; cnt < sz ; cnt++ )
		{
			lst.get( cnt ).draw(g, x, yoff);
			x += 15;
		}
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MrowRendNode p0 = new MrowRendNode( lst , nxt );
		ds.insert( p0 );
		return( p0 );
	}

	
}

