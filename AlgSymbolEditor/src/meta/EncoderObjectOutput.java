






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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.util.Vector;



/**
 * @author thorngreen
 *
 * A version of ObjectOutput for supporting Encoders such as XMLEncoder.
 */
public class EncoderObjectOutput implements ObjectOutput {
    
	/**
	 * Constructs the encoder.
	 */
    public EncoderObjectOutput() {
    }
    
    /**
     * Vector used to store the results of the encoding.
     */
    protected final Vector<Object> objs = new Vector<Object>();
    
    @Override
    public void writeObject(Object arg0) throws IOException {
        objs.add(arg0);
        
    }
    
    /**
     * Encodes an Externalizable into a set of objects.
     * @param in The Externalizable to be encoded.
     * @return The result of the encoding.
     * @throws IOException
     */
    public Object[] encodeObjects(Externalizable in) throws IOException {
        objs.add(in.getClass().getName());
        in.writeExternal(this);
        return (objs.toArray());
    }
    
    /**
     * Throws an UndefinedOperation exception.
     */
    protected void throwEx() {
        throw ( new Meta.UndefinedOperation() );
    }
    
    @Override
    public void write(int arg0) throws IOException {
        throwEx();
        
    }
    
    @Override
    public void write(byte[] arg0) throws IOException {
        throwEx();
    }
    
    @Override
    public void write(byte[] arg0, int arg1, int arg2) throws IOException {
        throwEx();
    }
    
    @Override
    public void flush() throws IOException {
        // Does Nothing.
    }
    
    @Override
    public void close() throws IOException {
        // Does Nothing.
        
    }
    
    @Override
    public void writeBoolean(boolean arg0) throws IOException {
        writeObject(new Boolean(arg0));
    }
    
    @Override
    public void writeByte(int arg0) throws IOException {
        writeObject(new Byte((byte) arg0));
    }
    
    @Override
    public void writeShort(int arg0) throws IOException {
        writeObject(new Short((short) arg0));
    }
    
    @Override
    public void writeChar(int arg0) throws IOException {
        writeObject(new Character((char) arg0));
    }
    
    @Override
    public void writeInt(int arg0) throws IOException {
        writeObject(new Integer(arg0));
    }
    
    @Override
    public void writeLong(long arg0) throws IOException {
        writeObject(new Long(arg0));
    }
    
    @Override
    public void writeFloat(float arg0) throws IOException {
        writeObject(new Float(arg0));
    }
    
    @Override
    public void writeDouble(double arg0) throws IOException {
        writeObject(new Double(arg0));
    }
    
    @Override
    public void writeBytes(String arg0) throws IOException {
        throwEx();
    }
    
    @Override
    public void writeChars(String arg0) throws IOException {
        throwEx();
    }
    
    @Override
    public void writeUTF(String arg0) throws IOException {
        throwEx();
    }
    
}
