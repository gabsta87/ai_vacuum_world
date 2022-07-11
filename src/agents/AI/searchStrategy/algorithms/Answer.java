package agents.AI.searchStrategy.algorithms;

public abstract class Answer{
	
	public boolean isSuccess() {
		return false;
	}

	public boolean isCutOff() {
		return false;
	}

	public boolean isFailure() {
		return false;
	}
	
	@Override
	public abstract String toString();
	
}

class Failure extends Answer{
	public Failure() {}
	
	@Override
	public boolean isFailure() {
		return true;
	}

	@Override
	public String toString() {
		return "Failure";
	}
}

class CutOff extends Answer{
	public CutOff() {}

	@Override
	public boolean isCutOff() {
		return true;
	}

	@Override
	public String toString() {
		return "CutOff";
	}
}