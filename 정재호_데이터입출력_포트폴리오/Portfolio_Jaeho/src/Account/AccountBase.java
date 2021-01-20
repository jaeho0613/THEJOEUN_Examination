package Account;

// 계좌 베이직 클레스
// - 기본 데이터
public class AccountBase {
    private String accountHolder = null; // 계좌주
    private int accountMoney = 0; // 잔액
    private static final int maxLimit = 1000000; // 최대 금액
    private static final int minLimit = 0; // 최소 금액

    public AccountBase(String holder, int deposit) {
        this.accountHolder = holder;
        setAccountMoney(deposit);
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public int getAccountDeposit() {
        return accountMoney;
    }

    public boolean setAccountMoney(int temp) {
        if (temp < minLimit) {
            System.out.println("결과 : 잔액을 확인하세요.");
            return false;
        } else if (temp > maxLimit) {
            System.out.println("결과 : 잔고 한도를 초과합니다.");
            return false;
        } else {
            this.accountMoney = temp;
            return true;
        }
    }
}
