package com.epam.hr.candidates;


public class PositionRequirement implements Comparable<PositionRequirement> {

	private Candidate modelCandidate;
	private int amount;
	private int priority; // 1 = top-priority

	public PositionRequirement() {
		modelCandidate = new Candidate();
	}

	public PositionRequirement(Candidate modelCandidate, int priority, int amount) {
		super();
		this.modelCandidate = modelCandidate;
		this.amount = amount;
		this.priority = priority;
	}

	public Candidate getModelCandidate() {
		return modelCandidate;
	}

	public void setModelCandidate(Candidate modelCandidate) {
		this.modelCandidate = modelCandidate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((modelCandidate.positionTitle == null) ? 0
						: modelCandidate.positionTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		PositionRequirement other = (PositionRequirement) obj;
		if (modelCandidate.getPositionLevel() != other.modelCandidate
				.getPositionLevel())
			return false;
		return true;
	}

	public int compareTo(PositionRequirement obj) {
		return priority - obj.priority;
	}

	@Override
	public String toString() {
		return "[requirementsForJob=" + modelCandidate.toStringAsRequirements()
				+ ", amount=" + amount + ", priority=" + priority + "]";
	}

}
