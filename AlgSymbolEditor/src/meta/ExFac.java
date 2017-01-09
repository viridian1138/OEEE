






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

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.Externalizable;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;



/**
 * @author thorngreen
 *
 * Factory class called by the XMLDecoder for decoding instances of
 * Externalizable objects.  Also creates loader-aware XMLDecoder
 * instances.
 */
public class ExFac {
    
    public static Object create(Object _in) {
        Externalizable ret = null;
        try {
            Object[] in = (Object[]) _in;
            DecoderObjectInput di = new DecoderObjectInput(in);
            ret = di.decodeObjects();
        } catch (Exception ex) {
            throw (new WrapRuntimeException(ex));
        }
        return ret;
    }
    
    public static XMLDecoder createXMLDecoder(
            InputStream is,
            ClassLoader loader) {
        return (new XMLDecoder(is, loader));
    }
    
    public static XMLDecoder createXMLDecoder(InputStream is) {
        return (createXMLDecoder(is, Meta.getDefaultClassLoader()));
    }
    
    public static XMLEncoder createXMLEncoder(
            OutputStream os,
            ClassLoader cl) {
        Object ob = null;
        try {
            Class[] types = { OutputStream.class };
            Object[] params = { os };
            Class xmls = cl.loadClass(ExXMLEncoder.class.getName());
            Constructor cr = xmls.getConstructor(types);
            ob = cr.newInstance(params);
        } catch (Exception ex) {
            throw (new WrapRuntimeException(ex));
        }
        return ((XMLEncoder) ob);
    }
    
    public static XMLEncoder createXMLEncoder(OutputStream os) {
        return (createXMLEncoder(os, Meta.getDefaultClassLoader()));
    }
    
}
