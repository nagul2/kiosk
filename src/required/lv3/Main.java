package required.lv3;

public class Main {

    public static void main(String[] args) {
        // Kiosk를 생성할 때 MenuItem 세팅
        Kiosk kiosk = new Kiosk(
                new MenuItem("ShackBurger", 6_900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8_900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("CheeseBurger", 6_900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5_900, "비프패티를 기반으로 야채가 들어간 기본버거")
        );

        kiosk.start();
    }
}
