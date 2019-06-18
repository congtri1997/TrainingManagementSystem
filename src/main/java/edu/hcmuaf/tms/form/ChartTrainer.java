package edu.hcmuaf.tms.form;

import edu.hcmuaf.tms.entity.Trainer;

public class ChartTrainer {

	private Trainer trainer;
	private long num;

	public ChartTrainer(Trainer trainer, long num) {
		this.trainer = trainer;
		this.num = num;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

}
