package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import org.eclipse.paho.client.mqttv3.MqttTopic;

class BoundedByteString extends LiteralByteString {
    private final int bytesLength;
    private final int bytesOffset;

    BoundedByteString(byte[] bArr, int i, int i2) {
        super(bArr);
        if (i < 0) {
            throw new IllegalArgumentException(new StringBuilder(29).append("Offset too small: ").append(i).toString());
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(new StringBuilder(29).append("Length too small: ").append(i).toString());
        }
        if (((long) i) + ((long) i2) > bArr.length) {
            throw new IllegalArgumentException(new StringBuilder(48).append("Offset+Length too large: ").append(i).append(MqttTopic.SINGLE_LEVEL_WILDCARD).append(i2).toString());
        }
        this.bytesOffset = i;
        this.bytesLength = i2;
    }

    @Override
    public byte byteAt(int i) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(28).append("Index too small: ").append(i).toString());
        }
        if (i >= size()) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(41).append("Index too large: ").append(i).append(", ").append(size()).toString());
        }
        return this.bytes[this.bytesOffset + i];
    }

    @Override
    public int size() {
        return this.bytesLength;
    }

    @Override
    protected int getOffsetIntoBytes() {
        return this.bytesOffset;
    }

    @Override
    protected void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.bytes, getOffsetIntoBytes() + i, bArr, i2, i3);
    }

    @Override
    public Iterator<Byte> iterator() {
        return new BoundedByteIterator();
    }

    private class BoundedByteIterator implements ByteString.ByteIterator {
        private final int limit;
        private int position;

        private BoundedByteIterator() {
            int offsetIntoBytes = BoundedByteString.this.getOffsetIntoBytes();
            this.position = offsetIntoBytes;
            this.limit = offsetIntoBytes + BoundedByteString.this.size();
        }

        @Override
        public boolean hasNext() {
            return this.position < this.limit;
        }

        @Override
        public Byte next() {
            return Byte.valueOf(nextByte());
        }

        @Override
        public byte nextByte() {
            if (this.position >= this.limit) {
                throw new NoSuchElementException();
            }
            byte[] bArr = BoundedByteString.this.bytes;
            int i = this.position;
            this.position = i + 1;
            return bArr[i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
