# JPA-Redis-Integration 과제

## 📜 문제
```text
편의점과의 제휴로 총 100장의 쿠폰을 제공하기로 하였다.
쿠폰은 회사에서 발급하며 숫자와 영대소문자로 구성된 총 10자리 이다.
쿠폰생성시점은 유저가 쿠폰발급 요청시 만들어도 무관하다.
인당 1장만 받을 수 있으며, 이미 받은 유저가 발급을 시도하면 이미 받은 쿠폰을 반환한다.
100장이 모두 제공된 후에는 404 에러를 반환한다.
그 외, 유저가 실패하는 케이스는 없다.
```
## 🪄 요구 사항
> [!important]
> 분산 환경이라고 가정한다.

### 1. 유저가 쿠폰을 신청한다.
- [x] 쿠폰 발급을 신청한다.
  - [POST] /coupon/issue
- [ ] 📢 예외 처리
  - 이미 쿠폰을 발급한 상태이면 `기존의 쿠폰`을 반환한다.

### 2. 쿠폰을 생성한다.
- [x] 쿠폰을 생성한다.
  - 유저가 `발급 신청 시`에 생성한다. 
  - `숫자`와 `영대소문자`로 구성된 총 `10자리`이다. 
  - 중복의 value값을 가진 쿠폰 만드는 것은 피한다.

### 3. 쿠폰을 반환한다.
- [ ] 쿠폰은 인당 한 장만 발급 가능하다.

### 4. 쿠폰 제공 마무리
- [ ] 쿠폰의 수량은 100장이다.
- [ ] 📢 예외처리
  - 쿠폰 100장이 모두 제공된 후에는 404 에러를 반환한다.

---

## 🛠️ 단계별 구현
- [ ] 비관락을 사용하여 구현하기
- [ ] 낙관락을 사용하여 구현하기
- [ ] 분산락을 사용하여 구현하기

---

## 💡 과제 제출 요령
- [ ] github에 레포하나 파서 각 락 단위로 pr을 남길것 
- [ ] k6로 부하테스트 한 걸 pr에 포함할 것
