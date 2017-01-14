






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
 * Serves as a placeholder for a ParseNode as part of Context Free Language (CFL) parsing of MathML.
 * Uses Drools ( <A href="http://drools.org">http://drools.org</A> ).
 * 
 * This documentation should be viewed using Firefox version 33.1.1 or above.
 * 
 * @author thorngreen
 *
 */
public class ParsePlaceholder
{

	/**
	 * Constructs the placeholder.
	 * 
	 * @param _elem The enclosed elem.
	 */
	public ParsePlaceholder( ParseNode _elem )
	{
		elem = _elem;
	}
	
	/**
	 * Constructs the placeholder for use in a Drools ( <A href="http://drools.org">http://drools.org</A> ) session.
	 * 
	 * @param _elem The enclosed elem.
	 * @param ds The Drools session.
	 */
	public ParsePlaceholder( ParseNode _elem , DroolsSession ds )
	{
		this( _elem );
		ds.insert( this );
	}
	
	
	
	/**
	 * Gets the enclosed elem.
	 * 
	 * @return The enclosed elem.
	 */
	public ParseNode getElem() {
		return elem;
	}
	
	
	/**
	 * Sets the enclosed elem.
	 * 
	 * @param _elem The enclosed elem.
	 */
	public void setElem( ParseNode _elem ) {
		elem  = _elem;
	}


	/**
	 * The enclosed elem.
	 */
	private ParseNode elem;

}


