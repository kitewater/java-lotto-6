package controller;

import camp.nextstep.edu.missionutils.Console;
import model.Lotto;
import model.User;
import model.WinningNumber;
import service.LottoService;
import validate.Validator;
import view.InputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    InputView inputView = new InputView();
    Validator validator = new Validator();
    LottoService lottoService = new LottoService();

    public void startLotto(){
        int price = AskPrice();
        User user = new User(price, lottoService.makeLottoList(price));
        WinningNumber winning_numbers = new WinningNumber(AskWinningNumbers(),AskBonus());


    }
    public int AskPrice(){
        inputView.printAskPrice();
        return inputPrice();
    }
    public int inputPrice(){
        String my_price = Console.readLine();
        try{
            validator.checkInteger(my_price);
            validator.checkNull(my_price);
            validator.checkPrice1000(Integer.parseInt(my_price));
        }catch (NullPointerException e){
            return AskPrice();
        }catch (IllegalArgumentException e){
            return AskPrice();
        }
        return Integer.parseInt(my_price);
    }


    public List<Integer> AskWinningNumbers(){
        List<Integer> winningNumberList = new ArrayList<>();

        inputView.printWinningNumbers();
        return inputWinningNumber();
    }

    public List<Integer> inputWinningNumber(){
        List<Integer> winningNumberList = new ArrayList<>();
        try {
            lottoService.makeWinningNumber(Console.readLine());
            validator.checkLottoRange(winningNumberList);
        }catch (NumberFormatException e){
            return inputWinningNumber();
        }catch (IllegalArgumentException e){
            return inputWinningNumber();
        }
        return winningNumberList;
    }

    public int AskBonus(){
        inputView.printAskBonusNumber();
        return inputBonus();
    }

    public int inputBonus(){
        String bonus = Console.readLine();
        try {
            validator.checkNull(bonus);
            validator.checkInteger(bonus);
        }catch (NullPointerException e){
            return AskBonus();
        }catch (IllegalArgumentException e){
            return AskBonus();
        }
        return Integer.parseInt(bonus);
    }
}
