package required.lv5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {

    private final List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String category) {
        this.category = category;
    }

    /**
     * 메뉴의 대분류를 구분하기 위한 category 필드
     */
    private final String category;

    public String getCategory() {
        return category;
    }

    /**
     * 외부에서 Menu에 맞게 생성된 MenuItem들을 menuItems에 저장
     * Collections.addAll()메서드를 활용하여 가변인자로 넘어온 menuItems를 menuItems에 저장
     * @param menuItems 보관할 MenuItem
     */
    public void add(MenuItem...menuItems) {
        Collections.addAll(this.menuItems, menuItems);
    }

    /**
     * 넘어온 매개변수(검증된 입력값)에 해당되는 MenuItem을 menuItems에서 조회하여 반환
     * 조회 시 input -1을 해야 제대로된 index에서 아이템을 조회할 수 있음
     * @param input 검증된 입력값
     * @return 조회된 MenuItem
     */
    public MenuItem findMenuItem(int input) {
        return menuItems.get(input - 1);
    }

    /**
     * List<MenuItem> 자체를 반환 즉, MenuItem 전체조회
     * @return menuItems
     */
    public List<MenuItem> findAllMenuItem() {
        return menuItems;
    }

}
