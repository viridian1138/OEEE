






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

import simplealgebra.symbolic.DroolsSession;


/**
 * Node indicating the end of a MathML mfenced production,
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
public class MfencedStartNode extends ParseNode {
	
	/**
	 * The rendering mode for the mfenced entity.
	 */
	protected MfencedRendNode.RendMode rendMode;

	/**
	 * Constructs the node.
	 * @param _rendMode The rendering mode for the mfenced entity.
	 * @param _next The next node in the list.
	 */
	public MfencedStartNode( MfencedRendNode.RendMode _rendMode , ParseNode _next) {
		super(_next);
		rendMode = _rendMode;
	}
	
	/**
	 * Applies the parsing of an mfenced production.
	 * @param script The node enclosed in the parsed mfenced production.
	 * @param endNode The production indicating the end of the mfenced.
	 * @param ds The Drools session.
	 * @return The constructed rendering node.
	 */
	public MfencedRendNode applyParse( ParseRendNode script , ParseNode endNode , DroolsSession ds )
	{
		MfencedRendNode r = new MfencedRendNode( script , rendMode );
		r.next = endNode.next;
		ds.insert( r );
		return( r );
	}
	
	
	@Override
	public ParseNode applyReng( ParseNode nxt , DroolsSession ds )
	{
		MfencedStartNode p0 = new MfencedStartNode( rendMode , nxt );
		ds.insert( p0 );
		return( p0 );
	}

	
}

