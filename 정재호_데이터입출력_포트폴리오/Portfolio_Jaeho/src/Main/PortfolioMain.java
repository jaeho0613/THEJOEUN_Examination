package Main;

import java.util.InputMismatchException;
import java.util.Scanner;
import Account.AccountBase;
import Account.AccountManager;
import Account.Action;

public class PortfolioMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = AccountManager.getInstance();
        Action myAction = Action.DEFAULT;
        int input = 0;

        while (!(myAction == Action.EXIT)) {
            try {
                System.out.println(
                        "------------------------------------------------------------------");
                System.out.println("1.계좌 생성 | 2.계좌 목록 | 3.예금 | 4.출금 | 5.계좌 해지 | 6.종료");
                System.out.println(
                        "------------------------------------------------------------------");
                System.out.print("입력> ");

                input = scanner.nextInt();

                myAction = accountManager.checkAction(input);

                switch (myAction) {
                    case CREATE:
                        System.out.println("---------");
                        System.out.println("계좌 생성");
                        System.out.println("---------");

                        System.out.print("계좌번호 : ");
                        String createNumber = scanner.next();

                        System.out.print("계좌주 : ");
                        String createHolder = scanner.next();

                        System.out.print("초기 금액 : ");
                        int createMoney = scanner.nextInt();

                        if (!accountManager.getAccountList().containsKey(createNumber)) {
                            AccountBase accountBase = new AccountBase(createHolder, createMoney);
                            if (accountBase.getAccountDeposit() != 0
                                    && accountBase.getAccountHolder() != null) {
                                accountManager.accountCreate(createNumber, accountBase);
                            }
                        } else {
                            System.out.println("결과 : 중복된 계좌번호가 있습니다.");
                        }
                        break;

                    case PRINTF:
                        System.out.println("---------");
                        System.out.println("계좌 목록");
                        System.out.println("---------");
                        accountManager.accountListPrint();
                        break;

                    case DEPOSIT:
                        System.out.println("-------");
                        System.out.println("예금");
                        System.out.println("-------");
                        System.out.print("계좌 번호 : ");
                        String depositNumber = scanner.next();
                        System.out.print("예금액 : ");
                        int depositMoney = scanner.nextInt();

                        if (accountManager.getAccountList().containsKey(depositNumber)) {
                            int listMoney = accountManager.getAccountList().get(depositNumber)
                                    .getAccountDeposit();
                            if (accountManager.accountAction(depositNumber,
                                    listMoney + depositMoney)) {
                                System.out.println("결과 : 예금이 성공했습니다.");
                            }
                        } else {
                            System.out.println("결과 : 계좌번호를 확인하세요.");
                        }
                        break;

                    case WITHDRAWAL:
                        System.out.println("-------");
                        System.out.println("출금");
                        System.out.println("-------");
                        System.out.print("계좌 번호 : ");
                        String withNumber = scanner.next();
                        System.out.print("출금액 : ");
                        int withMoney = scanner.nextInt();

                        if (accountManager.getAccountList().containsKey(withNumber)) {
                            int listMoney2 = accountManager.getAccountList().get(withNumber)
                                    .getAccountDeposit();
                            if (accountManager.accountAction(withNumber, listMoney2 - withMoney)) {
                                System.out.println("결과 : 출금이 성공했습니다.");
                            }
                        } else {
                            System.out.println("결과 : 계좌번호를 확인하세요.");
                        }

                        break;

                    case DESTORY:
                        System.out.println("-------");
                        System.out.println("계좌 해지");
                        System.out.println("-------");
                        System.out.print("계좌 번호 : ");
                        String destroyNumber = scanner.next();
                        System.out.print("계좌주 : ");
                        String destroyHolder = scanner.next();
                        if (accountManager.accountDestory(destroyNumber, destroyHolder)) {
                            System.out.println("결과 : 계좌 해지에 성공했습니다.");
                        }
                        break;

                    case EXIT:
                        System.out.println("종료");
                        break;

                    case DEFAULT:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("결과 : 잘못된 입력입니다.");
                scanner.next();
                continue;
            }
        }
    }
}
