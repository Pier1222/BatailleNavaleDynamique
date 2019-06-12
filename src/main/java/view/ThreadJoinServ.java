package view;

import javax.swing.SwingWorker;

public class ThreadJoinServ extends SwingWorker {
	View_accueil v;
	public ThreadJoinServ(View_accueil v) {
		this.v = v;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		v.useJoinThread();
		return null;
	}
}
