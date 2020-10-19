package net.krm.optimizer.file;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

import net.krm.optimizer.colors.Color;
import net.krm.optimizer.colors.ColorException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import net.krm.optimizer.figures.v3.Rectangle;

public class TestFileService {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void testFileReadWriteByteArray1() {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        try {
            File file = folder.newFile("test.dat");
            System.out.print(file);
            //FileService.writeByteArrayToBinaryFile(file, arrayToWrite);
            assertTrue(file.exists());
            assertEquals(arrayToWrite.length, file.length());
            //byte[] arrayRead = FileService.readByteArrayFromBinaryFile(file);
            //assertArrayEquals(arrayToWrite, arrayRead);
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileReadWriteByteArray2() {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        try {
            String fileName = folder.newFile("test.dat").getPath();
            //FileService.writeByteArrayToBinaryFile(fileName, arrayToWrite);
            File file = new File(fileName);
            assertTrue(file.exists());
            assertEquals(arrayToWrite.length, file.length());
            //byte[] arrayRead = FileService.readByteArrayFromBinaryFile(fileName);
            //assertArrayEquals(arrayToWrite, arrayRead);
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testByteStreamReadWriteByteArray() {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        //try {
           // byte[] result = FileService.writeAndReadByteArrayUsingByteStream(arrayToWrite);
           // assertArrayEquals(new byte[]{0, 5, 67}, result);
        //} catch (IOException ex) {
            fail();
        //}
    }

    @Test
    public void testFileReadWriteByteArray1Buffered() {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        try {
            File file = folder.newFile("test.dat");
            //FileService.writeByteArrayToBinaryFileBuffered(file, arrayToWrite);
            assertTrue(file.exists());
            assertEquals(arrayToWrite.length, file.length());
            //byte[] arrayRead = FileService.readByteArrayFromBinaryFileBuffered(file);
            //assertArrayEquals(arrayToWrite, arrayRead);
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileReadWriteByteArray2Buffered() {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        try {
            String fileName = folder.newFile("test.dat").getPath();
            System.out.print(fileName);
           // FileService.writeByteArrayToBinaryFileBuffered(fileName, arrayToWrite);
            File file = new File(fileName);
            assertTrue(file.exists());
            assertEquals(arrayToWrite.length, file.length());
            //byte[] arrayRead = FileService.readByteArrayFromBinaryFileBuffered(fileName);
            //assertArrayEquals(arrayToWrite, arrayRead);
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileReadWriteRectangleBinary() throws ColorException {
        Rectangle rectToWrite = new Rectangle(10000, 10000, 20000, 20000, Color.RED);
        try {
            File file = folder.newFile("test.dat");
            //FileService.writeRectangleToBinaryFile(file, rectToWrite);
            assertTrue(file.exists());
            assertEquals(16, file.length());
            //Rectangle rectRead = FileService.readRectangleFromBinaryFile(file);
            //assertEquals(rectToWrite, rectRead);
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileReadRectangleArrayBinary() throws ColorException {
        int count = 5;
        Rectangle[] rectsToWrite = new Rectangle[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            rectsToWrite[i] = new Rectangle(random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(),
                    Color.RED);
        }
        try {
            File file = folder.newFile("test.dat");
            //FileService.writeRectangleArrayToBinaryFile(file, rectsToWrite);
            assertTrue(file.exists());
            assertEquals(count * 16, file.length());
           // Rectangle[] rectsRead = FileService.readRectangleArrayFromBinaryFileReverse(file);
            /*for (int i = 0; i < rectsRead.length / 2; i++) {
                Rectangle temp = rectsRead[i];
                rectsRead[i] = rectsRead[rectsRead.length - i - 1];
                rectsRead[rectsRead.length - i - 1] = temp;
            }*/
            //assertArrayEquals(rectsToWrite, rectsRead);
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileReadWriteRectangleTextOneLine() throws ColorException {
        Rectangle rectToWrite = new Rectangle(10000, 10000, 20000, 20000, Color.RED);
        try {
            File file = folder.newFile("test.txt");
            //FileService.writeRectangleToTextFileOneLine(file, rectToWrite);
            assertTrue(file.exists());
            assertEquals(1, Files.readAllLines(file.toPath()).size());
            //Rectangle rectRead = FileService.readRectangleFromTextFileOneLine(file);
            //assertEquals(rectToWrite, rectRead);
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileReadWriteRectangleTextFourLines() throws ColorException {
        Rectangle rectToWrite = new Rectangle(10000, 10000, 20000, 20000, Color.RED);
        try {
            File file = folder.newFile("test.txt");
            //FileService.writeRectangleToTextFileFourLines(file, rectToWrite);
            assertTrue(file.exists());
            assertEquals(4, Files.readAllLines(file.toPath()).size());
            //Rectangle rectRead = FileService.readRectangleFromTextFileFourLines(file);
            //assertEquals(rectToWrite, rectRead);
        } catch (IOException ex) {
            fail();
        }
    }

}
