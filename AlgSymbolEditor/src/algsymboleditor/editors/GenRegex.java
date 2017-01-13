






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




import meta.*;
import java.util.*;


/**
 * Generates regular expression tokens from a MathML input string.
 * 
 * @author tgreen
 *
 */
public class GenRegex {

	/**
	 * Callback map used in token generation.
	 */
	protected final HashMap<FlexString,Runnable> map = new HashMap<FlexString,Runnable>();
	
	/**
	 * The current ParseNode.
	 */
	protected ParseNode<Integer> pnode = null;
	
	/**
	 * The current LiteralRendNode, or null.
	 */
	protected LiteralRendNode<Integer> lnode = null;
	
	
	/**
	 * Generates a new LiteralRendNode.
	 */
	protected void glnode()
	{
		if( lnode == null )
		{
			ParseNode<Integer> anode = pnode;
			lnode = new LiteralRendNode( Integer.valueOf( 3 ) , null );
			anode.next = lnode;
			pnode = lnode;
		}
	}
	
	
	/**
	 * Constructs the Regex tokenizer.
	 */
	public GenRegex() {
		
		
		map.put( new FlexString( "<mrow>" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				ParseNode<Integer> anode = pnode;
				pnode = new MrowStartNode<Integer>( Integer.valueOf( 3 ) , null );
				anode.next = pnode;
				lnode = null;
			}
		});
		
		
		
		map.put( new FlexString( "</mrow>" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				ParseNode<Integer> anode = pnode;
				pnode = new MrowEndNode<Integer>( Integer.valueOf( 3 ) , null );
				anode.next = pnode;
				lnode = null;
			}
		});
		
		
		
		map.put( new FlexString( "<msup>" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				ParseNode<Integer> anode = pnode;
				pnode = new MsupStartNode<Integer>( Integer.valueOf( 3 ) , null );
				anode.next = pnode;
				lnode = null;
			}
		});
		
		
		
		map.put( new FlexString( "</msup>" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				ParseNode<Integer> anode = pnode;
				pnode = new MsupEndNode<Integer>( Integer.valueOf( 3 ) , null );
				anode.next = pnode;
				lnode = null;
			}
		});
		
		
		
		map.put( new FlexString( "</mi>" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				lnode = null;
			}
		});
		
		
		
		map.put( new FlexString( "</mo>" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				lnode = null;
			}
		});
		
		
		
		map.put( new FlexString( "</mn>" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				lnode = null;
			}
		});
		
		
		
		map.put( new FlexString( "&nabla;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 8711 );
			}
		});
		
		
		
		map.put( new FlexString( "&CenterDot;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 0xb7 );
			}
		});
		
		
		
		map.put( new FlexString( "&lt;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( '<' );
			}
		});
		
		
		
		map.put( new FlexString( "&gt;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( '>' );
			}
		});
		
		
		
		map.put( new FlexString( "&alpha;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 945 );
			}
		});
		
		
		
		map.put( new FlexString( "&beta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 946 );
			}
		});
		
		
		
		map.put( new FlexString( "&gamma;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 947 );
			}
		});
		
		
		
		map.put( new FlexString( "&delta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 948 );
			}
		});
		
		
		
		map.put( new FlexString( "&epsilon;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 949 );
			}
		});
		
		
		
		map.put( new FlexString( "&zeta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 950 );
			}
		});
		
		
		
		map.put( new FlexString( "&eta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 951 );
			}
		});
		
		
		
		map.put( new FlexString( "&theta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 952 );
			}
		});
		
		
		
		map.put( new FlexString( "&iota;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 953 );
			}
		});
		
		
		
		map.put( new FlexString( "&kappa;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 954 );
			}
		});
		
		
		
		map.put( new FlexString( "&lambda;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 955 );
			}
		});
		
		
		
		map.put( new FlexString( "&mu;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 956 );
			}
		});
		
		
		
		map.put( new FlexString( "&nu;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 957 );
			}
		});
		
		
		
		map.put( new FlexString( "&xi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 958 );
			}
		});
		
		
		
		map.put( new FlexString( "&omicron;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 959 );
			}
		});
		
		
		
		map.put( new FlexString( "&pi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 960 );
			}
		});
		
		
		
		map.put( new FlexString( "&rho;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 961 );
			}
		});
		
		
		
		map.put( new FlexString( "&sigma;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 963 );
			}
		});
		
		
		
		map.put( new FlexString( "&tau;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 964 );
			}
		});
		
		
		
		map.put( new FlexString( "&upsilon;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 965 );
			}
		});
		
		
		
		map.put( new FlexString( "&phi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 966 );
			}
		});
		
		
		
		map.put( new FlexString( "&chi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 967 );
			}
		});
		
		
		
		map.put( new FlexString( "&psi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 968 );
			}
		});
		
		
		
		map.put( new FlexString( "&omega;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 969 );
			}
		});
		
		

		
		
		
		
		
		
		map.put( new FlexString( "&Alpha;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 913 );
			}
		});
		
		
		
		map.put( new FlexString( "&Beta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 914 );
			}
		});
		
		
		
		
		map.put( new FlexString( "&Gamma;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 915 );
			}
		});
		
		
		
		map.put( new FlexString( "&Delta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 916 );
			}
		});
		
		
		
		map.put( new FlexString( "&Epsilon;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 917 );
			}
		});
		
		
		
		map.put( new FlexString( "&Zeta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 918 );
			}
		});
		
		
		
		map.put( new FlexString( "&Eta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 919 );
			}
		});
		
		
		
		map.put( new FlexString( "&Theta;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 920 );
			}
		});
		
		
		
		map.put( new FlexString( "&Iota;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 921 );
			}
		});
		
		
		
		map.put( new FlexString( "&Kappa;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 922 );
			}
		});
		
		
		
		map.put( new FlexString( "&Lambda;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 923 );
			}
		});
		
		
		
		map.put( new FlexString( "&Mu;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 924 );
			}
		});
		
		
		
		map.put( new FlexString( "&Nu;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 925 );
			}
		});
		
		
		
		map.put( new FlexString( "&Xi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 926 );
			}
		});
		
		
		
		map.put( new FlexString( "&Omicron;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 927 );
			}
		});
		
		
		
		map.put( new FlexString( "&Pi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 928 );
			}
		});
		
		
		
		map.put( new FlexString( "&Rho;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 929 );
			}
		});
		
		
		
		map.put( new FlexString( "&Sigma;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 931 );
			}
		});
		
		
		
		map.put( new FlexString( "&Tau;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 932 );
			}
		});
		
		
		
		map.put( new FlexString( "&Upsilon;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 933 );
			}
		});
		
		
		
		map.put( new FlexString( "&Phi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 934 );
			}
		});
		
		
		
		map.put( new FlexString( "&Chi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 935 );
			}
		});
		
		
		
		map.put( new FlexString( "&Psi;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 936 );
			}
		});
		
		
		
		map.put( new FlexString( "&Omega;" ) , 
				new Runnable()
		{
			@Override
			public void run()
			{
				glnode();
				lnode.getStr().insertChar( (char) 937 );
			}
		});
		
		
		
		
	}
	
	
	
	/**
	 * Generates a token list from a string.
	 * @param str The input string.
	 * @return The token list.
	 */
	public ParseNode<Integer> parse( FlexString str )
	{
		int index = 0;
		pnode = new MrowStartNode<Integer>( Integer.valueOf( 3 ) , null);
		lnode = null;
		final ParseNode<Integer> rnode = pnode;
		while( str.getChar( index ) != '\0' )
		{
			index = parse( str , index );
		}
		return( rnode.next );
	}
	
	
	
	/**
	 * Generates one token from a string.
	 * @param str The string to parse.
	 * @param index The current index in the string.
	 * @return The parsed token.
	 */
	protected int parse( FlexString str , int index )
	{
		final char ch = str.getChar( index );
		
		
		switch( ch )
		{
				
			case '&':
			{
				int offset = 1;
				while( str.getBaseChar(index, offset) != ';' )
				{
					offset = offset + 1;
				}
				final FlexString os = new FlexString();
				str.midString(index, index+offset, os);
				final Runnable runn = map.get( os );
				if( runn != null )
				{
					runn.run();
				}
				return( index + offset + 1 );
			}
				
			case '<':
			{
				int offset = 1;
				while( str.getBaseChar(index, offset) != '>' )
				{
					offset = offset + 1;
				}
				final FlexString os = new FlexString();
				str.midString(index, index+offset, os);
				final Runnable runn = map.get( os );
				if( runn != null )
				{
					runn.run();
				}
				return( index + offset + 1 );
			}
				
			default:
			{
				glnode();
				lnode.getStr().insertChar( ch );
				return( index + 1 );
			}
				
		}
		
		
		// return( index + 1 );
		
	}
	
	
	

}



