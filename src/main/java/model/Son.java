package model;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Son extends Thread {
        private AudioClip clip;//le son créé depuis ton url

        public Son(String nomFichier) {
        	URL url = null;
        	try {
        		File file = new File(nomFichier);

        		if (file.canRead())
        			url = file.toURI().toURL();
        	} catch (MalformedURLException e) {
        		throw new IllegalArgumentException("could not play '" + nomFichier + "'", e);
        	}

        	// URL url = StdAudio.class.getResource(filename);
        	if (url == null) {
        		throw new IllegalArgumentException("could not play url '" + nomFichier + "'");
        	}

        	clip = Applet.newAudioClip(url);
        }
        
        public void jouer() {
        	clip.play();	
        }

        public void jouerEnBoucle() {
        	clip.loop();
        }

        public void arreter() {
        	clip.stop();
        }
}