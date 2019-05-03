package view;

import javax.swing.SwingWorker;

public class ThreadCreationServ extends SwingWorker {
	View v;
	public ThreadCreationServ(View v) {
		this.v = v;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		v.useCreateThread();
		return null;
	}

}
