






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


/**
 * Rule engine node for parsing the Context Free Language (CFL) in MathML.  Used to represent a symbolic refactoring in Drools ( <A href="http://drools.org">http://drools.org</A> ).
 * 
 * This documentation should be viewed using Firefox version 33.1.1 or above.
 * 
 * @author thorngreen
 *
 */
public class ParseReng {
	
	/**
	 * Constructs the node.
	 * 
	 * @param _strt The starting point of the refactoring.
	 * @param _end The result of the refactoring.
	 */
	public ParseReng( ParseNode _strt , ParseNode _end )
	{
		strt = _strt;
		end = _end;
	}
	
	/**
	 * Gets the starting point of the refactoring.
	 * 
	 * @return The starting point of the refactoring.
	 */
	public ParseNode getStrt() {
		return strt;
	}
	
	/**
	 * Gets the result of the refactoring.
	 * 
	 * @return The result of the refactoring.
	 */
	public ParseNode getEnd() {
		return end;
	}
	
	/**
	 * The starting point of the refactoring.
	 */
	private ParseNode strt;
	
	/**
	 * The result of the refactoring.
	 */
	private ParseNode end;

}



