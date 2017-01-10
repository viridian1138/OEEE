






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







package meta;

import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.Externalizable;
import java.io.OutputStream;


/**
 * @author thorngreen
 *
 * An extension to XMLEncoder that supports a subset of Externalizable objects.
 */
public class ExXMLEncoder extends XMLEncoder {
    
    protected static final ExPersistenceDelegate EX_DEL =
            new ExPersistenceDelegate();
    
    /**
     * Constructs the encoder.
     * @param arg0 The output stream to which to encode.
     */
    public ExXMLEncoder(OutputStream arg0) {
        super(arg0);
    }
    
    @Override
    public PersistenceDelegate getPersistenceDelegate(Class in) {
        PersistenceDelegate ret = ( in != null ) && !( in.isArray() ) &&
                ( Externalizable.class.isAssignableFrom( in ) )
                ? EX_DEL
                : super.getPersistenceDelegate(in);
        return( ret );
    }
    
    
}

