
import javax.swing.JFrame;

public class FuntactixTest extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuntactixTest(){
		setTitle("test by Yuli Matsai for Funtactix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display();
	}

	
	public void display() {
        add(new GameEngine());
        pack();        
        setMinimumSize(getSize());
        setVisible(true);
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new FuntactixTest(), "My Thread");
        t.start();
	}

	@Override
	public void run() {
		while (true){
			try {
				getContentPane().validate();
		        getContentPane().repaint();
				Thread.sleep(0);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

}
