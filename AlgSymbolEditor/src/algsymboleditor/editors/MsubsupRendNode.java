






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

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating a renderable version of an subscript-superscript production.
 * @author tgreen
 *
 */
public class MsubsupRendNode extends ParseRendNode {
	
	/**
	 * The parsed script production.
	 */
	protected ParseRendNode script;
	
	/**
	 * The parsed subscript production.
	 */
	protected ParseRendNode subscript;
	
	/**
	 * The parsed superscript production.
	 */
	protected ParseRendNode superscript;

	/**
	 * Constructs the node.
	 * @param _script The parsed script production.
	 * @param _subscript The parsed subscript production.
	 * @param _superscript The parsed superscript production.
	 */
	public MsubsupRendNode( ParseRendNode _script, ParseRendNode _subscript, ParseRendNode _superscript) {
		super( _superscript.next );
		script = _script;
		subscript = _subscript;
		superscript = _superscript;
	}

	@Override
	public void draw(Graphics2D g, double xoff, double yoff) {
		script.draw(g, xoff+xOffset, yoff+yOffset);
		subscript.draw(g, xoff+xOffset, yoff+yOffset);
		superscript.draw(g, xoff+xOffset, yoff+yOffset);
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MsubsupRendNode p0 = new MsubsupRendNode( script , subscript , superscript );
		p0.next = nxt;
		ds.insert( p0 );
		return( p0 );
	}
	
	@Override
	public void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc ) {
		// next = null;
		
		handleCharParse( script , superscript , subscript ,
				null , null , 
				inFont , fontSz, fontSz * 10.0 / 12.0 , tempFrc );
	}

	
}

