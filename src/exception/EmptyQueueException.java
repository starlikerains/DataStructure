package exception;

public class EmptyQueueException extends Exception {
	/**
	 * @Fields 
	 */
	private static final long serialVersionUID = -4663501535060153046L;
	public EmptyQueueException() {
		this("Queue is Empty!");
	}
	public EmptyQueueException(String exception) {
		super(exception);
	}
}
