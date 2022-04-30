package main.ua.advanced.practice6.iterator.tableIterator;

import main.ua.advanced.practice6.iterator.Iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;

public class TableIterator implements MyTableIterator {
    private String[] table;


    public TableIterator(String[] columns, int[] rows) {
        table = createAndFillTable(columns, rows);
    }

    private String[] createAndFillTable(String[] columns, int[] rows) {
        String[] elements = new String[columns.length * rows.length];
        int column = 0;
        int row = 0;
        for (int i = 0; i < elements.length; i++) {
            elements[i] = columns[column] + rows[row++];
            if (row == rows.length) {
                column++;
                row = 0;
            }
            if (column == columns.length) break;
        }
        return elements;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(table[i]);
            if (i < table.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < table.length;
            }

            @Override
            public String next() {
                try {
                    if (hasNext()) {
                        return table[current++];
                    }
                    throw new NoSuchElementException();
                } catch (NoSuchElementException e) {
                    Iterators.logger.log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
