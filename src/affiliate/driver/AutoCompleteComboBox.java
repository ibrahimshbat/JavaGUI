package affiliate.driver;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 * class implemeted Auto Complete ComboBox
* @author  Ibrahim Elsanosi
* @version 1.0
* @since   2019-09-20 
 * @author IbrahimEl-Sanosi
 */
class AutoCompleteComboBox extends JComboBox
{
   public int caretPos=0;
   public JTextField inputField=null;
  
   public AutoCompleteComboBox(final Object elements[]) {
      super(elements);
      setEditor(new BasicComboBoxEditor());
      setEditable(true);
   }
  
   public void setSelectedIndex(int index) {
      super.setSelectedIndex(index);
  
      inputField.setText(getItemAt(index).toString());
      inputField.setSelectionEnd(caretPos + inputField.getText().length());
      inputField.moveCaretPosition(caretPos);
   }
  
   public void setEditor(ComboBoxEditor editor) {
      super.setEditor(editor);
      if (editor.getEditorComponent() instanceof JTextField) {
         inputField = (JTextField) editor.getEditorComponent();
  
         inputField.addKeyListener(new KeyAdapter() {
            public void keyReleased( KeyEvent ev ) {
               char key=ev.getKeyChar();
               if (! (Character.isLetterOrDigit(key) || Character.isSpaceChar(key) )) return;
  
               caretPos=inputField.getCaretPosition();
               String text="";
               try {
                  text=inputField.getText(0, caretPos);
               }
               catch (javax.swing.text.BadLocationException e) {
                  e.printStackTrace();
               }
  
               for (int i=0; i<getItemCount(); i++) {
                  String element = (String) getItemAt(i);
                  if (element.startsWith(text)) {
                     setSelectedIndex(i);
                     return;
                  }
               }
            }
         });
      }
   }
}