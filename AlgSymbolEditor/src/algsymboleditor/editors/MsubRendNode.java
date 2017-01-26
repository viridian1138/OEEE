






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
import java.awt.geom.Rectangle2D.Double;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating a renderable version of a MathML subscript production,
 * producing an expression of the form <math display="inline">
 * <msub><mi>&alpha;</mi><mi>&beta;</mi></msub>
 * </math>.
 * 
 * This documentation should be viewed using Firefox version 33.1.1 or above.
 * 
 * @author tgreen
 *
 */
public class MsubRendNode extends ParseRendNode {
	
	/**
	 * The parsed script production.
	 */
	protected ParseRendNode script;
	
	/**
	 * The parsed subscript production.
	 */
	protected ParseRendNode subscript;

	/**
	 * Constructs the node.
	 * @param _script The parsed script production.
	 * @param _subscript The parsed subscript production.
	 */
	public MsubRendNode( ParseRendNode _script, ParseRendNode _subscript) {
		super( _subscript.next );
		script = _script;
		subscript = _subscript;
	}

	@Override
	public void draw(Graphics2D g, double xoff, double yoff) {
		script.draw(g, xoff+xOffset, yoff+yOffset);
		subscript.draw(g, xoff+xOffset, yoff+yOffset);
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MsubRendNode p0 = new MsubRendNode( script , subscript );
		p0.next = nxt;
		ds.insert( p0 );
		return( p0 );
	}
	
	@Override
	public void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc ) {
		// next = null;
		
		handleCharParse( script , null , subscript ,
				null , null , 
				inFont , fontSz, fontSz * 10.0 / 12.0 , tempFrc );
	}

	
}

