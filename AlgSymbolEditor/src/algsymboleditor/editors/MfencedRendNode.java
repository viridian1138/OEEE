






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
import java.awt.geom.Path2D;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating a renderable version of an mrow.
 * @author tgreen
 *
 */
public class MfencedRendNode extends ParseRendNode {
	
	
	public static enum RendMode
	{
		
		CIRC
		{

			@Override
			public void draw(MfencedRendNode rend, Graphics2D g, int xoff,
					int yoff) {
				
				rend.script.draw(g, xoff + 10 , yoff);
				
				Path2D.Double p = new Path2D.Double();
				
				p.moveTo( xoff + 5 , yoff - 10 );
				p.curveTo( xoff , yoff - 5 , xoff , yoff + 5 , xoff + 5 , yoff + 10 );
				
				p.moveTo( xoff + 25 , yoff - 10 );
				p.curveTo( xoff + 30 , yoff - 5 , xoff + 30 , yoff + 5 , xoff + 25 , yoff + 10 );
				
				g.draw( p );
				
			}

	
			
		},
		SQUARE
		{

			@Override
			public void draw(MfencedRendNode rend, Graphics2D g, int xoff,
					int yoff) {
				
				rend.script.draw(g, xoff + 10 , yoff);
				
				Path2D.Double p = new Path2D.Double();
				
				p.moveTo( xoff + 5 , yoff - 10 );
				p.lineTo( xoff , yoff - 10 );
				p.lineTo( xoff , yoff + 10 );
				p.lineTo( xoff + 5 , yoff + 10 );
				
				p.moveTo( xoff + 25 , yoff - 10 );
				p.lineTo( xoff + 30 , yoff - 10 );
				p.lineTo( xoff + 30 , yoff + 10 );
				p.lineTo( xoff + 25 , yoff + 10 );
				
				g.draw( p );
				
			}


			
		},
		CURLY
		{

			@Override
			public void draw(MfencedRendNode rend, Graphics2D g, int xoff,
					int yoff) {
				
				rend.script.draw(g, xoff + 10 , yoff);
				
				Path2D.Double p = new Path2D.Double();
				
				p.moveTo( xoff + 5 , yoff - 10 );
				p.lineTo( xoff , yoff - 10 );
				p.lineTo( xoff , yoff - 3 );
				p.lineTo( xoff - 5 , yoff );
				p.lineTo( xoff , yoff + 3 );
				p.lineTo( xoff , yoff + 10 );
				p.lineTo( xoff + 5 , yoff + 10 );
				
				p.moveTo( xoff + 25 , yoff - 10 );
				p.lineTo( xoff + 30 , yoff - 10 );
				p.lineTo( xoff + 30 , yoff - 3 );
				p.lineTo( xoff + 35 , yoff );
				p.lineTo( xoff + 30 , yoff + 3 );
				p.lineTo( xoff + 30 , yoff + 10 );
				p.lineTo( xoff + 25 , yoff + 10 );
				
				g.draw( p );
				
			}


			
		},
		VERT
		{

			@Override
			public void draw(MfencedRendNode rend, Graphics2D g, int xoff,
					int yoff) {
				
				rend.script.draw(g, xoff + 10 , yoff);
				
				Path2D.Double p = new Path2D.Double();
				
				p.moveTo( xoff + 5 , yoff - 10 );
				p.lineTo( xoff + 5 , yoff + 10 );
				
				p.moveTo( xoff + 25 , yoff - 10 );
				p.lineTo( xoff + 25 , yoff + 10 );
				
				g.draw( p );
				
			}


			
		};
		
		public abstract void draw( MfencedRendNode rend , Graphics2D g , int xoff ,int yoff);
		
	};
	
	protected ParseRendNode script;
	
	protected RendMode rendMode;

	/**
	 * Constructs the node.
	 * @param _parseValue The parsed token.
	 * @param _next The next node in the list.
	 */
	public MfencedRendNode( ParseRendNode a , RendMode c) {
		super( a.next );
		script = a;
		rendMode = c;
	}

	@Override
	public void draw(Graphics2D g, int xoff, int yoff) {
		rendMode.draw( this , g , xoff , yoff );
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MfencedRendNode p0 = new MfencedRendNode( script , rendMode );
		p0.next = nxt;
		ds.insert( p0 );
		return( p0 );
	}

	
}

