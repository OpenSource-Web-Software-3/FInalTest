package util;

import java.net.PasswordAuthentication;

import javax.mail.Authenticator;

public class Gmail extends Authenticator{

	public String emailID = "";
	@Override
	protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
		return new javax.mail.PasswordAuthentication("", ""); //parameter로 Gmail ID, PW 넣으세요
	}
	
}
