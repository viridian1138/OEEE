






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
	
	
	/**
	 * The connection rectangle.
	 */
	Rectangle2D.Double connRect;
	
	/**
	 * The image rectangle.
	 */
	Rectangle2D.Double imgRect;
	
	/**
	 * The X-Axis offset for rendering.
	 */
	double xOffset = 0.0;
	
	/**
	 * The Y-Axis offset for rendering.
	 */
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
	 * Gets the connection rectangle.
	 * @return The connection rectangle.
	 */
	public Rectangle2D.Double getConnRect() {
		return connRect;
	}

	/**
	 * Gets the image rectangle.
	 * @return The image rectangle.
	 */
	public Rectangle2D.Double getImgRect() {
		return imgRect;
	}

	/**
	 * Gets the X-Axis offset.
	 * @return The X-Axis offset.
	 */
	public double getxOffset() {
		return xOffset;
	}

	/**
	 * Sets the X-Axis offset.
	 * @param xOffset The X-Axis offset.
	 */
	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	/**
	 * Gets the Y-Axis offset.
	 * @return The Y-Axis offset.
	 */
	public double getyOffset() {
		return yOffset;
	}

	/**
	 * Sets the Y-Axis offset.
	 * @param yOffset The Y-Axis offset.
	 */
	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}
	
	
	

	/**
	 * Builds a connection rectangle from a list of rendering nodes.
	 * @param lst The list of rendering nodes.
	 * @return The generated connection rectangle.
	 */
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


	/**
	 * Builds an image rectangle from a list of rendering nodes.
	 * @param lst The list of rendering nodes.
	 * @return The generated image rectangle.
	 */
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
	
	
	
	/**
	 * Returns a smaller version of a font.
	 * @param inFont The font to be shrunk.
	 * @param nSize The desired font size.
	 * @return The smaller version of the font.
	 */
	protected Font smlFont( Font inFont , java.lang.Double nSize )	
	{
		int sz = Math.round( nSize.floatValue() );
		if( sz < 1 ) sz = 1;
		Font MyFont = new Font( inFont.getName() , inFont.getStyle() , sz );
		return( MyFont );
	}
	
	
	
	
	/**
	 * Returns a simple lexeme with no extent.
	 * @return A simple lexeme with no extent.
	 */
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
	
	
	
	/**
	 * Handles the parsing of a normal script symbol.
	 * @param scriptLex The parsed script production, or null.
	 * @param superscriptLex The parsed superscript production, or null.
	 * @param subscriptLex The parsed subscript production, or null.
	 * @param overscriptLex The parsed overscript produuction, or null.
	 * @param underscriptLex The parsed underscript production, or null.
	 * @param inFont The original input font.
	 * @param origFontSz The original input font size.
	 * @param altFontSize The font size for nested scripts such as superscript.
	 * @param tempFrc Rendering context used to calculate font metrics.
	 */
	protected void handleCharParse( ParseRendNode scriptLex , ParseRendNode superscriptLex , ParseRendNode subscriptLex ,
			ParseRendNode overscriptLex , ParseRendNode underscriptLex , 
			Font inFont , java.lang.Double origFontSz, java.lang.Double altFontSize , final FontRenderContext tempFrc )
	{
		if( superscriptLex == null ) superscriptLex = simpLexeme();
		if( subscriptLex == null ) subscriptLex = simpLexeme();
		if( overscriptLex == null ) overscriptLex = simpLexeme();
		if( underscriptLex == null ) underscriptLex = simpLexeme();

		Font sFont = smlFont( inFont , altFontSize );
		double leading = 0.0;
		if( /* ( TempMode & MathImageConstants.ParseOnlyMode ) == 0 */ true )
			{
			LineMetrics lm = sFont.getLineMetrics( " " , tempFrc );
			leading = lm.getLeading();
			}
		
		
		scriptLex.calcRects(inFont, origFontSz, tempFrc);
		superscriptLex.calcRects(sFont, altFontSize, tempFrc);
		subscriptLex.calcRects(sFont, altFontSize, tempFrc);
		overscriptLex.calcRects(sFont, altFontSize, tempFrc);
		underscriptLex.calcRects(sFont, altFontSize, tempFrc);
		

		double scriptXOff = 0;
		double scriptYOff = 0;
		double underscriptXOff = 0;
		double underscriptYOff = 0;
		double overscriptXOff = 0;
		double overscriptYOff = 0;
		double superscriptXOff = 0;
		double superscriptYOff = 0;
		double subscriptXOff = 0;
		double subscriptYOff = 0;

		double minOverYDelta = leading;
		/* minOverYDelta = 1; */

		overscriptYOff = scriptLex.getConnRect().y - minOverYDelta - 
			( overscriptLex.getConnRect().height + overscriptLex.getConnRect().y );


		minOverYDelta = leading;
		/* minOverYDelta = 1; */

		superscriptYOff = overscriptYOff + overscriptLex.getConnRect().y - minOverYDelta - 
			( superscriptLex.getConnRect().height + superscriptLex.getConnRect().y );



		double minUnderYDelta = leading;
		/* minUnderYDelta = 1; */

		underscriptYOff = minUnderYDelta - underscriptLex.getConnRect().y +
			( scriptLex.getConnRect().height + scriptLex.getConnRect().y );

		minUnderYDelta = leading;
		/* minUnderYDelta = 1; */

		subscriptYOff = underscriptYOff + minUnderYDelta - subscriptLex.getConnRect().y +
			( underscriptLex.getConnRect().height + underscriptLex.getConnRect().y );

		double scriptCWidth = ( scriptLex.getConnRect().width + scriptLex.getConnRect().x ) / 2;
		double overscriptCWidth = ( overscriptLex.getConnRect().width + overscriptLex.getConnRect().x ) / 2;
		double underscriptCWidth = ( underscriptLex.getConnRect().width + underscriptLex.getConnRect().x ) / 2;

		double maxCWidth = scriptCWidth;
		if( overscriptCWidth > maxCWidth ) maxCWidth = overscriptCWidth;
		if( underscriptCWidth > maxCWidth ) maxCWidth = underscriptCWidth;

		scriptXOff = maxCWidth - scriptCWidth;
		overscriptXOff = maxCWidth - overscriptCWidth;
		underscriptXOff = maxCWidth - underscriptCWidth;

		double scriptXMax = scriptLex.getConnRect().width + scriptXOff;
		double overscriptXMax = overscriptLex.getConnRect().width + overscriptXOff;
		double underscriptXMax = underscriptLex.getConnRect().width + underscriptXOff;

		double maxXMax = scriptXMax;
		if( overscriptXMax > maxXMax ) maxXMax = overscriptXMax;
		if( underscriptXMax > maxXMax ) maxXMax = underscriptXMax;

		superscriptXOff = maxXMax;
		subscriptXOff = maxXMax;

		scriptLex.setxOffset( scriptXOff );
		scriptLex.setyOffset( scriptYOff );
		underscriptLex.setxOffset( underscriptXOff );
		underscriptLex.setyOffset( underscriptYOff );
		overscriptLex.setxOffset( overscriptXOff );
		overscriptLex.setyOffset( overscriptYOff );
		superscriptLex.setxOffset( superscriptXOff );
		superscriptLex.setyOffset( superscriptYOff );
		subscriptLex.setxOffset( subscriptXOff );
		subscriptLex.setyOffset( subscriptYOff );
					
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

