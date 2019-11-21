package affiliate.driver;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;

public class FillLayoutExample {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FillLayoutExample window = new FillLayoutExample();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(744, 422);
		shell.setText("SWT Application");
		FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
		fl_shell.spacing = 20;
		shell.setLayout(fl_shell);
		
		Label label = new Label(shell, SWT.WRAP | SWT.HORIZONTAL | SWT.CENTER);
		label.setText("Evidence Based Education - Affiliate");
		label.setFont(SWTResourceManager.getFont("Futura LT", 18, SWT.BOLD));
		
		Composite composite_1 = new Composite(shell, SWT.BORDER);
		FillLayout fl_composite_1 = new FillLayout(SWT.HORIZONTAL);
		fl_composite_1.marginWidth = 20;
		fl_composite_1.spacing = 20;
		composite_1.setLayout(fl_composite_1);
		
		Composite composite_6 = new Composite(composite_1, SWT.NONE);
		
		Composite composite_7 = new Composite(composite_1, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite_7, SWT.NONE);
		lblNewLabel_1.setBounds(0, 10, 66, 15);
		lblNewLabel_1.setText("Affiliate (*)");
		
		Combo combo = new Combo(composite_7, SWT.NONE);
		combo.setBounds(0, 31, 136, 23);
		
		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		
		Label lblNewLabel_2 = new Label(composite_3, SWT.NONE);
		lblNewLabel_2.setBounds(0, 10, 55, 15);
		lblNewLabel_2.setText("New Label");
		
		Combo combo_1 = new Combo(composite_3, SWT.NONE);
		combo_1.setBounds(0, 31, 136, 23);
		
		Button btnNewButton = new Button(composite_3, SWT.NONE);
		btnNewButton.setBounds(29, 91, 75, 32);
		btnNewButton.setText("Display");
		
		Composite composite_4 = new Composite(composite_1, SWT.NONE);
		
		Label lblNewLabel_3 = new Label(composite_4, SWT.NONE);
		lblNewLabel_3.setBounds(0, 10, 55, 15);
		lblNewLabel_3.setText("New Label");
		
		Combo combo_2 = new Combo(composite_4, SWT.NONE);
		combo_2.setBounds(0, 31, 136, 23);
		
		Composite composite_5 = new Composite(composite_1, SWT.NONE);
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Composite composite_2 = new Composite(shell, SWT.BORDER);

	}
}
