






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
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node used to represent a renderable parsed MathML production.
 * @author tgreen
 *
 */
public abstract class ParseRendNode extends ParseNode {
	
	
	Rectangle2D.Double connRect;
	
	
	Rectangle2D.Double imgRect;
	
	double xOffset = 0.0;
	
	double yOffset = 0.0;

	
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
	public abstract void draw( Graphics2D g , double xoff , double yoff );
	
	
	public abstract void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc );

	
	/**
	 * @return the connRect
	 */
	public Rectangle2D.Double getConnRect() {
		return connRect;
	}

	/**
	 * @return the imgRect
	 */
	public Rectangle2D.Double getImgRect() {
		return imgRect;
	}

	/**
	 * @return the xOffset
	 */
	public double getxOffset() {
		return xOffset;
	}

	/**
	 * @param xOffset the xOffset to set
	 */
	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	/**
	 * @return the yOffset
	 */
	public double getyOffset() {
		return yOffset;
	}

	/**
	 * @param yOffset the yOffset to set
	 */
	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}
	
	
	


	protected Rectangle2D.Double buildConnRect( ArrayList<ParseRendNode> lst )

		{
		double x = 0;
		double y = 0;
		double maxX = 0;
		double maxY = 0;
		int count;

		final int sz = lst.size();
		for( count = 0 ; count < sz ; ++count )
			{
			ParseRendNode MyLex = lst.get( count );
			double OffX = MyLex.getxOffset();
			double OffY = MyLex.getyOffset();

			if( ( MyLex.getConnRect().x + OffX ) < x ) x = MyLex.getConnRect().x + OffX;
			if( ( MyLex.getConnRect().y + OffY ) < y ) y = MyLex.getConnRect().y + OffY;

			double xw = MyLex.getConnRect().width + MyLex.getConnRect().x + OffX;
			double yw = MyLex.getConnRect().height + MyLex.getConnRect().y + OffY;

			if( xw > maxX ) maxX = xw;
			if( yw > maxY ) maxY = yw;
			}
		
		Rectangle2D.Double MyRect = new Rectangle2D.Double( x , y , maxX - x , maxY - y );
		return( MyRect );
		}


	protected Rectangle2D.Double buildImgRect( ArrayList<ParseRendNode> lst )

		{
		double x = 0;
		double y = 0;
		double maxX = 0;
		double maxY = 0;
		int count;

		final int sz = lst.size();
		for( count = 0 ; count < sz ; ++count )
			{
			ParseRendNode MyLex = lst.get( count );
			double OffX = MyLex.getxOffset();
			double OffY = MyLex.getyOffset();

			if( ( MyLex.getImgRect().x + OffX ) < x ) x = MyLex.getImgRect().x + OffX;
			if( ( MyLex.getImgRect().y + OffY ) < y ) y = MyLex.getImgRect().y + OffY;

			double xw = MyLex.getImgRect().width + MyLex.getImgRect().x + OffX;
			double yw = MyLex.getImgRect().height + MyLex.getImgRect().y + OffY;

			if( xw > maxX ) maxX = xw;
			if( yw > maxY ) maxY = yw;
			}
		
		Rectangle2D.Double MyRect = new Rectangle2D.Double( x , y , maxX - x , maxY - y );
		return( MyRect );
		}
	
	
	
	protected Font smlFont( Font inFont , java.lang.Double nSize )	
	{
		int sz = Math.round( nSize.floatValue() );
		if( sz < 1 ) sz = 1;
		Font MyFont = new Font( inFont.getName() , inFont.getStyle() , sz );
		return( MyFont );
	}
	
	
	
	
	protected static ParseRendNode simpLexeme()
	{
		return( new ParseRendNode( null )
			{

				@Override
				public void draw(Graphics2D g, double xoff, double yoff) {
					throw( new RuntimeException( "Internal Error" ) );
				}

				@Override
				public void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc ) {
					connRect = new Rectangle2D.Double( 0 , 0 , 0 , 0 );
					imgRect = new Rectangle2D.Double( 0 , 0 , 0 , 0 );
				}

				@Override
				public ParseNode applyReng(ParseNode nxt, DroolsSession ds) {
					throw( new RuntimeException( "Internal Error" ) );
				}
			
			} );
	}
	
	
	
	
	protected void handleCharParse( ParseRendNode scriptLex , ParseRendNode superscriptLex , ParseRendNode subscriptLex ,
			ParseRendNode overscriptLex , ParseRendNode underscriptLex , 
			Font inFont , java.lang.Double origFontSz, java.lang.Double altFontSize , final FontRenderContext tempFrc )
	{
		if( superscriptLex == null ) superscriptLex = simpLexeme();
		if( subscriptLex == null ) subscriptLex = simpLexeme();
		if( overscriptLex == null ) overscriptLex = simpLexeme();
		if( underscriptLex == null ) underscriptLex = simpLexeme();

		Font SFont = smlFont( inFont , altFontSize );
		double leading = 0.0;
		if( /* ( TempMode & MathImageConstants.ParseOnlyMode ) == 0 */ true )
			{
			LineMetrics lm = SFont.getLineMetrics( " " , tempFrc );
			leading = lm.getLeading();
			}
		
		
		scriptLex.calcRects(inFont, origFontSz, tempFrc);
		superscriptLex.calcRects(SFont, altFontSize, tempFrc);
		subscriptLex.calcRects(SFont, altFontSize, tempFrc);
		overscriptLex.calcRects(SFont, altFontSize, tempFrc);
		underscriptLex.calcRects(SFont, altFontSize, tempFrc);
		

		double ScriptXOff = 0;
		double ScriptYOff = 0;
		double UnderscriptXOff = 0;
		double UnderscriptYOff = 0;
		double OverscriptXOff = 0;
		double OverscriptYOff = 0;
		double SuperscriptXOff = 0;
		double SuperscriptYOff = 0;
		double SubscriptXOff = 0;
		double SubscriptYOff = 0;

		double minOverYDelta = leading;
		/* minOverYDelta = 1; */

		OverscriptYOff = scriptLex.getConnRect().y - minOverYDelta - 
			( overscriptLex.getConnRect().height + overscriptLex.getConnRect().y );


		minOverYDelta = leading;
		/* minOverYDelta = 1; */

		SuperscriptYOff = OverscriptYOff + overscriptLex.getConnRect().y - minOverYDelta - 
			( superscriptLex.getConnRect().height + superscriptLex.getConnRect().y );



		double minUnderYDelta = leading;
		/* minUnderYDelta = 1; */

		UnderscriptYOff = minUnderYDelta - underscriptLex.getConnRect().y +
			( scriptLex.getConnRect().height + scriptLex.getConnRect().y );

		minUnderYDelta = leading;
		/* minUnderYDelta = 1; */

		SubscriptYOff = UnderscriptYOff + minUnderYDelta - subscriptLex.getConnRect().y +
			( underscriptLex.getConnRect().height + underscriptLex.getConnRect().y );

		double ScriptCWidth = ( scriptLex.getConnRect().width + scriptLex.getConnRect().x ) / 2;
		double OverscriptCWidth = ( overscriptLex.getConnRect().width + overscriptLex.getConnRect().x ) / 2;
		double UnderscriptCWidth = ( underscriptLex.getConnRect().width + underscriptLex.getConnRect().x ) / 2;

		double MaxCWidth = ScriptCWidth;
		if( OverscriptCWidth > MaxCWidth ) MaxCWidth = OverscriptCWidth;
		if( UnderscriptCWidth > MaxCWidth ) MaxCWidth = UnderscriptCWidth;

		ScriptXOff = MaxCWidth - ScriptCWidth;
		OverscriptXOff = MaxCWidth - OverscriptCWidth;
		UnderscriptXOff = MaxCWidth - UnderscriptCWidth;

		double ScriptXMax = scriptLex.getConnRect().width + ScriptXOff;
		double OverscriptXMax = overscriptLex.getConnRect().width + OverscriptXOff;
		double UnderscriptXMax = underscriptLex.getConnRect().width + UnderscriptXOff;

		double MaxXMax = ScriptXMax;
		if( OverscriptXMax > MaxXMax ) MaxXMax = OverscriptXMax;
		if( UnderscriptXMax > MaxXMax ) MaxXMax = UnderscriptXMax;

		SuperscriptXOff = MaxXMax;
		SubscriptXOff = MaxXMax;

		scriptLex.setxOffset( ScriptXOff );
		scriptLex.setyOffset( ScriptYOff );
		underscriptLex.setxOffset( UnderscriptXOff );
		underscriptLex.setyOffset( UnderscriptYOff );
		overscriptLex.setxOffset( OverscriptXOff );
		overscriptLex.setyOffset( OverscriptYOff );
		superscriptLex.setxOffset( SuperscriptXOff );
		superscriptLex.setyOffset( SuperscriptYOff );
		subscriptLex.setxOffset( SubscriptXOff );
		subscriptLex.setyOffset( SubscriptYOff );
					
		ArrayList<ParseRendNode> ar = new ArrayList<ParseRendNode>();
		ar.add( scriptLex );
		ar.add( superscriptLex );
		ar.add( subscriptLex );
		ar.add( overscriptLex );
		ar.add( underscriptLex );
		
		
		connRect = buildConnRect( ar );
		imgRect = buildImgRect( ar );
	}


	
	

	
}

