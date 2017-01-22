






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
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

import simplealgebra.symbolic.DroolsSession;


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
	public void draw(Graphics2D g, double xoff, double yoff) {
		
		numer.draw(g, xoff+xOffset, yoff+yOffset);
		
		Path2D.Double p = new Path2D.Double();
		
		p.moveTo( imgRect.x + xoff + xOffset , numer.getImgRect().y + numer.getImgRect().height + 1.5 + yoff + connRect.y );
		p.lineTo( imgRect.x + imgRect.width + xoff + xOffset , numer.getImgRect().y + numer.getImgRect().height + 1.5 + yoff + connRect.y );
		
		g.draw( p );
		
		denom.draw(g, xoff+xOffset, yoff+yOffset);
	}
	
	
	
	@Override
	public void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc )
	{
		// next = null;
		
		numer.calcRects(inFont, fontSz, tempFrc);
		denom.calcRects(inFont, fontSz, tempFrc);
		
		final double cnw = Math.max( numer.getConnRect().width , denom.getConnRect().width );
		
		numer.setxOffset( ( cnw - numer.getConnRect().width ) / 2.0 );
		
		denom.setxOffset( ( cnw - denom.getConnRect().width ) / 2.0 );
		
		final double deltaY = ( numer.getImgRect().y + numer.getImgRect().height + 3.0 ) + ( denom.getConnRect().y - denom.getImgRect().y );
		denom.setyOffset( deltaY );
		
		ArrayList<ParseRendNode> ar = new ArrayList<ParseRendNode>();
		ar.add( numer );
		ar.add( denom );
		
		connRect = buildConnRect( ar );
		imgRect = buildImgRect( ar );
		
	}
	
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MfracRendNode p0 = new MfracRendNode( numer , denom );
		p0.next = nxt;
		ds.insert( p0 );
		return( p0 );
	}

	
}

