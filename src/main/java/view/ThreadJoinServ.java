package view;

import javax.swing.SwingWorker;

public class ThreadJoinServ extends SwingWorker {
	View v;
	public ThreadJoinServ(View v) {
		this.v = v;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		v.useJoinThread();
		return null;
	}
}
