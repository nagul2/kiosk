package required.lv2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<MenuItem> menuItems = new ArrayList<>();
        // MenuItem 생성 및 List에 메뉴 아이템 추가
        menuItems.add(new MenuItem("ShackBurger", 6_900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8_900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("CheeseBurger", 6_900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5_900, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int input = InputValidator(scanner, menuItems);
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
     * @param menuItems 메뉴를 출력하기 위한 MenuItem 리스트
     * @return 검증된 입력값 및 예외 로직에 따른 동작을 반환
     */
    private static int InputValidator(Scanner scanner, List<MenuItem> menuItems) {
        try {
            menuPrinter(menuItems);
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
    private static void menuPrinter(List<MenuItem> menuItems) {
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
    private static void selectMenu(int input) {
        switch (input) {
            case 1 -> System.out.println("ShackBurger 1개를 선택 하셨습니다. 가격: 6,900원");
            case 2 -> System.out.println("SmokeShack 1개를 선택 하셨습니다. 가격: 8,900원");
            case 3 -> System.out.println("CheeseBurger 1개를 선택 하셨습니다. 가격: 6,900원");
            case 4 -> System.out.println("Hamburger 1개를 선택 하셨습니다. 가격: 5,400원");
        }
    }


}
