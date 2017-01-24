






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
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import simplealgebra.symbolic.DroolsSession;
import meta.FlexString;


/**
 * Node used to represent a renderable parsed MathML production.
 * @author tgreen
 *
 */
public class LiteralRendNode extends ParseRendNode {

	/**
	 * String to be displayed as the literal.
	 */
	protected final FlexString str = new FlexString();
	
	/**
	 * Font in which to display the string.
	 */
	protected Font rendFont = null;
	
	
	/**
	 * Constructs the node.
	 * @param _next The next node in the list.
	 */
	public LiteralRendNode(ParseNode _next) {
		super(_next);
	}
	
	
	/**
	 * Gets the string in which to display the literal.
	 * @return String in which to display the literal.
	 */
	public FlexString getStr()
	{
		return( str );
	}


	@Override
	public void draw(Graphics2D g, double xoff, double yoff) {
		g.setFont( rendFont );
		str.drawString(g, (int) ( xoff + xOffset), (int) ( yoff + yOffset ) );
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
	
	
	@Override
	public void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc )
	{
		// next = null;
		
		String str = this.str.exportString();
		TextLayout tl = new TextLayout( str , inFont , tempFrc );
		Rectangle2D bounds = tl.getBounds();
		double cwidth = tl.getAdvance();
		double imgX = bounds.getX();
		double imgY = bounds.getY();
		double imgWidth = bounds.getWidth();
		double imgHeight = bounds.getHeight();
		Rectangle2D.Double iConnRect = new Rectangle2D.Double( 0 , imgY , cwidth , imgHeight );

		Rectangle2D.Double iImgRect = new Rectangle2D.Double( imgX , imgY , imgWidth , imgHeight );

		connRect = iConnRect;
		imgRect = iImgRect;
		rendFont = inFont;
	}

	
	
}


