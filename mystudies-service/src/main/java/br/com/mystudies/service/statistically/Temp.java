package br.com.mystudies.service.statistically;

public class Temp {

	private Double average;
	private Integer pointsInBacklog;
	private Double sprintsToDo;

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public Integer getPointsInBacklog() {
		return pointsInBacklog;
	}

	public void setPointsInBacklog(Integer pointsInBacklog) {
		this.pointsInBacklog = pointsInBacklog;
	}

	public Double getSprintsToDo() {
		return sprintsToDo;
	}

	public void setSprintsToDo(double sprintsToDo) {
		this.sprintsToDo = sprintsToDo;
	}
}
