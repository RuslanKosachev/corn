package net.krm.optimizer.file;

import com.google.gson.Gson;
import net.krm.optimizer.colors.Color;
import net.krm.optimizer.colors.ColorException;
import net.krm.optimizer.figures.v3.Rectangle;

import java.io.*;
import java.util.Scanner;
import java.util.StringJoiner;

/**
* класс для операций ввода-вывода, который позволяет записывать различные данные в двоичные и текстовые файлы
* */
public class FileService {

    public static final String CHARSET_NAME = "UTF-8";

    private static final int SIZE_RECTANGLE = 16;
    private static final String LINER = "\n";

    /**
     * 1. Записывает массив байтов в двоичный файл, имя файла задается текстовой строкой.
     */
    public static void  writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    /**
     * 2. Записывает массив байтов в двоичный файл, имя файла задается экземпляром класса {@link File}.
     */
     public static void  writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
         try (OutputStream out = new FileOutputStream(file);)
         {
             out.write(array);
         } catch (IOException e) {
             throw  e;
         }
     }

    /**
     * 3. Читает массив байтов из двоичного файла, имя файла задается текстовой строкой.
     */
    public static byte[]  readByteArrayFromBinaryFile(String fileName) throws IOException {
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    /**
     * 4. Читает массив байтов из двоичного файла, имя файла задается экземпляром класса {@link File}.
     */
    public static byte[]  readByteArrayFromBinaryFile(File file) throws IOException {
        try (InputStream in = new FileInputStream(file);)
        {
            return readByte(in);
        } catch (IOException e) {
           throw  e;
        }
    }

    /**
     * 5. Записывает массив байтов в ByteArrayOutputStream, создает на основе данных в полученном
     * ByteArrayOutputStream экземпляр ByteArrayInputStream и читает из ByteArrayInputStream
     * байты с четными номерами. Возвращает массив прочитанных байтов.
     */
    public static byte[]  writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        try (ByteArrayInputStream inByteArray = new ByteArrayInputStream(array);
             ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();)
        {
            byte[] b = new byte[1];
            int itemByte = 0;
            while (-1 < inByteArray.read(b, 0, 1)) {
                if (itemByte % 2 == 0) {
                    outByteArray.write(b[0]);
                }
                itemByte++;
            }
            return outByteArray.toByteArray();
        } catch (IOException e) {
            throw  e;
        }
    }

    /**
     * 6. Записывает массив байтов в двоичный файл, используя буферизованный вывод,
     * имя файла задается текстовой строкой.
     */
    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFileBuffered(new File(fileName), array);
    }

    /**
     * 7. Записывает массив байтов в двоичный файл, используя буферизованный вывод,
     * имя файла задается экземпляром класса {@link File}.
     */
    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        try (OutputStream out = new BufferedOutputStream(
                                    new FileOutputStream(file));)
        {
            out.write(array);
        } catch (IOException e) {
            throw  e;
        }
    }

    /**
     * 8. Читает массив байтов из двоичного файла, используя буферизованный ввод,
     * имя файла задается текстовой строкой.
     */
    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        return readByteArrayFromBinaryFileBuffered(new File(fileName));
    }

    /**
     * 9. Читает массив байтов из двоичного файла, используя буферизованный ввод,
     * имя файла задается экземпляром класса {@link File}.
     */
    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        try (InputStream in = new BufferedInputStream(
                                new FileInputStream(file));)
        {
            return readByte(in);
        } catch (IOException e) {
            throw  e;
        }
    }

    /**
     * 10. Записывает Rectangle в двоичный файл, имя файла задается экземпляром
     * класса {@link File}. Поле цвета не записывает.
     */
    public static void  writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {
        try (DataOutputStream outData = new DataOutputStream(
                                            new FileOutputStream(file))) {
            writeRectangle(rect, outData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 11. Читает данные для {@link Rectangle} из двоичного файла и создает на их
     * основе экземпляр {@link Rectangle}, имя файла задается экземпляром
     * класса File. Предполагается, что данные в файл записаны в
     * формате метода {@link #writeRectangleToBinaryFile(File, Rectangle)}.
     * Устанавливает в Rectangle цвет “RED”.
     */
    public static Rectangle readRectangleFromBinaryFile(File file) throws ColorException, IOException {
        try (DataInputStream inData = new DataInputStream(
                                            new FileInputStream(file)))
        {
            return readRectangle(inData, Rectangle.DEFAULT_COLOR);
        } catch (IOException e) {
            throw  e;
        }
    }

    /**
     * 12. Записывает массив из Rectangle в двоичный файл, имя файла задается
     * экземпляром класса {@link File}. Поле цвета не записывает.
     */
    public static void writeRectangleArrayToBinaryFile(File file, Rectangle[] rects ) {
        try (DataOutputStream outData = new DataOutputStream(
                                            new BufferedOutputStream(
                                                new FileOutputStream(file)))) {
            for (Rectangle rect : rects) {
                writeRectangle(rect, outData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 13. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
     * Метод читает вначале данные о последнем записанном в файл {@link Rectangle}
     * и создает на их основе экземпляр {@link Rectangle}, затем читает данные
     * следующего с конца {@link Rectangle} и создает на их основе экземпляр Rectangle
     * и т.д. вплоть до данных для самого первого записанного в файл {@link Rectangle}.
     * Из созданных таким образом экземпляров Rectangle создается массив, который метод.
     * Устанавливает в Rectangle цвет “RED”.
     */
    public static Rectangle[] readRectangleArrayFromBinaryFileReverse(File file) throws ColorException, IOException {
        try (RandomAccessFile randomAccess = new RandomAccessFile(file, "r");)
        {
            byte[] rectangleToBytes = new byte[SIZE_RECTANGLE];
            Rectangle[] rectangles = new Rectangle[(int)randomAccess.length() / SIZE_RECTANGLE];
            int itemRectangle = 0;

            for (int i = (int)randomAccess.length() - SIZE_RECTANGLE; i >= 0; i -= SIZE_RECTANGLE) {
                randomAccess.seek(i);
                randomAccess.read(rectangleToBytes, 0, SIZE_RECTANGLE);

                try (DataInputStream inData = new DataInputStream(
                                                new ByteArrayInputStream(rectangleToBytes)))
                {
                    rectangles[itemRectangle] = readRectangle(inData, Rectangle.DEFAULT_COLOR);
                    itemRectangle++;
                } catch (IOException e) {
                    throw  e;
                }
            }
            return rectangles;
        } catch (IOException e) {
            throw  e;
        }
    }

    /**
     * 14. Записывает Rectangle в текстовый файл в одну строку, имя файла задается
     * экземпляром класса {@link File}. Поля в файле разделяются пробелами.
     * Поле цвета не записывает.
     */
    public static void writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {
        try (PrintWriter out = new PrintWriter (new FileWriter(file));)
        {
            writeRectangle(rect, out, "%1$d %2$d %3$d %4$d");
        } catch (IOException e) {
            throw  e;
        }
    }

    /**
     * 15. Читает данные для Rectangle из текстового файла и создает на их основе
     * экземпляр {@link Rectangle}, имя файла задается экземпляром класса
     * {@link File}. Предполагается, что данные в файл записаны в формате
     * метода {@link #writeRectangleToTextFileOneLine(File, Rectangle)}.
     * Устанавливает в Rectangle цвет “RED”.
     */
    public static Rectangle readRectangleFromTextFileOneLine(File file) throws IOException, ColorException {
        try (Scanner scanner = new Scanner(new FileReader(file));)
        {
            return readRectangle(scanner, Rectangle.DEFAULT_COLOR);
        } catch (IOException e) {
            throw  e;
        }
    }

    /**
     * 16. Записывает {@link Rectangle} в текстовый файл, каждое число в отдельной строке,
     * имя файла задается экземпляром класса {@link File}.
     * Поле цвета не записывает.
     */
    public static void writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(file));)
        {
            writeRectangle(rect, out, "%1$d%n%2$d%n%3$d%n%4$d");
        } catch (IOException e) {
            throw  e;
        }
    }

    /**
     * 17. Читает данные для {@link Rectangle} из текстового файла и создает
     * на их основе экземпляр {@link Rectangle}, имя файла задается
     * экземпляром класса {@link File}. Предполагается, что данные в файл
     * записаны в формате метода {@link #writeRectangleToTextFileFourLines(File, Rectangle)}.
     * Устанавливает в Rectangle цвет “RED”.
     */
    public static Rectangle readRectangleFromTextFileFourLines(File file) throws IOException, ColorException {
        try (Scanner scanner = new Scanner(new FileReader(file));)
        {
            return readRectangle(scanner, Rectangle.DEFAULT_COLOR);
        } catch (IOException e) {
            throw  e;
        }
    }


    private static void  writeRectangle(Rectangle rect, DataOutputStream outData) throws IOException {
        outData.writeInt(rect.getTopLeft().getX());
        outData.writeInt(rect.getTopLeft().getY());
        outData.writeInt(rect.getBottomRight().getX());
        outData.writeInt(rect.getBottomRight().getY());
    }

    private static void writeRectangle(Rectangle rect, PrintWriter out, String format) {
        out.printf(format,
                   rect.getTopLeft().getX(),
                   rect.getTopLeft().getY(),
                   rect.getBottomRight().getX(),
                   rect.getBottomRight().getY());
    }

    private static byte[] readByte(InputStream in) throws IOException {
        try (ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();)
        {
            byte[] b = new byte[1];
            while (-1 < in.read(b, 0, 1)) {
                outByteArray.write(b[0]);
            }

            return outByteArray.toByteArray();
        } catch (IOException e) {
            throw  e;
        }
    }

    private static Rectangle readRectangle(DataInputStream inData, String color) throws ColorException, IOException {
        return new Rectangle(inData.readInt(), inData.readInt(), inData.readInt(), inData.readInt(),
                             Color.colorFromString(color));
    }

    private static Rectangle readRectangle(Scanner scanner, String color) throws ColorException{
        return new Rectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                             Color.colorFromString(color));
    }

}
