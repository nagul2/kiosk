# 순수 자바로 키오스크 개발해보기
---

## 📍필수 과제
### LV 1️⃣ - Scanner를 활용한 입력 처리과 반복,조건문을 활용한 흐름제어 복습
- 햄버거 메뉴 및 출력을 선택할 수 있도록 기능 구현
- 반복문을 활용하여 특정 번호가 입력되면 프로그램을 종료

#### ✅ 구현 설명
- 단일 Main 클래스 하나로 로직을 구현
- 반복문 내부의 프로그램 흐름을 메서드로 추출하여 관리
  - menuPrinter(): 하드코딩된 메뉴를 출력
  - inputValidator(): 입력된 값을 검증하고 반환
  - selectMenu(): 입력값에 따라 로직을 분기하여 처리
    
---
### LV 2️⃣ - 객체 지향 설계를 적용해 햄버거 메뉴를 클래스로 관리
- 햄버거 메뉴를 MenuItem 클래스와 List를 통해 관리
  - 생성자 및 게터세터가 존재함


- MenuItem 클래스는 이름, 가격 설명 필드를 가지고 있음

#### ✅ 구현 설명
- MenuItem 클래스를 구현하여 Main 클래스와 MenuItem 클래스로 분리
- Main 클래스에서 List를 통해 MenuItem을 관리하고 생성

---
### LV 3️⃣ - 순서 제어를 클래스로 관리
- Kiosk 클래스를 생성하여 전체 순서 제어를 관리하여 Main 클래스는 필요한 객체를 생성, 할당, 실행만 하도록 수정
- 기존 요구사항 유지

#### ✅ 구현 설명
- Kiosk 클래스를 구현하여 프로그램 실행로직을 분리
  - Kiosk 생성시 MenuItem을 몇개를 생성할지 모르기 때문에 가변인자를 적용
  - start(): 프로그램 실행 흐름


- Main 클래스에는 Kiosk를 생성하고 MenuItem을 생성하는 로직과 start()를 호출하여 프로그램을 실행

---
### LV 4️⃣ - 음식 메뉴와 주문 내역을 클래스 기반으로 관리
- MenuItem 클래스를 관리하는 Menu 클래스를 생성하여 List\<MenuItem>를 관리
- Menu 클래스는 MenuItem의 상위 개념인 카테고리를 분류

#### ✅ 구현 설명
- Menu 클래스를 구현하여 각종 
  - add(): Kiosk를 생성할 때 MenuItem을 생성하던 로직을 해당 메서드가 가져감, 마찬가지로 가변인자 적용
  - findMenuItem(): 특정 MenuItem을 조회
  - finAllMenuItem(): 전체 MenuItem을 조회


- Kiosk 클래스는 Menu를 관리하고 프로그램 실행 흐름을 관리
  - 키오스크의 생성자로 MenuItem이 아닌 Menu를 가변인자로 받도로 설정
  - categoryPrinter(): 카테고리를 출력
  - selectMainMenu(): 특정 메뉴를 조회


- Main 클래스는 Menu를 생성하고 MenuItem을 생성후 각 메뉴에 할당한 후 Kiosk를 실행함
---
### LV 5️⃣ - 캡슐화 적용
- 각 클래스의 필드에 직접 접근하지 못하도록 캡슐화를 적용
#### ✅ 구현 설명
- 각 클래스의 필드에 캡슐화 적용

***
***

## 📍도전 과제
### LV 1️⃣ - 장바구니, 구매하기 기능 추가
- 장바구니 생성 및 관리기능 추가
  - 장바구니의 상품 출력 및 금액 계산
  - 장바구니 담기


- 주문 기능 추가
#### ✅ 구현 설명
- 금액을 포맷팅해서 보여주기 위한 PriceFormatter 유틸 클래스를 생성
  - priceFormat(): 금액 포맷팅


- ShoppingCart 클래스 생성 후 Map으로 장바구니를 관리
  - addCart(): 장바구니에 상품 및 수량 추가
  - showCart(): 장바구니의 내역 및 총 금액 출력
  - deleteAllCart(): 장바구니 전체 비우기
  - itemPrice(): 상품의 단가 * 수량을 계산


- Kiosk 클래스에 전체 흐름과 추가적인 로직이 수정됨
  - start(): 장바구니 관련 기능이 추가되어 주문, 장바구니 담기 등의 흐름이 추가됨
  - order(): 최종 주문 내역을 보여주고 최종 주문
  - XxxMenuPrinter(): 각종 메뉴들을 출력해주는 출력 메서드들
---
### LV 2️⃣ - Enum , 람다 & 스트림을 활용하여 주문 및 장바구니 관리
- Enum을 통해 할인 정책을 관리
- 람다 & 스트림을 활용한 장바구니 조회 기능 관리
  - 특정 메뉴의 빼기 기능을 추가

#### ✅ 구현 설명
- 전제 구조 변경
  - 과제 요구사항에 더 맞는 구조를 선택하고 객체지향적으로 더 맞는 구조를 선택하기 위해 구조를 변경  
  - 장바구니 관리를 Map이 아닌 List로 변경하고 수량을 MenuItem가 관리


- DiscountPolicy Enum을 생성하여 할인 정책을 관리
  - 각 대상별 할인율을 지정하고 id의 기능을하는 input, 할인율, 설명을 필드로 가지고 있음
  - 각 필드를 getter로 조회가 가능함
  - discount(): 할인 정책에 맞는 할인율을 적용하여 할인 금액을 반환


- MenuItem 클래스 수정
  - 수량을 관리하는 quantity 필드 추가하고 관련된 메서드를 추가
  - addQuantity(): 수량을 증가
  - 동등성 비교를 위해 equals()와 hashCode()를 구현 (IDE의 기능 활용)


- ShoppingCart 클래스 구조 및 기능 변경하고 대부분의 메서드 구현을 최대한 일부러 Stream을 활용함
  - 장바구니를 List 자료구조로 관리
  - showCart(): 장바구니의 내역을 출력만 하도록 기능을 분리
  - getTotalPrice(): 장바구니의 총 금액을 계산하고 반환, 출력은 포맷팅 된 금액을 출력
  - getDiscountPRice(): 할인 정책을 찾고 할인이 적용된 최종 금액을 포맷팅하여 반환
  - deleteOneCartList(): 장바구니의 특정 내역을 제거


- Kiosk 클래스 메서드들 수정
  - start(): 추가된 장바구니 관련 기능의 흐름과 할인 정책 관련 로직을 적용, 일부 로직을 별도로 메서드로 추출
  - checkOrder(): 기존의 order() 메서드가 하는 기능을 이름을 구분하기 위해 이름을 변경
  - discountWithOrder(): 할인 정책을 입력받고 적용하여 최종 주문을 완료
  - discountMenuPrinter(): 할인 정책을 출력
  - allCancel(): 기존에 start() 존재하던 장바구니를 전체 삭제하는 로직을 메서드화
  - partCancel(): 장바구니의 특정 내역을 제거하는 로직
---


