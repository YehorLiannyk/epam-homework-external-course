package main.ua.advanced.practice5.task9_auction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static main.ua.advanced.practice5.task9_auction.AuctionDefaults.*;

public class Auction {
    private static final Semaphore TAKE_STEP = new Semaphore(1);
    private static final AtomicReference<Member> whoBoughtLot = new AtomicReference<>();
    private static final AtomicInteger currentPrice = new AtomicInteger();
    private static final AtomicBoolean firstEntry = new AtomicBoolean(true);

    private AtomicReference<Member> lastMember = new AtomicReference<>();
    private Member[] members;
    private Lot[] lots;

    public static void main(String[] args) {
        Auction auction = new Auction();
        auction.lots = auction.getFilledLotArray(LOT_AMOUNT);
        auction.members = auction.getFilledMemberArray(MEMBER_AMOUNT);

        auction.startAuction();
    }

    private void startAuction() {
        for (int i = 0; i < LOT_AMOUNT; i++) {
            final Lot currentLot = lots[i];
            if (currentLot == null) {
                throw new NullPointerException();
            }
            printAuctionInfo(currentLot, members, i);
            preparingAuction(currentLot);

            ThreadGroup threadGroup = new ThreadGroup("Members");
            createAndStartMemberThreads(threadGroup);
            waitUntilThreadGroupFinish(threadGroup);
            actionAfterWin(currentLot);
        }
        afterAllLotsSold();
    }

    private void afterAllLotsSold() {
        printBorder();
        System.out.println("Auction is finished, thank you for joining");
        printBoughtLots();
        printBorder();
        System.out.println("Goodbye");
    }

    private void printBoughtLots() {
        System.out.println("List of Bought Lots is below: ");
        for (int i = 0; i < MEMBER_AMOUNT; i++) {
            List<Lot> boughtLots = members[i].getBoughtLots();
            if (!boughtLots.isEmpty()) {
                System.out.println(members[i].getName() + " bought " + Arrays.toString(boughtLots.toArray()));
            } else {
                System.out.println(members[i].getName() + " didn't buy anything");
            }
        }
    }

    private void actionAfterWin(Lot currentLot) {
        if (isAnybodyBuyLot()) {
            afterLotBuying(currentLot, whoBoughtLot.get());
        } else {
            notifyThatLotWasNotBought(currentLot);
        }
    }

    private void preparingAuction(Lot currentLot) {
        currentPrice.set(currentLot.getPrice());
        whoBoughtLot.set(null);
        firstEntry.set(true);
    }

    private boolean isAnybodyBuyLot() {
        return whoBoughtLot.get() != null;
    }

    private void notifyThatLotWasNotBought(Lot currentLot) {
        System.out.println("Nobody bought the " + currentLot.getNameLot() + " lot.");
    }

    private void afterLotBuying(Lot lot, Member member) {
        if (getLotIndex(lot) == -1 || getMemberIndex(member) == -1)
            throw new NoSuchElementException();
        int memberIndex = getMemberIndex(member);
        int lotIndex = getLotIndex(lot);
        if (buyingLotByMember(memberIndex, lotIndex)) {
            printCongratsAfterLotBuying(members[memberIndex], lot);
        }
    }

    private void printCongratsAfterLotBuying(Member member, Lot lot) {
        System.out.println("Congrats " + member.getName()
                + " with buying " + lot.getNameLot() + " for " + lot.getPrice());
    }

    private boolean buyingLotByMember(int memberIndex, int lotIndex) {
        if (doesHaveEnoughMoney(memberIndex)) {
            lots[lotIndex].setPrice(currentPrice.get());
            members[memberIndex].addBoughtLot(lots[lotIndex]);
            members[memberIndex].decreaseMoney(currentPrice.get());
            lots[lotIndex] = null;
            return true;
        } else {
            banIfHaveNoEnoughMoney(memberIndex);
            System.out.println(members[memberIndex].getName() + " was banned by having no enough money during "
                    + members[memberIndex].getAuctionBanLeft() + " sessions.");
            return false;
        }
    }

    private boolean doesHaveEnoughMoney(int memberIndex) {
        return members[memberIndex].getMoney() - currentPrice.get() >= 0;
    }

    private void banIfHaveNoEnoughMoney(int memberIndex) {
        if (!doesHaveEnoughMoney(memberIndex)) {
            members[memberIndex].banMember();
        }
    }

    private int getMemberIndex(Member member) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null && members[i].equals(member))
                return i;
        }
        return -1;
    }

    private int getLotIndex(Lot lot) {
        for (int i = 0; i < lots.length; i++) {
            if (lots[i] != null && lots[i].equals(lot))
                return i;
        }
        return -1;
    }

    private void createAndStartMemberThreads(ThreadGroup threadGroup) {
        for (int j = 0; j < MEMBER_AMOUNT; j++) {
            if (members[j].getAuctionBanLeft().get() == 0) {
                new Thread(threadGroup, members[j]).start();
            } else {
                members[j].decreaseAuctionBan();
            }
        }
    }

    private void waitUntilThreadGroupFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount() != 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printAuctionInfo(Lot lot, Member[] members, int i) {
        printBorder();
        System.out.println("Auction #" + i + " is starting");
        System.out.println("Lot: " + lot.getNameLot());
        System.out.println("Start price: " + lot.getPrice());
        System.out.println("Members amount: " + getNoBanMemberAmount(members));
        printBorder();
        printMemberMoney(members);
        printBorder();
    }

    private void printMemberMoney(Member[] members) {
        for (Member member : members) {
            if (member.getAuctionBanLeft().get() == 0)
                System.out.println(member.getName() + " has " + member.getMoney() + " coins");
        }
    }

    private void printBorder() {
        for (int i = 0; i < 20; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    private int getNoBanMemberAmount(Member[] members) {
        int amount = members.length;
        for (Member member : members) {
            if (member.getAuctionBanLeft().get() > 0)
                amount--;
        }
        return amount;
    }

    private Lot[] getFilledLotArray(int amountOfLots) {
        if (amountOfLots > LOT_NAMES.length || LOT_NAMES.length != LOT_START_PRICE.length)
            throw new IllegalArgumentException();

        Lot[] lotArr = new Lot[amountOfLots];
        for (int i = 0; i < amountOfLots; i++) {
            lotArr[i] = new Lot(LOT_NAMES[i], LOT_START_PRICE[i]);
        }
        return lotArr;
    }

    private Member[] getFilledMemberArray(int amountOfMembers) {
        if (amountOfMembers > MEMBER_NAMES.length)
            throw new IllegalArgumentException();

        Member[] memberArr = new Member[amountOfMembers];
        for (int i = 0; i < amountOfMembers; i++) {
            memberArr[i] = new Member(MEMBER_NAMES[i]);
        }
        return memberArr;
    }

    class Member implements Runnable {
        private final String name;
        private final List<Lot> boughtLots = new ArrayList<>();
        private int money;
        private AtomicInteger auctionBanLeft = new AtomicInteger(0);

        public Member(String name) {
            this.name = name;
            money = (int) (Math.random() * (MEMBER_MAX_MONEY - MEMBER_MIN_MONEY)) + MEMBER_MIN_MONEY;
        }

        public List<Lot> getBoughtLots() {
            return boughtLots;
        }

        public void addBoughtLot(Lot lot) {
            if (lot == null)
                throw new NullPointerException();
            boughtLots.add(lot);
        }

        @Override
        public void run() {
            while (haveAbilityToContinue(this) && Thread.activeCount() > 1) {
                try {
                    inRunFunc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void inRunFunc() throws InterruptedException {
            while (firstEntry.get() || !lastMember.get().equals(this)) {
                TAKE_STEP.acquire();
                lastMember = new AtomicReference<>(this);
                firstEntry.set(false);
                int stepAmount = takeStep();
                if (stepAmount > 0) {
                    ifStepAmountNotEqualZero(stepAmount);
                    TAKE_STEP.release();
                } else {
                    TAKE_STEP.release();
                    break;
                }
            }
        }

        private void ifStepAmountNotEqualZero(int stepAmount) {
            currentPrice.addAndGet(stepAmount);
            System.out.print(name + " raises the rate by " + stepAmount + ". ");
            System.out.println("New price = " + currentPrice);
            whoBoughtLot.set(this);
        }

        private boolean haveAbilityToContinue(Member member) {
            return member.getMoney() - (currentPrice.get() + MIN_STEP) >= 0;
        }

        private int takeStep() {
            int step = countStep();
            if (step != 0) {
                //probability of having no wish to take step
                if (Math.random() > PROBABILITY_SKIP_THE_STEP_INDEX) {
                    System.out.println("!== " + this.name + " skipped");
                    step = 0;
                }
            } else {
                //probability of taking step have no enough money (cheating)
                if (Math.random() > PROBABILITY_CHEATING_INDEX) {
                    step = countUnfairStep();
                    System.out.print("!== " + this.name + " cheated: ");
                }
            }
            return step;
        }

        private int countUnfairStep() {
            return (int) (Math.random() * 100) + MIN_STEP;
        }

        private int countStep() {
            if (currentPrice.get() > money + MIN_STEP)
                return 0;
            // від мінімальної ціни до загальної кількості грошей
            int maxRange = (money - currentPrice.get()) / 3;
            return (int) (Math.random() * maxRange) + MIN_STEP;
        }

        public void banMember() {
            auctionBanLeft.addAndGet(BANNED_SESSIONS);
        }

        public void decreaseAuctionBan() {
            if (auctionBanLeft.get() > 0) {
                auctionBanLeft = new AtomicInteger(auctionBanLeft.decrementAndGet());
            } else {
                auctionBanLeft = new AtomicInteger(0);
            }
        }

        public String getName() {
            return name;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public void decreaseMoney(int money) {
            this.money -= money;
        }

        public AtomicInteger getAuctionBanLeft() {
            return auctionBanLeft;
        }
    }
}
