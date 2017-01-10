






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
import java.io.ObjectInput;



/**
 * @author thorngreen
 *
 * A version of ObjectInput that is called by ExFac upon a decode request from a
 * Decoder such as XMLDecoder.
 */
public class DecoderObjectInput implements ObjectInput {
    
	/**
	 * The current index of the decoding.
	 */
    protected int index = 0;
    
    /**
     * The set of objects to be decoded.
     */
    protected Object[] readObjs = null;
    
    /**
     * Constructs the decoder.
     * @param _readObjs The set of objects to be decoded.
     */
    public DecoderObjectInput(Object[] _readObjs) {
        readObjs = _readObjs;
    }
    
    @Override
    public Object readObject() throws IOException {
        index++;
        return (readObjs[index]);
    }
    
    /**
     * Decodes the contents of readObjs into an Externalizable.
     * @return The decoded Externalizable.
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Externalizable decodeObjects()
    throws
            ClassNotFoundException,
            IOException,
            IllegalAccessException,
            InstantiationException {
        String cname = (String) (readObjs[0]);
        Object obj = ( Class.forName( cname, true, Meta.getDefaultClassLoader() ) ).newInstance();
        Externalizable ret = (Externalizable) (obj);
        ret.readExternal(this);
        return (ret);
    }
    
    /**
     * Throws an UndefinedOperation exception.
     */
    protected void throwEx() {
        throw (new Meta.UndefinedOperation() );
    }
    
    @Override
    public int read() throws IOException {
        throwEx();
        return 0;
    }
    
    @Override
    public int read(byte[] arg0) throws IOException {
        throwEx();
        return 0;
    }
    
    @Override
    public int read(byte[] arg0, int arg1, int arg2) throws IOException {
        throwEx();
        return 0;
    }
    
    @Override
    public long skip(long arg0) throws IOException {
        throwEx();
        return 0;
    }
    
    @Override
    public int available() throws IOException {
        throwEx();
        return 0;
    }
    
    @Override
    public void close() throws IOException {
        // Does Nothing.
        
    }
    
    @Override
    public void readFully(byte[] arg0) throws IOException {
        throwEx();
    }
    
    @Override
    public void readFully(byte[] arg0, int arg1, int arg2) throws IOException {
        throwEx();
    }
    
    @Override
    public int skipBytes(int arg0) throws IOException {
        throwEx();
        return 0;
    }
    
    @Override
    public boolean readBoolean() throws IOException {
        Boolean intv = (Boolean) (readObject());
        return (intv.booleanValue());
    }
    
    @Override
    public byte readByte() throws IOException {
        Byte intv = (Byte) (readObject());
        return (intv.byteValue());
    }
    
    @Override
    public int readUnsignedByte() throws IOException {
        throwEx();
        return 0;
    }
    
    @Override
    public short readShort() throws IOException {
        Short intv = (Short) (readObject());
        return (intv.shortValue());
    }
    
    @Override
    public int readUnsignedShort() throws IOException {
        throwEx();
        return 0;
    }
    
    @Override
    public char readChar() throws IOException {
        Character intv = (Character) (readObject());
        return (intv.charValue());
    }
    
    @Override
    public int readInt() throws IOException {
        Integer intv = (Integer) (readObject());
        return (intv.intValue());
    }
    
    @Override
    public long readLong() throws IOException {
        Long intv = (Long) (readObject());
        return (intv.longValue());
    }
    
    @Override
    public float readFloat() throws IOException {
        Float intv = (Float) (readObject());
        return (intv.floatValue());
    }
    
    @Override
    public double readDouble() throws IOException {
        Double intv = (Double) (readObject());
        return (intv.doubleValue());
    }
    
    @Override
    public String readLine() throws IOException {
        throwEx();
        return null;
    }
    
    @Override
    public String readUTF() throws IOException {
        throwEx();
        return null;
    }
    
}
