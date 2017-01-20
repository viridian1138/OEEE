






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
 * Node used to represent a renderable parsed MathML production.
 * @author tgreen
 *
 */
public abstract class ParseRendNode extends ParseNode {

	/**
	 * Constructs the node.
	 * @param _next The next node in the list.
	 */
	public ParseRendNode(ParseNode _next) {
		super(_next);
	}
	
	/**
	 * Draws the RendNode.
	 * @param g The graphics context in which to draw.
	 * @param xoff The X-offset at which to draw.
	 * @param yoff The Y-offset at which to draw.
	 */
	public abstract void draw( Graphics2D g , int xoff , int yoff );

	
}

