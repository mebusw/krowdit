package com.tadosoft.krowdit.type;

/**
 * Error Code
 * @author TadoSoft
 *
 */
public class EC {
	public static final int UNSUCCESSFUL = -1;		//operation not executed, generic error
	public static final int SUCCESS = 0;			//operation executed successfully
	public static final int TIMEOUT = 1;			//operation timeout 
	public static final int EXISTED = 3;			//the target is existed in database
	public static final int UNAUTHORIZED = 5;		//user is unauthorized to the operation
	public static final int NOT_FOUND = 7;			//the target is not found in database
	public static final int DAO_FAILED = 9;			//generic database error
	public static final int ILLEGAL_ARGUMENT = 11;		//unsupported or illegal parameter
	
	public static final int KROWD_CLOSED = 100;		//the krowd is closed, can't join it
	
	
}
