package edu.hcmuaf.tms.form;

import java.util.List;

public class FeedbackDTO {

	private List<FeedbackHolder> ratings;
	private String note;
	private long topicID;

	public long getTopicID() {
		return topicID;
	}

	public void setTopicID(long topicID) {
		this.topicID = topicID;
	}

	public List<FeedbackHolder> getRatings() {
		return ratings;
	}

	public void setRatings(List<FeedbackHolder> ratings) {
		this.ratings = ratings;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "FeedbackDTO [ratings=" + ratings + ", note=" + note + ", topicID=" + topicID + "]";
	}

}
