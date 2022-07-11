package tools.view;

public class DebugPrinter {
	public final static int complete = 0;
	public final static int errorsOnly = 1;
	public final static int silent = 2;
	
	private static final int defaultValue = silent;
	private int debugLevel = silent;
	
	public static DebugPrinter instance;
	
	DebugPrinter(int level) {
		this.debugLevel = level;
		instance = this;
	}
	
	public static DebugPrinter getInstance() {
		if(instance == null)
			instance = new DebugPrinter(defaultValue);
		return instance;
	}
	
	public void setDebugLevel(int level) {
		this.debugLevel = level;
	}
	
	public void printError(String s) {
		if(debugLevel == complete || debugLevel == errorsOnly)
			System.out.println(s);
	}
	
	public void printMessage(String s) {
		if(debugLevel == complete)
			System.out.println(s);
	}
}
