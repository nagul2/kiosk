package challenge.lv1;

import java.util.*;

import static challenge.lv1.util.PriceFormatter.priceFormat;

public class Kiosk {
    /**
     * order()메서드를 출력할지 말지 결정하는 플래그 변수
     */
    private boolean flag;

    /**
     * 장바구니 생성
     */
    private final ShoppingCart shoppingCart = new ShoppingCart();

    /**
     * Menu를 보관하고 관리하는 리스트
     */
    private final List<Menu> menus = new ArrayList<>();

    /**
     * Main에서 생성한 Menu들을 저장
     * @param menus Menu를 가변인자로 받을 수 있음
     */
    public Kiosk(Menu...menus) {
        Collections.addAll(this.menus, menus);
    }

    /**
     * main 메서드에서 실행하던 애플리케이션 로직을 Kiosk 클래스의 start()메서드로 이동
     * 프로그램을 실행하는 Main 클래스이 main 메서드는 키오스크를 생성하고 start()메서드만 호출하면 됨
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int menusSize = menus.size();       // 등록한 메뉴의 개수를 변수화 (자주 사용함)

        /**
         * 프로그램 시작
         */
        while (true) {
            categoryPrinter(menus);                         // 메인 메뉴 -> 메뉴의 대분류(카테고리) 출력

            // flag == true : 주문 로직 활성화
            if (flag) {
                orderMenuPrinter();                         // 주문 메뉴 출력
            }

            int mainInput = inputValidator(scanner);        // 입력값 검증 -> 메뉴 선택 or flag가 true일 때 주문 및 취소 선택

            if (mainInput == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            /**
             * 카테고리(Menu)가 고정개수가 아니라 여러 카테고리가 늘어날 수 있기 때문에 memusSize를 기준으로 로직을 작성
             * flag == true: 주문 메뉴가 활성화되므로 menusSize + 2보다 크면 잘못된 입력값 간주(주문 메뉴는 2개로 고정임)
             * flag == false: 주문 메뉴가 활성화 되지 않았으므로 menusSize보다 크면 잘못된 입력값으로 간주
             */
            if (flag && mainInput > menusSize + 2|| !flag && mainInput > menusSize){
                System.out.println("잘못된 메뉴를 선택하였습니다 다시 입력해 주세요.");
                continue;
            }

            /**
             * 주문 관련 로직
             */
            if (mainInput == menusSize + 1) {                       // mainInput == 4 -> 주문 진행
                String lastPriceFormat = order(shoppingCart);       // 주문 내역 출력

                int lastSelect = inputValidator(scanner);           // 입력값 검증 -> 주문, 메뉴판 이동 선택
                if (lastSelect == 1) {                              // lastSelect == 1 -> 최종 주문
                    System.out.println("주문이 완료 되었습니다. 금액은 ₩ " + lastPriceFormat + " 입니다");
                    break;
                } else if (lastSelect == 2) {                       // lastSelect == 2 -> 메뉴판 이동
                    System.out.println("메뉴로 돌아갑니다");
                    continue;
                } else {
                    System.out.println("잘못된 메뉴를 선택하였습니다 다시 입력해 주세요.");
                    continue;
                }
            }

            /**
             * 주문 취소 로직 - 장바구니 초기화
             */
            if (mainInput == menusSize + 2) {
                System.out.println("장바구니가 비워졌습니다.");
                System.out.println();
                shoppingCart.deleteAllCart();
                flag = false;                           // flag == false: 주문 메뉴 비활성화
                continue;
            }

            /**
             * 상세 메뉴 로직
             */
            Menu menu = selectMainMenu(mainInput);
            while (true) {
                menuPrinter(menu);                          // 카테고리 하위 메뉴 출력
                int menuInput = inputValidator(scanner);    // 입력값 검증(재사용) -> 상세 메뉴 입력

                if (menuInput == 0) {
                    System.out.println("처음으로 이동합니다.");
                    break;
                }

                if (menu.findAllMenuItem().size() < menuInput) {
                    System.out.println("잘못된 메뉴를 선택하였습니다 다시 입력해 주세요.");
                    continue;
                }

                MenuItem menuItem = selectMenuItem(menuInput, menu);    // 선택된 메뉴를 출력하고 반환

                /**
                 * 장바구니 관련 로직
                 */
                cartMenuPrinter(menuItem);                  // 장바구니 관련 메뉴 출력
                int cartAddInput = inputValidator(scanner); // 입력값 검증 -> 장바구니 담기, 취소 선택
                if (cartAddInput == 1) {
                    flag = true;
                    System.out.println("몇 개 주문 하시겠습니까? ");
                    int quantity = inputValidator(scanner); // 주문 수량
                    shoppingCart.addCart(menuItem, quantity);
                } else if (cartAddInput == 2) {
                    System.out.println("취소 하셨습니다. 이전으로 돌아갑니다.");
                    continue;
                } else {
                    System.out.println("잘못된 메뉴를 선택하였습니다 다시 입력해 주세요.");
                    continue;
                }
                break;
            }
        }
    }

    /**
     * 최종 주문 로직
     * shoppingCart.showCart()메서드를 호출하여 장바구니 메뉴와 장바구니에 담긴 전체 금액을 출력하고 전체 금액을 반환
     * @param shoppingCart  장바구니 리스트
     * @return  포맷팅 된 장바구니 총 금액
     */
    private String order(ShoppingCart shoppingCart) {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        String totalPriceFormat = shoppingCart.showCart();

        System.out.println();
        System.out.println("1. 확인\t 2. 메뉴판");
        return totalPriceFormat;
    }

    /**
     * 주문 메뉴 출력
     */
    private void orderMenuPrinter() {
        System.out.println();
        System.out.println("[ORDER MENU]");
        System.out.println("4. Orders\t| 장바구니를 확인 후 주문합니다.");
        System.out.println("5. Cancel\t| 진행중인 주문을 취소 합니다");
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
    private int inputValidator(Scanner scanner) {
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
            System.out.printf("%d. %-18s | ₩ %7s | %s%n", count++, menuItem.getName(), priceFormat(menuItem.getPrice()), menuItem.getDescription());        }
        System.out.println("0. 처음으로             | 처음으로 이동");

    }

    /**
     * 선택된 메뉴가 무엇인지 출력하는 메서드
     * menu에서 단일 MenuItem을 찾는 메서드를 총해 선택된 상세 메뉴를 조회하여 출력
     * @param input 검증된 메뉴 입력 값
     * @param menu 상세 메뉴가 속한 카테고리
     */
    private MenuItem selectMenuItem(int input, Menu menu) {
        MenuItem menuItem = menu.findMenuItem(input);
        System.out.printf("선택한 메뉴: %s | ₩ %7s | %s%n", menuItem.getName(), priceFormat(menuItem.getPrice()), menuItem.getDescription());
        return menuItem;
    }

    /**
     * 장바구니 메뉴 출럭
     * @param menuItem 선택된 상품
     */
    private void cartMenuPrinter(MenuItem menuItem) {
        System.out.println('"' + menuItem.getName() + " | ₩ " + priceFormat(menuItem.getPrice()) + " | " + menuItem.getDescription() + '"');
        System.out.println("위 메뉴를 장바구니에 추가 하시겠습니까?");
        System.out.println("1. 확인\t 2. 취소");
    }

}
