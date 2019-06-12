package view;

import javax.swing.SwingWorker;

public class ThreadCreationServ extends SwingWorker {
	View_accueil v;
	public ThreadCreationServ(View_accueil v) {
		this.v = v;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		v.useCreateThread();
		return null;
	}

}
