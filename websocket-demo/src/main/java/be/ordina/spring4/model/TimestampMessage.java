package be.ordina.spring4.model;

import java.util.Date;

/**
 * @author Ken Coenen
 */
public class TimestampMessage extends Message {
	private Date time;

	public TimestampMessage(Message sourceMsg, Date time) {
		super(sourceMsg.getId(), sourceMsg.getText());
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
