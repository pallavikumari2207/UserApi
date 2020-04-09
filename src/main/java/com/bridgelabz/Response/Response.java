package com.bridgelabz.Response;

public class Response {
	
	private String message;
	private int statusCode;

	public Response() {
		
	}
	
	
	
	public Response(String message) {
		super();
		this.message = message;
	}


	public Response(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
