






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
 * Node indicating a renderable version of an overscript production.
 * @author tgreen
 *
 */
public class MoverRendNode extends ParseRendNode {
	
	/**
	 * The parsed script production.
	 */
	protected ParseRendNode script;
	
	/**
	 * The parsed overscript production.
	 */
	protected ParseRendNode overscript;

	/**
	 * Constructs the node.
	 * @param _script The parsed script production.
	 * @param _overscript The parsed overscript production.
	 */
	public MoverRendNode( ParseRendNode _script, ParseRendNode _overscript) {
		super( _overscript.next );
		script = _script;
		overscript = _overscript;
	}

	@Override
	public void draw(Graphics2D g, double xoff, double yoff) {
		script.draw(g, xoff+xOffset, yoff+yOffset);
		overscript.draw(g, xoff+xOffset, yoff+yOffset);
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MoverRendNode p0 = new MoverRendNode( script , overscript );
		p0.next = nxt;
		ds.insert( p0 );
		return( p0 );
	}

	@Override
	public void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc ) {
		// next = null;
		
		handleCharParse( script , null , null ,
				overscript , null , 
				inFont , fontSz, fontSz * 10.0 / 12.0 , tempFrc );
	}

	
}

