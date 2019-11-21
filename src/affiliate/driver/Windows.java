package affiliate.driver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class Windows {

	public Windows() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));

		Group outerGroup = new Group(shell, SWT.NONE);

		// Tell the group to stretch in all directions
		outerGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		outerGroup.setLayout(new GridLayout(1, true));
		outerGroup.setText("Group");
		
		
		Label lblNewLabel = new Label(outerGroup, SWT.TOP);
		//lblNewLabel.setBackground(SWTResourceManager.getColor());
		lblNewLabel.setFont(SWTResourceManager.getFont("Futura LT", 18, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		//lblNewLabel.setBounds(443, 10, 500, 52);
		lblNewLabel.setText("Evidence Based Education - Affiliate");
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
		
		Group outerGroup1 = new Group(outerGroup, SWT.NONE);

		// Tell the group to stretch in all directions
		outerGroup1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		outerGroup1.setLayout(new GridLayout(3, true));
		outerGroup1.setText("Group");



		Label lblSelectCourseName = new Label(outerGroup1, SWT.NONE);
		lblSelectCourseName.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		//lblSelectCourseName.setBackground(shlUpdate.getBackground());
		//lblSelectCourseName.setBounds(479, 88, 175, 15);
		lblSelectCourseName.setText("Affiliate (*)");
		lblSelectCourseName.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));


		Combo affiliateNames = new Combo(outerGroup1, SWT.NONE);
		affiliateNames.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		affiliateNames.setToolTipText("Select Affiliate from Here");
		//affiliateNames.setBounds(479, 105, 175, 23);
		affiliateNames.setText("All Affiliates");
		affiliateNames.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));

		Combo period = new Combo(outerGroup1, SWT.NONE);
		period.setItems(new String[] {"All Quarters", "First Quarter (JAN-MAR)", "Second Quarter (APR-JUN)", "Third Quarter (JUL-SEP)", "Fourth Quarter (OCT-DEC)"});
		//groupNames.setTouchEnabled(true);
		period.setText("All Quarters");
		//period.setBounds(675, 105, 180, 23);
		period.setToolTipText("Select Quarter from Here");
		period.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));


		//lblNewLabel.setBackground(shlUpdate.getBackground());

		//	    Button left = new Button(outerGroup, SWT.PUSH);
		//	    left.setText("Left");
		//
		//	    // Tell the button to stretch in all directions
		//	    left.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		//	    Button right = new Button(outerGroup, SWT.PUSH);
		//	    right.setText("Right");
		//
		//	    // Tell the button to stretch in all directions
		//	    right.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		shell.setSize(1000,400);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
