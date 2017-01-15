






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
 * Single node (production or terminal) processed as part of the Context-Free Language (CFL) for MathML.
 * Uses Drools ( <A href="http://drools.org">http://drools.org</A> ).
 * 
 * This documentation should be viewed using Firefox version 33.1.1 or above.
 * 
 * @author tgreen
 *
 */
public abstract class ParseNode {
	
	
	/**
	 * Constructs the parsing node.
	 * @param _next The next node in the list.
	 */
	public ParseNode( ParseNode _next )
	{
		next = _next;
	}
	
	
	/**
	 * Applies Drools ( <A href="http://drools.org">http://drools.org</A> ) a rule engine node update.
	 * @param nxt The new next node.
	 * @param ds The rule session.
	 * @return The new parse node.
	 */
	public abstract ParseNode applyReng( ParseNode nxt , DroolsSession ds );
	
	
	
	/**
	 * The next value in the list.  Kept public so that Drools ( <A href="http://drools.org">http://drools.org</A> ) can have direct access.
	 */
	public ParseNode next = null;


	
}



