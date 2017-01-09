






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

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.io.Externalizable;


/**
 * @author thorngreen
 *
 * Persistence delegate for encoding a subset of Externalizable objects.
 */
public class ExPersistenceDelegate extends PersistenceDelegate {
    
    /**
     * Constructs the delegate.
     */
    public ExPersistenceDelegate() {
        super();
    }
    
    @Override
    protected Expression instantiate(Object arg0, Encoder arg1) {
        Expression ret = null;
        try {
            Externalizable e = (Externalizable) arg0;
            EncoderObjectOutput eo = new EncoderObjectOutput();
            Object[] params = { eo.encodeObjects( e ) };
            ret = new Expression(e, ExFac.class, "create", params);
        } catch (Exception ex) {
            throw (new WrapRuntimeException(ex));
        }
        return (ret);
    }
    
}
