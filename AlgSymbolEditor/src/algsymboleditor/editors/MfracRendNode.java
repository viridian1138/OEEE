






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


/**
 * Node indicating a renderable version of an mrow.
 * @author tgreen
 *
 */
public class MfracRendNode extends ParseRendNode {
	
	protected ParseRendNode numer;
	protected ParseRendNode denom;

	/**
	 * Constructs the node.
	 * @param _parseValue The parsed token.
	 * @param _next The next node in the list.
	 */
	public MfracRendNode( ParseRendNode a, ParseRendNode b) {
		super( b.next );
		numer = a;
		denom = b;
	}

	@Override
	public void draw(Graphics2D g, int xoff, int yoff) {
		numer.draw(g, xoff, yoff - 8);
		g.drawLine( xoff - 8 , yoff, xoff + 16, yoff);
		denom.draw(g, xoff, yoff + 15);
	}

	
}

