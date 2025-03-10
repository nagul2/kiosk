package required.lv3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    List<MenuItem> menuItems = new ArrayList<>();

    /**
     * 리스트인 menuItems 의 값을 Kiosk를 생성할 때 초기화하는 생성자
     * Collections.addAll(this.menuItems, menuItems); 메서드를 사용할 수 있지만 이해하기 쉽게 이대로 유지
     * @param menuItems MenuItem을 가변으로 받을 수 있도록 ... 키워드를 사용
     */
    public Kiosk(MenuItem ...menuItems) {
        for (MenuItem menuItem : menuItems) {
            this.menuItems.add(menuItem);
        }
    }

    /**
     * main 메서드에서 실행하던 애플리케이션 로직을 Kiosk 클래스의 start()메서드로 이동
     * 프로그램을 실행하는 Main 클래스이 main 메서드는 키오스크를 생성하고 start()메서드만 호출하면 됨
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menuPrinter(menuItems);
            int input = InputValidator(scanner);
            if (input == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            if (input == -9999) {
                continue;
            }

            selectMenu(input);
            System.out.println("주문이 완료 되었습니다.");
            break;
        }
    }

    /**
     * 입력값을 검증하고 반환
     * 숫자가 아닌 다른 입력값인 경우 비즈니스 로직이 벗어났으므로 별도의 예외처리하여 재시작을 위한 값을 반환
     * @param scanner 입력을 위한 Scanner
     * @return 검증된 입력값 및 예외 로직에 따른 동작을 반환
     */
    private int InputValidator(Scanner scanner) {
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return Math.abs(input);
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력 가능합니다. MENU의 숫자만 입력 해주세요.");
            System.out.println();
            scanner.nextLine();
            return -9999;
        }
    }

    /**
     * 메뉴를 출력하는 메서드
     * 하드코딩하지 않고 매개변수로 넘어온 List<MenuItem>을 활용
     * @param menuItems 상품이 담긴 MenuItem 리스트
     */
    private void menuPrinter(List<MenuItem> menuItems) {
        System.out.println("[SHAKESHACK MENU]");
        int count = 1;
        for (MenuItem menuItem : menuItems) {
            System.out.println(count++ + ". " + menuItem.getName() + "\t| ₩ " + menuItem.getPrice() + "\t| " + menuItem.getDescription());
        }
        System.out.println("0. 종료\t\t\t| 종료");
    }

    /**
     * 검증된 입력값에 따라 동작하는 메서드
     * @param input 검증된 입력 값
     */
    private void selectMenu(int input) {
        switch (input) {
            case 1 -> System.out.println("ShackBurger 1개를 선택 하셨습니다. 가격: 6,900원");
            case 2 -> System.out.println("SmokeShack 1개를 선택 하셨습니다. 가격: 8,900원");
            case 3 -> System.out.println("CheeseBurger 1개를 선택 하셨습니다. 가격: 6,900원");
            case 4 -> System.out.println("Hamburger 1개를 선택 하셨습니다. 가격: 5,400원");
        }
    }


}
