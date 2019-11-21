package affiliate.driver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.RowLayout;

public class UIGroup {

	protected Shell shlUpdate;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIGroup window = new UIGroup();
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
		shlUpdate.open();
		shlUpdate.layout();
		while (!shlUpdate.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlUpdate = new Shell();
		//shlUpdate.setSize(869, 300);
		//shell.setSize(450, 300);
		shlUpdate.setText("SWT Application");
        shlUpdate.setLayout(new RowLayout(SWT.HORIZONTAL));
        
        Label lblNewLabel = new Label(shlUpdate,SWT.SINGLE);
		lblNewLabel.setText("Evidence Based Education - Affiliate");

       // GridData gridData = new GridData();
       // gridData.widthHint = 203;
      //  gridData.horizontalAlignment = SWT.CENTER;
      //  lblNewLabel.setLayoutData(gridData);
        //lblNewLabel.setAlignment(SWT.RIGHT);
        //lblNewLabel.setAlignment(SWT.CENTER);
		//lblNewLabel.setBackground(SWTResourceManager.getColor());
	//	lblNewLabel.setFont(SWTResourceManager.getFont("Futura LT", 18, SWT.BOLD));
		//lblNewLabel.setAlignment(SWT.CENTER);
		//lblNewLabel.setBounds(443, 10, 500, 52);
		lblNewLabel.setText("Evidence Based Education - Affiliate");
		//lblNewLabel.setBackground(shlUpdate.getBackground());

		
//		  Text text = new Text(shell, SWT.SINGLE);
//	        GridData gridData = new GridData();
//	        gridData.horizontalSpan = 4;
//	        gridData.horizontalAlignment = GridData.FILL;
//	        text.setLayoutData(gridData);


	}

}
