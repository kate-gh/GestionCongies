
package btn;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;
    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
       Panel action = new Panel();
       action.initEvent(event, row);
       //action.setBackground(table1.getSelectionBackground());
       return action;
    }
    
     
    
}
