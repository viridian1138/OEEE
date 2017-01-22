






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
import java.awt.font.LineMetrics;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating a renderable version of a quotient fraction production.
 * @author tgreen
 *
 */
public class MfracRendNode extends ParseRendNode {
	
	/**
	 * The parsed numerator production.
	 */
	protected ParseRendNode numer;
	
	/**
	 * The parsed denominator production.
	 */
	protected ParseRendNode denom;
	
	/**
	 * The Y-Coordinate at which to draw the quotient line.
	 */
	protected double lineY = 0.0;

	/**
	 * Constructs the node.
	 * @param _numer The parsed numerator production.
	 * @param _denom The parsed denominator production.
	 */
	public MfracRendNode( ParseRendNode _numer, ParseRendNode _denom ) {
		super( _denom.next );
		numer = _numer;
		denom = _denom;
	}

	@Override
	public void draw(Graphics2D g, double xoff, double yoff) {
		
		numer.draw(g, xoff+xOffset, yoff+yOffset);
		
		Path2D.Double p = new Path2D.Double();
		
		p.moveTo( imgRect.x + xoff + xOffset , lineY + yoff + yOffset );
		p.lineTo( imgRect.x + imgRect.width + xoff + xOffset , lineY + yoff + yOffset );
		
		g.draw( p );
		
		denom.draw(g, xoff+xOffset, yoff+yOffset);
	}
	
	
	
	@Override
	public void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc )
	{
		// next = null;
		
		numer.calcRects(inFont, fontSz, tempFrc);
		denom.calcRects(inFont, fontSz, tempFrc);
		
		final LineMetrics lm = inFont.getLineMetrics( " " , tempFrc );
		final double asc2 = -( lm.getAscent() / 2.0 );
		lineY = asc2;
		
		final double cnw = Math.max( numer.getConnRect().width , denom.getConnRect().width );
		
		numer.setxOffset( ( cnw - numer.getConnRect().width ) / 2.0 );
		
		 denom.setxOffset( ( cnw - denom.getConnRect().width ) / 2.0 );
		
		final double deltaYN = -3.0 + asc2 - ( numer.getImgRect().y + numer.getImgRect().height );
		final double deltaYD = 3.0 + asc2 - denom.getImgRect().y;
		numer.setyOffset( deltaYN );
		denom.setyOffset( deltaYD );
		
		ArrayList<ParseRendNode> ar = new ArrayList<ParseRendNode>();
		ar.add( numer );
		ar.add( denom );
		
		imgRect = buildImgRect( ar );
		connRect = imgRect;
		
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

