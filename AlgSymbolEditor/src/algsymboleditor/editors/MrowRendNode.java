






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
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating a renderable version of an mrow.
 * @author tgreen
 *
 */
public class MrowRendNode extends ParseRendNode {
	
	/**
	 * The list of parsed productions in the row.
	 */
	protected ArrayList<ParseRendNode> lst;

	
	/**
	 * Constructs the node.
	 * @param _lst The list of parsed productions in the row.
	 * @param _next The next node in the parsing list.
	 */
	public MrowRendNode(  ArrayList<ParseRendNode> _lst, ParseNode _next) {
		super(_next);
		lst = _lst;
	}

	
	@Override
	public void draw(Graphics2D g, double xoff, double yoff) {
		final int sz = lst.size();
		for( int cnt = 0 ; cnt < sz ; cnt++ )
		{
			lst.get( cnt ).draw(g, xoff+xOffset, yoff+yOffset);
		}
	}
	
	
	
	@Override
	public void calcRects( final Font inFont , final java.lang.Double fontSz , final FontRenderContext tempFrc )
	{
		// next = null;
		
		final int sz = lst.size();
		for( int cnt = 0 ; cnt < sz ; cnt++ )
		{
			lst.get( cnt ).calcRects(inFont, fontSz, tempFrc);
		}
		
		double cnx = 0.0;
		for( int cnt = 0 ; cnt < sz ; cnt++ )
		{
			lst.get( cnt ).setxOffset(cnx);
			cnx = cnx + lst.get( cnt ).getConnRect().width;
		}
		
		
		connRect = buildConnRect( lst );
		imgRect = buildImgRect( lst );
	}
	
	
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MrowRendNode p0 = new MrowRendNode( lst , nxt );
		ds.insert( p0 );
		return( p0 );
	}

	
}

