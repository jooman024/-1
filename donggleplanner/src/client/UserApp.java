package client;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import database.UserData;

public class UserApp {
	
	public UserApp(){
		
		frontController();
	}
	/*클라이언트 화면 및 데이터 흐름 제어*/
	private void frontController() {
		Scanner scanner = new Scanner(System.in);
		String mainTitle = this.mainTitle(this.getToday(true));
		String makeMenu = this.makeMenu();
	    String close = this.close();
		String[] accessInfo = new String[2];
		boolean isLoop = true;
		boolean accessResult;
		
		
		while (isLoop) {
			
			for(int idx=0; idx<accessInfo.length; idx++) {
				this.display(mainTitle);
				this.display(this.makeAccesse(true, accessInfo[0]));
				accessInfo[idx] = this.userInput(scanner);
			}
			this.display(this.makeAccesse(false, null));
			/*서버에 로그인 정보 전달*/
			
			/*서버로부터 받은 로그인 결과에 따른 화면 출력*/
			accessResult = true;
			
			this.display(this.accessResult(accessResult));
			if(!accessResult) {
				/*로그인 실패*/			
				if(this.userInput(scanner).toUpperCase().equals("N")) {
					isLoop = false;
				}else {
					accessInfo[0] = null;          //초기화 2개 해야됨
					accessInfo[1] = null;
				}
			}else {
				/*로그인 성공*/
				while(isLoop) {
					String menuSelection = new String();
					this.display(mainTitle);
					this.display(makeMenu);
					menuSelection = this.userInput(scanner);
					
					if(menuSelection.toUpperCase().equals("0")) {
					/*0번 선택시 서버에 로그아웃 통보 후 프로그램 종료*/
						isLoop = false;
					}
				}
			}
		}
		this.display(close);
		scanner.close();
	}
	/*프로그램 메인 타이틀 제작*/
	private String mainTitle(String date) {
		StringBuffer title = new StringBuffer();
		
		title.append("◤Donggle◥〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
		title.append("∥\n");
		title.append("∥     D◎nggle D◎nggle        ╭◜◝ ͡ ◜◝       ╭◜◝ ͡ ◜◝\n");
		title.append("∥	       Planner      ( •‿•。   ) ☆  ( •‿•。   ) ☆\n");
		title.append("∥	                     ╰◟ ◞  ͜ ◟ ◞      ╰◟ ◞  ͜ ◟ ◞ \n");
		title.append("∥	                                ╭◜ ◝ ͡ ◜◝ \n");
		title.append("∥	                               ( •‿•。    ) ☆\n");
		title.append("∥      ★  ☆                             ╰◟ ◞  ͜ ◟ ◞╯\n");
		title.append("∥    L༼ ◕◡◕ ༽┘                        "+date+"\n");
		title.append("∥      hello                      design by. 4조\n");
		title.append("∥\n");
		title.append("●〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
		
		return title.toString();
	}
	/*로그인창*/
	private String makeAccesse(boolean isItem, String accessCode) {
		StringBuffer access = new StringBuffer();
		
		if(isItem) {
		access.append("◤ACCESSE◥〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
		access.append("∥\n");
		access.append("∥	  ____________              ____________\n");
		access.append("∥	 ｜AccessCode｜            ｜SecretCode｜  ");
		access.append("\n∥	  ￣￣￣￣￣￣              ￣￣￣￣￣￣\n");
		access.append("∥             "+((accessCode!=null)? accessCode+"       \t\t":""));
		}else {
		access.append("∥\n");
		access.append("●〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
		access.append("❥ ❥ ❥ Loading . . . ");
		}
		return access.toString();
	}
	/*서버응답에 따른 로그인 결과 출력*/
	private String accessResult(boolean isAccess) {
		StringBuffer accessResult = new StringBuffer();
		
		if(isAccess) {
			accessResult.append("❥ ❥ ❥ ❥ Successful Connection !\n");
		}else {
			accessResult.append("❥ ❥ ❥ ❥ Connection Failed\n");
			accessResult.append("_____________________________________________ Retry(Y/N) ? ❙");
		}
		return accessResult.toString();
	}
	/*오류메세지*/
	private String makeMessage(String text) {
		StringBuffer message = new StringBuffer();
		
		message.append("[message] ");
		message.append(text);
		message.append("\n");
		
		return message.toString();
	}
	/*메인 메뉴*/
	private String makeMenu(){
		StringBuffer menu = new StringBuffer();
		
		menu.append("\n");
		menu.append("◤MENU◥〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
		menu.append("∥\n");
		menu.append("∥	1. TASK LIST   	    2. TASK SETTINGS\n");
		menu.append("∥	3. MODIFY TASK      4. TASK STATS\n");
		menu.append("∥	0. DISCONNECT\n");
		menu.append("∥\n");
		menu.append("●〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓 Select:");
		
		return menu.toString();
	}
	//메세지
	private String close() {
		StringBuffer close = new StringBuffer();
		
		close.append("\n◤G◎◎D BYE !◥ 〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
		close.append("∥\n");
		close.append("∥	See you ~ ! ᕦ( ᐛ )ᕡ ᕦ( ᐛ )ᕡ\n");
		close.append("∥\n");
		close.append("●〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
		return close.toString();
	}
	/*날짜시간 출력 : localDataTime Class + DateTitmeFormatter Class */
	private String getToday(boolean isDate) {
		String pattern = (isDate)? "yyyy.MM.dd":"yyyy-MM-dd HH:mm:ss";
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
	/*사용자 입력*/
	private String userInput(Scanner scanner) {
		return scanner.next();
	}
	/*화면출력*/
	private void display(String text) {
		System.out.print(text);
	}
}
