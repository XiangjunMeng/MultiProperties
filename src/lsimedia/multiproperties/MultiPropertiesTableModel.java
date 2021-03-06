/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsimedia.multiproperties;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * The first column are the keys
 * 
 * @author sbodmer
 */
public class MultiPropertiesTableModel implements TableModel {
    ArrayList<TableModelListener> listeners = new ArrayList<>();

    // ArrayList<Column> columns = new ArrayList<Column>();

    DefaultListModel<Column> columns = new DefaultListModel<>();
    
    /**
     * The records
     */
    ArrayList<Record> records = new ArrayList<>();

    public MultiPropertiesTableModel() {
        //---
        columns.addElement(new Column("Key"));
    }

    //**************************************************************************
    //*** API
    //**************************************************************************
    public DefaultListModel<Column> getColumnListModel() {
        return columns;
        
    }
    
    public void addColumn(Column c) {
        columns.addElement(c);

        TableModelEvent e = new TableModelEvent(this, TableModelEvent.HEADER_ROW);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
    }
    
    
    public void removeColumn(Column c) {
        columns.removeElement(c);
        
        TableModelEvent e = new TableModelEvent(this, TableModelEvent.HEADER_ROW);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
    }
    
    /**
     * Fire event to nonitfy all elements have changed (columns too)
     */
    public void fireTableChanged() {
        TableModelEvent e = new TableModelEvent(this, TableModelEvent.HEADER_ROW);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
    }
    
    public void swapColumn(int from, int to) {
        if (from == 0) return;
        if (to == 0) return;
                
        Column f = columns.get(from);
        Column t = columns.get(to);
        columns.set(to, f);
        columns.set(from, t);
                
        //--- Also swap the records
        for (int i=0;i<records.size();i++) {
            Record rec = records.get(i);
            if (rec instanceof PropertyRecord) {
                PropertyRecord pr = (PropertyRecord) rec;
                pr.swapColumn(from-1, to-1);
            }
        }
        
        TableModelEvent e = new TableModelEvent(this, TableModelEvent.HEADER_ROW);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
                
    }
    
    public Column getColumn(int index) {
        return columns.get(index);
    }
    
    /**
     * Return the column index for the passed columns name
     * @param title
     * @return 
     */
    public int getColumnIndex(String name) {
        for (int i=0;i<columns.getSize();i++) {
            Column c = columns.get(i);
            if (c.getName().equals(name)) return i;
        }
        return -1;
    }
    
    public Record getRecord(int index) {
        return records.get(index);
    }

    /**
     * Index 0 is always the "Key" column
     * 
     * @return 
     */
    public String[] getColumnNames() {
        String names[] = new String[columns.size()];
        for (int i=0;i<columns.getSize();i++) {
            Column c = columns.get(i);
            names[i] = c.getName();
        }
        return names;
    }
    
    /**
     * Return the first occurence of the key
     * @param key
     * @return 
     */
    public Record getRecord(String key) {
        for (int i=0;i<records.size();i++) {
            Record rec = records.get(i);
            String c = rec.getKey();
            if (c == null) continue;
            
            if (c.equals(key)) return rec;
        }
        return null;
    }
    
    public void addRecord(Record r) {
        records.add(r);

        TableModelEvent e = new TableModelEvent(this);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
    }

    public void insertRecord(int index, Record record) {
        records.add(index, record);
        
        TableModelEvent e = new TableModelEvent(this);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
    }
    
    public void removeRecord(Record r) {
        records.remove(r);

        TableModelEvent e = new TableModelEvent(this);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
    }

    public Record removeRecord(int index) {
        Record rec = records.remove(index);
        TableModelEvent e = new TableModelEvent(this);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
        return rec;
    }
    
    public void removeAllElements() {
        records.clear();
        columns.clear();
        columns.addElement(new Column("Key"));
        
        TableModelEvent e = new TableModelEvent(this);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
    }

    
    
    /**
     * Swapt to record value
     * @param from
     * @param to 
     */
    public void swapValue(int from, int to) {
        Record f = records.get(from);
        Record t = records.get(to);
        records.set(to, f);
        records.set(from, t);
        
        TableModelEvent e = new TableModelEvent(this, from, to);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
    }
    
    /**
     * Sort the record by key
     */
    public void sort() {
        Object a[] = records.toArray();
        Arrays.sort(a);
        records.clear();
        for(int i=0;i<a.length;i++) records.add((Record) a[i]);
        
        TableModelEvent e = new TableModelEvent(this, 0, records.size()-1);
        for (int i = 0;i < listeners.size();i++) listeners.get(i).tableChanged(e);
        
    }
    //**************************************************************************
    //*** TableModel
    //**************************************************************************
    @Override
    public int getRowCount() {
        return records.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Record.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return records.get(rowIndex);
        
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Record rec = records.get(rowIndex);
        if (rec instanceof EmptyRecord) {
            //--- Do nothing here
            
        } else if (rec instanceof CommentRecord) {
            CommentRecord cr = (CommentRecord) rec;
            cr.setValue(aValue.toString());
            
        } else if (rec instanceof PropertyRecord) {
            //--- If rowIndex is 0, then change the key
            PropertyRecord pr = (PropertyRecord) rec;
            if (columnIndex == 0) {
                pr.setName(aValue.toString());
                
            } else {
                pr.setValueAt(columnIndex-1, aValue.toString());
                
            }
        }
    }

    
    
    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);

    }

}
