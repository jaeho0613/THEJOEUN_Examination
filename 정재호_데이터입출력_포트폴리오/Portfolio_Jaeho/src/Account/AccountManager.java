package Account;

import java.util.HashMap;
import java.util.Map;

// 계좌 매니저 클래스 (싱글톤)
@SuppressWarnings("unused")
public class AccountManager {

    // 계좌 리스트
    private Map<String, AccountBase> accountList = new HashMap<>();

    // 기본 생성자
    private AccountManager() {}

    // Thread-safe
    private static class InnerInstanceClass {
        private static final AccountManager Instance = new AccountManager();
    }

    // Instance Get
    public static AccountManager getInstance() {
        return InnerInstanceClass.Instance;
    }

    // 리스트 정보 얻기
    public Map<String, AccountBase> getAccountList() {
        return accountList;
    }

    // 계좌 생성 메서드
    public void accountCreate(String number, AccountBase account) {
        if (accountList.size() > 1) {
            System.out.println("결과 : 계좌를 개설할 수 없습니다.");
        } else {
            accountList.put(number, account);
            System.out.println("결과 : 계좌를 생성했습니다.");
        }
    }

    // 계좌 출력 메서드
    public void accountListPrint() {
        accountList.forEach((key, value) -> System.out
                .println(key + "\t" + value.getAccountHolder() + "\t" + value.getAccountDeposit()));
    }

    // 계좌 예금,출금 액션 메서드
    public boolean accountAction(String number, int temp) {
        if (accountList.containsKey(number)) {
            if (accountList.get(number).setAccountMoney(temp)) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("결과 : 잘못된 계좌 번호 입니다.");
            return false;
        }
    }

    // 계좌 해지 메서드
    public boolean accountDestory(String number,String holder) {
        if (accountList.containsKey(number)) {
            if(accountList.get(number).getAccountHolder().equals(holder)) {
                accountList.remove(number);
                return true;
            } else {
                System.out.println("결과 : 계좌를 확인하세요.");
                return false;
            }
        } else {
            System.out.println("결과 : 계좌를 확인하세요.");
            return false;
        }
    }

    // 액션 변경 메서드
    public Action checkAction(int number) {
        switch (number) {
            case 1:
                return Action.CREATE;
            case 2:
                return Action.PRINTF;
            case 3:
                return Action.DEPOSIT;
            case 4:
                return Action.WITHDRAWAL;
            case 5:
                return Action.DESTORY;
            case 6:
                return Action.EXIT;
            default:
                System.out.println("결과 : 잘못된 입력 입니다.");
                return Action.DEFAULT;
        }
    }
}
