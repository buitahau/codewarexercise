package com.java8.tutorial.codewar.byte_steam;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.SplittableRandom;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ByteStreamTest {
    private static SplittableRandom rnd = new SplittableRandom();
    private static SplittableRandom rnd1;
    private static SplittableRandom rnd2;

    static {
        long seed = rnd.nextLong();
        rnd1 = new SplittableRandom(seed);
        rnd2 = new SplittableRandom(seed);
    }

    @Test
    public void test_0() throws IOException {
        ByteStream stream = new ByteStream();
        try { stream.write((byte)0); fail("exception expected - not in write mode"); } catch (IOException e) {}
        try { stream.read(); fail("exception expected - not in read mode"); } catch (IOException e) {}

        stream.flip();
        try { stream.read(); fail("exception expected - not in read mode"); } catch (IOException e) {}
        // write nothing

        stream.flip();
        try { stream.write((byte)0); fail("exception expected - not in write mode"); } catch (IOException e) {}
        assertEquals(-1, stream.read());
        assertEquals(-1, stream.read());

        stream.flip();
        try { stream.read(); fail("exception expected - not in read mode"); } catch (IOException e) {}
        stream.write((byte)0);

        stream.flip();
        try { stream.write((byte)0); fail("exception expected - not in write mode"); } catch (IOException e) {}
        assertEquals(0, stream.read());
        assertEquals(-1, stream.read());
        assertEquals(-1, stream.read());
    }

    @Test
    public void test_1() throws IOException {
        ByteStream stream = new ByteStream();

        byte[] input = "Hello World".getBytes();
        int b = input.length;

        stream.flip();
        for (int j = 0; j < b; j++) {
            stream.write(input[j]);
        }

        byte[] output = new byte[b];

        stream.flip();
        for (int j = 0; j < b; j++) {
            int d = stream.read();
            assertTrue("out of read storage", d >= 0);
            output[j] = (byte) d;
        }

        assertEquals(-1, stream.read());
        assertArrayEquals(input, output);

        input = "Good bye".getBytes();
        b = input.length;

        stream.flip();
        for (int j = 0; j < b; j++) {
            stream.write(input[j]);
        }

        output = new byte[b];

        stream.flip();
        for (int j = 0; j < b; j++) {
            int d = stream.read();
            assertTrue("out of read storage", d >= 0);
            output[j] = (byte) d;
        }

        assertEquals(-1, stream.read());
        assertArrayEquals(output, input);
    }

    @Test
    public void test_2_200() throws IOException {
        test(20, 10, 20);
    }

    @Test
    public void test_3_8000() throws IOException {
        test(8, 100, 80);
    }

    @Test
    public void test_4_400000() throws IOException {
        test(4, 1000, 400);
    }

    @Test
    public void test_5_20000000() throws IOException {
        test(2, 30000, 650);
    }

    @Test
    public void test_6_400000000() throws IOException {
        test(1, 400000, 1000);
    }

    private void test(int rep, int num, int size) throws IOException {
        ByteStream stream = new ByteStream();

        for (int r = 0; r < rep; r++) {
            int var = 4+rnd.nextInt(3); // 4 to 6 variants
            num = num-rnd.nextInt(num/10);
            byte[][] input = new byte[var][];
            for (int v = 0; v < var; v++) {
                int b = size+rnd.nextInt(size/10);
                input[v] = new byte[b];
                rnd.nextBytes(input[v]);
            }

            stream.flip();
            for (int v : (Iterable<Integer>) rnd1.ints(num, 0, var)::iterator) {
                int len = input[v].length;
                for (int j = 0; j < len; j++) {
                    stream.write(input[v][j]);
                }
            }

            stream.flip();
            for (int v : (Iterable<Integer>) rnd2.ints(num, 0, var)::iterator) {
                int len = input[v].length;
                for (int j = 0; j < len; j++) {
                    int d = stream.read();
                    if (d < 0) fail();
                    if ((byte)d != input[v][j]) fail();
                }
            }

            assertEquals(-1, stream.read());
        }
    }

}