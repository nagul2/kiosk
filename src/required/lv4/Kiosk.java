package required.lv4;

import java.util.*;

public class Kiosk {

    /**
     * Menu를 보관하고 관리하는 리스트
     */
    List<Menu> menus = new ArrayList<>();

    /**
     * Main에서 생성한 Menu들을 저장
     * @param menus Menu를 가변인자로 받을 수 있음
     */
    public Kiosk(Menu  ...menus) {
        Collections.addAll(this.menus, menus);
    }

    /**
     * main 메서드에서 실행하던 애플리케이션 로직을 Kiosk 클래스의 start()메서드로 이동
     * 프로그램을 실행하는 Main 클래스이 main 메서드는 키오스크를 생성하고 start()메서드만 호출하면 됨
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            categoryPrinter(menus);                         // 메인 메뉴 -> 메뉴의 대분류(카테고리) 출력
            int mainInput = InputValidator(scanner);        // 입력값 검증 -> 카테고리 입력

            if (mainInput == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            if (menus.size() < mainInput) {
                System.out.println("잘못된 메뉴를 선택하였습니다 다시 입력해 주세요.");
                continue;
            }

            Menu menu = selectMainMenu(mainInput);

            while (true) {
                menuPrinter(menu);                          // 카테고리 하위 메뉴 출력
                int menuInput = InputValidator(scanner);    // 입력값 검증(재사용) -> 상세 메뉴 입력

                if (menuInput == 0) {
                    System.out.println("처음으로 이동합니다.");
                    break;
                }

                if (menu.findAllMenuItem().size() < menuInput) {
                    System.out.println("잘못된 메뉴를 선택하였습니다 다시 입력해 주세요.");
                    continue;
                }

                selectMenuItem(menuInput, menu);            // 선택된 메뉴 출력
                System.out.println("주문이 완료 되었습니다.");
                System.out.println();
                break;
            }
        }
    }

    /**
     * 카테고리(MenuItem의 대분류)를 출력
     * @param menus List<Menu>
     */
    private void categoryPrinter(List<Menu> menus) {
        System.out.println("[MAIN MENU]");
        int count = 1;
        for (Menu menu : menus) {
            System.out.println(count++ + ". " + menu.getCategory());
        }
        System.out.println("0. 종료");
    }

    /**
     * 조회된 메뉴를 반환
     * 검증된 입력값을 받아 menus 리스트에서 Menu를 조회해서 반환함(마찬가지로 입력값 -1이 필요함)
     * input 값이 menus의 값보다 큰 경우 예외 처리가 되도록 코드 추가
     * @param input 검증된 입력값
     * @return 조회된 Menu
     */
    private Menu selectMainMenu(int input) {
        return menus.get(input - 1);
    }

    /**
     * 입력값을 검증하고 반환
     * 숫자가 아닌 다른 입력값인 경우 비즈니스 로직이 벗어났으므로 예외처리 후 0으로 return(카테고리 조회 시 종료, 상세 메뉴 조회 시 처음으로 이동)
     * @param scanner 입력을 위한 Scanner
     * @return 검증된 입력값 및 예외 로직에 따른 동작을 반환
     */
    private int InputValidator(Scanner scanner) {
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return Math.abs(input);
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력 가능합니다. 숫자만 입력 해주세요.");   // 숫자만 입력 가능하다고 수정(재사용 위함)
            System.out.println();
            scanner.nextLine();
            return 0;           // 0으로 반환 Menu
        }
    }

    /**
     * 상세 메뉴를 출력
     * 매개변수의 menu로 category 필드를 조회하여 카테고리를 출력하고, 전체를 조회하는 메서드로 해당 카테고리의 상세 메뉴를 출력하고
     * 메뉴가 길어지는 메뉴가 많아 \t가 원하는대로 동작하지 않아서 prinf()로 포맷팅하여 출력
     * @param menu 첫 번째 반복문에서 조회된 Menu(카테고리)
     */
    private void menuPrinter(Menu menu) {
        List<MenuItem> menuItems = menu.findAllMenuItem();

        System.out.println("[" + menu.getCategory() + " MENU]");

        int count = 1;
        for (MenuItem menuItem : menuItems) {
            System.out.printf("%d. %-18s | ₩ %d | %s%n", count++, menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());        }
        System.out.println("0. 처음으로             | 처음으로 이동");

    }

    /**
     * 선택된 메뉴가 무엇인지 출력하는 메서드
     * menu에서 단일 MenuItem을 찾는 메서드를 총해 선택된 상세 메뉴를 조회하여 출력
     * @param input 검증된 메뉴 입력 값
     * @param menu 상세 메뉴가 속한 카테고리
     */
    private void selectMenuItem(int input, Menu menu) {
        MenuItem menuItem = menu.findMenuItem(input);
        System.out.printf("선택한 메뉴: %s | ₩ %d | %s%n", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }

}
