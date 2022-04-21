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

    private AtomicReference<Member> lastMember = new AtomicReference<>(/*new Member("FirstEntryMember")*/);
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
            System.out.println("== was bought by " + whoBoughtLot.get().getName());
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
        if (buyingLotByMember(member, memberIndex, lotIndex)) {
            printCongratsAfterLotBuying(members[memberIndex], lot);
        }
    }

    private void printCongratsAfterLotBuying(Member member, Lot lot) {
        System.out.println("Congrats " + member.getName()
                + " with buying " + lot.getNameLot() + " for " + lot.getPrice());
    }

    private boolean buyingLotByMember(Member member, int memberIndex, int lotIndex) {
        if (doesHaveEnoughMoney(member)) {
            lots[lotIndex].setPrice(currentPrice.get());
            members[memberIndex].addBoughtLot(lots[lotIndex]);
            members[memberIndex].decreaseMoney(currentPrice.get());
            lots[lotIndex] = null;
            return true;
        } else {
            banIfHaveNoEnoughMoney(member);
            System.out.println(member.getName() + " was banned by having no enough money during "
                    + member.getAuctionBanLeft() + " sessions.");
            return false;
        }
    }

    private boolean doesHaveEnoughMoney(Member member) {
        return member.getMoney() - currentPrice.get() >= 0;
    }

    private void banIfHaveNoEnoughMoney(Member member) {
        if (doesHaveEnoughMoney(member)) {
            members[getMemberIndex(member)].banMember();
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
            if (members[j].getAuctionBanLeft() == 0) {
                new Thread(threadGroup, members[j]).start();
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
            if (member.getAuctionBanLeft() == 0)
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
            if (member.getAuctionBanLeft() > 0)
                amount--;
        }
        return amount;
    }

    private Lot[] getFilledLotArray(int amountOfLots) {
        if (amountOfLots != LOT_NAMES.length || LOT_NAMES.length != LOT_START_PRICE.length)
            throw new IllegalArgumentException();

        Lot[] lotArr = new Lot[amountOfLots];
        for (int i = 0; i < amountOfLots; i++) {
            lotArr[i] = new Lot(LOT_NAMES[i], LOT_START_PRICE[i]);
        }
        return lotArr;
    }

    private Member[] getFilledMemberArray(int amountOfMembers) {
        if (amountOfMembers != MEMBER_NAMES.length)
            throw new IllegalArgumentException();

        Member[] memberArr = new Member[amountOfMembers];
        for (int i = 0; i < amountOfMembers; i++) {
            memberArr[i] = new Member(MEMBER_NAMES[i]);
        }
        return memberArr;
    }

    /*public AtomicInteger getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(AtomicInteger currentPrice) {
        this.currentPrice = currentPrice;
    }*/

    class Member implements Runnable {
        private final String name;
        private final List<Lot> boughtLots = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(3); // max number of skipping
        private int money;
        private int auctionBanLeft = 0;

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
                    while (firstEntry.get() || !lastMember.get().equals(this)) {
                        TAKE_STEP.acquire();
                        lastMember = new AtomicReference<>(this);
                        System.out.println("== " + this.name + " entry // lastMember " + lastMember.get().getName());
                        firstEntry.set(false);
                        int stepAmount = takeStep();
                        if (stepAmount > 0) {
                            currentPrice.addAndGet(stepAmount);
                            System.out.print(name + " raises the rate by " + stepAmount + ". ");
                            System.out.println("New price = " + currentPrice);
                            System.out.println("== " + this.name + " left  // lastMember " + lastMember.get().getName());
                            whoBoughtLot.set(this);
                            TAKE_STEP.release();
                        } else {
                            System.out.println("== " + this.name + " left  // lastMember " + lastMember.get().getName());
                            TAKE_STEP.release();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("== " + this.name + " left out loop // lastMember " + lastMember.get().getName());
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
            auctionBanLeft += 3;
        }

        public void decreaseAuctionBan() {
            auctionBanLeft = auctionBanLeft > 0 ? --auctionBanLeft : 0;
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

        public int getAuctionBanLeft() {
            return auctionBanLeft;
        }
    }
}
