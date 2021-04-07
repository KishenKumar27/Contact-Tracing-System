package main;

public class Visit {

	public String time;
	public String date;

	public Visit(String time, String date) {
		this.time = time;
		this.date = date;
	}

	public Visit() {
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Visit [time=" + time + ", date=" + date + "]";
	}

}
