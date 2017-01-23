






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

import meta.FlexString;
import meta.HighLevelList;
import meta.Meta;
import meta.StdLowLevelList;

import org.eclipse.swt.dnd.Clipboard;

/**
 * Common elements used by multiple editor parts.
 * @author tgreen
 *
 */
public class AlgCommon {

	
	/**
	 * Prefix string used to disambiguate algebra text on the clipboard.
	 */
	public static final String MATCH_STR = "$$%%Alg%%";
	
	
	/**
	 * Common clipboard instance.
	 */
	static Clipboard clipboard = null;
	
	
	/**
	 * Puts the contents of the string at the end of the list.
	 * @param str The input string.
	 * @param lst The list in which to put the string.
	 */
	public static void performLstInsert( final String str , final HighLevelList<StdLowLevelList<FlexString>,FlexString> lst )
	{
		if( !( lst.empty() ) )
		{
			lst.searchHead();
			lst.left();
		}
		
		lst.insertRight( new FlexString( str ) );
		lst.setCopyMode( Meta.COPY_ALL );
		lst.setEraseMode( Meta.ERASE_ALL );
		
	}
	
	
	
	/**
	 * If present, deletes the last item from the list.
	 * @param lst The list from which to delete the item.
	 */
	public static void performDelete( final HighLevelList<StdLowLevelList<FlexString>,FlexString> lst )
	{
		if( !( lst.empty() ) )
		{
			lst.searchHead();
			lst.left();
			
			lst.eraseNodeInfo();
			
		}
		
	}
	
	
	
	/**
	 * Condenses the contents of a list into a FlexString.
	 * @param in The list to be condensed.
	 * @param myStr The string in which the results are to be inserted.
	 */
	public static void condenseText( final HighLevelList<StdLowLevelList<FlexString>,FlexString> in , final FlexString myStr )
	{
		
		
		if( !( in.empty() ) )
		{
			boolean mrowUp = false;
			in.searchHead();
			
			{
				final StdLowLevelList<FlexString> tmp = in.exportNode();
				mrowUp = tmp.right() != tmp;
			}
			
			
			if( mrowUp )
			{
				myStr.insertJavaString( "<mrow>" );
			}
			
			
			boolean done = false;

			while (!done) {
				FlexString ndStr = in.getNode();
				ndStr.insertString(myStr);

				in.right();
				done = in.getHead();
			}
			
			
			if( mrowUp )
			{
				myStr.insertJavaString( "</mrow>" );
			}
			
		}
		
	}
	
	
	/**
	 * Exports the MathML expression to the console.
	 * @param str The editor display string.
	 */
	public static void exportTextToConsole( final FlexString str )
	{
		
		System.out.println( "" );
		System.out.println( "<math display=\"inline\">" );
		
		int rcnt = 0;
		final int len = str.strlen();
		
		for( int index = 0 ; index < len ; index++ )
		{
			rcnt++;
			final char ch = str.getChar( index );
			if( ( rcnt > 50 ) && ( ( ch == '&' ) || ( ch == '<' ) ) )
			{
				System.out.println( "" );
				rcnt = 0;
			}
			System.out.print( ch );
		}
		
		System.out.println( "" );
		System.out.println( "</math>" );
		
	}
	
	
	
	
}


