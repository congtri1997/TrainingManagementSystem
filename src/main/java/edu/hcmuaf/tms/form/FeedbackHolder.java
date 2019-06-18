package edu.hcmuaf.tms.form;

public class FeedbackHolder {
	private long id;
	private int rating;

	public FeedbackHolder() {
	}

	public FeedbackHolder(long id, int rating) {
		this.id = id;
		this.rating = rating;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "FeedbackHolder [id=" + id + ", rating=" + rating + "]";
	}
	
	

}
