






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
 * @param <R> Comparable to be used in node comparison.
 */
public class MsupRendNode< R extends Comparable<?> > extends ParseRendNode<R> {
	
	protected ParseRendNode<R> script;
	protected ParseRendNode<R> superscript;

	/**
	 * Constructs the node.
	 * @param _parseValue The parsed token.
	 * @param _next The next node in the list.
	 */
	public MsupRendNode( R val, ParseRendNode<R> a, ParseRendNode<R> b) {
		super( val , b.next );
		script = a;
		superscript = b;
	}

	@Override
	public void draw(Graphics2D g, int xoff, int yoff) {
		script.draw(g, xoff, yoff);
		superscript.draw(g, xoff + 15, yoff - 15);
	}

	
}

