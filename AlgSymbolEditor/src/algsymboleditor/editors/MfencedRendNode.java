






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
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating a renderable version of an mfenced production,
 * producing an expression of the form <math display="inline">
 * <mfenced open="(" close=")"><mi>&alpha;</mi></mfenced>
 * </math> for a rendering mode of CIRC, 
 * an expression of the form <math display="inline">
 * <mfenced open="[" close="]"><mi>&alpha;</mi></mfenced>
 * </math> for a rendering mode of SQUARE,
 * an expression of the form <math display="inline">
 * <mfenced open="{" close="}"><mi>&alpha;</mi></mfenced>
 * </math> for a rendering mode of CURLY, and
 * an expression of the form <math display="inline">
 * <mfenced open="|" close="|"><mi>&alpha;</mi></mfenced>
 * </math> for a rendering mode of VERT.
 * 
 * This documentation should be viewed using Firefox version 33.1.1 or above.
 * 
 * @author tgreen
 *
 */
public class MfencedRendNode extends ParseRendNode {
	
	
	/**
	 * The rendering mode for the mfenced production.
	 * @author tgreen
	 *
	 */
	public static enum RendMode
	{
		
		/**
		 * Mode for circular parenthesis.
		 */
		CIRC
		{

			@Override
			public void draw(MfencedRendNode rend, Graphics2D g, double xoff,
					double yoff) {
				
				rend.script.draw(g, xoff , yoff);
				
				Path2D.Double p = new Path2D.Double();
				
				Rectangle2D.Double ir = rend.getImgRect();
				
				p.moveTo( ir.x + xoff + 6 , ir.y + yoff + 1 );
				p.curveTo( ir.x + xoff + 1 , ir.y + yoff + 6 , 
							ir.x + xoff + 1 , ir.y + ir.height + yoff - 6 , 
							ir.x + xoff + 6 , ir.y + ir.height + yoff - 1 );
				
				p.moveTo( ir.x + ir.width + xoff - 6 , ir.y + yoff + 1 );
				p.curveTo( ir.x + ir.width + xoff - 1 , ir.y + yoff + 6 , 
							ir.x + ir.width + xoff - 1 , ir.y + ir.height + yoff - 6 , 
							ir.x + ir.width + xoff - 6 , ir.y + ir.height + yoff - 1 );
				
				g.draw( p );
				
			}
			
			
			@Override
			public double getDelx()
			{
				return( 7.0 );
			}

	
			
		},
		/**
		 * Mode for a square bracket.
		 */
		SQUARE
		{

			@Override
			public void draw(MfencedRendNode rend, Graphics2D g, double xoff,
					double yoff) {
				
				rend.script.draw(g, xoff , yoff);
				
				Path2D.Double p = new Path2D.Double();
				
				Rectangle2D.Double ir = rend.getImgRect();
				
				p.moveTo( ir.x + xoff + 6 , ir.y + yoff + 1 );
				p.lineTo( ir.x + xoff + 1 , ir.y + yoff + 1 );
				p.lineTo( ir.x + xoff + 1 , ir.y + ir.height + yoff - 1 );
				p.lineTo( ir.x + xoff + 6 , ir.y + ir.height + yoff - 1 );
				
				p.moveTo( ir.x + ir.width + xoff - 6 , ir.y + yoff + 1 );
				p.lineTo( ir.x + ir.width + xoff - 1 , ir.y + yoff + 1 );
				p.lineTo( ir.x + ir.width + xoff - 1 , ir.y + ir.height + yoff - 1 );
				p.lineTo( ir.x + ir.width + xoff - 6 , ir.y + ir.height + yoff - 1 );
				
				g.draw( p );
				
			}
			
			
			@Override
			public double getDelx()
			{
				return( 5.0 );
			}


			
		},
		/**
		 * Mode for a curly brace.
		 */
		CURLY
		{

			@Override
			public void draw(MfencedRendNode rend, Graphics2D g, double xoff,
					double yoff) {
				
				rend.script.draw(g, xoff , yoff);
				
				Path2D.Double p = new Path2D.Double();
				
				Rectangle2D.Double ir = rend.getImgRect();
				
				p.moveTo( ir.x + xoff + 11 , ir.y + yoff + 1 );
				p.lineTo( ir.x + xoff + 6 , ir.y + yoff + 1 );
				p.lineTo( ir.x + xoff + 6 , ir.y + ( ir.height / 2.0 ) + yoff - 3 );
				p.lineTo( ir.x + xoff + 1 , ir.y + ( ir.height / 2.0 ) + yoff );
				p.lineTo( ir.x + xoff + 6 , ir.y + ( ir.height / 2.0 ) + yoff + 3 );
				p.lineTo( ir.x + xoff + 6 , ir.y + ir.height + yoff - 1 );
				p.lineTo( ir.x + xoff + 11 , ir.y + ir.height + yoff - 1 );
				
				p.moveTo( ir.x + ir.width + xoff - 11 , ir.y + yoff + 1 );
				p.lineTo( ir.x + ir.width + xoff - 6 , ir.y + yoff + 1 );
				p.lineTo( ir.x + ir.width + xoff - 6 , ir.y + ( ir.height / 2.0 ) + yoff - 3 );
				p.lineTo( ir.x + ir.width + xoff - 1 , ir.y + ( ir.height / 2.0 ) + yoff );
				p.lineTo( ir.x + ir.width + xoff - 6 , ir.y + ( ir.height / 2.0 ) + yoff + 3 );
				p.lineTo( ir.x + ir.width + xoff - 6 , ir.y + ir.height + yoff - 1 );
				p.lineTo( ir.x + ir.width + xoff - 11 , ir.y + ir.height + yoff - 1 );
				
				g.draw( p );
				
			}
			
			
			@Override
			public double getDelx()
			{
				return( 10.0 );
			}


			
		},
		/**
		 * Mode for a vertical brace.
		 */
		VERT
		{

			@Override
			public void draw(MfencedRendNode rend, Graphics2D g, double xoff,
					double yoff) {
				
				rend.script.draw(g, xoff , yoff);
				
				Path2D.Double p = new Path2D.Double();
				
				Rectangle2D.Double ir = rend.getImgRect();
				
				p.moveTo( ir.x + xoff + 1 , ir.y + yoff + 1 );
				p.lineTo( ir.x + xoff + 1 , ir.y + ir.height + yoff - 1 );
				
				p.moveTo( ir.x + ir.width + xoff - 1 , ir.y + yoff + 1 );
				p.lineTo( ir.x + ir.width + xoff - 1 , ir.y + ir.height + yoff - 1 );
				
				g.draw( p );
				
			}
			
			
			@Override
			public double getDelx()
			{
				return( 5.0 );
			}


			
		};
		
		/**
		 * Draws the mfenced production.
		 * @param rend The parsed production to render.
		 * @param g The graphics context in which to render.
		 * @param xoff The X-Axis offset.
		 * @param yoff The Y-Axis offset.
		 */
		public abstract void draw( MfencedRendNode rend , Graphics2D g , double xoff , double yoff);
		
		/**
		 * Gets the horizontal spacing for the mode.
		 * @return The horizontal spacing for the mode.
		 */
		public abstract double getDelx();
		
	};
	
	/**
	 * The parsed production for the script that is inside the mfenced production.
	 */
	protected ParseRendNode script;
	
	/**
	 * The rendering mode for the mfenced production.
	 */
	protected RendMode rendMode;

	
	/**
	 * Constructs the node.
	 * @param _script The parsed production for the script that is inside the mfenced production.
	 * @param _rendMode The rendering mode for the mfenced production.
	 */
	public MfencedRendNode( ParseRendNode _script , RendMode _rendMode ) {
		super( _script.next );
		script = _script;
		rendMode = _rendMode;
	}

	@Override
	public void draw(Graphics2D g, double xoff, double yoff) {
		rendMode.draw( this , g , xoff + xOffset , yoff + yOffset );
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MfencedRendNode p0 = new MfencedRendNode( script , rendMode );
		p0.next = nxt;
		ds.insert( p0 );
		return( p0 );
	}
	
	
	@Override
	public void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc )
	{
		// next = null;
		
		script.calcRects(inFont, fontSz, tempFrc);
		
		
		final double delx = rendMode.getDelx();
		
		
		imgRect = new Rectangle2D.Double( script.getImgRect().x - delx , 
				script.getImgRect().y - 5.0 , 
				script.getImgRect().width + 2.0 * delx , 
				script.getImgRect().height + 10.0 );
		
		connRect = imgRect;
		
	}

	
}

