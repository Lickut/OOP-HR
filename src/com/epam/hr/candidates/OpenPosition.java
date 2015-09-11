package com.epam.hr.candidates;

public class OpenPosition {

	private Grade positionLevel;
	private int priority;
	private int amount;

	public OpenPosition() {
	}

	public OpenPosition(Grade positionLevel,int priority,int amount ) {
		super();
		this.positionLevel = positionLevel;
		this.priority = priority;
		this.amount = amount;
	}

	public Grade getPositionLevel() {
		return positionLevel;
	}

	public void setPositionLevel(Grade positionLevel) {
		this.positionLevel = positionLevel;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "NeededPosition [positionLevel=" + positionLevel + ", priority="
				+ priority + ", amount=" + amount + "]";
	}
}
