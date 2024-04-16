import java.util.Scanner;

public class welcome {
	static final int NUM_BOOK = 3;
	static final int NUM_ITEM = 7;
	static CartItem[] mCartItem = new CartItem[NUM_BOOK];
	static int mCartCount = 0;
	static Cart mCart = new Cart();
	static User mUser;
	
	public static void main(String[] args) {
		//String[][] mBook = new String[NUM_BOOK][NUM_ITEM];
		Book[] mBookList = new Book[NUM_BOOK];
		Scanner input = new Scanner(System.in);
		
		System.out.println("당신의 이름을 입력하세요: ");
		String name = input.next();
		
		System.out.println("연락처를 입력하세요: ");
		int phone = input.nextInt(); //숫자만 입력
		
		mUser = new User(name, phone);
		
		String greeting = "Welcome to Shopping Mall!";
		String tagline = "Welcome to Book Market!!";

		boolean quit = false;
		while (!quit) {
			System.out.println("********************************");
			System.out.println("\t" + greeting);
			System.out.println("\t" + tagline);
			
			/* 
			System.out.println("********************************");
			System.out.println("1. 고객 정보 확인하기 \t5.바구니에 항목 추가하기");
			System.out.println("2. 장바구니 상품 목록 보기\t6. 장바구니의 항목 수량 줄이기");
			System.out.println("3. 장바구니 비우기 \t7. 장바구니의 항목 삭제하기");
			System.out.println("4. 영수증 표시하기 \t8. 종료");
			System.out.println("********************************");
			*/
			
			menuIntro();
			
			System.out.println("메뉴 번호를 선택하세요: ");
			int choice = input.nextInt();
			System.out.println(choice + "번을 선택했습니다.");

			if (choice < 1 || choice > 9) {
				System.out.println("1부터 9까지의 숫자를 입력하세요.");
			}
			else {
				switch(choice) {
					case 1:
//						System.out.println("현재 고객 정보 : ");
//						System.out.println("이름" + name + " 연락처 " + phone);
						menuGuestInfo(name, phone);
						break;
					case 2:
						menuCartItemList();
						break;
					case 3:
						menuCartClear();
						break;
					case 4:
						menuCartAddItem(mBookList);
						break;
					case 5:
						menuCartRemoveItemCount();
						break;
					case 6:
						menuCartRemoveItem();
						break;
					case 7:
						menuCartBill();
						break;
					case 8:
						input.close();
						menuCartExit();
						quit = true;
						break;
					case 9:
						menuAdminLogin();
						break;
					
				}
			}
			
		}
		
	}
	
	public static boolean isCartInBook(String bookId) {
		/* boolean flag = false;
		for (int i = 0; i < mCartCount; i++) {
			if (bookId == mCartItem[i].getBookID()) {
				mCartItem[i].setQuantity(mCartItem[i].getQuantity()+1);
				flag = true;
			}
		}
		return flag;
		*/
		return mCart.isCartInBook(bookId);
	}
	/**
	 * 설명: Print Menu
	 * 매개변수: 
	 * 반환값: 
	 * 
	 */
	public static void menuIntro() {
		System.out.println("********************************");
		System.out.println("1. 고객 정보 확인하기 \t5.바구니에 항목 추가하기");
		System.out.println("2. 장바구니 상품 목록 보기\t6. 장바구니의 항목 수량 줄이기");
		System.out.println("3. 장바구니 비우기 \t7. 장바구니의 항목 삭제하기");
		System.out.println("4. 영수증 표시하기 \t8. 종료");
		System.out.println("9. 관리자 로그인");
		System.out.println("********************************");
	}
	
	/**
	 * 설명: 고객님 정보 출력
	 * 매개변수: 
	 *  - Strig	name	고객님의 이름
	 *  - int	phone	휴대전화 번호
	 * 반환값: 
	 * 
	 */
	public static void menuGuestInfo(String name, int phone) {
		System.out.println("현재 고객 정보 : ");
		// Person person = new Person(name, mobile);
		// System.out.println("이름 " + person.getName() + " 연락처" + person.getPhone());
		System.out.println("이름 " + mUser.getName() + "  연락처 " + mUser.getphone());
		Person Person = new Person(name, phone);
		System.out.println("이름" + Person.getName() + " 연락처 " + Person.getName());
	}
	
	/**
	 * 설명: 2번
	 * 매개변수: 
	 *  - Strig	name	고객님의 이름
	 *  - int	phone	휴대전화 번호
	 * 반환값: 
	 */
	public static void menuCartItemList() {
	/*	System.out.println("2. 장바구니 상품 목록 보기 : ");
		System.out.println("--------------------------------------");
		System.out.println("     도서ID \t      수량 \t       합계 ");
		for (int i = 0; i < mCartCount; i++) {
			System.out.println("     " + mCartItem[i].getBookID() + " \t| ");
			System.out.println("     " + mCartItem[i].getQuantity() + " \t| ");
			System.out.println("     " + mCartItem[i].getTotalPrice());
			System.out.println("   ");
		}
		System.out.println("---------------------------------");
		*/
		
		if (mCart.mCartCount >= 0) {
			mCart.printCart();
		}
	}
	public static void menuCartClear() {
		// System.out.println("3. 장바구니 비우기 : ");
		if (mCart.mCartCount == 0)
			System.out.println("장바구니에 항목이 없습니다.");
		else {
			System.out.println("장바구니의 모든 항목을 삭제하시겠습니까?  Y  | N");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			
			if (str.toUpperCase().equals("Y")) {
				System.out.println("장바구니의 모든 항목을 삭제했습니다.");
				mCart.deleteBook();
			}
					
		}
	}
	
	public static void menuCartAddItem(Book[] booklist) {
		/* for (int i = 0; i < NUM_BOOK; i++) {
			for (int j = 0; j < NUM_ITEM; j++)
				System.out.print(book[i][j] + " | " );
			System.out.println(" ");
		}
		*/
		mCart.printBookList(booklist);
		
		boolean quit = false;
		
		while (!quit) {
			System.out.print("장바구니에 추가할 도서의 ID를 입력하세요 : ");
			
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			
			boolean flag = false;
			int numId = -1;
			
			for (int i = 0; i < NUM_BOOK; i++) {
				if (str.equals(booklist[i].getBookId())) {
					numId = i;
					flag = true;
					break;
				}
			}
			
			if (flag) {
				System.out.println("장바구니에 추가하겠습니까? Y | N ");
				str = input.nextLine();
				if (str.toUpperCase().equals("Y")) {
					System.out.println(booklist[numId] + " 도서가 장바구니에 추가되었습니다.");
					if (!isCartInBook(booklist[numId].getBookId())) {
						mCart.insertBook(booklist[numId]);
				}
				quit = true;
			} else
				System.out.println("다시 입력해 주세요");
			
		}
		
		System.out.println("4. 장바구니 항목 추가하기 : ") ;
		}
	}
	public static void menuCartRemoveItemCount() {
		System.out.println("5. 장바구니 항목 수량 줄이기 : ");
	}
	public static void menuCartRemoveItem() {
		if (mCart.mCartCount == 0)
			System.out.println("장바구니에 항목이 없습니다");
		else {
			menuCartItemList();
			boolean quit = false;
			while (!quit) {
				System.out.print("장바구니에서 삭제할 도서의 ID를 입력하세요 :");
				Scanner input = new Scanner(System.in);
				String str = input.nextLine();
				boolean flag = false;
				int numId =-1;
				
				for (int i = 0; i < mCart.mCartCount; i++) {
					if (str.equals(mCart.mCartItem[i].getBookID())) {
						numId = i;
						flag = true;
						break;
					}
				}
				
				if (flag) {
					System.out.println("장바구니의 항목을 삭제하시겠습니까? Y |N");
					str = input.nextLine();
					if (str.toUpperCase().equals("Y")) {
						System.out.println(mCart.mCartItem[numId].getBookID() + "장바구니에서 도서가 삭제 되었습니다.");
						mCart.removeCart(numId);
					}
					quit = true;
				}
				else System.out.println("다시 입력해 주세요");
			}
		}
		System.out.println("6. 장바구니의 항목 삭제하기 : ");
	}
	public static void menuCartBill() {
		System.out.println("7. 영수증 표시하기 : ");
	}
	public static void menuCartExit() {
		System.out.println("8. 종료 : ");
	}
	public static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요");
		
		Scanner input = new Scanner(System.in);
		System.out.println("아이디 : ");
		String adminId = input.next();
		
		System.out.println("비밀번호 : ");
		String adminPW = input.next();
		
		Admin admin = new Admin(mUser.getName(), mUser.getphone());
		if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
			System.out.println("이름 " + admin.getName() + " 연락처" + admin.getphone());
			System.out.println("아이디 " + admin.getName() + " 비밀번호" + admin.getphone());
		} else
			System.out.println("관리자 정보가 없습니다.");
		
	}
	public static void BookList(Book[] booklist) {
		
		booklist[0] = new Book ("ISBN1234", "쉽게 배우는 JSP 웹 프로그래밍", 27000);
		booklist[0].setAuthor("송미영");
		booklist[0].setDescription("단계별로 쇼핑몰을 구현하며 배우는 JSP 웹 프로그래밍");
		booklist[0].setCategory("IT전문서");
		booklist[0].setReleaseDate("2018/10/08");
		
		booklist[0] = new Book ("ISBN1235", "안드로이드 프로그래밍", 33000);
	    booklist[0].setAuthor("우재남");            
		booklist[0].setDescription("실습 단계별 명쾌한 멘토링!");
		booklist[0].setCategory("IT전문서");        
	    booklist[0].setReleaseDate("2022/01/22");
		
	    booklist[0] = new Book ("ISBN1236", "스크래치", 22000);
		booklist[0].setAuthor("고광일");            
		booklist[0].setDescription("컴퓨팅 사고력을 키우는 블록 코딩");
        booklist[0].setCategory("컴퓨터입문");        
        booklist[0].setReleaseDate("2019/06/10");






	}
}

