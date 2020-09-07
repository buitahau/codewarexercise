package com.java8.tutorial.codewar.byte_steam;

import java.io.IOException;

public class ByteStream {

    private enum STATUS {
        UNDEFINED,
        WRITE,
        READ
    }

    private STATUS status;
    private byte[] bytes;
    private int count = 0;
    private int pos = 0;

    public ByteStream() throws IOException {
        bytes = new byte[50000];
        status = STATUS.UNDEFINED;
    }

    /**
     * Flip stream between write and read mode.
     * Before the first call of this method, the stream is neither in read nor write mode.
     * The first call of this method switches the stream to write mode.
     * After a switch to write mode, the stream is empty and bytes may be written.
     * After a switch to read mode, written bytes may be read.
     *
     * @throws IOException if an I/O error occurs
     */
    public void flip() throws IOException {

        if (status == STATUS.UNDEFINED) {
            status = STATUS.WRITE;
        } else if (status == STATUS.WRITE) {
            status = STATUS.READ;
        } else {
            status = STATUS.WRITE;
        }
    }

    /**
     * Write byte to stream.
     *
     * @param b byte
     * @throws IOException if stream is not in write mode or an I/O error occurs
     */
    public void write(byte b) throws IOException {

        if (status != STATUS.WRITE) {
            throw new IOException("Can not write");
        }

        bytes[count++] = b;
    }

    /**
     * Read byte from stream.
     *
     * @return -1 if stream is empty, byte value (0 to 255) otherwise
     * @throws IOException if stream is not in read mode or an I/O error occurs
     */
    public int read() throws IOException {
        if (status != STATUS.READ) {
            throw new IOException("Can not read");
        }

        if (pos >= count) {
            return -1;
        }

        return bytes[pos++];
    }
}

