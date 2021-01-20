package Account;

public enum Action {
    DEFAULT, // 기본 상태
    CREATE, // 계좌 생성 상태
    PRINTF, // 계좌 출력 상태
    DEPOSIT, // 계좌 예금 상태
    WITHDRAWAL, // 계좌 출금 상태
    DESTORY, // 계좌 해지 상태
    EXIT; // 종료 상태
}
