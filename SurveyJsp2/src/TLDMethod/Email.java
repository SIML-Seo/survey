package TLDMethod;

import DataObject.SurveyUserDto;

public class Email {
	public static String sub(SurveyUserDto dto, int check) {
		String email = dto.getEmail();
		String email1 = email.substring(0, email.indexOf('@'));
		String email2 = email.substring(email.indexOf('@')+1);
		if(check == 1) {
			return email1;
		}else {
			return email2;
		}
	}
}
