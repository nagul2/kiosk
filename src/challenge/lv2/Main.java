package challenge.lv2;

public class Main {

    public static void main(String[] args) {

        /**
         * Menu를 생성하며 대분류 이름을 지정
         */
        Menu burgers = new Menu("Burgers");     // 버거 메뉴 생성
        Menu drinks = new Menu("Drinks");       // 음료 메뉴 생성
        Menu desserts = new Menu("Desserts");   // 간식 메뉴 생성

        /**
         * 생성한 Menu의 카테고리에 따라 상세 메뉴(MenuItem)들을 생성하여 저장
         */
        burgers.add(
                new MenuItem("ShackBurger", 8_900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 11_100, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("ShackMeisterBurger", 9_900, "크리스피 어니언과 치즈 패치, 쉑 소스가 기반인 아메리칸 스타일의 버거"),
                new MenuItem("Hamburger", 7_300, "비프패티를 기반으로 야채가 들어간 기본버거"),
                new MenuItem("ShroomBurger", 10_500, "치즈로 속을 채워 바삭하게 튀겨낸 포토벨로 버섯 패티가 기반인 베지테리안 버거")
        );
        drinks.add(
                new MenuItem("Lemonade", 4_500, "매장에서 직접 만드는 상큼한 레몬에이드"),
                new MenuItem("Fifty/Fifty", 4_000, "레몬에이드와 유기농 홍차를 우려낸 아이스 티가 만나 탄생한 쉐이크쉑의 시크니처 음료"),
                new MenuItem("Fountain Soda", 2_900, "코카콜라, 코카콜라 제로, 스프라이트, 환타(오렌지, 그레이프, 파인애플)"),
                new MenuItem("Classic Shakes", 6_800, "쫀득하고 진한 커스더드가 들어간 클래신 쉐이크(바닐라/초콜릿/스트로베리/블랙 & 화이트/솔티트 카라멜/ 피넛 버터/커피")
        );
        desserts.add(
                new MenuItem("HotDog", 5_100, "훈연한 비프 소지지와 포테이토 번을 사용한 핫 도그"),
                new MenuItem("Fries", 4_900, "바삭하고 담백한 크릴클 컷 프라이"),
                new MenuItem("CheeseFries", 6_000, "고소하고 진한 치즈 소스를 듬뿍 올린 크링클 컷 프라이")
        );

        /**
         * 키오스크 생성시 생성된 Menu를 키오스크에 등록
         */
        Kiosk kiosk = new Kiosk(burgers, drinks, desserts);
        kiosk.start();
    }
}
